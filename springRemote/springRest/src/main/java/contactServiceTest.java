import contactService.service.ContactService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Alexander
 * 21.03.2017
 */
public class contactServiceTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("app-context.xml");
        ContactService contactService = context.getBean("contactServiceImpl", ContactService.class);
        contactService.findAll().forEach(System.out::println);
    }
}
