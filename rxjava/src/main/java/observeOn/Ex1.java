package observeOn;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.observers.TestSubscriber;
import rx.schedulers.Schedulers;
import rx.schedulers.TestScheduler;
import util.Util;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by oleksandr.prylypko on 04.07.2017.
 */
public class Ex1 {
    private static Subscription subon;

    public static void main(String[] args) throws IOException, InterruptedException {
        TestSubscriber<? super Object> suber = Util.getSubscriber();
        System.out.println("External Thread - " + Thread.currentThread().getName());
        subon = Observable.<Integer>create(subscriber -> {
            subscriber.onNext(1);
            subscriber.onNext(2);
            subscriber.onNext(3);
            subscriber.onCompleted();
        })
                .observeOn(Schedulers.computation())
                .subscribeOn(Schedulers.io())
                .filter(i -> {
                    System.out.println("Filter Thread - " + Thread.currentThread().getName());
                    return i % 2 == 0;
                })
                .observeOn(Schedulers.newThread())
                .map(i -> {
                    System.out.println("Map Thread - " + Thread.currentThread().getName());
                    return Integer.toString(i) + "str";
                })
                .observeOn(Schedulers.io())
                .subscribe(suber);
        suber.awaitTerminalEvent();
        System.out.println("External Thread - finish");
    }
}
