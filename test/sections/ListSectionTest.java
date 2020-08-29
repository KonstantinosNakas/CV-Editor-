package sections;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import sections.items.BulletListSectionItem;

public class ListSectionTest {

    private ListSection list;

    @Before
    public void beforeTest() {
        list = new ListSection("dummy");
    }

    @Test
    public void addSectionItemTest() {
        BulletListSectionItem item = new BulletListSectionItem();
        list.addSectionItem(item);
        assertEquals(1, list.getSize());
    }

    @Test
    public void removeSectionItemTest() {
        BulletListSectionItem item = new BulletListSectionItem();
        assertEquals(0, list.getSize());
        list.addSectionItem(item);
        list.deleteLastSectionItem();
        assertEquals(0, list.getSize());
    }

}
