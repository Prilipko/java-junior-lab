package txProgrammatic.service.impl;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import txProgrammatic.domain.Contact;
import txProgrammatic.repository.ContactRepository;
import txProgrammatic.service.ContactService;

import java.util.List;

@Service
@Repository
public class ContactServiceImpl implements ContactService {

    @Autowired
    TransactionTemplate transactionTemplate;

    @Autowired
    ContactRepository contactRepository;

    @Override
    public List<Contact> findAll() {
        return Lists.newArrayList(contactRepository.findAll());
    }

    @Override
    public Contact findById(Long id) {
        return contactRepository.findOne(id);
    }

    @Override
    public Contact save(Contact contact) {
        return contactRepository.save(contact);
    }

    @Override
    public Long countAll() {
        return transactionTemplate.execute(status -> contactRepository.count());
    }

}
