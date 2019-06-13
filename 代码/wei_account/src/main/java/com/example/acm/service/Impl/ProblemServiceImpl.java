
package com.example.acm.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.acm.entity.Problem;
import com.example.acm.mapper.ProblemMapper;
import com.example.acm.service.ProblemService;

/** 
 * 每日一题服务
 *
 * @author guanyiting
 * @date 2019-04-15 22:12:06
 */
@Service("problemService")
@Transactional
public class ProblemServiceImpl implements ProblemService {
	
	@Autowired
	private ProblemMapper  problemMapper;	
    
    /** 
     * 添加每日一题
     * 
     * @param problem 每日一题
     * @version v1.0
     * @author guanyiting
     * @date 2019-04-15 22:12:06
     */
    public void addProblem(Problem problem){
        problemMapper.addProblem(problem);
    }    
    
    /** 
     * 添加每日一题列表
     * 
     * @param list 每日一题列表
     * @version v1.0
     * @author guanyiting
     * @date 2019-04-15 22:12:06
     */
    public void addProblemList(List<Problem> list){
        problemMapper.addProblemList(list);
    }
    
    /** 
     * 删除每日一题
     * 
     * @param problemId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-04-15 22:12:06
     */
    public void deleteProblemByProblemId(Long problemId){
        problemMapper.deleteProblemByProblemId(problemId);
    }
    
    /**
     * 修改每日一题
     * 
     * @param problemId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-04-15 22:12:06
     */
    public void updateProblemByProblemId(Long problemId,Problem problem){
        problemMapper.updateProblemByProblemId(problemId,problem);
    }
    
    /** 
     * 根据获取每日一题
     * 
     * @param problemId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-04-15 22:12:06
     */
    public Problem getProblemByProblemId(Long problemId){
        return problemMapper.getProblemByProblemId(problemId);        
    }
    
    /**
     * 查询每日一题
     * 
     * @param problemId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-04-15 22:12:06
     */
    public List<Problem> findProblemListByProblemId(Long problemId){
        return problemMapper.findProblemListByProblemId(problemId);
    }
    
    /**
     * 查询每日一题
     * 
     * @param problemId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-04-15 22:12:06
     */
    public List<Map<String,Object>> findProblem2MapListByProblemId(Long problemId){
        return problemMapper.findProblem2MapListByProblemId(problemId);
    }

    /**
     * 获取每日一题列表
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-04-15 22:12:06
     */
    public List<Problem> findProblemList(){
        return problemMapper.findProblemList();
    }
    
    /**
     * 获取每日一题列表
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-04-15 22:12:06
     */
    public List<Map<String,Object>> findProblem2MapList(){
        return problemMapper.findProblem2MapList();
    }
    
    /**
     * 根据查询条件获取每日一题个数
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-04-15 22:12:06
     */
    public Integer countProblemListByQuery(Map<String, Object> map){
    	return problemMapper.countProblemListByQuery(map);
    }
    
    /**
     * 根据查询条件获取每日一题列表
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-04-15 22:12:06
     */
    public List<Problem> findProblemListByQuery(Map<String, Object> map){
    	return problemMapper.findProblemListByQuery(map);
    }
    
    /**
     * 根据查询条件获取每日一题个数(Map)
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-04-15 22:12:06
     */
    public Integer countProblemMapListByQuery(Map<String, Object> map){
    	return problemMapper.countProblemMapListByQuery(map);
    }
    
    /**
     * 根据查询条件获取每日一题列表(Map)
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-04-15 22:12:06
     */
    public List<Map<String,Object>> findProblemMapListByQuery(Map<String, Object> map){
    	return problemMapper.findProblemMapListByQuery(map);
    }

}
