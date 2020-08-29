package gui.selector;

import org.junit.*;
import org.junit.rules.ExpectedException;

import sections.GeneralInformationSection;
import cv.CV;
import cv.CVFactory;

public class CVSelectorControllerTest {

    private CVSelectorController controller;
    private CV chronologicalCV;
    private CV functionnalCV;
    private CV chronologicalCV_2;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void before() {
        CVSelectorGUI view = new CVSelectorGUI();
        controller = new CVSelectorController(view);
        CVFactory factory = new CVFactory();
        chronologicalCV = factory.createCV("Create a new chronological CV");
        functionnalCV = factory.createCV("Create a new functional CV");
        chronologicalCV_2 = factory.createCV("Create a new chronological CV");
    }

    @Test
    public void whenCVsHaveDifferentTypesThenExceptionIsThrown() {
        thrown.expect(IllegalArgumentException.class);
        controller.checkErrors(chronologicalCV, functionnalCV);
    }

    @Test
    public void whenCVsHaveDifferentNamesThenExceptionIsThrown() {
        GeneralInformationSection info =
            (GeneralInformationSection)chronologicalCV.getSection(0);
        info.setName("Foo");
        info = (GeneralInformationSection)chronologicalCV_2.getSection(0);
        info.setName("Bar");
        thrown.expect(IllegalArgumentException.class);
        controller.checkErrors(chronologicalCV, chronologicalCV_2);
    }
}
