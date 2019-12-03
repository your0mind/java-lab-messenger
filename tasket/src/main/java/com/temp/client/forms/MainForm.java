package com.temp.client.forms;

import com.temp.client.Client;
import com.temp.client.ClientDefaultListModels;
import com.temp.client.forms.customcellrenderers.MessageCellRenderer;
import com.temp.common.models.ChatMessage;
import com.temp.common.models.Contact;
import com.temp.common.requests.GetDialogContactsRequest;
import com.temp.common.requests.GetDialogMessagesRequest;
import com.temp.common.requests.params.GetDialogContactsRequestParams;
import com.temp.common.requests.params.GetDialogMessagesRequestParams;

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
    private JList list1;
    private JTextField messageToConfField;
    private JButton showParticipantsButton;
    private JButton showTasksButton;
    private JList<ChatMessage> dialogMessagesList;

    private MainForm(final Client client) {
        ClientDefaultListModels cdlm = client.getDefaultListModels();

        cdlm.getDialogContactsListModel().clear();
        cdlm.getDialogMessagesListModel().clear();

        dialogContactsList.setModel(cdlm.getDialogContactsListModel());
        dialogMessagesList.setModel(cdlm.getDialogMessagesListModel());

        MessageCellRenderer renderer = new MessageCellRenderer(new Contact(client.getUsername()));
        dialogMessagesList.setCellRenderer(renderer);


        try {
            GetDialogContactsRequestParams params = new GetDialogContactsRequestParams(true);
            client.getClientThread().sendRequestToServer(new GetDialogContactsRequest(params));

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
                    cdlm.getDialogMessagesListModel().clear();

                    try {
                        Contact dialogContact = dialogContactsList.getSelectedValue();
                        GetDialogMessagesRequest request = new GetDialogMessagesRequest(
                                new GetDialogMessagesRequestParams(dialogContact, true));
                        client.getClientThread().sendRequestToServer(request);

                        System.out.println("1");

                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(MainForm.this, ex.getMessage(),
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

//        dialogMessagesList.addComponentListener(new ComponentAdapter() {
//            @Override
//            public void componentResized(ComponentEvent e) {
//                dialogMessagesList.setFixedCellHeight(10);
//                dialogMessagesList.setFixedCellHeight(-1);
//            }
//        });

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
