package inspection;

import rx.Observable;
import rx.Scheduler;
import rx.schedulers.Schedulers;
import util.Util;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by oleksandr.prylypko on 30.06.2017.
 */
public class AllExample {
    public static void main(String[] args) throws IOException {
        Observable.interval(200, TimeUnit.MILLISECONDS, Schedulers.immediate())
                .all(i -> i < 10).subscribe(Util.getSubscriber());
        Observable.interval(200, TimeUnit.MILLISECONDS, Schedulers.immediate())
                .take(10)
                .all(i -> i < 10).subscribe(Util.getSubscriber());

    }
}
