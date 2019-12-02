package com.temp.client.forms;

import com.temp.client.Client;
import com.temp.common.requests.CreateDialogRequest;
import com.temp.common.requests.GetContactsRequest;
import com.temp.common.requests.params.CreateDialogRequestParams;
import com.temp.common.requests.params.GetContactsRequestParams;
import com.temp.model.models.Contact;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class CreateDialogForm extends JDialog {
    private JButton createDialogButton;
    private JList<Contact> contactsList;
    private JPanel mainFormPanel;

    public CreateDialogForm(Client client, JFrame owner) {
        super(owner, true);

        contactsList.setModel(client.getDefaultListModels().getContactsListModel());

        try {
            GetContactsRequestParams params = new GetContactsRequestParams();
            client.getClientThread().sendRequestToServer(new GetContactsRequest(params));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(CreateDialogForm.this,
                    e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        contactsList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                createDialogButton.setEnabled(true);
            }
        });

        createDialogButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    CreateDialogRequestParams params = new CreateDialogRequestParams(contactsList.getSelectedValue());
                    client.getClientThread().sendRequestToServer(new CreateDialogRequest(params));
                    CreateDialogForm.this.dispose();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(CreateDialogForm.this,
                            ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        initUI(owner);
    }

    public void initUI(JFrame owner) {
        setTitle("Select contact");
        setContentPane(mainFormPanel);
        setResizable(false);
        pack();
        setLocationRelativeTo(owner);
    }
}
