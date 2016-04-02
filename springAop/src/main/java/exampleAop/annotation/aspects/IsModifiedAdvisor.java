package exampleAop.annotation.aspects;

import org.springframework.aop.support.DefaultIntroductionAdvisor;
import org.springframework.stereotype.Component;

@Component("advisor")
public class IsModifiedAdvisor extends DefaultIntroductionAdvisor {
    public IsModifiedAdvisor() {
        super(new UserMixinModified());
    }
}
