package io.reader;

import java.io.File;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import static org.junit.Assert.assertEquals;

public class ReaderFactoryTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void whenTxtFileIsGivenThenTextReaderIsCreated() {
        File file = new File("filename.txt");
        IReader reader  = ReaderFactory.createReaderBasedOnFileExtension(file);
        assertEquals("io.reader.TextReader", reader.getClass().getName());
    }

    @Test
    public void whenTexFileIsGivenThenLatexReaderIsCreated() {
        File file = new File("filename.tex");
        IReader reader  = ReaderFactory.createReaderBasedOnFileExtension(file);
        assertEquals("io.reader.LatexReader", reader.getClass().getName());
    }

    @Test
    public void whenIllegalFileIsGivenThenExceptionIsThrown() {
        File file = new File("filename.doc");
        thrown.expect(IllegalArgumentException.class);
        ReaderFactory.createReaderBasedOnFileExtension(file);
    }

}
