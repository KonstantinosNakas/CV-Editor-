package gui.sections.items;

import javax.swing.JPanel;

import sections.items.BulletListSectionItem;
import sections.items.CareerSummarySectionItem;
import sections.items.EducationSectionItem;
import sections.items.ISectionItem;
import sections.items.ProfessionalExperienceSectionItem;

public class SectionItemFactory {

    public static JPanel createSectionItemGUI(ISectionItem model, String description) {
        if (description.equals("3. SKILLS AND EXPERIENCE")) {
            return new BulletListSectionItemGUI((BulletListSectionItem)model);
        } else if (description.equals("4. PROFESSIONAL EXPERIENCE")) {
            return new ProfessionalExperienceSectionItemGUI(
                    (ProfessionalExperienceSectionItem)model);
        } else if (description.equals("Achievements")) {
            return new BulletListSectionItemGUI((BulletListSectionItem) model);
        } else if (description.equals("4. CAREER SUMMARY")) {
            return new CareerSummarySectionItemGUI((CareerSummarySectionItem)model);
        } else if (description.equals("5. EDUCATION AND TRAINING")) {
            return new EducationSectionItemGUI((EducationSectionItem)model);
        } else if (description.equals("6. FURTHER COURSES")) {
            return new EducationSectionItemGUI((EducationSectionItem)model);
        } else {
            throw new IllegalArgumentException("Illegal call to SectionItemFactory: "
                    + description);
        }
    }

}
