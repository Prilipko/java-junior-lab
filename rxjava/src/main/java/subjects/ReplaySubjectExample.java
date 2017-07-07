package subjects;

import rx.subjects.ReplaySubject;

/**
 * Created by oleksandr.prylypko on 29.06.2017.
 */
public class ReplaySubjectExample {
    public static void main(String[] args) {
        ReplaySubject<Integer> s = ReplaySubject.create();
        s.subscribe(v -> System.out.println("Early:"+v));
        s.onNext(0);
        s.onNext(1);
        s.subscribe(v -> System.out.println("Late :"+v));
        s.onNext(2);
    }
}
//        Early:0
//        Early:1
//        Late :0
//        Late :1
//        Early:2
//        Late :2