package txProgrammatic;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import txProgrammatic.service.ContactService;

public class App {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:txProg-app-context.xml");
        ContactService contactService = context.getBean("contactServiceImpl", ContactService.class);
        System.out.println("contactService.countAll() = " + contactService.countAll());
    }

}
