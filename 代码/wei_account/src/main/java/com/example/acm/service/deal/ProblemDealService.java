package com.example.acm.service.deal;

import com.example.acm.common.ResultBean;
import com.example.acm.entity.User;

/**
 * Created by ggg on 2019/4/15.
 */
public interface ProblemDealService {

    ResultBean addProblem(String problemTitle, String problemBody, String myAns, User user);

    ResultBean updateProblem(long problemId, String problemTitle, String problemBody,  User user);

    ResultBean addAns(User user, long problemId, String ans, int my);

    ResultBean replyProblem(User user, long problemId, long p_replyproblemId ,String ansBody);

    ResultBean problemDetail(long problemId, User user);

    ResultBean newProblemDetail();

    ResultBean deleteProblem(long problemId, User user);

    ResultBean selectProblem(User user, String problemTitle, int aOrs, int pageNum,String order, int pageSize, int my);

    ResultBean selectReply(User user, long problemId, int aOrs, int pageNum,String order, int pageSize);

    ResultBean ansDetail(long ansId);

    ResultBean deleteAns(long ansId);
}
