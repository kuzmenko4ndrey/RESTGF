/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBclasses;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.spi.FileTypeDetector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Neophron
 */
class Picture {

    public static boolean isPicture(String path) {
        Path source = Paths.get(path);
        try {
            return Files.probeContentType(source).contains("image");
        } catch (IOException ex) {
            return false;
        }
    }

}
