package com.temp.client.forms;

import javax.swing.*;

public class CreateDialogForm extends JDialog {
    private JButton createDialogButton;
    private JList contactsList;
    private JPanel mainFormPanel;

    public CreateDialogForm(JFrame parent) {
        super(parent);
        InitializeForm();
    }

    public void InitializeForm() {
        setTitle("Select contact");
        setContentPane(mainFormPanel);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
    }
}
