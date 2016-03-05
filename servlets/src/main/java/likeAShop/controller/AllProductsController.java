package likeAShop.controller;

import likeAShop.dao.ProductDao;
import likeAShop.dao.exception.DaoSystemException;
import likeAShop.dao.exception.NoSuchEntityException;
import likeAShop.dao.impl.ProductDaoMock;
import likeAShop.entity.Product;
import mySession.ShopSession;
import mySession.ShopSessionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static likeAShop.ShopConstants.*;
/**
 * Created by Alexander on 20.05.2015.
 */
@WebServlet(urlPatterns = "/allProduct.do", name = "AllProductsController")
public class AllProductsController extends HttpServlet {
    public static  final Logger log = LoggerFactory.getLogger(AllProductsController.class);
    public static final String PAGE_OK = "allProducts.jsp";

    private ProductDao productDao = new ProductDaoMock();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            try {
                ServletContext sc = req.getServletContext();
                ShopSessionRepository repo = (ShopSessionRepository) sc.getAttribute(SESSION_PROVIDER);
                ShopSession session = repo.getSession(req);
                Map<Product, Integer> bucket = new HashMap<>();
                if (session != null) {
                    bucket = (Map<Product, Integer>) session.getAttribute(ATTRIBUTE_BUCKET);
                }
                req.setAttribute(ATTRIBUTE_BUCKET, bucket);


                List<Product> model = productDao.selectAll();
                req.setAttribute(ATTRIBUTE_ALL_PRODUCTS, model);
                req.getRequestDispatcher(PAGE_OK).forward(req, resp);
                return;
            } catch (NumberFormatException | DaoSystemException ignored) {
            }
        resp.sendRedirect(PAGE_ERROR);
    }
}
