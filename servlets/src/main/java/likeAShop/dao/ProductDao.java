package likeAShop.dao;

import likeAShop.dao.exception.DaoSystemException;
import likeAShop.dao.exception.NoSuchEntityException;
import likeAShop.entity.Product;

import java.util.List;

/**
 * Created by Alexander on 20.05.2015.
 */
public interface ProductDao {
    public Product selectById(int id) throws DaoSystemException, NoSuchEntityException;
    public List<Product> selectAll() throws DaoSystemException;
}
