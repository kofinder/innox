package com.finder.ecoshop.core.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finder.ecoshop.core.domain.CustomItem;
import com.finder.ecoshop.core.domain.CustomItemLayout;
import com.finder.ecoshop.core.dto.CustomItemLayoutDTO;
import com.finder.ecoshop.core.services.CustomItemLayoutService;
import com.finder.ecoshop.repository.CustomerItemLayoutDao;
import com.finder.ecoshop.utils.CommonConstant;
import com.finder.ecoshop.utils.CommonStatus;
import com.finder.ecoshop.utils.CommonUtil;
import com.finder.ecoshop.utils.ImagesUtil;

@Service
@Transactional
public class CustomItemLayoutServiceImpl implements CustomItemLayoutService {

	private final Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	private CustomerItemLayoutDao customLayoutDao;

	@Override
	public List<CustomItemLayoutDTO> getCustomItemLayoutListByItemId(long customItemId) {
		logger.info("getCustomItemLayoutListByItemId() >> Custom Item Id : " + customItemId);
		List<CustomItemLayout> entityList = customLayoutDao.getCustomItemLayoutListByItemId(customItemId);
		if (entityList == null || entityList.isEmpty()) {
			return new ArrayList<CustomItemLayoutDTO>();
		}

		List<CustomItemLayoutDTO> dtoList = new ArrayList<CustomItemLayoutDTO>();
		entityList.forEach(entity -> {
			CustomItemLayoutDTO dto = new CustomItemLayoutDTO(entity);
			dtoList.add(dto);
		});

		return dtoList;
	}

	@Override
	public CustomItemLayoutDTO manageCustomItemLayout(CustomItemLayoutDTO customItemLayoutDTO) throws Exception {
		// TODO custom item layout manage
		CustomItemLayout layoutEntity = null;

		if (customItemLayoutDTO.getSeq() > 0) {
			// edit
			layoutEntity = customLayoutDao.get(customItemLayoutDTO.getSeq());
			layoutEntity.setStatue(customItemLayoutDTO.getStatus());
		} else {
			// save
			layoutEntity = new CustomItemLayout();
			layoutEntity.setStatue(CommonStatus.ACTIVE.getCode());
			layoutEntity.setCreatedTime(new Date());
		}

		CustomItem customItem = new CustomItem();
		customItem.setSeq(customItemLayoutDTO.getCustomItemDTO().getSeq());
		layoutEntity.setCustomItem(customItem);

		layoutEntity.setUpdatedTime(new Date());
		layoutEntity.setLayoutName(customItemLayoutDTO.getLayoutName());
		layoutEntity.setLayoutPrice(customItemLayoutDTO.getLayoutPrice());

		customLayoutDao.save(layoutEntity);
		logger.info("manageCustomItemLayout() >> Save or Update is successful");

		if (!CommonUtil.isEmpty(customItemLayoutDTO.getLayoutImageFile().getOriginalFilename())) {
			try {
				String oldImageName = layoutEntity.getLayoutImage();
				String imageName = ImagesUtil.uploadMultipartFile(customItemLayoutDTO.getLayoutImageFile(),
						CommonConstant.CUSTOM_LAYOUT_DIRECTORY + CommonConstant.CUSTOM_LAYOUT_IMAGE_DIRECTORY
								+ layoutEntity.getSeq() + "/",
						CommonConstant.CUSTOM_LAYOUT_IMAGE_PERFIX, layoutEntity.getSeq());
				layoutEntity.setLayoutImage(imageName);

				// delete old image path
				if (!CommonUtil.isEmpty(oldImageName)) {
					ImagesUtil.deleteFile(CommonConstant.IMAGE_SAVE_DIRECTORY, oldImageName);
				}

				customLayoutDao.merge(layoutEntity);
				logger.info("manageCustomItemLayout() >> Custom Item Layout image save is successful");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return new CustomItemLayoutDTO(customLayoutDao.get(layoutEntity.getSeq()));
	}

	@Override
	public int isValidLayoutName(long customItemId, String layoutName) {
		return customLayoutDao.isValidLayoutName(customItemId, layoutName);
	}

	@Override
	public CustomItemLayoutDTO getCustomItemLayoutbyId(long customLayoutId) {
		logger.info("getCustomItemLayoutbyId() >> Custom Item Layout Id : " + customLayoutId);
		CustomItemLayout layout = customLayoutDao.get(customLayoutId);
		if (layout == null || layout.getSeq() <= 0) {
			return new CustomItemLayoutDTO();
		}
		return new CustomItemLayoutDTO(layout);
	}

}
