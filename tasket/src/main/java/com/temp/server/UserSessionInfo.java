package com.temp.server;

import com.temp.model.models.User;

public class UserSessionInfo {
    private User user = null;
    private boolean listenDialogContactUpdates = false;
    private boolean listenConferencesUpdates = false;
    private int listeningDialogId = -1;
    private int listeningConferenceId = -1;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isListenDialogContactUpdates() {
        return listenDialogContactUpdates;
    }

    public void setListenDialogContactUpdates(boolean listenDialogContactUpdates) {
        this.listenDialogContactUpdates = listenDialogContactUpdates;
    }

    public boolean isListenConferencesUpdates() {
        return listenConferencesUpdates;
    }

    public void setListenConferencesUpdates(boolean listenConferencesUpdates) {
        this.listenConferencesUpdates = listenConferencesUpdates;
    }

    public int getListeningDialogId() {
        return listeningDialogId;
    }

    public void setListeningDialogId(int listeningDialogId) {
        this.listeningDialogId = listeningDialogId;
    }

    public int getListeningConferenceId() {
        return listeningConferenceId;
    }

    public void setListeningConferenceId(int listeningConferenceId) {
        this.listeningConferenceId = listeningConferenceId;
    }
}
