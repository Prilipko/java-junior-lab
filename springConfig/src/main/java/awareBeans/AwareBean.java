package awareBeans;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.GenericXmlApplicationContext;

import javax.annotation.PreDestroy;

public class AwareBean implements BeanNameAware, ApplicationContextAware {
    GenericXmlApplicationContext ctx;
    String beanName;

    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("aware-beans.xml");
        ctx.refresh();

        ctx.getBean("myAwareBean");
    }

    @PreDestroy
    private void destroy() {
        System.out.println("Bean " + beanName + " destroyed");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (applicationContext instanceof GenericXmlApplicationContext) {
            ctx = (GenericXmlApplicationContext) applicationContext;
        } else {
            throw new IllegalStateException("App context not generic");
        }
        ctx.registerShutdownHook();
    }

    @Override
    public void setBeanName(String s) {
        beanName = s;
    }
}
