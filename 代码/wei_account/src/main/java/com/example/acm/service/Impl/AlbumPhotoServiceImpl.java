
package com.example.acm.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.acm.entity.AlbumPhoto;
import com.example.acm.mapper.AlbumPhotoMapper;
import com.example.acm.service.AlbumPhotoService;

/** 
 * 照片服务
 *
 * @author guanyiting
 * @date 2019-02-02 14:09:43
 */
@Service("albumPhotoService")
@Transactional
public class AlbumPhotoServiceImpl implements AlbumPhotoService {
	
	@Autowired
	private AlbumPhotoMapper  albumPhotoMapper;	
    
    /** 
     * 添加照片
     * 
     * @param albumPhoto 照片
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-02 14:09:43
     */
    public void addAlbumPhoto(AlbumPhoto albumPhoto){
        albumPhotoMapper.addAlbumPhoto(albumPhoto);
    }    
    
    /** 
     * 添加照片列表
     * 
     * @param list 照片列表
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-02 14:09:43
     */
    public void addAlbumPhotoList(List<AlbumPhoto> list){
        albumPhotoMapper.addAlbumPhotoList(list);
    }
    
    /** 
     * 删除照片
     * 
     * @param apId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-02 14:09:43
     */
    public void deleteAlbumPhotoByApId(Integer apId){
        albumPhotoMapper.deleteAlbumPhotoByApId(apId);
    }
    
    /**
     * 修改照片
     * 
     * @param apId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-02 14:09:43
     */
    public void updateAlbumPhotoByApId(Integer apId,AlbumPhoto albumPhoto){
        albumPhotoMapper.updateAlbumPhotoByApId(apId,albumPhoto);
    }
    
    /** 
     * 根据获取照片
     * 
     * @param apId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-02 14:09:43
     */
    public AlbumPhoto getAlbumPhotoByApId(Integer apId){
        return albumPhotoMapper.getAlbumPhotoByApId(apId);        
    }
    
    /**
     * 查询照片
     * 
     * @param apId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-02 14:09:43
     */
    public List<AlbumPhoto> findAlbumPhotoListByApId(Integer apId){
        return albumPhotoMapper.findAlbumPhotoListByApId(apId);
    }
    
    /**
     * 查询照片
     * 
     * @param apId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-02 14:09:43
     */
    public List<Map<String,Object>> findAlbumPhoto2MapListByApId(Integer apId){
        return albumPhotoMapper.findAlbumPhoto2MapListByApId(apId);
    }

    /**
     * 获取照片列表
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-02 14:09:43
     */
    public List<AlbumPhoto> findAlbumPhotoList(){
        return albumPhotoMapper.findAlbumPhotoList();
    }
    
    /**
     * 获取照片列表
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-02 14:09:43
     */
    public List<Map<String,Object>> findAlbumPhoto2MapList(){
        return albumPhotoMapper.findAlbumPhoto2MapList();
    }
    
    /**
     * 根据查询条件获取照片个数
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-02 14:09:43
     */
    public Integer countAlbumPhotoListByQuery(Map<String, Object> map){
    	return albumPhotoMapper.countAlbumPhotoListByQuery(map);
    }
    
    /**
     * 根据查询条件获取照片列表
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-02 14:09:43
     */
    public List<AlbumPhoto> findAlbumPhotoListByQuery(Map<String, Object> map){
    	return albumPhotoMapper.findAlbumPhotoListByQuery(map);
    }
    
    /**
     * 根据查询条件获取照片个数(Map)
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-02 14:09:43
     */
    public Integer countAlbumPhotoMapListByQuery(Map<String, Object> map){
    	return albumPhotoMapper.countAlbumPhotoMapListByQuery(map);
    }
    
    /**
     * 根据查询条件获取照片列表(Map)
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-02 14:09:43
     */
    public List<Map<String,Object>> findAlbumPhotoMapListByQuery(Map<String, Object> map){
    	return albumPhotoMapper.findAlbumPhotoMapListByQuery(map);
    }

}
