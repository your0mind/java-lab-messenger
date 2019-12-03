package com.temp.model.services;

import com.temp.model.models.ConferenceParticipant;
import com.temp.model.models.User;

import java.util.List;

public interface ConferenceParticipantService {
    List<ConferenceParticipant> findAllParticipationByUser(User user);
}
