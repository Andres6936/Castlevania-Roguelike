package co.castle.conf;

import co.castle.system.FileLoader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class KeyConfiguration extends Properties {
    public KeyConfiguration() {
        try {
            load(FileLoader.getInputStream("keys.cfg"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.err.println("keys.cfg config file not found");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Problem reading keys.cfg config file");
        }
    }
}
