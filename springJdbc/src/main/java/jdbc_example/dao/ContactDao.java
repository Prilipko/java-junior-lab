package jdbc_example.dao;

import jdbc_example.domain.Contact;

import java.util.List;

public interface ContactDao {
    List<Contact> findAll();
    List<Contact> findAllWithDetail();
    List<Contact> findByFirstName(String firstName);
    Contact findOne(Long id);
    Contact findOneWithDetail(Long id);
    String findLastNameById(Long id);
    String findFirstNameById(Long id);
    void insert(Contact contact);
    void update(Contact contact);
    void delete(Long id);
}
