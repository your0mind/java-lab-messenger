package com.temp.model.services.impl;

import com.temp.model.dao.DialogDao;
import com.temp.model.dao.impl.DialogDaoImpl;
import com.temp.model.models.Dialog;
import com.temp.model.models.User;
import com.temp.model.services.DialogService;

import java.util.List;

public class DialogServiceImpl implements DialogService {
    private DialogDao dialogsDao = new DialogDaoImpl();

    @Override
    public Dialog findDialog(User user1, User user2) {
        return dialogsDao.find(user1, user2);
    }

    @Override
    public List<Dialog> findAllDialogsByUser(User user) {
        return dialogsDao.findAllByUser(user);
    }

    @Override
    public int saveDialog(Dialog dialog) {
        return dialogsDao.save(dialog);
    }
}
