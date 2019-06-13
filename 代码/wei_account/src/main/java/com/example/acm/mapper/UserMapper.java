
package com.example.acm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.example.acm.entity.User;

/** 
 * user数据操作接口
 *
 * @author guanyiting
 * @date 2018-12-07 16:20:17
 */
public interface UserMapper{
    
    /** 
     * 添加user
     * 
     * @param user user
     * @version v1.0
     * @author guanyiting
     * @date 2018-12-07 16:20:17
     */
    public void addUser(@Param("user") User user);
    
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
    public void deleteUserByUserId(@Param("userId") Integer userId);
    
    /**
     * 修改user
     * 
     * @param userId  
     * @version v1.0
     * @author guanyiting
     * @date 2018-12-07 16:20:17
     */
    public void updateUserByUserId(@Param("userId") Integer userId, @Param("user") User user);
    
    /** 
     * 根据获取user
     * 
     * @param userId  
     * @version v1.0
     * @author guanyiting
     * @date 2018-12-07 16:20:17
     */
    public User getUserByUserId(@Param("userId") Integer userId);
    
    /**
     * 查询user
     * 
     * @param userId  
     * @version v1.0
     * @author guanyiting
     * @date 2018-12-07 16:20:17
     */
    public List<User> findUserListByUserId(@Param("userId") Integer userId);
    
    /**
     * 查询user
     * 
     * @param userId  
     * @version v1.0
     * @author guanyiting
     * @date 2018-12-07 16:20:17
     */
    public List<Map<String,Object>> findUser2MapListByUserId(@Param("userId") Integer userId);

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
    public Integer countUserListByQuery(@Param("map") Map<String, Object> map);
    
    /**
     * 根据查询条件获取user列表
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2018-12-07 16:20:17
     */
    public List<User> findUserListByQuery(@Param("map") Map<String, Object> map);
    
    /**
     * 根据查询条件获取user个数(Map)
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2018-12-07 16:20:17
     */
    public Integer countUserMapListByQuery(@Param("map") Map<String, Object> map);
    
    /**
     * 根据查询条件获取user列表(Map)
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2018-12-07 16:20:17
     */
    public List<Map<String,Object>> findUserMapListByQuery(@Param("map") Map<String, Object> map);

    public List<User> findUserListByQueryMy(@Param("map") Map<String, Object> map);

    public List<Map<String,Object>> findUserByCompetitionId(@Param("map") Map<String, Object> map);

    public Integer countUserByCompetitionId(@Param("map") Map<String, Object> map);

    public List<Map<String,Object>> findUserByLectureId(@Param("map") Map<String, Object> map);

    public Integer countUserByLectureId(@Param("map") Map<String, Object> map);

    public  List<Map<String,Object>> findUserBuImpressionSort(@Param("map") Map<String, Object> map);
}
