package com.temp.server;

import com.temp.model.models.User;

public class UserSessionInfo {
    private User user = null;
    private boolean isListenNewDialogs = false;
    private boolean isListenNewConferences = false;
    private int listenNewMessagesFromDialogId = -1;
    private int listenNewMessagesFromConferenceId = -1;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isListenNewDialogs() {
        return isListenNewDialogs;
    }

    public void setListenNewDialogs(boolean listenNewDialogs) {
        isListenNewDialogs = listenNewDialogs;
    }

    public boolean isListenNewConferences() {
        return isListenNewConferences;
    }

    public void setListenNewConferences(boolean listenNewConferences) {
        isListenNewConferences = listenNewConferences;
    }

    public int getListenNewMessagesFromDialogId() {
        return listenNewMessagesFromDialogId;
    }

    public void setListenNewMessagesFromDialogId(int listenNewMessagesFromDialogId) {
        this.listenNewMessagesFromDialogId = listenNewMessagesFromDialogId;
    }

    public int getListenNewMessagesFromConferenceId() {
        return listenNewMessagesFromConferenceId;
    }

    public void setListenNewMessagesFromConferenceId(int listenNewMessagesFromConferenceId) {
        this.listenNewMessagesFromConferenceId = listenNewMessagesFromConferenceId;
    }
}
