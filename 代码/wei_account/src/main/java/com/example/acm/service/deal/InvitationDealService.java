package com.example.acm.service.deal;

import com.example.acm.common.ResultBean;
import com.example.acm.entity.User;

/**
 * Created by ggg on 2019/2/5.
 */
public interface InvitationDealService {

    ResultBean addInvitation(User user, String invitationTitle, String invitationBody);

    ResultBean updateInvitation(User user, long invitationId, String invitationTitle, String invitationBody);

    ResultBean selectInvitation(User user, String invitationTitle, int aOrs, int pageNum, String order, int pageSize,int getMy);

    ResultBean invitationDetail(User user, long invitationId);

    ResultBean newInvitationDetail();

    ResultBean deleteInvitation(User user, long invitationId);

    ResultBean firstInvitation(User user, long invitationId, int isFirst);

    ResultBean greateInvitation(User user, long invitationId, int isFirst);
}
