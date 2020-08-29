package cv;

import static org.junit.Assert.*;
import org.junit.*;
import org.junit.rules.*;

public class CVFactoryTest {

    private CVFactory factory;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void beforeTest() {
        factory = new CVFactory();
    }

    @Test
    public void whenNoTypeIsGivenThenExceptionIsThrown() {
        thrown.expect(NullPointerException.class);
        factory.createCV(null);
    }

    @Test
    public void whenChronologicalTypeIsGivenThenChronologicalCVIsCreated() {
        CV result = factory.createCV("Create a new chronological CV");
        assertEquals(ChronologicalCV.class, result.getClass());
    }

    @Test
    public void whenFunctionalTypeIsGivenThenFunctionalCVIsCreated() {
        CV result = factory.createCV("Create a new functional CV");
        assertEquals(FunctionalCV.class, result.getClass());
    }

    @Test
    public void whenCombinedTypeIsGivenThenCombinedCVIsCreated() {
        CV result = factory.createCV("Create a new combined CV");
        assertEquals(CombinedCV.class, result.getClass());
    }

}
