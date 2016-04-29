package myInject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static likeAShop.ShopConstants.APP_CTX;

public class MyInjectServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(MyInjectServlet.class);

    private List<Field> getFields() {
        List<Field> result = new LinkedList<>();
        Class<?> clazz = this.getClass();
        while (!clazz.equals(MyInjectServlet.class)) {
            result.addAll(Arrays.asList(clazz.getDeclaredFields()));
            clazz = clazz.getSuperclass();
        }
        return result;
    }

    private List<Field> filterInjectFields(List<Field> fields) {
        List<Field> result = new LinkedList<>();
        for (Field field : fields) {
            MyInject myInject = field.getAnnotation(MyInject.class);
            if (myInject != null) {
                result.add(field);
            }
        }
        return result;
    }

    @Override
    public void init() throws ServletException {
        List<Field> fields = filterInjectFields(getFields());
//        String appCtxPath = getServletContext().getInitParameter(APP_CTX_PATH);
//        ApplicationContext appCtx = new ClassPathXmlApplicationContext(appCtxPath);
        ApplicationContext appCtx = (ApplicationContext) getServletContext().getAttribute(APP_CTX);
        for (Field field : fields) {
            MyInject myInject = field.getAnnotation(MyInject.class);
            String beanName = myInject.value();
            Object bean = appCtx.getBean(beanName);
            field.setAccessible(true);
            try {
                field.set(this,bean);
            } catch (IllegalAccessException e) {
                log.error("IllegalAccessException",e);// e.printStackTrace();
            }
        }

    }
}
