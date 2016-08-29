package tx.service;

import tx.domain.Contact;

import java.util.List;

public interface ContactService {
    List<Contact> findAll();

    Contact findById(Long id);

    Contact save(Contact contact);

    Long countAll();

    void readRead();

    Contact writeWithoutTX(Contact contact);

    void writeRead();
    void readWrite();
    void writeWrite();
    void writeNewerWrite();
}
