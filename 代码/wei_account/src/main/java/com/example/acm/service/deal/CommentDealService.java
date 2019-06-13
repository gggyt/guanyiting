package com.example.acm.service.deal;

import com.example.acm.common.ResultBean;
import com.example.acm.entity.User;

/**
 * Created by ggg on 2019/2/9.
 */
public interface CommentDealService {

    ResultBean addComment(User user,long invitationId, long p_commentId, String commentBody);

    ResultBean deleteComment(User user,long commentId);

    ResultBean selectCommend(User user,long invitationId, long p_commentId, int aOrs, int pageNum, String order, int pageSize);
    ResultBean selectBackCommend(User user,long invitationId, long p_commentId, int aOrs, int pageNum, String order, int pageSize);

    ResultBean selectUserCommend(User user, long p_commentId, int aOrs, int pageNum, String order, int pageSize, int getMy);
}
