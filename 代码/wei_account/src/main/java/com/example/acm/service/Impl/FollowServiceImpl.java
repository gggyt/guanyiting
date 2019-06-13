
package com.example.acm.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.acm.entity.Follow;
import com.example.acm.mapper.FollowMapper;
import com.example.acm.service.FollowService;

/** 
 * 关注表服务
 *
 * @author guanyiting
 * @date 2019-05-25 19:25:31
 */
@Service("followService")
@Transactional
public class FollowServiceImpl implements FollowService {
	
	@Autowired
	private FollowMapper  followMapper;	
    
    /** 
     * 添加关注表
     * 
     * @param follow 关注表
     * @version v1.0
     * @author guanyiting
     * @date 2019-05-25 19:25:31
     */
    public void addFollow(Follow follow){
        followMapper.addFollow(follow);
    }    
    
    /** 
     * 添加关注表列表
     * 
     * @param list 关注表列表
     * @version v1.0
     * @author guanyiting
     * @date 2019-05-25 19:25:31
     */
    public void addFollowList(List<Follow> list){
        followMapper.addFollowList(list);
    }
    
    /** 
     * 删除关注表
     * 
     * @param followId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-05-25 19:25:31
     */
    public void deleteFollowByFollowId(Long followId){
        followMapper.deleteFollowByFollowId(followId);
    }
    
    /**
     * 修改关注表
     * 
     * @param followId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-05-25 19:25:31
     */
    public void updateFollowByFollowId(Long followId,Follow follow){
        followMapper.updateFollowByFollowId(followId,follow);
    }
    
    /** 
     * 根据获取关注表
     * 
     * @param followId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-05-25 19:25:31
     */
    public Follow getFollowByFollowId(Long followId){
        return followMapper.getFollowByFollowId(followId);        
    }
    
    /**
     * 查询关注表
     * 
     * @param followId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-05-25 19:25:31
     */
    public List<Follow> findFollowListByFollowId(Long followId){
        return followMapper.findFollowListByFollowId(followId);
    }
    
    /**
     * 查询关注表
     * 
     * @param followId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-05-25 19:25:31
     */
    public List<Map<String,Object>> findFollow2MapListByFollowId(Long followId){
        return followMapper.findFollow2MapListByFollowId(followId);
    }

    /**
     * 获取关注表列表
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-05-25 19:25:31
     */
    public List<Follow> findFollowList(){
        return followMapper.findFollowList();
    }
    
    /**
     * 获取关注表列表
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-05-25 19:25:31
     */
    public List<Map<String,Object>> findFollow2MapList(){
        return followMapper.findFollow2MapList();
    }
    
    /**
     * 根据查询条件获取关注表个数
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-05-25 19:25:31
     */
    public Integer countFollowListByQuery(Map<String, Object> map){
    	return followMapper.countFollowListByQuery(map);
    }
    
    /**
     * 根据查询条件获取关注表列表
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-05-25 19:25:31
     */
    public List<Follow> findFollowListByQuery(Map<String, Object> map){
    	return followMapper.findFollowListByQuery(map);
    }
    
    /**
     * 根据查询条件获取关注表个数(Map)
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-05-25 19:25:31
     */
    public Integer countFollowMapListByQuery(Map<String, Object> map){
    	return followMapper.countFollowMapListByQuery(map);
    }
    
    /**
     * 根据查询条件获取关注表列表(Map)
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-05-25 19:25:31
     */
    public List<Map<String,Object>> findFollowMapListByQuery(Map<String, Object> map){
    	return followMapper.findFollowMapListByQuery(map);
    }

}
