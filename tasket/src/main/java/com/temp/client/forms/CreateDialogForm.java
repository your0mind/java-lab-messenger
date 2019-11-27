package com.temp.client.forms;

import com.temp.client.Client;
import com.temp.common.requests.GetContactsRequest;
import com.temp.common.requests.params.GetContactsRequestParams;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class CreateDialogForm extends JDialog {
    private JButton createDialogButton;
    private JList<String> contactsList;
    private JPanel mainFormPanel;

    public CreateDialogForm(Client client, JFrame owner) {
        super(owner, true);

        contactsList.setModel(client.getDefaultListModels().getContactsListModel());

        try {
            GetContactsRequestParams params = new GetContactsRequestParams();
            client.getClientThread().sendRequestToServer(new GetContactsRequest(params));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(CreateDialogForm.this, e.getMessage());
        }

        createDialogButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });

        InitUI(owner);
    }

    public void InitUI(JFrame owner) {
        setTitle("Select contact");
        setContentPane(mainFormPanel);
        setResizable(false);
        pack();
        setLocationRelativeTo(owner);
    }
}
