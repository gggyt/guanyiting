
package com.example.acm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.example.acm.entity.Replyproblem;

/** 
 * 每日一题题解数据操作接口
 *
 * @author guanyiting
 * @date 2019-04-16 11:15:34
 */
public interface ReplyproblemMapper{
    
    /** 
     * 添加每日一题题解
     * 
     * @param replyproblem 每日一题题解
     * @version v1.0
     * @author guanyiting
     * @date 2019-04-16 11:15:34
     */
    public void addReplyproblem(@Param("replyproblem") Replyproblem replyproblem);
    
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
    public void deleteReplyproblemByProblemansId(@Param("problemansId") Long problemansId);
    
    /**
     * 修改每日一题题解
     * 
     * @param problemansId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-04-16 11:15:34
     */
    public void updateReplyproblemByProblemansId(@Param("problemansId") Long problemansId, @Param("replyproblem") Replyproblem replyproblem);
    
    /** 
     * 根据获取每日一题题解
     * 
     * @param problemansId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-04-16 11:15:34
     */
    public Replyproblem getReplyproblemByProblemansId(@Param("problemansId") Long problemansId);
    
    /**
     * 查询每日一题题解
     * 
     * @param problemansId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-04-16 11:15:34
     */
    public List<Replyproblem> findReplyproblemListByProblemansId(@Param("problemansId") Long problemansId);
    
    /**
     * 查询每日一题题解
     * 
     * @param problemansId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-04-16 11:15:34
     */
    public List<Map<String,Object>> findReplyproblem2MapListByProblemansId(@Param("problemansId") Long problemansId);

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
    public Integer countReplyproblemListByQuery(@Param("map") Map<String, Object> map);
    
    /**
     * 根据查询条件获取每日一题题解列表
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-04-16 11:15:34
     */
    public List<Replyproblem> findReplyproblemListByQuery(@Param("map") Map<String, Object> map);
    
    /**
     * 根据查询条件获取每日一题题解个数(Map)
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-04-16 11:15:34
     */
    public Integer countReplyproblemMapListByQuery(@Param("map") Map<String, Object> map);
    
    /**
     * 根据查询条件获取每日一题题解列表(Map)
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-04-16 11:15:34
     */
    public List<Map<String,Object>> findReplyproblemMapListByQuery(@Param("map") Map<String, Object> map);
	
}
