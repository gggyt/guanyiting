
package com.example.acm.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import sun.rmi.runtime.Log;

/** 
 * 照片实体
 * 
 * @author guanyiting
 * @date 2019-02-02 13:41:21
 */
public class Photo implements Serializable {

    private Long photoId;//
    private String photoName;//
    private Integer createUser;//
    private Date createDate;//
    private Integer isPublic;//
    private Integer isEffective;//
    private String photoUrl;//

	public void setPhotoId(Long photoId) {
		this.photoId = photoId;
	}
	public Long getPhotoId() {
		return this.photoId;
	}
	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}
	public String getPhotoName() {
		return this.photoName;
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
	public void setIsPublic(Integer isPublic) {
		this.isPublic = isPublic;
	}
	public Integer getIsPublic() {
		return this.isPublic;
	}
	public void setIsEffective(Integer isEffective) {
		this.isEffective = isEffective;
	}
	public Integer getIsEffective() {
		return this.isEffective;
	}
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	public String getPhotoUrl() {
		return this.photoUrl;
	}
}

