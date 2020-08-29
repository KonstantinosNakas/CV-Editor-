package gui.merger;

import java.io.File;
import java.io.FileNotFoundException;

import cv.CV;
import cv.CVFactory;
import gui.common.CommonDialogs;
import gui.sections.SectionFactory;
import sections.Section;
import io.writer.IWriter;
import io.writer.WriterFactory;

public class CVMergerController {

    private CV leftCV;
    private CV rightCV;
    private CV mergedCV;
    private CVMergerGUI view;

    public CVMergerController(CV leftCV, CV rightCV) {
        this.leftCV = leftCV;
        this.rightCV = rightCV;
        CVFactory factory = new CVFactory();
        mergedCV = factory.createCV(leftCV.getClass().getName());
        view = new CVMergerGUI(this);
        view.setCVNames(leftCV.getFilename(), rightCV.getFilename());
        view.setLocationRelativeTo(null);
        view.setVisible(true);
        populateView();
    }

    public void sectionSelected(int sectionIndex, Section section) {
        mergedCV.replaceSection(sectionIndex, section);
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
        writer.saveCVToFile(mergedCV);
    }

    private void populateView() {
        for (int i = 0; i < leftCV.getSectionsCount(); ++i) {
            view.addSectionToLeftCV(i,
                    SectionFactory.createSectionGUI(leftCV.getSection(i)),
                    leftCV.getSection(i));
            view.addSectionToRightCV(i,
                    SectionFactory.createSectionGUI(rightCV.getSection(i)),
                    rightCV.getSection(i));
            view.setLastSectionsSymmetrical();
        }
    }

}
