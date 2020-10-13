package com.finder.innox.response;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.finder.innox.core.dto.ProductDTO;
import com.finder.innox.utils.CommonConstant;
import com.finder.innox.utils.CommonUtil;

public class ProductDetailResponse implements Serializable {

	private static final long serialVersionUID = -3252273375172086979L;

	private long product_id;

	private String product_name;

	private String code_number;

	private String image_path1;

	private String image_path2;

	private String image_path3;

	private String image_path4;

	private List<String> images = new ArrayList<String>();

	private BigDecimal price;

	private String price_text;

	private String origninal_price_text;

	private String discount_percentage_text;

	private boolean is_promotion;

	private boolean is_popular;

	private boolean is_new_arrival;

	private String detail;

	private String overview;

	List<ColorResponse> color_list = new ArrayList<ColorResponse>();

	List<SizeResponse> size_list = new ArrayList<SizeResponse>();

	public ProductDetailResponse() {
		super();
	}

	public ProductDetailResponse(ProductDTO product, HttpServletRequest request) {
		super();

		this.product_id = product.getSeq();
		this.product_name = product.getName();

		if (!CommonUtil.isEmpty(product.getImagePath1())) {
//			this.image_path1 = CommonUtil.prepareImagePath(product.getImagePath1(), request);
			this.images.add(CommonUtil.prepareImagePath(product.getImagePath1(), request));
		}

		if (!CommonUtil.isEmpty(product.getImagePath2())) {
//			this.image_path2 = CommonUtil.prepareImagePath(product.getImagePath2(), request);
			this.images.add(CommonUtil.prepareImagePath(product.getImagePath2(), request));
		}

		if (!CommonUtil.isEmpty(product.getImagePath3())) {
//			this.image_path3 = CommonUtil.prepareImagePath(product.getImagePath3(), request);
			this.images.add(CommonUtil.prepareImagePath(product.getImagePath3(), request));
		}

		if (!CommonUtil.isEmpty(product.getImagePath4())) {
//			this.image_path4 = CommonUtil.prepareImagePath(product.getImagePath4(), request);
			this.images.add(CommonUtil.prepareImagePath(product.getImagePath4(), request));
		}

		this.price = product.getPrice();
		this.price_text = CommonUtil.formatBigDecimalAsCurrency(product.getPrice(), CommonConstant.CURRENCY_CODE_KS);

		if (product.isPromotion()) {
			this.origninal_price_text = CommonUtil.formatBigDecimalAsCurrency(product.getOriginalPrice(),
					CommonConstant.CURRENCY_CODE_KS);
			this.discount_percentage_text = CommonUtil.formatDiscountPercentage(product.getDiscountPercent(),
					CommonConstant.PERCENTAGE_CODE);
		}

		this.is_promotion = product.isPromotion();
		this.is_popular = product.isPopular();
		this.is_new_arrival = product.isNewArrival();
		this.detail = product.getDetail();
		this.overview = product.getOverview();

		product.getProductColorList().forEach(color -> {
			this.getColor_list().add(new ColorResponse(color));
		});

		product.getProductSizeList().forEach(size -> {
			this.getSize_list().add(new SizeResponse(size));
		});

	}

	public long getProduct_id() {
		return product_id;
	}

	public void setProduct_id(long product_id) {
		this.product_id = product_id;
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

	public boolean isIs_promotion() {
		return is_promotion;
	}

	public void setIs_promotion(boolean is_promotion) {
		this.is_promotion = is_promotion;
	}

	public boolean isIs_popular() {
		return is_popular;
	}

	public void setIs_popular(boolean is_popular) {
		this.is_popular = is_popular;
	}

	public boolean isIs_new_arrival() {
		return is_new_arrival;
	}

	public void setIs_new_arrival(boolean is_new_arrival) {
		this.is_new_arrival = is_new_arrival;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public List<ColorResponse> getColor_list() {
		return color_list;
	}

	public void setColor_list(List<ColorResponse> color_list) {
		this.color_list = color_list;
	}

	public List<SizeResponse> getSize_list() {
		return size_list;
	}

	public void setSize_list(List<SizeResponse> size_list) {
		this.size_list = size_list;
	}

	public String getCode_number() {
		return code_number;
	}

	public void setCode_number(String code_number) {
		this.code_number = code_number;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

}
