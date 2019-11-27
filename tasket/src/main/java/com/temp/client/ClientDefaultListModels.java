package com.temp.client;

import javax.swing.*;

public class ClientDefaultListModels {
    private DefaultListModel<String> dialogContactsListModel;

    public DefaultListModel<String> getDialogContactsListModel() {
        return dialogContactsListModel;
    }

    public DefaultListModel<String> getContactsListModel() {
        return contactsListModel;
    }

    private DefaultListModel<String> contactsListModel;

    public ClientDefaultListModels() {
        dialogContactsListModel = new DefaultListModel<>();
        contactsListModel = new DefaultListModel<>();
    }
}
