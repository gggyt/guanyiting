package com.example.acm.controller;

import com.example.acm.common.ResultBean;
import com.example.acm.common.ResultCode;
import com.example.acm.entity.Photo;
import com.example.acm.entity.User;
import com.example.acm.service.PhotoService;
import com.example.acm.service.deal.PhotoDealService;
import com.example.acm.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Date;
import java.util.UUID;

/**
 * Created by ggg on 2019/2/1.
 */
@Controller
@RequestMapping("/photo")
public class PhotoController extends BaseController{

    private static final Logger LOG = LoggerFactory.getLogger(PhotoController.class);

    @Autowired
    public PhotoDealService photoDealService;

    @RequestMapping("/addPhoto")
    @ResponseBody
    public ResultBean addPhoto(@RequestParam(value = "albumId", required = true) int albumId,
                               @RequestParam(value = "myFileName", required = true) MultipartFile file,
                               HttpServletRequest request, HttpServletResponse response) {
        try {
            User user = getUserIdFromSession(request);
            if (user == null) {
                 return new ResultBean(ResultCode.SESSION_OUT);
            }
            if (user.getAuth()<1) {
                return new ResultBean(ResultCode.ACCOUNT_NOTAUTH);
            }

            return photoDealService.addPhoto(user, albumId, file);
        } catch (Exception e) {
            LOG.error(e.getMessage());
            return new ResultBean(ResultCode.SYSTEM_FAILED);
        }
    }

    @RequestMapping("/selectPhoto")
    @ResponseBody
    public ResultBean selectPhoto(@RequestParam(value = "albumId", required = true) int albumId,
                                  @RequestParam(value="aOrs", defaultValue = "1", required = false) int aOrs,
                                  @RequestParam(value = "pageNum", defaultValue = "1", required = false) int pageNum,
                                  @RequestParam(value="order", defaultValue = "createDate", required = false) String order,
                                  @RequestParam(value="pageSize", defaultValue="10", required = false) int pageSize,
                               HttpServletRequest request, HttpServletResponse response) {
        try {
            User user = getUserIdFromSession(request);
            if (user == null) {
                return new ResultBean(ResultCode.SESSION_OUT);
            }


            return photoDealService.selectPhoto(user, albumId, aOrs, pageNum, order, pageSize);
        } catch (Exception e) {
            LOG.error(e.getMessage());
            return new ResultBean(ResultCode.SYSTEM_FAILED);
        }
    }

    @RequestMapping("/deletePhoto")
    @ResponseBody
    public ResultBean deletePhoto(@RequestParam(value = "photoId", required = true) long photoId,
                                  HttpServletRequest request, HttpServletResponse response) {
        try {
            User user = getUserIdFromSession(request);
            if (user == null) {
                return new ResultBean(ResultCode.SESSION_OUT);
            }


            return photoDealService.deletePhoto(user, photoId);
        } catch (Exception e) {
            LOG.error(e.getMessage());
            return new ResultBean(ResultCode.SYSTEM_FAILED);
        }
    }
    @RequestMapping("/beCover")
    @ResponseBody
    public ResultBean beCover(@RequestParam(value = "albumId", required = true)int albumId,
                                @RequestParam(value = "photoId", required = true) long photoId,
                                  HttpServletRequest request, HttpServletResponse response) {
        try {
            User user = getUserIdFromSession(request);
            if (user == null) {
                return new ResultBean(ResultCode.SESSION_OUT);
            }


            return photoDealService.beCover(user, albumId, photoId);
        } catch (Exception e) {
            LOG.error(e.getMessage());
            return new ResultBean(ResultCode.SYSTEM_FAILED);
        }
    }

    @RequestMapping("/selectUserPhoto")
    @ResponseBody
    public ResultBean selectUserPhoto(@RequestParam(value = "userId", defaultValue = "-1", required = false) int userId,
                                  @RequestParam(value="aOrs", defaultValue = "1", required = false) int aOrs,
                                  @RequestParam(value = "pageNum", defaultValue = "1", required = false) int pageNum,
                                  @RequestParam(value="order", defaultValue = "createDate", required = false) String order,
                                  @RequestParam(value="pageSize", defaultValue="10", required = false) int pageSize,
                                  HttpServletRequest request, HttpServletResponse response) {
        try {
            User user = getUserIdFromSession(request);
            if (user == null) {
                return new ResultBean(ResultCode.SESSION_OUT);
            }


            return photoDealService.selectUserPhoto(user, userId, aOrs, pageNum, order, pageSize);
        } catch (Exception e) {
            LOG.error(e.getMessage());
            return new ResultBean(ResultCode.SYSTEM_FAILED);
        }
    }
}
