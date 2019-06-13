package com.example.acm.controller;

import com.example.acm.common.ResultBean;
import com.example.acm.common.ResultCode;
import com.example.acm.common.SysConst;
import com.example.acm.entity.Invitation;
import com.example.acm.entity.User;
import com.example.acm.mapper.InvitationMapper;
import com.example.acm.mapper.ProblemMapper;
import com.example.acm.service.*;
import com.example.acm.view.InvitationRet;
import com.example.acm.view.InvitationSumView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by ggg on 2019/5/16.
 */
@RequestMapping("/getChart")
@Controller
public class InfoController {

    @Autowired
    private InvitationMapper invitationMapper;
    @Autowired
    private ProblemMapper problemMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private InvitationService invitationService;
    @Autowired
    private FriendurlService friendurlService;
    @Autowired
    private LectureService lectureService;
    @Autowired
    private PhotoService photoService;

    @RequestMapping("/getIndexInfo")
    @ResponseBody
    public ResultBean getIndexInfo(HttpServletRequest request, HttpServletResponse response) {


        Map<String, Object> map = new HashMap<>();
        map.put("isEffective", SysConst.LIVE);

        int allUserNum = userService.countUserMapListByQuery(map);
        int allInvitationNum = invitationService.countInvitationListByQuery(map);
        int allFriendNum = friendurlService.countFriendurlListByQuery(map);
        int allPhotoNum = photoService.countPhotoListByQuery(map);

        map.put("allUserNum", allUserNum);
        map.put("allInvitationNum", allInvitationNum);
        map.put("allFriendNum", allFriendNum);
        map.put("allPhotoNum", allPhotoNum);

        return new ResultBean(ResultCode.SUCCESS, map);
    }
    @RequestMapping("/getInvitationSum")
    @ResponseBody
    public ResultBean getInvitationSum() {
        List<InvitationSumView> sumViews = new ArrayList<>();

        List<String> data = new ArrayList<>();
        List<Integer> sums = new ArrayList<>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();

        Calendar c = Calendar.getInstance();

        Map<String, Object> map = new HashMap<>();
        map.put("isEffective", SysConst.LIVE);
        for(int i=7; i>=0; i--) {
            c.setTime(now);
            c.add(Calendar.DATE, -1*(i-1));
            Date d = c.getTime();
            String day = sdf.format(d);
            map.put("beforeTime", day);
            int sum = invitationMapper.conutInvitationBefore(map);

            InvitationSumView invitationSumView = new InvitationSumView(day, sum);
            sumViews.add(invitationSumView);
            data.add(day);
            sums.add(sum);
        }

        InvitationRet ret = new InvitationRet();
        ret.setData(data);
        ret.setSum(sums);

        return new ResultBean(ResultCode.SUCCESS, ret);
    }
    @RequestMapping("/getProblemSum")
    @ResponseBody
    public ResultBean getProblemSum() {

        List<String> data = new ArrayList<>();
        List<Integer> sums = new ArrayList<>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();

        Calendar c = Calendar.getInstance();

        Map<String, Object> map = new HashMap<>();
        map.put("isEffective", SysConst.LIVE);
        for(int i=7; i>=0; i--) {
            c.setTime(now);
            c.add(Calendar.DATE, -1*(i-1));
            Date d = c.getTime();
            String day = sdf.format(d);
            map.put("beforeTime", day);
            int sum = problemMapper.conutProblemBefore(map);

            data.add(day);
            sums.add(sum);
        }

        InvitationRet ret = new InvitationRet();
        ret.setData(data);
        ret.setSum(sums);

        return new ResultBean(ResultCode.SUCCESS, ret);
    }
}
