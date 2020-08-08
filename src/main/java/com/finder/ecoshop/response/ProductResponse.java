package com.finder.ecoshop.response;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import com.finder.ecoshop.core.dto.ProductDTO;
import com.finder.ecoshop.utils.CommonConstant;
import com.finder.ecoshop.utils.CommonUtil;

public class ProductResponse implements Serializable {

	private static final long serialVersionUID = -8858292421878675123L;

	private long prodcut_id;

	private String product_name;

	private String image_path1;

	private String image_path2;

	private String image_path3;

	private String image_path4;

	private String price_text;

	private String origninal_price_text;

	private String discount_percentage_text;

	public ProductResponse() {
		super();
	}

	public ProductResponse(ProductDTO dto, HttpServletRequest request) {
		this.prodcut_id = dto.getSeq();
		this.product_name = dto.getName();

		if (!CommonUtil.isEmpty(dto.getImagePath1())) {
			this.image_path1 = CommonUtil.prepareImagePath(dto.getImagePath1(), request);
		}

		if (!CommonUtil.isEmpty(dto.getImagePath2())) {
			this.image_path2 = CommonUtil.prepareImagePath(dto.getImagePath2(), request);
		}

		if (!CommonUtil.isEmpty(dto.getImagePath3())) {
			this.image_path3 = CommonUtil.prepareImagePath(dto.getImagePath3(), request);
		}

		if (!CommonUtil.isEmpty(dto.getImagePath4())) {
			this.image_path4 = CommonUtil.prepareImagePath(dto.getImagePath4(), request);
		}

		this.price_text = CommonUtil.formatBigDecimalAsCurrency(dto.getPrice(), CommonConstant.CURRENCY_CODE_KS);

		if (dto.isPromotion()) {
			this.origninal_price_text = CommonUtil.formatBigDecimalAsCurrency(dto.getOriginalPrice(),
					CommonConstant.CURRENCY_CODE_KS);
			this.discount_percentage_text = CommonUtil.formatDiscountPercentage(dto.getDiscountPercent(),
					CommonConstant.PERCENTAGE_CODE);
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

	public String getImage_path1() {
		return image_path1;
	}

	public void setImage_path1(String image_path1) {
		this.image_path1 = image_path1;
	}

	public String getImage_path2() {
		return image_path2;
	}

	public void setImage_path2(String image_path2) {
		this.image_path2 = image_path2;
	}

	public String getImage_path3() {
		return image_path3;
	}

	public void setImage_path3(String image_path3) {
		this.image_path3 = image_path3;
	}

	public String getImage_path4() {
		return image_path4;
	}

	public void setImage_path4(String image_path4) {
		this.image_path4 = image_path4;
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
