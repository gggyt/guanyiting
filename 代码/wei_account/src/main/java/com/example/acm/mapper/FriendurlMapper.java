
package com.example.acm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.example.acm.entity.Friendurl;

/** 
 * 友链数据操作接口
 *
 * @author guanyiting
 * @date 2019-03-24 16:00:57
 */
public interface FriendurlMapper{
    
    /** 
     * 添加友链
     * 
     * @param friendurl 友链
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-24 16:00:57
     */
    public void addFriendurl(@Param("friendurl") Friendurl friendurl);
    
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
    public void deleteFriendurlByFriendurlId(@Param("friendurlId") Long friendurlId);
    
    /**
     * 修改友链
     * 
     * @param friendurlId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-24 16:00:57
     */
    public void updateFriendurlByFriendurlId(@Param("friendurlId") Long friendurlId, @Param("friendurl") Friendurl friendurl);
    
    /** 
     * 根据获取友链
     * 
     * @param friendurlId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-24 16:00:57
     */
    public Friendurl getFriendurlByFriendurlId(@Param("friendurlId") Long friendurlId);
    
    /**
     * 查询友链
     * 
     * @param friendurlId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-24 16:00:57
     */
    public List<Friendurl> findFriendurlListByFriendurlId(@Param("friendurlId") Long friendurlId);
    
    /**
     * 查询友链
     * 
     * @param friendurlId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-24 16:00:57
     */
    public List<Map<String,Object>> findFriendurl2MapListByFriendurlId(@Param("friendurlId") Long friendurlId);

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
    public Integer countFriendurlListByQuery(@Param("map") Map<String, Object> map);
    
    /**
     * 根据查询条件获取友链列表
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-24 16:00:57
     */
    public List<Friendurl> findFriendurlListByQuery(@Param("map") Map<String, Object> map);
    
    /**
     * 根据查询条件获取友链个数(Map)
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-24 16:00:57
     */
    public Integer countFriendurlMapListByQuery(@Param("map") Map<String, Object> map);
    
    /**
     * 根据查询条件获取友链列表(Map)
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-24 16:00:57
     */
    public List<Map<String,Object>> findFriendurlMapListByQuery(@Param("map") Map<String, Object> map);
	
}
