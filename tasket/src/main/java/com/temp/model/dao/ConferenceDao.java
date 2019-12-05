package com.temp.model.dao;

import com.temp.model.models.Conference;

public interface ConferenceDao {
    Conference find(int id);
    int save(Conference conference);
}

