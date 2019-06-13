
package com.example.acm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.example.acm.entity.Lecture;

/** 
 * 讲座数据操作接口
 *
 * @author guanyiting
 * @date 2019-03-11 22:19:56
 */
public interface LectureMapper{
    
    /** 
     * 添加讲座
     * 
     * @param lecture 讲座
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-11 22:19:56
     */
    public void addLecture(@Param("lecture") Lecture lecture);
    
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
    public void deleteLectureByLectureId(@Param("lectureId") Long lectureId);
    
    /**
     * 修改讲座
     * 
     * @param lectureId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-11 22:19:56
     */
    public void updateLectureByLectureId(@Param("lectureId") Long lectureId, @Param("lecture") Lecture lecture);
    
    /** 
     * 根据获取讲座
     * 
     * @param lectureId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-11 22:19:56
     */
    public Lecture getLectureByLectureId(@Param("lectureId") Long lectureId);
    
    /**
     * 查询讲座
     * 
     * @param lectureId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-11 22:19:56
     */
    public List<Lecture> findLectureListByLectureId(@Param("lectureId") Long lectureId);
    
    /**
     * 查询讲座
     * 
     * @param lectureId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-11 22:19:56
     */
    public List<Map<String,Object>> findLecture2MapListByLectureId(@Param("lectureId") Long lectureId);

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
    public Integer countLectureListByQuery(@Param("map") Map<String, Object> map);
    
    /**
     * 根据查询条件获取讲座列表
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-11 22:19:56
     */
    public List<Lecture> findLectureListByQuery(@Param("map") Map<String, Object> map);
    
    /**
     * 根据查询条件获取讲座个数(Map)
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-11 22:19:56
     */
    public Integer countLectureMapListByQuery(@Param("map") Map<String, Object> map);
    
    /**
     * 根据查询条件获取讲座列表(Map)
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-11 22:19:56
     */
    public List<Map<String,Object>> findLectureMapListByQuery(@Param("map") Map<String, Object> map);


    public List<Map<String,Object>> findLectureMapListByUser(@Param("map") Map<String, Object> map);
	
}
