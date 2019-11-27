package com.temp.server.requesthandlers;

import com.temp.common.requests.Request;
import com.temp.common.responses.Response;
import com.temp.server.ServerThread;

import java.util.LinkedList;
import java.util.logging.Logger;

public interface RequestHandler<T extends Request> {
    Logger logger = Logger.getLogger(RequestHandler.class.getSimpleName());
    Response handle(T request, ServerThread callerThread, LinkedList<ServerThread> threads);
}
