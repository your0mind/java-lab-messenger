package com.temp.model.dao;

import com.temp.model.models.*;

import java.util.List;

public interface ConferenceParticipantDao {
    List<ConferenceParticipant> findAllByUser(User user);
}
