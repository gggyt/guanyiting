package com.example.acm.controller;

import com.example.acm.common.ResultBean;
import com.example.acm.common.ResultCode;
import com.example.acm.entity.User;
import com.example.acm.service.deal.CommentDealService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by ggg on 2019/2/9.
 */
@Controller
@RequestMapping("/comment")
public class CommentController extends BaseController{

    private static final Logger LOG = LoggerFactory.getLogger(CommentController.class);

    @Autowired
    private CommentDealService commentDealService;

    @RequestMapping("/addComment")
    @ResponseBody
    public ResultBean addComment(@RequestParam(value = "invitationId", required = true) long invitationId,
                                 @RequestParam(value = "p_commentId", defaultValue = "0", required = false) long p_commentId,
                                 @RequestParam(value="commentBody", required = true) String commentBody,
                                 HttpServletRequest request, HttpServletResponse response){
        try {
            User user = getUserIdFromSession(request);
            if (user == null) {
                return new ResultBean(ResultCode.SESSION_OUT);
            }
            return commentDealService.addComment(user, invitationId, p_commentId, commentBody);
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error(e.getMessage());
            return new ResultBean(ResultCode.SYSTEM_FAILED);
        }
    }

    @RequestMapping("/deleteComment")
    @ResponseBody
    public ResultBean deleteComment(@RequestParam(value = "commentId", required = true) long commentId,
                                 HttpServletRequest request, HttpServletResponse response){
        try {
            User user = getUserIdFromSession(request);
            if (user == null) {
                return new ResultBean(ResultCode.SESSION_OUT);
            }
            return commentDealService.deleteComment(user, commentId);
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error(e.getMessage());
            return new ResultBean(ResultCode.SYSTEM_FAILED);
        }
    }

    @RequestMapping("/selectComment")
    @ResponseBody
    public ResultBean selectComment(@RequestParam(value = "invitationId",  required = true) long invitationId,
                                    @RequestParam(value = "p_commentId",defaultValue = "0", required = false) long p_commentId,
                                    @RequestParam(value="aOrs", defaultValue = "1", required = false) int aOrs,
                                    @RequestParam(value = "pageNum", defaultValue = "1", required = false) int pageNum,
                                    @RequestParam(value="order", defaultValue = "createDate", required = false) String order,
                                    @RequestParam(value="pageSize", defaultValue="10", required = false) int pageSize,
                                 HttpServletRequest request, HttpServletResponse response){
        try {
            User user = getUserIdFromSession(request);
            return commentDealService.selectCommend(user, invitationId,p_commentId, aOrs, pageNum, order, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error(e.getMessage());
            return new ResultBean(ResultCode.SYSTEM_FAILED);
        }
    }

    @RequestMapping("/selectBackComment")
    @ResponseBody
    public ResultBean selectBackComment(@RequestParam(value = "invitationId",  required = true) long invitationId,
                                    @RequestParam(value = "p_commentId",defaultValue = "0", required = false) long p_commentId,
                                    @RequestParam(value="aOrs", defaultValue = "1", required = false) int aOrs,
                                    @RequestParam(value = "pageNum", defaultValue = "1", required = false) int pageNum,
                                    @RequestParam(value="order", defaultValue = "createDate", required = false) String order,
                                    @RequestParam(value="pageSize", defaultValue="10", required = false) int pageSize,
                                    HttpServletRequest request, HttpServletResponse response){
        try {
            User user = getUserIdFromSession(request);
            return commentDealService.selectBackCommend(user, invitationId,p_commentId, aOrs, pageNum, order, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error(e.getMessage());
            return new ResultBean(ResultCode.SYSTEM_FAILED);
        }
    }

    @RequestMapping("/selectUserComment")
    @ResponseBody
    public ResultBean selectUserComment(@RequestParam(value = "p_commentId",defaultValue = "0", required = false) long p_commentId,
                                    @RequestParam(value="aOrs", defaultValue = "1", required = false) int aOrs,
                                    @RequestParam(value = "pageNum", defaultValue = "1", required = false) int pageNum,
                                    @RequestParam(value="order", defaultValue = "createDate", required = false) String order,
                                    @RequestParam(value="pageSize", defaultValue="10", required = false) int pageSize,
                                    @RequestParam(value = "getMy", defaultValue = "-1", required = false) int getMy,
                                    HttpServletRequest request, HttpServletResponse response){
        try {
            User user = getUserIdFromSession(request);
            return commentDealService.selectUserCommend(user,p_commentId, aOrs, pageNum, order, pageSize, getMy);
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error(e.getMessage());
            return new ResultBean(ResultCode.SYSTEM_FAILED);
        }
    }
}
