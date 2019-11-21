package com.temp.model.services;

import com.temp.model.models.Dialog;

import java.util.List;

public interface DialogService {
    List<Dialog> findAllDialogsByUserId(int userId);
    int saveDialog(Dialog dialog);
}
