package springJpa2.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springJpa2.domain.Contact;
import springJpa2.service.ContactService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@Service("jpaContactService")
@Repository
@Transactional
public class ContactServiceImpl implements ContactService {
    private Log log = LogFactory.getLog(ContactServiceImpl.class);

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional(readOnly = true)
    public List<Contact> findAll() {
        return em.createNamedQuery("Contact.findAll", Contact.class).getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Contact> findAllWithDetail() {
        return em.createNamedQuery("Contact.findAllWithDetail", Contact.class).getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Contact findById(Long id) {
        TypedQuery<Contact> query = em.createNamedQuery("Contact.findOneById", Contact.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public Contact save(Contact contact) {
        if (contact.getId() == null) {
            log.info("Insert new contact");
            em.persist(contact);
        } else {
            log.info("Updating exist contact");
            contact = em.merge(contact);
            log.info("Updating exist contact");
        }
        log.info("Contact saved with id: " + contact.getId());
        return contact;
    }

    @Override
    public void delete(Contact contact) {
        em.remove(em.merge(contact));
        log.info("Contact with id: " + contact.getId() + " deleted successfully");
    }

    @Override
    @Transactional(readOnly = true)
    public List<Contact> findAllByNativeQuery() {
        return em.createNativeQuery("SELECT ID, FIRST_NAME, LAST_NAME, BIRTH_DATE, VERSION FROM CONTACT", "contactResult").getResultList();
    }

    @Override
    public List<Contact> findByCriteriaQuery(String firstName, String lastName) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Contact> criteriaQuery = cb.createQuery(Contact.class);
        Root<Contact> contactRoot = criteriaQuery.from(Contact.class);
        contactRoot.fetch("contactTelDetails", JoinType.LEFT);
        contactRoot.fetch("hobbies", JoinType.LEFT);

        criteriaQuery.select(contactRoot).distinct(true);

        Predicate predicate = null;
        if (firstName != null) {
            predicate = cb.and(cb.equal(contactRoot.get("firstName"), firstName));
        }
        if (lastName != null) {
            predicate = predicate == null
                    ? cb.and(cb.equal(contactRoot.get("lastName"), lastName))
                    : cb.and(predicate, cb.equal(contactRoot.get("lastName"), lastName));
        }
        if (predicate != null) {
            criteriaQuery.where(predicate);
        }
        return em.createQuery(criteriaQuery).getResultList();
    }

}
