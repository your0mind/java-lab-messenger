package com.temp.model.services.impl;

import com.temp.model.dao.ConferenceDao;
import com.temp.model.dao.impl.ConferenceDaoImpl;
import com.temp.model.models.Conference;
import com.temp.model.services.ConferenceService;

public class ConferenceServiceImpl implements ConferenceService {
    ConferenceDao conferenceDao = new ConferenceDaoImpl();

    @Override
    public Conference findConference(int id) {
        return conferenceDao.find(id);
    }

    @Override
    public int saveConference(Conference conference) {
        return conferenceDao.save(conference);
    }
}
