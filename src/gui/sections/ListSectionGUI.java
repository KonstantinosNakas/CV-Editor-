package gui.sections;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import com.jgoodies.forms.layout.*;

import gui.sections.ListButtonsGUI.ButtonOptions;
import sections.items.ISectionItem;
import sections.ListSection;
import sections.items.SectionItemFactory;

public class ListSectionGUI extends JPanel implements IMergable{

    private static final long serialVersionUID = 1L;
    private static final int TOTAL_ROWS = 5;

    private TitledBorder border;
    private JPanel panel;
    private JLabel lblWarning;
    private ListSection model;
    private boolean shouldDisplayErrors;

    public ListSectionGUI(final ListSection model) {
        shouldDisplayErrors = true;
        this.model = model;

        border = new TitledBorder(null, model.getSectionTitle(),
                TitledBorder.LEADING, TitledBorder.TOP, null, null);
        setBorder(border);
        setLayout(new BorderLayout(0, 0));

        lblWarning = new JLabel("List is empty. Please add some elements.");
        lblWarning.setIcon(new ImageIcon("resources/label_warning.png"));
        lblWarning.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblWarning, BorderLayout.SOUTH);

        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                updateWarning();
            }
        };
        Timer timer = new Timer(1000 ,taskPerformer);
        timer.setRepeats(true);
        timer.start();

        panel = new JPanel();
        add(panel, BorderLayout.CENTER);
        panel.setLayout(new FormLayout(new ColumnSpec[] {
            ColumnSpec.decode("left:default:grow"),
                FormSpecs.RELATED_GAP_COLSPEC,
                FormSpecs.DEFAULT_COLSPEC,},
                new RowSpec[] {
                    RowSpec.decode("default:grow"),
                    FormSpecs.PARAGRAPH_GAP_ROWSPEC,
                    RowSpec.decode("default:grow"),
                    FormSpecs.PARAGRAPH_GAP_ROWSPEC,
                    RowSpec.decode("default:grow"),
                    FormSpecs.PARAGRAPH_GAP_ROWSPEC,
                    RowSpec.decode("default:grow"),
                    FormSpecs.PARAGRAPH_GAP_ROWSPEC,
                    RowSpec.decode("default:grow"),}));

        for (int i = 0; i < TOTAL_ROWS; ++i) {
            ListButtonsGUI listButtonsGUI = new ListButtonsGUI(this, i);
            panel.add(listButtonsGUI, "3, " + (2*i + 1) + ", center, center");
            listButtonsGUI.setVisible(false);
        }
        ListButtonsGUI firstListButtons = (ListButtonsGUI)panel.getComponent(0);
        firstListButtons.setVisible(true);
        firstListButtons.setOptions(ButtonOptions.ONLY_NEW);
        initialize();
    }

    private void initialize() {
        int i = 0;
        for (ISectionItem newItem : model) {
            JPanel newItemGUI = gui.sections.items.SectionItemFactory
                .createSectionItemGUI(newItem, model.getSectionTitle());
            panel.add(newItemGUI, "1, " + (2*i + 1) + ", fill, fill",
                    TOTAL_ROWS + i);
            ++i;
        }
        refreshButtons();
        revalidate();
    }

    public void setSectionTitle(String title) {
        border.setTitle(title);
    }

    public void upButtonPressed(int buttonRow) {
        model.swapSectionItems(buttonRow, buttonRow - 1);
        Component other = panel.getComponent(TOTAL_ROWS + buttonRow - 1);
        Component selected = panel.getComponent(TOTAL_ROWS + buttonRow);
        panel.remove(selected);
        panel.remove(other);
        panel.add(selected, "1, " + (2*buttonRow - 1) + ", fill, fill",
                TOTAL_ROWS + buttonRow - 1);
        panel.add(other, "1, " + (2*buttonRow + 1) + ", fill, fill",
                TOTAL_ROWS + buttonRow);
        revalidate();
    }

    public void downButtonPressed(int buttonRow) {
        model.swapSectionItems(buttonRow, buttonRow + 1);
        Component other = panel.getComponent(TOTAL_ROWS + buttonRow + 1);
        Component selected = panel.getComponent(TOTAL_ROWS + buttonRow);
        panel.remove(selected);
        panel.remove(other);
        panel.add(other, "1, " + (2*buttonRow + 1) + ", fill, fill",
                TOTAL_ROWS + buttonRow);
        panel.add(selected, "1, " + (2*buttonRow + 3) + ", fill, fill",
                TOTAL_ROWS + buttonRow + 1);
        revalidate();
    }

    public void deleteButtonPressed(int buttonRow) {
        for (int i = buttonRow; i < model.getSize() - 1; ++i) {
            downButtonPressed(i);
        }
        model.deleteLastSectionItem();
        panel.remove(TOTAL_ROWS + model.getSize());
        refreshButtons();
        revalidate();
        repaint();
    }

    public void newButtonPressed(int buttonRow) {
        ISectionItem newItem = SectionItemFactory.createSectionItem(model.getSectionTitle());
        model.addSectionItem(newItem);
        JPanel newItemGUI = gui.sections.items.SectionItemFactory
            .createSectionItemGUI(newItem, model.getSectionTitle());
        panel.add(newItemGUI, "1, " + (2*buttonRow + 1) + ", fill, fill",
                TOTAL_ROWS + buttonRow);
        refreshButtons();
        revalidate();
    }

    public void setInputEnabled(boolean enabled) {
        for (Component component : panel.getComponents()) {
            IMergable actualComponent = (IMergable)component;
            actualComponent.setInputEnabled(enabled);
        }
        shouldDisplayErrors = enabled;
        lblWarning.setVisible(enabled);
    }

    public void compareToSymmetrical(IMergable other) {
        ListSectionGUI symmetrical = (ListSectionGUI)other;
        int minSize = Math.min(model.getSize(), symmetrical.model.getSize());
        for (int i = 0; i < minSize; ++i) {
            IMergable our = (IMergable)panel.getComponent(TOTAL_ROWS + i);
            other = (IMergable)symmetrical.panel.getComponent(TOTAL_ROWS + i);
            our.compareToSymmetrical(other);
        }
    }

    private void updateWarning() {
        if (model.hasError() && shouldDisplayErrors) {
            lblWarning.setVisible(true);
            lblWarning.setText(model.getErrorDescription());
        } else {
            lblWarning.setVisible(false);
        }
    }

    private void refreshButtons() {
        setUpVisibleButtons();
        setUpHiddenButtons();
    }

    private void setUpVisibleButtons() {
        for (int i = 0; i < model.getSize(); ++i) {
            ListButtonsGUI currentButtons = (ListButtonsGUI)panel.getComponent(i);
            currentButtons.setVisible(true);
            if (i == 0 && model.getSize() > 1) {
                currentButtons.setOptions(ButtonOptions.ONLY_DOWN);
            } else if (i == 0) {
                currentButtons.setOptions(ButtonOptions.ONLY_DELETE);
            } else if (i == model.getSize() - 1 && model.getSize() > 1) {
                currentButtons.setOptions(ButtonOptions.ONLY_UP);
            } else if (i == model.getSize() - 1) {
                currentButtons.setOptions(ButtonOptions.ONLY_DELETE);
            } else {
                currentButtons.setOptions(ButtonOptions.UP_AND_DOWN);
            }
        }
    }

    private void setUpHiddenButtons() {
        for (int i = model.getSize(); i < TOTAL_ROWS; ++i) {
            ListButtonsGUI currentButtons = (ListButtonsGUI)panel.getComponent(i);
            if (i == model.getSize()) {
                currentButtons.setVisible(true);
                currentButtons.setOptions(ButtonOptions.ONLY_NEW);
            } else {
                currentButtons.setVisible(false);
            }
        }
    }

}
