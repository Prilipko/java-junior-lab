package subjects;

import rx.subjects.ReplaySubject;

/**
 * Created by oleksandr.prylypko on 29.06.2017.
 */
public class ReplaySubjectWithSizeExample {
    public static void main(String[] args) {
        ReplaySubject<Integer> s = ReplaySubject.createWithSize(2);
        s.onNext(0);
        s.onNext(1);
        s.onNext(2);
        s.subscribe(System.out::println);
        s.onNext(3);
    }
}
//        1
//        2
//        3
