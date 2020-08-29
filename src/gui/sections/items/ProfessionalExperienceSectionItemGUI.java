package gui.sections.items;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;
import com.jgoodies.forms.layout.*;

import sections.items.ProfessionalExperienceSectionItem;
import gui.sections.IMergable;
import gui.sections.ListSectionGUI;

public class ProfessionalExperienceSectionItemGUI extends JPanel implements IMergable {

    private static final long serialVersionUID = 1L;
    private ProfessionalExperienceSectionItem model;
    private JTextField textCompanyName;
    private JTextField textJobTitle;
    private JSpinner spnStartDate;
    private JSpinner spnEndDate;
    private JTextArea textResponsibilities;
    private ListSectionGUI listAchievements;

    public ProfessionalExperienceSectionItemGUI(final ProfessionalExperienceSectionItem model) {
        this.model = model;
        setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel panel = new JPanel();
        add(panel);
        panel.setLayout(new FormLayout(new ColumnSpec[] {
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

        JLabel lblCompanyName = new JLabel("Company name");
        panel.add(lblCompanyName, "1, 1");

        textCompanyName = new JTextField();
        textCompanyName.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent e) {
                model.setCompanyName(textCompanyName.getText());
            }
        });
        panel.add(textCompanyName, "3, 1");
        textCompanyName.setColumns(10);

        JLabel lblJobTitle = new JLabel("Job title");
        panel.add(lblJobTitle, "1, 3, right, default");

        textJobTitle = new JTextField();
        textJobTitle.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent e) {
                model.setJobTitle(textJobTitle.getText());
            }
        });
        panel.add(textJobTitle, "3, 3");
        textJobTitle.setColumns(10);

        JLabel lblStartDate = new JLabel("Start date");
        panel.add(lblStartDate, "1, 5, right, default");

        spnStartDate = new JSpinner();
        spnStartDate.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent arg0) {
                model.setStartDate((int)spnStartDate.getValue());
            }
        });
        panel.add(spnStartDate, "3, 5");

        JLabel lblEndDate = new JLabel("End date");
        panel.add(lblEndDate, "1, 7, right, default");

        spnEndDate = new JSpinner();
        spnEndDate.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent arg0) {
                model.setEndDate((int)spnEndDate.getValue());
            }
        });
        panel.add(spnEndDate, "3, 7");

        JLabel lblResponsibilities = new JLabel("Responsibilities");
        lblResponsibilities.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(lblResponsibilities);

        JScrollPane scrollPane = new JScrollPane();
        add(scrollPane);

        textResponsibilities = new JTextArea();
        textResponsibilities.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent e) {
                model.setResponsibilities(textResponsibilities.getText());
                textResponsibilities.setText(model.getResponsibilities());
            }
        });
        textResponsibilities.setRows(4);
        scrollPane.setViewportView(textResponsibilities);

        listAchievements = new ListSectionGUI(model.getAchievementsList());
        add(listAchievements);
        initialize();
    }

    private void initialize() {
        textCompanyName.setText(model.getCompanyName());
        textJobTitle.setText(model.getJobTitle());
        textResponsibilities.setText(model.getResponsibilities());
        spnStartDate.setModel(new SpinnerNumberModel(model.getEndDate(), 1950,
                    Calendar.getInstance().get(Calendar.YEAR), 1));
        spnEndDate.setModel(new SpinnerNumberModel(model.getEndDate(), 1950,
                    Calendar.getInstance().get(Calendar.YEAR), 1));
    }

    public void setInputEnabled(boolean enabled) {
        textCompanyName.setEnabled(enabled);
        textJobTitle.setEnabled(enabled);
        spnStartDate.setEnabled(enabled);
        spnEndDate.setEnabled(enabled);
        textResponsibilities.setEnabled(enabled);
        listAchievements.setInputEnabled(enabled);
    }

    @Override
    public void compareToSymmetrical(IMergable other) {
        ProfessionalExperienceSectionItemGUI symmetrical = (ProfessionalExperienceSectionItemGUI)other;
        if (model.getCompanyName().equals(symmetrical.model.getCompanyName()))
            textCompanyName.setBackground(SAME_COLOR);
        else
            textCompanyName.setBackground(DIFFERENT_COLOR);
        if (model.getJobTitle().equals(symmetrical.model.getJobTitle()))
            textJobTitle.setBackground(SAME_COLOR);
        else
            textJobTitle.setBackground(DIFFERENT_COLOR);
        if (model.getStartDate() == symmetrical.model.getStartDate())
            spnStartDate.setBackground(SAME_COLOR);
        else
            spnStartDate.setBackground(DIFFERENT_COLOR);
        if (model.getEndDate() == symmetrical.model.getEndDate())
            spnEndDate.setBackground(SAME_COLOR);
        else
            spnEndDate.setBackground(DIFFERENT_COLOR);
        if (model.getResponsibilities().equals(symmetrical.model.getResponsibilities()))
            textResponsibilities.setBackground(SAME_COLOR);
        else
            textResponsibilities.setBackground(DIFFERENT_COLOR);
        listAchievements.compareToSymmetrical(symmetrical.listAchievements);
    }

}
