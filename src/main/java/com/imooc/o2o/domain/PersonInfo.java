package com.imooc.o2o.domain;

import java.util.Date;

public class PersonInfo {

	private Long userId;
	private String name;
	private String gender;
	private String email;
	private String profileImg;
	@Override
	public String toString() {
		return "PersonInfo [userId=" + userId + ", name=" + name + ", gender=" + gender + ", email=" + email
				+ ", profileImg=" + profileImg + ", createTime=" + createTime + ", password=" + password
				+ ", lastEditTime=" + lastEditTime + ", userType=" + userType + ", enableStatus=" + enableStatus + "]";
	}

	private Date createTime;
	private String password;
	private Date lastEditTime;
	// 1.顾客2.店家3.超级管理员
	private Integer userType;
	private Integer enableStatus;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public String setName(String name) {
		return  name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getProfileImg() {
		return profileImg;
	}

	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public Date getLastEditTime() {
		return lastEditTime;
	}

	public void setLastEditTime(Date lastEditTime) {
		this.lastEditTime = lastEditTime;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Integer getEnableStatus() {
		return enableStatus;
	}

	public void setEnableStatus(Integer enableStatus) {
		this.enableStatus = enableStatus;
	}

}
