package com.finder.ecoshop.core.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finder.ecoshop.core.domain.Color;
import com.finder.ecoshop.core.domain.CustomItem;
import com.finder.ecoshop.core.domain.CustomProduct;
import com.finder.ecoshop.core.dto.CustomItemDTO;
import com.finder.ecoshop.core.services.CustomItemService;
import com.finder.ecoshop.repository.CustomItemDao;

@Service
@Transactional
public class CustomItemServiceImpl implements CustomItemService {

	private final Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	private CustomItemDao customItemDao;

	@Override
	public List<CustomItemDTO> getCustomItemListByCustomProductId(long id) {
		List<CustomItem> entityList = customItemDao.getCustomItemListByCustomProductId(id);
		if (entityList == null || entityList.isEmpty()) {
			return new ArrayList<CustomItemDTO>();
		}

		List<CustomItemDTO> dtoList = new ArrayList<CustomItemDTO>();
		entityList.forEach(entity -> {
			dtoList.add(new CustomItemDTO(entity));
		});
		return dtoList;
	}

	@Override
	public CustomItemDTO customItemManage(CustomItemDTO customItemDTO) throws Exception {
		logger.info("customItemManage() >> Start");
		CustomItem customItem = null;
		if (customItemDTO.getSeq() <= 0) {
			// save
			customItem = new CustomItem();
			customItem.setCreatedTime(new Date());
		} else {
			// update
			customItem = customItemDao.get(customItemDTO.getSeq());
			customItem.setUpdatedTime(new Date());
		}

		CustomProduct customProduct = new CustomProduct();
		customProduct.setSeq(customItemDTO.getCustomProductDTO().getSeq());
		customItem.setCustomProduct(customProduct);

		customItem.setItemCode(customItemDTO.getItemCode());
		customItem.setItemName(customItemDTO.getItemName());
		customItem.setItemPrice(customItemDTO.getItemPrice());
		customItem.setSequenceNo(customItemDTO.getSequenceNo());

		Color color = new Color();
		color.setSeq(customItemDTO.getColorDTO().getSeq());
		customItem.setColor(color);

		customItemDao.saveOrUpdate(customItem);
		logger.info("customItemManage() >> End >> Custom Item Id : " + customItem.getSeq());
		return new CustomItemDTO(customItem);
	}

	@Override
	public CustomItemDTO getCustomItemById(long customItemId) {
		logger.info("getCustomItemById() >> Start >> Custom Item Id : " + customItemId);
		CustomItem customItem = customItemDao.get(customItemId);
		if (customItem == null) {
			return null;
		}
		return new CustomItemDTO(customItem);
	}

}
