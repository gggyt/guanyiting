
package com.example.acm.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/** 
 * 照片实体
 * 
 * @author guanyiting
 * @date 2019-02-02 14:09:43
 */
public class AlbumPhoto implements Serializable {

    private Integer apId;//
    private Integer albumId;//
    private Long photoId;//
    private Integer createUser;//
    private Date createDate;//
    private Integer isEffective;//

	public void setApId(Integer apId) {
		this.apId = apId;
	}
	public Integer getApId() {
		return this.apId;
	}
	public void setAlbumId(Integer albumId) {
		this.albumId = albumId;
	}
	public Integer getAlbumId() {
		return this.albumId;
	}
	public void setPhotoId(Long photoId) {
		this.photoId = photoId;
	}
	public Long getPhotoId() {
		return this.photoId;
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

