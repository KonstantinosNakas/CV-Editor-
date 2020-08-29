package gui.sections.items;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.TitledBorder;
import java.awt.event.*;
import java.util.Calendar;
import com.jgoodies.forms.layout.*;

import gui.sections.IMergable;
import sections.items.EducationSectionItem;

public class EducationSectionItemGUI extends JPanel implements IMergable {

    private static final long serialVersionUID = 1L;
    private EducationSectionItem model;
    private JTextField textQualification;
    private JTextField textEstablishment;
    private JTextField textLocation;
    private JSpinner dateSpinner;

    public EducationSectionItemGUI(final EducationSectionItem model) {
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
                    FormSpecs.DEFAULT_ROWSPEC,
                    FormSpecs.RELATED_GAP_ROWSPEC,
                    FormSpecs.DEFAULT_ROWSPEC,}));

        JLabel lblQualification = new JLabel(model.getEducationType());
        add(lblQualification, "1, 1, right, default");

        textQualification = new JTextField();
        textQualification.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent e) {
                model.setQualification(textQualification.getText());
            }
        });
        add(textQualification, "3, 1, fill, default");
        textQualification.setColumns(10);

        JLabel lblEstablishment = new JLabel("Establishment");
        add(lblEstablishment, "1, 3, right, default");

        textEstablishment = new JTextField();
        textEstablishment.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent e) {
                model.setEstablishment(textEstablishment.getText());
            }
        });
        add(textEstablishment, "3, 3, fill, default");
        textEstablishment.setColumns(10);

        JLabel lblLocation = new JLabel("Location");
        add(lblLocation, "1, 5, right, default");

        textLocation = new JTextField();
        textLocation.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent e) {
                model.setLocation(textLocation.getText());
            }
        });
        add(textLocation, "3, 5, fill, default");
        textLocation.setColumns(10);

        JLabel lblDate = new JLabel("Date");
        add(lblDate, "1, 7, right, default");

        dateSpinner = new JSpinner();
        dateSpinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent arg0) {
                model.setDate((int)dateSpinner.getValue());
            }
        });
        add(dateSpinner, "3, 7");
        initialize();
    }

    private void initialize() {
        textQualification.setText(model.getQualification());
        textEstablishment.setText(model.getEstablishment());
        textLocation.setText(model.getLocation());
        dateSpinner.setModel(new SpinnerNumberModel(model.getDate(), 1950,
                    Calendar.getInstance().get(Calendar.YEAR), 1));
    }

    public void setInputEnabled(boolean enabled) {
        textQualification.setEnabled(enabled);
        textEstablishment.setEnabled(enabled);
        textLocation.setEnabled(enabled);
        dateSpinner.setEnabled(enabled);
    }

    @Override
    public void compareToSymmetrical(IMergable other) {
        EducationSectionItemGUI symmetrical = (EducationSectionItemGUI)other;
        if (model.getQualification().equals(symmetrical.model.getQualification()))
            textQualification.setBackground(SAME_COLOR);
        else
            textQualification.setBackground(DIFFERENT_COLOR);
        if (model.getEstablishment().equals(symmetrical.model.getEstablishment()))
            textEstablishment.setBackground(SAME_COLOR);
        else
            textEstablishment.setBackground(DIFFERENT_COLOR);
        if (model.getLocation().equals(symmetrical.model.getLocation()))
            textLocation.setBackground(SAME_COLOR);
        else
            textLocation.setBackground(DIFFERENT_COLOR);
        if (model.getDate() == symmetrical.model.getDate())
            dateSpinner.setBackground(SAME_COLOR);
        else
            dateSpinner.setBackground(DIFFERENT_COLOR);
    }

}
