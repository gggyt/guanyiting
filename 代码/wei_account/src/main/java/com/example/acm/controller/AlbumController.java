package com.example.acm.controller;

import com.example.acm.common.ResultBean;
import com.example.acm.common.ResultCode;
import com.example.acm.entity.User;
import com.example.acm.service.deal.AlbumDealService;
import com.example.acm.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by ggg on 2019/1/29.
 */
@Controller
@RequestMapping("/album")
public class AlbumController extends BaseController{

    private static final Logger LOG = LoggerFactory.getLogger(AlbumController.class);

    @Autowired
    public AlbumDealService albumDealService;

    @RequestMapping("/addAlbum")
    @ResponseBody
    public ResultBean addAlbum(@RequestParam(value="albumName",required = true) String albumName,
                               @RequestParam(value = "description", defaultValue = "") String description,
                               @RequestParam(value = "isPublic", required = true) int isPublic,
                               HttpServletRequest request){
        try {
            User user = getUserIdFromSession(request);
            if (user == null) {
                return new ResultBean(ResultCode.SESSION_OUT);
            }
            if(StringUtils.isNull(albumName)) {
                return new ResultBean(ResultCode.PARAM_ERROR, "相册名不为空");
            }
            if (albumName.length()>20) {
                return new ResultBean(ResultCode.PARAM_ERROR, "相册名过长");
            }
            if (isPublic!=1 && isPublic!=0) {
                return new ResultBean(ResultCode.PARAM_ERROR, "相册权限错误");
            }
            if (description.length()>256) {
                return new ResultBean(ResultCode.PARAM_ERROR, "描述过长");
            }
            return albumDealService.addAlbum(user, albumName, description, isPublic);
        } catch (Exception e) {
            LOG.error(e.getMessage());
            return new ResultBean(ResultCode.OTHER_FAIL, "系统异常");
        }
    }
    @RequestMapping("/selectAlbum")
    @ResponseBody
    public ResultBean selectAlbum(@RequestParam(value="albumName", defaultValue = "",required = false) String albumName,
                                  @RequestParam(value="aOrs", defaultValue = "1", required = false) int aOrs,
                                  @RequestParam(value = "pageNum", defaultValue = "1", required = false) int pageNum,
                                  @RequestParam(value="order", defaultValue = "createDate", required = false) String order,
                                  @RequestParam(value="pageSize", defaultValue="10", required = false) int pageSize,
                                  @RequestParam(value="isPublic", defaultValue="1", required = false) int isPublic,
                                  HttpServletRequest request, HttpServletResponse response){
        try {
            User user = getUserIdFromSession(request);
            if (user == null) {
                return new ResultBean(ResultCode.SESSION_OUT);
            }

            return albumDealService.selectAlbum(user, albumName, aOrs, pageNum, order, pageSize, isPublic);
        } catch (Exception e) {
            LOG.error(e.getMessage());
            return new ResultBean(ResultCode.OTHER_FAIL, "系统异常");
        }
    }
    @RequestMapping("/deleteAlbum")
    @ResponseBody
    public ResultBean deleteAlbum(@RequestParam(value="albumId", required = true) int alnumId,
                                  HttpServletRequest request, HttpServletResponse response){
        try {
            User user = getUserIdFromSession(request);
            if (user == null) {
                return new ResultBean(ResultCode.SESSION_OUT);
            }

            return albumDealService.deleteAlbum(user, alnumId);
        } catch (Exception e) {
            LOG.error(e.getMessage());
            return new ResultBean(ResultCode.OTHER_FAIL, "系统异常");
        }
    }
    @RequestMapping("/AlbumDetail")
    @ResponseBody
    public ResultBean AlbumDetail(@RequestParam(value="albumId", required = true) int alnumId,
                                  HttpServletRequest request, HttpServletResponse response){
        try {
            User user = getUserIdFromSession(request);
            if (user == null) {
                return new ResultBean(ResultCode.SESSION_OUT);
            }

            return albumDealService.AlbumDetail(user, alnumId);
        } catch (Exception e) {
            LOG.error(e.getMessage());
            return new ResultBean(ResultCode.OTHER_FAIL, "系统异常");
        }
    }
}
