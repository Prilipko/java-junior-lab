package tx;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import tx.service.ContactService;

public class App {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:app-context.xml");
        ContactService contactService =context.getBean("contactServiceImpl", ContactService.class);
        contactService.findAll().forEach(System.out::println);
        System.out.println(contactService.findById(1L));
        contactService.readRead();
        contactService.readWrite();
        contactService.writeRead();
        contactService.writeWrite();
        contactService.writeNewerWrite();
    }

}
