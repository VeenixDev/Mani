package de.veenix.mani.utils;

import java.io.File;
import java.io.InputStream;

public class FileUtils {

    /**
     * Gets an InputStream object of a file in the resources directory
     *
     * @param path The path to the file
     * @return The InputStream to the file
     */
    public static InputStream getResource(String path) {
        return FileUtils.class.getResourceAsStream(path);
    }

    /**
     * Gets a file from the local filesystem
     *
     * @param path The path to the file
     * @return The file from the filesystem, or null if the file doesn't exist
     */
    public static File getFile(String path) {
        File file = new File(path);

        return file.exists() ? file : null;
    }

}
