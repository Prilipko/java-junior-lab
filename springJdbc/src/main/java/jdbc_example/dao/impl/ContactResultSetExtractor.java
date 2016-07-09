package jdbc_example.dao.impl;

import jdbc_example.domain.Contact;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactResultSetExtractor implements RowMapper<Contact> {

    private static final ContactResultSetExtractor INSTANCE = new ContactResultSetExtractor();

    private ContactResultSetExtractor() {
    }

    public synchronized static ContactResultSetExtractor getInstance(){
        return INSTANCE;
    }

    @Override
    public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
        Contact contact = new Contact();
        contact.setId(rs.getLong("ID"));
        contact.setFirstName(rs.getString("FIRST_NAME"));
        contact.setLastName(rs.getString("LAST_NAME"));
        contact.setBirthDate(rs.getDate("BIRTH_DATE"));
        return contact;
    }
}
