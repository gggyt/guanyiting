
package com.example.acm.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/** 
 * 每日一题题解实体
 * 
 * @author guanyiting
 * @date 2019-04-16 11:15:34
 */
public class Replyproblem implements Serializable {

    private Long problemansId;//
    private Long problemId;//
    private Long pProblemans;//
    private Integer ansUser;//
    private Object ansBody;//
    private Date createDate;//
    private Integer agreeNum;//
    private Integer isEffective;//

	public void setProblemansId(Long problemansId) {
		this.problemansId = problemansId;
	}
	public Long getProblemansId() {
		return this.problemansId;
	}
	public void setProblemId(Long problemId) {
		this.problemId = problemId;
	}
	public Long getProblemId() {
		return this.problemId;
	}
	public void setPProblemans(Long pProblemans) {
		this.pProblemans = pProblemans;
	}
	public Long getPProblemans() {
		return this.pProblemans;
	}
	public void setAnsUser(Integer ansUser) {
		this.ansUser = ansUser;
	}
	public Integer getAnsUser() {
		return this.ansUser;
	}
	public void setAnsBody(Object ansBody) {
		this.ansBody = ansBody;
	}
	public Object getAnsBody() {
		return this.ansBody;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getCreateDate() {
		return this.createDate;
	}
	public void setAgreeNum(Integer agreeNum) {
		this.agreeNum = agreeNum;
	}
	public Integer getAgreeNum() {
		return this.agreeNum;
	}
	public void setIsEffective(Integer isEffective) {
		this.isEffective = isEffective;
	}
	public Integer getIsEffective() {
		return this.isEffective;
	}
}

