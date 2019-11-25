package com.temp.server.requests.handlers;

import com.temp.common.requests.Request;
import com.temp.common.responses.Response;
import com.temp.server.ServerThread;
import com.temp.server.UserSessionInfo;

import java.util.LinkedList;

public interface RequestHandler<T extends Request> {
    Response handle(T request, UserSessionInfo userSessionInfo, LinkedList<ServerThread> serverThreads);
}
