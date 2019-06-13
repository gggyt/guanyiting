
package com.example.acm.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.acm.entity.Lecture;
import com.example.acm.mapper.LectureMapper;
import com.example.acm.service.LectureService;

/** 
 * 讲座服务
 *
 * @author guanyiting
 * @date 2019-03-11 22:19:56
 */
@Service("lectureService")
@Transactional
public class LectureServiceImpl implements LectureService {
	
	@Autowired
	private LectureMapper  lectureMapper;	
    
    /** 
     * 添加讲座
     * 
     * @param lecture 讲座
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-11 22:19:56
     */
    public void addLecture(Lecture lecture){
        lectureMapper.addLecture(lecture);
    }    
    
    /** 
     * 添加讲座列表
     * 
     * @param list 讲座列表
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-11 22:19:56
     */
    public void addLectureList(List<Lecture> list){
        lectureMapper.addLectureList(list);
    }
    
    /** 
     * 删除讲座
     * 
     * @param lectureId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-11 22:19:56
     */
    public void deleteLectureByLectureId(Long lectureId){
        lectureMapper.deleteLectureByLectureId(lectureId);
    }
    
    /**
     * 修改讲座
     * 
     * @param lectureId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-11 22:19:56
     */
    public void updateLectureByLectureId(Long lectureId,Lecture lecture){
        lectureMapper.updateLectureByLectureId(lectureId,lecture);
    }
    
    /** 
     * 根据获取讲座
     * 
     * @param lectureId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-11 22:19:56
     */
    public Lecture getLectureByLectureId(Long lectureId){
        return lectureMapper.getLectureByLectureId(lectureId);        
    }
    
    /**
     * 查询讲座
     * 
     * @param lectureId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-11 22:19:56
     */
    public List<Lecture> findLectureListByLectureId(Long lectureId){
        return lectureMapper.findLectureListByLectureId(lectureId);
    }
    
    /**
     * 查询讲座
     * 
     * @param lectureId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-11 22:19:56
     */
    public List<Map<String,Object>> findLecture2MapListByLectureId(Long lectureId){
        return lectureMapper.findLecture2MapListByLectureId(lectureId);
    }

    /**
     * 获取讲座列表
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-11 22:19:56
     */
    public List<Lecture> findLectureList(){
        return lectureMapper.findLectureList();
    }
    
    /**
     * 获取讲座列表
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-11 22:19:56
     */
    public List<Map<String,Object>> findLecture2MapList(){
        return lectureMapper.findLecture2MapList();
    }
    
    /**
     * 根据查询条件获取讲座个数
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-11 22:19:56
     */
    public Integer countLectureListByQuery(Map<String, Object> map){
    	return lectureMapper.countLectureListByQuery(map);
    }
    
    /**
     * 根据查询条件获取讲座列表
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-11 22:19:56
     */
    public List<Lecture> findLectureListByQuery(Map<String, Object> map){
    	return lectureMapper.findLectureListByQuery(map);
    }
    
    /**
     * 根据查询条件获取讲座个数(Map)
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-11 22:19:56
     */
    public Integer countLectureMapListByQuery(Map<String, Object> map){
    	return lectureMapper.countLectureMapListByQuery(map);
    }
    
    /**
     * 根据查询条件获取讲座列表(Map)
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-03-11 22:19:56
     */
    public List<Map<String,Object>> findLectureMapListByQuery(Map<String, Object> map){
    	return lectureMapper.findLectureMapListByQuery(map);
    }


    public List<Map<String,Object>> findLectureMapListByUser(Map<String, Object> map) {
        return lectureMapper.findLectureMapListByUser(map);
    }
}
