package jdbc_example.dao.impl.queries;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlFunction;

import javax.sql.DataSource;
import java.sql.Types;

public class GetFirstNameByIdFunction extends SqlFunction<String> {
    public GetFirstNameByIdFunction(DataSource ds) {
        super(ds, "SELECT getFirstNameById(?)");
        declareParameter(new SqlParameter(Types.INTEGER));
        compile();
    }
}
