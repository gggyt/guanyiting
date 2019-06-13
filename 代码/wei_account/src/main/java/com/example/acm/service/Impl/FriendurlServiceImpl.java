
package com.example.acm.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.acm.entity.Friendurl;
import com.example.acm.mapper.FriendurlMapper;
import com.example.acm.service.FriendurlService;

/** 
 * 友链服务
 *
 * @author guanyiting
 * @date 2019-03-24 16:00:57
 */
@Service("friendurlService")
@Transactional
public class FriendurlServiceImpl implements FriendurlService {
	
	@Autowired
	private FriendurlMapper  friendurlMapper;	
    
    /** 
     * 添加友链
     * 
     * @param friendurl 友链
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-24 16:00:57
     */
    public void addFriendurl(Friendurl friendurl){
        friendurlMapper.addFriendurl(friendurl);
    }    
    
    /** 
     * 添加友链列表
     * 
     * @param list 友链列表
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-24 16:00:57
     */
    public void addFriendurlList(List<Friendurl> list){
        friendurlMapper.addFriendurlList(list);
    }
    
    /** 
     * 删除友链
     * 
     * @param friendurlId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-24 16:00:57
     */
    public void deleteFriendurlByFriendurlId(Long friendurlId){
        friendurlMapper.deleteFriendurlByFriendurlId(friendurlId);
    }
    
    /**
     * 修改友链
     * 
     * @param friendurlId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-24 16:00:57
     */
    public void updateFriendurlByFriendurlId(Long friendurlId,Friendurl friendurl){
        friendurlMapper.updateFriendurlByFriendurlId(friendurlId,friendurl);
    }
    
    /** 
     * 根据获取友链
     * 
     * @param friendurlId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-24 16:00:57
     */
    public Friendurl getFriendurlByFriendurlId(Long friendurlId){
        return friendurlMapper.getFriendurlByFriendurlId(friendurlId);        
    }
    
    /**
     * 查询友链
     * 
     * @param friendurlId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-24 16:00:57
     */
    public List<Friendurl> findFriendurlListByFriendurlId(Long friendurlId){
        return friendurlMapper.findFriendurlListByFriendurlId(friendurlId);
    }
    
    /**
     * 查询友链
     * 
     * @param friendurlId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-24 16:00:57
     */
    public List<Map<String,Object>> findFriendurl2MapListByFriendurlId(Long friendurlId){
        return friendurlMapper.findFriendurl2MapListByFriendurlId(friendurlId);
    }

    /**
     * 获取友链列表
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-24 16:00:57
     */
    public List<Friendurl> findFriendurlList(){
        return friendurlMapper.findFriendurlList();
    }
    
    /**
     * 获取友链列表
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-24 16:00:57
     */
    public List<Map<String,Object>> findFriendurl2MapList(){
        return friendurlMapper.findFriendurl2MapList();
    }
    
    /**
     * 根据查询条件获取友链个数
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-24 16:00:57
     */
    public Integer countFriendurlListByQuery(Map<String, Object> map){
    	return friendurlMapper.countFriendurlListByQuery(map);
    }
    
    /**
     * 根据查询条件获取友链列表
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-24 16:00:57
     */
    public List<Friendurl> findFriendurlListByQuery(Map<String, Object> map){
    	return friendurlMapper.findFriendurlListByQuery(map);
    }
    
    /**
     * 根据查询条件获取友链个数(Map)
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-24 16:00:57
     */
    public Integer countFriendurlMapListByQuery(Map<String, Object> map){
    	return friendurlMapper.countFriendurlMapListByQuery(map);
    }
    
    /**
     * 根据查询条件获取友链列表(Map)
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-24 16:00:57
     */
    public List<Map<String,Object>> findFriendurlMapListByQuery(Map<String, Object> map){
    	return friendurlMapper.findFriendurlMapListByQuery(map);
    }

}
