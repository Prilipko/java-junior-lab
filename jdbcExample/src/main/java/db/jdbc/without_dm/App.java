package db.jdbc.without_dm;

import com.mysql.jdbc.Driver;
import com.mysql.jdbc.Statement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class App {
    public static void main(String[] args) throws SQLException {
        Driver d = new Driver();
        java.sql.Connection conn = d.connect("jdbc:mysql://localhost:3306/mytest?user=root&password=root",
                new Properties());
        java.sql.Statement s = conn.createStatement();
        ResultSet rs = s.executeQuery("SELECT * FROM products");
        rs.next();
        System.out.println(rs.getString("Name"));


        rs.close();
        s.close();
        conn.close();
    }
}
