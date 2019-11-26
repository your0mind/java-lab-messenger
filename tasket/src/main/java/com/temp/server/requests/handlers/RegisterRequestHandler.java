package com.temp.server.requests.handlers;

import com.temp.common.requests.RegisterRequest;
import com.temp.common.responses.ErrorResponse;
import com.temp.common.responses.RegisterResponse;
import com.temp.common.responses.Response;
import com.temp.model.models.User;
import com.temp.model.services.UserService;
import com.temp.model.services.impl.UserServiceImpl;
import com.temp.server.ServerThread;

import java.util.LinkedList;

public class RegisterRequestHandler implements RequestHandler<RegisterRequest> {
    @Override
    public Response handle(RegisterRequest request, ServerThread callerThread, LinkedList<ServerThread> threads) {
        User requester = request.getParams().getUser();

        UserService userService = new UserServiceImpl();
        User user = userService.findUserByUsername(requester.getUsername());

        if (user != null) {
            return new ErrorResponse("User with this username is already exists");
        } else {
            userService.saveUser(requester);
            return new RegisterResponse();
        }
    }
}
