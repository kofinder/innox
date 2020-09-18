package com.finder.innox.core.services.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finder.innox.core.domain.Color;
import com.finder.innox.core.domain.Product;
import com.finder.innox.core.domain.ShoppingCart;
import com.finder.innox.core.domain.Size;
import com.finder.innox.core.domain.User;
import com.finder.innox.core.dto.ShoppingCartDTO;
import com.finder.innox.core.services.ShoppingCartService;
import com.finder.innox.repository.ShoppingCartDao;
import com.finder.innox.request.InstockShoppingCartRequest;
import com.finder.innox.utils.CommonConstant;
import com.finder.innox.utils.CommonUtil;

@Service
@Transactional
public class ShoppigCartServiceImpl implements ShoppingCartService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ShoppingCartDao cartDao;

	@Override
	public Long instockAddToCart(InstockShoppingCartRequest shoppingCartRequest) {
		logger.info("instockAddToCart() >> Start");
		ShoppingCart shoppingCart = null;

		if (cartDao.getDataById(shoppingCartRequest.getCustomer_id(), 0, shoppingCartRequest.getProduct_id(),
				shoppingCartRequest.getColor_id(), shoppingCartRequest.getSize_id()) != null) {
			// Update
			shoppingCart = cartDao.getDataById(0, shoppingCartRequest.getShopping_cart_id(), 0, 0, 0);
			shoppingCart.setQuantity(Math.addExact(shoppingCart.getQuantity(), shoppingCartRequest.getQuantity()));
		} else {
			// Save
			shoppingCart = new ShoppingCart();

			User customer = new User();
			customer.setUserSeq(shoppingCartRequest.getCustomer_id());
			shoppingCart.setCustomer(customer);

			Product product = new Product();
			product.setSeq(shoppingCartRequest.getProduct_id());
			shoppingCart.setProduct(product);

			Color color = new Color();
			color.setSeq(shoppingCartRequest.getColor_id());
			shoppingCart.setColor(color);

			Size size = new Size();
			size.setSeq(shoppingCartRequest.getSize_id());
			shoppingCart.setSize(size);

			shoppingCart.setQuantity(shoppingCartRequest.getQuantity());
			shoppingCart.setIsCustomProduct(false);

			shoppingCart.setCreatedTime(new Date());

		}

		shoppingCart.setUpdatedTime(new Date());
		cartDao.saveOrUpdate(shoppingCart);

		logger.info("instockAddToCart() >> End");
		return shoppingCart.getSeq();
	}

	@Override
	public ShoppingCartDTO getShoppingCartById(long id) {
		logger.info("getShoppingCartById() >> Shopping Cart Id : " + id);
		return new ShoppingCartDTO(cartDao.getDataById(0, id, 0, 0, 0));
	}

	@Override
	public ShoppingCartDTO updateShoppingCart(InstockShoppingCartRequest updateCartRequest) {
		logger.info("updateShoppingCart() >> Start >> Cart Id : " + updateCartRequest.getShopping_cart_id()
				+ " >> Quantity >>" + updateCartRequest.getQuantity());

		cartDao.updateShoppingCart(updateCartRequest.getShopping_cart_id(), updateCartRequest.getQuantity());

		List<ShoppingCart> dtoList = cartDao.getShoppingCartDataByIds(updateCartRequest.getCustomer_id(),
				updateCartRequest.getShopping_cart_id(), 0);

		BigDecimal totalAmount = dtoList.stream().filter(p -> p.getProduct() != null)
				.map(p -> p.getProduct().getPrice().multiply(new BigDecimal(p.getQuantity())))
				.reduce(BigDecimal.ZERO, BigDecimal::add);

		ShoppingCartDTO shoppingCartDTO = new ShoppingCartDTO();
		shoppingCartDTO.setTotalAmount(totalAmount);
		shoppingCartDTO.setTotalAmountText(
				CommonUtil.formatBigDecimalAsCurrency(totalAmount, CommonConstant.CURRENCY_CODE_KS));

		return shoppingCartDTO;
	}

	@Override
	public List<ShoppingCartDTO> getShoppingCartListByCusId(long customerId) {
		logger.info("getShoppingCartListByCusId() >> Start >> Customer Id : " + customerId);
		List<ShoppingCart> entityList = cartDao.getShoppingCartDataByIds(customerId, 0, 0);
		if (entityList == null || entityList.isEmpty()) {
			return new ArrayList<ShoppingCartDTO>();
		}

		List<ShoppingCartDTO> dtoList = new ArrayList<ShoppingCartDTO>();
		entityList.forEach(cart -> {
			ShoppingCartDTO dto = new ShoppingCartDTO(cart);
			if (dto.getProductDTO() != null) {
				dto.setProductSubTotal(dto.getProductDTO().getPrice().multiply(new BigDecimal(dto.getQuantity())));
				dtoList.add(dto);
			}
		});

		return dtoList;
	}

	@Override
	public List<ShoppingCartDTO> deleteShoppingCarts(List<Long> cartIds, long customerId) throws Exception {
		logger.info("deleteShoppingCarts() Start >> Cart Id List >> " + cartIds.size());
		cartIds.forEach(id -> {
			cartDao.deleteShoppingCard(id);
		});
		return this.getShoppingCartListByCusId(customerId);
	}

}
