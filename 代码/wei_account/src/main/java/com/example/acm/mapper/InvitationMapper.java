
package com.example.acm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.example.acm.entity.Invitation;

/** 
 * 讨论数据操作接口
 *
 * @author guanyiting
 * @date 2019-02-05 16:05:40
 */
public interface InvitationMapper{
    
    /** 
     * 添加讨论
     * 
     * @param invitation 讨论
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-05 16:05:40
     */
    public void addInvitation(@Param("invitation") Invitation invitation);
    
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
    public void deleteInvitationByInvitationId(@Param("invitationId") Long invitationId);
    
    /**
     * 修改讨论
     * 
     * @param invitationId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-05 16:05:40
     */
    public void updateInvitationByInvitationId(@Param("invitationId") Long invitationId, @Param("invitation") Invitation invitation);
    
    /** 
     * 根据获取讨论
     * 
     * @param invitationId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-05 16:05:40
     */
    public Invitation getInvitationByInvitationId(@Param("invitationId") Long invitationId);
    
    /**
     * 查询讨论
     * 
     * @param invitationId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-05 16:05:40
     */
    public List<Invitation> findInvitationListByInvitationId(@Param("invitationId") Long invitationId);
    
    /**
     * 查询讨论
     * 
     * @param invitationId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-05 16:05:40
     */
    public List<Map<String,Object>> findInvitation2MapListByInvitationId(@Param("invitationId") Long invitationId);

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
    public Integer countInvitationListByQuery(@Param("map") Map<String, Object> map);
    
    /**
     * 根据查询条件获取讨论列表
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-05 16:05:40
     */
    public List<Invitation> findInvitationListByQuery(@Param("map") Map<String, Object> map);
    
    /**
     * 根据查询条件获取讨论个数(Map)
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-05 16:05:40
     */
    public Integer countInvitationMapListByQuery(@Param("map") Map<String, Object> map);
    
    /**
     * 根据查询条件获取讨论列表(Map)
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-05 16:05:40
     */
    public List<Map<String,Object>> findInvitationMapListByQuery(@Param("map") Map<String, Object> map);

    public int conutInvitationBefore(@Param("map") Map<String, Object> map);
}
