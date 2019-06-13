package com.example.acm.config;

import com.example.acm.common.SysConst;
import com.example.acm.entity.Lecture;
import com.example.acm.service.LectureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ggg on 2019/4/9.
 */
@Component
public class ZoneScheduled {

    @Autowired
    private LectureService lectureService;

    /**
     * 定时任务，将超时得讲座标记为结束
     */
    @Scheduled(cron = "0 0 12 * * ?")
    public void deealWithLecture() {
        Map<String, Object> map = new HashMap<>();
        map.put("isEffective", 1);

        List<Lecture> lectures = lectureService.findLectureListByQuery(map);

        Date now = new Date();

        for (Lecture le:lectures) {
            if (now.after(le.getCreateDate())) {
                le.setIsDone(SysConst.NOT_LIVE);

                lectureService.updateLectureByLectureId(le.getLectureId(), le);
            }
        }
    }

}
