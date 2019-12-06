package com.temp.model.services;

import com.temp.model.models.Conference;
import com.temp.model.models.ConferenceParticipant;
import com.temp.model.models.User;

import java.util.List;

public interface ConferenceParticipantService {
    List<ConferenceParticipant> findAllParticipants(Conference conference);
    int saveParticipant(ConferenceParticipant participant);
}
