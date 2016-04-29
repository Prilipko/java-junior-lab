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
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import static likeAShop.ShopConstants.*;

@WebServlet(urlPatterns = "/bucket.do", name = "BucketController")
public class BucketController extends MyInjectServlet {
    private static Logger log = LoggerFactory.getLogger(BucketController.class);

    @MyInject("productDao")
    private ProductDao productDao;
    @MyInject("transactionManager")
    private TransactionManager tx;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext sc = req.getServletContext();
        ShopSessionRepository repo = (ShopSessionRepository) sc.getAttribute(SESSION_PROVIDER);

        try {
            int productID = Integer.parseInt(req.getParameter(PARAM_ID));
            int productNumber = Integer.parseInt(req.getParameter(PARAM_NUMBER));
            Product product = tx.doInTx(() -> productDao.selectById(productID));
//            Product product = productDao.selectById(productID);
            ShopSession session = repo.getSession(req, false);
            Map<Product, Integer> newBucket;
            if (session == null) {
                if (productNumber > 0) {
                    session = repo.getSession(req);
                    newBucket = Collections.singletonMap(product, productNumber);
                    session.setAttribute(ATTRIBUTE_BUCKET, newBucket);
                }
            } else {
                Map<Product, Integer> sessionBucket =
                        (Map<Product, Integer>) session.getAttribute(ATTRIBUTE_BUCKET);
                if (sessionBucket == null) {
                    if (productNumber > 0) {
                        newBucket = Collections.singletonMap(product, productNumber);
                        session.setAttribute(ATTRIBUTE_BUCKET, newBucket);
                    }
                } else {
                    Map<Product, Integer> oldBucket = new LinkedHashMap<>(sessionBucket);
                    if (oldBucket.containsKey(product)) {
                        int oldNumber = oldBucket.get(product);
                        int newNumber = oldNumber + productNumber;
                        newNumber = newNumber < 0 ? 0 : newNumber;
                        if (newNumber == 0) {
                            oldBucket.remove(product);
                        } else {
                            oldBucket.put(product, newNumber);
                        }
                    } else {
                        if (productNumber > 0) {
                            oldBucket.put(product, productNumber);
                        }
                    }
                    newBucket = Collections.unmodifiableMap(oldBucket);
                    session.setAttribute(ATTRIBUTE_BUCKET, newBucket);
                }
            }
            repo.saveSession(resp, session);
        } catch (Exception e) {
            log.error("Exception in BucketController", e);
        }
        String referer = req.getHeader("referer");
//        log.info("referer: " + referer);
        resp.sendRedirect(referer);
    }
}
