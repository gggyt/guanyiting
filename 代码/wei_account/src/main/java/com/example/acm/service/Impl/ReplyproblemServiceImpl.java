
package com.example.acm.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.acm.entity.Replyproblem;
import com.example.acm.mapper.ReplyproblemMapper;
import com.example.acm.service.ReplyproblemService;

/** 
 * 每日一题题解服务
 *
 * @author guanyiting
 * @date 2019-04-16 11:15:34
 */
@Service("replyproblemService")
@Transactional
public class ReplyproblemServiceImpl implements ReplyproblemService {
	
	@Autowired
	private ReplyproblemMapper  replyproblemMapper;	
    
    /** 
     * 添加每日一题题解
     * 
     * @param replyproblem 每日一题题解
     * @version v1.0
     * @author guanyiting
     * @date 2019-04-16 11:15:34
     */
    public void addReplyproblem(Replyproblem replyproblem){
        replyproblemMapper.addReplyproblem(replyproblem);
    }    
    
    /** 
     * 添加每日一题题解列表
     * 
     * @param list 每日一题题解列表
     * @version v1.0
     * @author guanyiting
     * @date 2019-04-16 11:15:34
     */
    public void addReplyproblemList(List<Replyproblem> list){
        replyproblemMapper.addReplyproblemList(list);
    }
    
    /** 
     * 删除每日一题题解
     * 
     * @param problemansId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-04-16 11:15:34
     */
    public void deleteReplyproblemByProblemansId(Long problemansId){
        replyproblemMapper.deleteReplyproblemByProblemansId(problemansId);
    }
    
    /**
     * 修改每日一题题解
     * 
     * @param problemansId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-04-16 11:15:34
     */
    public void updateReplyproblemByProblemansId(Long problemansId,Replyproblem replyproblem){
        replyproblemMapper.updateReplyproblemByProblemansId(problemansId,replyproblem);
    }
    
    /** 
     * 根据获取每日一题题解
     * 
     * @param problemansId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-04-16 11:15:34
     */
    public Replyproblem getReplyproblemByProblemansId(Long problemansId){
        return replyproblemMapper.getReplyproblemByProblemansId(problemansId);        
    }
    
    /**
     * 查询每日一题题解
     * 
     * @param problemansId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-04-16 11:15:34
     */
    public List<Replyproblem> findReplyproblemListByProblemansId(Long problemansId){
        return replyproblemMapper.findReplyproblemListByProblemansId(problemansId);
    }
    
    /**
     * 查询每日一题题解
     * 
     * @param problemansId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-04-16 11:15:34
     */
    public List<Map<String,Object>> findReplyproblem2MapListByProblemansId(Long problemansId){
        return replyproblemMapper.findReplyproblem2MapListByProblemansId(problemansId);
    }

    /**
     * 获取每日一题题解列表
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-04-16 11:15:34
     */
    public List<Replyproblem> findReplyproblemList(){
        return replyproblemMapper.findReplyproblemList();
    }
    
    /**
     * 获取每日一题题解列表
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-04-16 11:15:34
     */
    public List<Map<String,Object>> findReplyproblem2MapList(){
        return replyproblemMapper.findReplyproblem2MapList();
    }
    
    /**
     * 根据查询条件获取每日一题题解个数
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-04-16 11:15:34
     */
    public Integer countReplyproblemListByQuery(Map<String, Object> map){
    	return replyproblemMapper.countReplyproblemListByQuery(map);
    }
    
    /**
     * 根据查询条件获取每日一题题解列表
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-04-16 11:15:34
     */
    public List<Replyproblem> findReplyproblemListByQuery(Map<String, Object> map){
    	return replyproblemMapper.findReplyproblemListByQuery(map);
    }
    
    /**
     * 根据查询条件获取每日一题题解个数(Map)
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-04-16 11:15:34
     */
    public Integer countReplyproblemMapListByQuery(Map<String, Object> map){
    	return replyproblemMapper.countReplyproblemMapListByQuery(map);
    }
    
    /**
     * 根据查询条件获取每日一题题解列表(Map)
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-04-16 11:15:34
     */
    public List<Map<String,Object>> findReplyproblemMapListByQuery(Map<String, Object> map){
    	return replyproblemMapper.findReplyproblemMapListByQuery(map);
    }

}
