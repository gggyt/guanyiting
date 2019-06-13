package com.example.acm.service.deal.Impl;

import com.example.acm.common.ResultBean;
import com.example.acm.common.ResultCode;
import com.example.acm.common.SysConst;
import com.example.acm.entity.Album;
import com.example.acm.entity.AlbumPhoto;
import com.example.acm.entity.Photo;
import com.example.acm.entity.User;
import com.example.acm.mapper.PhotoMapper;
import com.example.acm.service.AlbumPhotoService;
import com.example.acm.service.AlbumService;
import com.example.acm.service.PhotoService;
import com.example.acm.service.deal.PhotoDealService;
import com.example.acm.utils.DateUtils;
import com.example.acm.utils.ListPage;
import com.example.acm.utils.UUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.*;

/**
 * Created by ggg on 2019/2/2.
 */
@Service
public class PhotoDealImpl implements PhotoDealService{

    private static final Logger LOG = LoggerFactory.getLogger(PhotoDealImpl.class);

    @Autowired
    public PhotoService photoService;
    @Autowired
    public AlbumPhotoService albumPhotoService;
    @Autowired
    public PhotoMapper photoMapper;
    @Autowired
    public AlbumService albumService;

    public ResultBean addPhoto(User user, int albumId, MultipartFile file){
        try {
            System.out.println("--开始");
            if (file.isEmpty()) {
                System.out.println("文件为空空");
            }
            String fileName = file.getOriginalFilename();  // 文件名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
            if (!suffixName.endsWith("jpg")&&!suffixName.endsWith("png")) {
                return new ResultBean(ResultCode.PARAM_ERROR, "请上传图片");
            }
            String filePath = "D://pic/photo/"; // 上传后的路径
            fileName = UUID.randomUUID() + suffixName; // 新文件名 File dest = new File(filePath + fileName);
            File dest = new File(filePath + fileName);
            System.out.println(filePath + fileName);
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            file.transferTo(dest);
            Long bigInteger = new Long(UUIDUtil.getNumUUID());
            Photo photo = new Photo();
            photo.setPhotoId(bigInteger);
            photo.setPhotoName(DateUtils.convDateToStr(new Date(), "yyyy-MM-dd"));
            photo.setCreateUser(user.getUserId());
            photo.setCreateDate(new Date());
            photo.setIsPublic(1);
            photo.setIsEffective(1);
            String url = "http://"+ SysConst.localhost+"/image/photo/";
            photo.setPhotoUrl(url + fileName);
            photoService.addPhoto(photo);

            AlbumPhoto albumPhoto = new AlbumPhoto();
            albumPhoto.setAlbumId(albumId);
            albumPhoto.setPhotoId(bigInteger);
            albumPhoto.setCreateUser(user.getUserId());
            albumPhoto.setCreateDate(new Date());
            albumPhoto.setIsEffective(1);
            albumPhotoService.addAlbumPhoto(albumPhoto);


            return new ResultBean(ResultCode.SUCCESS);
        }catch (Exception e) {
            LOG.error(e.getMessage());

            return new ResultBean(ResultCode.SYSTEM_FAILED);
        }
    }

    public ResultBean selectPhoto(User user, int albumId, int aOrs, int pageNum, String order, int pageSize){
        try {
            Map<String , Object> map = new HashMap<>();
            if (pageNum < 1) {
                //return new ResultBean(ResultCode.PARAM_ERROR, "页码不能小于1");
                pageNum = 1;
            }
            if (pageSize < 0) {
                return new ResultBean(ResultCode.PARAM_ERROR, "一页展示数量不能小于0");
            }
            int start = (pageNum - 1) * pageSize;
            int limit = pageSize;
            map.put("start", start);
            map.put("limit", limit);
            map.put("order", order);
            if (aOrs == 1) {
                map.put("aOrS", "DESC");
            } else {
                map.put("aOrS", "ASC");
            }
            map.put("isEffective", 1);
            map.put("albumId", albumId);
            List<Map<String, Object>> list = photoMapper.findPhotoMapListByAlbumId(map);
            if (list.size() >0) {
                for (Map<String, Object> mapTemp : list) {
                    mapTemp.put("createDate", DateUtils.convDateToStr((Date) mapTemp.get("createDate"), "yyyy/MM/dd HH:mm:ss"));
                }
            }

            int allNum = photoMapper.countPhotoMapListByAlbumId(map);

            ListPage<List<Map<String, Object>>> listPage = ListPage.createListPage(pageNum, pageSize, allNum, list);

            return new ResultBean(ResultCode.SUCCESS, listPage);
        } catch (Exception e) {
            LOG.error(e.getMessage());

            return new ResultBean(ResultCode.SYSTEM_FAILED);

        }
    }

    public ResultBean deletePhoto(User user, long photoId){
        try {
            List<Photo> photos = photoService.findPhotoListByPhotoId(photoId);
            if (photos.size()==0) {
                return new ResultBean(ResultCode.PARAM_ERROR, "相片不存在");
            }
            Photo photo = photos.get(0);
            if (photo.getIsEffective()==0) {
                return new ResultBean(ResultCode.PARAM_ERROR, "相片不存在");
            }
            photo.setIsEffective(0);
            photoService.updatePhotoByPhotoId(photoId, photo);

            return new ResultBean(ResultCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error(e.getMessage());

            return new ResultBean(ResultCode.SYSTEM_FAILED);

        }
    }
    public  ResultBean beCover(User user, int albumId, long photoId){
        try {
            List<Album> albums = albumService.findAlbumListByAlbumId(albumId);
            if (albums.size()==0) {
                return new ResultBean(ResultCode.PARAM_ERROR, "相册不存在");
            }
            Album album = albums.get(0);
            if (album.getIsEffective()==0) {
                return new ResultBean(ResultCode.PARAM_ERROR, "相册不存在");
            }
            List<Photo> photos = photoService.findPhotoListByPhotoId(photoId);
            if (photos.size()==0) {
                return new ResultBean(ResultCode.PARAM_ERROR, "相片不存在");
            }
            Photo photo = photos.get(0);
            if (photo.getIsEffective()==0) {
                return new ResultBean(ResultCode.PARAM_ERROR, "相片不存在");
            }

            album.setCoverPhotoId(photoId);
            albumService.updateAlbumByAlbumId(albumId, album);

            return new ResultBean(ResultCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error(e.getMessage());
            return new ResultBean(ResultCode.SYSTEM_FAILED);
        }
    }

    public ResultBean selectUserPhoto(User user, int userId, int aOrs, int pageNum, String order, int pageSize){
        try {
            Map<String , Object> map = new HashMap<>();
            if (pageNum < 0) {
                return new ResultBean(ResultCode.PARAM_ERROR, "页码不能小于0");
            }
            if (pageSize < 0) {
                return new ResultBean(ResultCode.PARAM_ERROR, "一页展示数量不能小于0");
            }
            int start = (pageNum - 1) * pageSize;
            int limit = pageSize;
            map.put("start", start);
            map.put("limit", limit);
            map.put("order", order);
            if (aOrs == 1) {
                map.put("aOrS", "DESC");
            } else {
                map.put("aOrS", "ASC");
            }
            map.put("isEffective", 1);
            map.put("createUser", userId);
            if (userId==-1) {
                map.put("createUser", user.getUserId());
            }
            List<Map<String, Object>> list = photoMapper.findPhotoMapListByAlbumId(map);
            if (list.size() >0) {
                for (Map<String, Object> mapTemp : list) {
                    mapTemp.put("createDate", DateUtils.convDateToStr((Date) mapTemp.get("createDate"), "yyyy/MM/dd HH:mm:ss"));
                }
            }

            int allNum = photoMapper.countPhotoMapListByAlbumId(map);

            ListPage<List<Map<String, Object>>> listPage = ListPage.createListPage(pageNum, pageSize, allNum, list);

            return new ResultBean(ResultCode.SUCCESS, listPage);
        } catch (Exception e) {
            LOG.error(e.getMessage());

            return new ResultBean(ResultCode.SYSTEM_FAILED);

        }
    }
}
