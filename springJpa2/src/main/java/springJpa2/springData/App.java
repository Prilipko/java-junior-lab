package springJpa2.springData;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import springJpa2.domain.ContactAudit;
import springJpa2.springData.service.ContactAuditService;
import springJpa2.springData.service.ContactService;

import java.util.Date;

public class App {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:app-context.xml");
        ContactService service = context.getBean("springDataJpaContactService", ContactService.class);
        System.out.println("findAll:");
        service.findAll().forEach(System.out::println);
        System.out.println();
        System.out.println("findByFirstName(John):");
        service.findByFirstName("John").forEach(System.out::println);
        System.out.println();
        System.out.println("findByFirstNameAndLastName(Chris, Schaefer):");
        service.findByFirstNameAndLastName("Chris", "Schaefer").forEach(System.out::println);
        System.out.println();
        ContactAuditService auditingService = context.getBean("contactAuditServiceImpl", ContactAuditService.class);
        System.out.println("findAll in audit:");
        auditingService.findAll().forEach(System.out::println);
        System.out.println();

        ContactAudit contact = new ContactAudit();
        contact.setFirstName("123");
        contact.setLastName("456");
        contact.setBirthDate(new Date());
        contact = auditingService.save(contact);
        System.out.println("Add contact: " + contact);

        System.out.println("findAll in audit:");
        auditingService.findAll().forEach(System.out::println);
        System.out.println();

        contact.setFirstName("666");
        contact = auditingService.save(contact);
        System.out.println("Update contact: " + contact);

        contact.setFirstName("999");
        contact = auditingService.save(contact);
        System.out.println("Update contact: " + contact);

        System.out.println("findAll in audit:");
        auditingService.findAll().forEach(System.out::println);
        System.out.println();

        ContactAudit oldContact = auditingService.findAuditByRevision(1L, 1);
        System.out.println(oldContact);
        oldContact = auditingService.findAuditByRevision(1L, 2);
        System.out.println(oldContact);
        oldContact = auditingService.findAuditByRevision(1L, 3);
        System.out.println(oldContact);
    }

}
