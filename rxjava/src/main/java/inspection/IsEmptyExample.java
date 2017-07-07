package inspection;

import rx.Observable;
import rx.schedulers.Schedulers;
import util.Util;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by oleksandr.prylypko on 30.06.2017.
 */
public class IsEmptyExample {
    public static void main(String[] args) throws InterruptedException, IOException {
        Observable.timer(1000, TimeUnit.MILLISECONDS,Schedulers.immediate())
                .isEmpty().subscribe(Util.getSubscriber());
        Observable.empty().isEmpty().subscribe(Util.getSubscriber());
    }
}
