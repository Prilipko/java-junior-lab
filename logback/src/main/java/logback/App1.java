package logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

/**
 * Logback example 1
 *
 * @author oleksandr.prylypko (oprylypk)
 */
public class App1 {
    private static final Logger LOG = LoggerFactory.getLogger(App1.class);

    public static void main(String[] args) {
        A a = new A(1, 2);
        MDC.put("name", "Zaharov");
        MDC.put("A", a.toString());
        LOG.debug("Hello world {}", a);
        MDC.clear();
        final Exception e = new Exception("Any error");
        LOG.debug("Hello world {}", e.getMessage(), e);
    }

    private static class A {
        final private int a;
        final private int b;

        public A(final int a, final int b) {
            this.a = a;
            this.b = b;
        }

        public int getA() {
            return a;
        }

        public int getB() {
            return b;
        }

        @Override
        public String toString() {
            return String.valueOf(a) + " " + String.valueOf(b);
        }
    }
}
