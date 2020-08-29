package sections.items;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ProfessionalExperienceSectionItemTestTest {

    private ProfessionalExperienceSectionItem item1;
    private ProfessionalExperienceSectionItem item2;

    @Before
    public void beforeTest() {
        item1 = new ProfessionalExperienceSectionItem();
        item1.setCompanyName("dummy");
        item1.setJobTitle("dummy");
        item1.setResponsibilities("dummy");
        item1.setStartDate(2016);
        item1.setEndDate(2017);
        item2 = new ProfessionalExperienceSectionItem();
        item2.setCompanyName("dummy");
        item2.setJobTitle("dummy");
        item2.setResponsibilities("dummy");
        item2.setEndDate(2016);
        item2.setStartDate(2016);
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

    @Test
    public void whenEndDateSmallerThanStartDateThenError() {
        item1.setEndDate(2013);
        assertTrue(item1.hasError(null));
        assertEquals("Start and end date are in wrong order: 2016, 2013", item1.getErrorDescription());
    }

}
