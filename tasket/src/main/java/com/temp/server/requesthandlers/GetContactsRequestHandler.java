package com.temp.server.requesthandlers;

import com.temp.common.requests.GetContactsRequest;
import com.temp.common.responses.ErrorMessage;
import com.temp.common.responses.GetContactsResponse;
import com.temp.common.responses.Response;
import com.temp.common.models.Contact;
import com.temp.model.models.User;
import com.temp.model.services.UserService;
import com.temp.model.services.impl.UserServiceImpl;
import com.temp.server.ServerThread;
import com.temp.server.UserSessionInfo;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class GetContactsRequestHandler implements RequestHandler<GetContactsRequest> {
    @Override
    public Response handle(GetContactsRequest request, ServerThread callerThread,
                           LinkedList<ServerThread> threads) {
        UserSessionInfo userSessionInfo = callerThread.getUserSessionInfo();
        User requester = userSessionInfo.getUser();

        if (requester == null) {
            return new GetContactsResponse(new ErrorMessage(("Log in first")));
        }

        UserService userService = new UserServiceImpl();
        List<User> users = userService.getAllUsersExcept(requester);
        List<Contact> contacts = users.stream()
                .map(u -> new Contact(u.getUsername())).collect(Collectors.toList());

        return new GetContactsResponse(contacts);
    }
}
