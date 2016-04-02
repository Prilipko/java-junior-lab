package exampleAop.annotation;

import org.springframework.stereotype.Component;

@Component
public class Divider {
    public int div(int a, int b) {
        return a / b;
    }
}
