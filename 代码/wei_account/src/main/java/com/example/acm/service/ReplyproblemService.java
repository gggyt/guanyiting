
package com.example.acm.service;

import java.util.List;
import java.util.Map;

import com.example.acm.entity.Replyproblem;

/** 
 * 每日一题题解服务接口
 *
 * @author guanyiting
 * @date 2019-04-16 11:15:34
 */
public interface ReplyproblemService{
    
    /** 
     * 添加每日一题题解
     * 
     * @param replyproblem 每日一题题解
     * @version v1.0
     * @author guanyiting
     * @date 2019-04-16 11:15:34
     */
    public void addReplyproblem(Replyproblem replyproblem);
    
    /** 
     * 添加每日一题题解列表
     * 
     * @param list 每日一题题解列表
     * @version v1.0
     * @author guanyiting
     * @date 2019-04-16 11:15:34
     */
    public void addReplyproblemList(List<Replyproblem> list);      
    
    /** 
     * 删除每日一题题解
     * 
     * @param problemansId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-04-16 11:15:34
     */
    public void deleteReplyproblemByProblemansId(Long problemansId);
    
    /**
     * 修改每日一题题解
     * 
     * @param problemansId  
     * @param replyproblem 每日一题题解信息
     * @version v1.0
     * @author guanyiting
     * @date 2019-04-16 11:15:34
     */
    public void updateReplyproblemByProblemansId(Long problemansId, Replyproblem replyproblem);
    
    /** 
     * 根据获取每日一题题解
     * 
     * @param problemansId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-04-16 11:15:34
     */
    public Replyproblem getReplyproblemByProblemansId(Long problemansId);

    /**
     * 查询每日一题题解
     * 
     * @param problemansId  
     * @param replyproblem 每日一题题解信息
     * @version v1.0
     * @author guanyiting
     * @date 2019-04-16 11:15:34
     */
    public List<Replyproblem> findReplyproblemListByProblemansId(Long problemansId);
    
    /**
     * 查询每日一题题解
     * 
     * @param problemansId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-04-16 11:15:34
     */
    public List<Map<String,Object>> findReplyproblem2MapListByProblemansId(Long problemansId);

    /**
     * 获取每日一题题解列表
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-04-16 11:15:34
     */
    public List<Replyproblem> findReplyproblemList();
    
    /**
     * 获取每日一题题解列表
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-04-16 11:15:34
     */
    public List<Map<String,Object>> findReplyproblem2MapList();
    
    /**
     * 根据查询条件获取每日一题题解个数
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-04-16 11:15:34
     */
    public Integer countReplyproblemListByQuery(Map<String, Object> map);
    
    /**
     * 根据查询条件获取每日一题题解列表
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-04-16 11:15:34
     */
    public List<Replyproblem> findReplyproblemListByQuery(Map<String, Object> map);
    
    /**
     * 根据查询条件获取每日一题题解个数(Map)
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-04-16 11:15:34
     */
    public Integer countReplyproblemMapListByQuery(Map<String, Object> map);
    
    /**
     * 根据查询条件获取每日一题题解列表(Map)
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-04-16 11:15:34
     */
    public List<Map<String,Object>> findReplyproblemMapListByQuery(Map<String, Object> map);
	
}
