
package com.example.acm.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/** 
 * 评论实体
 * 
 * @author guanyiting
 * @date 2019-02-09 16:32:52
 */
public class Comment implements Serializable {

    private Long commentId;//
    private Long invitationId;//
    private Long pCommentid;//
    private Object commentBody;//
    private Integer createUser;//
    private Date createDate;//
    private Integer isEffective;//

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}
	public Long getCommentId() {
		return this.commentId;
	}
	public void setInvitationId(Long invitationId) {
		this.invitationId = invitationId;
	}
	public Long getInvitationId() {
		return this.invitationId;
	}
	public void setPCommentid(Long pCommentid) {
		this.pCommentid = pCommentid;
	}
	public Long getPCommentid() {
		return this.pCommentid;
	}
	public void setCommentBody(Object commentBody) {
		this.commentBody = commentBody;
	}
	public Object getCommentBody() {
		return this.commentBody;
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

