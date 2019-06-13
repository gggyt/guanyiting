package com.example.acm.service.deal.Impl;

import com.example.acm.common.ResultBean;
import com.example.acm.common.ResultCode;
import com.example.acm.entity.Album;
import com.example.acm.entity.Photo;
import com.example.acm.entity.User;
import com.example.acm.mapper.PhotoMapper;
import com.example.acm.service.AlbumService;
import com.example.acm.service.PhotoService;
import com.example.acm.service.deal.AlbumDealService;
import com.example.acm.utils.DateUtils;
import com.example.acm.utils.ListPage;
import com.example.acm.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ggg on 2019/1/29.
 */
@Service
public class AlbumDealImpl implements AlbumDealService{

    private static final Logger LOG = LoggerFactory.getLogger(AlbumDealImpl.class);

    @Autowired
    private AlbumService albumService;
    @Autowired
    private PhotoService photoService;
    public ResultBean addAlbum(User user, String albumName, String description, int isPublic){
        try{
            Map<String, Object> mapSearch = new HashMap<>();
            mapSearch.put("albumNameE", albumName);
            mapSearch.put("createUser", user.getUserId());
            mapSearch.put("isEffective", 1);
            List<Album> albums = albumService.findAlbumListByQuery(mapSearch);
            if(albums.size()>0) {
                return new ResultBean(ResultCode.PARAM_ERROR, "相册名已存在");
            }
            Album album = new Album();
            album.setAlbumName(albumName);
            album.setCreateUser(user.getUserId());
            album.setCreateDate(new Date());
            album.setDescription(description);
            album.setIsPublic(isPublic);
            album.setIsEffective(1);

            albumService.addAlbum(album);
            return new ResultBean(ResultCode.SUCCESS);

        } catch (Exception e) {
            LOG.error(e.getMessage());
            return new ResultBean(ResultCode.OTHER_FAIL, "系统异常");
        }
    }

    public ResultBean selectAlbum(User user, String albumName, int aOrs, int pageNum,String order, int pageSize, int isPublic){
        try {
            Map<String, Object> map = new HashMap<>();
            if (pageNum < 0) {
                return new ResultBean(ResultCode.PARAM_ERROR, "页码不能小于0");
            }
            if (pageSize < 0) {
                return new ResultBean(ResultCode.PARAM_ERROR, "一页展示数量不能小于0");
            }
            int start = (pageNum - 1) * pageSize;
            int limit = pageSize;
            map.put("albumName", albumName);
            map.put("start", start);
            map.put("limit", limit);
            map.put("order", order);
          //  map.put("isPublic", isPublic);
            if (isPublic!=1) {
                map.put("isPublic", 1);
            }
            if (aOrs == 1) {
                map.put("aOrS", "DESC");
            } else {
                map.put("aOrS", "ASC");
            }
            map.put("isEffective", 1);
            List<Map<String, Object>> list = albumService.findAlbumMapListByQuery(map);

            int allNum = albumService.countAlbumMapListByQuery(map);
            if (list.size() >0) {
                for (Map<String, Object> mapTemp : list) {
                    mapTemp.put("createDate", DateUtils.convDateToStr((Date) mapTemp.get("createDate"), "yyyy/MM/dd HH:mm:ss"));
                    if ((long)mapTemp.get("coverPhotoId")==-1) {
                        mapTemp.put("coverPhotoId", "http://localhost:9999/image/photo/null.jpg");
                    }else {
                        Long photoId = (Long)mapTemp.get("coverPhotoId");

                        List<Photo> photos = photoService.findPhotoListByPhotoId(photoId);
                        if (photos.size()==0) {
                            mapTemp.put("coverPhotoId", "http://localhost:9999/image/photo/null.jpg");
                        } else{
                            mapTemp.put("coverPhotoId", photos.get(0).getPhotoUrl());
                        }
                    }
                }
            }
           ListPage<List<Map<String, Object>>> listPage = ListPage.createListPage(pageNum, pageSize, allNum, list);

            return new ResultBean(ResultCode.SUCCESS, listPage);
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error(e.getMessage());
            return new ResultBean(ResultCode.OTHER_FAIL);
        }
    }

    public ResultBean deleteAlbum(User user, int albumId){
        try {
            List<Album> albums = albumService.findAlbumListByAlbumId(albumId);
            if (albums.size()==0) {
                return new ResultBean(ResultCode.PARAM_ERROR, "相册不存在");
            }
            Album album = albums.get(0);
            if (album.getIsEffective()==0) {
                return new ResultBean(ResultCode.PARAM_ERROR, "相册不存在");
            }
            album.setIsEffective(0);
            albumService.updateAlbumByAlbumId(albumId, album);

            return new ResultBean(ResultCode.SUCCESS);
        } catch (Exception e){
            e.printStackTrace();
            LOG.error(e.getMessage());
            return new ResultBean(ResultCode.SYSTEM_FAILED);
        }
    }

    @Autowired
    private PhotoMapper photoMapper;

    public ResultBean AlbumDetail(User user, int albumId){
        try {
            List<Album> albums = albumService.findAlbumListByAlbumId(albumId);
            if (albums.size()==0) {
                return new ResultBean(ResultCode.PARAM_ERROR, "相册不存在");
            }
            Album album = albums.get(0);
            if (album.getIsEffective()==0) {
                return new ResultBean(ResultCode.PARAM_ERROR, "相册不存在");
            }
            Map<String, Object> map = new HashMap<>();
            map.put("albumId", albumId);
            if (album.getCoverPhotoId()==-1) {
                map.put("coverPhotoId", "http://localhost:9999/image/photo/null.jpg");
            }
            else {
                List<Photo> list = photoService.findPhotoListByPhotoId(album.getCoverPhotoId());
                map.put("coverPhotoId", list.get(0).getPhotoUrl());
            }
            map.put("description", album.getDescription());
            map.put("albumName", album.getAlbumName());
            return new ResultBean(ResultCode.SUCCESS, map);
        } catch (Exception e){
            e.printStackTrace();
            LOG.error(e.getMessage());
            return new ResultBean(ResultCode.SYSTEM_FAILED);
        }
    }
}
