
package com.example.acm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.example.acm.entity.Comment;

/** 
 * 评论数据操作接口
 *
 * @author guanyiting
 * @date 2019-02-09 16:32:52
 */
public interface CommentMapper{
    
    /** 
     * 添加评论
     * 
     * @param comment 评论
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-09 16:32:52
     */
    public void addComment(@Param("comment") Comment comment);
    
    /** 
     * 添加评论列表
     * 
     * @param list 评论列表
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-09 16:32:52
     */
    public void addCommentList(List<Comment> list);       
    
    /** 
     * 删除评论
     * 
     * @param commentId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-09 16:32:52
     */
    public void deleteCommentByCommentId(@Param("commentId") Long commentId);
    
    /**
     * 修改评论
     * 
     * @param commentId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-09 16:32:52
     */
    public void updateCommentByCommentId(@Param("commentId") Long commentId, @Param("comment") Comment comment);
    
    /** 
     * 根据获取评论
     * 
     * @param commentId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-09 16:32:52
     */
    public Comment getCommentByCommentId(@Param("commentId") Long commentId);
    
    /**
     * 查询评论
     * 
     * @param commentId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-09 16:32:52
     */
    public List<Comment> findCommentListByCommentId(@Param("commentId") Long commentId);
    
    /**
     * 查询评论
     * 
     * @param invitationId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-09 16:32:52
     */
    public List<Comment> findCommentListByInvitationId(@Param("invitationId") Long invitationId);

    /**
     * 查询评论
     * 
     * @param commentId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-09 16:32:52
     */
    public List<Map<String,Object>> findComment2MapListByCommentId(@Param("commentId") Long commentId);

    /**
     * 查询评论
     * 
     * @param invitationId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-09 16:32:52
     */
    public List<Map<String,Object>> findComment2MapListByInvitationId(@Param("invitationId") Long invitationId);

    /**
     * 获取评论列表
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-09 16:32:52
     */
    public List<Comment> findCommentList();
    
    /**
     * 获取评论列表
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-09 16:32:52
     */
    public List<Map<String,Object>> findComment2MapList();
    
    /**
     * 根据查询条件获取评论个数
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-09 16:32:52
     */
    public Integer countCommentListByQuery(@Param("map") Map<String, Object> map);
    
    /**
     * 根据查询条件获取评论列表
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-09 16:32:52
     */
    public List<Comment> findCommentListByQuery(@Param("map") Map<String, Object> map);
    
    /**
     * 根据查询条件获取评论个数(Map)
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-09 16:32:52
     */
    public Integer countCommentMapListByQuery(@Param("map") Map<String, Object> map);
    
    /**
     * 根据查询条件获取评论列表(Map)
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-09 16:32:52
     */
    public List<Map<String,Object>> findCommentMapListByQuery(@Param("map") Map<String, Object> map);


    /**
     * 根据查询条件获取评论列表(Map)
     *
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-09 16:32:52
     */
    public List<Map<String,Object>> finfCommentAndSonCommtne(@Param("map") Map<String, Object> map);


    public Integer countCommentAndSonCommtne(@Param("map") Map<String, Object> map);
	
}
