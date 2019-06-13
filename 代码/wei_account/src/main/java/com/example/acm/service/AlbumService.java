
package com.example.acm.service;

import java.util.List;
import java.util.Map;

import com.example.acm.entity.Album;

/** 
 * 相册服务接口
 *
 * @author guanyiting
 * @date 2019-01-29 21:50:36
 */
public interface AlbumService{
    
    /** 
     * 添加相册
     * 
     * @param album 相册
     * @version v1.0
     * @author guanyiting
     * @date 2019-01-29 21:50:36
     */
    public void addAlbum(Album album);
    
    /** 
     * 添加相册列表
     * 
     * @param list 相册列表
     * @version v1.0
     * @author guanyiting
     * @date 2019-01-29 21:50:36
     */
    public void addAlbumList(List<Album> list);      
    
    /** 
     * 删除相册
     * 
     * @param albumId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-01-29 21:50:36
     */
    public void deleteAlbumByAlbumId(Integer albumId);
    
    /**
     * 修改相册
     * 
     * @param albumId  
     * @param album 相册信息
     * @version v1.0
     * @author guanyiting
     * @date 2019-01-29 21:50:36
     */
    public void updateAlbumByAlbumId(Integer albumId, Album album);
    
    /** 
     * 根据获取相册
     * 
     * @param albumId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-01-29 21:50:36
     */
    public Album getAlbumByAlbumId(Integer albumId);

    /**
     * 查询相册
     * 
     * @param albumId  
     * @param album 相册信息
     * @version v1.0
     * @author guanyiting
     * @date 2019-01-29 21:50:36
     */
    public List<Album> findAlbumListByAlbumId(Integer albumId);
    
    /**
     * 查询相册
     * 
     * @param albumId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-01-29 21:50:36
     */
    public List<Map<String,Object>> findAlbum2MapListByAlbumId(Integer albumId);

    /**
     * 获取相册列表
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-01-29 21:50:36
     */
    public List<Album> findAlbumList();
    
    /**
     * 获取相册列表
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-01-29 21:50:36
     */
    public List<Map<String,Object>> findAlbum2MapList();
    
    /**
     * 根据查询条件获取相册个数
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-01-29 21:50:36
     */
    public Integer countAlbumListByQuery(Map<String, Object> map);
    
    /**
     * 根据查询条件获取相册列表
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-01-29 21:50:36
     */
    public List<Album> findAlbumListByQuery(Map<String, Object> map);
    
    /**
     * 根据查询条件获取相册个数(Map)
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-01-29 21:50:36
     */
    public Integer countAlbumMapListByQuery(Map<String, Object> map);
    
    /**
     * 根据查询条件获取相册列表(Map)
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-01-29 21:50:36
     */
    public List<Map<String,Object>> findAlbumMapListByQuery(Map<String, Object> map);
	
}
