package springJpa2.springData.repository;

import org.springframework.data.repository.CrudRepository;
import springJpa2.domain.ContactAudit;

public interface ContactAuditRepository extends CrudRepository<ContactAudit, Long> {

}
