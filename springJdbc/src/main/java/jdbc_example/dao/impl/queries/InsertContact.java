package jdbc_example.dao.impl.queries;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

public class InsertContact extends SqlUpdate {
    public InsertContact(DataSource ds) {
        super(ds, "INSERT INTO contact (FIRST_NAME, LAST_NAME, BIRTH_DATE) " +
                "VALUES (:firstName, :lastName, :birthDate)");
        super.declareParameter(new SqlParameter("firstName", Types.VARCHAR));
        super.declareParameter(new SqlParameter("lastName", Types.VARCHAR));
        super.declareParameter(new SqlParameter("birthDate", Types.DATE));
        super.setGeneratedKeysColumnNames("id");
        super.setReturnGeneratedKeys(true);
    }
}
