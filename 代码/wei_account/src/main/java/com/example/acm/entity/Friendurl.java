
package com.example.acm.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/** 
 * 友链实体
 * 
 * @author guanyiting
 * @date 2019-03-24 16:00:57
 */
public class Friendurl implements Serializable {

    private Long friendurlId;//
    private Object friendTitle;//
    private Object friendBody;//
    private Long createUser;//
    private Date createDate;//
    private Long updateUser;//
    private Date updateDate;//
    private Integer isEffective;//

	public void setFriendurlId(Long friendurlId) {
		this.friendurlId = friendurlId;
	}
	public Long getFriendurlId() {
		return this.friendurlId;
	}
	public void setFriendTitle(Object friendTitle) {
		this.friendTitle = friendTitle;
	}
	public Object getFriendTitle() {
		return this.friendTitle;
	}
	public void setFriendBody(Object friendBody) {
		this.friendBody = friendBody;
	}
	public Object getFriendBody() {
		return this.friendBody;
	}
	public void setCreateUser(Long createUser) {
		this.createUser = createUser;
	}
	public Long getCreateUser() {
		return this.createUser;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getCreateDate() {
		return this.createDate;
	}
	public void setUpdateUser(Long updateUser) {
		this.updateUser = updateUser;
	}
	public Long getUpdateUser() {
		return this.updateUser;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public Date getUpdateDate() {
		return this.updateDate;
	}
	public void setIsEffective(Integer isEffective) {
		this.isEffective = isEffective;
	}
	public Integer getIsEffective() {
		return this.isEffective;
	}
}

