package com.finder.innox.core.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.finder.innox.core.domain.Product;
import com.finder.innox.utils.CommonConstant;
import com.finder.innox.utils.CommonStatus;
import com.finder.innox.utils.CommonUtil;
import com.finder.innox.utils.ProductTypeEnum;

public class ProductDTO implements Serializable {

	private static final long serialVersionUID = -2046303784632633483L;

	private long seq;

	private BrandDTO brandDTO;

	private CategoryDTO categoryDTO;

	private SubCategoryDTO subCategoryDTO;

	private String name;

	private String codeNumber;

	private BigDecimal price;

	private BigDecimal originalPrice;

	private double discountPercent;

	private int quantity;

	private boolean promotion;

	private boolean newArrival;

	private boolean popular;

	private int status;

	private String overview;

	private String detail;

	private MultipartFile imageFile1;

	private String imagePath1;

	private MultipartFile imageFile2;

	private String imagePath2;

	private MultipartFile imageFile3;

	private String imagePath3;

	private MultipartFile imageFile4;

	private String imagePath4;

	private List<ProductImageDTO> productImageDTOs = new ArrayList<ProductImageDTO>();

	private String priceDesc;

	private String size;

	private String color;

	private List<ColorDTO> productColorList = new ArrayList<ColorDTO>();

	private List<SizeDTO> productSizeList = new ArrayList<SizeDTO>();

	private List<Long> prdColorList = new ArrayList<Long>();

	private List<Long> prdSizeList = new ArrayList<Long>();

	private int isCustomProduct;

	private CustomProductDTO customProductDTO;

	private CustomItemDTO customItemDTO;

	public ProductDTO() {
		super();
	}

	public ProductDTO(Product product) {
		if (product != null) {
			this.seq = product.getSeq();
			this.brandDTO = new BrandDTO(product.getBrand());
			this.categoryDTO = new CategoryDTO(product.getCategory());
			this.subCategoryDTO = new SubCategoryDTO(product.getSubCategory());
			this.name = product.getName();
			this.codeNumber = product.getCodeNumber();
			this.price = product.getPrice() == null ? BigDecimal.ZERO : product.getPrice();
			this.originalPrice = product.getOriginalPrice() == null ? BigDecimal.ZERO : product.getOriginalPrice();
			this.discountPercent = product.getDiscountPercent() == null ? 0.00 : product.getDiscountPercent();
			this.quantity = product.getQuantity() == null ? 0 : product.getQuantity();
			this.promotion = product.getIsPromotion() == null ? false : product.getIsPromotion();
			this.newArrival = product.getIsNewArrival() == null ? false : product.getIsNewArrival();
			this.popular = product.getIsPopular() == null ? false : product.getIsPopular();
			this.status = product.getStatus() == null ? CommonStatus.INACTIVE.getCode() : product.getStatus();
			this.overview = product.getOverview();
			this.detail = product.getDetail();
			this.priceDesc = CommonUtil.formatBigDecimalAsCurrency(this.price, CommonConstant.CURRENCY_CODE_KS);
			this.imagePath1 = product.getImagePath1();
			this.imagePath2 = product.getImagePath2();
			this.imagePath3 = product.getImagePath3();
			this.imagePath4 = product.getImagePath4();
			this.isCustomProduct = product.getIsCustomProduct() == null ? ProductTypeEnum.INSTOCK.getCode()
					: product.getIsCustomProduct();
			this.customProductDTO = new CustomProductDTO(product.getCustomProduct());
			this.customItemDTO = new CustomItemDTO(product.getCustomProductItem());

			if (product.getProductColorList() != null) {
				product.getProductColorList().forEach(color -> {
					prdColorList.add(color.getColor().getSeq());
					productColorList.add(new ColorDTO(color.getColor()));
				});
			}

			if (product.getProductSizeList() != null) {
				product.getProductSizeList().forEach(size -> {
					prdSizeList.add(size.getSize().getSeq());
					productSizeList.add(new SizeDTO(size.getSize()));
				});
			}

		}
	}

	public long getSeq() {
		return seq;
	}

	public void setSeq(long seq) {
		this.seq = seq;
	}

	public BrandDTO getBrandDTO() {
		return brandDTO;
	}

	public void setBrandDTO(BrandDTO brandDTO) {
		this.brandDTO = brandDTO;
	}

	public CategoryDTO getCategoryDTO() {
		return categoryDTO;
	}

	public void setCategoryDTO(CategoryDTO categoryDTO) {
		this.categoryDTO = categoryDTO;
	}

	public SubCategoryDTO getSubCategoryDTO() {
		return subCategoryDTO;
	}

	public void setSubCategoryDTO(SubCategoryDTO subCategoryDTO) {
		this.subCategoryDTO = subCategoryDTO;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCodeNumber() {
		return codeNumber;
	}

	public void setCodeNumber(String codeNumber) {
		this.codeNumber = codeNumber;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(BigDecimal originalPrice) {
		this.originalPrice = originalPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public boolean isPromotion() {
		return promotion;
	}

	public void setPromotion(boolean promotion) {
		this.promotion = promotion;
	}

	public boolean isNewArrival() {
		return newArrival;
	}

	public void setNewArrival(boolean newArrival) {
		this.newArrival = newArrival;
	}

	public boolean isPopular() {
		return popular;
	}

	public void setPopular(boolean popular) {
		this.popular = popular;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public double getDiscountPercent() {
		return discountPercent;
	}

	public void setDiscountPercent(double discountPercent) {
		this.discountPercent = discountPercent;
	}

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public MultipartFile getImageFile1() {
		return imageFile1;
	}

	public void setImageFile1(MultipartFile imageFile1) {
		this.imageFile1 = imageFile1;
	}

	public String getImagePath1() {
		return imagePath1;
	}

	public void setImagePath1(String imagePath1) {
		this.imagePath1 = imagePath1;
	}

	public MultipartFile getImageFile2() {
		return imageFile2;
	}

	public void setImageFile2(MultipartFile imageFile2) {
		this.imageFile2 = imageFile2;
	}

	public String getImagePath2() {
		return imagePath2;
	}

	public void setImagePath2(String imagePath2) {
		this.imagePath2 = imagePath2;
	}

	public MultipartFile getImageFile3() {
		return imageFile3;
	}

	public void setImageFile3(MultipartFile imageFile3) {
		this.imageFile3 = imageFile3;
	}

	public String getImagePath3() {
		return imagePath3;
	}

	public void setImagePath3(String imagePath3) {
		this.imagePath3 = imagePath3;
	}

	public MultipartFile getImageFile4() {
		return imageFile4;
	}

	public void setImageFile4(MultipartFile imageFile4) {
		this.imageFile4 = imageFile4;
	}

	public String getImagePath4() {
		return imagePath4;
	}

	public void setImagePath4(String imagePath4) {
		this.imagePath4 = imagePath4;
	}

	public List<ProductImageDTO> getProductImageDTOs() {
		return productImageDTOs;
	}

	public void setProductImageDTOs(List<ProductImageDTO> productImageDTOs) {
		this.productImageDTOs = productImageDTOs;
	}

	public String getPriceDesc() {
		return priceDesc;
	}

	public void setPriceDesc(String priceDesc) {
		this.priceDesc = priceDesc;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public List<ColorDTO> getProductColorList() {
		return productColorList;
	}

	public void setProductColorList(List<ColorDTO> productColorList) {
		this.productColorList = productColorList;
	}

	public List<SizeDTO> getProductSizeList() {
		return productSizeList;
	}

	public void setProductSizeList(List<SizeDTO> productSizeList) {
		this.productSizeList = productSizeList;
	}

	public List<Long> getPrdColorList() {
		return prdColorList;
	}

	public void setPrdColorList(List<Long> prdColorList) {
		this.prdColorList = prdColorList;
	}

	public List<Long> getPrdSizeList() {
		return prdSizeList;
	}

	public void setPrdSizeList(List<Long> prdSizeList) {
		this.prdSizeList = prdSizeList;
	}

	public int getIsCustomProduct() {
		return isCustomProduct;
	}

	public void setIsCustomProduct(int isCustomProduct) {
		this.isCustomProduct = isCustomProduct;
	}

	public CustomProductDTO getCustomProductDTO() {
		return customProductDTO;
	}

	public void setCustomProductDTO(CustomProductDTO customProductDTO) {
		this.customProductDTO = customProductDTO;
	}

	public CustomItemDTO getCustomItemDTO() {
		return customItemDTO;
	}

	public void setCustomItemDTO(CustomItemDTO customItemDTO) {
		this.customItemDTO = customItemDTO;
	}

}
