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

import com.finder.innox.core.domain.Artwork;
import com.finder.innox.core.domain.Color;
import com.finder.innox.core.domain.CustomItem;
import com.finder.innox.core.domain.CustomItemLayout;
import com.finder.innox.core.domain.CustomProduct;
import com.finder.innox.core.domain.Fonts;
import com.finder.innox.core.domain.Product;
import com.finder.innox.core.domain.ProductArtwork;
import com.finder.innox.core.domain.ProductFont;
import com.finder.innox.core.domain.ProductLayout;
import com.finder.innox.core.domain.ProductSize;
import com.finder.innox.core.domain.ShoppingCart;
import com.finder.innox.core.domain.Size;
import com.finder.innox.core.domain.User;
import com.finder.innox.core.dto.ShoppingCartDTO;
import com.finder.innox.core.services.ShoppingCartService;
import com.finder.innox.repository.ArtworkDao;
import com.finder.innox.repository.CustomProductDao;
import com.finder.innox.repository.FontsDao;
import com.finder.innox.repository.ProductArtworkDao;
import com.finder.innox.repository.ProductDao;
import com.finder.innox.repository.ProductFontDao;
import com.finder.innox.repository.ProductLayoutDao;
import com.finder.innox.repository.ProductSizeDao;
import com.finder.innox.repository.ShoppingCartDao;
import com.finder.innox.repository.SizeDao;
import com.finder.innox.repository.UserDao;
import com.finder.innox.request.CustomProductRequest;
import com.finder.innox.request.InstockShoppingCartRequest;
import com.finder.innox.request.ProductLayoutRequest;
import com.finder.innox.request.ProductSizeRequest;
import com.finder.innox.utils.CommonConstant;
import com.finder.innox.utils.CommonStatus;
import com.finder.innox.utils.CommonUtil;
import com.finder.innox.utils.ImagesUtil;
import com.finder.innox.utils.ProductTypeEnum;
import com.finder.innox.utils.UserRoleEnum;

@Service
@Transactional
public class ShoppigCartServiceImpl implements ShoppingCartService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ShoppingCartDao cartDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private ProductDao productDao;

	@Autowired
	private ProductLayoutDao productLayoutDao;

	@Autowired
	private FontsDao fontDao;

	@Autowired
	private ArtworkDao artworkDao;

	@Autowired
	private ProductFontDao productFontDao;

	@Autowired
	private ProductArtworkDao productArtworkDao;

	@Autowired
	private ProductSizeDao productSizeDao;

	@Autowired
	private SizeDao sizeDao;

	@Autowired
	private CustomProductDao customProductDao;

	@Autowired
	private ShoppingCartDao shoppingCartDao;

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
	public List<ShoppingCartDTO> updateShoppingCart(InstockShoppingCartRequest updateCartRequest) {
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

		return this.getShoppingCartListByCusId(updateCartRequest.getCustomer_id());
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

	@Override
	public Long customAddToCart(CustomProductRequest customProductRequest, String customerName) throws Exception {
		User user = userDao.findByUserName(customerName, UserRoleEnum.ROLE_USER.getCode());
		BigDecimal finalSellPrice = BigDecimal.ZERO;

		// custom product
		Product product = new Product();
		product.setStatus(CommonStatus.ACTIVE.getCode());
		product.setCreatedBy(user);
		product.setCreatedTime(new Date());
		product.setUpdatedTime(new Date());
		product.setIsCustomProduct(ProductTypeEnum.CUSTOM.getCode());

		product.setPrice(BigDecimal.ZERO);
		product.setOriginalPrice(BigDecimal.ZERO);

		CustomProduct customProduct = customProductDao.get(customProductRequest.getCustom_product_id());
		product.setCustomProduct(customProduct);
		finalSellPrice = finalSellPrice.add(customProduct.getInitialPrice());

		product.setName(customProduct.getProdcutName());
		product.setImagePath1(customProduct.getImagePath());

		CustomItem customItem = new CustomItem();
		customItem.setSeq(customProductRequest.getCustom_item_id());
		product.setCustomProductItem(customItem);

		productDao.save(product);
		logger.info("customAddToCart() >> Product save success");

		// custom product layout
		for (ProductLayoutRequest layout : customProductRequest.getProduct_layout()) {
			ProductLayout productLayout = new ProductLayout();
			productLayout.setProduct(product);

			CustomItemLayout customItemLayout = new CustomItemLayout();
			customItemLayout.setSeq(layout.getProduct_layout_id());
			productLayout.setCustomItemLayout(customItemLayout);

			productLayout.setCreatedTime(new Date());
			productLayout.setUpdatedTime(new Date());

			productLayoutDao.save(productLayout);

			if (!CommonUtil.isEmpty(layout.getCreated_image())) {
				try {
					String imageName = ImagesUtil.uploadBase64File(layout.getCreated_image(),
							CommonConstant.CUSTOM_LAYOUT_DIRECTORY + CommonConstant.CUSTOM_LAYOUT_IMAGE_DIRECTORY
									+ productLayout.getSeq() + "/",
							CommonConstant.CUSTOM_LAYOUT_IMAGE_PERFIX, productLayout.getSeq());
					productLayout.setCreatedDesignImage(imageName);

					productLayoutDao.merge(productLayout);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			// product font
			for (Long fontId : layout.getProduct_fonts()) {

				ProductFont productFont = new ProductFont();
				productFont.setProductLayout(productLayout);

				Fonts font = fontDao.get(fontId);
				productFont.setFonts(font);

				productFont.setPrice(BigDecimal.ZERO);
				productFont.setCreatedTime(new Date());
				productFont.setUpdatedTime(new Date());

				productFontDao.save(productFont);
			}
			logger.info("customAddToCart() >> Product font save success");

			// product artwork
			for (Long artworkId : layout.getProduct_artworks()) {
				ProductArtwork productArtwork = new ProductArtwork();
				productArtwork.setProductLayout(productLayout);

				Artwork artwork = artworkDao.get(artworkId);
				BigDecimal sellPrice = artwork.getSellPrice() == null ? BigDecimal.ZERO : artwork.getSellPrice();

				productArtwork.setArtwork(artwork);
				productArtwork.setPrice(sellPrice);
				finalSellPrice = finalSellPrice.add(sellPrice);

				productArtwork.setCreatedTime(new Date());
				productArtwork.setUpdatedTime(new Date());

				productArtworkDao.save(productArtwork);
			}
			logger.info("customAddToCart() >> Product artwork save success");
		}
		logger.info("customAddToCart() >> Product layout save success");

		// product size
		int totalQty = 0;
		for (ProductSizeRequest prdSize : customProductRequest.getProduct_sizes()) {
			BigDecimal prdPrice = BigDecimal.ZERO;
			ProductSize productSize = new ProductSize();
			productSize.setProduct(product);

			Size size = sizeDao.get(prdSize.getSize_id());
			productSize.setSize(size);
			productSize.setQuantity(prdSize.getQuantity());
			productSize.setCreatedBy(user);
			productSize.setCreatedTime(new Date());
			productSize.setUpdatedTime(new Date());

			productSizeDao.save(productSize);

			prdPrice = finalSellPrice.multiply(new BigDecimal(prdSize.getQuantity()));
			logger.info("customAddToCart() >> Product Size Qty >> " + prdSize.getQuantity());
			product.setPrice(product.getPrice().add(prdPrice));
			product.setOriginalPrice(product.getOriginalPrice().add(prdPrice));

			totalQty = Math.addExact(totalQty, prdSize.getQuantity());
		}
		logger.info("customAddToCart() >> Product size save success");

		// update total price
		productDao.update(product);
		logger.info("customAddToCart() >> End");

		// TODO implement insert shopping cart for custom product
		ShoppingCart shoppingCart = new ShoppingCart();
		shoppingCart.setCustomer(user);
		shoppingCart.setProduct(product);
		shoppingCart.setQuantity(totalQty);
		shoppingCart.setIsCustomProduct(true);
		shoppingCart.setCreatedTime(new Date());
		shoppingCart.setUpdatedTime(new Date());

		shoppingCartDao.save(shoppingCart);
		logger.info("customAddToCart() >> Custom product add to cart successful");

		return product.getSeq();
	}

	@Override
	public List<ShoppingCartDTO> getShoppingCartsForOrderPreload(List<Long> cartIds) {
		List<ShoppingCart> entityList = cartDao.getShoppingCartsForOrderPreload(cartIds);
		if (entityList == null || entityList.isEmpty()) {
			return new ArrayList<ShoppingCartDTO>();
		}

		List<ShoppingCartDTO> dtoList = new ArrayList<ShoppingCartDTO>();
		entityList.forEach(cart -> {
			dtoList.add(new ShoppingCartDTO(cart));
		});
		return dtoList;
	}

}
