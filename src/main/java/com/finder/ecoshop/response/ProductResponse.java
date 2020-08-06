package com.finder.ecoshop.response;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import com.finder.ecoshop.core.dto.ProductDTO;
import com.finder.ecoshop.utils.CommonConstant;
import com.finder.ecoshop.utils.CommonUtil;

public class ProductResponse implements Serializable {

	private static final long serialVersionUID = -8858292421878675123L;

	private long prodcut_id;

	private String product_name;

	private String image_path;

	private String price_text;

	private String origninal_price_text;

	private String discount_percentage_text;

	public ProductResponse() {
		super();
	}

	public ProductResponse(ProductDTO dto, HttpServletRequest request) {
		this.prodcut_id = dto.getSeq();
		this.product_name = dto.getName();
		this.image_path = CommonUtil.prepareImagePath(dto.getImagePath1(), request);
		this.price_text = CommonUtil.formatBigDecimalAsCurrency(dto.getPrice(), CommonConstant.CURRENCY_CODE_KS);

		if (dto.isPromotion()) {
			this.origninal_price_text = CommonUtil.formatBigDecimalAsCurrency(dto.getOriginalPrice(),
					CommonConstant.CURRENCY_CODE_KS);
			this.discount_percentage_text = CommonUtil.formatBigDecimalAsCurrency(
					new BigDecimal(dto.getDiscountPercent()), CommonConstant.PERCENTAGE_CODE);
		}
	}

	public long getProdcut_id() {
		return prodcut_id;
	}

	public void setProdcut_id(long prodcut_id) {
		this.prodcut_id = prodcut_id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getImage_path() {
		return image_path;
	}

	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}

	public String getPrice_text() {
		return price_text;
	}

	public void setPrice_text(String price_text) {
		this.price_text = price_text;
	}

	public String getOrigninal_price_text() {
		return origninal_price_text;
	}

	public void setOrigninal_price_text(String origninal_price_text) {
		this.origninal_price_text = origninal_price_text;
	}

	public String getDiscount_percentage_text() {
		return discount_percentage_text;
	}

	public void setDiscount_percentage_text(String discount_percentage_text) {
		this.discount_percentage_text = discount_percentage_text;
	}

}
