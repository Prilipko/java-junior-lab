package observableCreation;

import rx.Observable;

/**
 * Created by oleksandr.prylypko on 29.06.2017.
 */
public class EmptyExample {
    public static void main(String[] args) {
        Observable.empty().subscribe(
                v -> System.out.println("Received: " + v),
                e -> System.out.println("Error: " + e),
                () -> System.out.println("Completed")
        );
    }
}
//Completed