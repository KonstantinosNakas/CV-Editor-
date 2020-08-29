package sections.items;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class EducationSectionItemTest {

    private EducationSectionItem item1;
    private EducationSectionItem item2;

    @Before
    public void beforeTest() {
        item1 = new EducationSectionItem("dummy");
        item1.setQualification("dummy");
        item1.setEstablishment("dummy");
        item1.setLocation("dummy");
        item1.setDate(2017);
        item2 = new EducationSectionItem("dummy");
        item2.setQualification("dummy");
        item2.setEstablishment("dummy");
        item2.setLocation("dummy");
        item2.setDate(2016);
    }

    @Test
    public void whenOneItemNoError() {
        assertFalse(item1.hasError(null));
        assertFalse(item2.hasError(null));
    }

    @Test
    public void whenDatesInRightOrderThenNoError() {
        assertFalse(item2.hasError(item1));
    }

    @Test
    public void whenDatesInWrongOrderThenError() {
        assertTrue(item1.hasError(item2));
        assertEquals("Some dates are wrong. Please fix their order.", item1.getErrorDescription());
    }

}
