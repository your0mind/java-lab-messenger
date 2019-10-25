package com.temp.client;

import com.temp.client.forms.SignInForm;
import com.temp.server.Server;

import javax.swing.*;

public class Client{
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        new SignInForm().setVisible(true);
    }
}
