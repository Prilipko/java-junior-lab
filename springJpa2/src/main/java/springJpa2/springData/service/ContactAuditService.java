package springJpa2.springData.service;

import springJpa2.domain.ContactAudit;

import java.util.List;

public interface ContactAuditService {
    List<ContactAudit> findAll();
    ContactAudit findById(Long id);
    ContactAudit save(ContactAudit contactAudit);
    ContactAudit findAuditByRevision(Long id, int revision);
}
