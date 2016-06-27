package jdbc_example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

public class JustStartDb {
    public static void main(String[] args) {
        ApplicationContext context =new ClassPathXmlApplicationContext("app-context.xml");
        DataSource dataSourceBoneCP = context.getBean("dataSourceBoneCP", DataSource.class);
        try (Connection connection = dataSourceBoneCP.getConnection()){
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getDatabaseProductName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
