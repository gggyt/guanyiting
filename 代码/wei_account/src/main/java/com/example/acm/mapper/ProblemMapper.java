
package com.example.acm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.example.acm.entity.Problem;

/** 
 * 每日一题数据操作接口
 *
 * @author guanyiting
 * @date 2019-04-15 22:12:06
 */
public interface ProblemMapper{
    
    /** 
     * 添加每日一题
     * 
     * @param problem 每日一题
     * @version v1.0
     * @author guanyiting
     * @date 2019-04-15 22:12:06
     */
    public void addProblem(@Param("problem") Problem problem);
    
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
    public void deleteProblemByProblemId(@Param("problemId") Long problemId);
    
    /**
     * 修改每日一题
     * 
     * @param problemId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-04-15 22:12:06
     */
    public void updateProblemByProblemId(@Param("problemId") Long problemId, @Param("problem") Problem problem);
    
    /** 
     * 根据获取每日一题
     * 
     * @param problemId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-04-15 22:12:06
     */
    public Problem getProblemByProblemId(@Param("problemId") Long problemId);
    
    /**
     * 查询每日一题
     * 
     * @param problemId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-04-15 22:12:06
     */
    public List<Problem> findProblemListByProblemId(@Param("problemId") Long problemId);
    
    /**
     * 查询每日一题
     * 
     * @param problemId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-04-15 22:12:06
     */
    public List<Map<String,Object>> findProblem2MapListByProblemId(@Param("problemId") Long problemId);

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
    public Integer countProblemListByQuery(@Param("map") Map<String, Object> map);
    
    /**
     * 根据查询条件获取每日一题列表
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-04-15 22:12:06
     */
    public List<Problem> findProblemListByQuery(@Param("map") Map<String, Object> map);
    
    /**
     * 根据查询条件获取每日一题个数(Map)
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-04-15 22:12:06
     */
    public Integer countProblemMapListByQuery(@Param("map") Map<String, Object> map);
    
    /**
     * 根据查询条件获取每日一题列表(Map)
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-04-15 22:12:06
     */
    public List<Map<String,Object>> findProblemMapListByQuery(@Param("map") Map<String, Object> map);

    public int conutProblemBefore(@Param("map") Map<String, Object> map);
}
