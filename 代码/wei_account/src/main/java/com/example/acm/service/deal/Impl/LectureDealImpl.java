package com.example.acm.service.deal.Impl;

import com.example.acm.common.ResultBean;
import com.example.acm.common.ResultCode;
import com.example.acm.common.SysConst;
import com.example.acm.entity.Applylecture;
import com.example.acm.entity.Lecture;
import com.example.acm.entity.Photo;
import com.example.acm.entity.User;
import com.example.acm.service.ApplylectureService;
import com.example.acm.service.LectureService;
import com.example.acm.service.UserService;
import com.example.acm.service.deal.LectureDealService;
import com.example.acm.utils.DateUtils;
import com.example.acm.utils.ListPage;
import com.example.acm.utils.UUIDUtil;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ggg on 2019/3/11.
 */
@Service
public class LectureDealImpl implements LectureDealService {

    private static final Logger LOG = LoggerFactory.getLogger(LectureDealImpl.class);

    @Autowired
    private LectureService lectureService;

    @Autowired
    private ApplylectureService applylectureService;

    @Autowired
    private UserService userService;

    public ResultBean addLecture(User user, String lectureTitle, String lectureBody, String date){
        try {
            Long bigInteger = new Long(UUIDUtil.getNumUUID());
            Lecture lecture = new Lecture();
            LOG.info(date);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            lecture.setLectureId(bigInteger);
            lecture.setLectureTitle(lectureTitle);
            lecture.setLectureBody(lectureBody);
            lecture.setCreateUser(user.getUserId());
            lecture.setCreateDate(sdf.parse(date)); //createDate变为开始时间
            lecture.setIsDone(SysConst.LIVE);
            lecture.setUpdateUser(user.getUserId());
            lecture.setUpdateDate(new Date());
            lecture.setIsFirst(0);
            lecture.setIsEffective(SysConst.LIVE);

            lectureService.addLecture(lecture);

            return new ResultBean(ResultCode.SUCCESS, bigInteger);
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error(e.getMessage());
            return new ResultBean(ResultCode.OTHER_FAIL);
        }

    }

    public ResultBean updateLecture(User user, long lectureId, String lectureTitle, String lectureBody, String date){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            List<Lecture> lectures = lectureService.findLectureListByLectureId(lectureId);
            if (lectures.size()<1) {
                return new ResultBean(ResultCode.PARAM_ERROR, "不存在的讲座id");
            }
            Lecture lec = lectures.get(0);
            if (lec.getIsDone()==SysConst.NOT_USE) {
                return new ResultBean(ResultCode.PARAM_ERROR, "该讲座已结束");
            }
            lec.setLectureTitle(lectureTitle);
            lec.setLectureBody(lectureBody);
            lec.setCreateDate(sdf.parse(date));
            lectureService.updateLectureByLectureId(lectureId, lec);

            return new ResultBean(ResultCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error(e.getMessage());
            return new ResultBean(ResultCode.OTHER_FAIL);
        }
    }

    public ResultBean selectLecture(User user, String lectureTitle, int aOrs, int pageNum,String order, int pageSize){
        try {
            Map<String, Object> map = new HashMap<>();
            if (pageNum < 0) {
                return new ResultBean(ResultCode.PARAM_ERROR, "页码不能小于0");
            }
            if (pageSize < 0) {
                return new ResultBean(ResultCode.PARAM_ERROR, "一页展示数量不能小于0");
            }
            int start = (pageNum - 1) * pageSize;
            int limit = pageSize;
            map.put("lectureTitle", lectureTitle);
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
            List<Map<String, Object>> list = lectureService.findLectureMapListByQuery(map);

            int allNum = lectureService.countLectureListByQuery(map);
            if (list.size() >0) {
                for (Map<String, Object> mapTemp : list) {
                    mapTemp.put("createDate", DateUtils.convDateToStr((Date) mapTemp.get("createDate"), "yyyy-MM-dd HH:mm:ss"));
                }
            }
            ListPage<List<Map<String, Object>>> listPage = ListPage.createListPage(pageNum, pageSize, allNum, list);

            return new ResultBean(ResultCode.SUCCESS, listPage);
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error(e.getMessage());
            return new ResultBean(ResultCode.OTHER_FAIL);
        }
    }

    public ResultBean newLecture() {
        Map<String, Object> map = new HashMap<>();

        map.put("order", "createDate");
        map.put("aOrS", "DESC");
        map.put("isEffective", 1);
        List<Map<String, Object>> list = lectureService.findLectureMapListByQuery(map);

        Map<String, Object> lecture = list.get(0);
        lecture.put("createDate", DateUtils.convDateToStr((Date) lecture.get("createDate"), "yyyy-MM-dd HH:mm:ss"));
        int allNum = lectureService.countLectureListByQuery(map);

        return new ResultBean(ResultCode.SUCCESS, lecture);
    }

    public ResultBean selectUserLecture(User user, int userId, int aOrs, int pageNum,String order, int pageSize){
        try {
            Map<String, Object> map = new HashMap<>();
            if (pageNum < 0) {
                return new ResultBean(ResultCode.PARAM_ERROR, "页码不能小于0");
            }
            if (pageSize < 0) {
                return new ResultBean(ResultCode.PARAM_ERROR, "一页展示数量不能小于0");
            }
            int start = (pageNum - 1) * pageSize;
            int limit = pageSize;
            map.put("joinUser", userId);
            map.put("isEffective", 1);
            if (userId==-1) {
                map.put("joinUser", user.getUserId());
            }
            List<Map<String, Object>> listTmp = lectureService.findLectureMapListByUser(map);
            int allNum = listTmp.size();
            map.put("start", start);
            map.put("limit", limit);
            map.put("order", order);
            //  map.put("isPublic", isPublic);
            if (aOrs == 1) {
                map.put("aOrS", "DESC");
            } else {
                map.put("aOrS", "ASC");
            }
            List<Map<String, Object>> list = lectureService.findLectureMapListByUser(map);

            if (list.size() >0) {
                for (Map<String, Object> mapTemp : list) {
                    mapTemp.put("createDate", DateUtils.convDateToStr((Date) mapTemp.get("createDate"), "yyyy-MM-dd HH:mm:ss"));


                }
            }
            ListPage<List<Map<String, Object>>> listPage = ListPage.createListPage(pageNum, pageSize, allNum, list);

            return new ResultBean(ResultCode.SUCCESS, listPage);
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error(e.getMessage());
            return new ResultBean(ResultCode.OTHER_FAIL);
        }
    }

    public ResultBean selectLecturePerson(User user, long lectureId, int aOrs, int pageNum,String order, int pageSize){
        try {
            List<Lecture> lectures = lectureService.findLectureListByLectureId(lectureId);
            if (lectures.size()==0) {
                return new ResultBean(ResultCode.PARAM_ERROR, "不存在校赛");
            }
            Lecture lecture = lectures.get(0);
            if (lecture.getIsEffective()==SysConst.NOT_PASS) {
                return new ResultBean(ResultCode.PARAM_ERROR, "不存在该校赛");
            }
            Map<String, Object> map = new HashMap<>();
            if (pageNum < 0) {
                return new ResultBean(ResultCode.PARAM_ERROR, "页码不能小于0");
            }
            if (pageSize < 0) {
                return new ResultBean(ResultCode.PARAM_ERROR, "一页展示数量不能小于0");
            }
            int start = (pageNum - 1) * pageSize;
            int limit = pageSize;
            map.put("lectureId", lectureId);
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
            List<Map<String, Object>> list = userService.findUserByLectureId(map);
            if (list.size() >0) {
                for (Map<String, Object> mapTemp : list) {
                    mapTemp.put("createDate", DateUtils.convDateToStr((Date) mapTemp.get("createDate"), "yyyy-MM-dd HH:mm:ss"));
                }
            }

            int allNum = userService.countUserByLectureId(map);

            ListPage<List<Map<String, Object>>> listPage = ListPage.createListPage(pageNum, pageSize, allNum, list);
            return new ResultBean(ResultCode.SUCCESS, listPage);
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error(e.getMessage());
            return new ResultBean(ResultCode.OTHER_FAIL);
        }
    }

    public ResultBean lectureDetail(User user, long lectureId){
        try {
            List<Map<String, Object>> lectures = lectureService.findLecture2MapListByLectureId(lectureId);
            if (lectures.size()<1) {
                return new ResultBean(ResultCode.PARAM_ERROR, "不存在的讲座id");
            }
            Map<String, Object> lecture = lectures.get(0);
            lecture.put("createDate", DateUtils.convDateToStr((Date) lecture.get("createDate"), "yyyy-MM-dd HH:mm:ss"));
            lecture.put("updateDate", DateUtils.convDateToStr((Date) lecture.get("updateDate"), "yyyy-MM-dd HH:mm:ss"));
            Map<String, Object> map = new HashMap<>();
            map.put("isEffective", SysConst.LIVE);
            map.put("lectureId", lectureId);
            List<Applylecture> applylectures = applylectureService.findApplylectureListByQuery(map);
            lecture.put("joinUserNum", applylectures.size());


            return new ResultBean(ResultCode.SUCCESS, lecture);
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error(e.getMessage());
            return new ResultBean(ResultCode.OTHER_FAIL);
        }
    }

    public ResultBean applyLecture(User user, long lectureId){
        try {
            List<Lecture> lectures = lectureService.findLectureListByLectureId(lectureId);
            if (lectures.size()<1) {
                return new ResultBean(ResultCode.PARAM_ERROR, "不存在的讲座id");
            }
            Lecture lec = lectures.get(0);
            if (lec.getIsDone()==SysConst.NOT_LIVE) {
                return new ResultBean(ResultCode.PARAM_ERROR, "该讲座已结束");
            }
            Map<String, Object> map = new HashMap<>();
            map.put("isEffective", SysConst.LIVE);
            map.put("lectureId", lectureId);
            map.put("joinUser",user.getUserId());
            List<Applylecture> applylectures = applylectureService.findApplylectureListByQuery(map);
            if (applylectures.size()>0) {
                return new ResultBean(ResultCode.SYSTEM_FAILED, "你已申请该讲座");
            }
            Applylecture applylecture = new Applylecture();
            applylecture.setLectureId(lectureId);
            applylecture.setJoinUser(user.getUserId());
            applylecture.setCreateDate(new Date());
            applylecture.setIsEffective(SysConst.LIVE);

            applylectureService.addApplylecture(applylecture);

            return new ResultBean(ResultCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error(e.getMessage());
            return new ResultBean(ResultCode.OTHER_FAIL);
        }
    }

    public ResultBean deleteApplyLecture(User user, long lectureId){
        try {
            List<Lecture> lectures = lectureService.findLectureListByLectureId(lectureId);
            if (lectures.size()<1) {
                return new ResultBean(ResultCode.PARAM_ERROR, "不存在的讲座id");
            }
            Lecture lec = lectures.get(0);
            if (lec.getIsDone()==SysConst.NOT_LIVE) {
                return new ResultBean(ResultCode.PARAM_ERROR, "该讲座已结束");
            }
            Map<String, Object> map = new HashMap<>();
            map.put("isEffective", SysConst.LIVE);
            map.put("lectureId", lectureId);
            map.put("joinUser",user.getUserId());
            List<Applylecture> applylectures = applylectureService.findApplylectureListByQuery(map);
            if (applylectures.size()==0) {
                return new ResultBean(ResultCode.SYSTEM_FAILED, "你未申请该讲座");
            }
            Applylecture applylecture = applylectures.get(0);
            applylecture.setIsEffective(SysConst.NOT_LIVE);

            applylectureService.updateApplylectureByApplyLectureId(applylecture.getApplyLectureId(), applylecture);

            return new ResultBean(ResultCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error(e.getMessage());
            return new ResultBean(ResultCode.OTHER_FAIL);
        }
    }

    public ResultBean doneLecture(User user, long lectureId){
        try {
            List<Lecture> lectures = lectureService.findLectureListByLectureId(lectureId);
            if (lectures.size()<1) {
                return new ResultBean(ResultCode.PARAM_ERROR, "不存在的讲座id");
            }
            Lecture lec = lectures.get(0);
            if (lec.getIsDone()==SysConst.NOT_USE) {
                return new ResultBean(ResultCode.PARAM_ERROR, "该讲座已结束");
            }
            lec.setIsDone(SysConst.NOT_USE);
            lec.setUpdateDate(new Date());
            lec.setUpdateUser(user.getUserId());

            lectureService.updateLectureByLectureId(lectureId, lec);

            return new ResultBean(ResultCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error(e.getMessage());
            return new ResultBean(ResultCode.OTHER_FAIL);
        }
    }

    public ResultBean deleteLecture(User user, long lectureId){
        List<Lecture> lectures = lectureService.findLectureListByLectureId(lectureId);
        if (lectures.size()<1) {
            return new ResultBean(ResultCode.PARAM_ERROR, "不存在的讲座id");
        }
        Lecture lec = lectures.get(0);
        if (lec.getIsEffective()==SysConst.NOT_LIVE) {
            return new ResultBean(ResultCode.PARAM_ERROR, "该讲座已删除");
        }
        lec.setIsEffective(SysConst.NOT_LIVE);
        lec.setUpdateDate(new Date());
        lec.setUpdateUser(user.getUserId());

        lectureService.updateLectureByLectureId(lectureId, lec);

        return new ResultBean(ResultCode.SUCCESS);

    }

    public ResultBean applyOrNot(User user, long lectureId){
        try {
            List<Lecture> lectures = lectureService.findLectureListByLectureId(lectureId);
            if (lectures.size()<1) {
                return new ResultBean(ResultCode.PARAM_ERROR, "不存在的讲座id");
            }
            Lecture lec = lectures.get(0);
            if (lec.getIsDone()==SysConst.NOT_LIVE) {
                return new ResultBean(ResultCode.PARAM_ERROR, "该讲座已结束");
            }
            Map<String, Object> map = new HashMap<>();
            map.put("isEffective", SysConst.LIVE);
            map.put("lectureId", lectureId);
            map.put("joinUser",user.getUserId());
            List<Applylecture> applylectures = applylectureService.findApplylectureListByQuery(map);
            if (applylectures.size()>0) {
                return new ResultBean(ResultCode.SYSTEM_FAILED, "你已申请该讲座");
            }


            return new ResultBean(ResultCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error(e.getMessage());
            return new ResultBean(ResultCode.OTHER_FAIL);
        }
    }

    public XSSFWorkbook export(long lectureId) {
        try {
            List<Lecture> lectures = lectureService.findLectureListByLectureId(lectureId);
            if (lectures.size()==0) {
                //return new ResultBean(ResultCode.PARAM_ERROR, "不存在校赛");
                return null;
            }
            Lecture lecture = lectures.get(0);
            if (lecture.getIsEffective()==SysConst.NOT_PASS) {
                //return new ResultBean(ResultCode.PARAM_ERROR, "不存在该校赛");
                return null;
            }

            // 第一步：定义一个新的工作簿
            XSSFWorkbook wb = new XSSFWorkbook();
            // 第二步：创建一个Sheet页
            XSSFSheet sheet = wb.createSheet("参赛证明");


            sheet.setDefaultRowHeight((short) (2 * 256));//设置行高
            sheet.setColumnWidth(0, 4000);//设置列宽
            sheet.setColumnWidth(1,5500);
            XSSFFont font = wb.createFont();
            font.setFontName("宋体");
            font.setFontHeightInPoints((short) 20);

            CellStyle cellStyle = wb.createCellStyle();
            cellStyle.setFont(font);
            cellStyle.setAlignment(HorizontalAlignment.CENTER);

            XSSFRow row0 = sheet.createRow(0);
            XSSFCell cell0 = row0.createCell(0);
            cell0.setCellValue(lecture.getLectureTitle());
            cell0.setCellStyle(cellStyle);



            XSSFRow row = sheet.createRow(1);
            XSSFCell cell = row.createCell(0);
            cell.setCellValue("参加人姓名 ");
            cell = row.createCell(1);
            cell.setCellValue("参加人学号");

            Map<String, Object> map = new HashMap<>();

            map.put("lectureId", lectureId);
            //  map.put("isPublic", isPublic);
            map.put("isEffective", 1);
            List<Map<String, Object>> list = userService.findUserByLectureId(map);
            XSSFRow rows;
            XSSFCell cells;
            int sum=2;
            if (list.size() >0) {
                for (Map<String, Object> mapTemp : list) {
                    mapTemp.put("createDate", DateUtils.convDateToStr((Date) mapTemp.get("createDate"), "yyyy-MM-dd HH:mm:ss"));
                    // 第三步：在这个sheet页里创建一行
                    rows = sheet.createRow(sum++);
                    // 第四步：在该行创建一个单元格
                    cells = rows.createCell(0);
                    // 第五步：在该单元格里设置值
                    cells.setCellValue((String)mapTemp.get("realname"));

                    cells = rows.createCell(1);
                    cells.setCellValue((long)mapTemp.get("studentId"));
                }
            }

            // 合并日期占两行(4个参数，分别为起始行，结束行，起始列，结束列)
            // 行和列都是从0开始计数，且起始结束都会合并
            // 这里是合并excel中日期的两行为一行
            CellRangeAddress region = new CellRangeAddress(0, 0, 0, 1);
            sheet.addMergedRegion(region);


            return wb;
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error(e.getMessage());
            return null;
        }
    }
}
