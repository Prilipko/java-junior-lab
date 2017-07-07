package observableCreation;

import rx.Observable;

/**
 * Created by oleksandr.prylypko on 29.06.2017.
 */
public class JustExample {
    public static void main(String[] args) {
        Observable<String> values = Observable.just("one", "two", "three");
        values.subscribe(
                v -> System.out.println("Received: " + v),
                e -> System.out.println("Error: " + e),
                () -> System.out.println("Completed")
        );
    }
}
//        Received: one
//        Received: two
//        Received: three
//        Completed
