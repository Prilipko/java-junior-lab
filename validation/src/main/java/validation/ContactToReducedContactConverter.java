package validation;

import org.springframework.core.convert.converter.Converter;
import validation.domain.Contact;
import validation.domain.ContactReduced;

public class ContactToReducedContactConverter implements Converter<Contact,ContactReduced> {

    @Override
    public ContactReduced convert(Contact contact) {
        ContactReduced reduced = new ContactReduced();
        reduced.setFullName(contact.getFirstName() + " " + contact.getLastName());
        return reduced;
    }
}
