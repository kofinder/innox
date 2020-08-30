package com.finder.innox.request;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.finder.innox.core.dto.ArtworkDTO;
import com.finder.innox.core.dto.UserDTO;

public class ArtworkUploadRequest implements Serializable {

	private static final long serialVersionUID = 113939625124760160L;

	private Long designer_id;

	private String artwork_name;

	private BigDecimal original_price;

	private String artwork_image;

	private List<Long> artwork_templates;

	public static ArtworkDTO convertRequestToDTO(ArtworkUploadRequest request) {
		ArtworkDTO dto = new ArtworkDTO();
		if (request != null) {
			UserDTO designerDTO = new UserDTO();
			designerDTO.setSeq(request.getDesigner_id());
			dto.setDesignerDTO(designerDTO);

			dto.setArtworkName(request.getArtwork_name());
			dto.setOriginalPrice(request.getOriginal_price());
			dto.setSellPrice(request.getOriginal_price());
			dto.setArtworkImage(request.getArtwork_image());

			dto.getArtworkTemplateIdList().addAll(request.getArtwork_templates());
		}
		return dto;
	}

	public Long getDesigner_id() {
		return designer_id;
	}

	public void setDesigner_id(Long designer_id) {
		this.designer_id = designer_id;
	}

	public String getArtwork_name() {
		return artwork_name;
	}

	public void setArtwork_name(String artwork_name) {
		this.artwork_name = artwork_name;
	}

	public BigDecimal getOriginal_price() {
		return original_price;
	}

	public void setOriginal_price(BigDecimal original_price) {
		this.original_price = original_price;
	}

	public List<Long> getArtwork_templates() {
		return artwork_templates;
	}

	public void setArtwork_templates(List<Long> artwork_templates) {
		this.artwork_templates = artwork_templates;
	}

	public String getArtwork_image() {
		return artwork_image;
	}

	public void setArtwork_image(String artwork_image) {
		this.artwork_image = artwork_image;
	}

}
