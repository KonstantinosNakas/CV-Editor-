package gui.common;

import java.io.File;
import java.awt.Component;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class CommonDialogs {

    public static void showWarningMessage(Component parent, String title, String message) {
        JOptionPane.showMessageDialog(parent, message, title, JOptionPane.WARNING_MESSAGE);
    }

    public static File getOpenDialogFile(Component parent, String title) {
        JFileChooser fileChooser = prepareJFileChooser();
        fileChooser.setDialogTitle(title);
        if (fileChooser.showOpenDialog(parent) == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        } else {
            throw new NullPointerException("Action canceled by the user.");
        }
    }

    public static File getSaveDialogFile(Component parent) {
        JFileChooser fileChooser = prepareJFileChooser();
        fileChooser.setDialogTitle("CV Editor - Save CV");
        if (fileChooser.showSaveDialog(parent) == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        } else {
            throw new NullPointerException("Action canceled by the user.");
        }
    }

    private static JFileChooser prepareJFileChooser() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter(
                    "All Supported Files", "txt", "tex"));
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter(
                    "Text Files", "txt"));
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter(
                    "LaTeX Files", "tex"));
        return fileChooser;
    }

}
