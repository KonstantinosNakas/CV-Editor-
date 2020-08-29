package io.reader;

import java.io.FileNotFoundException;

import sections.GeneralInformationSection;
import sections.ListSection;
import sections.ParagraphSection;
import sections.items.BulletListSectionItem;
import sections.items.CareerSummarySectionItem;
import sections.items.EducationSectionItem;
import sections.items.ProfessionalExperienceSectionItem;
import cv.CV;

public interface IReader {
    public CV readCVFromFile() throws FileNotFoundException;

    public void visit(GeneralInformationSection section);
    public void visit(ListSection section);
    public void visit(ParagraphSection section);
    public void visit(BulletListSectionItem sectionItem);
    public void visit(CareerSummarySectionItem sectionItem);
    public void visit(EducationSectionItem sectionItem);
    public void visit(ProfessionalExperienceSectionItem sectionItem);
}
