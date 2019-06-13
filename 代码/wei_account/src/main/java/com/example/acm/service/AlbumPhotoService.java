
package com.example.acm.service;

import java.util.List;
import java.util.Map;

import com.example.acm.entity.AlbumPhoto;

/** 
 * 照片服务接口
 *
 * @author guanyiting
 * @date 2019-02-02 14:09:43
 */
public interface AlbumPhotoService{
    
    /** 
     * 添加照片
     * 
     * @param albumPhoto 照片
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-02 14:09:43
     */
    public void addAlbumPhoto(AlbumPhoto albumPhoto);
    
    /** 
     * 添加照片列表
     * 
     * @param list 照片列表
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-02 14:09:43
     */
    public void addAlbumPhotoList(List<AlbumPhoto> list);      
    
    /** 
     * 删除照片
     * 
     * @param apId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-02 14:09:43
     */
    public void deleteAlbumPhotoByApId(Integer apId);
    
    /**
     * 修改照片
     * 
     * @param apId  
     * @param albumPhoto 照片信息
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-02 14:09:43
     */
    public void updateAlbumPhotoByApId(Integer apId, AlbumPhoto albumPhoto);
    
    /** 
     * 根据获取照片
     * 
     * @param apId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-02 14:09:43
     */
    public AlbumPhoto getAlbumPhotoByApId(Integer apId);

    /**
     * 查询照片
     * 
     * @param apId  
     * @param albumPhoto 照片信息
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-02 14:09:43
     */
    public List<AlbumPhoto> findAlbumPhotoListByApId(Integer apId);
    
    /**
     * 查询照片
     * 
     * @param apId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-02 14:09:43
     */
    public List<Map<String,Object>> findAlbumPhoto2MapListByApId(Integer apId);

    /**
     * 获取照片列表
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-02 14:09:43
     */
    public List<AlbumPhoto> findAlbumPhotoList();
    
    /**
     * 获取照片列表
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-02 14:09:43
     */
    public List<Map<String,Object>> findAlbumPhoto2MapList();
    
    /**
     * 根据查询条件获取照片个数
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-02 14:09:43
     */
    public Integer countAlbumPhotoListByQuery(Map<String, Object> map);
    
    /**
     * 根据查询条件获取照片列表
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-02 14:09:43
     */
    public List<AlbumPhoto> findAlbumPhotoListByQuery(Map<String, Object> map);
    
    /**
     * 根据查询条件获取照片个数(Map)
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-02 14:09:43
     */
    public Integer countAlbumPhotoMapListByQuery(Map<String, Object> map);
    
    /**
     * 根据查询条件获取照片列表(Map)
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-02 14:09:43
     */
    public List<Map<String,Object>> findAlbumPhotoMapListByQuery(Map<String, Object> map);
	
}
