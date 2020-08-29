package io.reader;

import java.io.*;
import java.util.Scanner;

import cv.CV;
import cv.CVFactory;
import sections.*;
import sections.items.*;

public class TextReader implements IReader {

    private File file;
    private Scanner reader;

    public TextReader(File file) {
        this.file = file;
    }

    public CV readCVFromFile() throws FileNotFoundException {
        reader = new Scanner(file);
        CVFactory factory = new CVFactory();
        CV cv = factory.createCV(reader.nextLine());
        if (cv == null) throw new IllegalArgumentException("Invalid CV type.");
        skipEmpty();
        for (Section section : cv) {
            section.accept(this);
        }
        cv.setFilename(file.getAbsolutePath());
        return cv;
    }

    public void visit(GeneralInformationSection section) {
        if (!reader.nextLine().equals("1. GENERAL INFORMATION")) {
            throw new IllegalArgumentException("Invalid general information.");
        }
        section.setName(reader.nextLine());
        section.setAddress(reader.nextLine());
        section.setEmail(reader.nextLine());
        section.setTelephone(reader.nextLine());
        section.setMobile(reader.nextLine());
        skipEmpty();
    }

    public void visit(ListSection section) {
        String sectionItemType = reader.nextLine();
        int sectionsCount = reader.nextInt();
        skipEmpty();
        for (int i = 0; i < sectionsCount; ++i) {
            ISectionItem sectionItem =
                SectionItemFactory.createSectionItem(sectionItemType);
            sectionItem.accept(this);
            section.addSectionItem(sectionItem);
        }
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
        sectionItem.setCompanyName(reader.nextLine());
        sectionItem.setJobTitle(reader.nextLine());
        sectionItem.setDate(reader.nextInt());
        skipEmpty();
    }

    public void visit(EducationSectionItem sectionItem) {
        sectionItem.setQualification(reader.nextLine());
        sectionItem.setEstablishment(reader.nextLine());
        sectionItem.setLocation(reader.nextLine());
        sectionItem.setDate(reader.nextInt());
        skipEmpty();
    }

    public void visit(ProfessionalExperienceSectionItem sectionItem) {
        sectionItem.setCompanyName(reader.nextLine());
        sectionItem.setJobTitle(reader.nextLine());
        sectionItem.setStartDate(reader.nextInt());
        skipEmpty();
        sectionItem.setEndDate(reader.nextInt());
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

}
