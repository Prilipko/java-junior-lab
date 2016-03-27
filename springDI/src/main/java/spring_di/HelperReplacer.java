package spring_di;

import org.springframework.beans.factory.support.MethodReplacer;

import java.lang.reflect.Method;

public class HelperReplacer implements MethodReplacer {
    @Override
    public Object reimplement(Object o, Method method, Object[] objects) throws Throwable {
        if (isThatMethod(method)) {
            return "<" + o.hashCode() + ">";
        } else {
            throw new RuntimeException("Replaced " + method.getName() + " method is fail");
        }
    }

    private boolean isThatMethod(Method method) {
        return
                method.getParameterTypes().length == 0 &&
                        "getMessage".equals(method.getName()) &&
                        method.getReturnType() == String.class;
    }
}
