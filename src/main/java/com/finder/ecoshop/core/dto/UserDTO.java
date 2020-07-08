package com.finder.ecoshop.core.dto;

import org.springframework.web.multipart.MultipartFile;

public class UserDTO {

	private long id;

	private String userName;

	private String password;

	private String email;

	private int status;

	private String avatar;

	private MultipartFile imageFile;

	private boolean draftFlag;

	private int recordRegSeq;

	private int recordUpdateSeq;

	private String recordRegDate;

	private String recordUpdateDate;

	private boolean recordTmpFlag;

	private boolean recordDelFlag;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public MultipartFile getImageFile() {
		return imageFile;
	}

	public void setImageFile(MultipartFile imageFile) {
		this.imageFile = imageFile;
	}

	public boolean isDraftFlag() {
		return draftFlag;
	}

	public void setDraftFlag(boolean draftFlag) {
		this.draftFlag = draftFlag;
	}

	public int getRecordRegSeq() {
		return recordRegSeq;
	}

	public void setRecordRegSeq(int recordRegSeq) {
		this.recordRegSeq = recordRegSeq;
	}

	public int getRecordUpdateSeq() {
		return recordUpdateSeq;
	}

	public void setRecordUpdateSeq(int recordUpdateSeq) {
		this.recordUpdateSeq = recordUpdateSeq;
	}

	public String getRecordRegDate() {
		return recordRegDate;
	}

	public void setRecordRegDate(String recordRegDate) {
		this.recordRegDate = recordRegDate;
	}

	public String getRecordUpdateDate() {
		return recordUpdateDate;
	}

	public void setRecordUpdateDate(String recordUpdateDate) {
		this.recordUpdateDate = recordUpdateDate;
	}

	public boolean isRecordTmpFlag() {
		return recordTmpFlag;
	}

	public void setRecordTmpFlag(boolean recordTmpFlag) {
		this.recordTmpFlag = recordTmpFlag;
	}

	public boolean isRecordDelFlag() {
		return recordDelFlag;
	}

	public void setRecordDelFlag(boolean recordDelFlag) {
		this.recordDelFlag = recordDelFlag;
	}

}
