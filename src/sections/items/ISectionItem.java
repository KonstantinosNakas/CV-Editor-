package sections.items;

import io.reader.IReader;
import io.writer.IWriter;

public interface ISectionItem {
    public boolean hasError(ISectionItem previous); // to check dates
    public String getErrorDescription();
    abstract public void accept(IWriter writer);
    abstract public void accept(IReader reader);
}
