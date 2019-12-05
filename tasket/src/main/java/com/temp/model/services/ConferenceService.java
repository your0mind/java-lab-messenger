package com.temp.model.services;

import com.temp.model.models.Conference;

public interface ConferenceService {
    Conference findConference(int id);
    int saveConference(Conference conference);
}
