package com.temp.model.services.impl;

import com.temp.model.dao.ConferenceParticipantDao;
import com.temp.model.dao.impl.ConferenceParticipantDaoImpl;
import com.temp.model.models.Conference;
import com.temp.model.models.ConferenceParticipant;
import com.temp.model.models.User;
import com.temp.model.services.ConferenceParticipantService;

import java.util.List;

public class ConferenceParticipantServiceImpl implements ConferenceParticipantService {
    private ConferenceParticipantDao dao = new ConferenceParticipantDaoImpl();

    @Override
    public List<ConferenceParticipant> findAllParticipants(Conference conference) {
        return dao.findAll(conference);
    }

    @Override
    public int saveParticipant(ConferenceParticipant participant) {
        return dao.save(participant);
    }
}
