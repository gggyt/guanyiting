
package com.example.acm.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/** 
 * 讲座实体
 * 
 * @author guanyiting
 * @date 2019-03-11 22:22:49
 */
public class Lecture implements Serializable {

    private Long lectureId;//
    private String lectureTitle;//
    private Object lectureBody;//
    private Integer createUser;//
    private Date createDate;//
    private Integer updateUser;//
    private Date updateDate;//
    private Integer isDone;//1 没结束 0结束
    private Integer isFirst;//
    private Integer isEffective;//

	public void setLectureId(Long lectureId) {
		this.lectureId = lectureId;
	}
	public Long getLectureId() {
		return this.lectureId;
	}
	public void setLectureTitle(String lectureTitle) {
		this.lectureTitle = lectureTitle;
	}
	public String getLectureTitle() {
		return this.lectureTitle;
	}
	public void setLectureBody(Object lectureBody) {
		this.lectureBody = lectureBody;
	}
	public Object getLectureBody() {
		return this.lectureBody;
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
	public void setIsDone(Integer isDone) {
		this.isDone = isDone;
	}
	public Integer getIsDone() {
		return this.isDone;
	}
	public void setIsFirst(Integer isFirst) {
		this.isFirst = isFirst;
	}
	public Integer getIsFirst() {
		return this.isFirst;
	}
	public void setIsEffective(Integer isEffective) {
		this.isEffective = isEffective;
	}
	public Integer getIsEffective() {
		return this.isEffective;
	}
}

