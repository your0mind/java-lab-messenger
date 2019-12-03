package com.temp.model.services.impl;

import com.temp.model.dao.DialogMessageDao;
import com.temp.model.dao.impl.DialogMessageDaoImpl;
import com.temp.model.models.Dialog;
import com.temp.model.models.DialogMessage;
import com.temp.model.services.DialogMessageService;

import java.util.List;

public class DialogMessageServiceImpl implements DialogMessageService {
    private DialogMessageDao dialogMessageDao = new DialogMessageDaoImpl();

    @Override
    public List<DialogMessage> findAllMessages(Dialog dialog) {
        return dialogMessageDao.findAll(dialog);
    }

    @Override
    public int saveMessage(DialogMessage message) {
        return dialogMessageDao.save(message);
    }
}
