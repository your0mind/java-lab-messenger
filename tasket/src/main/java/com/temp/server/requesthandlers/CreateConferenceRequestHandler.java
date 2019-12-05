package com.temp.server.requesthandlers;

import com.temp.common.models.Contact;
import com.temp.common.requests.CreateConferenceRequest;
import com.temp.common.requests.params.CreateConferenceRequestParams;
import com.temp.common.responses.CreateConferenceResponse;
import com.temp.common.responses.ErrorMessage;
import com.temp.common.responses.Response;
import com.temp.common.updates.ConferenceUpdate;
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
import com.temp.server.UserSessionInfo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Level;

public class CreateConferenceRequestHandler implements RequestHandler<CreateConferenceRequest> {
    @Override
    public Response handle(CreateConferenceRequest request, ServerThread callerThread,
                           LinkedList<ServerThread> threads) {
        User requester = callerThread.getUserSessionInfo().getUser();

        if (requester == null) {
            return new CreateConferenceResponse(new ErrorMessage("Log in first"));
        }

        CreateConferenceRequestParams params = request.getParams();

        List<Contact> participants = params.getParticipants();
        List<User> users = new ArrayList<>(participants.size());

        UserService userService = new UserServiceImpl();

        for (Contact contact : participants) {
            User user = userService.findUser(contact.getUsername());

            if (user == null) {
                return new CreateConferenceResponse(new ErrorMessage("Unknown contact"));
            } else if (user.getId() == requester.getId()) {
                ErrorMessage error = new ErrorMessage("Dialog with oneself is inaccessible");
                return new CreateConferenceResponse(error);
            } else {
                users.add(user);
            }
        }

        ConferenceService conferenceService = new ConferenceServiceImpl();
        int id = conferenceService.saveConference(new Conference(params.getName()));

        ConferenceParticipantService service = new ConferenceParticipantServiceImpl();
        service.saveParticipant(new ConferenceParticipant(id, requester.getId()));
        users.forEach(u -> service.saveParticipant(new ConferenceParticipant(id, u.getId())));

        for (ServerThread thread : threads) {
            ListIterator<User> iter = users.listIterator();
            while (iter.hasNext()){
                User user = iter.next();
                UserSessionInfo userSessionInfo = thread.getUserSessionInfo();
                if (userSessionInfo.getUser().getId() == user.getId()) {
                    if (userSessionInfo.isListenConferencesUpdates()) {
                        try {
                            thread.sendMessage(new ConferenceUpdate(params.getName()));
                        } catch (IOException e) {
                            String message = "Failed to send conference update to user";
                            logger.log(Level.SEVERE, message, e);
                        }
                        iter.remove();
                        break;
                    }
                }
            }
        }

        return new CreateConferenceResponse(params.getName());
    }
}
