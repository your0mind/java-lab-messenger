package com.temp.server.requesthandlers;

import com.temp.common.models.ChatMessage;
import com.temp.common.models.Contact;
import com.temp.common.requests.GetDialogMessagesRequest;
import com.temp.common.responses.ErrorMessage;
import com.temp.common.responses.GetDialogMessagesResponse;
import com.temp.common.responses.Response;
import com.temp.model.models.Dialog;
import com.temp.model.models.DialogMessage;
import com.temp.model.models.User;
import com.temp.model.services.DialogMessageService;
import com.temp.model.services.DialogService;
import com.temp.model.services.UserService;
import com.temp.model.services.impl.DialogMessageServiceImpl;
import com.temp.model.services.impl.DialogServiceImpl;
import com.temp.model.services.impl.UserServiceImpl;
import com.temp.server.ServerThread;
import com.temp.server.UserSessionInfo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GetDialogMessagesRequestHandler implements RequestHandler<GetDialogMessagesRequest> {
    @Override
    public Response handle(GetDialogMessagesRequest request, ServerThread callerThread,
                           LinkedList<ServerThread> threads) {
        UserSessionInfo userSessionInfo = callerThread.getUserSessionInfo();
        User requester = userSessionInfo.getUser();

        if (requester == null) {
            return new GetDialogMessagesResponse(new ErrorMessage("Log in first"));
        }

        UserService userService = new UserServiceImpl();
        User user = userService.findUser(request.getParams().getDialogContact().getUsername());

        if (user == null) {
            return new GetDialogMessagesResponse(new ErrorMessage("Unknown contact"));
        }

        DialogService dialogService = new DialogServiceImpl();
        Dialog dialog = dialogService.findDialog(requester, user);

        if (dialog == null) {
            ErrorMessage errorMessage = new ErrorMessage("You have no dialog with that contact");
            return new GetDialogMessagesResponse(errorMessage);
        }

        DialogMessageService dialogMessageService = new DialogMessageServiceImpl();
        List<DialogMessage> dialogMessages = dialogMessageService.findAllMessages(dialog);

        List<ChatMessage> messages = new ArrayList<>();
        for (DialogMessage m : dialogMessages) {
            User sender = (user.getId() == m.getUserId()) ? user : requester;
            Contact contact = new Contact(sender.getUsername());
            messages.add(new ChatMessage(contact, m.getText(), m.getDate()));
        }

        if (request.getParams().isListenUpdates()) {
            userSessionInfo.setListeningDialogId(dialog.getId());
        }

        return new GetDialogMessagesResponse(messages);
    }
}
