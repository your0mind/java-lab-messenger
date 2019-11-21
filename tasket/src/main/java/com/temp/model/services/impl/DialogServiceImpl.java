package com.temp.model.services.impl;

import com.temp.model.dao.DialogDao;
import com.temp.model.dao.impl.DialogDaoImpl;
import com.temp.model.models.Dialog;
import com.temp.model.services.DialogService;

import java.util.List;

public class DialogServiceImpl implements DialogService {
    private DialogDao dialogsDao = new DialogDaoImpl();

    @Override
    public List<Dialog> findAllDialogsByUserId(int userId) {
        return dialogsDao.findAllByUserId(userId);
    }

    @Override
    public int saveDialog(Dialog dialog) {
        return dialogsDao.save(dialog);
    }
}
