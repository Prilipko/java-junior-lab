package reducing;

import rx.Observable;
import rx.schedulers.Schedulers;
import util.Util;

import java.util.concurrent.TimeUnit;

/**
 * Created by oleksandr.prylypko on 30.06.2017.
 * Delay on 2,5
 */

public class SkipLastExample {
    public static void main(String[] args) {
        Observable.interval(100, TimeUnit.MILLISECONDS, Schedulers.immediate()).take(10)
                .skipLast(250, TimeUnit.MILLISECONDS, Schedulers.immediate())
                .subscribe(Util.getSubscriber());
    }
}
//value: 0
//value: 1
//value: 2
//value: 3
//value: 4
//value: 5
//value: 6
//complete.