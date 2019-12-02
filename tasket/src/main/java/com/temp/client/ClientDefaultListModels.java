package com.temp.client;

import com.temp.model.models.Contact;

import javax.swing.*;

public class ClientDefaultListModels {
    private DefaultListModel<Contact> dialogContactsListModel;

    public DefaultListModel<Contact> getDialogContactsListModel() {
        return dialogContactsListModel;
    }

    public DefaultListModel<Contact> getContactsListModel() {
        return contactsListModel;
    }

    private DefaultListModel<Contact> contactsListModel;

    public ClientDefaultListModels() {
        dialogContactsListModel = new DefaultListModel<>();
        contactsListModel = new DefaultListModel<>();
    }
}
