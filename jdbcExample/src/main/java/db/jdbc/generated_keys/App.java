package db.jdbc.generated_keys;

import java.sql.*;

public class App {
    public static void main(String[] args) throws SQLException {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/mytest", "root", "root");
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO users (name,password,email,age) VALUES (?,?,?,?);",
                     Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, "John");
            statement.setString(2, "qwe");
            statement.setString(3, "john@mail.com");
            statement.setInt(4, 25);
            int i = statement.executeUpdate();
            System.out.println("Created " + i + " record");
            try (ResultSet ids = statement.getGeneratedKeys()) {
                ids.next();
                int id = ids.getInt(1);
                System.out.println("ID = " + id);
            }
        }
    }
}
