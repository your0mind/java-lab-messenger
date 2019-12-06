package com.temp.model.services;

import com.temp.model.models.Conference;
import com.temp.model.models.User;

import java.util.List;

public interface ConferenceService {
    Conference findConference(int id);
    Conference findConference(String name);
    List<Conference> findAllConferencesByUser(User user);
    int saveConference(Conference conference);
}
