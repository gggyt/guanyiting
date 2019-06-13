
package com.example.acm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.example.acm.entity.Album;

/** 
 * 相册数据操作接口
 *
 * @author guanyiting
 * @date 2019-01-29 21:50:36
 */
public interface AlbumMapper{
    
    /** 
     * 添加相册
     * 
     * @param album 相册
     * @version v1.0
     * @author guanyiting
     * @date 2019-01-29 21:50:36
     */
    public void addAlbum(@Param("album") Album album);
    
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
    public void deleteAlbumByAlbumId(@Param("albumId") Integer albumId);
    
    /**
     * 修改相册
     * 
     * @param albumId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-01-29 21:50:36
     */
    public void updateAlbumByAlbumId(@Param("albumId") Integer albumId, @Param("album") Album album);
    
    /** 
     * 根据获取相册
     * 
     * @param albumId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-01-29 21:50:36
     */
    public Album getAlbumByAlbumId(@Param("albumId") Integer albumId);
    
    /**
     * 查询相册
     * 
     * @param albumId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-01-29 21:50:36
     */
    public List<Album> findAlbumListByAlbumId(@Param("albumId") Integer albumId);
    
    /**
     * 查询相册
     * 
     * @param albumId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-01-29 21:50:36
     */
    public List<Map<String,Object>> findAlbum2MapListByAlbumId(@Param("albumId") Integer albumId);

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
    public Integer countAlbumListByQuery(@Param("map") Map<String, Object> map);
    
    /**
     * 根据查询条件获取相册列表
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-01-29 21:50:36
     */
    public List<Album> findAlbumListByQuery(@Param("map") Map<String, Object> map);
    
    /**
     * 根据查询条件获取相册个数(Map)
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-01-29 21:50:36
     */
    public Integer countAlbumMapListByQuery(@Param("map") Map<String, Object> map);
    
    /**
     * 根据查询条件获取相册列表(Map)
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-01-29 21:50:36
     */
    public List<Map<String,Object>> findAlbumMapListByQuery(@Param("map") Map<String, Object> map);
	
}
