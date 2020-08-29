package gui.sections;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

import sections.ParagraphSection;

public class ParagraphSectionGUI extends JPanel implements IMergable {

    private static final long serialVersionUID = 1L;

    private ParagraphSection model;
    private TitledBorder border;
    private JTextArea text;
    private JLabel lblWarning;

    public ParagraphSectionGUI(final ParagraphSection model) {
        this.model = model;
        border = new TitledBorder(null, model.getSectionTitle(),
                TitledBorder.LEADING, TitledBorder.TOP, null, null);
        setBorder(border);
        setLayout(new BorderLayout(0, 0));

        lblWarning = new JLabel(
                "Paragraph text is empty. Please add some information.");
        lblWarning.setIcon(new ImageIcon("resources/label_warning.png"));
        lblWarning.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblWarning, BorderLayout.SOUTH);

        text = new JTextArea();
        text.setRows(4);
        text.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent e) {
                model.setParagraph(text.getText());
                text.setText(model.getParagraph());
                updateWarning(lblWarning, model);
            }
        });
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(text);
        add(scrollPane, BorderLayout.CENTER);
        text.setText(model.getParagraph());
        updateWarning(lblWarning, model);
    }

    public void setSectionTitle(String title) {
        border.setTitle(title);
    }

    public void setInputEnabled(boolean enabled) {
        text.setEnabled(enabled);
        lblWarning.setVisible(enabled);
        if (enabled) updateWarning(lblWarning, model);
    }

    public void compareToSymmetrical(IMergable other) {
        ParagraphSectionGUI symmetrical = (ParagraphSectionGUI)other;
        if (model.getParagraph().equals(symmetrical.model.getParagraph())) {
            text.setBackground(SAME_COLOR);
        } else {
            text.setBackground(DIFFERENT_COLOR);
        }
    }

    private void updateWarning(JLabel lblWarning, ParagraphSection model) {
        if (model.hasError()) {
            lblWarning.setVisible(true);
            lblWarning.setText(model.getErrorDescription());
        } else {
            lblWarning.setVisible(false);
        }
    }

}
