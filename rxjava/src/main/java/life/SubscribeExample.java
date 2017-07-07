package life;

import rx.subjects.ReplaySubject;
import rx.subjects.Subject;

/**
 * Created by oleksandr.prylypko on 29.06.2017.
 */
public class SubscribeExample {
    public static void main(String[] args) {
        Subject<Integer, Integer> s = ReplaySubject.create();
        s.subscribe(System.out::println,System.err::println);
        s.onNext(0);
        s.onError(new Exception("Oops"));
    }
}
