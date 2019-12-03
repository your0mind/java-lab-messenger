package com.temp.client.forms.customcellrenderers;

import com.temp.common.models.ChatMessage;
import com.temp.common.models.Contact;

import javax.swing.*;
import java.awt.*;

public class MessageCellRenderer extends JLabel implements ListCellRenderer<ChatMessage> {

    private Contact clientContact;

    public MessageCellRenderer(Contact clientContact) {
        this.clientContact = clientContact;
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends ChatMessage> list,
                                                  ChatMessage value, int index, boolean isSelected,
                                                  boolean cellHasFocus) {
        String a = "<html>Text color: <font color='red'>red</font></html>";
        String clientUsername = clientContact.getUsername();
        String sender = value.getSender().getUsername();

        String senderColor = (clientUsername.equals(sender)) ? "blue" : "red";

        String styleHtml = String.format("<body style='width: %spx'; word-wrap: break-word>", list.getWidth());
        String senderHtml = String.format("<font color='%s'>%s</font>", senderColor, sender);
        String dateHtml = String.format("<font color=''>%s</font>", value.getDate().toString());

        setText(String.format("<html>%s%s (%s): %s</html>",
                styleHtml, senderHtml, dateHtml, value.getText()));

        return this;
    }
}
