
package com.example.acm.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/** 
 * 讲座-添加实体
 * 
 * @author guanyiting
 * @date 2019-03-23 16:18:31
 */
public class Applylecture implements Serializable {

    private Long applyLectureId;//
    private Long lectureId;//
    private Integer joinUser;//
    private Date createDate;//
    private Integer isEffective;//

	public void setApplyLectureId(Long applyLectureId) {
		this.applyLectureId = applyLectureId;
	}
	public Long getApplyLectureId() {
		return this.applyLectureId;
	}
	public void setLectureId(Long lectureId) {
		this.lectureId = lectureId;
	}
	public Long getLectureId() {
		return this.lectureId;
	}
	public void setJoinUser(Integer joinUser) {
		this.joinUser = joinUser;
	}
	public Integer getJoinUser() {
		return this.joinUser;
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

