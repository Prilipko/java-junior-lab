package reducing;

import rx.Observable;
import rx.Scheduler;
import rx.schedulers.Schedulers;
import util.Util;

import java.util.concurrent.TimeUnit;

/**
 * Created by oleksandr.prylypko on 30.06.2017.
 */
public class TakeLastExample {
    public static void main(String[] args) {
        Observable.interval(100, TimeUnit.MILLISECONDS, Schedulers.trampoline()).take(10)
                .takeLast(250, TimeUnit.MILLISECONDS, Schedulers.trampoline())
                .subscribe(Util.getSubscriber());
    }
}
//value: 7
//value: 8
//value: 9
//complete.