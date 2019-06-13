
package com.example.acm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.example.acm.entity.Applylecture;

/** 
 * 讲座-添加数据操作接口
 *
 * @author guanyiting
 * @date 2019-03-23 16:18:31
 */
public interface ApplylectureMapper{
    
    /** 
     * 添加讲座-添加
     * 
     * @param applylecture 讲座-添加
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-23 16:18:31
     */
    public void addApplylecture(@Param("applylecture") Applylecture applylecture);
    
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
    public void deleteApplylectureByApplyLectureId(@Param("applyLectureId") Long applyLectureId);
    
    /**
     * 修改讲座-添加
     * 
     * @param applyLectureId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-23 16:18:31
     */
    public void updateApplylectureByApplyLectureId(@Param("applyLectureId") Long applyLectureId, @Param("applylecture") Applylecture applylecture);
    
    /** 
     * 根据获取讲座-添加
     * 
     * @param applyLectureId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-23 16:18:31
     */
    public Applylecture getApplylectureByApplyLectureId(@Param("applyLectureId") Long applyLectureId);
    
    /**
     * 查询讲座-添加
     * 
     * @param applyLectureId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-23 16:18:31
     */
    public List<Applylecture> findApplylectureListByApplyLectureId(@Param("applyLectureId") Long applyLectureId);
    
    /**
     * 查询讲座-添加
     * 
     * @param lectureId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-23 16:18:31
     */
    public List<Applylecture> findApplylectureListByLectureId(@Param("lectureId") Long lectureId);

    /**
     * 查询讲座-添加
     * 
     * @param applyLectureId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-23 16:18:31
     */
    public List<Map<String,Object>> findApplylecture2MapListByApplyLectureId(@Param("applyLectureId") Long applyLectureId);

    /**
     * 查询讲座-添加
     * 
     * @param lectureId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-23 16:18:31
     */
    public List<Map<String,Object>> findApplylecture2MapListByLectureId(@Param("lectureId") Long lectureId);

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
    public Integer countApplylectureListByQuery(@Param("map") Map<String, Object> map);
    
    /**
     * 根据查询条件获取讲座-添加列表
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-23 16:18:31
     */
    public List<Applylecture> findApplylectureListByQuery(@Param("map") Map<String, Object> map);
    
    /**
     * 根据查询条件获取讲座-添加个数(Map)
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-23 16:18:31
     */
    public Integer countApplylectureMapListByQuery(@Param("map") Map<String, Object> map);
    
    /**
     * 根据查询条件获取讲座-添加列表(Map)
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-23 16:18:31
     */
    public List<Map<String,Object>> findApplylectureMapListByQuery(@Param("map") Map<String, Object> map);
	
}
