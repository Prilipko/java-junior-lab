package jdbc_example.dao.impl.queries;

import jdbc_example.dao.impl.ContactResultSetExtractor;
import jdbc_example.domain.Contact;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class SelectContactsByFirstName extends MappingSqlQuery<Contact> {
    ContactResultSetExtractor contactResultSetExtractor = ContactResultSetExtractor.getInstance();
    public SelectContactsByFirstName(DataSource ds) {
        super(ds, "SELECT ID, FIRST_NAME, LAST_NAME, BIRTH_DATE FROM contact WHERE FIRST_NAME = :firstName");
        super.declareParameter(new SqlParameter("firstName", Types.VARCHAR));
    }

    @Override
    protected Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
        return contactResultSetExtractor.mapRow(rs,rowNum);
    }
}
