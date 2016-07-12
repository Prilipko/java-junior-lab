package springHibernate;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import springHibernate.dao.ContactDao;
import springHibernate.domain.Contact;
import springHibernate.domain.ContactTelDetail;
import springHibernate.domain.Hobby;

import java.sql.Date;
import java.util.LinkedHashSet;
import java.util.List;

public class ReadEntitiesHibernate {
    public static void main(String[] args) {
        ApplicationContext context
                = new ClassPathXmlApplicationContext("app-context.xml");

        ContactDao contactDao = context.getBean("contactDao", ContactDao.class);

        System.out.println("Simple find all");
        List<Contact> contacts = contactDao.findAll();

        contacts.forEach(System.out::println);

        try {
            System.out.println(contacts.get(0).getHobbies());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            System.out.println(contacts.get(0).getContactTelDetails());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println();
        System.out.println("Find all with detail");
        List<Contact> contactsWithDetails = contactDao.findAllWithDetail();
        contactsWithDetails.forEach(ReadEntitiesHibernate::printContact);

        System.out.println();
        System.out.println("Get by Id = 1");
        Contact contact = contactDao.findById(1L);
        printContact(contact);

        System.out.println();
        System.out.println("Create one");
        ContactTelDetail contactTelDetail1 = new ContactTelDetail();
        contactTelDetail1.setTelType("home");
        contactTelDetail1.setTelNumber("1111111111");
        ContactTelDetail contactTelDetail2 = new ContactTelDetail();
        contactTelDetail2.setTelType("work");
        contactTelDetail2.setTelNumber("2222222222");
        Contact contact1 = new Contact();
        contact1.setFirstName("Den");
        contact1.setLastName("Ben");
        contact1.setBirthDate(new Date(System.currentTimeMillis()));
        contact1.setContactTelDetails(new LinkedHashSet<>());
        contact1.addContactTelDetail(contactTelDetail1);
        contact1.addContactTelDetail(contactTelDetail2);
        contact1.setHobbies(new LinkedHashSet<>());
        Hobby hobby1= new Hobby(); hobby1.setHobbyId("Swimming");
        contact1.getHobbies().add(hobby1);
//        Hobby hobby2= new Hobby(); hobby2.setHobbyId("Codding");
//        contact1.getHobbies().add(hobby2); // Referential integrity constraint violation
        Hobby hobby3= new Hobby(); hobby3.setHobbyId("Programming");
        contact1.getHobbies().add(hobby3);
        contactDao.save(contact1);
        contactsWithDetails = contactDao.findAllWithDetail();
        contactsWithDetails.forEach(ReadEntitiesHibernate::printContact);

        System.out.println();
        System.out.println("Update one");
        contact = contactDao.findById(4L);
        contact.removeContactTelDetails((ContactTelDetail) contact.getContactTelDetails().toArray()[0]);
        ContactTelDetail contactTelDetail3 = new ContactTelDetail();
        contactTelDetail3.setTelType("lover");
        contactTelDetail3.setTelNumber("2222222222");
        contact.addContactTelDetail(contactTelDetail3);
        contact.setFirstName("Blah");
        contact.getHobbies().remove(hobby1);
        Hobby hobby4= new Hobby(); hobby4.setHobbyId("Jogging");
        contact.getHobbies().add(hobby4);
        contactDao.save(contact);
        contactDao.findAllWithDetail().forEach(ReadEntitiesHibernate::printContact);

        System.out.println();
        System.out.println("Delete one");
        contactDao.delete(contactDao.findById(1L));
        contactDao.findAllWithDetail().forEach(ReadEntitiesHibernate::printContact);
    }

    private static void printContact(Contact contact) {
        System.out.println(contact);
        for (ContactTelDetail contactTelDetail : contact.getContactTelDetails()) {
            System.out.println(">>" + contactTelDetail);
        }
        if (!contact.getHobbies().isEmpty()) System.out.println(">>" + contact.getHobbies());
    }

}
