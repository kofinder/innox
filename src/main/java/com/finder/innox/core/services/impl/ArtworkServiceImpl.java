package com.finder.innox.core.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finder.innox.core.domain.Artwork;
import com.finder.innox.core.domain.ArtworkCategory;
import com.finder.innox.core.domain.ArtworkTag;
import com.finder.innox.core.domain.User;
import com.finder.innox.core.dto.ArtworkDTO;
import com.finder.innox.core.services.ArtworkService;
import com.finder.innox.repository.ArtworkDao;
import com.finder.innox.repository.ArtworkTagDao;
import com.finder.innox.utils.CommonConstant;
import com.finder.innox.utils.CommonStatus;
import com.finder.innox.utils.CommonUtil;
import com.finder.innox.utils.ImagesUtil;

@Service
@Transactional
public class ArtworkServiceImpl implements ArtworkService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ArtworkDao artworkDao;

	@Autowired
	private ArtworkTagDao artworkTagDao;

	@Override
	public ArtworkDTO uploadArtwork(ArtworkDTO artworkDTO) {
		Artwork artwork = new Artwork();

		User user = new User();
		user.setUserSeq(artworkDTO.getDesignerDTO().getSeq());
		artwork.setDesigner(user);

		artwork.setArtworkName(artworkDTO.getArtworkName());
		artwork.setOriginalPrice(artworkDTO.getOriginalPrice());
		artwork.setSellPrice(artworkDTO.getSellPrice());
		artwork.setStatus(CommonStatus.ACTIVE.getCode());

		artwork.setCreatedTime(new Date());
		artwork.setUpdatedTime(new Date());

		artworkDao.saveOrUpdate(artwork);

		if (!CommonUtil.isEmpty(artworkDTO.getArtworkImage())) {
			try {
				String imageName = ImagesUtil.uploadBase64File(
						artworkDTO.getArtworkImage(), CommonConstant.ARTWORK_DIRECTORY
								+ CommonConstant.ARTWORK_IMAGE_DIRECTORY + artwork.getSeq() + "/",
						CommonConstant.ARTWORK_IMAGE_PERFIX, artwork.getSeq());
				artwork.setArtworkImage(imageName);

				artworkDao.merge(artwork);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (artworkDTO.getArtworkTemplateIdList() != null && artworkDTO.getArtworkTemplateIdList().size() > 0) {
			artworkDTO.getArtworkTemplateIdList().forEach(art -> {
				ArtworkTag artworkTag = new ArtworkTag();
				artworkTag.setArtwork(artwork);

				ArtworkCategory artworkCategory = new ArtworkCategory();
				artworkCategory.setSeq(art);
				artworkTag.setArtworkCategory(artworkCategory);

				artworkTag.setStatus(CommonStatus.ACTIVE.getCode());
				artworkTag.setCreatedTime(new Date());
				artworkTag.setUpdatedTime(new Date());

				artworkTagDao.saveOrUpdate(artworkTag);
			});
		}

		return new ArtworkDTO(artworkDao.get(artwork.getSeq()));
	}

	@Override
	public List<ArtworkDTO> getArtworkListByCategory(long artworkCategoryId) {
		logger.info("getArtworkListByCategory() >> Start >> Artwork Categroy Id : " + artworkCategoryId);
		List<ArtworkDTO> dtoList = artworkDao.getArtworkByCategroyId(artworkCategoryId);
		if (dtoList == null || dtoList.isEmpty()) {
			return new ArrayList<ArtworkDTO>();
		}
		logger.info("getArtworkListByCategory() >> End >> Artwork List : " + dtoList.size());
		return dtoList;
	}

	@Override
	public List<ArtworkDTO> getArtworkListByDesigner(long designerId) {
		logger.info("getArtworkListByDesigner() >> Start >> Designer Id : " + designerId);

		List<Artwork> entityList = artworkDao.getArtworkListByDesigner(designerId);
		if (entityList == null || entityList.isEmpty()) {
			return new ArrayList<ArtworkDTO>();
		}

		List<ArtworkDTO> dtoList = new ArrayList<ArtworkDTO>();
		entityList.forEach(entity -> {
			ArtworkDTO artworkDTO = new ArtworkDTO(entity);
			dtoList.add(artworkDTO);
		});
		logger.info("getArtworkListByDesigner() >> End >> Artwork List : " + dtoList.size());
		return dtoList;
	}

}
