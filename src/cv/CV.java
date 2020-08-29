package cv;

import java.util.ArrayList;
import java.util.Iterator;

import sections.GeneralInformationSection;
import sections.Section;

public class CV implements Iterable<Section> {

    private String filename;
    private ArrayList<Section> sections;

    public CV() {
        sections = new ArrayList<Section>();
        sections.add(new GeneralInformationSection("1. GENERAL INFORMATION"));
    }

    public Iterator<Section> iterator() {
        return sections.iterator();
    }

    public Section getSection(int index) {
        return sections.get(index);
    }

    public int getSectionsCount() {
        return sections.size();
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }

    public String getAuthorName() {
        return ((GeneralInformationSection)sections.get(0)).getName();
    }

    public void replaceSection(int sectionIndex, Section newSection) {
        sections.set(sectionIndex, newSection);
    }

    protected void addSection(Section section) {
        sections.add(section);
    }

}
