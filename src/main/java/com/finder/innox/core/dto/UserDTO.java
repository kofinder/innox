	package com.finder.innox.core.dto;

import org.springframework.web.multipart.MultipartFile;

import com.finder.innox.core.domain.User;
import com.finder.innox.utils.CommonStatus;
import com.finder.innox.utils.DeviceTypeEnum;
import com.finder.innox.utils.UserRoleEnum;

public class UserDTO {

	private long seq;

	private String userName;

	private String password;

	private String email;

	private String phoneNo;

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

	private int userRoleLevel;

	private String deviceToken;

	private int deviceType;

	public UserDTO() {
		super();
	}

	public UserDTO(User user) {
		if (user != null) {
			this.seq = user.getUserSeq();
			this.userName = user.getUserName();
			this.password = user.getPassword();
			this.email = user.getEmail();
			this.phoneNo = user.getPhoneNo();
			this.status = user.getStatus() == null ? CommonStatus.ACTIVE.getCode() : user.getStatus();
			this.avatar = user.getAvatar();
			this.draftFlag = user.getDraftFlag() == null ? false : user.getDraftFlag();
			this.recordRegSeq = user.getRecordRegSeq() == null ? 1 : user.getRecordRegSeq();
			this.recordUpdateSeq = user.getRecordUpdateSeq() == null ? 1 : user.getRecordUpdateSeq();
			this.recordTmpFlag = user.getRecordTmpFlag() == null ? false : user.getRecordTmpFlag();
			this.recordDelFlag = user.getRecordDelFlag() == null ? false : user.getRecordDelFlag();
			this.userRoleLevel = user.getUserRoleLevel() == null ? UserRoleEnum.ROLE_USER.getCode()
					: user.getUserRoleLevel();
			this.deviceToken = user.getDeviceToken();
			this.deviceType = user.getDeviceType() == null ? DeviceTypeEnum.ANDROID.getCode() : user.getDeviceType();
		}
	}

	public long getSeq() {
		return seq;
	}

	public void setSeq(long seq) {
		this.seq = seq;
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

	public int getUserRoleLevel() {
		return userRoleLevel;
	}

	public void setUserRoleLevel(int userRoleLevel) {
		this.userRoleLevel = userRoleLevel;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getDeviceToken() {
		return deviceToken;
	}

	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}

	public int getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(int deviceType) {
		this.deviceType = deviceType;
	}

}
