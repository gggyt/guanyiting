package com.example.acm.service.deal.Impl;

import com.example.acm.common.ResultBean;
import com.example.acm.common.ResultCode;
import com.example.acm.common.SysConst;
import com.example.acm.entity.Friendurl;
import com.example.acm.entity.User;
import com.example.acm.service.FriendurlService;
import com.example.acm.service.deal.FriendurlDealService;
import com.example.acm.utils.DateUtils;
import com.example.acm.utils.ListPage;
import com.example.acm.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ggg on 2019/3/24.
 */
@Service
public class FriendurlDealImpl implements FriendurlDealService{

    @Autowired
    private FriendurlService friendurlService;

    public ResultBean addFriendurl(User user, String friendurlTitle, String friendurlBody){

        if (!StringUtils.isConnect(friendurlBody)) {
            return new ResultBean(ResultCode.PARAM_ERROR, "网址不正确");
        }
        Friendurl friendurl = new Friendurl();
        friendurl.setFriendTitle(friendurlTitle);
        friendurl.setFriendBody(friendurlBody);
        friendurl.setCreateUser(user.getUserId().longValue());
        friendurl.setCreateDate(new Date());
        friendurl.setUpdateUser(user.getUserId().longValue());
        friendurl.setUpdateDate(new Date());
        friendurl.setIsEffective(SysConst.LIVE);

        friendurlService.addFriendurl(friendurl);

        return new ResultBean(ResultCode.SUCCESS);
    }

    public ResultBean selectFriendurl(String friendurlTitle, int aOrs, String order, int pageNum, int pageSize){
        Map<String, Object> map = new HashMap<>();
        if (pageNum < 0) {
            return new ResultBean(ResultCode.PARAM_ERROR, "页码不能小于0");
        }
        if (pageSize < 0) {
            return new ResultBean(ResultCode.PARAM_ERROR, "一页展示数量不能小于0");
        }
        int start = (pageNum - 1) * pageSize;
        int limit = pageSize;
        map.put("friendTitle", friendurlTitle);
        map.put("start", start);
        map.put("limit", limit);
        map.put("order", order);
        if (aOrs == 1) {
            map.put("aOrS", "DESC");
        } else {
            map.put("aOrS", "ASC");
        }
        map.put("isEffective", SysConst.LIVE);
        List<Map<String, Object>> list = friendurlService.findFriendurlMapListByQuery(map);

        if (list.size() >0) {
            for (Map<String, Object> mapTemp : list) {
                mapTemp.put("createDate", DateUtils.convDateToStr((Date) mapTemp.get("createDate"), "yyyy-MM-dd HH:mm:ss"));
                mapTemp.put("updateDate", DateUtils.convDateToStr((Date) mapTemp.get("updateDate"), "yyyy-MM-dd HH:mm:ss"));
            }
        }

        int allNum = friendurlService.countFriendurlMapListByQuery(map);

        ListPage<List<Map<String, Object>>> listPage = ListPage.createListPage(pageNum, pageSize, allNum, list);

        return new ResultBean(ResultCode.SUCCESS, listPage);
    }

    public ResultBean updateFriendurl(User user, long friendurlId, String friendurlTitle, String friendurlBody) {
        List<Friendurl> friendurls = friendurlService.findFriendurlListByFriendurlId(friendurlId);
        if (friendurls.size()<1) {
            return new ResultBean(ResultCode.PARAM_ERROR, "不存在该友链");
        }
        Friendurl friendurl = friendurls.get(0);

        friendurl.setFriendTitle(friendurlTitle);
        friendurl.setFriendBody(friendurlBody);

        friendurlService.updateFriendurlByFriendurlId(friendurlId, friendurl);

        return new ResultBean(ResultCode.SUCCESS);
    }

    public ResultBean detailFriendurl(User user, long friendurlId) {
        List<Friendurl> friendurls = friendurlService.findFriendurlListByFriendurlId(friendurlId);
        if (friendurls.size()<1) {
            return new ResultBean(ResultCode.PARAM_ERROR, "不存在该友链");
        }
        Friendurl friendurl = friendurls.get(0);

        return new ResultBean(ResultCode.SUCCESS, friendurl);
    }

    public ResultBean deleteFriendurl(User user, long friendurlId){
        List<Friendurl> friendurls = friendurlService.findFriendurlListByFriendurlId(friendurlId);
        if (friendurls.size()<1) {
            return new ResultBean(ResultCode.PARAM_ERROR, "不存在该友链");
        }
        Friendurl friendurl = friendurls.get(0);

        friendurl.setUpdateUser(user.getUserId().longValue());
        friendurl.setUpdateDate(new Date());
        friendurl.setIsEffective(SysConst.NOT_LIVE);

        friendurlService.updateFriendurlByFriendurlId(friendurlId, friendurl);

        return new ResultBean(ResultCode.SUCCESS);
    }
}
