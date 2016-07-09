package jdbc_example;

import jdbc_example.dao.ContactDao;
import jdbc_example.domain.Contact;
import jdbc_example.domain.ContactTelDetail;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Date;
import java.util.Collections;
import java.util.Objects;

public class ReadEntitiesJdbcTemplate {
    public static void main(String[] args) {
        ApplicationContext context
                = new ClassPathXmlApplicationContext("app-context.xml");
        StringBuilder old = new StringBuilder();
        StringBuilder young = new StringBuilder();
        
        ContactDao contactDao = context.getBean("jdbcContactDao", ContactDao.class);
        ContactDao newContactDao = context.getBean("newJdbcContactDao", ContactDao.class);

        doTest(old,contactDao);
        doTest(young,newContactDao);

        String s1 = old.toString();
        String s2 = young.toString();

        if(Objects.equals(s1, s2)) {
            System.out.println("+++++++++++");
            System.out.println("Test passed");
            System.out.println("+++++++++++");
            System.out.println(s1);
        }else {
            System.out.println("-----------");
            System.out.println("Test failed");
            System.out.println("-----------");
            String[] ss1 = s1.split("\r");
            String[] ss2 = s2.split("\r");
            for (int i = 0; i < ss1.length; i++) {
                System.out.println("old --> "+ ss1[i]);
                if (!ss1[i].equals(ss2[i])) {
                    System.out.println("new --> "+ ss2[i]);
                }
            }
            System.out.println("   +++   If only id different it's ok   +++   ");
        }
    }

    static void doTest(StringBuilder stringBuilder, ContactDao contactDao){
        stringBuilder.append("Last name where id = 1 : ").append(contactDao.findLastNameById(1L)).append('\r');
        stringBuilder.append("First name where id = 1 : ").append(contactDao.findFirstNameById(1L)).append('\r');
        stringBuilder.append("Contact where id = 1 : ").append(contactDao.findOne(1L)).append('\r');
        stringBuilder.append("Contact with details where id = 1 : ").append(contactDao.findOneWithDetail(1L)).append('\r');
        stringBuilder.append("All contacts : ").append(contactDao.findAll()).append('\r');
        stringBuilder.append("All contacts with details: ").append(contactDao.findAllWithDetail()).append('\r');
        stringBuilder.append("By first name 'Chris' : ").append(contactDao.findByFirstName("Chris")).append('\r');

        Contact contact123 = new Contact();
        ContactTelDetail contactTelDetail123 = new ContactTelDetail();
        contactTelDetail123.setTelType("123");
        contactTelDetail123.setTelNumber("123");
        contact123.setFirstName("123");
        contact123.setLastName("123");
        contact123.setBirthDate(new Date(System.currentTimeMillis()));
        contact123.setContactTelDetails(Collections.singletonList(contactTelDetail123));

        contactDao.insert(contact123);
        stringBuilder.append("Insert 123 ---").append('\r');
        stringBuilder.append("All contacts with details: ").append(contactDao.findAllWithDetail()).append('\r');
        contact123 = contactDao.findByFirstName("123").get(0);
        stringBuilder.append("By first name '123', id : ").append(contact123.getId()).append('\r');
        stringBuilder.append("Update 123 LastName to 321 ---").append('\r');
        contact123.setLastName("321");
        contactDao.update(contact123);
        stringBuilder.append("All contacts with details: ").append(contactDao.findAllWithDetail()).append('\r');
        stringBuilder.append("DELETE 123 ---").append('\r');
        contactDao.delete(contact123.getId());
        stringBuilder.append("All contacts with details: ").append(contactDao.findAllWithDetail()).append('\r');

    }
}
