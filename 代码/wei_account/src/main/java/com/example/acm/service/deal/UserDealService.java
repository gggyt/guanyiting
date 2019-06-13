package com.example.acm.service.deal;

import com.example.acm.common.ResultBean;
import com.example.acm.entity.User;

/**
 * Created by ggg on 2018/11/25.
 */
public interface UserDealService {

    public ResultBean webLogin(String username, String password);

    public ResultBean register(String mobile, String username, String number, String password, String openId, String image);

    public ResultBean selectUsers(User user, String name, int aOrs, String order, int pageNum, int pageSize);

    public ResultBean changeAuth(User user, int userId, int auth);

    public ResultBean userInfo(User user, int userId);

    public ResultBean updateUserImage(User user, int userId, String image);

    public ResultBean updateUserInfo(User user, int userId, String username, String realname, String password, String mobile,
                                     long studentId, int grade, int classNum);
}