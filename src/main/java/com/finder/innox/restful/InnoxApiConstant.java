package com.finder.innox.restful;

public interface InnoxApiConstant {
	public static String API_RESOURCES_NAME = "/api/v1.0";
	public static String API_AUTH_RESOURCES_NAME = "/api/auth";

	public static String API_EXECUTE_ENDPOINT = "/hello";
	public static String API_WELCOME = "/welcome";
	public static String API_HOME_PAGE_DATA = "/home";

	public static String API_PRODUCT_LIST = "/products";
	public static String API_PRODUCT_LIST_BY_SUB_CATEGORY = "/products/list_by_sub_category";
	public static String API_PRODUCT_LIST_SEARCH = "/products/search";
	public static String API_PRODUCT_DETAIL = "/product";

	public static String API_CATEGORY_LIST = "/categorys";
	public static String API_SUB_CATEGORY_LIST = "/sub_categorys/list_by_category";

	public static String API_CUSTOM_PRODUCT_LIST = "/custom_products/list_by_sub_category";
	public static String API_CUSTOM_PRODUCT_DETAIl = "/custom_products/detail";

	public static String API_ARTWORK_UPLOAD = "/artwork/upload";
	public static String API_ARTWORK_CATEGORY = "/artwork/categorys";
	public static String API_ARTWORK_DESIGNER = "/artwork/designers";
	public static String API_ARTWORK_LIST_BY_CATEGORY = "/artworks_by_category";
	public static String API_ARTWORK_LIST_BY_DESIGNER = "/artworks_by_designer";
	public static String API_FONT_LIST = "/fonts";

	public static String API_STATE_LIST = "/states";
	public static String API_TOWNSHIP_LIST = "/townships";

	public static String API_USER = "/user";
	public static String API_USER_REGISTER = "/register";
	public static String API_USER_LOGIN = "/login";
	public static String API_DEVICE_TOKEN_UPDATE = "/device_token";

	public static String API_SHOPPING_CART_LIST = "/shopping/carts";
	public static String API_INSTOCK_ADD_TO_CART = "/shopping/cart";
	public static String API_CUSTOM_ADD_TO_CART = "/shopping/custom_add_to_cart";

	public static String API_ORDER_PRELOAD = "/order/preload";
	public static String API_ORDER_CONFIRM = "/order/confirm";
	public static String API_ORDER_HISTORY = "/order/history/{order_status}";
	public static String API_ORDER_DETAIL = "/order/{order_id}";

	public static String API_ANNOUNCEMENT_LIST = "/announcements";
	public static String API_ANNOUNCEMENT_DETAIL = "/announcement/{announcement_id}";

}
