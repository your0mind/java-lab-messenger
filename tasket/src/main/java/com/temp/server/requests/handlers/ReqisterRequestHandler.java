package com.temp.server.requests.handlers;

import com.temp.common.requests.RegisterRequest;
import com.temp.common.responses.ErrorResponse;
import com.temp.common.responses.RegisterResponse;
import com.temp.common.responses.Response;
import com.temp.model.models.User;
import com.temp.model.models.services.UserService;
import com.temp.model.models.services.UserServiceImpl;

public class ReqisterRequestHandler implements RequestHandler<RegisterRequest> {
    @Override
    public Response handle(RegisterRequest request) {
        User requester = request.getRequester();

        UserService userService = new UserServiceImpl();
        User user = userService.findUserByUsername(requester.username);

        if (user != null) {
            return new ErrorResponse("User with this username is already exists");
        } else {
            userService.saveUser(requester);
            return new RegisterResponse();
        }
    }
}
