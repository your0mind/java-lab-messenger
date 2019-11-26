package com.temp.server.requests.handlers;

import com.temp.common.requests.GetDialogsRequest;
import com.temp.common.responses.ErrorResponse;
import com.temp.common.responses.GetDialogsResponse;
import com.temp.common.responses.Response;
import com.temp.model.models.Dialog;
import com.temp.model.models.User;
import com.temp.model.services.DialogService;
import com.temp.model.services.impl.DialogServiceImpl;
import com.temp.server.ServerThread;
import com.temp.server.UserSessionInfo;

import java.util.LinkedList;
import java.util.List;

public class GetDialogsRequestHandler implements RequestHandler<GetDialogsRequest> {
    @Override
    public Response handle(GetDialogsRequest request, ServerThread callerThread, LinkedList<ServerThread> threads) {
        UserSessionInfo userSessionInfo = callerThread.getUserSessionInfo();
        User requester = userSessionInfo.getUser();

        if (requester == null) {
            return new ErrorResponse("Log in first");
        }

        DialogService dialogService = new DialogServiceImpl();
        List<Dialog> dialogs = dialogService.findAllDialogsByUserId(requester.getId());

        boolean listenUpdates = request.getParams().isListenUpdates();
        userSessionInfo.setListenDialogsUpdates(listenUpdates);

        return new GetDialogsResponse(dialogs);
    }
}
