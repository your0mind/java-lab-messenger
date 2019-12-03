package com.temp.client;

import com.temp.common.models.ChatMessage;
import com.temp.common.models.Contact;

import javax.swing.*;

public class ClientDefaultListModels {
    private DefaultListModel<Contact> dialogContactsListModel;
    private DefaultListModel<Contact> contactsListModel;
    private DefaultListModel<ChatMessage> dialogMessagesListModel;
    private DefaultListModel<String> conferencesListModel;

    public DefaultListModel<Contact> getDialogContactsListModel() {
        return dialogContactsListModel;
    }

    public DefaultListModel<Contact> getContactsListModel() {
        return contactsListModel;
    }

    public DefaultListModel<ChatMessage> getDialogMessagesListModel() {
        return dialogMessagesListModel;
    }

    public DefaultListModel<String> getConferencesListModel() {
        return conferencesListModel;
    }

    public ClientDefaultListModels() {
        dialogContactsListModel = new DefaultListModel<>();
        contactsListModel = new DefaultListModel<>();
        dialogMessagesListModel = new DefaultListModel<>();
        conferencesListModel = new DefaultListModel<>();
    }
}
