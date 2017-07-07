package subjects;

import rx.Scheduler;
import rx.schedulers.Schedulers;
import rx.subjects.ReplaySubject;

import java.util.concurrent.TimeUnit;

/**
 * Created by oleksandr.prylypko on 29.06.2017.
 */
public class ReplaySubjectWithTimeExample {
    public static void main(String[] args) throws InterruptedException {
        ReplaySubject<Integer> s = ReplaySubject.createWithTime(1500, TimeUnit.MILLISECONDS, Schedulers.immediate());
        s.onNext(0);
        Thread.sleep(1000);
        s.onNext(1);
        Thread.sleep(1000);
        s.onNext(2);
        s.subscribe(System.out::println);
        s.onNext(3);
    }
}
//        1
//        2
//        3
