package spring_di;

import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.Arrays;
public abstract class SingletonUser {
    public abstract Helper getHelper();

    public void doWork() {
        System.out.println(getHelper().getMessage());
    }

    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("app-context-xml.xml");
        ctx.setParent(new ClassPathXmlApplicationContext("common-parent.xml"));
        ctx.refresh();
        SingletonUser singletonUser1 = ctx.getBean("singletonUser", SingletonUser.class);
        SingletonUser singletonUser2 = ctx.getBean("singletonUser", SingletonUser.class);

        SingletonUser standardLookup1 = ctx.getBean("standardLookup", SingletonUser.class);
        SingletonUser standardLookup2 = ctx.getBean("standardLookup", SingletonUser.class);

        singletonUser1.doWork();
        singletonUser2.doWork();
        standardLookup1.doWork();
        standardLookup2.doWork();

        System.out.println(Arrays.toString(ctx.getAliases("singletonUser")));
        System.out.println(ctx.getBean("paramChild"));
    }
}
