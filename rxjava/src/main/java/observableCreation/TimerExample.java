package observableCreation;

import rx.Observable;
import rx.Scheduler;
import rx.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

/**
 * Created by oleksandr.prylypko on 29.06.2017.
 */
public class TimerExample {
    public static void main(String[] args) throws InterruptedException {
        Observable<Long> values = Observable.timer(100, TimeUnit.MILLISECONDS);
        values.subscribe(
                v -> System.out.println("Received: " + v),
                e -> System.out.println("Error: " + e),
                () -> System.out.println("Completed")
        );
        Thread.sleep(200);
    }
}
//Received: 0
//Completed