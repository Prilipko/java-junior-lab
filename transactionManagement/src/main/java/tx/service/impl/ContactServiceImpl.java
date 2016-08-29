package tx.service.impl;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tx.domain.Contact;
import tx.repository.ContactRepository;
import tx.service.ContactService;

import java.util.Date;
import java.util.List;

@Service
@Repository
@Transactional
public class ContactServiceImpl implements ContactService {

    @Autowired
    ContactRepository contactRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Contact> findAll() {
        return Lists.newArrayList(contactRepository.findAll());
    }

    @Transactional(readOnly = true)
    @Override
    public Contact findById(Long id) {
        return contactRepository.findOne(id);
    }

    @Override
    public Contact save(Contact contact) {
        return contactRepository.save(contact);
    }

    @Transactional(propagation = Propagation.NEVER)
    @Override
    public Long countAll() {
        return contactRepository.countAllContacts();
    }

    @Override
    @Transactional(readOnly = true)
    public void readRead() {
        findAll().forEach(System.out::println);
        System.out.println(findById(1L));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Contact writeWithoutTX(Contact contact) {
        return save(contact);
    }

    @Override
    @Transactional
    public void writeRead() {
        save(new Contact("first1","last1",new Date()));
        System.out.println(findById(1L));
    }

    @Override
    @Transactional
    public void readWrite() {
        System.out.println(findById(1L));
        System.out.println(save(new Contact("first2","last2",new Date())));
    }

    @Override
    @Transactional
    public void writeWrite() {
        System.out.println(save(new Contact("first3","last3",new Date())));
        System.out.println(save(new Contact("first4","last4",new Date())));
    }

    @Override
    @Transactional
    public void writeNewerWrite() {
        System.out.println(save(new Contact("first5","last5",new Date())));
        System.out.println(writeWithoutTX(new Contact("first6","last6",new Date())));
        System.out.println(save(new Contact("first7","last7",new Date())));
        rollBack();
    }

    @Transactional()
    public void rollBack(){
        throw new RuntimeException("My Rollback");
    }
}
