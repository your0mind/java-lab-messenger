package com.temp.client.forms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;

public class MainForm extends JFrame{
    private JTabbedPane tabbedPane1;
    private JPanel mainFormPanel;
    private JButton createDialogButton;
    private JList dialogList;
    private JTextArea dialogMessagesArea;
    private JTextField messageToDialogField;
    private JButton sendToDialogButton;
    private JButton createConferenceButton;
    private JTextArea confMessagesArea;
    private JButton sendToConfButton;
    private JList list1;
    private JTextField messageToConfField;
    private JButton showParticipantsButton;
    private JButton showTasksButton;
    private JTabbedPane tabbedPane2;
    private JTextArea textArea1;
    private JTextField textField1;
    private JButton sendButton;

    public MainForm() {
        showTasksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TaskListForm(MainForm.this).setVisible(true);
            }
        });

        Initialize();
        });
    }

    public void Initialize() {
        setTitle("JavaLabMessenger");
        setContentPane(mainFormPanel);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
    }
}
