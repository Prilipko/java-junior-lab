package life;

import rx.subjects.ReplaySubject;
import rx.subjects.Subject;

/**
 * Created by oleksandr.prylypko on 29.06.2017.
 */
public class ErrorCompleteExample {
    public static void main(String[] args) {
        Subject<Integer, Integer> values = ReplaySubject.create();
        values.subscribe(
                v -> System.out.println("Value:" + v),
                e -> System.out.println(e.toString()),
                () -> System.out.println("Completed"));
        values.onNext(0);
        values.onNext(1);
        values.onCompleted();
        values.onNext(2);
    }
}
//        Value:0
//        Value:1
//        Completed