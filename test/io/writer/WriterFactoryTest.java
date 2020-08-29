package io.writer;

import java.io.File;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import static org.junit.Assert.assertEquals;

public class WriterFactoryTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void whenTxtFileIsGivenThenTextWriterIsCreated() {
        File file = new File("filename.txt");
        IWriter writer  = WriterFactory.createWriterBasedOnFileExtension(file);
        assertEquals("io.writer.TextWriter", writer.getClass().getName());
    }

    @Test
    public void whenTexFileIsGivenThenLatexWriterIsCreated() {
        File file = new File("filename.tex");
        IWriter writer  = WriterFactory.createWriterBasedOnFileExtension(file);
        assertEquals("io.writer.LatexWriter", writer.getClass().getName());
    }

    @Test
    public void whenIllegalFileIsGivenThenExceptionIsThrown() {
        File file = new File("filename.doc");
        thrown.expect(IllegalArgumentException.class);
        WriterFactory.createWriterBasedOnFileExtension(file);
    }

}
