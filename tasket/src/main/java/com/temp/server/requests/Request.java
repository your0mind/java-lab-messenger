package com.temp.server.requests;

import com.google.gson.JsonObject;

public interface Request {
    void handle(JsonObject objectRequest);
}
