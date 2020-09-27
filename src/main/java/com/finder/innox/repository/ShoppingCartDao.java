package com.finder.innox.repository;

import java.util.List;

import com.finder.innox.core.domain.ShoppingCart;

public interface ShoppingCartDao extends GenericDao<ShoppingCart, Long> {

	ShoppingCart getDataById(long customerId, long shoppingCartId, long productId, long colorId, long sizeId);

	void updateShoppingCart(long id, int quantity);

	List<ShoppingCart> getShoppingCartDataByIds(long customerId, long cartId, long productId);

	void deleteShoppingCard(long cartId);

	List<ShoppingCart> getShoppingCartsForOrderPreload(List<Long> cartIds);

	boolean deleteDataByIdList(List<Long> ids);

}
