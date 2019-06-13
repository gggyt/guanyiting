
package com.example.acm.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/** 
 * 每日一题实体
 * 
 * @author guanyiting
 * @date 2019-04-15 22:12:06
 */
public class Problem implements Serializable {

    private Long problemId;//
    private String problemTitle;//
    private Object problemBody;//
    private Object myAns;//
    private Integer createUser;//
    private Date createDate;//
    private Date updateDate;//
    private Integer updateUser;//
    private Integer readNum;//
    private Integer isEffective;//

	public void setProblemId(Long problemId) {
		this.problemId = problemId;
	}
	public Long getProblemId() {
		return this.problemId;
	}
	public void setProblemTitle(String problemTitle) {
		this.problemTitle = problemTitle;
	}
	public String getProblemTitle() {
		return this.problemTitle;
	}
	public void setProblemBody(Object problemBody) {
		this.problemBody = problemBody;
	}
	public Object getProblemBody() {
		return this.problemBody;
	}
	public void setMyAns(Object myAns) {
		this.myAns = myAns;
	}
	public Object getMyAns() {
		return this.myAns;
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
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public Date getUpdateDate() {
		return this.updateDate;
	}
	public void setUpdateUser(Integer updateUser) {
		this.updateUser = updateUser;
	}
	public Integer getUpdateUser() {
		return this.updateUser;
	}
	public void setReadNum(Integer readNum) {
		this.readNum = readNum;
	}
	public Integer getReadNum() {
		return this.readNum;
	}
	public void setIsEffective(Integer isEffective) {
		this.isEffective = isEffective;
	}
	public Integer getIsEffective() {
		return this.isEffective;
	}
}

