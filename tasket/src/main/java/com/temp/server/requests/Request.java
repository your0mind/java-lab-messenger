package com.temp.server.requests;

import com.temp.model.models.User;

public interface Request {
    void handle(User requester, String params);
}
