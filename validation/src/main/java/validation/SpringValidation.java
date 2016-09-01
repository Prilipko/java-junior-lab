package validation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import validation.domain.Contact;

import java.util.List;

public class SpringValidation {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-validator-app-context.xml");
        Validator validator = context.getBean("contactValidator",Validator.class);
        Contact contact = new Contact();

        BeanPropertyBindingResult result = new BeanPropertyBindingResult(contact,"Chris");
        ValidationUtils.invokeValidator(validator,contact,result);

        List<ObjectError> errors = result.getAllErrors();
        errors.forEach(System.out::println);
    }
}
