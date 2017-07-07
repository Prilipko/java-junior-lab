package life;

import rx.Subscription;
import rx.subjects.ReplaySubject;
import rx.subjects.Subject;

/**
 * Created by oleksandr.prylypko on 29.06.2017.
 */
public class UnsubscribeExample {
    public static void main(String[] args) {
        Subject<Integer,Integer> s = ReplaySubject.create();
        Subscription subscription =  s.subscribe(v -> System.out.println("First :"+v));
        Subscription subscription2 = s.subscribe(v -> System.out.println("Second:"+v));
        s.onNext(0);
        s.onNext(1);
        subscription.unsubscribe();
        s.onNext(2);
    }
}
//        First :0
//        Second:0
//        First :1
//        Second:1
//        Second:2
