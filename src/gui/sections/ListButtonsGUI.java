package gui.sections;

import javax.swing.*;
import java.awt.event.*;

public class ListButtonsGUI extends JPanel implements IMergable {

    private static final long serialVersionUID = 1L;

    private JButton btnDown;
    private JButton btnUp;
    private JButton btnDelete;
    private JButton btnNew;

    public enum ButtonOptions {
        ONLY_UP, ONLY_DOWN, UP_AND_DOWN, ONLY_DELETE, ONLY_NEW;
    }

    public ListButtonsGUI(final ListSectionGUI list, final int buttonRow) {

        btnDown = new JButton("");
        btnDown.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                list.downButtonPressed(buttonRow);
            }
        });
        btnDown.setToolTipText("Move element down.");
        btnDown.setIcon(new ImageIcon("resources/button_down.png"));
        add(btnDown);

        btnUp = new JButton("");
        btnUp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                list.upButtonPressed(buttonRow);
            }
        });
        btnUp.setIcon(new ImageIcon("resources/button_up.png"));
        btnUp.setToolTipText("Move element up.");
        add(btnUp);

        btnDelete = new JButton("");
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                list.deleteButtonPressed(buttonRow);
            }
        });
        btnDelete.setIcon(new ImageIcon("resources/button_delete.png"));
        btnDelete.setToolTipText("Delete element.");
        add(btnDelete);

        btnNew = new JButton("");
        btnNew.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                list.newButtonPressed(buttonRow);
            }
        });
        btnNew.setIcon(new ImageIcon("resources/button_add.png"));
        btnNew.setToolTipText("Create new element.");
        add(btnNew);
    }

    public void setOptions(ButtonOptions value) {
        if (value == ButtonOptions.ONLY_UP) {
            setOnlyUpButton();
        } else if (value == ButtonOptions.ONLY_DOWN) {
            setOnlyDownButton();
        } else if (value == ButtonOptions.UP_AND_DOWN) {
            setUpAndDownButtons();
        } else if (value == ButtonOptions.ONLY_DELETE) {
            setOnlyDeleteButton();
        } else if (value == ButtonOptions.ONLY_NEW) {
            setOnlyNewButton();
        }
    }

    public void setInputEnabled(boolean enabled) {
        //this.setVisible(false);
    }

    public void compareToSymmetrical(IMergable other) {
        // nothing to do here
    }

    private void setOnlyNewButton() {
        btnDown.setVisible(false);
        btnUp.setVisible(false);
        btnDelete.setVisible(false);
        btnNew.setVisible(true);
    }

    private void setOnlyDeleteButton() {
        btnDown.setVisible(true);
        btnDown.setEnabled(false);
        btnUp.setVisible(true);
        btnUp.setEnabled(false);
        btnDelete.setVisible(true);
        btnNew.setVisible(false);
    }

    private void setUpAndDownButtons() {
        btnDown.setVisible(true);
        btnDown.setEnabled(true);
        btnUp.setVisible(true);
        btnUp.setEnabled(true);
        btnDelete.setVisible(true);
        btnNew.setVisible(false);
    }

    private void setOnlyDownButton() {
        btnDown.setVisible(true);
        btnDown.setEnabled(true);
        btnUp.setVisible(true);
        btnUp.setEnabled(false);
        btnDelete.setVisible(true);
        btnNew.setVisible(false);
    }

    private void setOnlyUpButton() {
        btnDown.setVisible(true);
        btnDown.setEnabled(false);
        btnUp.setVisible(true);
        btnUp.setEnabled(true);
        btnDelete.setVisible(true);
        btnNew.setVisible(false);
    }
}
