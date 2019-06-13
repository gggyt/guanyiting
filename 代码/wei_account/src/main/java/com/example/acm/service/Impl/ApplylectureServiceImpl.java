
package com.example.acm.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.acm.entity.Applylecture;
import com.example.acm.mapper.ApplylectureMapper;
import com.example.acm.service.ApplylectureService;

/** 
 * 讲座-添加服务
 *
 * @author guanyiting
 * @date 2019-03-23 16:18:31
 */
@Service("applylectureService")
@Transactional
public class ApplylectureServiceImpl implements ApplylectureService {
	
	@Autowired
	private ApplylectureMapper  applylectureMapper;	
    
    /** 
     * 添加讲座-添加
     * 
     * @param applylecture 讲座-添加
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-23 16:18:31
     */
    public void addApplylecture(Applylecture applylecture){
        applylectureMapper.addApplylecture(applylecture);
    }    
    
    /** 
     * 添加讲座-添加列表
     * 
     * @param list 讲座-添加列表
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-23 16:18:31
     */
    public void addApplylectureList(List<Applylecture> list){
        applylectureMapper.addApplylectureList(list);
    }
    
    /** 
     * 删除讲座-添加
     * 
     * @param applyLectureId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-23 16:18:31
     */
    public void deleteApplylectureByApplyLectureId(Long applyLectureId){
        applylectureMapper.deleteApplylectureByApplyLectureId(applyLectureId);
    }
    
    /**
     * 修改讲座-添加
     * 
     * @param applyLectureId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-23 16:18:31
     */
    public void updateApplylectureByApplyLectureId(Long applyLectureId,Applylecture applylecture){
        applylectureMapper.updateApplylectureByApplyLectureId(applyLectureId,applylecture);
    }
    
    /** 
     * 根据获取讲座-添加
     * 
     * @param applyLectureId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-23 16:18:31
     */
    public Applylecture getApplylectureByApplyLectureId(Long applyLectureId){
        return applylectureMapper.getApplylectureByApplyLectureId(applyLectureId);        
    }
    
    /**
     * 查询讲座-添加
     * 
     * @param applyLectureId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-23 16:18:31
     */
    public List<Applylecture> findApplylectureListByApplyLectureId(Long applyLectureId){
        return applylectureMapper.findApplylectureListByApplyLectureId(applyLectureId);
    }
    
    /**
     * 查询讲座-添加
     * 
     * @param lectureId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-23 16:18:31
     */
    public List<Applylecture> findApplylectureListByLectureId(Long lectureId){
        return applylectureMapper.findApplylectureListByLectureId(lectureId);
    }

    /**
     * 查询讲座-添加
     * 
     * @param applyLectureId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-23 16:18:31
     */
    public List<Map<String,Object>> findApplylecture2MapListByApplyLectureId(Long applyLectureId){
        return applylectureMapper.findApplylecture2MapListByApplyLectureId(applyLectureId);
    }

    /**
     * 查询讲座-添加
     * 
     * @param lectureId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-23 16:18:31
     */
    public List<Map<String,Object>> findApplylecture2MapListByLectureId(Long lectureId){
        return applylectureMapper.findApplylecture2MapListByLectureId(lectureId);
    }

    /**
     * 获取讲座-添加列表
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-23 16:18:31
     */
    public List<Applylecture> findApplylectureList(){
        return applylectureMapper.findApplylectureList();
    }
    
    /**
     * 获取讲座-添加列表
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-23 16:18:31
     */
    public List<Map<String,Object>> findApplylecture2MapList(){
        return applylectureMapper.findApplylecture2MapList();
    }
    
    /**
     * 根据查询条件获取讲座-添加个数
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-23 16:18:31
     */
    public Integer countApplylectureListByQuery(Map<String, Object> map){
    	return applylectureMapper.countApplylectureListByQuery(map);
    }
    
    /**
     * 根据查询条件获取讲座-添加列表
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-23 16:18:31
     */
    public List<Applylecture> findApplylectureListByQuery(Map<String, Object> map){
    	return applylectureMapper.findApplylectureListByQuery(map);
    }
    
    /**
     * 根据查询条件获取讲座-添加个数(Map)
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-23 16:18:31
     */
    public Integer countApplylectureMapListByQuery(Map<String, Object> map){
    	return applylectureMapper.countApplylectureMapListByQuery(map);
    }
    
    /**
     * 根据查询条件获取讲座-添加列表(Map)
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-23 16:18:31
     */
    public List<Map<String,Object>> findApplylectureMapListByQuery(Map<String, Object> map){
    	return applylectureMapper.findApplylectureMapListByQuery(map);
    }

}
