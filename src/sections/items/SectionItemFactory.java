package sections.items;

public class SectionItemFactory {

    public static ISectionItem createSectionItem(String description) {
        if (description.contains("SKILLS AND EXPERIENCE")) {
            return new BulletListSectionItem();
        } else if (description.contains("PROFESSIONAL EXPERIENCE")) {
            return new ProfessionalExperienceSectionItem();
        } else if (description.equals("Achievements")) {
            return new BulletListSectionItem();
        } else if (description.contains("CAREER SUMMARY")) {
            return new CareerSummarySectionItem();
        } else if (description.contains("EDUCATION AND TRAINING")) {
            return new EducationSectionItem("Qualification");
        } else if (description.contains("FURTHER COURSES")) {
            return new EducationSectionItem("Course");
        } else {
            throw new IllegalArgumentException("Illegal call to SectionItemFactory: "
                    + description);
        }
    }
}
