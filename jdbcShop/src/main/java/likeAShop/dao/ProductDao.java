package likeAShop.dao;

import likeAShop.dao.exception.DaoSystemException;
import likeAShop.dao.exception.NoSuchEntityException;
import likeAShop.entity.Product;

import java.util.List;

public interface ProductDao {
    Product selectById(int id) throws DaoSystemException, NoSuchEntityException;

    List<Product> selectAll() throws DaoSystemException;
}
