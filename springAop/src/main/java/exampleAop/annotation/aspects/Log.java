package exampleAop.annotation.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.Arrays;

@Aspect()
@Component
public class Log {
    @Pointcut("execution( * exampleAop.annotation..*(..))")
    public void all() {
    }

    @Before("all()")
    public void before(JoinPoint jp) {
        System.out.println("before " + jp.getSignature() + Arrays.toString(jp.getArgs()));
    }

    @AfterReturning(value = "all()", returning = "ret")
    public void afterRet(JoinPoint jp, Object ret) {
        System.out.println("after returning " + jp.getSignature() + Arrays.toString(jp.getArgs()));
        if (ret != null) {
            System.out.println("returned " + ret.toString());
        }
    }

    @AfterThrowing(value = "all()", throwing = "e")
    public void afterThr(JoinPoint jp, Exception e) {
        System.out.println("after throwing " + jp.getSignature() + Arrays.toString(jp.getArgs()));
        System.out.println("thrown " + e.getMessage());
    }

}
