package com.temp.client.forms;

import javax.swing.*;

public class TaskListForm extends JDialog {
    private JList taskList;
    private JComboBox filterComboBox;
    private JButton createTaskButton;
    private JTextArea taskDescrArea;
    private JPanel taskListPanel;

    public TaskListForm(JFrame owner) {
        super(owner, true);

        InitUI();
    }

    public void InitUI() {
        setTitle("Task list");
        setContentPane(taskListPanel);
        pack();
    }
}
