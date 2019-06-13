
package com.example.acm.service;

import java.util.List;
import java.util.Map;

import com.example.acm.entity.Invitation;

/** 
 * 讨论服务接口
 *
 * @author guanyiting
 * @date 2019-02-05 16:05:40
 */
public interface InvitationService{
    
    /** 
     * 添加讨论
     * 
     * @param invitation 讨论
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-05 16:05:40
     */
    public void addInvitation(Invitation invitation);
    
    /** 
     * 添加讨论列表
     * 
     * @param list 讨论列表
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-05 16:05:40
     */
    public void addInvitationList(List<Invitation> list);      
    
    /** 
     * 删除讨论
     * 
     * @param invitationId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-05 16:05:40
     */
    public void deleteInvitationByInvitationId(Long invitationId);
    
    /**
     * 修改讨论
     * 
     * @param invitationId  
     * @param invitation 讨论信息
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-05 16:05:40
     */
    public void updateInvitationByInvitationId(Long invitationId, Invitation invitation);
    
    /** 
     * 根据获取讨论
     * 
     * @param invitationId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-05 16:05:40
     */
    public Invitation getInvitationByInvitationId(Long invitationId);

    /**
     * 查询讨论
     * 
     * @param invitationId  
     * @param invitation 讨论信息
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-05 16:05:40
     */
    public List<Invitation> findInvitationListByInvitationId(Long invitationId);
    
    /**
     * 查询讨论
     * 
     * @param invitationId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-05 16:05:40
     */
    public List<Map<String,Object>> findInvitation2MapListByInvitationId(Long invitationId);

    /**
     * 获取讨论列表
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-05 16:05:40
     */
    public List<Invitation> findInvitationList();
    
    /**
     * 获取讨论列表
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-05 16:05:40
     */
    public List<Map<String,Object>> findInvitation2MapList();
    
    /**
     * 根据查询条件获取讨论个数
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-05 16:05:40
     */
    public Integer countInvitationListByQuery(Map<String, Object> map);
    
    /**
     * 根据查询条件获取讨论列表
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-05 16:05:40
     */
    public List<Invitation> findInvitationListByQuery(Map<String, Object> map);
    
    /**
     * 根据查询条件获取讨论个数(Map)
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-05 16:05:40
     */
    public Integer countInvitationMapListByQuery(Map<String, Object> map);
    
    /**
     * 根据查询条件获取讨论列表(Map)
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-05 16:05:40
     */
    public List<Map<String,Object>> findInvitationMapListByQuery(Map<String, Object> map);
	
}
