package likeAShop.controller;

import likeAShop.dao.ProductDao;
import likeAShop.dao.exception.DaoSystemException;
import likeAShop.dao.exception.NoSuchEntityException;
import likeAShop.entity.Product;
import likeAShop.tx.TransactionManager;
import myInject.MyInject;
import myInject.MyInjectServlet;
import mySession.ShopSession;
import mySession.ShopSessionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
public class ProductController extends MyInjectServlet {
    private static Logger log = LoggerFactory.getLogger(ProductController.class);

    public static final String PAGE_OK = "product.jsp";

    @MyInject("productDao")
    private ProductDao productDao;
    @MyInject("transactionManager")
    private TransactionManager tx;

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
                Product model = tx.doInTx(() -> productDao.selectById(id));
//                Product model = productDao.selectById(id);
                req.setAttribute(ATTRIBUTE_PRODUCT, model);
                req.setAttribute(ATTRIBUTE_BUCKET, bucket);
                req.getRequestDispatcher(PAGE_OK).forward(req, resp);
                return;
            } catch (Exception e) {
                log.error("Exception in product controller", e);
            }
        }
        resp.sendRedirect(PAGE_ERROR);
    }
}
