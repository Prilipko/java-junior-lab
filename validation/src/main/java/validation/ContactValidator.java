package validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import validation.domain.Contact;

@Component
public class ContactValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(Contact.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"firstName","firstName.empty");
    }
}
