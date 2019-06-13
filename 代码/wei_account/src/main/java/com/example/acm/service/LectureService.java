
package com.example.acm.service;

import java.util.List;
import java.util.Map;

import com.example.acm.entity.Lecture;

/** 
 * 讲座服务接口
 *
 * @author guanyiting
 * @date 2019-03-11 22:19:56
 */
public interface LectureService{
    
    /** 
     * 添加讲座
     * 
     * @param lecture 讲座
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-11 22:19:56
     */
    public void addLecture(Lecture lecture);
    
    /** 
     * 添加讲座列表
     * 
     * @param list 讲座列表
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-11 22:19:56
     */
    public void addLectureList(List<Lecture> list);      
    
    /** 
     * 删除讲座
     * 
     * @param lectureId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-11 22:19:56
     */
    public void deleteLectureByLectureId(Long lectureId);
    
    /**
     * 修改讲座
     * 
     * @param lectureId  
     * @param lecture 讲座信息
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-11 22:19:56
     */
    public void updateLectureByLectureId(Long lectureId, Lecture lecture);
    
    /** 
     * 根据获取讲座
     * 
     * @param lectureId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-11 22:19:56
     */
    public Lecture getLectureByLectureId(Long lectureId);

    /**
     * 查询讲座
     * 
     * @param lectureId  
     * @param lecture 讲座信息
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-11 22:19:56
     */
    public List<Lecture> findLectureListByLectureId(Long lectureId);
    
    /**
     * 查询讲座
     * 
     * @param lectureId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-11 22:19:56
     */
    public List<Map<String,Object>> findLecture2MapListByLectureId(Long lectureId);

    /**
     * 获取讲座列表
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-11 22:19:56
     */
    public List<Lecture> findLectureList();
    
    /**
     * 获取讲座列表
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-11 22:19:56
     */
    public List<Map<String,Object>> findLecture2MapList();
    
    /**
     * 根据查询条件获取讲座个数
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-11 22:19:56
     */
    public Integer countLectureListByQuery(Map<String, Object> map);
    
    /**
     * 根据查询条件获取讲座列表
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-11 22:19:56
     */
    public List<Lecture> findLectureListByQuery(Map<String, Object> map);
    
    /**
     * 根据查询条件获取讲座个数(Map)
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-11 22:19:56
     */
    public Integer countLectureMapListByQuery(Map<String, Object> map);
    
    /**
     * 根据查询条件获取讲座列表(Map)
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-11 22:19:56
     */
    public List<Map<String,Object>> findLectureMapListByQuery(Map<String, Object> map);

    public List<Map<String,Object>> findLectureMapListByUser(Map<String, Object> map);
	
}
