package stored_proc;

import java.sql.*;

public class LikeAFunction {
    public static void main(String[] args) {
        try (Connection conn =
                     DriverManager.getConnection(
                             "jdbc:mysql://localhost:3306/mytest", "root", "root")) {

            try (Statement stmt = conn.createStatement()) {
                System.out.println(stmt.execute("DROP PROCEDURE IF EXISTS likeFuncProc"));
            }

            try (Statement stmt = conn.createStatement()) {
                System.out.println(stmt.execute(
                        "CREATE FUNCTION likeFuncProc(x INT) RETURNS INT " +
                                "BEGIN " +
                                "RETURN (x * x); " +
                                "END "));
            }

            try (CallableStatement cstmt = conn.prepareCall("{? = call likeFuncProc(?)}")) {
                cstmt.registerOutParameter(1, Types.INTEGER);
                cstmt.setInt(2, 8);
                System.out.println(cstmt.executeUpdate());
                System.out.println(cstmt.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
