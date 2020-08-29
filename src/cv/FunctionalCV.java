package cv;

import sections.ListSection;
import sections.ParagraphSection;

public class FunctionalCV extends CV {

    public FunctionalCV() {
        addSection(new ParagraphSection("2. PROFESSIONAL PROFILE"));
        addSection(new ListSection("3. SKILLS AND EXPERIENCE"));
        addSection(new ListSection("4. CAREER SUMMARY"));
        addSection(new ListSection("5. EDUCATION AND TRAINING"));
        addSection(new ListSection("6. FURTHER COURSES"));
        addSection(new ParagraphSection("7. ADDITIONAL INFORMATION"));
        addSection(new ParagraphSection("8. INTERESTS"));
    }
}
