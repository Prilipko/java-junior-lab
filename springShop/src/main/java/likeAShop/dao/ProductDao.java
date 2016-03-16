package likeAShop.dao;

import likeAShop.dao.exception.DaoSystemException;
import likeAShop.dao.exception.NoSuchEntityException;
import likeAShop.entity.Product;

import java.util.List;

public interface ProductDao {
    public Product selectById(int id) throws DaoSystemException, NoSuchEntityException;
    public List<Product> selectAll() throws DaoSystemException;
    public boolean containsProductById(int id) throws DaoSystemException;
}
