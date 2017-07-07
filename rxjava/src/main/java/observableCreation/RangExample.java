package observableCreation;

import rx.Observable;

/**
 * Created by oleksandr.prylypko on 29.06.2017.
 */
public class RangExample {
    public static void main(String[] args) {
        Observable<Integer> values = Observable.range(10, 15);
        values.subscribe(
                v -> System.out.println("Received: " + v),
                e -> System.out.println("Error: " + e),
                () -> System.out.println("Completed")
        );
    }
}
//Received: 10
//Received: 11
//Received: 12
//Received: 13
//Received: 14
//Received: 15
//Received: 16
//Received: 17
//Received: 18
//Received: 19
//Received: 20
//Received: 21
//Received: 22
//Received: 23
//Received: 24
//Completed
