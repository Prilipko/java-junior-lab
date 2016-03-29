package env;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("envTest")
public class EnvTest {
    @Resource(name = "name")
    String name;
    @Resource(name = "home")
    String home;
    public static void main(String[] args) {

        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("env-app-context.xml");

        ConfigurableEnvironment env = ctx.getEnvironment();
        System.out.println(System.getProperty("user.home"));
        System.out.println(env.getProperty("myName"));
        System.out.println(env.getProperty("user.home"));
        System.out.println(ctx.getBean("envTest",EnvTest.class));
    }

    @Override
    public String toString() {
        return "EnvTest{" +
                "name='" + name + '\'' +
                ", home='" + home + '\'' +
                '}';
    }
}
