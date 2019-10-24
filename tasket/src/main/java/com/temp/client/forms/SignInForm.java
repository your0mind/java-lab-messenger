package com.temp.client.forms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignInForm extends JFrame {
    private JTextField passwordField;
    private JCheckBox registerCheckBox;
    private JButton signInButton;
    private JPanel signInPanel;
    private JTextField loginField;

    public SignInForm() {
        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new MainForm().setVisible(true);
            }
        });

        Initialize();
    }

    public void Initialize() {
        setTitle("Sign In");
        setContentPane(signInPanel);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();
    }
}
