
package com.example.acm.service;

import java.util.List;
import java.util.Map;

import com.example.acm.entity.Problem;

/** 
 * 每日一题服务接口
 *
 * @author guanyiting
 * @date 2019-04-15 22:12:06
 */
public interface ProblemService{
    
    /** 
     * 添加每日一题
     * 
     * @param problem 每日一题
     * @version v1.0
     * @author guanyiting
     * @date 2019-04-15 22:12:06
     */
    public void addProblem(Problem problem);
    
    /** 
     * 添加每日一题列表
     * 
     * @param list 每日一题列表
     * @version v1.0
     * @author guanyiting
     * @date 2019-04-15 22:12:06
     */
    public void addProblemList(List<Problem> list);      
    
    /** 
     * 删除每日一题
     * 
     * @param problemId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-04-15 22:12:06
     */
    public void deleteProblemByProblemId(Long problemId);
    
    /**
     * 修改每日一题
     * 
     * @param problemId  
     * @param problem 每日一题信息
     * @version v1.0
     * @author guanyiting
     * @date 2019-04-15 22:12:06
     */
    public void updateProblemByProblemId(Long problemId, Problem problem);
    
    /** 
     * 根据获取每日一题
     * 
     * @param problemId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-04-15 22:12:06
     */
    public Problem getProblemByProblemId(Long problemId);

    /**
     * 查询每日一题
     * 
     * @param problemId  
     * @param problem 每日一题信息
     * @version v1.0
     * @author guanyiting
     * @date 2019-04-15 22:12:06
     */
    public List<Problem> findProblemListByProblemId(Long problemId);
    
    /**
     * 查询每日一题
     * 
     * @param problemId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-04-15 22:12:06
     */
    public List<Map<String,Object>> findProblem2MapListByProblemId(Long problemId);

    /**
     * 获取每日一题列表
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-04-15 22:12:06
     */
    public List<Problem> findProblemList();
    
    /**
     * 获取每日一题列表
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-04-15 22:12:06
     */
    public List<Map<String,Object>> findProblem2MapList();
    
    /**
     * 根据查询条件获取每日一题个数
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-04-15 22:12:06
     */
    public Integer countProblemListByQuery(Map<String, Object> map);
    
    /**
     * 根据查询条件获取每日一题列表
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-04-15 22:12:06
     */
    public List<Problem> findProblemListByQuery(Map<String, Object> map);
    
    /**
     * 根据查询条件获取每日一题个数(Map)
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-04-15 22:12:06
     */
    public Integer countProblemMapListByQuery(Map<String, Object> map);
    
    /**
     * 根据查询条件获取每日一题列表(Map)
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-04-15 22:12:06
     */
    public List<Map<String,Object>> findProblemMapListByQuery(Map<String, Object> map);
	
}
