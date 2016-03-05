package likeAShop;

import mySession.CustomHttpSessionOnServerRepository;

/**
 * Created by Worker on 05.03.2016.
 */
public class ShopConstants {
    public static final String PARAM_ID = "id";
    public static final String PARAM_NUMBER = "num";

    public static final String ATTRIBUTE_BUCKET = "bucket";
    public static final String ATTRIBUTE_PRODUCT = "product";
    public static final String ATTRIBUTE_ALL_PRODUCTS = "allProducts";

    public static final String PAGE_ERROR = "error.jsp";

    public static final String SESSION_PROVIDER = CustomHttpSessionOnServerRepository.CONTEXT_ATTRIBUTE;
}
