package reducing;


import rx.Observable;

/**
 * Created by oleksandr.prylypko on 29.06.2017.
 */
public class DistinctExample {
    public static void main(String[] args) {
        Observable.just(1, 1, 2, 3, 2).distinct().subscribe(System.out::println);
        System.out.println();
        Observable.just("One", "Two", "Tree", "Four", "Five", "Six", "Seven")
                .distinct(v -> v.charAt(0)).subscribe(System.out::println);
        System.out.println();
        Observable.just(1, 1, 2, 3, 2).distinctUntilChanged()
                .subscribe(System.out::println);
    }
}
