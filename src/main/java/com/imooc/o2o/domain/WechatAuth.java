package com.imooc.o2o.domain;

import java.util.Date;

/**
 * 微信账号
 * @author 优雅而痞（Xiexiang)
 *
 */
public class WechatAuth {
	private Long weChatId;
	private String openId;
	private Date creatTime;
	private PersonInfo personInfo;
	
	public Long getWeChatId() {
		return weChatId;
	}
	public void setWeChatId(Long weChatId) {
		this.weChatId = weChatId;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public Date getCreatTime() {
		return creatTime;
	}
	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}
	public PersonInfo getPersonInfo() {
		return personInfo;
	}
	public void setPersonInfo(PersonInfo personInfo) {
		this.personInfo = personInfo;
	}
	public WechatAuth(Long weChatId, String openId, Date creatTime, PersonInfo personInfo) {
		super();
		this.weChatId = weChatId;
		this.openId = openId;
		this.creatTime = creatTime;
		this.personInfo = personInfo;
	}
	public WechatAuth() {
		super();
	}
	@Override
	public String toString() {
		return "WechatAuth [weChatId=" + weChatId + ", openId=" + openId + ", creatTime=" + creatTime + ", personInfo="
				+ personInfo + "]";
	}

}
