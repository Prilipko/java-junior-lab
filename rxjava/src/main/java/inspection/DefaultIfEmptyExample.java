package inspection;

import rx.Observable;
import util.Util;

/**
 * Created by oleksandr.prylypko on 30.06.2017.
 */
public class DefaultIfEmptyExample {
    public static void main(String[] args) {
        Observable.empty().defaultIfEmpty(123).subscribe(Util.getSubscriber());
        Observable.just(321).defaultIfEmpty(123).subscribe(Util.getSubscriber());
    }
}
