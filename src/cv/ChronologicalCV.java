package cv;

import sections.ListSection;
import sections.ParagraphSection;

public class ChronologicalCV extends CV {

    public ChronologicalCV() {
        addSection(new ParagraphSection("2. PROFESSIONAL PROFILE"));
        addSection(new ParagraphSection("3. CORE STRENGTHS"));
        addSection(new ListSection("4. PROFESSIONAL EXPERIENCE"));
        addSection(new ListSection("5. EDUCATION AND TRAINING"));
        addSection(new ListSection("6. FURTHER COURSES"));
        addSection(new ParagraphSection("7. ADDITIONAL INFORMATION"));
        addSection(new ParagraphSection("8. INTERESTS"));
    }
}
