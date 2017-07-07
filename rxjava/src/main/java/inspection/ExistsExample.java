package inspection;

import rx.Observable;
import util.Util;

/**
 * Created by oleksandr.prylypko on 30.06.2017.
 */
public class ExistsExample {
    public static void main(String[] args) {
        Observable.range(0, 10)
                .exists(i -> i == 5)
                .subscribe(Util.getSubscriber("10"));
        Observable.range(0, 5)
                .exists(i -> i == 5)
                .subscribe(Util.getSubscriber("5"));
    }
}
