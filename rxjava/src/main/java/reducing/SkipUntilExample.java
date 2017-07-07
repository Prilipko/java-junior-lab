package reducing;

import rx.Observable;
import rx.schedulers.Schedulers;
import util.Util;

import java.util.concurrent.TimeUnit;

/**
 * Created by oleksandr.prylypko on 30.06.2017.
 */
public class SkipUntilExample {
    public static void main(String[] args) {
        Observable.interval(1, TimeUnit.SECONDS, Schedulers.immediate()).take(10)
                .skipUntil(Observable.timer(2500, TimeUnit.MILLISECONDS))
                .subscribe(Util.getSubscriber());
    }
}
