package contactService;

import contactService.service.ContactService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author worker
 * @created 11.09.16.
 */
public class InvokerApp {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("invoker-app-context.xml");
        ContactService contactService = context.getBean("invokerContactService", ContactService.class);
        contactService.findAll().forEach(System.out::println);
    }
}
