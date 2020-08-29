package io.writer;

import java.io.*;

import cv.CV;
import sections.*;
import sections.items.*;

public class LatexWriter implements IWriter {

    private File file;
    private PrintWriter writer;

    public LatexWriter(File file) {
        this.file = file;
    }

    public void saveCVToFile(CV cv) throws FileNotFoundException {
        writer = new PrintWriter(file);
        writer.println("% " + cv.getClass().getName());
        writer.println("\\documentclass{article}\\begin{document}");
        for (Section section : cv) {
            section.accept(this);
        }
        writer.println();
        writer.println("\\end{document}");
        writer.close();
    }

    public void visit(GeneralInformationSection section) {
        writer.println("\\centerline{\\Huge{\\textbf{" + section.getName() + "}}}");
        writer.println("\\centerline{\\emph{" + section.getAddress() + "}}");
        writer.println("\\centerline{\\emph{" + section.getEmail() + "} | ");
        writer.println("\\emph{" + section.getTelephone() + "} | ");
        writer.println("\\emph{" + section.getMobile() + "}}");
    }

    public void visit(ListSection section) {
        writeListTitle(section.getSectionTitle());
        writer.println("% " + section.getSize());
        if (section.getSize() > 0) {
            writer.println("\\begin{itemize}");
            for (ISectionItem sectionItem : section) {
                writer.println("\\item");
                sectionItem.accept(this);
            }
            writer.print("\n\\end{itemize}");
        } else {
            writer.println();
            writer.println();
        }
    }

    private void writeListTitle(String title) {
        if (Character.isDigit(title.charAt(0))) {
            writer.println("\n\\section{" + title.substring(3) + "}");
        } else {
            writer.println("\n\\subsection{" + title + "}");
        }
    }

    public void visit(ParagraphSection section) {
        String title = section.getSectionTitle().substring(3);
        writer.println("\n\\section{" + title + "}");
        if (section.getParagraph().length() > 0) {
            writer.println(section.getParagraph());
        }
    }

    public void visit(BulletListSectionItem sectionItem) {
        writer.println(sectionItem.getText());
    }

    public void visit(CareerSummarySectionItem sectionItem) {
        writer.println("\\textsc{" + sectionItem.getCompanyName() + "} \\\\");
        writer.println(sectionItem.getJobTitle());
        writer.println("\\\\ \\emph{" + sectionItem.getDate() + "}");
    }

    public void visit(EducationSectionItem sectionItem) {
        writer.println("\\textsc{" + sectionItem.getQualification() + "} \\\\");
        writer.println(sectionItem.getEstablishment());
        writer.println("\\\\ \\emph{" + sectionItem.getLocation() + "}, ");
        writer.println("\\emph{" + sectionItem.getDate() + "}");
    }

    public void visit(ProfessionalExperienceSectionItem sectionItem) {
        writer.println("\\textsc{" + sectionItem.getCompanyName() + "} \\\\");
        writer.println(sectionItem.getJobTitle());
        writer.println("\\\\ \\emph{" + sectionItem.getStartDate() + "} -- ");
        writer.println("\\emph{" + sectionItem.getEndDate() +
                "} \\\\\n\\textbf{Responsibilities:} ");
        if (sectionItem.getResponsibilities().length() > 0) {
            writer.println(sectionItem.getResponsibilities());
        }
        sectionItem.getAchievementsList().accept(this);
        writer.println();
    }
}
