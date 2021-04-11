package co.castle.system;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Load resources from resources folder directory.
 */
public class FileLoader {

    /**
     * @param filename The system-dependent file name.
     * @return Creates and return a FileInputStream by opening a connection to
     * an actual file, the file named by the path name name in the file system.
     * @throws FileNotFoundException If the file does not exist, is a directory
     *                               rather than a regular file, or for some
     *                               other reason cannot be opened for reading.
     */
    public static FileInputStream getFileInputStream(final String filename) throws FileNotFoundException {
        return new FileInputStream(filename);
    }
}
