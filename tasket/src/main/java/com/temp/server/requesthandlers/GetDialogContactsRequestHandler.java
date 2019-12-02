package com.temp.server.requesthandlers;

import com.temp.common.requests.GetDialogContactsRequest;
import com.temp.common.responses.GetDialogContactsResponse;
import com.temp.common.responses.Response;
import com.temp.model.models.Contact;
import com.temp.model.models.Dialog;
import com.temp.model.models.User;
import com.temp.model.services.DialogService;
import com.temp.model.services.UserService;
import com.temp.model.services.impl.DialogServiceImpl;
import com.temp.model.services.impl.UserServiceImpl;
import com.temp.server.ServerThread;
import com.temp.server.UserSessionInfo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GetDialogContactsRequestHandler implements RequestHandler<GetDialogContactsRequest> {
    @Override
    public Response handle(GetDialogContactsRequest request, ServerThread callerThread, LinkedList<ServerThread> threads) {
        UserSessionInfo userSessionInfo = callerThread.getUserSessionInfo();
        User requester = userSessionInfo.getUser();

        if (requester == null) {
            return new GetDialogContactsResponse("Log in first");
        }

        DialogService dialogService = new DialogServiceImpl();
        List<Dialog> dialogs = dialogService.findAllDialogsByUser(requester);

        UserService userService = new UserServiceImpl();
        List<Contact> contacts = new ArrayList<>();

        // Set requester as user1 in dialog
        for (Dialog dialog: dialogs) {
            if (dialog.getUser1Id() == requester.getId()) {
                contacts.add(new Contact(userService.findUser(dialog.getUser2Id())));
            } else {
                contacts.add(new Contact(userService.findUser(dialog.getUser1Id())));
            }
        }

        boolean listenUpdates = request.getParams().isListenUpdates();
        userSessionInfo.setListenDialogContactUpdates(listenUpdates);

        return new GetDialogContactsResponse(contacts);
    }
}
