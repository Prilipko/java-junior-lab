package contactService.repisitory;

import contactService.domain.Contact;
import org.springframework.data.repository.CrudRepository;

/**
 * @author worker
 * @created 11.09.16.
 */
public interface ContactRepository extends CrudRepository<Contact,Long> {
    Contact findByName(String name);
}
