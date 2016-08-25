package springJpa2;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import springJpa2.domain.Contact;
import springJpa2.domain.ContactTelDetail;
import springJpa2.domain.Hobby;
import springJpa2.service.ContactService;
import springJpa2.service.impl.ContactSummaryService;
import springJpa2.service.impl.ContactSummaryUntypeImpl;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

public class App {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:app-context.xml");
        ContactService service = context.getBean("jpaContactService", ContactService.class);
        service.findAll().forEach(System.out::println);
        System.out.println();
        service.findAllWithDetail().forEach(App::printContactWithDetails);
        System.out.println();
        System.out.println("Find by id = 1 : ");
        printContactWithDetails(service.findById(1L));
        System.out.println();
        context.getBean("contactSummaryUntype", ContactSummaryUntypeImpl.class).displayAllContactSummary();
        System.out.println();
        context.getBean("contactSummaryService", ContactSummaryService.class).findAll().forEach(System.out::println);
        System.out.println();
        Contact newContact = new Contact();
        newContact.setFirstName("FirstName");
        newContact.setLastName("LastName");
        newContact.setBirthDate(new Date());
        newContact.addContactTelDetail(new ContactTelDetail("Home", "1111"));
        newContact.addContactTelDetail(new ContactTelDetail("Mobile", "2222"));
        newContact.setHobbies(new HashSet<>(Arrays.asList(new Hobby("Swimming"), new Hobby("Reading"))));
        service.save(newContact);
        long newId = newContact.getId();
        service.findAllWithDetail().forEach(App::printContactWithDetails);
        System.out.println("mutate below...");
        Contact upContact = service.findById(newId);
        upContact.getHobbies().remove(new Hobby("Swimming"));
        upContact.addContactTelDetail(new ContactTelDetail("Work", "3333"));
        upContact.removeContactTelDetails(new ContactTelDetail("Mobile", "2222"));
        ContactTelDetail home = upContact.getContactTelDetails().stream().filter(tel -> tel.getTelType().equals("Home")).findAny().orElse(null);
        home.setTelNumber("1234");
        upContact = service.save(upContact);
        service.findAllWithDetail().forEach(App::printContactWithDetails);
        System.out.println();
        service.delete(upContact);
        service.findAllWithDetail().forEach(App::printContactWithDetails);
        System.out.println();
        Contact forDelete = new Contact();
        forDelete.setFirstName("First");
        forDelete.setLastName("Last");
        forDelete.setBirthDate(new Date());
        service.save(forDelete);
        service.delete(forDelete);
        service.findAllWithDetail().forEach(App::printContactWithDetails);
        System.out.println();
        service.findAllByNativeQuery().forEach(System.out::println);
        System.out.println();
        System.out.println("Find by firstName: Chris");
        service.findByCriteriaQuery("Chris", null).forEach(App::printContactWithDetails);
        System.out.println("Find by lastName: Tiger");
        service.findByCriteriaQuery(null, "Tiger").forEach(App::printContactWithDetails);
        System.out.println("Find by firstName and lastName: John Smith");
        service.findByCriteriaQuery("John", "Smith").forEach(App::printContactWithDetails);
        System.out.println("Find by nothing");
        service.findByCriteriaQuery(null, null).forEach(App::printContactWithDetails);
    }

    public static void printContactWithDetails(Contact contact) {
        System.out.println("Contact: " + contact);
        System.out.println("Tells:");
        contact.getContactTelDetails().forEach(System.out::println);
        System.out.println("Hobbies:");
        contact.getHobbies().forEach(System.out::println);
    }
}
