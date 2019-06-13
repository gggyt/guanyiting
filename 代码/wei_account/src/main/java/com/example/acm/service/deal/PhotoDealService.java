package com.example.acm.service.deal;

import com.example.acm.common.ResultBean;
import com.example.acm.entity.User;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by ggg on 2019/2/2.
 */
public interface PhotoDealService {

    ResultBean addPhoto(User user, int albumId, MultipartFile file);

    ResultBean selectPhoto(User user, int albumId, int aOrs, int pageNum, String order, int pageSize);

    ResultBean selectUserPhoto(User user, int userId, int aOrs, int pageNum, String order, int pageSize);

    ResultBean deletePhoto(User user, long photoId);

    ResultBean beCover(User user, int albumId, long photoId);
}
