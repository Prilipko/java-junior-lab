package validation;


import org.springframework.context.support.GenericXmlApplicationContext;
import validation.domain.Contact;

public class PropEditorExample {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:prop-editor-app-context.xml");
        ctx.refresh();
        Contact chris = ctx.getBean("chris", Contact.class);
        System.out.println("Chris info: " + chris);
        Contact myContact = ctx.getBean("myContact", Contact.class);
        System.out.println("My contact info: " + myContact);
    }
}
