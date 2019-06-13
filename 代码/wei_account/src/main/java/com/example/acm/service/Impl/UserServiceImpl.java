
package com.example.acm.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.acm.entity.User;
import com.example.acm.mapper.UserMapper;
import com.example.acm.service.UserService;

/** 
 * user服务
 *
 * @author guanyiting
 * @date 2018-12-07 16:20:17
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper  userMapper;	
    
    /** 
     * 添加user
     * 
     * @param user user
     * @version v1.0
     * @author guanyiting
     * @date 2018-12-07 16:20:17
     */
    public void addUser(User user){
        userMapper.addUser(user);
    }    
    
    /** 
     * 添加user列表
     * 
     * @param list user列表
     * @version v1.0
     * @author guanyiting
     * @date 2018-12-07 16:20:17
     */
    public void addUserList(List<User> list){
        userMapper.addUserList(list);
    }
    
    /** 
     * 删除user
     * 
     * @param userId  
     * @version v1.0
     * @author guanyiting
     * @date 2018-12-07 16:20:17
     */
    public void deleteUserByUserId(Integer userId){
        userMapper.deleteUserByUserId(userId);
    }
    
    /**
     * 修改user
     * 
     * @param userId  
     * @version v1.0
     * @author guanyiting
     * @date 2018-12-07 16:20:17
     */
    public void updateUserByUserId(Integer userId,User user){
        userMapper.updateUserByUserId(userId,user);
    }
    
    /** 
     * 根据获取user
     * 
     * @param userId  
     * @version v1.0
     * @author guanyiting
     * @date 2018-12-07 16:20:17
     */
    public User getUserByUserId(Integer userId){
        return userMapper.getUserByUserId(userId);        
    }
    
    /**
     * 查询user
     * 
     * @param userId  
     * @version v1.0
     * @author guanyiting
     * @date 2018-12-07 16:20:17
     */
    public List<User> findUserListByUserId(Integer userId){
        return userMapper.findUserListByUserId(userId);
    }
    
    /**
     * 查询user
     * 
     * @param userId  
     * @version v1.0
     * @author guanyiting
     * @date 2018-12-07 16:20:17
     */
    public List<Map<String,Object>> findUser2MapListByUserId(Integer userId){
        return userMapper.findUser2MapListByUserId(userId);
    }

    /**
     * 获取user列表
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2018-12-07 16:20:17
     */
    public List<User> findUserList(){
        return userMapper.findUserList();
    }
    
    /**
     * 获取user列表
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2018-12-07 16:20:17
     */
    public List<Map<String,Object>> findUser2MapList(){
        return userMapper.findUser2MapList();
    }
    
    /**
     * 根据查询条件获取user个数
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2018-12-07 16:20:17
     */
    public Integer countUserListByQuery(Map<String, Object> map){
    	return userMapper.countUserListByQuery(map);
    }
    
    /**
     * 根据查询条件获取user列表
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2018-12-07 16:20:17
     */
    public List<User> findUserListByQuery(Map<String, Object> map){
    	return userMapper.findUserListByQuery(map);
    }
    
    /**
     * 根据查询条件获取user个数(Map)
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2018-12-07 16:20:17
     */
    public Integer countUserMapListByQuery(Map<String, Object> map){
    	return userMapper.countUserMapListByQuery(map);
    }
    
    /**
     * 根据查询条件获取user列表(Map)
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2018-12-07 16:20:17
     */
    public List<Map<String,Object>> findUserMapListByQuery(Map<String, Object> map){
    	return userMapper.findUserMapListByQuery(map);
    }

    public List<User> findUserListByQueryMy(Map<String, Object> map){
        return userMapper.findUserListByQueryMy(map);
    }
    public List<Map<String,Object>> findUserByCompetitionId(Map<String, Object> map){
        return userMapper.findUserByCompetitionId(map);
    }

    public Integer countUserByCompetitionId(Map<String, Object> map){
        return userMapper.countUserByCompetitionId(map);
    }

    public List<Map<String,Object>> findUserByLectureId(Map<String, Object> map){
        return userMapper.findUserByLectureId(map);
    }

    public Integer countUserByLectureId(Map<String, Object> map){
        return userMapper.countUserByLectureId(map);
    }
}
