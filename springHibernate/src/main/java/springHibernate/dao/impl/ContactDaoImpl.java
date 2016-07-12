package springHibernate.dao.impl;

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

    SessionFactory sessionFactory;

    @Override
    @Transactional(readOnly = true)
    public List<Contact> findAll() {
        return sessionFactory.getCurrentSession().createQuery("select c from Contact c").list();
    }

    @Override
    public List<Contact> findAllWithDetail() {
        return sessionFactory.getCurrentSession().createNamedQuery("Contact.findAllWithDetail").list();
    }

    @Override
    public Contact findById(Long id) {
        return null;
    }

    @Override
    public Contact save(Contact contact) {
        return null;
    }

    @Override
    public void delete(Contact contact) {

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
