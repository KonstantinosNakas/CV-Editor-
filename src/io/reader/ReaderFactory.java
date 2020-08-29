package io.reader;

import java.io.File;

public class ReaderFactory {

    public static IReader createReaderBasedOnFileExtension(File file) {
        String extension = getExtension(file.getAbsolutePath());
        if (extension.equals("txt")) {
            return new TextReader(file);
        } else if (extension.equals("tex")) {
            return new LatexReader(file);
        } else {
            throw new IllegalArgumentException("Unsupported file extension.");
        }
    }

    private static String getExtension(String filename) {
        int index = filename.lastIndexOf('.');
        if (index > 0) {
            return filename.substring(index + 1);
        } else {
            return "???"; // error (and invalid filename character)
        }
    }
}
