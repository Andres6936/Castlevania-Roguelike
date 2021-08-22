package co.castle.system;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;

/**
 * Load resources from resources folder directory. Allow the change of
 * structure of project without affect the load of resources needed for
 * execute the application.
 */
public class FileLoader {

    /**
     * @param filename The name of the file to read.
     * @return A new FileReader, given the name of the file to read, using the
     * platform's default charset.
     * @throws FileNotFoundException If the named file does not exist, is a
     *                               directory rather than a regular file, or
     *                               for some other reason cannot be opened for
     *                               reading.
     */
    public static FileReader getFileReader(final String filename) throws FileNotFoundException {
        return new FileReader(ClassLoader.getSystemResource(filename).getFile());
    }

    /**
     * @param filename The file to be opened for reading.
     * @return A new File instance by converting the given pathname string into
     * an abstract pathname.
     */
    public static File getResourceFile(final String filename) {
        return new File(ClassLoader.getSystemResource(filename).getFile());
    }

    /**
     * Returns an input stream for reading the specified resource.
     *
     * @param filename The file to be opened for reading.
     * @return Creates and return a InputStream by opening a connection to
     * an actual file, the file named by the path name name in the file system.
     */
    public static InputStream getInputStream(final String filename) {
        return FileLoader.class.getClassLoader().getResourceAsStream(filename);
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
