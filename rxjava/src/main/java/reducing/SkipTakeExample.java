package reducing;

import rx.Observable;

import java.io.Serializable;

import static util.Util.getSubscriber;


/**
 * Created by oleksandr.prylypko on 30.06.2017.
 */
public class SkipTakeExample {
    public static void main(String[] args) {
        Observable<Integer> source = Observable.just(0, 1, 2, 3, 4);
        source.skip(2).subscribe(getSubscriber());
        System.out.println();
        source.take(2).subscribe(getSubscriber());
        System.out.println();
        Observable<Serializable> bedSource = Observable.unsafeCreate(subscriber -> {
            subscriber.onNext(1);
            subscriber.onNext(2);
            subscriber.onNext(3);
            subscriber.onNext(4);
            subscriber.onError(new Exception("Oops"));
            subscriber.onNext(5);
        });
        bedSource.skip(2).subscribe(getSubscriber());
        System.out.println();
        bedSource.take(2).subscribe(getSubscriber());
    }
}
//value: 2
//value: 3
//value: 4
//complete.
//
//value: 0
//value: 1
//complete.
//
//value: 3
//value: 4
//error: java.lang.Exception: Oops
//
//value: 1
//value: 2
//complete.
