
package com.example.acm.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/** 
 * 讨论实体
 * 
 * @author guanyiting
 * @date 2019-02-05 16:05:40
 */
public class Invitation implements Serializable {

    private Long invitationId;//
    private String invitationTitle;//
    private Object invitationBody;//
    private Integer createUser;//
    private Date createDate;//
    private Integer updateUser;//
    private Date updateDate;//
    private Integer readNum;//
    private Integer agreeNum;//
    private Integer isFirst;//
    private Integer isGreate;//
    private Integer isEffective;//

	public void setInvitationId(Long invitationId) {
		this.invitationId = invitationId;
	}
	public Long getInvitationId() {
		return this.invitationId;
	}
	public void setInvitationTitle(String invitationTitle) {
		this.invitationTitle = invitationTitle;
	}
	public String getInvitationTitle() {
		return this.invitationTitle;
	}
	public void setInvitationBody(Object invitationBody) {
		this.invitationBody = invitationBody;
	}
	public Object getInvitationBody() {
		return this.invitationBody;
	}
	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}
	public Integer getCreateUser() {
		return this.createUser;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getCreateDate() {
		return this.createDate;
	}
	public void setUpdateUser(Integer updateUser) {
		this.updateUser = updateUser;
	}
	public Integer getUpdateUser() {
		return this.updateUser;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public Date getUpdateDate() {
		return this.updateDate;
	}
	public void setReadNum(Integer readNum) {
		this.readNum = readNum;
	}
	public Integer getReadNum() {
		return this.readNum;
	}
	public void setAgreeNum(Integer agreeNum) {
		this.agreeNum = agreeNum;
	}
	public Integer getAgreeNum() {
		return this.agreeNum;
	}
	public void setIsFirst(Integer isFirst) {
		this.isFirst = isFirst;
	}
	public Integer getIsFirst() {
		return this.isFirst;
	}
	public void setIsGreate(Integer isGreate) {
		this.isGreate = isGreate;
	}
	public Integer getIsGreate() {
		return this.isGreate;
	}
	public void setIsEffective(Integer isEffective) {
		this.isEffective = isEffective;
	}
	public Integer getIsEffective() {
		return this.isEffective;
	}
}

