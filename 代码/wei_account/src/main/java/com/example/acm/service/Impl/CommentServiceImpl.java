
package com.example.acm.service.Impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.acm.entity.Comment;
import com.example.acm.mapper.CommentMapper;
import com.example.acm.service.CommentService;

/** 
 * 评论服务
 *
 * @author guanyiting
 * @date 2019-02-09 16:14:22
 */
@Service("commentService")
@Transactional
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private CommentMapper  commentMapper;	
    
    /** 
     * 添加评论
     * 
     * @param comment 评论
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-09 16:14:22
     */
    public void addComment(Comment comment){
        commentMapper.addComment(comment);
    }    
    
    /** 
     * 添加评论列表
     * 
     * @param list 评论列表
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-09 16:14:22
     */
    public void addCommentList(List<Comment> list){
        commentMapper.addCommentList(list);
    }
    
    /** 
     * 删除评论
     * 
     * @param commentId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-09 16:14:22
     */
    public void deleteCommentByCommentId(Long commentId){
        commentMapper.deleteCommentByCommentId(commentId);
    }
    
    /**
     * 修改评论
     * 
     * @param commentId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-09 16:14:22
     */
    public void updateCommentByCommentId(Long commentId,Comment comment){
        commentMapper.updateCommentByCommentId(commentId,comment);
    }
    
    /** 
     * 根据获取评论
     * 
     * @param commentId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-09 16:14:22
     */
    public Comment getCommentByCommentId(Long commentId){
        return commentMapper.getCommentByCommentId(commentId);        
    }
    
    /**
     * 查询评论
     * 
     * @param commentId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-09 16:14:22
     */
    public List<Comment> findCommentListByCommentId(Long commentId){
        return commentMapper.findCommentListByCommentId(commentId);
    }
    
    /**
     * 查询评论
     * 
     * @param invitationId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-09 16:14:22
     */
    public List<Comment> findCommentListByInvitationId(Long invitationId){
        return commentMapper.findCommentListByInvitationId(invitationId);
    }

    /**
     * 查询评论
     * 
     * @param commentId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-09 16:14:22
     */
    public List<Map<String,Object>> findComment2MapListByCommentId(Long commentId){
        return commentMapper.findComment2MapListByCommentId(commentId);
    }

    /**
     * 查询评论
     * 
     * @param invitationId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-09 16:14:22
     */
    public List<Map<String,Object>> findComment2MapListByInvitationId(Long invitationId){
        return commentMapper.findComment2MapListByInvitationId(invitationId);
    }

    /**
     * 获取评论列表
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-09 16:14:22
     */
    public List<Comment> findCommentList(){
        return commentMapper.findCommentList();
    }
    
    /**
     * 获取评论列表
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-09 16:14:22
     */
    public List<Map<String,Object>> findComment2MapList(){
        return commentMapper.findComment2MapList();
    }
    
    /**
     * 根据查询条件获取评论个数
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-09 16:14:22
     */
    public Integer countCommentListByQuery(Map<String, Object> map){
    	return commentMapper.countCommentListByQuery(map);
    }
    
    /**
     * 根据查询条件获取评论列表
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-09 16:14:22
     */
    public List<Comment> findCommentListByQuery(Map<String, Object> map){
    	return commentMapper.findCommentListByQuery(map);
    }
    
    /**
     * 根据查询条件获取评论个数(Map)
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-09 16:14:22
     */
    public Integer countCommentMapListByQuery(Map<String, Object> map){
    	return commentMapper.countCommentMapListByQuery(map);
    }
    
    /**
     * 根据查询条件获取评论列表(Map)
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-09 16:14:22
     */
    public List<Map<String,Object>> findCommentMapListByQuery(Map<String, Object> map){
    	return commentMapper.findCommentMapListByQuery(map);
    }

    public List<Map<String,Object>> finfCommentAndSonCommtne(@Param("map") Map<String, Object> map){
        return commentMapper.finfCommentAndSonCommtne(map);
    }


    public Integer countCommentAndSonCommtne(@Param("map") Map<String, Object> map){
        return commentMapper.countCommentAndSonCommtne(map);
    }

}
