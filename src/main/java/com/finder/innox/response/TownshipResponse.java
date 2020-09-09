package com.finder.innox.response;

import java.io.Serializable;

import com.finder.innox.core.dto.TownshipDTO;

public class TownshipResponse implements Serializable {

	private static final long serialVersionUID = 6601905724062848257L;

	private Long township_id;

	private String township_name;

	public TownshipResponse() {
		super();
	}

	public TownshipResponse(TownshipDTO township) {
		if (township != null) {
			this.township_id = township.getSeq();
			this.township_name = township.getTownshipName();
		}
	}

	public Long getTownship_id() {
		return township_id;
	}

	public void setTownship_id(Long township_id) {
		this.township_id = township_id;
	}

	public String getTownship_name() {
		return township_name;
	}

	public void setTownship_name(String township_name) {
		this.township_name = township_name;
	}

}
