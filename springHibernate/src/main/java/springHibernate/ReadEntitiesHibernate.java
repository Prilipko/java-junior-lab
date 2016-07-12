package springHibernate;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import springHibernate.dao.ContactDao;
import springHibernate.domain.Contact;
import springHibernate.domain.ContactTelDetail;

import java.util.List;

public class ReadEntitiesHibernate {
    public static void main(String[] args) {
        ApplicationContext context
                = new ClassPathXmlApplicationContext("app-context.xml");

        ContactDao contactDao = context.getBean("contactDao", ContactDao.class);

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
        List<Contact> contactsWithDetails = contactDao.findAllWithDetail();
        for (Contact contact : contactsWithDetails) {
            System.out.println(contact);
            for (ContactTelDetail contactTelDetail : contact.getContactTelDetails()) {
                System.out.println(">>" + contactTelDetail);
            }
            if (!contact.getHobbies().isEmpty()) System.out.println(">>" + contact.getHobbies());
        }


    }

}
