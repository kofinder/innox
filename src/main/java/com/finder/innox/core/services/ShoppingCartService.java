package com.finder.innox.core.services;

import com.finder.innox.core.dto.ShoppingCartDTO;
import com.finder.innox.request.InstockShoppingCartRequest;

public interface ShoppingCartService {

	Long instockAddToCart(InstockShoppingCartRequest shoppingCartRequest);

	ShoppingCartDTO getShoppingCartById(long id);

	ShoppingCartDTO updateShoppingCart(InstockShoppingCartRequest updateCartRequest);

}
