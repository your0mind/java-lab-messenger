package com.temp.model.dao;

import com.temp.model.models.Dialog;

import java.util.List;

public interface DialogDao {
    List<Dialog> findAllByUserId(int userId);
    int save(Dialog dialog);
}
