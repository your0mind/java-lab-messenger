package com.temp.client.forms;

import com.temp.client.Client;
import com.temp.client.ClientDefaultListModels;
import com.temp.common.requests.GetDialogContactsRequest;
import com.temp.common.requests.params.GetDialogContactsRequestParams;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MainForm extends JFrame {
    private static MainForm instance = null;
    private JTabbedPane tabbedPane1;
    private JPanel mainFormPanel;
    private JButton createDialogButton;
    private JList<String> dialogContactsList;
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

    private MainForm(final Client client) {
        ClientDefaultListModels dlm = client.getDefaultListModels();

        dialogContactsList.setModel(dlm.getDialogContactsListModel());

        try {
            GetDialogContactsRequestParams params = new GetDialogContactsRequestParams(true);
            client.getClientThread().sendRequestToServer(new GetDialogContactsRequest(params));

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }

        createDialogButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateDialogForm createDialogForm = new CreateDialogForm(client, MainForm.this);
                createDialogForm.setVisible(true);
            }
        });

        initUI();
    }

    public static MainForm getInstance(Client client) {
        if (instance == null) {
            instance = new MainForm(client);
        }
        return instance;
    }

    public DefaultListModel<String> getDialogContactsListModel() {
        return (DefaultListModel<String>) dialogContactsList.getModel();
    }

    private void initUI() {
        setTitle("JavaLabMessenger");
        setContentPane(mainFormPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }
}
