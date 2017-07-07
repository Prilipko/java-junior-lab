package reducing;

import rx.Observable;
import util.Util;

/**
 * Created by oleksandr.prylypko on 30.06.2017.
 */
public class SkipWhileExample {
    public static void main(String[] args) {
        Observable.just(1, 2, 3, 4, 5).skipWhile(i -> i < 3).subscribe(Util.getSubscriber());
    }
}
