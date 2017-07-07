package inspection;

import rx.Observable;
import rx.Observer;
import rx.schedulers.Schedulers;
import util.Util;

import java.util.concurrent.TimeUnit;

/**
 * Created by oleksandr.prylypko on 30.06.2017.
 */
public class ElementAtExample {
    public static void main(String[] args) {
        Observable.interval(500, TimeUnit.MILLISECONDS, Schedulers.trampoline())
                .map(aLong -> aLong * 2)
                .elementAt(3).subscribe(Util.getSubscriber());
        Observable.range(0,10).elementAtOrDefault(10,123).subscribe(Util.getSubscriber());
    }
}
