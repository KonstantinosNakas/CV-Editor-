package io.writer;

import java.io.*;

import cv.CV;
import sections.*;
import sections.items.*;

public class TextWriter implements IWriter {

    private File file;
    private PrintWriter writer;

    public TextWriter(File file) {
        this.file = file;
    }

    public void saveCVToFile(CV cv) throws FileNotFoundException {
        writer = new PrintWriter(file);
        writer.println(cv.getClass().getName());
        for (Section section : cv) {
            writer.println("\n" + section.getSectionTitle());
            section.accept(this);
        }
        writer.println();
        writer.close();
    }

    public void visit(GeneralInformationSection section) {
        writer.println(section.getName());
        writer.println(section.getAddress());
        writer.println(section.getEmail());
        writer.println(section.getTelephone());
        writer.println(section.getMobile());
    }

    public void visit(ListSection section) {
        writer.println(section.getSize());
        for (ISectionItem sectionItem : section) {
            sectionItem.accept(this);
        }
    }

    public void visit(ParagraphSection section) {
        if (section.getParagraph().length() > 0) {
            writer.println(section.getParagraph());
        }
    }

    public void visit(BulletListSectionItem sectionItem) {
        writer.println(sectionItem.getText());
    }

    public void visit(CareerSummarySectionItem sectionItem) {
        writer.println(sectionItem.getCompanyName());
        writer.println(sectionItem.getJobTitle());
        writer.println(sectionItem.getDate());
    }

    public void visit(EducationSectionItem sectionItem) {
        writer.println(sectionItem.getQualification());
        writer.println(sectionItem.getEstablishment());
        writer.println(sectionItem.getLocation());
        writer.println(sectionItem.getDate());
    }

    public void visit(ProfessionalExperienceSectionItem sectionItem) {
        writer.println(sectionItem.getCompanyName());
        writer.println(sectionItem.getJobTitle());
        writer.println(sectionItem.getStartDate());
        writer.println(sectionItem.getEndDate());
        if (sectionItem.getResponsibilities().length() > 0) {
            writer.println(sectionItem.getResponsibilities());
        }
        writer.println();
        writer.println(sectionItem.getAchievementsList().getSectionTitle());
        sectionItem.getAchievementsList().accept(this);
        writer.println();
    }
}
