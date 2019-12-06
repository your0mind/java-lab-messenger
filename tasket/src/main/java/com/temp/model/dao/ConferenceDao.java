package com.temp.model.dao;

import com.temp.model.models.Conference;
import com.temp.model.models.User;

import java.util.List;

public interface ConferenceDao {
    Conference find(int id);
    Conference find(String name);
    List<Conference> findAllByUser(User user);
    int save(Conference conference);
}

