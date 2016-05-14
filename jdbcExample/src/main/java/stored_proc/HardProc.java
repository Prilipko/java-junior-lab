package stored_proc;

import java.sql.*;

public class HardProc {
    public static void main(String[] args) {
        try (Connection conn =
                     DriverManager.getConnection(
                             "jdbc:mysql://localhost:3306/mytest", "root", "root")) {

            try (Statement stmt = conn.createStatement()) {
                System.out.println(stmt.execute("DROP PROCEDURE IF EXISTS hardProc"));
            }

            try (Statement stmt = conn.createStatement()) {
                System.out.println(stmt.execute(
                        "CREATE PROCEDURE hardProc(" +
                                "IN a INTEGER, INOUT b INTEGER, " +
                                "OUT y INTEGER, OUT c INTEGER)" +
                                "BEGIN " +
                                "SELECT COUNT(*) INTO c FROM products;" +
                                "SET y = b * c + a; " +
                                "SET b = b + a;" +
                                "SELECT * FROM users;" +
                                "SELECT * FROM products;" +
                                "END "));
            }

            try (CallableStatement cstmt = conn.prepareCall(
                    "{call hardProc(?, ?, ?, ?)}")) {
                cstmt.setInt(1, 1);
                cstmt.setInt(2, 2);
                cstmt.registerOutParameter(2, Types.INTEGER);
                cstmt.registerOutParameter(3, Types.INTEGER);
                cstmt.registerOutParameter(4, Types.INTEGER);

                System.out.println(cstmt.executeUpdate());

                System.out.println("2 = " + cstmt.getInt(2));
                System.out.println("3 = " + cstmt.getInt(3));
                System.out.println("4 = " + cstmt.getInt(4));

                do {
                    try (ResultSet rs = cstmt.getResultSet()) {
                        int count = rs.getMetaData().getColumnCount();
                        while (rs.next()) {
                            for (int i = 1; i < count + 1; i++) {
                                System.out.println(rs.getObject(i));
                            }
                        }
                    }
                }while (cstmt.getMoreResults());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
