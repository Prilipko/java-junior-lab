package exampleAop.annotation;

import javax.annotation.Resource;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Secure {
}
