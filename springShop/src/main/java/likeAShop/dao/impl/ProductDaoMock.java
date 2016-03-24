package likeAShop.dao.impl;

import likeAShop.dao.ProductDao;
import likeAShop.dao.exception.DaoSystemException;
import likeAShop.dao.exception.NoSuchEntityException;
import likeAShop.entity.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Alexander on 20.05.2015.
 */
public class ProductDaoMock implements ProductDao {
    private static Logger log = LoggerFactory.getLogger(ProductDaoMock.class);
    private final Map<Integer, Product> memory = new ConcurrentHashMap<Integer, Product>();

    public ProductDaoMock() {
        this.memory.put(1, new Product(1, "Bread"));
        this.memory.put(2, new Product(2, "Paper"));
        this.memory.put(3, new Product(3, "Sugar"));
    }

    public Product selectById(int id) throws DaoSystemException, NoSuchEntityException {
        log.info("selectById:" + id + " " + getClass().getSimpleName());
        if (!memory.containsKey(id)) {
            throw new NoSuchEntityException("No product for id = " + id + ", only " + memory.size());
        }
        return memory.get(id);
    }

    public List<Product> selectAll() throws DaoSystemException {
        log.info("selectAll: " + getClass().getSimpleName());
        return new ArrayList<Product>(memory.values());
    }

    @Override
    public boolean containsProductById(int id) throws DaoSystemException {
        return memory.containsKey(id);
    }
}
