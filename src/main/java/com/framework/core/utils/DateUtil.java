package com.framework.core.utils;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * DateUtils class
 * 
 * @author matrix
 *
 */
public class DateUtil {

    public static Calendar cl = Calendar.getInstance();

    /** 日期格式yyyy-MM字符串常量 */
    private static final String MONTH_FORMAT = "yyyy-MM";
    /** 日期格式yyyy-MM-dd字符串常量 */
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    /** 日期格式HH:mm:ss字符串常量 */
    private static final String HOUR_FORMAT = "HH:mm:ss";
    /** 日期格式yyyy-MM-dd HH:mm:ss字符串常量 */
    private static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    /** 某天结束时分秒字符串常量 23:59:59 */
    public static final String DAY_END_STRING_HHMMSS = " 23:59:59";

    public static SimpleDateFormat sdf_month_format = new SimpleDateFormat(MONTH_FORMAT);
    public static SimpleDateFormat sdf_date_format = new SimpleDateFormat(DATE_FORMAT);
    public static SimpleDateFormat sdf_hour_format = new SimpleDateFormat(HOUR_FORMAT);
    public static SimpleDateFormat sdf_datetime_format = new SimpleDateFormat(DATETIME_FORMAT);

    public DateUtil() {

    }

    /**
     * Get current date time, the format is "yyyy-MM-dd HH:mm:ss" <br>
     * Example: 2016-08-11 15:18:10
     * 
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String getNowDateTime() {
        return sdf_datetime_format.format(Calendar.getInstance().getTime());
    }

    /**
     * Get current year, the format is such as 2016
     * 
     * @return
     */
    public static String getNowYear() {
        return String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
    }

    /**
     * Get current year-month, the format is "yyyy-MM"<br>
     * Example: 2016-08
     * 
     * @return yyyy-MM
     */
    public static String getNowYearMonth() {
        return sdf_month_format.format(Calendar.getInstance().getTime());
    }

    /**
     * Get current year-month-date, the format is "yyyy-MM-dd"<br>
     * Example: 2016-08-11
     * 
     * @return yyyy-MM-dd
     */
    public static String getNowYearMonthDate() {
        return sdf_date_format.format(Calendar.getInstance().getTime());
    }

    /**
     * Get current time, the format is "HH:mm:ss"<br>
     * Example: 15:56:50
     * 
     * @return HH:mm:ss
     */
    public static String getNowTime() {
        return sdf_hour_format.format(Calendar.getInstance().getTime());
    }

    /**
     * Get the day number within the current year.
     * 
     * @return
     */
    public static Integer getDayInYear() {
        return Calendar.getInstance().get(Calendar.DAY_OF_YEAR);
    }

    /**
     * Get the day number within the current month.
     * 
     * @return
     */
    public static Integer getDayInMonth() {
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }

    /**
     * Get the day number within the current week. <br>
     * Example:Sunday-Saturday(0-7)
     * 
     * @return
     */
    public static Integer getDayInWeek() {
        return Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
    }

    /**
     * Add special number days to the special date.
     * 
     * @param date special date
     * @param num days
     * @return the date after addDay
     */
    public static Date addDay(Date date, int num) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, num);
        return cal.getTime();
    }

    // TODO update the method follow.

    public static Date getMouthDate(int nextMouths, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.DAY_OF_MONTH, days);
        calendar.add(Calendar.MONTH, nextMouths);

        return calendar.getTime();
    }

    public static String nowString() {
        return getDateString(new Date(), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 指定时间格式转字符串
     * 
     * @param date
     * @param format
     * @return
     */
    public static String getDateString(Date date, String format) {
        Date tmpDate = null == date ? new Date() : date;

        String tmpFormat = "yyyy-MM-dd HH:mm:ss";
        if (null != format || "".equals(format)) {
            tmpFormat = format;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(tmpFormat);
        return sdf.format(tmpDate);
    }

    /**
     * 
     * @param text
     * @param format
     * @return
     */
    public static Date parsedate(String text, String format) {
        try {
            return new SimpleDateFormat(format).parse(text);
        } catch (ParseException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static DatePeriods getYestodayPeriods() {
        Calendar calendar = Calendar.getInstance();

        /*** 定制每日00:00时间 ***/

        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        Date date = calendar.getTime();

        // 此时要在 第一次执行定时任务的时间 加一天，以便此任务在下个时间点执行。如果不加一天，任务会立即执行。
        // if (date.before(new Date())) {
        // date = addDay(date, -1);
        // }

        Date yestoday = addDay(date, -1);

        long begin = yestoday.getTime();
        long end = yestoday.getTime() + 60 * 60 * 24 * 1000;

        return new DatePeriods(begin, end);
    }

    /*
     * @Test public void testDatePeriods(){ DatePeriods y1 = getYestodayPeriods(); DatePeriods y2 =
     * getYestodayPeriods(); TestCase.assertEquals(y1, y2); System.out.println(y1);
     * System.out.println(y2); System.out.println(System.currentTimeMillis()); }
     * 
     * @Test public void testNewDate(){ DatePeriods y1 = getDayPeriods(-1); DatePeriods y2 =
     * getYestodayPeriods(); TestCase.assertEquals(y1, y2); }
     */

    public static class DatePeriods implements Serializable {
        /**
         * 
         */
        private static final long serialVersionUID = 1L;
        private long beginTime;
        private long endTime;

        public DatePeriods(long beginTime, long endTime) {
            super();
            this.beginTime = beginTime;
            this.endTime = endTime;
        }

        public long getBeginTime() {
            return beginTime;
        }

        public void setBeginTime(long beginTime) {
            this.beginTime = beginTime;
        }

        public long getEndTime() {
            return endTime;
        }

        public void setEndTime(long endTime) {
            this.endTime = endTime;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + (int) (beginTime ^ (beginTime >>> 32));
            result = prime * result + (int) (endTime ^ (endTime >>> 32));
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            DatePeriods other = (DatePeriods) obj;
            if (beginTime != other.beginTime) return false;
            if (endTime != other.endTime) return false;
            return true;
        }

        @Override
        public String toString() {
            return "DatePeriods:yestoday [beginTime=" + beginTime + ", endTime=" + endTime + "]";
        }

    }

    public static int getDaysBetweenDates(Date d1, Date d2) {
        long t1 = d1.getTime();
        long t2 = d2.getTime();
        long between_days = Math.abs((t1 - t2) / (1000 * 3600 * 24));

        return Integer.parseInt(String.valueOf(between_days));
    }

    public static DatePeriods getDayPeriods(int day) {
        Calendar calendar = Calendar.getInstance();

        /*** 定制每日00:00时间 ***/

        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        Date date = calendar.getTime();

        // 此时要在 第一次执行定时任务的时间 加一天，以便此任务在下个时间点执行。如果不加一天，任务会立即执行。
        // if (date.before(new Date())) {
        // date = addDay(date, -1);
        // }

        Date newDay = addDay(date, day);

        long begin = newDay.getTime();
        long end = newDay.getTime() + 60 * 60 * 24 * 1000;

        return new DatePeriods(begin, end);
    }



    /**
     * 获得到当天晚上12所剩余的毫秒数
     * 
     * @return 时间的毫秒值
     */
    public static long getRemainTime() {
        Calendar calendar = Calendar.getInstance();

        /*** 定制每日00:00时间 ***/

        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        Date date = calendar.getTime(); // 第一次执行定时任务的时间

        // 如果第一次执行定时任务的时间 小于 当前的时间
        // 此时要在 第一次执行定时任务的时间 加一天，以便此任务在下个时间点执行。如果不加一天，任务会立即执行。
        if (date.before(new Date())) {
            date = addDay(date, 1);
        }

        // SimpleDateFormat sdf = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
        // System.out.println("12 times:"+sdf.format(date));
        // System.out.println("now times:"+sdf.format(new Date()));

        return date.getTime() - new Date().getTime();
    }
}
