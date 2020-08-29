package io.writer;

import java.io.FileNotFoundException;

import cv.CV;
import sections.*;
import sections.items.*;

public interface IWriter {
    public void saveCVToFile(CV cv) throws FileNotFoundException;

    public void visit(GeneralInformationSection section);
    public void visit(ListSection section);
    public void visit(ParagraphSection section);
    public void visit(BulletListSectionItem sectionItem);
    public void visit(CareerSummarySectionItem sectionItem);
    public void visit(EducationSectionItem sectionItem);
    public void visit(ProfessionalExperienceSectionItem sectionItem);
}
