package jdbc_example.dao.impl.queries;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.BatchSqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

public class InsertContactTelDetail extends BatchSqlUpdate {
    public InsertContactTelDetail(DataSource ds) {
        super(ds, "INSERT INTO " +
                "contact_tel_detail (CONTACT_ID, TEL_TYPE, TEL_NUMBER) " +
                "VALUES (:contactId, :telType, :telNumber)");
        declareParameter(new SqlParameter("contactId", Types.INTEGER));
        declareParameter(new SqlParameter("telType", Types.VARCHAR));
        declareParameter(new SqlParameter("telNumber", Types.VARCHAR));
        setBatchSize(10);
    }
}
