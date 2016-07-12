package springHibernate;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import springHibernate.dao.ContactDao;
import springHibernate.domain.Contact;

import java.util.List;

public class ReadEntitiesHibernate {
    public static void main(String[] args) {
        ApplicationContext context
                = new ClassPathXmlApplicationContext("app-context.xml");

        ContactDao contactDao = context.getBean("contactDao", ContactDao.class);

        List<Contact> contacts = contactDao.findAll();

        for(Contact contact : contacts){
            System.out.println(contact.getId()+"\t"+contact.getFirstName()+"\t"+contact.getLastName()
                    +"\t"+contact.getBirthDate()+"\t"+contact.getVersion());
        }

        try {
            System.out.println(contacts.get(0).getHobbies());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        try {
            System.out.println(contacts.get(0).getContactTelDetails());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }


    }

}
