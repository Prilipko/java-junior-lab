package jdbc_example.dao.impl.queries;

import jdbc_example.dao.impl.ContactResultSetExtractor;
import jdbc_example.domain.Contact;
import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectAllContacts extends MappingSqlQuery<Contact> {
    ContactResultSetExtractor contactResultSetExtractor = ContactResultSetExtractor.getInstance();
    private static final String SQL = "SELECT ID, FIRST_NAME, LAST_NAME, BIRTH_DATE FROM contact";
    public SelectAllContacts(DataSource ds) {
        super(ds, SQL);
    }

    @Override
    protected Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
        return contactResultSetExtractor.mapRow(rs,rowNum);
    }
}
