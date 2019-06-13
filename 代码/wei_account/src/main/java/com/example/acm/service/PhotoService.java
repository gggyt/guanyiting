
package com.example.acm.service;

import java.util.List;
import java.util.Map;

import com.example.acm.entity.Photo;

/** 
 * 照片服务接口
 *
 * @author guanyiting
 * @date 2019-02-01 22:52:23
 */
public interface PhotoService{
    
    /** 
     * 添加照片
     * 
     * @param photo 照片
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-01 22:52:23
     */
    public void addPhoto(Photo photo);
    
    /** 
     * 添加照片列表
     * 
     * @param list 照片列表
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-01 22:52:23
     */
    public void addPhotoList(List<Photo> list);      
    
    /** 
     * 删除照片
     * 
     * @param photoId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-01 22:52:23
     */
    public void deletePhotoByPhotoId(Long photoId);
    
    /**
     * 修改照片
     * 
     * @param photoId  
     * @param photo 照片信息
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-01 22:52:23
     */
    public void updatePhotoByPhotoId(Long photoId, Photo photo);
    
    /** 
     * 根据获取照片
     * 
     * @param photoId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-01 22:52:23
     */
    public Photo getPhotoByPhotoId(Long photoId);

    /**
     * 查询照片
     * 
     * @param photoId  
     * @param photo 照片信息
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-01 22:52:23
     */
    public List<Photo> findPhotoListByPhotoId(Long photoId);
    
    /**
     * 查询照片
     * 
     * @param photoId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-01 22:52:23
     */
    public List<Map<String,Object>> findPhoto2MapListByPhotoId(Long photoId);

    /**
     * 获取照片列表
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-01 22:52:23
     */
    public List<Photo> findPhotoList();
    
    /**
     * 获取照片列表
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-01 22:52:23
     */
    public List<Map<String,Object>> findPhoto2MapList();
    
    /**
     * 根据查询条件获取照片个数
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-01 22:52:23
     */
    public Integer countPhotoListByQuery(Map<String, Object> map);
    
    /**
     * 根据查询条件获取照片列表
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-01 22:52:23
     */
    public List<Photo> findPhotoListByQuery(Map<String, Object> map);
    
    /**
     * 根据查询条件获取照片个数(Map)
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-01 22:52:23
     */
    public Integer countPhotoMapListByQuery(Map<String, Object> map);
    
    /**
     * 根据查询条件获取照片列表(Map)
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-01 22:52:23
     */
    public List<Map<String,Object>> findPhotoMapListByQuery(Map<String, Object> map);
	
}
