
package com.example.acm.utils;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class DateUtils {
    private static final SimpleDateFormat ORA_DATE_TIME_EXTENDED_FORMAT = new SimpleDateFormat("yyyyMMddHHmmss");
    private static final int ERRCODE = -99;

    public DateUtils() {
    }

    public static String calDateDayDiff(Date currentDate, Date preDate) {
        StringBuffer sb = new StringBuffer("");
        if(currentDate != null && preDate != null) {
            if(currentDate.before(preDate)) {
                return null;
            } else {
                Date date_1970 = new Date(0L);
                date_1970 = new Date(date_1970.getTime() - 28800000L);
                Long currentTime = Long.valueOf(currentDate.getTime());
                Long preTime = Long.valueOf(preDate.getTime());
                Date new_date = new Date(currentTime.longValue() - preTime.longValue() - 28800000L);
                Calendar c1 = Calendar.getInstance();
                c1.setTime(new_date);
                Calendar c2 = Calendar.getInstance();
                c2.setTime(date_1970);
                int year1 = c1.get(1);
                int year2 = c2.get(1);
                int ynum = year1 - year2;
                if(ynum > 0) {
                    sb.append(ynum).append("年");
                }

                int month1 = c1.get(2);
                int month2 = c2.get(2);
                int mnum = month1 - month2;
                if(mnum > 0) {
                    sb.append(mnum).append("月");
                }

                int day1 = c1.get(5);
                int day2 = c2.get(5);
                int dnum = day1 - day2;
                if(dnum > 0) {
                    sb.append(dnum).append("天");
                }

                int h1 = c1.get(11);
                int h2 = c2.get(11);
                int hnum = h1 - h2;
                if(hnum > 0) {
                    sb.append(hnum).append("小时");
                }

                int m1 = c1.get(12);
                int m2 = c2.get(12);
                int mmnum = m1 - m2;
                if(mmnum > 0) {
                    sb.append(mmnum).append("分钟");
                }

                int s1 = c1.get(13);
                int s2 = c2.get(13);
                int snum = s1 - s2;
                if(snum > 0) {
                    sb.append(snum).append("秒");
                } else {
                    sb.append("0秒");
                }

                return sb.toString();
            }
        } else {
            return null;
        }
    }

    public static String calRemainderTime(Date lastDateTime) {
        if(lastDateTime == null) {
            return null;
        } else {
            long time = (new Date()).getTime() - lastDateTime.getTime();
            if(time < 0L) {
                time = lastDateTime.getTime() - (new Date()).getTime();
            }

            SimpleDateFormat simpleDateFormat = null;
            if(time > 3600000L) {
                return null;
            } else {
                if(3599999L > time && time > 60000L) {
                    simpleDateFormat = new SimpleDateFormat("mm分ss秒");
                } else if(59999L > time) {
                    simpleDateFormat = new SimpleDateFormat("ss秒");
                }

                return simpleDateFormat.format(Long.valueOf(time));
            }
        }
    }

    public static String calTimeMinuteFormat(long min) throws ParseException {
        if(min < 1L) {
            return null;
        } else {
            long day = min / 1440L;
            long hour = min / 60L - day * 24L;
            long min1 = min - day * 24L * 60L - hour * 60L;
            StringBuffer sb = new StringBuffer();
            if(day != 0L) {
                sb.append(day + "天");
            }

            if(hour != 0L) {
                sb.append(hour + "小时");
            }

            if(min1 != 0L) {
                sb.append(min1 + "分钟");
            }

            return sb.toString();
        }
    }

    public static String calTimeToStr(Date d1, Date d2, int second) {
        if(d1 != null && d2 != null) {
            long l1 = d1.getTime();
            long l2 = d2.getTime();
            long l3 = l1 + (long)(second * 1000);
            long l4 = l3 - l2;
            int hour = (int)l4 / 3600000;
            if(hour > 0) {
                return hour + "小时";
            } else {
                int min = (int)l4 / '\uea60';
                if(min > 0) {
                    return min + "分钟";
                } else {
                    int sec = (int)l4 / 1000;
                    return sec > 0?sec + "秒":"0秒";
                }
            }
        } else {
            return null;
        }
    }

    public static boolean dateCompare(Date date1, Date date2) {
        if(date1 != null && date2 != null) {
            long d1 = date1.getTime();
            long d2 = date2.getTime();
            return d1 > d2;
        } else {
            return false;
        }
    }

    public static Date addSecDate(Date date, int second) {
        if(date == null) {
            return null;
        } else {
            Calendar ca = new GregorianCalendar();
            ca.setTime(date);
            int nextHour = ca.get(13) + second;
            ca.set(13, nextHour);
            return ca.getTime();
        }
    }

    public static Date addMinDate(Date date, int minute) {
        if(date == null) {
            return null;
        } else {
            Calendar ca = new GregorianCalendar();
            ca.setTime(date);
            int nextHour = ca.get(12) + minute;
            ca.set(12, nextHour);
            return ca.getTime();
        }
    }

    public static Date addHourDate(Date date, int hour) {
        if(date == null) {
            return null;
        } else {
            Calendar ca = new GregorianCalendar();
            ca.setTime(date);
            int nextHour = ca.get(10) + hour;
            ca.set(10, nextHour);
            return ca.getTime();
        }
    }

    public static Date addDayDate(Date date, int day) {
        if(date == null) {
            return null;
        } else {
            Calendar ca = new GregorianCalendar();
            ca.setTime(date);
            int nextday = ca.get(5) + day;
            ca.set(5, nextday);
            return ca.getTime();
        }
    }

    public static Date getDateBeforeMin(Date date, Integer min) {
        if(date == null) {
            return null;
        } else {
            Calendar ca = new GregorianCalendar();
            ca.setTime(date);
            int nextHour = ca.get(12) - min.intValue();
            ca.set(12, nextHour);
            return ca.getTime();
        }
    }

    public static Date getDateBeforeMin(Integer min) {
        Date date = new Date();
        Calendar ca = new GregorianCalendar();
        ca.setTime(date);
        int nextHour = ca.get(12) - min.intValue();
        ca.set(12, nextHour);
        return ca.getTime();
    }

    public static int getMinsBetweenDays(Date date1, Date date2) {
        if(date1 != null && date2 != null) {
            long dayNumber = 0L;
            long mins = 60000L;
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            try {
                String dateA = df.format(date1);
                String dateB = df.format(date2);
                Date d1 = df.parse(dateA);
                Date d2 = df.parse(dateB);
                dayNumber = (d1.getTime() - d2.getTime()) / mins;
            } catch (Exception var11) {
                var11.printStackTrace();
            }

            return (int)dayNumber;
        } else {
            return -99;
        }
    }

    public static int getSecBetweenDays(Date date1, Date date2) {
        if(date1 != null && date2 != null) {
            long dayNumber = 0L;
            long sec = 1000L;
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            try {
                String dateA = df.format(date1);
                String dateB = df.format(date2);
                Date d1 = df.parse(dateA);
                Date d2 = df.parse(dateB);
                dayNumber = (d1.getTime() - d2.getTime()) / sec;
            } catch (Exception var11) {
                var11.printStackTrace();
            }

            return (int)dayNumber;
        } else {
            return -99;
        }
    }

    public static int getDayNumBetweenDays(Date currentDate, Date preDate) {
        if(currentDate != null && preDate != null) {
            Calendar c1 = Calendar.getInstance();
            c1.setTime(currentDate);
            Calendar c2 = Calendar.getInstance();
            c2.setTime(preDate);
            int year1 = c1.get(1);
            int day1 = c1.get(6);
            int year2 = c2.get(1);
            int day2 = c2.get(6);
            return (year1 - year2) * 365 + (day1 - day2);
        } else {
            return -99;
        }
    }

    public static Date getFormatDate(Date date, String pattern) {
        if(date != null && pattern != null && !"".equals(pattern)) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);

            try {
                String datestr = df.format(date);
                date = df.parse(datestr);
            } catch (Exception var4) {
                ;
            }

            return date;
        } else {
            return null;
        }
    }

    public static String convDateToStr(Date theDate, DateFormat dateFormat) {
        return theDate != null && dateFormat != null?dateFormat.format(theDate):"";
    }

    public static String convDateToStr(Date date, String pattern) {
        if(date != null && pattern != null && !"".equals(pattern)) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            String datestr = "";

            try {
                datestr = df.format(date);
            } catch (Exception var5) {
                ;
            }

            return datestr;
        } else {
            return null;
        }
    }

    public static Date convStrToDate(String dateStr, String pattern) {
        if(dateStr != null && !"".equals(dateStr)) {
            if(pattern != null && !"".equals(pattern)) {
                SimpleDateFormat df = new SimpleDateFormat(pattern);
                Date date = null;

                try {
                    date = df.parse(dateStr);
                } catch (Exception var5) {
                    ;
                }

                return date;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public static Date convStrToDate(String dateStr) throws Exception {
        if(dateStr != null && !dateStr.equals("")) {
            if(dateStr.length() == 0) {
                return null;
            } else {
                SimpleDateFormat format = null;
                Date date = null;
                int i = dateStr.split(" ").length;
                if(i >= 2) {
                    if(dateStr.contains("-")) {
                        format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        date = format.parse(dateStr);
                    } else if(dateStr.contains("/")) {
                        format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                        date = format.parse(dateStr);
                    }
                } else if(dateStr.contains("-")) {
                    format = new SimpleDateFormat("yyyy-MM-dd");
                    date = format.parse(dateStr);
                } else if(dateStr.contains("/")) {
                    format = new SimpleDateFormat("yyyy/MM/dd");
                    date = format.parse(dateStr);
                } else if(dateStr.contains(".")) {
                    format = new SimpleDateFormat("yyyy.MM.dd");
                    date = format.parse(dateStr);
                } else {
                    format = new SimpleDateFormat("yyyyMMdd");
                    date = format.parse(dateStr);
                }

                return date;
            }
        } else {
            return null;
        }
    }

    public static String convDateToCnString(Date date) {
        if(date == null) {
            return null;
        } else {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            String[] weekDays = new String[]{"星期六", "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"};
            String result = cal.get(1) + "年" + (cal.get(2) + 1) + "月" + cal.get(5) + "日" + " " + weekDays[cal.get(7)];
            return result;
        }
    }

    public static String convLongToStr(long longTime, String dataFormat, String timeFormat) {
        String str = "";
        Date d = new Date(longTime);
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat s = new SimpleDateFormat(timeFormat);
        if(isSameDay(date, d)) {
            str = s.format(d);
        } else {
            s = new SimpleDateFormat(dataFormat);
            str = s.format(d);
        }

        return str;
    }

    public static String convLongToStr(long longTime, String pattern) {
        if(pattern != null && !"".equals(pattern)) {
            Date d = new Date(longTime);
            SimpleDateFormat s = new SimpleDateFormat(pattern);
            return s.format(d);
        } else {
            return null;
        }
    }

    public static Long convStrToLong(String date) {
        Date d = null;

        try {
            d = convStrToDate(date);
        } catch (Exception var3) {
            return null;
        }

        if(d == null) {
            return null;
        } else {
            Calendar c = Calendar.getInstance();
            c.setTime(d);
            return Long.valueOf(c.getTimeInMillis());
        }
    }

    public static Long convDateToLong(Date date) {
        if(date == null) {
            return null;
        } else {
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            return Long.valueOf(c.getTimeInMillis());
        }
    }

    public static Date convLongToDate(Long date, String pattern) {
        if(date == null) {
            return null;
        } else if(pattern != null && !"".equals(pattern)) {
            String dateString = convLongToStr(date.longValue(), pattern);
            return dateString == null?null:convStrToDate(dateString, pattern);
        } else {
            return null;
        }
    }

    public static Date getTimeBeginOfToday(Date date) {
        if(date == null) {
            return null;
        } else {
            String nowStr = convDateToStr(date, "yyyy-MM-dd");
            System.out.println(nowStr);
            StringBuilder sb = new StringBuilder("");
            sb.append(nowStr);
            sb.append(" 00:00:00.000");
            return convStrToDate(sb.toString(), "yyyy-MM-dd HH:mm:ss.SSS");
        }
    }

    public static String getTimeBeginOfTodayStr(Date date) {
        if(date == null) {
            return null;
        } else {
            String nowStr = convDateToStr(date, "yyyy-MM-dd");
            System.out.println(nowStr);
            StringBuilder sb = new StringBuilder("");
            sb.append(nowStr);
            sb.append(" 00:00:00");
            return sb.toString();
        }
    }

    public static Date getTimeEndOfDay(Date date) {
        if(date == null) {
            return null;
        } else {
            String nowStr = convDateToStr(date, "yyyy-MM-dd");
            System.out.println(nowStr);
            StringBuilder sb = new StringBuilder("");
            sb.append(nowStr);
            sb.append(" 23:59:59.999");
            return convStrToDate(sb.toString(), "yyyy-MM-dd HH:mm:ss.SSS");
        }
    }

    public static String getTimeEndOfDayStr(Date date) {
        if(date == null) {
            return null;
        } else {
            String nowStr = convDateToStr(date, "yyyy-MM-dd");
            System.out.println(nowStr);
            StringBuilder sb = new StringBuilder("");
            sb.append(nowStr);
            sb.append(" 23:59:59");
            return sb.toString();
        }
    }

    public static Date getBeginDateTimeByDateStr(String date) {
        if(date == null) {
            return null;
        } else {
            StringBuilder dataBuilder1 = new StringBuilder();
            dataBuilder1.append(date);
            dataBuilder1.append(" 00:00:00.000");
            Date d = convStrToDate(dataBuilder1.toString(), "yyyy-MM-dd HH:mm:ss.SSS");
            return d;
        }
    }

    public static Date getEndDateTimeByDateStr(String date) {
        if(date == null) {
            return null;
        } else {
            StringBuilder dataBuilder1 = new StringBuilder();
            dataBuilder1.append(date);
            dataBuilder1.append(" 23:59:59.999");
            Date d = convStrToDate(dataBuilder1.toString(), "yyyy-MM-dd HH:mm:ss.SSS");
            return d;
        }
    }

    public static boolean isSameDay(Date date1, Date date2) {
        if(date1 == null) {
            return false;
        } else {
            Calendar c1 = Calendar.getInstance();
            c1.setTime(date1);
            int day1 = c1.get(6);
            if(date2 == null) {
                return false;
            } else {
                Calendar c2 = Calendar.getInstance();
                c2.setTime(date2);
                int day2 = c2.get(6);
                return day1 == day2;
            }
        }
    }

    public static int getMondayPlus() {
        Calendar cd = Calendar.getInstance();
        int dayOfWeek = cd.get(7) - 1;
        System.out.println("dayweek:" + dayOfWeek);
        return dayOfWeek == 0?-6:1 - dayOfWeek;
    }

    public static String getCurrentMonday() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        int mondayPlus = getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(5, mondayPlus);
        Date monday = currentDate.getTime();
        return sdf.format(monday);
    }

    public static String getDateStr(int year, int month, int day) {
        StringBuffer result = new StringBuffer();
        result.append(String.valueOf(year)).append("-").append(month < 10?"0" + String.valueOf(month):String.valueOf(month)).append("-").append(day < 10?"0" + String.valueOf(day):String.valueOf(day));
        return result.toString();
    }

    public static Integer year() {
        Calendar calendar = Calendar.getInstance();
        return new Integer(calendar.get(1));
    }

    public static Integer month() {
        Calendar calendar = Calendar.getInstance();
        return new Integer(calendar.get(2) + 1);
    }

    public static Integer day() {
        Calendar calendar = Calendar.getInstance();
        return new Integer(calendar.get(5));
    }

    public static String getDayNumBeforeToday(Integer dayNum) {
        if(dayNum == null) {
            return null;
        } else {
            Date date = new Date(System.currentTimeMillis());
            GregorianCalendar gc = (GregorianCalendar)Calendar.getInstance();
            gc.setTime(date);
            gc.add(5, -dayNum.intValue());
            return doTransform(convDateToStr(gc.getTime(), getOraExtendDateTimeFormat())).substring(0, 10);
        }
    }

    public static String getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        return getDateStr(calendar.get(1), calendar.get(2) + 1, calendar.get(5));
    }

    public static String getCurrentDateTime() {
        Calendar newCalendar = Calendar.getInstance();
        newCalendar.setTimeInMillis(System.currentTimeMillis());
        int year = newCalendar.get(1);
        int month = newCalendar.get(2) + 1;
        int day = newCalendar.get(5);
        int hour = newCalendar.get(11);
        int minute = newCalendar.get(12);
        int second = newCalendar.get(13);
        return doTransform(year, month, day, hour, minute, second);
    }

    private static DateFormat getOraExtendDateTimeFormat() {
        SimpleDateFormat dateTimeFormat = (SimpleDateFormat)ORA_DATE_TIME_EXTENDED_FORMAT.clone();
        dateTimeFormat.setLenient(false);
        return dateTimeFormat;
    }

    private static String doTransform(String date) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(date.substring(0, 4));
        buffer.append("-");
        buffer.append(date.substring(4, 6));
        buffer.append("-");
        buffer.append(date.substring(6, 8));
        buffer.append(" ");
        buffer.append(date.substring(8, 10));
        buffer.append(":");
        buffer.append(date.substring(10, 12));
        buffer.append(":");
        buffer.append(date.substring(12, 14));
        return buffer.toString();
    }

    private static String doTransform(int year, int month, int day, int hour, int minute, int second) {
        StringBuffer result = new StringBuffer();
        result.append(String.valueOf(year)).append("-").append(month < 10?"0" + String.valueOf(month):String.valueOf(month)).append("-").append(day < 10?"0" + String.valueOf(day):String.valueOf(day)).append(" ").append(hour < 10?"0" + String.valueOf(hour):String.valueOf(hour)).append(":").append(minute < 10?"0" + String.valueOf(minute):String.valueOf(minute)).append(":").append(second < 10?"0" + String.valueOf(second):String.valueOf(second));
        return result.toString();
    }

    public static List<Date> findDates(Date dBegin, Date dEnd) {
        if(dBegin != null && dEnd != null) {
            List lDate = new ArrayList();
            lDate.add(dBegin);
            Calendar calBegin = Calendar.getInstance();
            calBegin.setTime(dBegin);
            Calendar calEnd = Calendar.getInstance();
            calEnd.setTime(dEnd);

            while(dEnd.after(calBegin.getTime())) {
                calBegin.add(5, 1);
                lDate.add(calBegin.getTime());
            }

            return lDate;
        } else {
            return null;
        }
    }

    public static List<Date> findMonths(Date mBegin, Date mEnd) {
        if(mBegin != null && mEnd != null) {
            List<Date> lMonth = new ArrayList();
            lMonth.add(mBegin);
            Calendar calBegin = Calendar.getInstance();
            calBegin.setTime(mBegin);
            Calendar calEnd = Calendar.getInstance();
            calEnd.setTime(mEnd);

            while(mEnd.after(calBegin.getTime())) {
                calBegin.add(2, 1);
                lMonth.add(calBegin.getTime());
            }

            return lMonth;
        } else {
            return null;
        }
    }

    public static Date getTimeEndOfDayAfterTaday(Integer dayNum) {
        if(dayNum == null) {
            return null;
        } else {
            String nowStr = getDayNumAfterToday(dayNum);
            System.out.println(nowStr);
            StringBuilder sb = new StringBuilder("");
            sb.append(nowStr);
            sb.append(" 23:59:59:999");
            return convStrToDate(sb.toString(), "yyyy-MM-dd HH:mm:ss:SSS");
        }
    }

    public static String getDayNumAfterToday(Integer dayNum) {
        if(dayNum == null) {
            return null;
        } else {
            Date date = new Date(System.currentTimeMillis());
            GregorianCalendar gc = (GregorianCalendar)Calendar.getInstance();
            gc.setTime(date);
            gc.add(5, dayNum.intValue());
            return doTransform(convDateToStr(gc.getTime(), getOraExtendDateTimeFormat())).substring(0, 10);
        }
    }

    public static Date getFirstDayTimeFromMonth(String month) {
        if(month != null && !"".equals(month)) {
            Calendar c = Calendar.getInstance();
            c.setTime(convStrToDate(month, "yyyy-MM"));
            c.set(5, 1);
            c.set(11, 0);
            c.set(12, 0);
            c.set(13, 0);
            c.set(14, 0);
            return c.getTime();
        } else {
            return null;
        }
    }

    public static Date getLastDayTimeFromMonth(String month) {
        if(month != null && !"".equals(month)) {
            Calendar c = Calendar.getInstance();
            c.setTime(convStrToDate(month, "yyyy-MM"));
            c.set(5, c.getActualMaximum(5));
            c.set(11, 23);
            c.set(12, 59);
            c.set(13, 59);
            c.set(14, 999);
            return c.getTime();
        } else {
            return null;
        }
    }

    public static Date getFirstDayFromDate(Date date) {
        if(date == null) {
            return null;
        } else {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.set(5, 1);
            return convStrToDate(format.format(c.getTime()), "yyyy-MM-dd");
        }
    }

    public static Date getLastDayFromDate(Date date) {
        if(date == null) {
            return null;
        } else {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.set(5, c.getActualMaximum(5));
            return convStrToDate(format.format(c.getTime()), "yyyy-MM-dd");
        }
    }

    public static boolean monthCompare(String month1, String month2) {
        if(month1 != null && month2 != null) {
            if(!"".equals(month1) && !"".equals(month2)) {
                Calendar c = Calendar.getInstance();
                c.setTime(convStrToDate(month1, "yyyy-MM"));
                long d1 = c.getTimeInMillis();
                c.setTime(convStrToDate(month2, "yyyy-MM"));
                long d2 = c.getTimeInMillis();
                return d1 > d2;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static String getCurrentMonth() {
        DateFormat df1 = new SimpleDateFormat("yyyy-MM");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        Date date1 = c.getTime();
        String currentMonth = df1.format(date1);
        return currentMonth;
    }

    public static Date getYesterday() {
        DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        int nextday = c.get(5) - 1;
        c.set(5, nextday);
        return convStrToDate(df1.format(c.getTime()), "yyyy-MM-dd");
    }

    public static Date getToday() {
        DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        return convStrToDate(df1.format(c.getTime()), "yyyy-MM-dd");
    }

    public static boolean isFirstDayOfMonth() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.set(5, 1);
        return isSameDay(c.getTime(), new Date());
    }

    public static String getLastMonth() {
        DateFormat df1 = new SimpleDateFormat("yyyy-MM");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(2, -1);
        Date date1 = c.getTime();
        String lastMonth = df1.format(date1);
        return lastMonth;
    }

    public static Date getDayBeforeDate(Date date, Integer dayNum) {
        if(date == null) {
            return null;
        } else if(dayNum == null) {
            return null;
        } else {
            GregorianCalendar gc = (GregorianCalendar)Calendar.getInstance();
            gc.setTime(date);
            gc.add(5, -dayNum.intValue());
            return gc.getTime();
        }
    }

    public static String getChineseMonthFromMonth(String month) {
        if(month != null && !"".equals(month)) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月");
            Calendar c = Calendar.getInstance();
            c.setTime(convStrToDate(month, "yyyy-MM"));
            return format.format(c.getTime());
        } else {
            return null;
        }
    }

    public static Date parserMonth(String datetime) {
        if(datetime != null && !"".equals(datetime)) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");

            try {
                return format.parse(datetime);
            } catch (ParseException var3) {
                var3.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }

    public static String getSundayBeforeDay(String date, Integer num, String pattern) {
        if(date != null && pattern != null) {
            if(!"".equals(date) && !"".equals(pattern)) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(convStrToDate(date, pattern));
                cal.add(5, -num.intValue() * 7);
                Date firstSunday = cal.getTime();
                return convDateToStr(firstSunday, pattern);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public static String getLastSundayBeforeToday(Date day, String pattern) {
        if(day != null && pattern != null) {
            if("".equals(pattern)) {
                return null;
            } else {
                Calendar cal = Calendar.getInstance();
                cal.setTime(day);

                while(cal.get(7) != 1) {
                    cal.add(5, -1);
                }

                Date firstSunday = cal.getTime();
                return convDateToStr(firstSunday, pattern);
            }
        } else {
            return null;
        }
    }

    public static String getLastMondayBeforeSunday(Date day, String pattern) {
        if(day != null && pattern != null) {
            if("".equals(pattern)) {
                return null;
            } else {
                Calendar cal = Calendar.getInstance();
                cal.setTime(day);

                while(cal.get(7) != 2) {
                    cal.add(5, -1);
                }

                Date firstMonday = cal.getTime();
                return convDateToStr(firstMonday, pattern);
            }
        } else {
            return null;
        }
    }

    public static Time getTime(String timeStr, String pattern) {
        if(timeStr != null && pattern != null) {
            if("".equals(pattern)) {
                return null;
            } else {
                SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                Time time = null;

                try {
                    time = new Time(sdf.parse(timeStr).getTime());
                } catch (Exception var5) {
                    ;
                }

                return time;
            }
        } else {
            return null;
        }
    }

    public static String getTimeStr(Time time, String pattern) {
        if(time != null && pattern != null) {
            if("".equals(pattern)) {
                return null;
            } else {
                SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                String timeStr = "";

                try {
                    timeStr = sdf.format(time);
                } catch (Exception var5) {
                    ;
                }

                return timeStr;
            }
        } else {
            return null;
        }
    }

    public static Time secToTime(Long time) {
        String timeStr = secToHour(time);
        if(null == timeStr) {
            return null;
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            Time timeing = null;

            try {
                timeing = new Time(sdf.parse(timeStr).getTime());
            } catch (Exception var5) {
                ;
            }

            return timeing;
        }
    }

    public static long getAllSeconds(Time time) {
        if(null == time) {
            return 0L;
        } else {
            int hours = time.getHours();
            int minutes = time.getMinutes();
            int seconds = time.getSeconds();
            return (long)(hours * 60 * 60 + minutes * 60 + seconds);
        }
    }

    public static Time timePlus(Time time, Time timePlus) {
        long timeSeconds = getAllSeconds(time);
        long timeSecondsMinus = getAllSeconds(timePlus);
        long minus = timeSeconds + timeSecondsMinus;
        return minus < 0L?null:(minus == 0L?getTime("00:00:00", "HH:mm:ss"):secToTime(Long.valueOf(minus)));
    }

    public static Time timeMinus(Time time, Time timeMinus) {
        long timeSeconds = getAllSeconds(time);
        long timeSecondsMinus = getAllSeconds(timeMinus);
        long minus = timeSeconds - timeSecondsMinus;
        return minus < 0L?null:(minus == 0L?getTime("00:00:00", "HH:mm:ss"):secToTime(Long.valueOf(minus)));
    }

    public static String secToHour(Long time) {
        if(null == time) {
            return null;
        } else {
            String timeStr = null;
            long hour = 0L;
            long minute = 0L;
            long second = 0L;
            if(time.longValue() <= 0L) {
                return "00:00";
            } else {
                minute = time.longValue() / 60L;
                if(minute < 60L) {
                    second = time.longValue() % 60L;
                    timeStr = "00:" + unitFormat(minute) + ":" + unitFormat(second);
                } else {
                    hour = minute / 60L;
                    minute %= 60L;
                    second = time.longValue() - hour * 3600L - minute * 60L;
                    timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":" + unitFormat(second);
                }

                return timeStr;
            }
        }
    }

    public static String secToMinute(Long time) {
        if(null == time) {
            return null;
        } else {
            String timeStr = null;
            long minute = 0L;
            long second = 0L;
            if(time.longValue() <= 0L) {
                return "00:00";
            } else {
                minute = time.longValue() / 60L;
                second = time.longValue() % 60L;
                timeStr = unitFormat(minute) + ":" + unitFormat(second);
                return timeStr;
            }
        }
    }

    private static String unitFormat(long i) {
        String retStr = null;
        if(i >= 0L && i < 10L) {
            retStr = "0" + Long.toString(i);
        } else {
            retStr = "" + i;
        }

        return retStr;
    }

    public static int compare_date(Date date1, Date date2) {
        try {
            return date1.getTime() > date2.getTime()?1:(date1.getTime() < date2.getTime()?-1:0);
        } catch (Exception var3) {
            var3.printStackTrace();
            return 0;
        }
    }
    public static int differentDays(Date date1,Date date2)
    {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1= cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if(year1 != year2)   //同一年
        {
            int timeDistance = 0 ;
            for(int i = year1 ; i < year2 ; i ++)
            {
                if(i%4==0 && i%100!=0 || i%400==0)    //闰年
                {
                    timeDistance += 366;
                }
                else    //不是闰年
                {
                    timeDistance += 365;
                }
            }

            return timeDistance + (day2-day1) ;
        }
        else    //不同年
        {
            System.out.println("判断day2 - day1 : " + (day2-day1));
            return day2-day1;
        }
    }
    public static void main(String[] args) throws Exception {
        Date d1 = new Date();
        Date d2 = addDayDate(d1, -1);
        System.out.println("calDateDayDiff===>" + calDateDayDiff(d1, d2));
        System.out.println("calDateDayDiff===>" + calDateDayDiff((Date)null, (Date)null));
        System.out.println("calRemainderTime===>" + calRemainderTime((Date)null));
        System.out.println("calRemainderTime===>" + calRemainderTime(addMinDate(new Date(), -59)));
        System.out.println("calRemainderTime===>" + calRemainderTime(addSecDate(new Date(), -59)));
        System.out.println("calTimeMinuteFormat===>" + calTimeMinuteFormat(-1L));
        System.out.println("calTimeMinuteFormat===>" + calTimeMinuteFormat(20L));
        System.out.println("calTimeMinuteFormat===>" + calTimeMinuteFormat(120L));
        System.out.println("calTimeMinuteFormat===>" + calTimeMinuteFormat(3000L));
        System.out.println("calTimeToStr===>" + calTimeToStr((Date)null, (Date)null, 1));
        System.out.println("calTimeToStr===>" + calTimeToStr(new Date(), new Date(), 0));
        System.out.println("calTimeToStr===>" + calTimeToStr(addSecDate(new Date(), 1), new Date(), 1));
        System.out.println("calTimeToStr===>" + calTimeToStr(addSecDate(new Date(), 1), new Date(), 100));
        System.out.println("dateCompare===>" + dateCompare((Date)null, (Date)null));
        System.out.println("dateCompare===>" + dateCompare(new Date(), new Date()));
        System.out.println("dateCompare===>" + dateCompare(addSecDate(new Date(), 1), new Date()));
        System.out.println("dateCompare===>" + dateCompare(addSecDate(new Date(), -1), new Date()));
        System.out.println("addSecDate===>" + addSecDate((Date)null, 5));
        System.out.println("addSecDate===>" + addSecDate(new Date(), 5));
        System.out.println("addMinDate===>" + addMinDate((Date)null, 5));
        System.out.println("addMinDate===>" + addMinDate(new Date(), 5));
        System.out.println("addHourDate===>" + addHourDate((Date)null, 5));
        System.out.println("addHourDate===>" + addHourDate(new Date(), 5));
        System.out.println("addDayDate===>" + addDayDate((Date)null, 5));
        System.out.println("addDayDate===>" + addDayDate(new Date(), 5));
        System.out.println("getDateBeforeMin===>" + getDateBeforeMin((Date)null, Integer.valueOf(5)));
        System.out.println("getDateBeforeMin===>" + getDateBeforeMin(new Date(), Integer.valueOf(5)));
        System.out.println("getDateBeforeMin===>" + getDateBeforeMin(Integer.valueOf(10)));
        System.out.println("getMinsBetweenDays===>" + getMinsBetweenDays((Date)null, (Date)null));
        System.out.println("getMinsBetweenDays===>" + getMinsBetweenDays(addMinDate(new Date(), 5), new Date()));
        System.out.println("getSecBetweenDays===>" + getSecBetweenDays((Date)null, (Date)null));
        System.out.println("getSecBetweenDays===>" + getSecBetweenDays(addMinDate(new Date(), 5), new Date()));
        System.out.println("getDayNumBetweenDays===>" + getDayNumBetweenDays((Date)null, (Date)null));
        System.out.println("getDayNumBetweenDays===>" + getDayNumBetweenDays(addDayDate(new Date(), 3), new Date()));
        System.out.println("getFormatDate===>" + getFormatDate((Date)null, (String)null));
        System.out.println("getFormatDate===>" + getFormatDate(new Date(), ""));
        System.out.println("getFormatDate===>" + getFormatDate(new Date(), "yyyy-MM-dd HH:mm:ss:SSS"));
        System.out.println("getFormatDate===>" + getFormatDate(new Date(), "yyyy-MM-dd"));
        System.out.println("getFormatDate===>" + getFormatDate(new Date(), "yyyy-MM-dd HH:mm"));
        System.out.println("convDateToStr1===>" + convDateToStr(new Date(), (DateFormat)(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))));
        System.out.println("convDateToStr1===>" + convDateToStr(new Date(), (DateFormat)(new SimpleDateFormat("yyyy-MM-dd"))));
        System.out.println("convDateToStr1===>" + convDateToStr(new Date(), (DateFormat)(new SimpleDateFormat("MM-dd"))));
        System.out.println("convDateToStr2===>" + convDateToStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
        System.out.println("convDateToStr2===>" + convDateToStr(new Date(), "yyyy-MM-dd"));
        System.out.println("convDateToStr2===>" + convDateToStr(new Date(), "MM-dd"));
        System.out.println("convStrToDate1===>" + convStrToDate("", ""));
        System.out.println("convStrToDate1===>" + convStrToDate((String)null, (String)null));
        System.out.println("convStrToDate1===>" + convStrToDate("2015-02-14", "yyyy-MM-dd"));
        System.out.println("convStrToDate1===>" + convStrToDate("2010/09/19 16:47:18.000", "yyyy/MM/dd HH:mm:ss"));
        System.out.println("convStrToDate2===>" + convStrToDate(""));
        System.out.println("convStrToDate2===>" + convStrToDate((String)null));
        System.out.println("convStrToDate2===>" + convStrToDate("2015-02-14"));
        System.out.println("convStrToDate2===>" + convStrToDate("2010/09/19 16:47:18.000"));
        System.out.println("convStrToDate2===>" + convStrToDate("2015/02/14"));
        System.out.println("convStrToDate2===>" + convStrToDate("2010.09.19"));
        System.out.println("convStrToDate2===>" + convStrToDate("20100919"));
        System.out.println("convDateToCnString===>" + convDateToCnString((Date)null));
        System.out.println("convDateToCnString===>" + convDateToCnString(new Date()));
        System.out.println("convDateToCnString===>" + convDateToCnString(addDayDate(new Date(), 1)));
        System.out.println("convDateToCnString===>" + convDateToCnString(addDayDate(new Date(), 2)));
        System.out.println("convDateToCnString===>" + convDateToCnString(addDayDate(new Date(), 3)));
        System.out.println("convDateToCnString===>" + convDateToCnString(addDayDate(new Date(), 4)));
        System.out.println("convDateToCnString===>" + convDateToCnString(addDayDate(new Date(), 5)));
        System.out.println("convDateToCnString===>" + convDateToCnString(addDayDate(new Date(), 6)));
        System.out.println("convDateToCnString===>" + convDateToCnString(addDayDate(new Date(), 7)));
        System.out.println("convLongToStr1===>" + convLongToStr(addDayDate(new Date(), -1).getTime(), "", ""));
        System.out.println("convLongToStr1===>" + convLongToStr(addDayDate(new Date(), -1).getTime(), "yyyy-MM-dd", "HH:mm"));
        System.out.println("convLongToStr1===>" + convLongToStr((new Date()).getTime(), "yyyy-MM-dd", "HH:mm"));
        System.out.println("convLongToStr2===>" + convLongToStr((new Date()).getTime(), ""));
        System.out.println("convLongToStr2===>" + convLongToStr((new Date()).getTime(), (String)null));
        System.out.println("convLongToStr2===>" + convLongToStr((new Date()).getTime(), "yyyy-MM-dd"));
        System.out.println("convLongToStr2===>" + convLongToStr((new Date()).getTime(), "yyyy-MM-dd HH:mm:ss:SSS"));
        System.out.println("convStrToLong===>" + convStrToLong("2010/09/19 16:47:18.000"));
        System.out.println("convStrToLong===>" + convStrToLong("2010/09/19"));
        System.out.println("convStrToLong===>" + convStrToLong("2010-09-19"));
        System.out.println("convStrToLong===>" + convStrToLong(""));
        System.out.println("convStrToLong===>" + convStrToLong((String)null));
        System.out.println("convDateToLong===>" + convDateToLong((Date)null));
        System.out.println("convDateToLong===>" + convDateToLong(new Date()));
        System.out.println("convLongToDate===>" + convLongToDate(Long.valueOf((new Date()).getTime()), ""));
        System.out.println("convLongToDate===>" + convLongToDate(Long.valueOf((new Date()).getTime()), (String)null));
        System.out.println("convLongToDate===>" + convLongToDate(Long.valueOf((new Date()).getTime()), "yyyy-MM-dd"));
        System.out.println("convLongToDate===>" + convLongToDate(Long.valueOf((new Date()).getTime()), "yyyy-MM-dd HH:mm:ss"));
        System.out.println("getTimeBeginOfToday===>" + getTimeBeginOfToday((Date)null));
        System.out.println("getTimeBeginOfToday===>" + getTimeBeginOfToday(new Date()));
        System.out.println("getTimeEndOfDay===>" + getTimeEndOfDay((Date)null));
        System.out.println("getTimeEndOfDay===>" + getTimeEndOfDay(new Date()));
        System.out.println("getBeginDateTimeByDateStr===>" + getBeginDateTimeByDateStr((String)null));
        System.out.println("getBeginDateTimeByDateStr===>" + getBeginDateTimeByDateStr("2010-09-19"));
        System.out.println("getEndDateTimeByDateStr===>" + getEndDateTimeByDateStr((String)null));
        System.out.println("getEndDateTimeByDateStr===>" + getEndDateTimeByDateStr("2010-09-19"));
        System.out.println("isSameDay===>" + isSameDay((Date)null, (Date)null));
        System.out.println("isSameDay===>" + isSameDay(new Date(), new Date()));
        System.out.println("isSameDay===>" + isSameDay(addDayDate(new Date(), 1), new Date()));
        System.out.println("getMondayPlus===>" + getMondayPlus());
        System.out.println("getCurrentMonday===>" + getCurrentMonday());
        System.out.println("getDateStr===>" + getDateStr(2016, 5, 1));
        System.out.println("getDateStr===>" + getDateStr(16, 5, 1));
        System.out.println("year===>" + year());
        System.out.println("month===>" + month());
        System.out.println("day===>" + day());
        System.out.println("getDayNumBeforeToday===>" + getDayNumBeforeToday((Integer)null));
        System.out.println("getDayNumBeforeToday===>" + getDayNumBeforeToday(Integer.valueOf(1)));
        System.out.println("getCurrentDate===>" + getCurrentDate());
        System.out.println("getCurrentDateTime===>" + getCurrentDateTime());
        System.out.println("doTransform===>" + doTransform(2016, 5, 23, 1, 2, 3));
        System.out.println("doTransform===>" + doTransform(15, 11, 12, 23, 22, 56));
        System.out.println("findDates===>" + findDates((Date)null, (Date)null));
        System.out.println("findDates===>" + findDates(addDayDate(new Date(), -2), new Date()));
        System.out.println("findMonths===>" + findMonths((Date)null, (Date)null));
        System.out.println("findMonths===>" + findMonths(addDayDate(new Date(), -20), new Date()));
        System.out.println("getTimeEndOfDayAfterTaday===>" + getTimeEndOfDayAfterTaday((Integer)null));
        System.out.println("getTimeEndOfDayAfterTaday===>" + getTimeEndOfDayAfterTaday(Integer.valueOf(2)));
        System.out.println("getDayNumAfterToday===>" + getDayNumAfterToday((Integer)null));
        System.out.println("getDayNumAfterToday===>" + getDayNumAfterToday(Integer.valueOf(3)));
        System.out.println("getFirstDayTimeFromMonth===>" + getFirstDayTimeFromMonth((String)null));
        System.out.println("getFirstDayTimeFromMonth===>" + getFirstDayTimeFromMonth("2016-01"));
        Long startDate = Long.valueOf(getFirstDayTimeFromMonth(getLastMonth()).getTime());
        System.out.println("l1======================" + startDate);
        System.out.println("getLastDayTimeFromMonth===>" + getLastDayTimeFromMonth(""));
        System.out.println("getLastDayTimeFromMonth===>" + getLastDayTimeFromMonth("2016-02"));
        long endDate = getLastDayTimeFromMonth(getLastMonth()).getTime();
        System.out.println("l2======================" + endDate);
        System.out.println("getFirstDayFromDate===>" + getFirstDayFromDate((Date)null));
        System.out.println("getFirstDayFromDate===>" + getFirstDayFromDate(new Date()));
        System.out.println("getLastDayFromDate===>" + getLastDayFromDate((Date)null));
        System.out.println("getLastDayFromDate===>" + getLastDayFromDate(new Date()));
        System.out.println("monthCompare===>" + monthCompare((String)null, ""));
        System.out.println("monthCompare===>" + monthCompare("2016-04", "2016-05"));
        System.out.println("monthCompare===>" + monthCompare("2016-05", "2016-04"));
        System.out.println("getCurrentMonth===>" + getCurrentMonth());
        System.out.println("getYesterday===>" + getYesterday());
        System.out.println("getToday===>" + getToday());
        System.out.println("isFirstDayOfMonth===>" + isFirstDayOfMonth());
        System.out.println("getLastMonth===>" + getLastMonth());
        System.out.println("getDayBeforeDate===>" + getDayBeforeDate((Date)null, Integer.valueOf(3)));
        System.out.println("getDayBeforeDate===>" + getDayBeforeDate(new Date(), (Integer)null));
        System.out.println("getDayBeforeDate===>" + getDayBeforeDate(new Date(), Integer.valueOf(3)));
        System.out.println("getChineseMonthFromMonth===>" + getChineseMonthFromMonth(""));
        System.out.println("getChineseMonthFromMonth===>" + getChineseMonthFromMonth((String)null));
        System.out.println("getChineseMonthFromMonth===>" + getChineseMonthFromMonth("2016-05"));
        System.out.println("parserMonth===>" + parserMonth(""));
        System.out.println("parserMonth===>" + parserMonth((String)null));
        System.out.println("parserMonth===>" + parserMonth((String)null));
        System.out.println("getSundayBeforeDay===>" + getSundayBeforeDay((String)null, Integer.valueOf(1), (String)null));
        System.out.println("getSundayBeforeDay===>" + getSundayBeforeDay("", Integer.valueOf(1), ""));
        System.out.println("getSundayBeforeDay===>" + getSundayBeforeDay("2010-09-19", Integer.valueOf(1), "yyyy-MM-dd"));
        System.out.println("getSundayBeforeDay===>" + getSundayBeforeDay("2010/09/19 16:47:18.000", Integer.valueOf(1), "yyyy/MM/dd HH:mm:ss.SSS"));
        System.out.println("getLastSundayBeforeToday===>" + getLastSundayBeforeToday((Date)null, (String)null));
        System.out.println("getLastSundayBeforeToday===>" + getLastSundayBeforeToday(new Date(), ""));
        System.out.println("getLastSundayBeforeToday===>" + getLastSundayBeforeToday(new Date(), "yyyy-MM-dd"));
        System.out.println("getLastSundayBeforeToday===>" + getLastSundayBeforeToday(new Date(), "yyyy/MM/dd HH:mm:ss.SSS"));
        System.out.println("getLastMondayBeforeSunday===>" + getLastMondayBeforeSunday((Date)null, (String)null));
        System.out.println("getLastMondayBeforeSunday===>" + getLastMondayBeforeSunday(new Date(), ""));
        System.out.println("getLastMondayBeforeSunday===>" + getLastMondayBeforeSunday(new Date(), "yyyy-MM-dd"));
        System.out.println("getLastMondayBeforeSunday===>" + getLastMondayBeforeSunday(new Date(), "yyyy/MM/dd HH:mm:ss.SSS"));
        System.out.println(getTimeBeginOfTodayStr(new Date()));
        System.out.println(getTimeEndOfDayStr(new Date()));
        System.out.println(getTime("1:50:49", "HH:mm:ss"));
        System.out.println(getTimeStr(getTime("1:50:49", "HH:mm:ss"), "HH:mm:ss"));
    }
}
