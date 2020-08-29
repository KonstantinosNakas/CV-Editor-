package gui.selector;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class CVSelectorGUI extends JFrame {

    private static final long serialVersionUID = 1L; // to keep the compiler happy
    private JPanel contentPane;
    private CVSelectorController controller;
    private final JScrollPane scrollPane = new JScrollPane();

    @SuppressWarnings({ "rawtypes", "unchecked" }) // window builder has issues otherwise
    public CVSelectorGUI() {
        controller = new CVSelectorController(this);
        setTitle("CV Editor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 389, 380);
        setMinimumSize(new Dimension(238, 187));
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

            final JList list = new JList(controller.getCVTemplateDescriptions());
            list.setCellRenderer(new CreateOptionsListRenderer());
            list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            scrollPane.setViewportView(list);
                        contentPane.add(scrollPane, BorderLayout.CENTER);

        JPanel ButtonsPanel = new JPanel();
        FlowLayout fl_ButtonsPanel = (FlowLayout) ButtonsPanel.getLayout();
        fl_ButtonsPanel.setAlignment(FlowLayout.RIGHT);
        contentPane.add(ButtonsPanel, BorderLayout.SOUTH);

            JButton btnEditCv = new JButton("Edit CV");
            btnEditCv.setIcon(new ImageIcon("resources/icon_continue.png"));
            btnEditCv.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    controller.editCVType((String) list.getSelectedValue());
                }
            });
            ButtonsPanel.add(btnEditCv);

                JButton btnExit = new JButton("Exit");
                btnExit.setIcon(new ImageIcon("resources/icon_exit.png"));
                btnExit.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        controller.exit();
                    }
                });
                ButtonsPanel.add(btnExit);

                    JPanel LabelsPanel = new JPanel();
                    contentPane.add(LabelsPanel, BorderLayout.NORTH);
                    LabelsPanel.setLayout(new BoxLayout(LabelsPanel, BoxLayout.Y_AXIS));

                    Component verticalStrut_2 = Box.createVerticalStrut(10);
                    LabelsPanel.add(verticalStrut_2);

        JLabel lblLogo = new JLabel("CV Editor");
        lblLogo.setFont(new Font("Dialog", Font.BOLD, 32));
        lblLogo.setIcon(new ImageIcon("resources/logo.png"));
        LabelsPanel.add(lblLogo);

            Component verticalStrut = Box.createVerticalStrut(20);
            LabelsPanel.add(verticalStrut);

            JLabel lblInstructions = new JLabel("Select an action to continue:");
            LabelsPanel.add(lblInstructions);

            Component verticalStrut_1 = Box.createVerticalStrut(10);
            LabelsPanel.add(verticalStrut_1);
    }

}
