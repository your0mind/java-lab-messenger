package com.temp.client.forms.customcellrenderers;

import javax.swing.*;
import java.awt.*;

public class MessageCellRenderer implements ListCellRenderer {

    private JPanel p;
    private JLabel l;
    private JTextArea ta;

    public MessageCellRenderer() {
        p = new JPanel();
        p.setLayout(new BorderLayout());

        ta = new JTextArea();

        ta.setLineWrap(true);
        ta.setWrapStyleWord(true);
        p.add(ta, BorderLayout.CENTER);
    }

    @Override
    public Component getListCellRendererComponent(final JList list,
                                                  final Object value, final int index, final boolean isSelected,
                                                  final boolean hasFocus) {

        ta.setText((String) value);
        int width = list.getWidth();
        // this is just to lure the ta's internal sizing mechanism into action
        if (width > 0)
            ta.setSize(width, Short.MAX_VALUE);
        return p;
    }
}
