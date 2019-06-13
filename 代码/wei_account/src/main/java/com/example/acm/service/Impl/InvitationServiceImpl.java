
package com.example.acm.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.acm.entity.Invitation;
import com.example.acm.mapper.InvitationMapper;
import com.example.acm.service.InvitationService;

/** 
 * 讨论服务
 *
 * @author guanyiting
 * @date 2019-02-05 16:05:40
 */
@Service("invitationService")
@Transactional
public class InvitationServiceImpl implements InvitationService {
	
	@Autowired
	private InvitationMapper  invitationMapper;	
    
    /** 
     * 添加讨论
     * 
     * @param invitation 讨论
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-05 16:05:40
     */
    public void addInvitation(Invitation invitation){
        invitationMapper.addInvitation(invitation);
    }    
    
    /** 
     * 添加讨论列表
     * 
     * @param list 讨论列表
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-05 16:05:40
     */
    public void addInvitationList(List<Invitation> list){
        invitationMapper.addInvitationList(list);
    }
    
    /** 
     * 删除讨论
     * 
     * @param invitationId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-05 16:05:40
     */
    public void deleteInvitationByInvitationId(Long invitationId){
        invitationMapper.deleteInvitationByInvitationId(invitationId);
    }
    
    /**
     * 修改讨论
     * 
     * @param invitationId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-05 16:05:40
     */
    public void updateInvitationByInvitationId(Long invitationId,Invitation invitation){
        invitationMapper.updateInvitationByInvitationId(invitationId,invitation);
    }
    
    /** 
     * 根据获取讨论
     * 
     * @param invitationId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-05 16:05:40
     */
    public Invitation getInvitationByInvitationId(Long invitationId){
        return invitationMapper.getInvitationByInvitationId(invitationId);        
    }
    
    /**
     * 查询讨论
     * 
     * @param invitationId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-05 16:05:40
     */
    public List<Invitation> findInvitationListByInvitationId(Long invitationId){
        return invitationMapper.findInvitationListByInvitationId(invitationId);
    }
    
    /**
     * 查询讨论
     * 
     * @param invitationId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-05 16:05:40
     */
    public List<Map<String,Object>> findInvitation2MapListByInvitationId(Long invitationId){
        return invitationMapper.findInvitation2MapListByInvitationId(invitationId);
    }

    /**
     * 获取讨论列表
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-05 16:05:40
     */
    public List<Invitation> findInvitationList(){
        return invitationMapper.findInvitationList();
    }
    
    /**
     * 获取讨论列表
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-05 16:05:40
     */
    public List<Map<String,Object>> findInvitation2MapList(){
        return invitationMapper.findInvitation2MapList();
    }
    
    /**
     * 根据查询条件获取讨论个数
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-05 16:05:40
     */
    public Integer countInvitationListByQuery(Map<String, Object> map){
    	return invitationMapper.countInvitationListByQuery(map);
    }
    
    /**
     * 根据查询条件获取讨论列表
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-05 16:05:40
     */
    public List<Invitation> findInvitationListByQuery(Map<String, Object> map){
    	return invitationMapper.findInvitationListByQuery(map);
    }
    
    /**
     * 根据查询条件获取讨论个数(Map)
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-05 16:05:40
     */
    public Integer countInvitationMapListByQuery(Map<String, Object> map){
    	return invitationMapper.countInvitationMapListByQuery(map);
    }
    
    /**
     * 根据查询条件获取讨论列表(Map)
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-05 16:05:40
     */
    public List<Map<String,Object>> findInvitationMapListByQuery(Map<String, Object> map){
    	return invitationMapper.findInvitationMapListByQuery(map);
    }

}
