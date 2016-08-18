package springJpa2.service.impl;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service("contactSummaryUntype")
@Repository
@Transactional
public class ContactSummaryUntypeImpl {
    @PersistenceContext
    private EntityManager em;

    public void displayAllContactSummary() {
        List<?> result = em.createQuery("select c.firstName, c.lastName, t.telNumber " +
                "from Contact c left join c.contactTelDetails t " +
                "where t.telType = 'Home'").getResultList();
        final int[] count = {0};
        result.forEach(row -> {
            Object[] values = (Object[]) row;
            System.out.println(++count[0] + ": " + values[0] + ", " + values[1] + ", " + values[2]);
        });
    }
}
