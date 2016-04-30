package likeAShop.ds;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlDataSource extends MyDataSource {
    private static Logger log = LoggerFactory.getLogger(MySqlDataSource.class);
    final private String jdbcUrl;

    public MySqlDataSource(String driverClassName, String jdbcUrl) {
        try {
            Class.forName(driverClassName);
        } catch (ClassNotFoundException e) {
            log.error("Can't load jdbc driver", e);
        }
        this.jdbcUrl = jdbcUrl;
    }
    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcUrl);
    }
}
