package txXml.repository;


import org.springframework.data.repository.CrudRepository;
import txXml.domain.Contact;

public interface ContactRepository extends CrudRepository<Contact, Long> {
}
