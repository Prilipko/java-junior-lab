package subjects;

import rx.subjects.AsyncSubject;

/**
 * Created by oleksandr.prylypko on 29.06.2017.
 */
public class AsyncSubjectExample {
    public static void main(String[] args) {
        AsyncSubject<Integer> s = AsyncSubject.create();
        s.subscribe(System.out::println);
        s.onNext(0);
        s.onNext(1);
        s.onNext(2);
        s.onCompleted();
}
}
//      2