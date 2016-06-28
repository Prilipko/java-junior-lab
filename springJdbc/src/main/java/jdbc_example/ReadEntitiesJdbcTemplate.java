package jdbc_example;

import jdbc_example.dao.ContactDao;
import jdbc_example.domain.Contact;
import jdbc_example.domain.ContactTelDetail;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Date;
import java.util.Collections;

public class ReadEntitiesJdbcTemplate {
    public static void main(String[] args) {
        ApplicationContext context
                = new ClassPathXmlApplicationContext("app-context.xml");
        ContactDao contactDao = context.getBean("contactDao", ContactDao.class);
        System.out.println("Last name where id = 1 : " + contactDao.findLastNameById(1L));
        System.out.println("First name where id = 1 : " + contactDao.findFirstNameById(1L));
        System.out.println("Contact where id = 1 : " + contactDao.findOne(1L));
        System.out.println("Contact with details where id = 1 : " + contactDao.findOneWithDetail(1L));
        System.out.println("All contacts : " + contactDao.findAll());
        System.out.println("All contacts with details: " + contactDao.findAllWithDetail());
        System.out.println("By first name 'Chris' : " + contactDao.findByFirstName("Chris"));

        Contact contact123 = new Contact();
        ContactTelDetail contactTelDetail123 = new ContactTelDetail();
        contactTelDetail123.setTelType("123");
        contactTelDetail123.setTelNumber("123");
        contact123.setFirstName("123");
        contact123.setLastName("123");
        contact123.setBirthday(new Date(System.currentTimeMillis()));
        contact123.setContactTelDetails(Collections.singletonList(contactTelDetail123));

        contactDao.insert(contact123);
        System.out.println("Insert 123 ---");
        System.out.println("All contacts with details: " + contactDao.findAllWithDetail());
        contact123 = contactDao.findByFirstName("123").get(0);
        System.out.println("By first name '123', id : " + contact123.getId());
        System.out.println("Update 123 LastName to 321 ---");
        contact123.setLastName("321");
        contactDao.update(contact123);
        System.out.println("All contacts with details: " + contactDao.findAllWithDetail());
        System.out.println("DELETE 123 ---");
        contactDao.delete(contact123.getId());
        System.out.println("All contacts with details: " + contactDao.findAllWithDetail());

    }
}
