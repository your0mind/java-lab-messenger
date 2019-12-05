package com.temp.client.forms;

import com.temp.client.Client;
import com.temp.client.ClientDefaultListModels;
import com.temp.common.models.Contact;
import com.temp.common.requests.GetContactsRequest;
import com.temp.common.requests.params.GetContactsRequestParams;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ConferenceParticipantsForm extends JDialog {
    private JList<Contact> participantsList;
    private JList<Contact> contactsList;
    private JButton addParticipantsButton;
    private JButton OKButton;
    private JPanel mainFormPanel;
    private JButton removeParticipantsButton;

    public ConferenceParticipantsForm(Client client, JFrame owner, String conferenceName) {
        super(owner, true);

        GetConfParticipantsRequest request1 = new GetConfParticipantsRequest(
                new GetConfParticipantsRequestParams(conferenceName));
        GetContactsRequest request2 = new GetContactsRequest(new GetContactsRequestParams());

        try {
            client.getClientThread().sendRequestToServer(request1);
            client.getClientThread().sendRequestToServer(request2);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(ConferenceParticipantsForm.this,
                    e.getMessage(), null, JOptionPane.ERROR_MESSAGE);
        }

        ClientDefaultListModels models = client.getDefaultListModels();
        participantsList.setModel(models.getConfParticipantsListModel());
        contactsList.setModel(models.getContactsListModel());

        addParticipantsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        removeParticipantsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        initUI(owner);
    }

    public void initUI(JFrame owner) {
        setTitle("Conference participants");
        setContentPane(mainFormPanel);
        setResizable(false);
        pack();
        setLocationRelativeTo(owner);
    }
}
