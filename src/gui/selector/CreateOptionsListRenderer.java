package gui.selector;

import java.awt.Component;

import javax.swing.*;

public class CreateOptionsListRenderer extends DefaultListCellRenderer {

    private static final long serialVersionUID = 1L;

    @Override
    public Component getListCellRendererComponent(
            @SuppressWarnings("rawtypes") JList list, Object value, int index,
            boolean isSelected, boolean cellHasFocus) {

        JLabel label = (JLabel) super.getListCellRendererComponent(
                list, value, index, isSelected, cellHasFocus);
        String filename = "resources/" + ((String)value).toLowerCase() + ".png";
        label.setIcon(new ImageIcon(filename)); // does not crash if file doesn't exist
        label.setHorizontalTextPosition(JLabel.RIGHT);

        return label;
    }

}
