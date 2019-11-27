package com.temp.server.requesthandlers;

import com.temp.common.requests.SignUpRequest;
import com.temp.common.requests.params.SignUpRequestParams;
import com.temp.common.responses.ErrorResponse;
import com.temp.common.responses.Response;
import com.temp.common.responses.SignUpResponse;
import com.temp.model.models.User;
import com.temp.model.services.UserService;
import com.temp.model.services.impl.UserServiceImpl;
import com.temp.server.ServerThread;

import java.util.LinkedList;

public class SignUpRequestHandler implements RequestHandler<SignUpRequest> {
    @Override
    public Response handle(SignUpRequest request, ServerThread callerThread, LinkedList<ServerThread> threads) {
        SignUpRequestParams params = request.getParams();

        UserService userService = new UserServiceImpl();
        User user = userService.findUserByUsername(params.getUsername());

        if (user != null) {
            return new ErrorResponse("User with this username is already exists");
        } else {
            userService.saveUser(new User(params.getUsername(), params.getPassword()));
            return new SignUpResponse();
        }
    }
}
