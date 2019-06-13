package com.example.acm.service.deal.Impl;

import com.example.acm.common.ResultBean;
import com.example.acm.common.ResultCode;
import com.example.acm.common.SysConst;
import com.example.acm.entity.Problem;
import com.example.acm.entity.Replyproblem;
import com.example.acm.entity.User;
import com.example.acm.service.ProblemService;
import com.example.acm.service.ReplyproblemService;
import com.example.acm.service.deal.ProblemDealService;
import com.example.acm.utils.DateUtils;
import com.example.acm.utils.ListPage;
import com.example.acm.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ggg on 2019/4/15.
 */
@Service
@Transactional
public class ProblemDealImpl implements ProblemDealService {

    @Autowired
    private ProblemService problemService;
    @Autowired
    private ReplyproblemService replyproblemService;

    public ResultBean addProblem(String problemTitle, String problemBody, String myAns, User user) {

        Problem problem = new Problem();
        Long bigInteger = new Long(UUIDUtil.getNumUUID());

        problem.setProblemId(bigInteger);
        problem.setProblemTitle(problemTitle);
        problem.setProblemBody(problemBody);
        problem.setMyAns(myAns);
        problem.setCreateUser(user.getUserId());
        Date date = new Date();
        problem.setCreateDate(date);
        problem.setUpdateUser(user.getUserId());
        problem.setUpdateDate(date);
        problem.setReadNum(0);
        problem.setIsEffective(SysConst.LIVE);

        problemService.addProblem(problem);

        return new ResultBean(ResultCode.SUCCESS, bigInteger);
    }

    public ResultBean updateProblem(long problemId, String problemTitle, String problemBody, User user) {

        List<Problem> problems = problemService.findProblemListByProblemId(problemId);
        if (problems.size()==0 || problems.get(0).getIsEffective()==SysConst.NOT_LIVE) {
            return new ResultBean(ResultCode.PARAM_ERROR, "不存在该问题");
        }

        Problem problem = problems.get(0);

        problem.setProblemTitle(problemTitle);
        problem.setProblemBody(problemBody);
        problem.setUpdateUser(user.getUserId());
        problem.setUpdateDate(new Date());

        problemService.updateProblemByProblemId(problemId, problem);

        return new ResultBean(ResultCode.SUCCESS);
    }

    public ResultBean addAns(User user, long problemId, String ans, int my) {
        List<Problem> problems = problemService.findProblemListByProblemId(problemId);
        if (problems.size()==0 || problems.get(0).getIsEffective()==SysConst.NOT_LIVE) {
            return new ResultBean(ResultCode.PARAM_ERROR, "不存在该问题");
        }

        Problem problem = problems.get(0);

        if (my==-1) {
            if (problem.getCreateUser()!=user.getUserId()) {
                return new ResultBean(ResultCode.PARAM_ERROR, "无法添加今日一题我的题解");
            }
            problem.setMyAns(ans);
        }

        problemService.updateProblemByProblemId(problemId, problem);

        return new ResultBean(ResultCode.SUCCESS);

    }

    public ResultBean replyProblem(User user, long problemId, long p_replyproblemId ,String ansBody) {

        List<Problem> problems = problemService.findProblemListByProblemId(problemId);
        if (problems.size()==0 || problems.get(0).getIsEffective()==SysConst.NOT_LIVE) {
            return new ResultBean(ResultCode.PARAM_ERROR, "不存在该问题");
        }

        Replyproblem replyproblem = new Replyproblem();
        replyproblem.setProblemId(problemId);
        replyproblem.setPProblemans(p_replyproblemId);
        replyproblem.setAnsUser(user.getUserId());
        replyproblem.setAnsBody(ansBody);
        replyproblem.setCreateDate(new Date());
        replyproblem.setAgreeNum(0);
        replyproblem.setIsEffective(SysConst.LIVE);

        replyproblemService.addReplyproblem(replyproblem);

        return new ResultBean(ResultCode.SUCCESS);
    }

    public ResultBean problemDetail(long problemId, User user) {
        List<Map<String, Object>> problems = problemService.findProblem2MapListByProblemId(problemId);
        if (problems.size()==0 || (int)problems.get(0).get("isEffective")==SysConst.NOT_LIVE) {
            return new ResultBean(ResultCode.PARAM_ERROR, "不存在该问题");
        }

        Map<String, Object> problem = problems.get(0);
        problem.put("createDate", DateUtils.convDateToStr((Date) problem.get("createDate"), "yyyy-MM-dd HH:mm:ss"));

        List<Problem> list = problemService.findProblemListByProblemId(problemId);

        Problem problem1 = list.get(0);
        problem1.setReadNum(problem1.getReadNum()+1);

        problemService.updateProblemByProblemId(problemId, problem1);

        return new ResultBean(ResultCode.SUCCESS, problem);
    }

    public ResultBean newProblemDetail() {
        Map<String, Object> map = new HashMap<>();
        map.put("order", "createDate");
        //  map.put("isPublic", isPublic);

            map.put("aOrS", "DESC");

        map.put("isEffective", 1);
        List<Map<String, Object>> list = problemService.findProblemMapListByQuery(map);
        if (list.size()==0) {
            return new ResultBean(ResultCode.PARAM_ERROR, "不存在校赛");
        }
        Map<String, Object> pro = list.get(0);
        pro.put("createDate", DateUtils.convDateToStr((Date) pro.get("createDate"), "yyyy-MM-dd HH:mm:ss"));

        return new ResultBean(ResultCode.SUCCESS, pro);
    }

    public ResultBean deleteProblem(long problemId, User user) {
        List<Problem> problems = problemService.findProblemListByProblemId(problemId);
        if (problems.size()==0 || problems.get(0).getIsEffective()==SysConst.NOT_LIVE) {
            return new ResultBean(ResultCode.PARAM_ERROR, "不存在该问题");
        }

        Problem problem = problems.get(0);

        problem.setIsEffective(SysConst.NOT_LIVE);

        problemService.updateProblemByProblemId(problemId, problem);
        return new ResultBean(ResultCode.SUCCESS);
    }

    public ResultBean selectProblem(User user, String problemTitle, int aOrs, int pageNum,String order, int pageSize, int my) {
        Map<String, Object> map = new HashMap<>();
        if (pageNum < 0) {
            return new ResultBean(ResultCode.PARAM_ERROR, "页码不能小于0");
        }
        if (pageSize < 0) {
            return new ResultBean(ResultCode.PARAM_ERROR, "一页展示数量不能小于0");
        }
        int start = (pageNum - 1) * pageSize;
        int limit = pageSize;
        map.put("problemTitle", problemTitle);
        if (my!=-1) {
            map.put("createUser", user.getUserId());
        }
        map.put("start", start);
        map.put("limit", limit);
        map.put("order", order);
        //  map.put("isPublic", isPublic);
        if (aOrs == 1) {
            map.put("aOrS", "DESC");
        } else {
            map.put("aOrS", "ASC");
        }
        map.put("isEffective", 1);
        List<Map<String, Object>> list = problemService.findProblemMapListByQuery(map);

        int allNum = problemService.countProblemMapListByQuery(map);
        if (list.size() >0) {
            for (Map<String, Object> mapTemp : list) {
                mapTemp.put("createDate", DateUtils.convDateToStr((Date) mapTemp.get("createDate"), "yyyy-MM-dd HH:mm:ss"));
            }
        }
        ListPage<List<Map<String, Object>>> listPage = ListPage.createListPage(pageNum, pageSize, allNum, list);

        return new ResultBean(ResultCode.SUCCESS, listPage);
    }

    public ResultBean selectReply(User user, long problemId, int aOrs, int pageNum,String order, int pageSize) {
        Map<String, Object> map = new HashMap<>();
        if (pageNum < 0) {
            return new ResultBean(ResultCode.PARAM_ERROR, "页码不能小于0");
        }
        if (pageSize < 0) {
            return new ResultBean(ResultCode.PARAM_ERROR, "一页展示数量不能小于0");
        }
        int start = (pageNum - 1) * pageSize;
        int limit = pageSize;
        map.put("problemId", problemId);
        map.put("start", start);
        map.put("limit", limit);
        map.put("order", order);
        //  map.put("isPublic", isPublic);
        if (aOrs == 1) {
            map.put("aOrS", "DESC");
        } else {
            map.put("aOrS", "ASC");
        }
        map.put("isEffective", 1);

        List<Map<String, Object>> list = replyproblemService.findReplyproblemMapListByQuery(map);

        int allNum = replyproblemService.countReplyproblemMapListByQuery(map);
        if (list.size() >0) {
            for (Map<String, Object> mapTemp : list) {
                mapTemp.put("createDate", DateUtils.convDateToStr((Date) mapTemp.get("createDate"), "yyyy-MM-dd HH:mm:ss"));
            }
        }
        ListPage<List<Map<String, Object>>> listPage = ListPage.createListPage(pageNum, pageSize, allNum, list);

        return new ResultBean(ResultCode.SUCCESS, listPage);
    }

    public ResultBean ansDetail(long ansId) {
        List<Map<String, Object>> list = replyproblemService.findReplyproblem2MapListByProblemansId(ansId);

        if (list.size()==0) {
            return new ResultBean(ResultCode.PARAM_ERROR, "不存在该回答");
        }
        if ((int)list.get(0).get("isEffective")==SysConst.NOT_LIVE) {
            return new ResultBean(ResultCode.PARAM_ERROR, "不存在该回答");
        }

        return new ResultBean(ResultCode.SUCCESS, list.get(0));
    }

    public ResultBean deleteAns(long ansId) {
        List<Replyproblem> list = replyproblemService.findReplyproblemListByProblemansId(ansId);

        if (list.size()==0) {
            return new ResultBean(ResultCode.PARAM_ERROR, "不存在该回答");
        }
        if (list.get(0).getIsEffective()==SysConst.NOT_LIVE) {
            return new ResultBean(ResultCode.PARAM_ERROR, "不存在该回答");
        }

        Replyproblem replyproblem = list.get(0);

        replyproblem.setIsEffective(SysConst.NOT_LIVE);
        replyproblemService.updateReplyproblemByProblemansId(ansId, replyproblem);

        return new ResultBean(ResultCode.SUCCESS);
    }
}
