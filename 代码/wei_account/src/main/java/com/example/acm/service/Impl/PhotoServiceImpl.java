
package com.example.acm.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.acm.entity.Photo;
import com.example.acm.mapper.PhotoMapper;
import com.example.acm.service.PhotoService;

/** 
 * 照片服务
 *
 * @author guanyiting
 * @date 2019-02-01 22:52:23
 */
@Service("photoService")
@Transactional
public class PhotoServiceImpl implements PhotoService {
	
	@Autowired
	private PhotoMapper  photoMapper;	
    
    /** 
     * 添加照片
     * 
     * @param photo 照片
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-01 22:52:23
     */
    public void addPhoto(Photo photo){
        photoMapper.addPhoto(photo);
    }    
    
    /** 
     * 添加照片列表
     * 
     * @param list 照片列表
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-01 22:52:23
     */
    public void addPhotoList(List<Photo> list){
        photoMapper.addPhotoList(list);
    }
    
    /** 
     * 删除照片
     * 
     * @param photoId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-01 22:52:23
     */
    public void deletePhotoByPhotoId(Long photoId){
        photoMapper.deletePhotoByPhotoId(photoId);
    }
    
    /**
     * 修改照片
     * 
     * @param photoId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-01 22:52:23
     */
    public void updatePhotoByPhotoId(Long photoId,Photo photo){
        photoMapper.updatePhotoByPhotoId(photoId,photo);
    }
    
    /** 
     * 根据获取照片
     * 
     * @param photoId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-01 22:52:23
     */
    public Photo getPhotoByPhotoId(Long photoId){
        return photoMapper.getPhotoByPhotoId(photoId);        
    }
    
    /**
     * 查询照片
     * 
     * @param photoId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-01 22:52:23
     */
    public List<Photo> findPhotoListByPhotoId(Long photoId){
        return photoMapper.findPhotoListByPhotoId(photoId);
    }
    
    /**
     * 查询照片
     * 
     * @param photoId  
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-01 22:52:23
     */
    public List<Map<String,Object>> findPhoto2MapListByPhotoId(Long photoId){
        return photoMapper.findPhoto2MapListByPhotoId(photoId);
    }

    /**
     * 获取照片列表
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-01 22:52:23
     */
    public List<Photo> findPhotoList(){
        return photoMapper.findPhotoList();
    }
    
    /**
     * 获取照片列表
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-01 22:52:23
     */
    public List<Map<String,Object>> findPhoto2MapList(){
        return photoMapper.findPhoto2MapList();
    }
    
    /**
     * 根据查询条件获取照片个数
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-01 22:52:23
     */
    public Integer countPhotoListByQuery(Map<String, Object> map){
    	return photoMapper.countPhotoListByQuery(map);
    }
    
    /**
     * 根据查询条件获取照片列表
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-01 22:52:23
     */
    public List<Photo> findPhotoListByQuery(Map<String, Object> map){
    	return photoMapper.findPhotoListByQuery(map);
    }
    
    /**
     * 根据查询条件获取照片个数(Map)
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-01 22:52:23
     */
    public Integer countPhotoMapListByQuery(Map<String, Object> map){
    	return photoMapper.countPhotoMapListByQuery(map);
    }
    
    /**
     * 根据查询条件获取照片列表(Map)
     * 
     * @version v1.0
     * @author guanyiting
     * @date 2019-02-01 22:52:23
     */
    public List<Map<String,Object>> findPhotoMapListByQuery(Map<String, Object> map){
    	return photoMapper.findPhotoMapListByQuery(map);
    }

}
