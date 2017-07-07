package observableCreation;

import rx.Observable;

import java.util.Arrays;
import java.util.concurrent.FutureTask;

/**
 * Created by oleksandr.prylypko on 29.06.2017.
 */
public class FromCreation {
    public static void main(String[] args) {
        Observable.from(Arrays.asList(1, 2, 3)).subscribe(
                v -> System.out.println("Received: " + v),
                e -> System.out.println("Error: " + e),
                () -> System.out.println("Completed")
        );
        System.out.println();

        FutureTask<Integer> f = new FutureTask<>(() -> {
            Thread.sleep(1000);
            return 21;
        });
        new Thread(f).start();
        Observable.from(f).subscribe(
                v -> System.out.println("Received: " + v),
                e -> System.out.println("Error: " + e),
                () -> System.out.println("Completed")
        );
    }
}

//Received: 1
//Received: 2
//Received: 3
//Completed
//
//Received: 21
//Completed