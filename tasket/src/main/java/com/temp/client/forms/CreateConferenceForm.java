package com.temp.client.forms;

import com.temp.client.Client;
import com.temp.common.requests.CreateConferenceRequest;
import com.temp.common.requests.CreateDialogRequest;
import com.temp.common.requests.GetContactsRequest;
import com.temp.common.requests.params.CreateConferenceRequestParams;
import com.temp.common.requests.params.CreateDialogRequestParams;
import com.temp.common.requests.params.GetContactsRequestParams;
import com.temp.common.models.Contact;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class CreateConferenceForm extends JDialog {
    private JButton createConferenceButton;
    private JList<Contact> contactsList;
    private JPanel mainFormPanel;
    private JTextField conferenceNameField;

    public CreateConferenceForm(Client client, JFrame owner) {
        super(owner, true);

        client.getDefaultListModels().getContactsListModel().clear();
        contactsList.setModel(client.getDefaultListModels().getContactsListModel());

        try {
            GetContactsRequestParams params = new GetContactsRequestParams();
            client.getClientThread().sendRequestToServer(new GetContactsRequest(params));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(CreateConferenceForm.this,
                    e.getMessage(), null, JOptionPane.ERROR_MESSAGE);
        }

        contactsList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                createConferenceButton.setEnabled(true);
            }
        });

        createConferenceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = conferenceNameField.getText();
                    List<Contact> contacts = contactsList.getSelectedValuesList();
                    client.getClientThread().sendRequestToServer(new CreateConferenceRequest(
                            new CreateConferenceRequestParams(contacts, name)));
                    CreateConferenceForm.this.dispose();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(CreateConferenceForm.this,
                            ex.getMessage(), null, JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        initUI(owner);
    }

    private void initUI(JFrame owner) {
        setContentPane(mainFormPanel);
        setResizable(false);
        pack();
        setLocationRelativeTo(owner);
    }
}

