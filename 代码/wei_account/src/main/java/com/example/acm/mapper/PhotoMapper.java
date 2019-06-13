
package com.example.acm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.example.acm.entity.Photo;

/** 
 * 照片数据操作接口
 *
 * @author guanyiting
 * @date 2019-02-02 13:41:21
 */
public interface PhotoMapper{
    
    /** 
     * 添加照片
     * 
     * @param photo 照片
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-02 13:41:21
     */
    public void addPhoto(@Param("photo") Photo photo);
    
    /** 
     * 添加照片列表
     * 
     * @param list 照片列表
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-02 13:41:21
     */
    public void addPhotoList(List<Photo> list);       
    
    /** 
     * 删除照片
     * 
     * @param photoId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-02 13:41:21
     */
    public void deletePhotoByPhotoId(@Param("photoId") Long photoId);
    
    /**
     * 修改照片
     * 
     * @param photoId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-02 13:41:21
     */
    public void updatePhotoByPhotoId(@Param("photoId") Long photoId, @Param("photo") Photo photo);
    
    /** 
     * 根据获取照片
     * 
     * @param photoId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-02 13:41:21
     */
    public Photo getPhotoByPhotoId(@Param("photoId") Long photoId);
    
    /**
     * 查询照片
     * 
     * @param photoId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-02 13:41:21
     */
    public List<Photo> findPhotoListByPhotoId(@Param("photoId") Long photoId);
    
    /**
     * 查询照片
     * 
     * @param photoId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-02 13:41:21
     */
    public List<Map<String,Object>> findPhoto2MapListByPhotoId(@Param("photoId") Long photoId);

    /**
     * 获取照片列表
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-02 13:41:21
     */
    public List<Photo> findPhotoList();
    
    /**
     * 获取照片列表
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-02 13:41:21
     */
    public List<Map<String,Object>> findPhoto2MapList();
    
    /**
     * 根据查询条件获取照片个数
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-02 13:41:21
     */
    public Integer countPhotoListByQuery(@Param("map") Map<String, Object> map);
    
    /**
     * 根据查询条件获取照片列表
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-02 13:41:21
     */
    public List<Photo> findPhotoListByQuery(@Param("map") Map<String, Object> map);
    
    /**
     * 根据查询条件获取照片个数(Map)
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-02 13:41:21
     */
    public Integer countPhotoMapListByQuery(@Param("map") Map<String, Object> map);
    
    /**
     * 根据查询条件获取照片列表(Map)
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-02 13:41:21
     */
    public List<Map<String,Object>> findPhotoMapListByQuery(@Param("map") Map<String, Object> map);

    public List<Map<String,Object>> findPhotoMapListByAlbumId(@Param("map") Map<String, Object> map);

    public int countPhotoMapListByAlbumId(@Param("map") Map<String, Object> map);
}
