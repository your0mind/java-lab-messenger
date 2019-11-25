package com.temp.server.requests.handlers;

import com.temp.common.requests.ListenNewDialogsRequest;
import com.temp.common.responses.ErrorResponse;
import com.temp.common.responses.ListenNewDialogsResponse;
import com.temp.common.responses.Response;
import com.temp.model.models.User;
import com.temp.server.ServerThread;
import com.temp.server.UserSessionInfo;

import java.util.LinkedList;

public class ListenNewDialogsRequestHandler implements RequestHandler<ListenNewDialogsRequest> {
    @Override
    public Response handle(ListenNewDialogsRequest request, UserSessionInfo userSessionInfo, LinkedList<ServerThread> serverThreads) {
        User requester = userSessionInfo.getUser();

        if (requester == null || requester.getId() == 0) {
            return new ErrorResponse("Log in first");
        }

        userSessionInfo.setListenNewDialogs(true);

        return new ListenNewDialogsResponse();
    }
}
