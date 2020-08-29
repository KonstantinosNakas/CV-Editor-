package gui.selector;

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

import gui.common.CommonDialogs;
import gui.editor.CVEditorController;
import gui.merger.CVMergerController;
import gui.selector.CVSelectorGUI;
import io.reader.IReader;
import io.reader.ReaderFactory;
import cv.CV;
import cv.CVFactory;

public class CVSelectorController {

    private CVFactory factory = new CVFactory();
    private CVSelectorGUI view;

    public CVSelectorController(CVSelectorGUI view) {
        this.view = view;
    }

    public void exit() {
        System.exit(0);
    }

    public String[] getCVTemplateDescriptions() {
        ArrayList<String> result = factory.getCVTypesList();
        result.add(0, "Open an existing CV from file");
        result.add("Compare two CVs");

        return (String[]) result.toArray(new String[0]);
    }

    public void editCVType(String cvType) {
        try {
            createNewCV(cvType);
        } catch (NullPointerException e) {
            CommonDialogs.showWarningMessage(view, "CV Editor",
                    "Please select an option to continue.");
            return;
        }
        openCVFromFile(cvType);
        compareCVs(cvType);
    }

    private void openCVFromFile(String cvType) {
        if (cvType.equals("Open an existing CV from file")) {
            try {
                openCVHelper();
            } catch (NullPointerException e) {
                // nothing to do, user just canceled the operation
            } catch (Exception e) {
                CommonDialogs.showWarningMessage(view, "CV Editor",
                        "An error occured while reading.\n" + e.getMessage());
            }
        }
    }

    private void openCVHelper() throws FileNotFoundException {
        File file = CommonDialogs.getOpenDialogFile(view, "CV Editor - Open CV");
        IReader reader = ReaderFactory.createReaderBasedOnFileExtension(file);
        new CVEditorController(reader.readCVFromFile());
        view.setVisible(false);
    }

    private void compareCVs(String cvType) {
        if (cvType.equals("Compare two CVs")) {
            try {
                compareCVsHelper();
            } catch (NullPointerException e) {
                // nothing to do, user just canceled the operation
            } catch (Exception e) {
                CommonDialogs.showWarningMessage(view, "CV Editor",
                        "An error occured while reading.\n" + e.getMessage());
            }
        }
    }

    private void compareCVsHelper() throws FileNotFoundException {
        File leftCVFile = CommonDialogs.getOpenDialogFile(view,
                "CV Editor - Open first CV");
        IReader reader = ReaderFactory.createReaderBasedOnFileExtension(leftCVFile);
        CV leftCV = reader.readCVFromFile();

        File rightCVFile = CommonDialogs.getOpenDialogFile(view,
                "CV Editor - Open second CV");
        reader = ReaderFactory.createReaderBasedOnFileExtension(rightCVFile);
        CV rightCV = reader.readCVFromFile();

        checkErrors(leftCV, rightCV);
        new CVMergerController(leftCV, rightCV);
        view.setVisible(false);
    }

    void checkErrors(CV leftCV, CV rightCV) {
        if (!leftCV.getClass().getName().equals(rightCV.getClass().getName())) {
            throw new IllegalArgumentException("CV types do not match.");
        }
        if (!leftCV.getAuthorName().equals(rightCV.getAuthorName())) {
            throw new IllegalArgumentException("CVs do not belong to the same person.");
        }
    }

    private void createNewCV(String cvType) {
        CV newCV = factory.createCV(cvType);
        if (newCV == null) return;

        new CVEditorController(newCV);
        view.setVisible(false);
    }

}
