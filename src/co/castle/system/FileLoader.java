package co.castle.system;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Load resources from resources folder directory. Allow the change of
 * structure of project without affect the load of resources needed for
 * execute the application.
 */
public class FileLoader {

    /**
     * @param filename The file to be opened for reading.
     * @return A new File instance by converting the given pathname string into
     * an abstract pathname.
     */
    public static File getResourceFile(final String filename) {
        return new File(filename);
    }

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

    /**
     * @param filename The file to be opened for reading.
     * @return Creates and return a FileInputStream by opening a connection to
     * an actual file, the file named by the path name name in the file system.
     * @throws FileNotFoundException If the file does not exist, is a directory
     *                               rather than a regular file, or for some
     *                               other reason cannot be opened for reading.
     */
    public static FileInputStream getFileInputStream(final File filename) throws FileNotFoundException {
        return new FileInputStream(filename);
    }
}
