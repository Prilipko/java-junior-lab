package springJpa2.springData.repository;

import org.springframework.data.repository.CrudRepository;
import springJpa2.domain.Contact;

import java.util.List;

public interface ContactRepository extends CrudRepository<Contact, Long> {

    List<Contact> findByFirstName(String firstName);

    List<Contact> findByFirstNameAndLastName(String firstName, String lastName);

}
