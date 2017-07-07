package inspection;


import rx.Observable;
import util.Util;

/**
 * Created by oleksandr.prylypko on 30.06.2017.
 */
public class SequenceEqualExample {
    public static void main(String[] args) {
        Observable.sequenceEqual(
                Observable.just(1, 2, 3),
                Observable.just("1", "2", "3"),
                (s1, s2) -> s1.toString().equals(s2)).subscribe(Util.getSubscriber());
    }
}
