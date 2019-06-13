package com.example.acm.service.deal;

import com.example.acm.common.ResultBean;
import com.example.acm.entity.User;

/**
 * Created by ggg on 2019/3/24.
 */
public interface FriendurlDealService {

    ResultBean addFriendurl(User user, String friendurlTitle, String friendurlBody);

    ResultBean selectFriendurl(String friendurlTitle, int aOrs, String order, int pageNum, int pageSize);

    ResultBean updateFriendurl(User user, long friendurlId, String friendurlTitle, String friendurlBody);

    ResultBean detailFriendurl(User user, long friendurlId);

    ResultBean deleteFriendurl(User user, long friendurlId);
}
