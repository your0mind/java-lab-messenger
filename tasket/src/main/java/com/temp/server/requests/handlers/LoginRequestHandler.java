package com.temp.server.requests.handlers;

import com.temp.common.requests.LoginRequest;
import com.temp.common.responses.ErrorResponse;
import com.temp.common.responses.LoginResponse;
import com.temp.common.responses.Response;
import com.temp.model.models.User;
import com.temp.model.models.services.UserService;
import com.temp.model.models.services.UserServiceImpl;

public class LoginRequestHandler implements RequestHandler<LoginRequest> {
    @Override
    public Response handle(LoginRequest request) {
        User requester = request.getRequester();

        UserService userService = new UserServiceImpl();
        User user = userService.findUserByUsername(requester.username);

        if (user == null) {
            return new ErrorResponse("Unknown username");
        } else if (!requester.password.equals(user.password)) {
            return new ErrorResponse("Invalid password");
        } else {
            return new LoginResponse(user.id);
        }
    }
}
