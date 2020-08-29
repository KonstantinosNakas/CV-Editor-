package gui.editor;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.event.*;

public class CVEditorGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public CVEditorGUI(final CVEditorController controller) {
        setTitle("CV Editor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 800);

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
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(contentPane);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
    }

    public void addSection(JPanel section) {
        contentPane.add(section);
    }

}
