package com.temp.server.requesthandlers;

import com.temp.common.requests.GetContactsRequest;
import com.temp.common.responses.ErrorResponse;
import com.temp.common.responses.GetContactsResponse;
import com.temp.common.responses.Response;
import com.temp.model.models.User;
import com.temp.model.services.UserService;
import com.temp.model.services.impl.UserServiceImpl;
import com.temp.server.ServerThread;
import com.temp.server.UserSessionInfo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GetContactsRequestHandler implements RequestHandler<GetContactsRequest> {
    @Override
    public Response handle(GetContactsRequest request, ServerThread callerThread, LinkedList<ServerThread> threads) {
        UserSessionInfo userSessionInfo = callerThread.getUserSessionInfo();
        User requester = userSessionInfo.getUser();

        if (requester == null) {
            return new ErrorResponse("Log in first");
        }

        UserService userService = new UserServiceImpl();
        List<User> users = userService.getAllUsers();

        List<String> contacts = new ArrayList<>();
        for (User user: users) {
            if (user.getId() != requester.getId()) {
                contacts.add(user.getUsername());
            }
        }

        return new GetContactsResponse(contacts);
    }
}
