package exampleAop.annotation.aspects;

import exampleAop.annotation.PrivateSystem;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Aspect
@Component
public class Secure {
    PrivateSystem ps;

    @PostConstruct
    public void init(){
        ps = new PrivateSystem();
    }

    @Pointcut("@annotation(exampleAop.annotation.Secure)")
    public void secure(){}

    @Before("secure()")
    public void before(JoinPoint jp) {
        if ("Sasha".equals(ps.getUser().getName())
                && "123".equals(ps.getUser().getPassword())){
            return;
        }
        throw new SecurityException("Access denied!!!");
    }

}
