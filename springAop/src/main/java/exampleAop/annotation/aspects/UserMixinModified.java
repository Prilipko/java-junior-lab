package exampleAop.annotation.aspects;

import exampleAop.annotation.IsModified;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;

import java.lang.reflect.Method;

public class UserMixinModified extends
        DelegatingIntroductionInterceptor implements IsModified {
    boolean modified = false;

    @Override
    public boolean isModified() {
        return modified;
    }

    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {
        if (mi.getMethod().getName().startsWith("set")
                && mi.getArguments().length == 1) {
            String setterName = mi.getMethod().getName().replaceFirst("set", "get");
            Method getter = mi.getMethod().getDeclaringClass().getMethod(setterName);
            Object oldValue = getter.invoke(mi.getThis());
            Object newValue = mi.getArguments()[0];
            modified = !oldValue.equals(newValue);
        }


        return super.invoke(mi);
    }
}
