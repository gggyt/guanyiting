
package com.example.acm.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.acm.entity.Album;
import com.example.acm.mapper.AlbumMapper;
import com.example.acm.service.AlbumService;

/** 
 * 相册服务
 *
 * @author guanyiting
 * @date 2019-01-29 21:50:36
 */
@Service("albumService")
@Transactional
public class AlbumServiceImpl implements AlbumService {
	
	@Autowired
	private AlbumMapper  albumMapper;	
    
    /** 
     * 添加相册
     * 
     * @param album 相册
     * @version v1.0
     * @author guanyiting
     * @date 2019-01-29 21:50:36
     */
    public void addAlbum(Album album){
        albumMapper.addAlbum(album);
    }    
    
    /** 
     * 添加相册列表
     * 
     * @param list 相册列表
     * @version v1.0
     * @author guanyiting
     * @date 2019-01-29 21:50:36
     */
    public void addAlbumList(List<Album> list){
        albumMapper.addAlbumList(list);
    }
    
    /** 
     * 删除相册
     * 
     * @param albumId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-01-29 21:50:36
     */
    public void deleteAlbumByAlbumId(Integer albumId){
        albumMapper.deleteAlbumByAlbumId(albumId);
    }
    
    /**
     * 修改相册
     * 
     * @param albumId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-01-29 21:50:36
     */
    public void updateAlbumByAlbumId(Integer albumId,Album album){
        albumMapper.updateAlbumByAlbumId(albumId,album);
    }
    
    /** 
     * 根据获取相册
     * 
     * @param albumId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-01-29 21:50:36
     */
    public Album getAlbumByAlbumId(Integer albumId){
        return albumMapper.getAlbumByAlbumId(albumId);        
    }
    
    /**
     * 查询相册
     * 
     * @param albumId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-01-29 21:50:36
     */
    public List<Album> findAlbumListByAlbumId(Integer albumId){
        return albumMapper.findAlbumListByAlbumId(albumId);
    }
    
    /**
     * 查询相册
     * 
     * @param albumId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-01-29 21:50:36
     */
    public List<Map<String,Object>> findAlbum2MapListByAlbumId(Integer albumId){
        return albumMapper.findAlbum2MapListByAlbumId(albumId);
    }

    /**
     * 获取相册列表
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-01-29 21:50:36
     */
    public List<Album> findAlbumList(){
        return albumMapper.findAlbumList();
    }
    
    /**
     * 获取相册列表
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-01-29 21:50:36
     */
    public List<Map<String,Object>> findAlbum2MapList(){
        return albumMapper.findAlbum2MapList();
    }
    
    /**
     * 根据查询条件获取相册个数
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-01-29 21:50:36
     */
    public Integer countAlbumListByQuery(Map<String, Object> map){
    	return albumMapper.countAlbumListByQuery(map);
    }
    
    /**
     * 根据查询条件获取相册列表
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-01-29 21:50:36
     */
    public List<Album> findAlbumListByQuery(Map<String, Object> map){
    	return albumMapper.findAlbumListByQuery(map);
    }
    
    /**
     * 根据查询条件获取相册个数(Map)
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-01-29 21:50:36
     */
    public Integer countAlbumMapListByQuery(Map<String, Object> map){
    	return albumMapper.countAlbumMapListByQuery(map);
    }
    
    /**
     * 根据查询条件获取相册列表(Map)
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-01-29 21:50:36
     */
    public List<Map<String,Object>> findAlbumMapListByQuery(Map<String, Object> map){
    	return albumMapper.findAlbumMapListByQuery(map);
    }

}
