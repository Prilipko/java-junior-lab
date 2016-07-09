package jdbc_example.dao.impl;

import jdbc_example.dao.ContactDao;
import jdbc_example.domain.Contact;
import jdbc_example.domain.ContactTelDetail;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JdbcContactDao implements ContactDao, InitializingBean {

    private DataSource dataSource;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private JdbcTemplate jdbcTemplate;
    private static final ContactResultSetExtractor CONTACT_RESULT_SET_EXTRACTOR = ContactResultSetExtractor.getInstance();
    private static final ContactParameterMapper CONTACT_PARAMETER_MAPPER = ContactParameterMapper.getInstance();
    private static final String INSERT_CONTACT_SQL = "INSERT INTO contact (FIRST_NAME, LAST_NAME, BIRTH_DATE) " +
            "VALUES (?, ?, ?)";
    private static final String INSERT_DETAIL_SQL = "INSERT INTO " +
            "contact_tel_detail (CONTACT_ID, TEL_TYPE, TEL_NUMBER) " +
            "VALUES (?, ?, ?)";
    private static final String UPDATE_SQL = "UPDATE contact " +
            "SET FIRST_NAME = :firstName, " +
            "LAST_NAME = :lastName, " +
            "BIRTH_DATE = :birthDate " +
            "WHERE ID = :id";
    private static final String DELETE_SQL = "DELETE FROM contact " +
            "WHERE ID = :id";

    @Override
    public List<Contact> findAll() {
        return namedParameterJdbcTemplate.query(
                "SELECT ID, FIRST_NAME, LAST_NAME, BIRTH_DATE FROM contact",
                CONTACT_RESULT_SET_EXTRACTOR);
    }

    @Override
    public List<Contact> findAllWithDetail() {
        return namedParameterJdbcTemplate.query(
                "SELECT c.ID, FIRST_NAME, LAST_NAME, BIRTH_DATE, d.ID, TEL_TYPE, TEL_NUMBER " +
                        "FROM contact c LEFT JOIN contact_tel_detail d ON c.ID = d.CONTACT_ID", (resultSet) -> {
                    Map<Long, Contact> contactMap = new HashMap<>();
                    while (resultSet.next()) {
                        Long id = resultSet.getLong("c.ID");
                        Contact contact = null;
                        if (!contactMap.containsKey(id)) {
                            contact = new Contact();
                            List<ContactTelDetail> contactTelDetails = new ArrayList<>();
                            contact.setContactTelDetails(contactTelDetails);

                            contact.setId(resultSet.getLong("c.ID"));
                            contact.setFirstName(resultSet.getString("FIRST_NAME"));
                            contact.setLastName(resultSet.getString("LAST_NAME"));
                            contact.setBirthDate(resultSet.getDate("BIRTH_DATE"));

                            contactMap.put(id, contact);
                        } else {
                            contact = contactMap.get(id);
                        }
                        Long detailId = resultSet.getLong("d.ID");
                        if (!detailId.equals(0L)) {
                            ContactTelDetail contactTelDetail = new ContactTelDetail();
                            contactTelDetail.setId(resultSet.getLong("d.ID"));
                            contactTelDetail.setContactId(contact.getId());
                            contactTelDetail.setTelType(resultSet.getString("TEL_TYPE"));
                            contactTelDetail.setTelNumber(resultSet.getString("TEL_NUMBER"));
                            contact.getContactTelDetails().add(contactTelDetail);
                        }
                    }
                    return new ArrayList<>(contactMap.values());
                });
    }

    @Override
    public List<Contact> findByFirstName(String firstName) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("firstName", firstName);
        return namedParameterJdbcTemplate.query(
                "SELECT ID, FIRST_NAME, LAST_NAME, BIRTH_DATE FROM contact WHERE FIRST_NAME = :firstName",
                parameters,
                CONTACT_RESULT_SET_EXTRACTOR);
    }

    @Override
    public Contact findOne(Long id) {
        return jdbcTemplate.queryForObject(
                "SELECT ID, FIRST_NAME, LAST_NAME, BIRTH_DATE FROM contact WHERE ID = ?",
                CONTACT_RESULT_SET_EXTRACTOR, id);
    }

    @Override
    public Contact findOneWithDetail(Long id) {
        return jdbcTemplate.query(
                "SELECT FIRST_NAME, LAST_NAME, BIRTH_DATE, d.ID, TEL_TYPE, TEL_NUMBER " +
                        "FROM contact c LEFT JOIN contact_tel_detail d ON c.ID  = d.CONTACT_ID " +
                        "WHERE c.ID = ?"
                , (resultSet) -> {
                    Contact contact = new Contact();
                    List<ContactTelDetail> contactTelDetails = new ArrayList<>();
                    contact.setContactTelDetails(contactTelDetails);
                    while (resultSet.next()) {
                        if (contact.getId() == null) {
                            contact.setId(id);
                            contact.setFirstName(resultSet.getString("FIRST_NAME"));
                            contact.setLastName(resultSet.getString("LAST_NAME"));
                            contact.setBirthDate(resultSet.getDate("BIRTH_DATE"));
                        }
                        ContactTelDetail contactTelDetail = new ContactTelDetail();
                        contactTelDetail.setId(resultSet.getLong("d.ID"));
                        contactTelDetail.setContactId(id);
                        contactTelDetail.setTelType(resultSet.getString("TEL_TYPE"));
                        contactTelDetail.setTelNumber(resultSet.getString("TEL_NUMBER"));
                        contactTelDetails.add(contactTelDetail);
                    }
                    return contact;
                }, id);
    }

    @Override
    public String findLastNameById(Long id) {
        return jdbcTemplate.queryForObject(
                "SELECT LAST_NAME FROM contact WHERE ID = ?",
                new Object[]{id}, String.class);
    }

    @Override
    public String findFirstNameById(Long id) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        return namedParameterJdbcTemplate.queryForObject(
                "SELECT FIRST_NAME FROM contact WHERE ID = :id",
                map, String.class);
    }

    @Override
    public void insert(Contact contact) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(INSERT_CONTACT_SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, contact.getFirstName());
            ps.setString(2, contact.getLastName());
            ps.setDate(3, contact.getBirthdate());
            return ps;
        }, keyHolder);
        for (ContactTelDetail contactTelDetail : contact.getContactTelDetails()) {
            contactTelDetail.setContactId(keyHolder.getKey().longValue());
            insert(contactTelDetail);
        }
    }

    private void insert(ContactTelDetail contactTelDetail) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(INSERT_DETAIL_SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, contactTelDetail.getContactId());
            ps.setString(2, contactTelDetail.getTelType());
            ps.setString(3, contactTelDetail.getTelNumber());
            return ps;
        }, keyHolder);
    }

    @Override
    public void update(Contact contact) {
        namedParameterJdbcTemplate.update(UPDATE_SQL, CONTACT_PARAMETER_MAPPER.getMap(contact));
    }

    @Override
    public void delete(Long id) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", id);
        namedParameterJdbcTemplate.update(DELETE_SQL, parameters);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (dataSource == null) {
            throw new BeanCreationException("Must set DataSource in ContactDao");
        }
        if (jdbcTemplate == null) {
            throw new BeanCreationException("jdbcTemplate == null");
        }
        if (namedParameterJdbcTemplate == null) {
            throw new BeanCreationException("namedParameterJdbcTemplate == null");
        }
    }

    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new JdbcTemplate(dataSource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

}
