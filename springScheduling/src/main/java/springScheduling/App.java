package springScheduling;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import springScheduling.service.AsyncService;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by Alexander
 * 07.09.2016
 */
public class App {
    public static void main(String[] args) throws InterruptedException {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/schedulingContext.xml");
        AsyncService asyncService = context.getBean("asyncService", AsyncService.class);
        asyncService.simpleTask(1);
        asyncService.simpleTask(2);
        asyncService.simpleTask(3);
//        Future<String> result1 = asyncService.futureTask("Sasha");
//        Future<String> result2 = asyncService.futureTask("Anna");
//        Future<String> result3 = asyncService.futureTask("Mania");
//        try {
//            System.out.println(result1.get());
//            System.out.println(result2.get());
//            System.out.println(result3.get());
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
        System.out.println("Main thread is over");
    }
}
