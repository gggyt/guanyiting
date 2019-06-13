package com.example.acm.controller;

import com.example.acm.common.ResultBean;
import com.example.acm.common.ResultCode;
import com.example.acm.common.SysConst;
import com.example.acm.entity.Follow;
import com.example.acm.entity.User;
import com.example.acm.service.FollowService;
import com.example.acm.utils.ListPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ggg on 2019/5/26.
 */
@Controller
@RequestMapping("/follow")
public class FollowController extends BaseController{

    @Autowired
    private FollowService followService;

    @RequestMapping("/addFollow")
    @ResponseBody
    public ResultBean addFollow(@RequestParam(value="beUserId", required=true) int beUserId,
                                HttpServletRequest request, HttpServletResponse response) {
        User user = getUserIdFromSession(request);
        if (user==null) {
            return new ResultBean(ResultCode.SESSION_OUT);
        }

        Map<String, Object> map = new HashMap<>();
        map.put("isEffective", SysConst.LIVE);
        map.put("beUserId", beUserId);
        map.put("createUser", user.getUserId());
        int count = followService.countFollowMapListByQuery(map);

        if (count>0) {
            return new ResultBean(ResultCode.PARAM_ERROR, "你已关注该用户");
        }

        Follow follow = new Follow();
        follow.setBeUserId(beUserId);
        follow.setCreateUser(user.getUserId());
        follow.setCreateDate(new Date());
        follow.setIsEffective(SysConst.LIVE);

        followService.addFollow(follow);

        return new ResultBean(ResultCode.SUCCESS, "关注成功");
    }

    @RequestMapping("/isFollow")
    @ResponseBody
    public ResultBean isFollow(@RequestParam(value="beUserId", required=true) int beUserId,
                                HttpServletRequest request, HttpServletResponse response) {
        User user = getUserIdFromSession(request);
        if (user==null) {
            return new ResultBean(ResultCode.SESSION_OUT);
        }

        Map<String, Object> map = new HashMap<>();
        map.put("isEffective", SysConst.LIVE);
        map.put("beUserId", beUserId);
        map.put("createUser", user.getUserId());
        int count = followService.countFollowMapListByQuery(map);

        if (count>0) {
            return new ResultBean(ResultCode.PARAM_ERROR, "你已关注该用户");
        }

        return new ResultBean(ResultCode.SUCCESS, "未关注");
    }

    @RequestMapping("/deleteFollow")
    @ResponseBody
    public ResultBean deleteFollow(@RequestParam(value="beUserId", required = true) int beUserId,
                                   HttpServletRequest request, HttpServletResponse response) {
        User user = getUserIdFromSession(request);
        if (user==null) {
            return new ResultBean(ResultCode.SESSION_OUT);
        }

        Map<String, Object> map = new HashMap<>();
        map.put("isEffective", SysConst.LIVE);
        map.put("beUserId", beUserId);
        map.put("createUser", user.getUserId());
        List<Follow> follows = followService.findFollowListByQuery(map);
        if (follows.size()==0) {
            return new ResultBean(ResultCode.PARAM_ERROR, "您未关注该用户");
        }
        Follow follow = follows.get(0);
        follow.setIsEffective(SysConst.NOT_LIVE);

        followService.updateFollowByFollowId(follow.getFollowId(), follow);
        return new ResultBean(ResultCode.SUCCESS);
    }

    //获取自己的关注列表
    @RequestMapping("/selectFollow")
    @ResponseBody
    public ResultBean selectFollow(@RequestParam(value="aOrs", defaultValue = "1", required = false) int aOrs,
                                   @RequestParam(value = "pageNum", defaultValue = "1", required = false) int pageNum,
                                   @RequestParam(value="order", defaultValue = "createDate", required = false) String order,
                                   @RequestParam(value="pageSize", defaultValue="10", required = false) int pageSize,
                                   @RequestParam(value = "getMy", defaultValue = "-1", required = false) int getMy,
                                   HttpServletRequest request, HttpServletResponse response) {
        User user = getUserIdFromSession(request);
        if (user==null) {
            return new ResultBean(ResultCode.SESSION_OUT);
        }

        Map<String, Object> map = new HashMap<>();
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
        map.put("createUser", user.getUserId());
        map.put("isEffective", SysConst.LIVE);

        List<Map<String, Object>> follows = followService.findFollowMapListByQuery(map);
        int allNum = followService.countFollowListByQuery(map);
        ListPage<List<Map<String, Object>>> listPage = ListPage.createListPage(pageNum, pageSize, allNum, follows);

        return new ResultBean(ResultCode.SUCCESS, listPage);
    }
}
