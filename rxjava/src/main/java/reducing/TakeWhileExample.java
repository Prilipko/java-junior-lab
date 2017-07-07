package reducing;


import rx.Observable;
import rx.Scheduler;
import rx.schedulers.Schedulers;
import util.Util;

import java.util.concurrent.TimeUnit;

/**
 * Created by oleksandr.prylypko on 30.06.2017.
 */
public class TakeWhileExample {
    public static void main(String[] args) {
        Observable.interval(1, TimeUnit.SECONDS, Schedulers.newThread())
                .takeWhile(i -> i < 3).subscribe(Util.getSubscriber());
    }
}
