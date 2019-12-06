package com.temp.model.dao;

import com.temp.model.models.*;

import java.util.List;

public interface ConferenceParticipantDao {
    List<ConferenceParticipant> findAll(Conference conference);
    int save(ConferenceParticipant participant);
}
