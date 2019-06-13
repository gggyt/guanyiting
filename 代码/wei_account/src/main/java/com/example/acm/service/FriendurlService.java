
package com.example.acm.service;

import java.util.List;
import java.util.Map;

import com.example.acm.entity.Friendurl;

/** 
 * 友链服务接口
 *
 * @author guanyiting
 * @date 2019-03-24 16:00:57
 */
public interface FriendurlService{
    
    /** 
     * 添加友链
     * 
     * @param friendurl 友链
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-24 16:00:57
     */
    public void addFriendurl(Friendurl friendurl);
    
    /** 
     * 添加友链列表
     * 
     * @param list 友链列表
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-24 16:00:57
     */
    public void addFriendurlList(List<Friendurl> list);      
    
    /** 
     * 删除友链
     * 
     * @param friendurlId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-24 16:00:57
     */
    public void deleteFriendurlByFriendurlId(Long friendurlId);
    
    /**
     * 修改友链
     * 
     * @param friendurlId  
     * @param friendurl 友链信息
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-24 16:00:57
     */
    public void updateFriendurlByFriendurlId(Long friendurlId, Friendurl friendurl);
    
    /** 
     * 根据获取友链
     * 
     * @param friendurlId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-24 16:00:57
     */
    public Friendurl getFriendurlByFriendurlId(Long friendurlId);

    /**
     * 查询友链
     * 
     * @param friendurlId  
     * @param friendurl 友链信息
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-24 16:00:57
     */
    public List<Friendurl> findFriendurlListByFriendurlId(Long friendurlId);
    
    /**
     * 查询友链
     * 
     * @param friendurlId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-24 16:00:57
     */
    public List<Map<String,Object>> findFriendurl2MapListByFriendurlId(Long friendurlId);

    /**
     * 获取友链列表
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-24 16:00:57
     */
    public List<Friendurl> findFriendurlList();
    
    /**
     * 获取友链列表
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-24 16:00:57
     */
    public List<Map<String,Object>> findFriendurl2MapList();
    
    /**
     * 根据查询条件获取友链个数
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-24 16:00:57
     */
    public Integer countFriendurlListByQuery(Map<String, Object> map);
    
    /**
     * 根据查询条件获取友链列表
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-24 16:00:57
     */
    public List<Friendurl> findFriendurlListByQuery(Map<String, Object> map);
    
    /**
     * 根据查询条件获取友链个数(Map)
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-24 16:00:57
     */
    public Integer countFriendurlMapListByQuery(Map<String, Object> map);
    
    /**
     * 根据查询条件获取友链列表(Map)
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-24 16:00:57
     */
    public List<Map<String,Object>> findFriendurlMapListByQuery(Map<String, Object> map);
	
}
