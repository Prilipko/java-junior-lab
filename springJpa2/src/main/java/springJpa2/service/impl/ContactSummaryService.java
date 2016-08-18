package springJpa2.service.impl;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springJpa2.domain.ContactSummary;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@Transactional
@Repository
public class ContactSummaryService {
    @PersistenceContext
    private EntityManager em;

    public List<ContactSummary> findAll() {
        return em.createQuery("select new springJpa2.domain.ContactSummary(c.firstName, c.lastName, t.telNumber) " +
                "from Contact c left join c.contactTelDetails t " +
                "where t.telType = 'Home'", ContactSummary.class).getResultList();
    }
}
