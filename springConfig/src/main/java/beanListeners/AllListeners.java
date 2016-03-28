package beanListeners;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.support.GenericXmlApplicationContext;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class AllListeners implements DisposableBean,
        InitializingBean {

    public static void main(String[] args) {
        GenericXmlApplicationContext ctx =new GenericXmlApplicationContext();
        ctx.load("bean-listeners.xml");
        //для вызова destroy у контекста при закрытии приложения
        ctx.registerShutdownHook();
        ctx.refresh();

        ctx.getBean("exampleBean");
    }

    @PostConstruct    //необходимо чтобы IoC контейнер поддерживал JRS-250
    private void postConstructAnnotation() {
        System.out.println("postConstructAnnotation");
    }

    @Override    //необходимо наследовать интерфейс
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean.afterPropertiesSet");
    }

    //необходимо указывать у каждого бина
    private void initMethod() {
        System.out.println("initMethod");
    }

    //не вызывается у прототипа
    @PreDestroy
    private void preDestroyAnnotation() {
        System.out.println("preDestroyAnnotation");
    }

    //не вызывается у прототипа
    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean.destroy");
    }

    //не вызывается у прототипа
    private void destroyMethod() {
        System.out.println("destroyMethod");
    }

}
