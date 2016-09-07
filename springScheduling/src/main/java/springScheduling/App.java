package springScheduling;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Alexander
 * 07.09.2016
 */
public class App {
    public static void main(String[] args) throws InterruptedException {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/schedulingContext.xml");
        Thread.sleep(5000);
    }
}
