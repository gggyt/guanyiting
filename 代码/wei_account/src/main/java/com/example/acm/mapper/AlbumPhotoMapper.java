
package com.example.acm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.example.acm.entity.AlbumPhoto;

/** 
 * 照片数据操作接口
 *
 * @author guanyiting
 * @date 2019-02-02 14:09:43
 */
public interface AlbumPhotoMapper{
    
    /** 
     * 添加照片
     * 
     * @param albumPhoto 照片
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-02 14:09:43
     */
    public void addAlbumPhoto(@Param("albumPhoto") AlbumPhoto albumPhoto);
    
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
    public void deleteAlbumPhotoByApId(@Param("apId") Integer apId);
    
    /**
     * 修改照片
     * 
     * @param apId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-02 14:09:43
     */
    public void updateAlbumPhotoByApId(@Param("apId") Integer apId, @Param("albumPhoto") AlbumPhoto albumPhoto);
    
    /** 
     * 根据获取照片
     * 
     * @param apId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-02 14:09:43
     */
    public AlbumPhoto getAlbumPhotoByApId(@Param("apId") Integer apId);
    
    /**
     * 查询照片
     * 
     * @param apId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-02 14:09:43
     */
    public List<AlbumPhoto> findAlbumPhotoListByApId(@Param("apId") Integer apId);
    
    /**
     * 查询照片
     * 
     * @param apId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-02 14:09:43
     */
    public List<Map<String,Object>> findAlbumPhoto2MapListByApId(@Param("apId") Integer apId);

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
    public Integer countAlbumPhotoListByQuery(@Param("map") Map<String, Object> map);
    
    /**
     * 根据查询条件获取照片列表
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-02 14:09:43
     */
    public List<AlbumPhoto> findAlbumPhotoListByQuery(@Param("map") Map<String, Object> map);
    
    /**
     * 根据查询条件获取照片个数(Map)
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-02 14:09:43
     */
    public Integer countAlbumPhotoMapListByQuery(@Param("map") Map<String, Object> map);
    
    /**
     * 根据查询条件获取照片列表(Map)
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-02 14:09:43
     */
    public List<Map<String,Object>> findAlbumPhotoMapListByQuery(@Param("map") Map<String, Object> map);
	
}
