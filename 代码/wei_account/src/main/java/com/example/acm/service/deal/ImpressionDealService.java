package com.example.acm.service.deal;

import com.example.acm.common.ResultBean;
import com.example.acm.entity.User;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by ggg on 2019/4/11.
 */
public interface ImpressionDealService {

    ResultBean addImpression(int userId, String impressionTitle, User user);

    ResultBean deleteImpression(long impressionId, User user);

    ResultBean selectImpression(User user, String name, int aOrs, String order, int pageNum, int pageSizer);

    ResultBean userImpression(int userId, User user);

    ResultBean agreeImpression(long impressionId, User user);

    ResultBean interestImpression(int userId, User user);
}
