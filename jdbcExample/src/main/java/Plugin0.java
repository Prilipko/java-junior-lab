import javax.sound.sampled.spi.AudioFileReader;
import java.nio.charset.spi.CharsetProvider;
import java.sql.Driver;
import java.util.ServiceLoader;

public class Plugin0 {
    public static void main(String[] args) {
        ServiceLoader<Driver> drivers = ServiceLoader.load(Driver.class);
        drivers.forEach(System.out::println);
        System.out.println();
        ServiceLoader<CharsetProvider> charsetProviders = ServiceLoader.load(CharsetProvider.class);
        charsetProviders.forEach(System.out::println);
        System.out.println();
        ServiceLoader<AudioFileReader> audioFileReaders = ServiceLoader.load(AudioFileReader.class);
        audioFileReaders.forEach(System.out::println);
    }
}
