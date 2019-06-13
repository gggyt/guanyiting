
package com.example.acm.service;

import java.util.List;
import java.util.Map;

import com.example.acm.entity.Comment;
import org.apache.ibatis.annotations.Param;

/** 
 * 评论服务接口
 *
 * @author guanyiting
 * @date 2019-02-09 16:14:22
 */
public interface CommentService{
    
    /** 
     * 添加评论
     * 
     * @param comment 评论
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-09 16:14:22
     */
    public void addComment(Comment comment);
    
    /** 
     * 添加评论列表
     * 
     * @param list 评论列表
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-09 16:14:22
     */
    public void addCommentList(List<Comment> list);      
    
    /** 
     * 删除评论
     * 
     * @param commentId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-09 16:14:22
     */
    public void deleteCommentByCommentId(Long commentId);
    
    /**
     * 修改评论
     * 
     * @param commentId  
     * @param comment 评论信息
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-09 16:14:22
     */
    public void updateCommentByCommentId(Long commentId, Comment comment);
    
    /** 
     * 根据获取评论
     * 
     * @param commentId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-09 16:14:22
     */
    public Comment getCommentByCommentId(Long commentId);

    /**
     * 查询评论
     * 
     * @param commentId  
     * @param comment 评论信息
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-09 16:14:22
     */
    public List<Comment> findCommentListByCommentId(Long commentId);
    
    /**
     * 查询评论
     * 
     * @param invitationId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-09 16:14:22
     */
    public List<Comment> findCommentListByInvitationId(Long invitationId);

    /**
     * 查询评论
     * 
     * @param commentId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-09 16:14:22
     */
    public List<Map<String,Object>> findComment2MapListByCommentId(Long commentId);

    /**
     * 查询评论
     * 
     * @param invitationId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-09 16:14:22
     */
    public List<Map<String,Object>> findComment2MapListByInvitationId(Long invitationId);

    /**
     * 获取评论列表
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-09 16:14:22
     */
    public List<Comment> findCommentList();
    
    /**
     * 获取评论列表
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-09 16:14:22
     */
    public List<Map<String,Object>> findComment2MapList();
    
    /**
     * 根据查询条件获取评论个数
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-09 16:14:22
     */
    public Integer countCommentListByQuery(Map<String, Object> map);
    
    /**
     * 根据查询条件获取评论列表
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-09 16:14:22
     */
    public List<Comment> findCommentListByQuery(Map<String, Object> map);
    
    /**
     * 根据查询条件获取评论个数(Map)
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-09 16:14:22
     */
    public Integer countCommentMapListByQuery(Map<String, Object> map);
    
    /**
     * 根据查询条件获取评论列表(Map)
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-09 16:14:22
     */
    public List<Map<String,Object>> findCommentMapListByQuery(Map<String, Object> map);

    public List<Map<String,Object>> finfCommentAndSonCommtne(@Param("map") Map<String, Object> map);


    public Integer countCommentAndSonCommtne(@Param("map") Map<String, Object> map);
}
