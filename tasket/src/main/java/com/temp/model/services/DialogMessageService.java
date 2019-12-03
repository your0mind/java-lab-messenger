package com.temp.model.services;

import com.temp.model.models.Dialog;
import com.temp.model.models.DialogMessage;

import java.util.List;

public interface DialogMessageService {
    DialogMessage findMessage(int id);
    List<DialogMessage> findAllMessages(Dialog dialog);
    int saveMessage(DialogMessage message);
}
