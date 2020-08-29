package sections.items;

import io.reader.IReader;
import io.writer.IWriter;

public class BulletListSectionItem implements ISectionItem {

    private String text;

    public BulletListSectionItem() {
        text = "";
    }

    public boolean hasError(ISectionItem previous) {
        return text.isEmpty();
    }

    public String getErrorDescription() {
        return "Bullet list text is empty. Please add some information.";
    }

    public void accept(IWriter writer) {
        writer.visit(this);
    }

    public void accept(IReader reader) {
        reader.visit(this);
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

}
