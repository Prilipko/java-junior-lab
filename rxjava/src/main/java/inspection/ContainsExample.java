package inspection;

import rx.Observable;
import util.Util;

/**
 * Created by oleksandr.prylypko on 30.06.2017.
 */
public class ContainsExample {
    public static void main(String[] args) {
        Observable.range(0, 10).contains(5).subscribe(Util.getSubscriber());
        Observable.range(0, 10).contains(10).subscribe(Util.getSubscriber());
    }
}
