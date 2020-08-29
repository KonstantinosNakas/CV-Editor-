package sections;

import io.reader.IReader;
import io.writer.IWriter;

public class ParagraphSection extends Section {

    private String paragraph;

    public ParagraphSection(String sectionTitle) {
        super(sectionTitle);
        paragraph = "";
    }

    public boolean hasError() {
        return paragraph.isEmpty();
    }

    public String getErrorDescription() {
        return "Paragraph text is empty. Please add some information.";
    }

    public void accept(IWriter writer) {
        writer.visit(this);
    }

    public void accept(IReader reader) {
        reader.visit(this);
    }

    public void setParagraph(String paragraph) {
        // we do not want to allow black lines to make reading from file easier
        paragraph = paragraph.replace("\n\n", "\n");
        if (paragraph.endsWith("\n")) {
            paragraph = paragraph.substring(0, paragraph.length() - 1);
        }
        this.paragraph = paragraph;
    }

    public String getParagraph() {
        return paragraph;
    }

}
