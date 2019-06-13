
package com.example.acm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.example.acm.entity.Follow;

/** 
 * 关注表数据操作接口
 *
 * @author guanyiting
 * @date 2019-05-25 19:25:31
 */
public interface FollowMapper{
    
    /** 
     * 添加关注表
     * 
     * @param follow 关注表
     * @version v1.0
     * @author guanyiting
     * @date 2019-05-25 19:25:31
     */
    public void addFollow(@Param("follow") Follow follow);
    
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
    public void deleteFollowByFollowId(@Param("followId") Long followId);
    
    /**
     * 修改关注表
     * 
     * @param followId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-05-25 19:25:31
     */
    public void updateFollowByFollowId(@Param("followId") Long followId, @Param("follow") Follow follow);
    
    /** 
     * 根据获取关注表
     * 
     * @param followId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-05-25 19:25:31
     */
    public Follow getFollowByFollowId(@Param("followId") Long followId);
    
    /**
     * 查询关注表
     * 
     * @param followId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-05-25 19:25:31
     */
    public List<Follow> findFollowListByFollowId(@Param("followId") Long followId);
    
    /**
     * 查询关注表
     * 
     * @param followId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-05-25 19:25:31
     */
    public List<Map<String,Object>> findFollow2MapListByFollowId(@Param("followId") Long followId);

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
    public Integer countFollowListByQuery(@Param("map") Map<String, Object> map);
    
    /**
     * 根据查询条件获取关注表列表
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-05-25 19:25:31
     */
    public List<Follow> findFollowListByQuery(@Param("map") Map<String, Object> map);
    
    /**
     * 根据查询条件获取关注表个数(Map)
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-05-25 19:25:31
     */
    public Integer countFollowMapListByQuery(@Param("map") Map<String, Object> map);
    
    /**
     * 根据查询条件获取关注表列表(Map)
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-05-25 19:25:31
     */
    public List<Map<String,Object>> findFollowMapListByQuery(@Param("map") Map<String, Object> map);
	
}
