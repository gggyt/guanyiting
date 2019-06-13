package com.example.acm.service.deal.Impl;

import com.example.acm.common.ResultBean;
import com.example.acm.common.ResultCode;
import com.example.acm.common.SysConst;
import com.example.acm.entity.Comment;
import com.example.acm.entity.Invitation;
import com.example.acm.entity.User;
import com.example.acm.service.CommentService;
import com.example.acm.service.InvitationService;
import com.example.acm.service.deal.CommentDealService;
import com.example.acm.utils.DateUtils;
import com.example.acm.utils.ListPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ggg on 2019/2/9.
 */
@Service
public class CommentDealServiceImpl implements CommentDealService{

    private static final Logger LOG = LoggerFactory.getLogger(CommentDealServiceImpl.class);

    @Autowired
    private CommentService commentService;
    @Autowired
    private InvitationService invitationService;

    public ResultBean addComment(User user, long invitationId, long p_commentId, String commentBody){
        try{

            List<Invitation> invitations = invitationService.findInvitationListByInvitationId(invitationId);
            if (invitations.size()==0) {
                LOG.info("不存在invitationId的invitation");
                return new ResultBean(ResultCode.PARAM_ERROR, "该帖子不存在");
            }

            Comment comment = new Comment();
            comment.setInvitationId(invitationId);
            comment.setPCommentid(p_commentId);
            comment.setCommentBody(commentBody);
            comment.setCreateUser(user.getUserId());
            comment.setCreateDate(new Date());
            comment.setIsEffective(SysConst.LIVE);

            commentService.addComment(comment);

            return new ResultBean(ResultCode.SUCCESS);
        } catch (Exception e){
            e.printStackTrace();
            LOG.error(e.getMessage());
            return new ResultBean(ResultCode.SYSTEM_FAILED);
        }
    }

    public ResultBean deleteComment(User user,long commentId) {
        List<Comment> comments = commentService.findCommentListByCommentId(commentId);
        if (comments.size()==0) {
            return new ResultBean(ResultCode.PARAM_ERROR, "不存在该评论");
        }
        Comment comment = comments.get(0);
        if (comment.getIsEffective()==SysConst.NOT_LIVE) {
            return new ResultBean(ResultCode.PARAM_ERROR, "该评论已删除");
        }

        comment.setIsEffective(SysConst.NOT_LIVE);
        commentService.updateCommentByCommentId(commentId, comment);

        return new ResultBean(ResultCode.SUCCESS);
    }
    public ResultBean selectCommend(User user,long invitationId, long p_commentId, int aOrs, int pageNum, String order, int pageSize) {
        try{

            Map<String, Object> map = new HashMap<>();
            if (pageNum <= 0) {
                //return new ResultBean(ResultCode.PARAM_ERROR, "页码不能小于0");
                pageNum = 1;
            }
            if (pageSize < 0) {
                return new ResultBean(ResultCode.PARAM_ERROR, "一页展示数量不能小于0");
            }
            int start = (pageNum - 1) * pageSize;
            int limit = pageSize;
            map.put("start", start);
            map.put("limit", limit);
            map.put("order", order);
            //  map.put("isPublic", isPublic);
            if (aOrs == 1) {
                map.put("aOrS", "DESC");
            } else {
                map.put("aOrS", "ASC");
            }
            map.put("p_commentId", p_commentId);
            map.put("invitationId", invitationId);
            map.put("isEffective", SysConst.LIVE);
            List<Map<String, Object>> comments = commentService.finfCommentAndSonCommtne(map);
            if (comments.size() >0) {
                for (Map<String, Object> mapTemp : comments) {
                    mapTemp.put("createDate", DateUtils.convDateToStr((Date) mapTemp.get("createDate"), "yyyy-MM-dd HH:mm:ss"));

                }
            }
            int allNum = commentService.countCommentAndSonCommtne(map);
            ListPage<List<Map<String, Object>>> listPage = ListPage.createListPage(pageNum, pageSize, allNum, comments);
            return new ResultBean(ResultCode.SUCCESS, listPage);
        } catch (Exception e){
            e.printStackTrace();
            LOG.error(e.getMessage());
            return new ResultBean(ResultCode.SYSTEM_FAILED);
        }
    }
    public ResultBean selectBackCommend(User user,long invitationId, long p_commentId, int aOrs, int pageNum, String order, int pageSize) {
        try{

            Map<String, Object> map = new HashMap<>();
            if (pageNum <= 0) {
                pageNum = 1;
            }
            if (pageSize < 0) {
                return new ResultBean(ResultCode.PARAM_ERROR, "一页展示数量不能小于0");
            }
            int start = (pageNum - 1) * pageSize;
            int limit = pageSize;
            map.put("start", start);
            map.put("limit", limit);
            map.put("order", order);
            if (aOrs == 1) {
                map.put("aOrS", "DESC");
            } else {
                map.put("aOrS", "ASC");
            }
            map.put("p_commentId", p_commentId);
            map.put("invitationId", invitationId);
            map.put("isEffective", SysConst.LIVE);
            List<Map<String, Object>> comments = commentService.finfCommentAndSonCommtne(map);
            if (comments.size() >0) {
                for (Map<String, Object> mapTemp : comments) {
                    mapTemp.put("createDate", DateUtils.convDateToStr((Date) mapTemp.get("createDate"), "yyyy-MM-dd HH:mm:ss"));
                    Map<String, Object> tmp = new HashMap<>();
                    tmp.put("p_commentId", mapTemp.get("commentId"));
                    tmp.put("invitationId", invitationId);
                    tmp.put("isEffective", SysConst.LIVE);

                    List<Map<String, Object>> tmps = commentService.finfCommentAndSonCommtne(tmp);
                    for(Map<String, Object> mm: tmps) {
                        mm.put("createDate", DateUtils.convDateToStr((Date) mm.get("createDate"), "yyyy-MM-dd HH:mm:ss"));

                    }
                    mapTemp.put("subComment", tmps);
                }
            }
            int allNum = commentService.countCommentAndSonCommtne(map);
            ListPage<List<Map<String, Object>>> listPage = ListPage.createListPage(pageNum, pageSize, allNum, comments);
            return new ResultBean(ResultCode.SUCCESS, listPage);
        } catch (Exception e){
            e.printStackTrace();
            LOG.error(e.getMessage());
            return new ResultBean(ResultCode.SYSTEM_FAILED);
        }
    }
    public ResultBean selectUserCommend(User user, long p_commentId, int aOrs, int pageNum, String order, int pageSize, int getMy) {
        try{

            Map<String, Object> map = new HashMap<>();
            if (pageNum <= 0) {
                //return new ResultBean(ResultCode.PARAM_ERROR, "页码不能小于0");
                pageNum = 1;
            }
            if (pageSize < 0) {
                return new ResultBean(ResultCode.PARAM_ERROR, "一页展示数量不能小于0");
            }
            int start = (pageNum - 1) * pageSize;
            int limit = pageSize;
            map.put("start", start);
            map.put("limit", limit);
            map.put("order", order);
            if (getMy==-1) {
                map.put("createUser", user.getUserId());
            } else {
                map.put("createUser", getMy);
            }
            //  map.put("isPublic", isPublic);
            if (aOrs == 1) {
                map.put("aOrS", "DESC");
            } else {
                map.put("aOrS", "ASC");
            }
          //  map.put("p_commentId", p_commentId);
            map.put("isEffective", SysConst.LIVE);
            List<Map<String, Object>> comments = commentService.finfCommentAndSonCommtne(map);
            if (comments.size() >0) {
                for (Map<String, Object> mapTemp : comments) {
                    mapTemp.put("createDate", DateUtils.convDateToStr((Date) mapTemp.get("createDate"), "yyyy-MM-dd HH:mm:ss"));

                }
            }
            int allNum = commentService.countCommentAndSonCommtne(map);
            ListPage<List<Map<String, Object>>> listPage = ListPage.createListPage(pageNum, pageSize, allNum, comments);
            return new ResultBean(ResultCode.SUCCESS, listPage);
        } catch (Exception e){
            e.printStackTrace();
            LOG.error(e.getMessage());
            return new ResultBean(ResultCode.SYSTEM_FAILED);
        }
    }
}
