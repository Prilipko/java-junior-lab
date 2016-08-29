package txXml;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import txXml.service.ContactService;

public class App {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:txXml-app-context.xml");
        ContactService contactService =context.getBean("contactServiceImpl", ContactService.class);
        contactService.findAll().forEach(System.out::println);
    }

}
