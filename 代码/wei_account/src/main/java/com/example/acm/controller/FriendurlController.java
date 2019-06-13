package com.example.acm.controller;

import com.example.acm.common.ResultBean;
import com.example.acm.common.ResultCode;
import com.example.acm.entity.User;
import com.example.acm.service.deal.FriendurlDealService;
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
 * Created by ggg on 2019/3/24.
 */
@Controller
@RequestMapping("/friendurl")
public class FriendurlController extends BaseController{
    private static final Logger LOG = LoggerFactory.getLogger(FriendurlController.class);

    @Autowired
    private FriendurlDealService friendurlDealService;

    @RequestMapping("/addFriendurl")
    @ResponseBody
    public ResultBean addFriendurl(@RequestParam(value = "friendurlTitle", required = true) String friendurlTitle,
                                   @RequestParam(value = "friendurlBody", required = true) String friendurlBody,
                                   HttpServletRequest request, HttpServletResponse response) {
        try{
            User user = getUserIdFromSession(request);
            if (user == null) {
                return new ResultBean(ResultCode.SESSION_OUT);
            }
            return friendurlDealService.addFriendurl(user, friendurlTitle, friendurlBody);
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error(e.getMessage());
            return new ResultBean(ResultCode.SYSTEM_FAILED);
        }
    }

    @RequestMapping("/selectFriendurl")
    @ResponseBody
    public ResultBean selectCompetition(@RequestParam(value = "friendurlTitle", defaultValue = "", required = false) String friendurlTitle,
                                        @RequestParam(value="aOrs", defaultValue = "1", required = false) int aOrs,
                                        @RequestParam(value = "pageNum", defaultValue = "1", required = false) int pageNum,
                                        @RequestParam(value="order", defaultValue = "createDate", required = false) String order,
                                        @RequestParam(value="pageSize", defaultValue="10", required = false) int pageSize,
                                        HttpServletRequest request, HttpServletResponse response) {
        User user = getUserIdFromSession(request);
        if (user == null) {
            //return new ResultBean(ResultCode.SESSION_OUT);
            user = new User();
            user.setUserId(2);
        }

        return friendurlDealService.selectFriendurl(friendurlTitle, aOrs,  order,  pageNum,  pageSize);

    }
    @RequestMapping("/updateFriendurl")
    @ResponseBody
    public ResultBean updateFriendurl(@RequestParam(value = "friendurlId", required = true) long friendurlId,
                                      @RequestParam(value = "friendurlTitle", required = true) String friendurlTitle,
                                      @RequestParam(value = "friendurlBody", required = true) String friendurlBody,
                                        HttpServletRequest request, HttpServletResponse response) {
        User user = getUserIdFromSession(request);
        if (user == null) {
            //return new ResultBean(ResultCode.SESSION_OUT);
            user = new User();
            user.setUserId(2);
        }

        return friendurlDealService.updateFriendurl(user, friendurlId, friendurlTitle, friendurlBody);

    }

    @RequestMapping("/detailFriendurl")
    @ResponseBody
    public ResultBean detailFriendurl(@RequestParam(value = "friendurlId", required = true) long friendurlId,
                                      HttpServletRequest request, HttpServletResponse response) {
        User user = getUserIdFromSession(request);
        if (user == null) {
            //return new ResultBean(ResultCode.SESSION_OUT);
            user = new User();
            user.setUserId(2);
        }

        return friendurlDealService.detailFriendurl(user, friendurlId);

    }

    @RequestMapping("/deleteFriendurl")
    @ResponseBody
    public ResultBean deleteFriendurl(@RequestParam(value = "friendurlId", required = true) long friendurlId,
                                      HttpServletRequest request, HttpServletResponse response) {
        User user = getUserIdFromSession(request);
        if (user == null) {
            user = new User();
            user.setUserId(2);
            //return new ResultBean(ResultCode.SESSION_OUT);
        }

        return friendurlDealService.deleteFriendurl(user, friendurlId);

    }
}
