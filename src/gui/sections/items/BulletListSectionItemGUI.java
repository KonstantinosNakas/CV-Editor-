package gui.sections.items;

import sections.items.BulletListSectionItem;

import javax.swing.*;
import java.awt.event.*;
import com.jgoodies.forms.layout.*;

import gui.sections.IMergable;

public class BulletListSectionItemGUI extends JPanel implements IMergable {
    private static final long serialVersionUID = 1L;
    private BulletListSectionItem model;
    private JTextField text;

    public BulletListSectionItemGUI(final BulletListSectionItem model) {
        this.model = model;
        setLayout(new FormLayout(new ColumnSpec[] {
            FormSpecs.DEFAULT_COLSPEC,
                FormSpecs.RELATED_GAP_COLSPEC,
                ColumnSpec.decode("default:grow"),},
                new RowSpec[] {
                    RowSpec.decode("default:grow"),}));

        JLabel lblText = new JLabel("•");
        add(lblText, "1, 1, right, default");

        text = new JTextField();
        text.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent e) {
                model.setText(text.getText());
            }
        });
        add(text, "3, 1, fill, default");
        text.setColumns(10);
        text.setText(model.getText());
    }

    public void setInputEnabled(boolean enabled) {
        text.setEnabled(enabled);
    }

    public void compareToSymmetrical(IMergable other) {
        BulletListSectionItemGUI symmetrical = (BulletListSectionItemGUI)other;
        if (model.getText().equals(symmetrical.model.getText())) {
            text.setBackground(SAME_COLOR);
        } else {
            text.setBackground(DIFFERENT_COLOR);
        }
    }

}
