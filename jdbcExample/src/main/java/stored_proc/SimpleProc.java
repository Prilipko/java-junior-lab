package stored_proc;

import java.sql.*;
import java.util.concurrent.Callable;

public class SimpleProc {
    public static void main(String[] args) {
        try (Connection conn =
                     DriverManager.getConnection(
                             "jdbc:mysql://localhost:3306/mytest", "root", "root")) {

            try (Statement stmt = conn.createStatement()) {
                System.out.println(stmt.execute("DROP PROCEDURE IF EXISTS simpleProc"));
            }

            try (Statement stmt = conn.createStatement()) {
                System.out.println(stmt.execute("CREATE PROCEDURE simpleProc()" +
                        "BEGIN " +
                        "SELECT COUNT(*) FROM products;" +
                        "END "));
            }

            try (CallableStatement cstmt = conn.prepareCall("{call simpleProc}")) {
                System.out.println(cstmt.executeUpdate());
                try (ResultSet rs = cstmt.getResultSet()) {
                    while (rs.next()) {
                        System.out.println(rs.getInt(1));
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
