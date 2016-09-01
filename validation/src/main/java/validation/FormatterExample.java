package validation;


import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.convert.ConversionService;
import validation.domain.Contact;

public class FormatterExample {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:formatter-app-context.xml");
        ctx.refresh();
        Contact chris = ctx.getBean("chris", Contact.class);
        System.out.println("Chris info: " + chris);

        ConversionService conversionService = ctx.getBean("conversionService", ConversionService.class);
        String formatted = conversionService.convert(chris.getBirthDate(),String.class);
        System.out.println(formatted);

    }
}
