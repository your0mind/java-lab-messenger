package com.temp.server.requesthandlers;

import com.temp.common.requests.CreateDialogRequest;
import com.temp.common.responses.CreateDialogResponse;
import com.temp.common.responses.Response;
import com.temp.common.updates.DialogContactUpdate;
import com.temp.model.models.Contact;
import com.temp.model.models.Dialog;
import com.temp.model.models.User;
import com.temp.model.services.DialogService;
import com.temp.model.services.UserService;
import com.temp.model.services.impl.DialogServiceImpl;
import com.temp.model.services.impl.UserServiceImpl;
import com.temp.server.ServerThread;
import com.temp.server.UserSessionInfo;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;

public class CreateDialogRequestHandler implements RequestHandler<CreateDialogRequest> {

    @Override
    public Response handle(CreateDialogRequest request, ServerThread callerThread, LinkedList<ServerThread> threads) {
        UserSessionInfo userSessionInfo = callerThread.getUserSessionInfo();
        User requester = userSessionInfo.getUser();

        if (requester == null) {
            return new CreateDialogResponse("Log in first");
        }

        UserService userService = new UserServiceImpl();
        User userContact = userService.findUser(request.getParams().getContact().getUsername());

        if (userContact == null) {
            return new CreateDialogResponse("Unknown contact");
        } else if (userContact.getId() == requester.getId()) {
            return new CreateDialogResponse("You can't create dialog with yourself");
        }

        DialogService dialogService = new DialogServiceImpl();
        List<Dialog> dialogs = dialogService.findAllDialogsByUser(requester);

        for (Dialog dialog: dialogs) {
            if (dialog.getUser1Id() == userContact.getId() || dialog.getUser2Id() == userContact.getId()) {
                return new CreateDialogResponse("You already have dialog with this contact");
            }
        }

        Dialog dialog = new Dialog(requester.getId(), userContact.getId());
        dialogService.saveDialog(dialog);

        ServerThread contactThread = threads.stream()
                .filter(t -> t.getUserSessionInfo().getUser().getId() == userContact.getId())
                .filter(t -> t.getUserSessionInfo().isListenDialogContactUpdates())
                .findFirst()
                .orElse(null);

        if (contactThread != null) {
            try {
                contactThread.sendMessage(new DialogContactUpdate(new Contact(requester)));
            } catch (IOException e) {
                logger.log(Level.SEVERE, "Failed to send dialog contact update to user", e);
            }
        }

        return new CreateDialogResponse(new Contact(userContact));
    }
}
