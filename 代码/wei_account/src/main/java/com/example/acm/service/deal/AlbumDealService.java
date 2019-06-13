package com.example.acm.service.deal;

import com.example.acm.common.ResultBean;
import com.example.acm.controller.AlbumController;
import com.example.acm.entity.User;
import org.springframework.stereotype.Service;

/**
 * Created by ggg on 2019/1/29.
 */
public interface AlbumDealService{

    ResultBean addAlbum(User user, String albumName, String description, int isPublic);

    ResultBean selectAlbum(User user, String albumName, int aOrs, int pageName,String order, int pageSize, int isPublic);

    ResultBean deleteAlbum(User user, int albumId);

    ResultBean AlbumDetail(User user, int albumId);
}
