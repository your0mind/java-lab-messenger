package com.temp.server.requests.handlers;

import com.temp.common.requests.LoginRequest;
import com.temp.common.responses.ErrorResponse;
import com.temp.common.responses.LoginResponse;
import com.temp.common.responses.Response;
import com.temp.model.models.User;
import com.temp.model.services.UserService;
import com.temp.model.services.impl.UserServiceImpl;

public class LoginRequestHandler implements RequestHandler<LoginRequest> {
    @Override
    public Response handle(LoginRequest request) {
        User requester = request.getRequester();

        UserService userService = new UserServiceImpl();
        User user = userService.findUserByUsername(requester.getUsername());

        if (user == null) {
            return new ErrorResponse("Unknown username");
        } else if (!requester.getPassword().equals(user.getPassword())) {
            return new ErrorResponse("Invalid password");
        } else {
            return new LoginResponse(user.getId());
        }
    }
}
