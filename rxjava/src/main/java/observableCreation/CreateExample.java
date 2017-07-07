package observableCreation;

import rx.Observable;

/**
 * Created by oleksandr.prylypko on 29.06.2017.
 */
public class CreateExample {
    public static void main(String[] args) throws InterruptedException {
        Observable<String> values = Observable.unsafeCreate(o -> {
            o.onNext("Hello");
            sleep(500);
            o.onCompleted();
        });
        values.subscribe(
                v -> System.out.println("Received1: " + v),
                e -> System.out.println("Error1: " + e),
                () -> System.out.println("Completed1")
        );
        values.subscribe(
                v -> System.out.println("Received2: " + v),
                e -> System.out.println("Error2: " + e),
                () -> System.out.println("Completed2")
        );

    }

    public static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
//Received1: Hello
//Completed1
//Received2: Hello
//Completed2
