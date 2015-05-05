package simple;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Alexander on 30.04.2015.
 */
public class FIS {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("C:\\BS_drv\\Output\\SetupGeConfigurator_1.3.exe");
        System.out.println("fis.available() = " + fis.available());
        byte[] bytes = new byte[fis.available()];
        int i = fis.read(bytes);
        System.out.println("i = " + i);
    }
}
