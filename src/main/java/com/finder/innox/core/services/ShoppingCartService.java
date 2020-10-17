package com.finder.innox.core.services;

import java.util.List;

import com.finder.innox.core.dto.ShoppingCartDTO;
import com.finder.innox.request.CustomProductRequest;
import com.finder.innox.request.InstockShoppingCartRequest;

public interface ShoppingCartService {

	Long instockAddToCart(InstockShoppingCartRequest shoppingCartRequest);

	ShoppingCartDTO getShoppingCartById(long id);

	List<ShoppingCartDTO> updateShoppingCart(InstockShoppingCartRequest updateCartRequest);

	List<ShoppingCartDTO> getShoppingCartListByCusId(long customerId);

	List<ShoppingCartDTO> deleteShoppingCarts(List<Long> cartIds, long customerId) throws Exception;

	Long customAddToCart(CustomProductRequest customProductRequest, String customerName) throws Exception;

	List<ShoppingCartDTO> getShoppingCartsForOrderPreload(List<Long> cartIds);

}
