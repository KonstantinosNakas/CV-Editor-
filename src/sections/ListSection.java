package sections;

import io.reader.IReader;
import io.writer.IWriter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import sections.items.ISectionItem;

public class ListSection extends Section implements Iterable<ISectionItem> {

    private ArrayList<ISectionItem> list;
    private String errorDescription;

    public ListSection(String sectionTitle) {
        super(sectionTitle);
        list = new ArrayList<ISectionItem>();
    }

    public void addSectionItem(ISectionItem element) {
        list.add(element);
    }

    public void deleteLastSectionItem() {
        list.remove(list.size() - 1);
    }

    public void swapSectionItems(int index1, int index2) {
        Collections.swap(list, index1, index2);
    }

    public Iterator<ISectionItem> iterator() {
        return list.iterator();
    }

    public boolean hasError() {
        if (list.isEmpty()) {
            errorDescription = "List is empty. Please add some elements.";
            return true;
        }

        for (int i = 0; i < list.size(); ++i) {
            ISectionItem previous = (i == 0) ? null : list.get(i - 1);
            if (list.get(i).hasError(previous)) {
                errorDescription = list.get(i).getErrorDescription();
                return true;
            }
        }

        return false;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void accept(IWriter writer) {
        writer.visit(this);
    }

    public void accept(IReader reader) {
        reader.visit(this);
    }

    public int getSize() {
        return list.size();
    }

}
