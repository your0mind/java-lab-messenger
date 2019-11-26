package com.temp.server.requesthandlers;

import com.temp.common.requests.Request;
import com.temp.common.responses.Response;
import com.temp.server.ServerThread;

import java.util.LinkedList;

public interface RequestHandler<T extends Request> {
    Response handle(T request, ServerThread callerThread, LinkedList<ServerThread> threads);
}
