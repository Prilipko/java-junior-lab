package likeAShop.tx.impl;

import likeAShop.tx.TransactionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.Callable;

public class TransactionManagerImpl extends TransactionManager {

    private static Logger log = LoggerFactory.getLogger(TransactionManagerImpl.class);
    private DataSource dataSource;

    public TransactionManagerImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private ThreadLocal<Connection> connectionThreadLocal = new ThreadLocal<>();

    @Override
    public Connection getConnection() throws SQLException {
        return connectionThreadLocal.get();
    }

    @Override
    public <T> T doInTx(Callable<T> operation) throws Exception {
        try(Connection conn = dataSource.getConnection()) {
            try  {
                connectionThreadLocal.set(conn);
                conn.setAutoCommit(false);
                T result = operation.call();
                conn.commit();
                return result;
            } catch (Exception e) {
                conn.rollback();
                throw e;
            } finally {
                connectionThreadLocal.remove();
            }
        } catch (SQLException e) {
            throw e;
        }
    }
}
