package util;

import rx.Subscriber;
import rx.observers.TestSubscriber;

import java.util.Optional;

/**
 * Created by oleksandr.prylypko on 30.06.2017.
 */
public final class Util {
    private Util() {
    }

    public static void sleep(long millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static TestSubscriber<? super Object> getSubscriber() {
        return new TestSubscriber(new ExampleSubscriber());
    }

    public static TestSubscriber getSubscriber(String prefix) {
        return new TestSubscriber(new ExampleSubscriber(prefix));
    }

    static public class ExampleSubscriber extends Subscriber<Object> {
        final private String prefix;

        private ExampleSubscriber() {
            this(null);
        }

        private ExampleSubscriber(String prefix) {
            this.prefix = Optional.ofNullable(prefix).map(s -> s + " - ").orElse("");
        }


        @Override
        public void onCompleted() {
            System.out.println(getThreadPrefix() + prefix + "Completed.");
        }

        @Override
        public void onError(Throwable e) {
            System.out.println(getThreadPrefix() + prefix + "Error: " + e.toString());
        }

        @Override
        public void onNext(Object o) {
            System.out.println(getThreadPrefix() + prefix + "Value: " + o.toString());
        }

        private String getThreadPrefix() {
            return Thread.currentThread().getName() + ": ";
        }
    }
}
