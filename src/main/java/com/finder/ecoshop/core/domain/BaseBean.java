package com.finder.ecoshop.core.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class BaseBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long draftflg;
	private Byte recordTmpFlg;
	private Byte recordDelFlg;

	private Long recordRegSeq;
	private Long recordUpdSeq;
	private Timestamp recordUpdDate;
	private Timestamp recordRegDate;

	public Long getDraftflg() {
		return draftflg;
	}

	public void setDraftflg(Long draftflg) {
		this.draftflg = draftflg;
	}

	public Byte getRecordTmpFlg() {
		return recordTmpFlg;
	}

	public void setRecordTmpFlg(Byte recordTmpFlg) {
		this.recordTmpFlg = recordTmpFlg;
	}

	public Byte getRecordDelFlg() {
		return recordDelFlg;
	}

	public void setRecordDelFlg(Byte recordDelFlg) {
		this.recordDelFlg = recordDelFlg;
	}

	public Long getRecordRegSeq() {
		return recordRegSeq;
	}

	public void setRecordRegSeq(Long recordRegSeq) {
		this.recordRegSeq = recordRegSeq;
	}

	public Long getRecordUpdSeq() {
		return recordUpdSeq;
	}

	public void setRecordUpdSeq(Long recordUpdSeq) {
		this.recordUpdSeq = recordUpdSeq;
	}

	public Timestamp getRecordUpdDate() {
		return recordUpdDate;
	}

	public void setRecordUpdDate(Timestamp recordUpdDate) {
		this.recordUpdDate = recordUpdDate;
	}

	public Timestamp getRecordRegDate() {
		return recordRegDate;
	}

	public void setRecordRegDate(Timestamp recordRegDate) {
		this.recordRegDate = recordRegDate;
	}

	/*
	 * @convert registration date format to @String from @Timestamp
	 * 
	 * @return date string with format
	 */
	public final String getRecordAtYMDHM() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		return recordRegDate != null ? sdf.format(recordRegDate) : null;
	}

	/*
	 * @convert update date format to @String from @Timestamp
	 * 
	 * @return date string with format
	 */
	public final String getRecordUpdAtYMDHM() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		return recordUpdDate != null ? sdf.format(recordUpdDate) : null;
	}

}
