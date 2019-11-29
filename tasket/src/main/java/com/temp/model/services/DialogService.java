package com.temp.model.services;

import com.temp.model.models.Dialog;
import com.temp.model.models.User;

import java.util.List;

public interface DialogService {
    List<Dialog> findAllDialogsByUser(User user);
    int saveDialog(Dialog dialog);
}
