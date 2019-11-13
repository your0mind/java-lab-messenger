package com.temp.server.requests;

import com.temp.common.requests.RequestInfo;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RequestBuilder {
    private static Map<String, Class> requestsMap = new HashMap<String, Class>() {{
        put("login", LoginRequest.class);
        put("getMessages", GetMessagesRequest.class);
    }};

    private final static Logger logger = Logger.getLogger(RequestBuilder.class.getSimpleName());

    public static Request build(RequestInfo reqInfo) throws InvalidParameterException {
        Request request = null;

        try {
            request = (Request) requestsMap.get(reqInfo.type).newInstance();
        }
        catch (InstantiationException | IllegalAccessException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }

        if (request == null) {
            throw new InvalidParameterException();
        }

        return request;
    }
}
