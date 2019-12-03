package com.temp.model.dao;

import com.temp.model.models.Dialog;
import com.temp.model.models.DialogMessage;

import java.util.List;

public interface DialogMessageDao {
    List<DialogMessage> findAll(Dialog dialog);
    int save(DialogMessage message);
}

