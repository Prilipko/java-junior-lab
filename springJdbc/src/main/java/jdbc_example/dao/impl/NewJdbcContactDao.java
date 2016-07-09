package jdbc_example.dao.impl;

import jdbc_example.dao.ContactDao;
import jdbc_example.dao.impl.queries.*;
import jdbc_example.domain.Contact;
import jdbc_example.domain.ContactTelDetail;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class NewJdbcContactDao implements ContactDao, InitializingBean {

    @Resource(name = "jdbcContactDao")
    ContactDao oldContactDao;

    DataSource dataSource;
    SelectAllContacts selectAllContacts;
    SelectContactsByFirstName selectContactsByFirstName;
    UpdateContact updateContact;
    InsertContact insertContact;
    InsertContactTelDetail insertContactTelDetail;
    GetFirstNameByIdFunction getFirstNameByIdFunction;

    private static final Log log = LogFactory.getLog(NewJdbcContactDao.class);
    private static final ContactParameterMapper CONTACT_PARAMETER_MAPPER = ContactParameterMapper.getInstance();
    private static final ContactTelDetailParameterMapper CONTACT_TEL_DETAIL_PARAMETER_MAPPER =
            ContactTelDetailParameterMapper.getInstance();


    @Override
    public List<Contact> findAll() {
        return selectAllContacts.execute();
    }

    @Override
    public List<Contact> findAllWithDetail() {
        return oldContactDao.findAllWithDetail();
    }

    @Override
    public List<Contact> findByFirstName(String firstName) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("firstName", firstName);
        return selectContactsByFirstName.executeByNamedParam(parameters);
    }

    @Override
    public Contact findOne(Long id) {
        return oldContactDao.findOne(id);
    }

    @Override
    public Contact findOneWithDetail(Long id) {
        return oldContactDao.findOneWithDetail(id);
    }

    @Override
    public String findLastNameById(Long id) {
        return oldContactDao.findLastNameById(id);
    }

    @Override
    public String findFirstNameById(Long id) {
        return getFirstNameByIdFunction.execute(id).get(0);
    }

    @Override
    public void insert(Contact contact) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        Map<String, ?> parameters = CONTACT_PARAMETER_MAPPER.getMap(contact);
        parameters.remove("id");
        insertContact.updateByNamedParam(parameters, keyHolder);
        contact.setId(keyHolder.getKey().longValue());
        log.info("New contact inserted with id : " + contact.getId());
        for (ContactTelDetail contactTelDetail : contact.getContactTelDetails()) {
            contactTelDetail.setContactId(contact.getId());
            insertContactTelDetail.updateByNamedParam(
                    CONTACT_TEL_DETAIL_PARAMETER_MAPPER.getMap(contactTelDetail));
        }
        insertContactTelDetail.flush();
    }

    @Override
    public void update(Contact contact) {
        updateContact.updateByNamedParam(CONTACT_PARAMETER_MAPPER.getMap(contact));
    }

    @Override
    public void delete(Long id) {
        oldContactDao.delete(id);
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        selectAllContacts = new SelectAllContacts(dataSource);
        selectContactsByFirstName = new SelectContactsByFirstName(dataSource);
        updateContact = new UpdateContact(dataSource);
        insertContact = new InsertContact(dataSource);
        insertContactTelDetail = new InsertContactTelDetail(dataSource);
        getFirstNameByIdFunction = new GetFirstNameByIdFunction(dataSource);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (dataSource == null) {
            throw new BeanCreationException("Must set DataSource in ContactDao");
        }
        if (selectAllContacts == null) {
            throw new BeanCreationException("selectAllContacts must be set");
        }
        if (selectContactsByFirstName == null) {
            throw new BeanCreationException("selectContactsByFirstName must be set");
        }
        if (updateContact == null) {
            throw new BeanCreationException("updateContact must be set");
        }
        if (insertContact == null) {
            throw new BeanCreationException("insertContact must be set");
        }
        if (insertContactTelDetail == null) {
            throw new BeanCreationException("insertContactTelDetail must be set");
        }
        if (getFirstNameByIdFunction == null) {
            throw new BeanCreationException("getFirstNameByIdFunction must be set");
        }
    }
}
