
package com.example.acm.service;

import java.util.List;
import java.util.Map;

import com.example.acm.entity.Follow;

/** 
 * 关注表服务接口
 *
 * @author guanyiting
 * @date 2019-05-25 19:25:31
 */
public interface FollowService{
    
    /** 
     * 添加关注表
     * 
     * @param follow 关注表
     * @version v1.0
     * @author guanyiting
     * @date 2019-05-25 19:25:31
     */
    public void addFollow(Follow follow);
    
    /** 
     * 添加关注表列表
     * 
     * @param list 关注表列表
     * @version v1.0
     * @author guanyiting
     * @date 2019-05-25 19:25:31
     */
    public void addFollowList(List<Follow> list);      
    
    /** 
     * 删除关注表
     * 
     * @param followId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-05-25 19:25:31
     */
    public void deleteFollowByFollowId(Long followId);
    
    /**
     * 修改关注表
     * 
     * @param followId  
     * @param follow 关注表信息
     * @version v1.0
     * @author guanyiting
     * @date 2019-05-25 19:25:31
     */
    public void updateFollowByFollowId(Long followId, Follow follow);
    
    /** 
     * 根据获取关注表
     * 
     * @param followId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-05-25 19:25:31
     */
    public Follow getFollowByFollowId(Long followId);

    /**
     * 查询关注表
     * 
     * @param followId  
     * @param follow 关注表信息
     * @version v1.0
     * @author guanyiting
     * @date 2019-05-25 19:25:31
     */
    public List<Follow> findFollowListByFollowId(Long followId);
    
    /**
     * 查询关注表
     * 
     * @param followId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-05-25 19:25:31
     */
    public List<Map<String,Object>> findFollow2MapListByFollowId(Long followId);

    /**
     * 获取关注表列表
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-05-25 19:25:31
     */
    public List<Follow> findFollowList();
    
    /**
     * 获取关注表列表
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-05-25 19:25:31
     */
    public List<Map<String,Object>> findFollow2MapList();
    
    /**
     * 根据查询条件获取关注表个数
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-05-25 19:25:31
     */
    public Integer countFollowListByQuery(Map<String, Object> map);
    
    /**
     * 根据查询条件获取关注表列表
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-05-25 19:25:31
     */
    public List<Follow> findFollowListByQuery(Map<String, Object> map);
    
    /**
     * 根据查询条件获取关注表个数(Map)
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-05-25 19:25:31
     */
    public Integer countFollowMapListByQuery(Map<String, Object> map);
    
    /**
     * 根据查询条件获取关注表列表(Map)
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-05-25 19:25:31
     */
    public List<Map<String,Object>> findFollowMapListByQuery(Map<String, Object> map);
	
}
