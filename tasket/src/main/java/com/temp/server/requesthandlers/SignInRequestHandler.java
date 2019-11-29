package com.temp.server.requesthandlers;

import com.temp.common.requests.SignInRequest;
import com.temp.common.requests.params.SignInRequestParams;
import com.temp.common.responses.Response;
import com.temp.common.responses.SignInResponse;
import com.temp.model.models.User;
import com.temp.model.services.UserService;
import com.temp.model.services.impl.UserServiceImpl;
import com.temp.server.ServerThread;

import java.util.LinkedList;

public class SignInRequestHandler implements RequestHandler<SignInRequest> {
    @Override
    public Response handle(SignInRequest request, ServerThread callerThread, LinkedList<ServerThread> threads) {
        SignInRequestParams params = request.getParams();

        UserService userService = new UserServiceImpl();
        User user = userService.findUser(params.getUsername());

        if (user == null) {
            return new SignInResponse("Unknown username");
        } else if (!params.getPassword().equals(user.getPassword())) {
            return new SignInResponse("Invalid password");
        } else {
            callerThread.getUserSessionInfo().setUser(user);
            return new SignInResponse();
        }
    }
}
