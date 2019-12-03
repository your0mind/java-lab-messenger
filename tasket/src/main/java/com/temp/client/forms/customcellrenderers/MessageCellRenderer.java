package com.temp.client.forms.customcellrenderers;

import com.temp.common.models.ChatMessage;
import com.temp.common.models.Contact;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;

public class MessageCellRenderer implements ListCellRenderer<ChatMessage> {
    private Contact clientContact;

    public MessageCellRenderer(Contact clientContact) {
        this.clientContact = clientContact;
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends ChatMessage> list,
                                                  ChatMessage value, int index, boolean isSelected,
                                                  boolean cellHasFocus) {
        String clientUsername = clientContact.getUsername();
        String sender = value.getSender().getUsername();

        Color senderColor = (clientUsername.equals(sender)) ? Color.blue : Color.red;

        JTextPane jTextPane = new JTextPane();
        jTextPane.setEditorKit(new WrapEditorKit());

        StyledDocument doc = jTextPane.getStyledDocument();
        Style style = jTextPane.addStyle("I'm a Style", null);

        StyleConstants.setForeground(style, senderColor);
        try { doc.insertString(doc.getLength(), value.getSender().getUsername(), style); }
        catch (BadLocationException e){}

        StyleConstants.setForeground(style, Color.gray);
        try { doc.insertString(doc.getLength(), String.format(" (%s): ", value.getDate()), style); }
        catch (BadLocationException e){}

        StyleConstants.setForeground(style, Color.black);
        try { doc.insertString(doc.getLength(), value.getText(), style); }
        catch (BadLocationException e){}

//        appendToPane(jTextPane, value.getSender().getUsername(), senderColor);
//        appendToPane(jTextPane, String.format(" (%s): ", value.getDate()), Color.gray);
//        appendToPane(jTextPane, value.getText(), Color.black);

//        String styleHtml = String.format("<body style='width: %spx'>", list.getWidth());
//        String senderHtml = String.format("<font color='%s'>%s</font>", senderColor, sender);
//        String dateHtml = String.format("<font color='gray'>(%s)</font>", value.getDate().toString());
//        setMaximumSize(new Dimension(list.getWidth(), -1));
//
//        setText(String.format("<html>%s%s %s: %s</html>",
//                styleHtml, senderHtml, dateHtml, value.getText()));

        System.out.println(jTextPane.getText());

        return jTextPane;
    }

    private void appendToPane(JTextPane jTextPane, String msg, Color c) {
        StyleContext sc = StyleContext.getDefaultStyleContext();

        int len = jTextPane.getDocument().getLength();
        jTextPane.setCaretPosition(len);
        jTextPane.replaceSelection(msg);
    }

    public class WrapEditorKit extends StyledEditorKit {
        ViewFactory defaultFactory=new WrapColumnFactory();
        public ViewFactory getViewFactory() {
            return defaultFactory;
        }
    }

    class WrapColumnFactory implements ViewFactory {
        public View create(Element elem) {
            String kind = elem.getName();
            if (kind != null) {
                if (kind.equals(AbstractDocument.ContentElementName)) {
                    return new WrapLabelView(elem);
                } else if (kind.equals(AbstractDocument.ParagraphElementName)) {
                    return new ParagraphView(elem);
                } else if (kind.equals(AbstractDocument.SectionElementName)) {
                    return new BoxView(elem, View.Y_AXIS);
                } else if (kind.equals(StyleConstants.ComponentElementName)) {
                    return new ComponentView(elem);
                } else if (kind.equals(StyleConstants.IconElementName)) {
                    return new IconView(elem);
                }
            }

            // default to text display
            return new LabelView(elem);
        }
    }

    class WrapLabelView extends LabelView {
        public WrapLabelView(Element elem) {
            super(elem);
        }

        public float getMinimumSpan(int axis) {
            switch (axis) {
                case View.X_AXIS:
                    return 0;
                case View.Y_AXIS:
                    return super.getMinimumSpan(axis);
                default:
                    throw new IllegalArgumentException("Invalid axis: " + axis);
            }
        }

    }
}
