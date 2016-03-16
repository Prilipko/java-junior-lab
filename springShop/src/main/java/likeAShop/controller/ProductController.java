package likeAShop.controller;

import likeAShop.dao.ProductDao;
import likeAShop.dao.exception.DaoSystemException;
import likeAShop.dao.exception.NoSuchEntityException;
import likeAShop.dao.impl.ProductDaoMock;
import likeAShop.entity.Product;
import myInject.MyInject;
import mySession.ShopSession;
import mySession.ShopSessionRepository;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static likeAShop.ShopConstants.*;

/**
 * Created by Alexander on 20.05.2015.
 */
@WebServlet(urlPatterns = "/product.do", name = "ProductController")
public class ProductController extends HttpServlet {

    public static final String PAGE_OK = "product.jsp";

    @MyInject("productDao")
    private ProductDao productDao = new ProductDaoMock();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter(PARAM_ID);
        if (idStr != null) {
            try {
                ServletContext sc = req.getServletContext();
                ShopSessionRepository repo = (ShopSessionRepository) sc.getAttribute(SESSION_PROVIDER);
                ShopSession session = repo.getSession(req);
                Map<Product, Integer> bucket = new HashMap<>();
                if (session != null) {
                        bucket = (Map<Product, Integer>) session.getAttribute(ATTRIBUTE_BUCKET);
                }

                int id = Integer.valueOf(idStr);
                Product model = productDao.selectById(id);
                req.setAttribute(ATTRIBUTE_PRODUCT, model);
                req.setAttribute(ATTRIBUTE_BUCKET, bucket);
                req.getRequestDispatcher(PAGE_OK).forward(req, resp);
                return;
            } catch (NumberFormatException | DaoSystemException | NoSuchEntityException ignored) {
            }
        }
        resp.sendRedirect(PAGE_ERROR);
    }
}
