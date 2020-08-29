package sections;

import io.reader.IReader;
import io.writer.IWriter;

public abstract class Section {

    private String sectionTitle;

    public Section(String sectionTitle) {
        this.sectionTitle = sectionTitle;
    }

    public String getSectionTitle() {
        return sectionTitle;
    }

    abstract public boolean hasError();
    abstract public String getErrorDescription();
    abstract public void accept(IWriter writer);
    abstract public void accept(IReader reader);
}
