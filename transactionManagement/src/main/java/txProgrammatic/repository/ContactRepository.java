package txProgrammatic.repository;


import org.springframework.data.repository.CrudRepository;
import txProgrammatic.domain.Contact;

public interface ContactRepository extends CrudRepository<Contact, Long> {
}
