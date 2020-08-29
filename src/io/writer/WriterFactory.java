package io.writer;

import java.io.File;

public class WriterFactory {

    public static IWriter createWriterBasedOnFileExtension(File file) {
        String extension = getExtension(file.getAbsolutePath());
        if (extension.equals("txt")) {
            return new TextWriter(file);
        } else if (extension.equals("tex")) {
            return new LatexWriter(file);
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
