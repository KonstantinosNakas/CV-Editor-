package gui.merger;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import com.jgoodies.forms.layout.*;

import sections.Section;

public class CVMergerGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private CVMergerController controller;
    private SectionHolderGUI lastLeftHolder;
    private SectionHolderGUI lastRightHolder;
    private JPanel contentPane;
    private JLabel lblLeft;
    private JLabel lblRight;

    public CVMergerGUI(final CVMergerController controller) {
        this.controller = controller;
        setTitle("CV Editor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1200, 800);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu mnfile = new JMenu("File");
        mnfile.setMnemonic('F');
        menuBar.add(mnfile);

        JMenuItem mntmSaveAs = new JMenuItem("Save As...");
        mntmSaveAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
        mntmSaveAs.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                controller.saveCV();
            }
        });
        mntmSaveAs.setIcon(new ImageIcon("resources/icon_save_as.png"));
        mnfile.add(mntmSaveAs);

        JSeparator separator = new JSeparator();
        mnfile.add(separator);

        JMenuItem mntmExit = new JMenuItem("Exit");
        mntmExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
        mntmExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        mntmExit.setIcon(new ImageIcon("resources/icon_exit.png"));
        mnfile.add(mntmExit);

        JMenu mnAbout = new JMenu("About");
        mnAbout.setMnemonic('A');
        menuBar.add(mnAbout);

        JMenuItem mntmAbout = new JMenuItem("About...");
        mntmAbout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "CV Editor\nVersion 1.0\n" +
                        "Created by Ilias Kleftakis and Konstantinos Panagiotis Nakas.",
                        "CV Editor", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        mntmAbout.setIcon(new ImageIcon("resources/icon_about.png"));
        mnAbout.add(mntmAbout);

        contentPane = new JPanel();
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(contentPane);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new FormLayout(new ColumnSpec[] {
            ColumnSpec.decode("default:grow"),
                FormSpecs.RELATED_GAP_COLSPEC,
                ColumnSpec.decode("default:grow"),},
                new RowSpec[] {
                    FormSpecs.DEFAULT_ROWSPEC,
                    FormSpecs.RELATED_GAP_ROWSPEC,
                    FormSpecs.DEFAULT_ROWSPEC,
                    FormSpecs.RELATED_GAP_ROWSPEC,
                    FormSpecs.DEFAULT_ROWSPEC,
                    FormSpecs.RELATED_GAP_ROWSPEC,
                    FormSpecs.DEFAULT_ROWSPEC,
                    FormSpecs.RELATED_GAP_ROWSPEC,
                    FormSpecs.DEFAULT_ROWSPEC,
                    FormSpecs.RELATED_GAP_ROWSPEC,
                    FormSpecs.DEFAULT_ROWSPEC,
                    FormSpecs.RELATED_GAP_ROWSPEC,
                    FormSpecs.DEFAULT_ROWSPEC,
                    FormSpecs.RELATED_GAP_ROWSPEC,
                    FormSpecs.DEFAULT_ROWSPEC,}));

        JPanel labelsPanel = new JPanel();

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(labelsPanel);
        labelsPanel.setLayout(new FormLayout(new ColumnSpec[] {
            ColumnSpec.decode("default:grow"),
                FormSpecs.RELATED_GAP_COLSPEC,
                ColumnSpec.decode("default:grow"),},
                new RowSpec[] {
                    FormSpecs.DEFAULT_ROWSPEC,}));

        lblLeft = new JLabel("Left CV");
        lblLeft.setHorizontalAlignment(SwingConstants.CENTER);
        labelsPanel.add(lblLeft, "1, 1");

        lblRight = new JLabel("Right CV");
        lblRight.setHorizontalAlignment(SwingConstants.CENTER);
        labelsPanel.add(lblRight, "3, 1");
        mainPanel.add(scrollPane);
        setContentPane(mainPanel);
    }

    public void setCVNames(String nameLeft, String nameRight) {
        lblLeft.setText(nameLeft);
        lblRight.setText(nameRight);
    }

    public void addSectionToLeftCV(int sectionIndex, JPanel section,
            Section model) {
        SectionHolderGUI holder = new SectionHolderGUI(sectionIndex,
                section, model, controller, true);
        contentPane.add(holder, "1, " + (2 * sectionIndex + 1));
        lastLeftHolder = holder;
    }

    public void addSectionToRightCV(int sectionIndex, JPanel section,
            Section model) {
        SectionHolderGUI holder = new SectionHolderGUI(sectionIndex,
                section, model, controller, false);
        contentPane.add(holder, "3, " + (2 * sectionIndex + 1));
        lastRightHolder = holder;
    }

    public void setLastSectionsSymmetrical() {
        lastLeftHolder.setSymmetrical(lastRightHolder);
        lastRightHolder.setSymmetrical(lastLeftHolder);
    }

}
