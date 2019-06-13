package com.example.acm.controller;

import com.example.acm.common.ResultBean;
import com.example.acm.common.ResultCode;
import com.example.acm.entity.User;
import com.example.acm.service.deal.ProblemDealService;
import com.example.acm.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by ggg on 2019/4/15.
 */
@Controller
@RequestMapping("/problem")
public class problemController extends BaseController{

    @Autowired
    private ProblemDealService problemDealService;

    @RequestMapping("/addProblem")
    @ResponseBody
    public ResultBean addProblem(@RequestParam(value = "problemTitle", required = true) String problemTitle,
                                 @RequestParam(value = "problemBody", required = true) String problemBody,
                                 @RequestParam(value = "myAns", defaultValue = "", required = false) String myAns,
                                 HttpServletRequest request, HttpServletResponse response) {
        User user = getUserIdFromSession(request);
        problemBody = StringUtils.getHtml(problemBody);
        if (user == null) {
            return new ResultBean(ResultCode.SESSION_OUT);
        }
        if (user.getAuth()<1) {
            return new ResultBean(ResultCode.ACCOUNT_NOTAUTH);
        }

        if (StringUtils.isNull(problemTitle)) {
            return new ResultBean(ResultCode.PARAM_ERROR, "标题不能为空");
        }
        if (StringUtils.isNull(problemBody)) {
            return new ResultBean(ResultCode.PARAM_ERROR, "题目内容不能为空");
        }

        return problemDealService.addProblem(problemTitle, problemBody, myAns, user);
    }

    @RequestMapping("/updateProblem")
    @ResponseBody
    public ResultBean updateProblem(@RequestParam(value = "problemId", required = true) long problemId,
                                    @RequestParam(value = "problemTitle", required = true) String problemTitle,
                                     @RequestParam(value = "problemBody", required = true) String problemBody,
                                     HttpServletRequest request, HttpServletResponse response) {
        User user = getUserIdFromSession(request);
        problemBody = StringUtils.getHtml(problemBody);
        if (user == null) {
            return new ResultBean(ResultCode.SESSION_OUT);
        }

        if (user.getAuth()<1) {
            return new ResultBean(ResultCode.ACCOUNT_NOTAUTH);
        }
        if (StringUtils.isNull(problemTitle)) {
            return new ResultBean(ResultCode.PARAM_ERROR, "标题不能为空");
        }
        if (StringUtils.isNull(problemBody)) {
            return new ResultBean(ResultCode.PARAM_ERROR, "题目内容不能为空");
        }

        return problemDealService.updateProblem(problemId, problemTitle, problemBody, user);
    }


    @RequestMapping("/addAns")
    @ResponseBody
    public ResultBean addAns(@RequestParam(value = "problemId", required = true) long problemId,
                             @RequestParam(value = "ans", required = true) String ans,
                             @RequestParam(value = "my", defaultValue = "-1", required = true) int my,
                             HttpServletRequest request, HttpServletResponse response) {
        User user = getUserIdFromSession(request);
        ans = StringUtils.getHtml(ans);
        if (user == null) {
            return new ResultBean(ResultCode.SESSION_OUT);
        }
        if (user.getAuth()<1) {
            return new ResultBean(ResultCode.ACCOUNT_NOTAUTH);
        }

        return problemDealService.addAns(user, problemId, ans, my);
    }

    @RequestMapping("/problemDetail")
    @ResponseBody
    public ResultBean problemDetail(@RequestParam(value = "problemId", required = true) long problemId,
                                    HttpServletRequest request, HttpServletResponse response) {
        User user = getUserIdFromSession(request);
        if (user == null) {
            return new ResultBean(ResultCode.SESSION_OUT);
        }

        return problemDealService.problemDetail(problemId, user);
    }

    @RequestMapping("/newProblemDetail")
    @ResponseBody
    public ResultBean newProblemDetail(HttpServletRequest request, HttpServletResponse response) {

        return problemDealService.newProblemDetail();
    }

    @RequestMapping("/deleteProblem")
    @ResponseBody
    public ResultBean deleteProblem(@RequestParam(value = "problemId", required = true) long problemId,
                                    HttpServletRequest request, HttpServletResponse response) {
        User user = getUserIdFromSession(request);
        if (user == null) {
            return new ResultBean(ResultCode.SESSION_OUT);
        }

        return problemDealService.deleteProblem(problemId, user);
    }

    @RequestMapping("/selectProblem")
    @ResponseBody
    public ResultBean selectProblem(@RequestParam(value="problemTitle", defaultValue = "", required = false) String problemTitle,
                                    @RequestParam(value="aOrs", defaultValue = "1", required = false) int aOrs,
                                    @RequestParam(value = "pageNum", defaultValue = "1", required = false) int pageNum,
                                    @RequestParam(value="order", defaultValue = "updateDate", required = false) String order,
                                    @RequestParam(value="pageSize", defaultValue="10", required = false) int pageSize,
                                    @RequestParam(value = "my", defaultValue = "-1", required = true) int my,
                                    HttpServletRequest request, HttpServletResponse response) {

        User user = getUserIdFromSession(request);
        if (user == null) {
            return new ResultBean(ResultCode.SESSION_OUT);
        }

        return problemDealService.selectProblem(user,  problemTitle,  aOrs,  pageNum, order,  pageSize, my);
    }

    @RequestMapping("/replyProblem")
    @ResponseBody
    public ResultBean replyProblem(@RequestParam(value = "ansBody", required = true) String ansBody,
                                   @RequestParam(value = "problemId", required = true) long problemId,
                                   @RequestParam(value = "p_replyproblemId", defaultValue = "0", required = false) long p_replyproblemId,
                                   HttpServletRequest request, HttpServletResponse response) {
        User user = getUserIdFromSession(request);
        if (user == null) {
            return new ResultBean(ResultCode.SESSION_OUT);
        }
        return problemDealService.replyProblem(user, problemId, p_replyproblemId, ansBody);
    }

    @RequestMapping("/selectReply")
    @ResponseBody
    public ResultBean selectReply(@RequestParam(value="problemId", required = true) long problemId,
                                    @RequestParam(value="aOrs", defaultValue = "1", required = false) int aOrs,
                                    @RequestParam(value = "pageNum", defaultValue = "1", required = false) int pageNum,
                                    @RequestParam(value="order", defaultValue = "createDate", required = false) String order,
                                    @RequestParam(value="pageSize", defaultValue="10", required = false) int pageSize,
                                    HttpServletRequest request, HttpServletResponse response) {

        User user = getUserIdFromSession(request);
        if (user == null) {
            return new ResultBean(ResultCode.SESSION_OUT);
        }

        return problemDealService.selectReply(user,  problemId,  aOrs,  pageNum, order,  pageSize);
    }

    @RequestMapping("/ansDetail")
    @ResponseBody
    public ResultBean DetailAns(@RequestParam(value = "ansId" ,required = true) long ansId,
                                HttpServletRequest request, HttpServletResponse response) {
        User user = getUserIdFromSession(request);
        if (user == null) {
            return new ResultBean(ResultCode.SESSION_OUT);
        }

        return problemDealService.ansDetail(ansId);
    }

    @RequestMapping("/deleteAns")
    @ResponseBody
    public ResultBean deleteAns(@RequestParam(value = "ansId" ,required = true) long ansId,
                                HttpServletRequest request, HttpServletResponse response) {
        User user = getUserIdFromSession(request);
        if (user == null) {
            return new ResultBean(ResultCode.SESSION_OUT);
        }

        return problemDealService.deleteAns(ansId);
    }
}
