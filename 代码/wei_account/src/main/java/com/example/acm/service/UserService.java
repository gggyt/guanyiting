package com.example.acm.service;

import java.util.List;
import java.util.Map;

import com.example.acm.entity.User;
import org.apache.ibatis.annotations.Param;

/** 
 * user服务接口
 *
 * @author guanyiting
 * @date 2018-12-07 16:20:17
 */
public interface UserService{
    
    /** 
     * 添加user
     * 
     * @param user user
     * @version v1.0
     * @author guanyiting
     * @date 2018-12-07 16:20:17
     */
    public void addUser(User user);
    
    /** 
     * 添加user列表
     * 
     * @param list user列表
     * @version v1.0
     * @author guanyiting
     * @date 2018-12-07 16:20:17
     */
    public void addUserList(List<User> list);      
    
    /** 
     * 删除user
     * 
     * @param userId  
     * @version v1.0
     * @author guanyiting
     * @date 2018-12-07 16:20:17
     */
    public void deleteUserByUserId(Integer userId);
    
    /**
     * 修改user
     * 
     * @param userId  
     * @param user user信息
     * @version v1.0
     * @author guanyiting
     * @date 2018-12-07 16:20:17
     */
    public void updateUserByUserId(Integer userId, User user);
    
    /** 
     * 根据获取user
     * 
     * @param userId  
     * @version v1.0
     * @author guanyiting
     * @date 2018-12-07 16:20:17
     */
    public User getUserByUserId(Integer userId);

    /**
     * 查询user
     * 
     * @param userId  
     * @param user user信息
     * @version v1.0
     * @author guanyiting
     * @date 2018-12-07 16:20:17
     */
    public List<User> findUserListByUserId(Integer userId);
    
    /**
     * 查询user
     * 
     * @param userId  
     * @version v1.0
     * @author guanyiting
     * @date 2018-12-07 16:20:17
     */
    public List<Map<String,Object>> findUser2MapListByUserId(Integer userId);

    /**
     * 获取user列表
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2018-12-07 16:20:17
     */
    public List<User> findUserList();
    
    /**
     * 获取user列表
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2018-12-07 16:20:17
     */
    public List<Map<String,Object>> findUser2MapList();
    
    /**
     * 根据查询条件获取user个数
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2018-12-07 16:20:17
     */
    public Integer countUserListByQuery(Map<String, Object> map);
    
    /**
     * 根据查询条件获取user列表
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2018-12-07 16:20:17
     */
    public List<User> findUserListByQuery(Map<String, Object> map);
    
    /**
     * 根据查询条件获取user个数(Map)
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2018-12-07 16:20:17
     */
    public Integer countUserMapListByQuery(Map<String, Object> map);
    
    /**
     * 根据查询条件获取user列表(Map)
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2018-12-07 16:20:17
     */
    public List<Map<String,Object>> findUserMapListByQuery(Map<String, Object> map);

    public List<User> findUserListByQueryMy(Map<String, Object> map);

    public List<Map<String,Object>> findUserByCompetitionId(Map<String, Object> map);

    public Integer countUserByCompetitionId(Map<String, Object> map);

    public List<Map<String,Object>> findUserByLectureId(Map<String, Object> map);

    public Integer countUserByLectureId(Map<String, Object> map);
}
