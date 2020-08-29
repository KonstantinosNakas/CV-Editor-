package sections.items;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CareerSummarySectionItemTest {

    private CareerSummarySectionItem item1;
    private CareerSummarySectionItem item2;

    @Before
    public void beforeTest() {
        item1 = new CareerSummarySectionItem();
        item1.setCompanyName("dummy");
        item1.setJobTitle("dummy");
        item1.setDate(2017);
        item2 = new CareerSummarySectionItem();
        item2.setCompanyName("dummy");
        item2.setJobTitle("dummy");
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
