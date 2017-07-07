package streams.finals;

import java.util.stream.Stream;

/**
 * Created by oleksandr.prylypko on 04.07.2017.
 */
public class Example {
    public static void main(String[] args) {
        int last = 0;
        Stream.of(1, 2, 3).forEach(i -> {
//            last = i;
        });
        System.out.println(last);
    }
}
