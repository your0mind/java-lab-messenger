package com.temp.server.requesthandlers;

import com.temp.common.requests.GetConferencesRequest;
import com.temp.common.responses.ErrorMessage;
import com.temp.common.responses.GetConferencesResponse;
import com.temp.common.responses.Response;
import com.temp.model.models.Conference;
import com.temp.model.models.User;
import com.temp.model.services.ConferenceService;
import com.temp.model.services.impl.ConferenceServiceImpl;
import com.temp.server.ServerThread;
import com.temp.server.UserSessionInfo;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class GetConferencesRequestHandler implements RequestHandler<GetConferencesRequest> {
    @Override
    public Response handle(GetConferencesRequest request, ServerThread callerThread,
                           LinkedList<ServerThread> threads) {
        UserSessionInfo userSessionInfo = callerThread.getUserSessionInfo();
        User requester = userSessionInfo.getUser();

        if (requester == null) {
            return new GetConferencesResponse(new ErrorMessage(("Log in first")));
        }

        ConferenceService service = new ConferenceServiceImpl();
        List<String> conferenceNames = service.findAllConferencesByUser(requester)
                .stream()
                .map(Conference::getName)
                .collect(Collectors.toList());

        ConferenceService conferenceService = new ConferenceServiceImpl();
        boolean listenUpdates = request.getParams().isListenUpdates();
        userSessionInfo.setListenConferencesUpdates(listenUpdates);

        return new GetConferencesResponse(conferenceNames);
    }
}
