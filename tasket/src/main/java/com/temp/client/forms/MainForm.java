package com.temp.client.forms;

import com.temp.client.Client;
import com.temp.common.requests.GetDialogsRequest;
import com.temp.common.requests.params.GetDialogsRequestParams;
import com.temp.model.models.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

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
        Client client = Client.getInstance();

        dialogList.setModel(new DefaultListModel<Dialog>());

        try {
            GetDialogsRequestParams params = new GetDialogsRequestParams(true);
            client.getClientThread().sendRequestToServer(new GetDialogsRequest(params));

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }

        showTasksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TaskListForm(MainForm.this).setVisible(true);
            }
        });

        InitializeForm();
    }

    public DefaultListModel<Dialog> getDialogListModel() {
        return (DefaultListModel<Dialog>) dialogList.getModel();
    }

    public void InitializeForm() {
        setTitle("JavaLabMessenger");
        setContentPane(mainFormPanel);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
    }
}
