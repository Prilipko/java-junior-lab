package subjects;

import rx.subjects.PublishSubject;

/**
 * Created by oleksandr.prylypko on 29.06.2017.
 */
public class PublishSubjectExample {
    public static void main(String[] args) {
        PublishSubject<Integer> subject = PublishSubject.create();
        subject.onNext(1);
        subject.subscribe(System.out::println);
        subject.onNext(2);
        subject.onNext(3);
        subject.onNext(4);
    }
}
//2
//3
//4