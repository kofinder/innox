package com.finder.innox.response;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import com.finder.innox.core.dto.ShoppingCartDTO;
import com.finder.innox.utils.CommonConstant;
import com.finder.innox.utils.CommonUtil;

public class ShoppingCartResponse implements Serializable {

	private static final long serialVersionUID = -811169499388094419L;

	private long cart_id;

	private long product_id;

	private String product_name;

	private BigDecimal product_price;

	private String product_price_text;

	private int quantity;

	private BigDecimal product_sub_total;

	private String product_sub_total_text;

	private String product_image;

	public static ShoppingCartResponse transferDtoToResponseData(ShoppingCartDTO cartDto, HttpServletRequest request) {
		ShoppingCartResponse response = new ShoppingCartResponse();
		response.setCart_id(cartDto.getSeq());
		response.setProduct_id(cartDto.getProductDTO().getSeq());
		response.setProduct_name(cartDto.getProductDTO().getName());
		response.setProduct_price(cartDto.getProductDTO().getPrice());
		response.setProduct_price_text(
				CommonUtil.formatBigDecimalAsCurrency(response.getProduct_price(), CommonConstant.CURRENCY_CODE_KS));
		response.setQuantity(cartDto.getQuantity());
		response.setProduct_sub_total(cartDto.getProductSubTotal());
		response.setProduct_sub_total_text(CommonUtil.formatBigDecimalAsCurrency(response.getProduct_sub_total(),
				CommonConstant.CURRENCY_CODE_KS));
		if (!CommonUtil.isEmpty(cartDto.getProductDTO().getImagePath1())) {
			response.setProduct_image(CommonUtil.prepareImagePath(cartDto.getProductDTO().getImagePath1(), request));
		} else if (!CommonUtil.isEmpty(cartDto.getProductDTO().getImagePath2())) {
			response.setProduct_image(CommonUtil.prepareImagePath(cartDto.getProductDTO().getImagePath2(), request));
		} else if (!CommonUtil.isEmpty(cartDto.getProductDTO().getImagePath3())) {
			response.setProduct_image(CommonUtil.prepareImagePath(cartDto.getProductDTO().getImagePath3(), request));
		} else if (!CommonUtil.isEmpty(cartDto.getProductDTO().getImagePath4())) {
			response.setProduct_image(CommonUtil.prepareImagePath(cartDto.getProductDTO().getImagePath4(), request));
		}

		return response;
	}

	public long getCart_id() {
		return cart_id;
	}

	public void setCart_id(long cart_id) {
		this.cart_id = cart_id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public BigDecimal getProduct_price() {
		return product_price;
	}

	public void setProduct_price(BigDecimal product_price) {
		this.product_price = product_price;
	}

	public String getProduct_price_text() {
		return product_price_text;
	}

	public void setProduct_price_text(String product_price_text) {
		this.product_price_text = product_price_text;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getProduct_sub_total() {
		return product_sub_total;
	}

	public void setProduct_sub_total(BigDecimal product_sub_total) {
		this.product_sub_total = product_sub_total;
	}

	public String getProduct_sub_total_text() {
		return product_sub_total_text;
	}

	public void setProduct_sub_total_text(String product_sub_total_text) {
		this.product_sub_total_text = product_sub_total_text;
	}

	public String getProduct_image() {
		return product_image;
	}

	public void setProduct_image(String product_image) {
		this.product_image = product_image;
	}

	public long getProduct_id() {
		return product_id;
	}

	public void setProduct_id(long product_id) {
		this.product_id = product_id;
	}

}
