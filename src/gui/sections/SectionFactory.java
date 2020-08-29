package gui.sections;

import javax.swing.JPanel;

import sections.GeneralInformationSection;
import sections.ListSection;
import sections.ParagraphSection;
import sections.Section;

public class SectionFactory {

    public static JPanel createSectionGUI(Section section) {
        if (section.getSectionTitle().equals("1. GENERAL INFORMATION")) {
            return new GeneralInformationSectionGUI((GeneralInformationSection)section);
        } else if (section.getSectionTitle().equals("2. PROFESSIONAL PROFILE")) {
            return new ParagraphSectionGUI((ParagraphSection)section);
        } else if (section.getSectionTitle().equals("3. CORE STRENGTHS")) {
            return new ParagraphSectionGUI((ParagraphSection)section);
        } else if (section.getSectionTitle().equals("3. SKILLS AND EXPERIENCE")) {
            return new ListSectionGUI((ListSection)section);
        } else if (section.getSectionTitle().equals("4. PROFESSIONAL EXPERIENCE")) {
            return new ListSectionGUI((ListSection)section);
        } else if (section.getSectionTitle().equals("4. CAREER SUMMARY")) {
            return new ListSectionGUI((ListSection)section);
        } else if (section.getSectionTitle().equals("5. EDUCATION AND TRAINING")) {
            return new ListSectionGUI((ListSection)section);
        } else if (section.getSectionTitle().equals("6. FURTHER COURSES")) {
            return new ListSectionGUI((ListSection)section);
        } else if (section.getSectionTitle().equals("7. ADDITIONAL INFORMATION")) {
            return new ParagraphSectionGUI((ParagraphSection)section);
        } else if (section.getSectionTitle().equals("8. INTERESTS")) {
            return new ParagraphSectionGUI((ParagraphSection)section);
        } else {
            throw new IllegalArgumentException("Illegal call to SectionFactory: "
                    + section.getSectionTitle());
        }
    }

}
