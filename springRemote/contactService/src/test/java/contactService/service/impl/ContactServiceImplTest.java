package contactService.service.impl;

import contactService.domain.Contact;
import contactService.service.ContactService;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @author worker
 * @created 11.09.16.
 */
public class ContactServiceImplTest {
    Logger log = LoggerFactory.getLogger(ContactServiceImplTest.class);
    private ContactService contactService;
    @Before
    public void setUp() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("app-context.xml");
        contactService = context.getBean("contactServiceImpl",ContactService.class);
    }

    @Test
    public void findAll() throws Exception {
        List<Contact> contacts = contactService.findAll();
        log.debug(contacts.toString());
    }

}