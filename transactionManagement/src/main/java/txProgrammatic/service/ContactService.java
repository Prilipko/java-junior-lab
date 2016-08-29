package txProgrammatic.service;

import txProgrammatic.domain.Contact;

import java.util.List;

public interface ContactService {
    List<Contact> findAll();

    Contact findById(Long id);

    Contact save(Contact contact);

    Long countAll();
}
