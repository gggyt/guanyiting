
package com.example.acm.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/** 
 * 相册实体
 * 
 * @author guanyiting
 * @date 2019-01-29 21:50:36
 */
public class Album implements Serializable {

    private Integer albumId;//
    private String albumName;//
    private Integer createUser;//
    private Date createDate;//
    private Long coverPhotoId;//
    private String description;//
    private Integer isPublic;//
    private Integer isEffective;//

	public void setAlbumId(Integer albumId) {
		this.albumId = albumId;
	}
	public Integer getAlbumId() {
		return this.albumId;
	}
	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}
	public String getAlbumName() {
		return this.albumName;
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
	public void setCoverPhotoId(Long coverPhotoId) {
		this.coverPhotoId = coverPhotoId;
	}
	public Long getCoverPhotoId() {
		return this.coverPhotoId;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDescription() {
		return this.description;
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
}

