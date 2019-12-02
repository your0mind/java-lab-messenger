package com.temp.client.forms;

import com.temp.client.Client;
import com.temp.client.ClientDefaultListModels;
import com.temp.client.forms.customcellrenderers.MessageCellRenderer;
import com.temp.common.requests.GetDialogContactsRequest;
import com.temp.common.requests.params.GetDialogContactsRequestParams;
import com.temp.model.models.Contact;

import javax.swing.*;
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
    private JList<String> dialogMessagesList;

    private MainForm(final Client client) {
        ClientDefaultListModels dlm = client.getDefaultListModels();

        dialogContactsList.setModel(dlm.getDialogContactsListModel());

        DefaultListModel<String> m = new DefaultListModel<>();
        m.addElement("<html>Text color: <font color='red'>red</font>Text color: <font color='red'>red</font>Text color: <font color='red'>red</font>Text color: <font color='red'>red</font>Text color: <font color='red'>red</font>Text color: <font color='red'>red</font>Text color: <font color='red'>red</font>Text color: <font color='red'>red</font>Text color: <font color='red'>red</font>Text color: <font color='red'>red</font>Text color: <font color='red'>red</font>Text color: <font color='red'>red</font>Text color: <font color='red'>red</font>Text color: <font color='red'>red</font></html>");
//        dialogMessagesList.setCellRenderer(new MessageCellRenderer());
//
//        ComponentListener l = new ComponentAdapter() {
//
//            @Override
//            public void componentResized(ComponentEvent e) {
//                // next line possible if list is of type JXList
//                // list.invalidateCellSizeCache();
//                // for core: force cache invalidation by temporarily setting fixed height
//                dialogMessagesList.setFixedCellHeight(10);
//                dialogMessagesList.setFixedCellHeight(-1);
//            }
//
//        };
//
//        dialogMessagesList.addComponentListener(l);

        dialogMessagesList.setModel(m);

        try {
            GetDialogContactsRequestParams params = new GetDialogContactsRequestParams(true);
            client.getClientThread().sendRequestToServer(new GetDialogContactsRequest(params));

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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

    private void initUI() {
        setTitle("JavaLabMessenger");
        setContentPane(mainFormPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }
}
