package com.temp.server.requests;

import com.temp.common.requests.RequestInfo;
import com.temp.server.exceptions.*;

import java.util.HashMap;
import java.util.Map;

public class RequestBuilder {
    private static Map<String, Class> requestsMap = new HashMap<String, Class>() {{
        put("login", LoginRequest.class);
        put("getMessages", GetMessagesRequest.class);
    }};

    public static Request build(RequestInfo reqInfo) throws UnknownRequestException, InvalidRequestParamsException,
            IllegalAccessException, InstantiationException {
        Request request = (Request) requestsMap.get(reqInfo.type).newInstance();

        if (request == null) {
            throw new UnknownRequestException("Unknown request type: " + reqInfo.type);
        }

        if (request.getParamsClass() != reqInfo.params.getClass()) {
            throw new InvalidRequestParamsException("Discrepancy between request and parameters");
        }

        return request;
    }
}
