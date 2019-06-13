
package com.example.acm.service;

import java.util.List;
import java.util.Map;

import com.example.acm.entity.Applylecture;

/** 
 * 讲座-添加服务接口
 *
 * @author guanyiting
 * @date 2019-03-23 16:18:31
 */
public interface ApplylectureService{
    
    /** 
     * 添加讲座-添加
     * 
     * @param applylecture 讲座-添加
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-23 16:18:31
     */
    public void addApplylecture(Applylecture applylecture);
    
    /** 
     * 添加讲座-添加列表
     * 
     * @param list 讲座-添加列表
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-23 16:18:31
     */
    public void addApplylectureList(List<Applylecture> list);      
    
    /** 
     * 删除讲座-添加
     * 
     * @param applyLectureId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-23 16:18:31
     */
    public void deleteApplylectureByApplyLectureId(Long applyLectureId);
    
    /**
     * 修改讲座-添加
     * 
     * @param applyLectureId  
     * @param applylecture 讲座-添加信息
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-23 16:18:31
     */
    public void updateApplylectureByApplyLectureId(Long applyLectureId, Applylecture applylecture);
    
    /** 
     * 根据获取讲座-添加
     * 
     * @param applyLectureId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-23 16:18:31
     */
    public Applylecture getApplylectureByApplyLectureId(Long applyLectureId);

    /**
     * 查询讲座-添加
     * 
     * @param applyLectureId  
     * @param applylecture 讲座-添加信息
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-23 16:18:31
     */
    public List<Applylecture> findApplylectureListByApplyLectureId(Long applyLectureId);
    
    /**
     * 查询讲座-添加
     * 
     * @param lectureId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-23 16:18:31
     */
    public List<Applylecture> findApplylectureListByLectureId(Long lectureId);

    /**
     * 查询讲座-添加
     * 
     * @param applyLectureId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-23 16:18:31
     */
    public List<Map<String,Object>> findApplylecture2MapListByApplyLectureId(Long applyLectureId);

    /**
     * 查询讲座-添加
     * 
     * @param lectureId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-23 16:18:31
     */
    public List<Map<String,Object>> findApplylecture2MapListByLectureId(Long lectureId);

    /**
     * 获取讲座-添加列表
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-23 16:18:31
     */
    public List<Applylecture> findApplylectureList();
    
    /**
     * 获取讲座-添加列表
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-23 16:18:31
     */
    public List<Map<String,Object>> findApplylecture2MapList();
    
    /**
     * 根据查询条件获取讲座-添加个数
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-23 16:18:31
     */
    public Integer countApplylectureListByQuery(Map<String, Object> map);
    
    /**
     * 根据查询条件获取讲座-添加列表
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-23 16:18:31
     */
    public List<Applylecture> findApplylectureListByQuery(Map<String, Object> map);
    
    /**
     * 根据查询条件获取讲座-添加个数(Map)
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-23 16:18:31
     */
    public Integer countApplylectureMapListByQuery(Map<String, Object> map);
    
    /**
     * 根据查询条件获取讲座-添加列表(Map)
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-23 16:18:31
     */
    public List<Map<String,Object>> findApplylectureMapListByQuery(Map<String, Object> map);
	
}
