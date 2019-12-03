package com.temp.client.forms;

import com.temp.client.Client;
import com.temp.client.ClientDefaultListModels;
import com.temp.client.forms.customcellrenderers.MessageCellRenderer;
import com.temp.common.models.*;
import com.temp.common.requests.*;
import com.temp.common.requests.params.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.*;
import java.io.IOException;

public class MainForm extends JFrame {
    private static MainForm instance = null;
    private JTabbedPane tabbedPane1;
    private JPanel mainFormPanel;
    private JButton createDialogButton;
    private JList<Contact> dialogContactsList;
    private JTextField messageToDialogField;
    private JButton sendToDialogButton;
    private JButton createConferenceButton;
    private JTextArea confMessagesArea;
    private JButton sendToConfButton;
    private JList<String> conferencesList;
    private JTextField messageToConfField;
    private JButton showParticipantsButton;
    private JButton showTasksButton;
    private JList<ChatMessage> dialogMessagesList;

    private MainForm(final Client client) {
        MessageCellRenderer renderer = new MessageCellRenderer(new Contact(client.getUsername()));
        dialogMessagesList.setCellRenderer(renderer);

        ClientDefaultListModels cdlm = client.getDefaultListModels();

        dialogContactsList.setModel(cdlm.getDialogContactsListModel());
        dialogMessagesList.setModel(cdlm.getDialogMessagesListModel());
        conferencesList.setModel(cdlm.getConferencesListModel());

        GetDialogContactsRequestParams params1 = new GetDialogContactsRequestParams(true);
        GetConferencesRequestParams params2 = new GetConferencesRequestParams(true);

        try {
            client.getClientThread().sendRequestToServer(new GetDialogContactsRequest(params1));
            client.getClientThread().sendRequestToServer(new GetConferencesRequest(params2));

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }

        createDialogButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateDialogForm createDialogForm = new CreateDialogForm(client, MainForm.this);
                createDialogForm.setVisible(true);
            }
        });

        dialogContactsList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    Contact dialogContact = dialogContactsList.getSelectedValue();
                    GetDialogMessagesRequest request = new GetDialogMessagesRequest(
                            new GetDialogMessagesRequestParams(dialogContact, true));

                    try {
                        client.getClientThread().sendRequestToServer(request);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(MainForm.this, ex.getMessage(),
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        dialogMessagesList.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                dialogMessagesList.setFixedCellHeight(10);
                dialogMessagesList.setFixedCellHeight(-1);
            }
        });

        sendToDialogButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = messageToDialogField.getText();
                Contact contact = dialogContactsList.getSelectedValue();

                SendDialogMessageRequestParams params = new SendDialogMessageRequestParams(message, contact);

                try {
                    client.getClientThread().sendRequestToServer(new SendDialogMessageRequest(params));
                    messageToDialogField.setText("");

                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(MainForm.this, ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
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

    private void initUI() {
        setTitle("JavaLabMessenger");
        setContentPane(mainFormPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }
}
