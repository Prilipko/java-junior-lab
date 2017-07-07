package reducing;

import rx.Observable;

/**
 * Created by oleksandr.prylypko on 29.06.2017.
 */
public class FilterExample {
    public static void main(String[] args) {
        Observable.range(0, 10).filter(v -> v % 2 == 0).subscribe(
                System.out::println,
                e -> System.out.println("Error: " + e),
                () -> System.out.println("Completed"));
    }
}
