package subjects;

import rx.subjects.BehaviorSubject;

/**
 * Created by oleksandr.prylypko on 29.06.2017.
 */
public class BehaviorSubjectExample {
    public static void main(String[] args) {
        BehaviorSubject<Integer> s = BehaviorSubject.create();
        s.onNext(0);
        s.onNext(1);
        s.onNext(2);
        s.subscribe(System.out::println);
        s.onNext(3);
        System.out.println();

        BehaviorSubject<Integer> sWithDef = BehaviorSubject.create(0);
        sWithDef.subscribe(System.out::println);
        sWithDef.onNext(1);
    }
}
//        2
//        3
//
//        0
//        1