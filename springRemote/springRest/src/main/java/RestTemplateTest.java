import contactService.domain.Contact;
import contactService.service.ContactService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import springRest.domain.Contacts;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexander
 * 21.03.2017
 */
public class RestTemplateTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("restful-client-app-context.xml");
        RestTemplate restTemplate = context.getBean("restTemplate", RestTemplate.class);


        Contacts contacts = restTemplate.getForObject("http://localhost:8080/rest/contact/all", Contacts.class);
        contacts.getContacts().forEach(System.out::println);
        System.out.println();

        Contact contact = restTemplate.getForObject("http://localhost:8080/rest/contact/{id}", Contact.class, 1);
        contact.setName("Batman");
        restTemplate.put("http://localhost:8080/rest/contact", contact);

        contacts = restTemplate.getForObject("http://localhost:8080/rest/contact/all", Contacts.class);
        contacts.getContacts().forEach(System.out::println);
        System.out.println();


    }
}
