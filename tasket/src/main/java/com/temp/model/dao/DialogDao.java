package com.temp.model.dao;

import com.temp.model.models.Dialog;
import com.temp.model.models.User;

import java.util.List;

public interface DialogDao {
    Dialog find(User user1, User user2);
    List<Dialog> findAllByUser(User user);
    int save(Dialog dialog);
}

