package gui.editor;

import java.io.File;
import java.io.FileNotFoundException;

import cv.CV;
import gui.common.CommonDialogs;
import gui.sections.SectionFactory;
import sections.Section;
import io.writer.IWriter;
import io.writer.WriterFactory;

public class CVEditorController {

    private CV cv;
    private CVEditorGUI view;

    public CVEditorController(CV cv) {
        this.cv = cv;
        view = new CVEditorGUI(this);
        view.setLocationRelativeTo(null);
        view.setVisible(true);
        populateView();
    }

    public void saveCV() {
        try {
            saveCVHelper();
        } catch (NullPointerException e) {
            // nothing to do, user just canceled the operation
        } catch (Exception e) {
            CommonDialogs.showWarningMessage(view, "CV Editor",
                    "An error occured while reading.\n" + e.getMessage());
        }
    }

    private void saveCVHelper() throws FileNotFoundException {
        File file = CommonDialogs.getSaveDialogFile(view);
        IWriter writer = WriterFactory.createWriterBasedOnFileExtension(file);
        writer.saveCVToFile(cv);
    }

    private void populateView() {
        for (Object object : cv) {
            Section section = (Section)object;
            view.addSection(SectionFactory.createSectionGUI(section));
        }
    }

}
