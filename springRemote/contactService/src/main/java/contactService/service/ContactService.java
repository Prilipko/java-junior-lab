package contactService.service;

import contactService.domain.Contact;

import java.util.List;

/**
 * @author worker
 * @created 11.09.16.
 */
public interface ContactService {
    List<Contact> findAll();
    Contact findById(Long id);
    Contact findByName(String name);
    Contact save(Contact contact);
    void delete(Contact contact);
}
