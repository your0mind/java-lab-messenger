package com.temp.server.requesthandlers;

import com.temp.common.models.ChatMessage;
import com.temp.common.models.Contact;
import com.temp.common.requests.SendDialogMessageRequest;
import com.temp.common.responses.CreateDialogResponse;
import com.temp.common.responses.ErrorMessage;
import com.temp.common.responses.Response;
import com.temp.common.responses.SendDialogMessageResponse;
import com.temp.common.updates.DialogMessageUpdate;
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

import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.logging.Level;

public class SendDialogMessageRequestHandler implements RequestHandler<SendDialogMessageRequest> {

    @Override
    public Response handle(SendDialogMessageRequest request, ServerThread callerThread,
                           LinkedList<ServerThread> threads) {
        UserSessionInfo userSessionInfo = callerThread.getUserSessionInfo();
        User requester = userSessionInfo.getUser();

        if (requester == null) {
            return new CreateDialogResponse(new ErrorMessage("Log in first"));
        }

        UserService userService = new UserServiceImpl();
        User user = userService.findUser(request.getParams().getDialogContact().getUsername());

        if (user == null) {
            return new SendDialogMessageResponse(new ErrorMessage("Unknown contact"));
        }

        DialogService dialogService = new DialogServiceImpl();
        Dialog dialog = dialogService.findDialog(requester, user);

        if (dialog == null) {
            ErrorMessage error = new ErrorMessage("You have no dialog with that contact");
            return new SendDialogMessageResponse(error);
        }

        DialogMessageService dialogMessageService = new DialogMessageServiceImpl();
        int id = dialogMessageService.saveMessage(new DialogMessage(dialog.getId(),
                requester.getId(), request.getParams().getText()));
        DialogMessage dialogMessage = dialogMessageService.findMessage(id);

        ServerThread contactThread = threads.stream()
                .filter(t -> t.getUserSessionInfo().getUser().getId() == user.getId())
                .filter(t -> t.getUserSessionInfo().getListeningDialogId() == dialog.getId())
                .findFirst()
                .orElse(null);

        Contact sender = new Contact(requester.getUsername());
        String text = dialogMessage.getText();
        Date date = dialogMessage.getDate();

        ChatMessage chatMessage = new ChatMessage(sender, text, date);

        if (contactThread != null) {
            try {
                contactThread.sendMessage(new DialogMessageUpdate(chatMessage));
            } catch (IOException e) {
                logger.log(Level.SEVERE, "Failed to send dialog contact update to user", e);
            }
        }

        return new SendDialogMessageResponse(chatMessage);
    }
}
