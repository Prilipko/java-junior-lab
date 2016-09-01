package validation;


import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.convert.ConversionService;
import validation.domain.Contact;
import validation.domain.ContactReduced;

public class ConvEditorExample {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:conv-editor-app-context.xml");
        ctx.refresh();
        Contact chris = ctx.getBean("chris", Contact.class);
        System.out.println("Chris info: " + chris);
        Contact myContact = ctx.getBean("myContact", Contact.class);
        System.out.println("My contact info: " + myContact);

        ConversionService conversionService = ctx.getBean(ConversionService.class);
        ContactReduced reduced = conversionService.convert(chris,ContactReduced.class);
        System.out.println(reduced);

    }
}
