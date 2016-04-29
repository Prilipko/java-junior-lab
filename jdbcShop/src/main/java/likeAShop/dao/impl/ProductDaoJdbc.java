package likeAShop.dao.impl;

import likeAShop.dao.ProductDao;
import likeAShop.dao.exception.DaoSystemException;
import likeAShop.dao.exception.NoSuchEntityException;
import likeAShop.entity.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoJdbc implements ProductDao {
    private static Logger log = LoggerFactory.getLogger(ProductDaoJdbc.class);
    final private String jdbcUrl;// = "jdbc:mysql://localhost/mytest?user=root&password=root";

    public ProductDaoJdbc(String driverClassName,String jdbcUrl) {
        try {
            Class.forName(driverClassName);//"com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        this.jdbcUrl = jdbcUrl;
    }

    @Override
    public Product selectById(int id) throws DaoSystemException, NoSuchEntityException {
        log.info("selectById:" + id + " " + getClass().getSimpleName());
        try (Connection conn = DriverManager.getConnection(jdbcUrl);
             PreparedStatement prStm = conn.prepareStatement("SELECT name FROM products WHERE id = ?;")) {
            prStm.setInt(1, id);
            try (ResultSet res = prStm.executeQuery()) {
                if (res.next()) {
                    return new Product(id, res.getString("name"));
                } else {
                    throw new NoSuchEntityException("There is no element with id = " + id);
                }
            }
        } catch (SQLException e) {
            throw new DaoSystemException("SQLException when id = " + id, e);
        }
    }

    @Override
    public List<Product> selectAll() throws DaoSystemException {
        log.info("selectAll: " + getClass().getSimpleName());
        try (Connection conn = DriverManager.getConnection(jdbcUrl);
             Statement stm = conn.createStatement();
             ResultSet res = stm.executeQuery("SELECT id, name FROM products;")) {
            List<Product> resultList = new ArrayList<>();
            while (res.next()) {
                resultList.add(new Product(res.getInt("id"), res.getString("name")));
            }
            return resultList;
        } catch (SQLException e) {
            throw new DaoSystemException("SQLException when selectAll", e);
        }
    }

//    public void setJdbcUrl(String jdbcUrl) {
//        this.jdbcUrl = jdbcUrl;
//    }
}
