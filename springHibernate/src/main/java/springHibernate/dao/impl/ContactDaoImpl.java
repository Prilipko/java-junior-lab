package springHibernate.dao.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import springHibernate.dao.ContactDao;
import springHibernate.domain.Contact;

import javax.annotation.Resource;
import java.util.List;

@Transactional
@Repository("contactDao")
public class ContactDaoImpl implements ContactDao,InitializingBean {
    Log log = LogFactory.getLog(ContactDaoImpl.class);
    SessionFactory sessionFactory;

    @Override
    @Transactional(readOnly = true)
    public List<Contact> findAll() {
        return sessionFactory.getCurrentSession().createQuery("select c from Contact c",Contact.class).list();
    }

    @Override
    public List<Contact> findAllWithDetail() {
        return sessionFactory.getCurrentSession().createNamedQuery("Contact.findAllWithDetail",Contact.class).list();
    }

    @Override
    public Contact findById(Long id) {
        return sessionFactory.getCurrentSession().createNamedQuery("Contact.findOneById",Contact.class)
                .setParameter("id",id).uniqueResult();
    }

    @Override
    public Contact save(Contact contact) {
        sessionFactory.getCurrentSession().saveOrUpdate(contact);
        log.info("Contact with id : "+contact.getId()+" created or updated");
        return contact;
    }

    @Override
    public void delete(Contact contact) {
        sessionFactory.getCurrentSession().delete(contact);
        log.info("Contact with id : "+contact.getId()+" deleted");
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Resource(name = "sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if(sessionFactory == null){
            throw new BeanCreationException("sessionFactory property must be set");
        }
    }
}
