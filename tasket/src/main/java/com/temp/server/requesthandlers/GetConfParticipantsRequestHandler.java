package com.temp.server.requesthandlers;

import com.temp.common.models.Contact;
import com.temp.common.requests.GetConfParticipantsRequest;
import com.temp.common.requests.params.GetConfParticipantsRequestParams;
import com.temp.common.responses.CreateConferenceResponse;
import com.temp.common.responses.ErrorMessage;
import com.temp.common.responses.GetConfParticipantsResponse;
import com.temp.common.responses.Response;
import com.temp.model.models.Conference;
import com.temp.model.models.ConferenceParticipant;
import com.temp.model.models.User;
import com.temp.model.services.ConferenceParticipantService;
import com.temp.model.services.ConferenceService;
import com.temp.model.services.UserService;
import com.temp.model.services.impl.ConferenceParticipantServiceImpl;
import com.temp.model.services.impl.ConferenceServiceImpl;
import com.temp.model.services.impl.UserServiceImpl;
import com.temp.server.ServerThread;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class GetConfParticipantsRequestHandler
        implements RequestHandler<GetConfParticipantsRequest> {
    @Override
    public Response handle(GetConfParticipantsRequest request, ServerThread callerThread,
                           LinkedList<ServerThread> threads) {
        User requester = callerThread.getUserSessionInfo().getUser();

        if (requester == null) {
            return new GetConfParticipantsResponse(new ErrorMessage("Log in first"));
        }

        GetConfParticipantsRequestParams params = request.getParams();

        ConferenceService conferenceService = new ConferenceServiceImpl();
        Conference conference = conferenceService.findConference(params.getConferenceName());

        if (conference == null) {
            return new GetConfParticipantsResponse(new ErrorMessage("Unknown conference"));
        }

        UserService userService = new UserServiceImpl();
        ConferenceParticipantService service = new ConferenceParticipantServiceImpl();

        List<Integer> participantIds = service.findAllParticipants(conference)
                .stream()
                .map(ConferenceParticipant::getParticipantId)
                .collect(Collectors.toList());

        List<Contact> participants = participantIds.stream()
                .map(i -> new Contact(userService.findUser(i).getUsername()))
                .collect(Collectors.toList());

        return new GetConfParticipantsResponse(participants);
    }
}
