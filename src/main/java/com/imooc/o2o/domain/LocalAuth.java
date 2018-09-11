package com.imooc.o2o.domain;

import java.util.Date;

/**
 * 本地账号
 * @author 优雅而痞（Xiexiang)
 *
 */
public class LocalAuth {
	private Long localId;
	private String username;
	private String password;
	private Date creatTime;
	private Date lastEditTime;
	private PersonInfo personInfo;
	public Long getLocalId() {
		return localId;
	}
	public void setLocalId(Long localId) {
		this.localId = localId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getCreatTime() {
		return creatTime;
	}
	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}
	public Date getLastEditTime() {
		return lastEditTime;
	}
	public void setLastEditTime(Date lastEditTime) {
		this.lastEditTime = lastEditTime;
	}
	public PersonInfo getPersonInfo() {
		return personInfo;
	}
	public void setPersonInfo(PersonInfo personInfo) {
		this.personInfo = personInfo;
	}
	@Override
	public String toString() {
		return "LocalAuth [localId=" + localId + ", username=" + username + ", password=" + password + ", creatTime="
				+ creatTime + ", lastEditTime=" + lastEditTime + ", personInfo=" + personInfo + "]";
	}
	public LocalAuth(Long localId, String username, String password, Date creatTime, Date lastEditTime,
			PersonInfo personInfo) {
		super();
		this.localId = localId;
		this.username = username;
		this.password = password;
		this.creatTime = creatTime;
		this.lastEditTime = lastEditTime;
		this.personInfo = personInfo;
	}
	public LocalAuth() {
		super();
	}
	

}
