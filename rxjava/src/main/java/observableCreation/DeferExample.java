package observableCreation;

import rx.Observable;

/**
 * Created by oleksandr.prylypko on 29.06.2017.
 */
public class DeferExample {
    public static void main(String[] args) throws InterruptedException {
        Observable<Long> o = Observable.defer(() -> Observable.just(System.currentTimeMillis()));
        o.subscribe(
                v -> System.out.println("Received1: " + v),
                e -> System.out.println("Error1: " + e),
                () -> System.out.println("Completed1")
        );
        Thread.sleep(100);
        o.subscribe(
                v -> System.out.println("Received2: " + v),
                e -> System.out.println("Error2: " + e),
                () -> System.out.println("Completed2")
        );

    }
}
//Received1: 1498735592065
//Completed1
//Received2: 1498735592171
//Completed2