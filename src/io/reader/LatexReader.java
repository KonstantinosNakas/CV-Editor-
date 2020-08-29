package io.reader;

import java.util.Scanner;
import java.io.*;

import cv.CV;
import cv.CVFactory;
import sections.*;
import sections.items.*;

public class LatexReader implements IReader {

    private File file;
    private Scanner reader;

    public LatexReader(File file) {
        this.file = file;
    }

    public CV readCVFromFile() throws FileNotFoundException {
        reader = new Scanner(file);
        CVFactory factory = new CVFactory();
        CV cv = factory.createCV(reader.nextLine().substring(2));
        if (cv == null) throw new IllegalArgumentException("Invalid CV type.");
        skipEmpty();
        for (Section section : cv) {
            section.accept(this);
        }
        cv.setFilename(file.getAbsolutePath());
        return cv;
    }

    public void visit(GeneralInformationSection section) {
        section.setName(readTextInBrackets());
        section.setAddress(readTextInBrackets());
        section.setEmail(readTextInBrackets());
        section.setTelephone(readTextInBrackets());
        section.setMobile(readTextInBrackets());
        skipEmpty();
    }

    public void visit(ListSection section) {
        String type = readTextInBrackets();
        int sectionsCount = Integer.parseInt(reader.nextLine().substring(2));
        skipEmpty();
        for (int i = 0; i < sectionsCount; ++i) {
            ISectionItem sectionItem =
                SectionItemFactory.createSectionItem(type);
            skipEmpty();
            sectionItem.accept(this);
            section.addSectionItem(sectionItem);
        }
        skipEmpty();
        skipEmpty();
    }

    public void visit(ParagraphSection section) {
        skipEmpty();
        section.setParagraph(getParagraphText());
    }

    public void visit(BulletListSectionItem sectionItem) {
        sectionItem.setText(reader.nextLine());
    }

    public void visit(CareerSummarySectionItem sectionItem) {
        sectionItem.setCompanyName(readTextInBrackets());
        sectionItem.setJobTitle(reader.nextLine());
        sectionItem.setDate(readNumberInBrackets());
    }

    public void visit(EducationSectionItem sectionItem) {
        sectionItem.setQualification(readTextInBrackets());
        sectionItem.setEstablishment(reader.nextLine());
        sectionItem.setLocation(readTextInBrackets());
        sectionItem.setDate(readNumberInBrackets());
    }

    public void visit(ProfessionalExperienceSectionItem sectionItem) {
        sectionItem.setCompanyName(readTextInBrackets());
        sectionItem.setJobTitle(reader.nextLine());
        sectionItem.setStartDate(readNumberInBrackets());
        sectionItem.setEndDate(readNumberInBrackets());
        skipEmpty();
        sectionItem.setResponsibilities(getParagraphText());
        sectionItem.getAchievementsList().accept(this);
    }

    private void skipEmpty() {
        reader.nextLine();
    }

    private String getParagraphText() {
        String newString, result = "";
        while (true) {
            newString = reader.nextLine();
            if (newString.equals("")) break;
            result += newString + "\n";
        }

        if (result.length() > 0) {
            return result.substring(0, result.length() - 1);
        } else {
            return result;
        }
    }

    private String readTextInBrackets() {
        String text = reader.nextLine();
        return text.substring(text.lastIndexOf('{')+1, text.indexOf('}'));
    }

    private int readNumberInBrackets() {
        String text = reader.nextLine();
        String date = text.substring(text.lastIndexOf('{')+1, text.indexOf('}'));
        return Integer.parseInt(date);
    }

}
