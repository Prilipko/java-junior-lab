import java.sql.*;
import java.util.Enumeration;
import java.util.Properties;

public class App1 {
    public static void main(String[] args) throws SQLException {
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        String url = "jdbc:mysql://localhost/mytest?user=root&password=root";
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            System.out.println(driver);
        }

        try (Connection conn = DriverManager.getConnection(url);//, "root", "root");
             Statement s = conn.createStatement();
             ResultSet rs = s.executeQuery("SELECT * FROM products;")) {
            while (rs.next()) {
                System.out.print("Id = " + rs.getString("id") + " : ");
                System.out.println("Name = " + rs.getString("name"));
            }
        }
    }
}
