package jdbc_example.dao.impl.queries;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;
import org.springframework.jdbc.object.UpdatableSqlQuery;

import javax.sql.DataSource;
import java.sql.Types;

public class UpdateContact extends SqlUpdate {
    public UpdateContact(DataSource ds) {
        super(ds, "UPDATE contact " +
                "SET FIRST_NAME = :firstName, " +
                "LAST_NAME = :lastName, " +
                "BIRTH_DATE = :birthDate " +
                "WHERE ID = :id");
        super.declareParameter(new SqlParameter("firstName", Types.VARCHAR));
        super.declareParameter(new SqlParameter("lastName", Types.VARCHAR));
        super.declareParameter(new SqlParameter("birthDate", Types.DATE));
        super.declareParameter(new SqlParameter("id", Types.INTEGER));
    }
}
