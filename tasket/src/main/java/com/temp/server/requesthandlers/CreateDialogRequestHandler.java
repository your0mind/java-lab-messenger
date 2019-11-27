package com.temp.server.requesthandlers;

import com.temp.common.requests.CreateDialogRequest;
import com.temp.common.responses.CreateDialogResponse;
import com.temp.common.responses.ErrorResponse;
import com.temp.common.responses.Response;
import com.temp.common.updates.DialogContactUpdate;
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
import java.util.logging.Level;

public class CreateDialogRequestHandler implements RequestHandler<CreateDialogRequest> {

    @Override
    public Response handle(CreateDialogRequest request, ServerThread callerThread, LinkedList<ServerThread> threads) {
        UserSessionInfo userSessionInfo = callerThread.getUserSessionInfo();
        User requester = userSessionInfo.getUser();

        if (requester == null) {
            return new ErrorResponse("Log in first");
        }

        UserService userService = new UserServiceImpl();
        User userContact = userService.findUserByUsername(request.getParams().getContactUsername());

        if (userContact == null) {
            return new ErrorResponse("Unknown contact");
        } else if (userContact.getId() == requester.getId()) {
            return new ErrorResponse("You can't create dialog with yourself");
        }

        DialogService dialogService = new DialogServiceImpl();
        List<Dialog> dialogs = dialogService.findAllDialogsByUserId(requester.getId());

        for (Dialog dialog: dialogs) {
            if (dialog.getUser1Id() == userContact.getId() || dialog.getUser2Id() == userContact.getId()) {
                return new ErrorResponse("You already have dialog with this contact");
            }
        }

        Dialog dialog = new Dialog(requester.getId(), userContact.getId());
        dialogService.saveDialog(dialog);

        for (ServerThread thread: threads) {
            UserSessionInfo usInfo = thread.getUserSessionInfo();
            if (usInfo.getUser().getId() == userContact.getId() && usInfo.isListenDialogContactUpdates()) {
                try {
                    thread.sendMessage(new DialogContactUpdate(requester.getUsername()));
                } catch (IOException e) {
                    logger.log(Level.SEVERE, "Failed to send dialog contact update to user", e);
                }
                break;
            }
        }

        return new CreateDialogResponse(userContact.getUsername());
    }
}
