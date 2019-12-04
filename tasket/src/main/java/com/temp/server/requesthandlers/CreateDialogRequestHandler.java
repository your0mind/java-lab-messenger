package com.temp.server.requesthandlers;

import com.temp.common.requests.CreateDialogRequest;
import com.temp.common.responses.CreateDialogResponse;
import com.temp.common.responses.ErrorMessage;
import com.temp.common.responses.Response;
import com.temp.common.updates.DialogContactUpdate;
import com.temp.common.models.Contact;
import com.temp.model.models.Dialog;
import com.temp.model.models.User;
import com.temp.model.services.DialogService;
import com.temp.model.services.UserService;
import com.temp.model.services.impl.DialogServiceImpl;
import com.temp.model.services.impl.UserServiceImpl;
import com.temp.server.ServerThread;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;

public class CreateDialogRequestHandler implements RequestHandler<CreateDialogRequest> {
    @Override
    public Response handle(CreateDialogRequest request, ServerThread callerThread,
                           LinkedList<ServerThread> threads) {
        User requester = callerThread.getUserSessionInfo().getUser();

        if (requester == null) {
            return new CreateDialogResponse(new ErrorMessage("Log in first"));
        }

        UserService userService = new UserServiceImpl();
        User user = userService.findUser(request.getParams().getContact().getUsername());

        if (user == null) {
            return new CreateDialogResponse(new ErrorMessage("Unknown contact"));
        } else if (user.getId() == requester.getId()) {
            ErrorMessage error = new ErrorMessage("Dialog with oneself is inaccessible");
            return new CreateDialogResponse(error);
        }

        DialogService dialogService = new DialogServiceImpl();
        List<Dialog> dialogs = dialogService.findAllDialogsByUser(requester);

        for (Dialog dialog: dialogs) {
            if (dialog.getUser1Id() == user.getId() || dialog.getUser2Id() == user.getId()) {
                ErrorMessage error = new ErrorMessage("You already have dialog with this contact");
                return new CreateDialogResponse(error);
            }
        }

        Dialog dialog = new Dialog(requester.getId(), user.getId());
        dialogService.saveDialog(dialog);

        ServerThread contactThread = threads.stream()
                .filter(t -> t.getUserSessionInfo().getUser().getId() == user.getId())
                .filter(t -> t.getUserSessionInfo().isListenDialogContactUpdates())
                .findFirst()
                .orElse(null);

        if (contactThread != null) {
            try {
                Contact updateContact = new Contact(requester.getUsername());
                contactThread.sendMessage(new DialogContactUpdate(updateContact));
            } catch (IOException e) {
                logger.log(Level.SEVERE, "Failed to send dialog contact update to user", e);
            }
        }

        return new CreateDialogResponse(new Contact(user.getUsername()));
    }
}
