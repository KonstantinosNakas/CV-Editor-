package gui.sections.items;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.TitledBorder;
import java.awt.event.*;
import java.util.Calendar;
import com.jgoodies.forms.layout.*;

import gui.sections.IMergable;
import sections.items.CareerSummarySectionItem;

public class CareerSummarySectionItemGUI extends JPanel implements IMergable {

    private static final long serialVersionUID = 1L;
    private CareerSummarySectionItem model;
    private JTextField textCompanyName;
    private JTextField textJobTitle;
    private JSpinner dateSpinner;

    public CareerSummarySectionItemGUI(final CareerSummarySectionItem model) {
        this.model = model;
        setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        setLayout(new FormLayout(new ColumnSpec[] {
            FormSpecs.DEFAULT_COLSPEC,
                FormSpecs.RELATED_GAP_COLSPEC,
                ColumnSpec.decode("default:grow"),},
                new RowSpec[] {
                    FormSpecs.DEFAULT_ROWSPEC,
                    FormSpecs.RELATED_GAP_ROWSPEC,
                    FormSpecs.DEFAULT_ROWSPEC,
                    FormSpecs.RELATED_GAP_ROWSPEC,
                    FormSpecs.DEFAULT_ROWSPEC,}));

        JLabel lblCompanyName = new JLabel("Company name");
        add(lblCompanyName, "1, 1, right, default");

        textCompanyName = new JTextField();
        textCompanyName.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent e) {
                model.setCompanyName(textCompanyName.getText());
            }
        });
        add(textCompanyName, "3, 1, fill, default");
        textCompanyName.setColumns(10);

        JLabel lblJobTitle = new JLabel("Job title");
        add(lblJobTitle, "1, 3, right, default");

        textJobTitle = new JTextField();
        textJobTitle.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent e) {
                model.setJobTitle(textJobTitle.getText());
            }
        });
        add(textJobTitle, "3, 3, fill, default");
        textJobTitle.setColumns(10);

        JLabel lblDate = new JLabel("Date");
        add(lblDate, "1, 5, right, default");

        dateSpinner = new JSpinner();
        dateSpinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent arg0) {
                model.setDate((int)dateSpinner.getValue());
            }
        });
        add(dateSpinner, "3, 5");
        initialize();
    }

    private void initialize() {
        textCompanyName.setText(model.getCompanyName());
        textJobTitle.setText(model.getJobTitle());
        dateSpinner.setModel(new SpinnerNumberModel(model.getDate(), 1950,
                    Calendar.getInstance().get(Calendar.YEAR), 1));
    }

    public void setInputEnabled(boolean enabled) {
        textCompanyName.setEnabled(enabled);
        textJobTitle.setEnabled(enabled);
        dateSpinner.setEnabled(enabled);
    }

    @Override
    public void compareToSymmetrical(IMergable other) {
        CareerSummarySectionItemGUI symmetrical = (CareerSummarySectionItemGUI)other;
        if (model.getCompanyName().equals(symmetrical.model.getCompanyName()))
            textCompanyName.setBackground(SAME_COLOR);
        else
            textCompanyName.setBackground(DIFFERENT_COLOR);
        if (model.getJobTitle().equals(symmetrical.model.getJobTitle()))
            textJobTitle.setBackground(SAME_COLOR);
        else
            textJobTitle.setBackground(DIFFERENT_COLOR);
        if (model.getDate() == symmetrical.model.getDate())
            dateSpinner.setBackground(SAME_COLOR);
        else
            dateSpinner.setBackground(DIFFERENT_COLOR);
    }

}
