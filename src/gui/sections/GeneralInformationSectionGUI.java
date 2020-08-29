package gui.sections;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import com.jgoodies.forms.layout.*;

import sections.GeneralInformationSection;

public class GeneralInformationSectionGUI extends JPanel implements IMergable {
    private static final long serialVersionUID = 1L;

    private TitledBorder border;
    private JLabel lblWarning;
    private JTextField textAddress;
    private JTextField textName;
    private JTextField textEmail;
    private JTextField textTelephone;
    private JTextField textMobile;

    private GeneralInformationSection model;

    public GeneralInformationSectionGUI(final GeneralInformationSection model) {
        this.model = model;
        border = new TitledBorder(new LineBorder(new Color(184, 207, 229)), model.getSectionTitle(),
                TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51));
        setBorder(border);
        setLayout(new BorderLayout(0, 0));

        lblWarning = new JLabel("Some fields are empty. Please add some information.");
        lblWarning.setIcon(new ImageIcon("resources/label_warning.png"));
        lblWarning.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblWarning, BorderLayout.SOUTH);

        JPanel panel = new JPanel();
        add(panel, BorderLayout.CENTER);
        FormLayout fl_panel = new FormLayout(new ColumnSpec[] {
            FormSpecs.DEFAULT_COLSPEC,
                FormSpecs.RELATED_GAP_COLSPEC,
                ColumnSpec.decode("default:grow"),
                FormSpecs.DEFAULT_COLSPEC,},
                new RowSpec[] {
                    FormSpecs.DEFAULT_ROWSPEC,
                    FormSpecs.RELATED_GAP_ROWSPEC,
                    FormSpecs.DEFAULT_ROWSPEC,
                    FormSpecs.RELATED_GAP_ROWSPEC,
                    FormSpecs.DEFAULT_ROWSPEC,
                    FormSpecs.RELATED_GAP_ROWSPEC,
                    FormSpecs.DEFAULT_ROWSPEC,
                    FormSpecs.RELATED_GAP_ROWSPEC,
                    FormSpecs.DEFAULT_ROWSPEC,});
        panel.setLayout(fl_panel);

        JLabel lblName = new JLabel("Name");
        panel.add(lblName, "1, 1, right, fill");

        textName = new JTextField();
        textName.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent e) {
                model.setName(textName.getText());
                updateWarning();
            }
        });
        panel.add(textName, "3, 1, fill, default");
        textName.setColumns(10);

        JLabel lblAddress = new JLabel("Address");
        panel.add(lblAddress, "1, 3, right, default");

        textAddress = new JTextField();
        textAddress.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent e) {
                model.setAddress(textAddress.getText());
                updateWarning();
            }
        });
        panel.add(textAddress, "3, 3, fill, default");
        textAddress.setColumns(10);

        JLabel lblEmail = new JLabel("Email");
        panel.add(lblEmail, "1, 5, right, default");

        textEmail = new JTextField();
        textEmail.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent e) {
                model.setEmail(textEmail.getText());
                updateWarning();
            }
        });
        panel.add(textEmail, "3, 5, fill, default");
        textEmail.setColumns(10);

        JLabel lblTelephone = new JLabel("Telephone");
        panel.add(lblTelephone, "1, 7, right, default");

        textTelephone = new JTextField();
        textTelephone.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent e) {
                model.setTelephone(textTelephone.getText());
                updateWarning();
            }
        });
        panel.add(textTelephone, "3, 7, fill, default");
        textTelephone.setColumns(10);

        JLabel lblMobile = new JLabel("Mobile");
        lblMobile.setHorizontalAlignment(SwingConstants.RIGHT);
        panel.add(lblMobile, "1, 9, right, default");

        textMobile = new JTextField();
        textMobile.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent e) {
                model.setMobile(textMobile.getText());
                updateWarning();
            }
        });
        panel.add(textMobile, "3, 9, fill, default");
        textMobile.setColumns(10);
        initialize();
    }

    private void initialize() {
        textName.setText(model.getName());
        textAddress.setText(model.getAddress());
        textEmail.setText(model.getEmail());
        textTelephone.setText(model.getTelephone());
        textMobile.setText(model.getMobile());
        updateWarning();
    }

    public void setSectionTitle(String title) {
        border.setTitle(title);
    }

    public void setInputEnabled(boolean enabled) {
        textAddress.setEnabled(enabled);
        textName.setEnabled(false);
        textEmail.setEnabled(enabled);
        textTelephone.setEnabled(enabled);
        textMobile.setEnabled(enabled);
        lblWarning.setVisible(enabled);
        if (enabled) updateWarning();
    }

    public void compareToSymmetrical(IMergable other) {
        GeneralInformationSectionGUI symmetrical = (GeneralInformationSectionGUI)other;
        if (model.getName().equals(symmetrical.model.getName()))
            textName.setBackground(SAME_COLOR);
        else
            textName.setBackground(DIFFERENT_COLOR);
        if (model.getAddress().equals(symmetrical.model.getAddress()))
            textAddress.setBackground(SAME_COLOR);
        else
            textAddress.setBackground(DIFFERENT_COLOR);
        if (model.getTelephone().equals(symmetrical.model.getTelephone()))
            textTelephone.setBackground(SAME_COLOR);
        else
            textTelephone.setBackground(DIFFERENT_COLOR);
        if (model.getMobile().equals(symmetrical.model.getMobile()))
            textMobile.setBackground(SAME_COLOR);
        else
            textMobile.setBackground(DIFFERENT_COLOR);
        if (model.getEmail().equals(symmetrical.model.getEmail()))
            textEmail.setBackground(SAME_COLOR);
        else
            textEmail.setBackground(DIFFERENT_COLOR);
    }

    private void updateWarning() {
        if (model.hasError()) {
            lblWarning.setVisible(true);
            lblWarning.setText(model.getErrorDescription());
        } else {
            lblWarning.setVisible(false);
        }
    }

}
