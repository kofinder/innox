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

	private String image_path;

	private String price_desc;

	public ProductResponse() {
		super();
	}

	public ProductResponse(ProductDTO dto, HttpServletRequest request) {
		this.prodcut_id = dto.getSeq();
		this.product_name = dto.getName();
		this.image_path = CommonUtil.prepareImagePath(dto.getImagePath1(), request);
		this.price_desc = CommonUtil.formatBigDecimalAsCurrency(dto.getPrice(), CommonConstant.CURRENCY_CODE_KS);
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

	public String getPrice_desc() {
		return price_desc;
	}

	public void setPrice_desc(String price_desc) {
		this.price_desc = price_desc;
	}

}
