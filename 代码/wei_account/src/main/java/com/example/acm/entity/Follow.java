
package com.example.acm.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/** 
 * 关注表实体
 * 
 * @author guanyiting
 * @date 2019-05-25 19:25:31
 */
public class Follow implements Serializable {

    private Long followId;//
    private Integer beUserId;//
    private Integer createUser;//
    private Date createDate;//
    private Integer isEffective;//

	public void setFollowId(Long followId) {
		this.followId = followId;
	}
	public Long getFollowId() {
		return this.followId;
	}
	public void setBeUserId(Integer beUserId) {
		this.beUserId = beUserId;
	}
	public Integer getBeUserId() {
		return this.beUserId;
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
	public void setIsEffective(Integer isEffective) {
		this.isEffective = isEffective;
	}
	public Integer getIsEffective() {
		return this.isEffective;
	}
}

