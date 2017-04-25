package cn.com.git.udmp.utils.lang;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import cn.com.git.udmp.component.model.CharConst;
import cn.com.git.udmp.component.model.Cst;
import cn.com.git.udmp.component.model.PubConstants;
import cn.com.git.udmp.core.logging.ILog;

/**
 * 
 * @description 提供对日期进行操作方法
 * @author Spring Cao
 * @date 2016年8月30日 下午1:38:49
 */
public class DateUtilsEx implements ILog{
    private static String[] optionDateFormats = new String[] { "yyyy-MM-dd HH:mm:ss.S a", "yyyy-MM-dd HH:mm:ssz",
        "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm:ssa" }; // backwards

    private static final String DATE = new String(CharConst.FORMAT_STR_DATE);
    private static final String LONGDATE = new String(CharConst.FORMAT_STR_DATE_NO_LINE);
    private static final String TIME = new String(CharConst.FORMAT_STR_TIME);
    private static final String LONGTIME = new String(CharConst.FORMAT_STR_LONGTIME);
    private static final String BIZTIME = new String("HH:mm:ss.SSS ");

    // compatability

    /**
     * <p>
     * Title:
     * </p>
     * <p>
     * Description: 构造方法
     * </p>
     */
    private DateUtilsEx() {
    }

    /**
     * @description 获得输入日期中的年份
     * @version
     * @title
     * @param date date类型为Date，输入日期
     * @return 输入日期中的年份，类型为int
     */
    public static int getYear(java.util.Date date) {
        Calendar calendar = createCalendar();
        setCalTime(date, calendar);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * @description 得到年份的最后两位
     * @version
     * @title
     * @param date date类型为Date，输入日期
     * @return 输入日期中的年份的后两位，类型为String
     */
    public static String getShortYear(java.util.Date date) {
        String year = getYear(date) + "";
        int length = year.length();
        return year.substring(length - 2, length);
    }

    /**
     * @description 获得日期中的月份
     * @version
     * @title
     * @author caosb_wb caosb_wb@newchina.live
     * @param date 类型为Date，输入日期
     * @return 输入日期中的月份，类型为int
     */
    public static int getMonth(java.util.Date date) {
        Calendar calendar = createCalendar();
        setCalTime(date, calendar);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * @description 获得年月日中的具体的某一天
     * @version
     * @title
     * @author caosb_wb caosb_wb@newchina.live
     * @param date 类型为Date，输入日期
     * @return 输入日期的具体天数，类型为int
     */
    public static int getDay(java.util.Date date) {
        Calendar calendar = createCalendar();
        setCalTime(date, calendar);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * @description 获得输入时间中的小时
     * @version
     * @title
     * @author caosb_wb caosb_wb@newchina.live
     * @param date 类型为Date，输入日期
     * @return 输入时间的小时，类型为int
     */
    public static int getHour(java.util.Date date) {
        Calendar calendar = createCalendar();
        setCalTime(date, calendar);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * @description 获得输入时间中的分钟
     * @version
     * @title
     * @author caosb_wb caosb_wb@newchina.live
     * @param date 类型为Date，输入日期
     * @return 输入时间的分钟，类型为int
     */
    public static int getMinute(java.util.Date date) {
        Calendar calendar = createCalendar();
        setCalTime(date, calendar);
        return calendar.get(Calendar.MINUTE);
    }

    /**
     * @description 获得输入时间中的秒数
     * @version
     * @title
     * @author caosb_wb caosb_wb@newchina.live
     * @param date 类型为Date，输入日期
     * @return 输入时间的秒数，类型为int
     */
    public static int getSecond(java.util.Date date) {
        Calendar calendar = createCalendar();
        setCalTime(date, calendar);
        return calendar.get(Calendar.SECOND);
    }

    /**
     * @description 获得在源日期的基础上增加指定的天数的日期
     * @version
     * @title
     * @param oldDate oldDate类型为Date，输入日期
     * @param addDays addDays类型为int,增加日期的天数
     * @return 增加后的日期，类型为Date
     */
    public static Date addDay(java.util.Date oldDate, int addDays) {
        Calendar calendar = createCalendar();
        setCalTime(oldDate, calendar);
        calendar.add(Calendar.DATE, addDays);
        return calendar.getTime();
    }

    /**
     * @description 获得在源日期的基础上增加指定的小时数的日期
     * @version
     * @title
     * @param oldDate oldDate类型为Date，输入日期
     * @param addHours addHours类型为int,增加日期的小时数
     * @return 增加后的日期，类型为Date
     */
    public static Date addHour(java.util.Date oldDate, int addHours) {
        Calendar calendar = createCalendar();
        setCalTime(oldDate, calendar);

        calendar.add(Calendar.HOUR, addHours);
        return calendar.getTime();
    }

    /**
     * @description 根据给定日期Sets this Calendar's time
     * @version
     * @title
     * @param oldDate 源日期
     * @param cal 目标日历
     */
    private static void setCalTime(java.util.Date oldDate, Calendar cal) {
        cal.setTime(oldDate);
    }

    /**
     * @description 获得在源日期的基础上增加指定的月数的日期
     * @version
     * @title
     * @author caosb_wb caosb_wb@newchina.live
     * @param oldDate oldDate类型为Date，输入日期
     * @param addMonths addMonths类型为int,增加日期的月数
     * @return 增加后的日期，类型为Date
     */
    public static Date addMonth(java.util.Date oldDate, int addMonths) {
        Calendar calendar = createCalendar();
        calendar.setTime(oldDate);
        calendar.add(Calendar.MONTH, addMonths);
        return calendar.getTime();
    }

    /**
     * @description 获得在源日期的基础上增加指定的年数的日期
     * @version
     * @title
     * @author caosb_wb caosb_wb@newchina.live
     * @param oldDate oldDate类型为Date，输入日期
     * @param addYears 类型为int,增加日期的年数
     * @return 增加后的日期，类型为Date
     */
    public static Date addYear(java.util.Date oldDate, int addYears) {
        Calendar calendar = createCalendar();
        calendar.setTime(oldDate);
        calendar.add(Calendar.YEAR, addYears);
        return calendar.getTime();
    }

    /**
     * @description 获得对输入日期的年份四舍五入之后的日期
     * @version
     * @title
     * @param date date类型为Date，输入日期
     * @return 四舍五入之后的日期，类型为Date
     */
    public static Date roundYear(Date date) {
        return org.apache.commons.lang3.time.DateUtils.round(date, Calendar.YEAR);
    }

    /**
     * @description 获得对输入日期的月份四舍五入之后的日期
     * @version
     * @title
     * @author caosb_wb caosb_wb@newchina.live
     * @param date date类型为Date，输入日期
     * @return 四舍五入之后的日期，类型为Date
     */
    public static Date roundMonth(Date date) {
        return org.apache.commons.lang3.time.DateUtils.round(date, Calendar.MONTH);
    }

    /**
     * @description 获得对输入日期的天数四舍五入之后的日期
     * @version
     * @title
     * @param date date类型为Date，输入日期
     * @return 四舍五入之后的日期，类型为Date
     */
    public static Date roundDay(Date date) {
        return org.apache.commons.lang3.time.DateUtils.round(date, Calendar.DATE);
    }

    /**
     * @description 获得对输入日期的小时数四舍五入之后的日期
     * @version
     * @title
     * @param date date类型为Date，输入日期
     * @return 四舍五入之后的日期，类型为Date
     */
    public static Date roundHour(Date date) {
        return org.apache.commons.lang3.time.DateUtils.round(date, Calendar.HOUR);
    }

    /**
     * @description 获得对输入日期的分钟四舍五入之后的日期
     * @version
     * @title
     * @param date date类型为Date，输入日期
     * @return 四舍五入之后的日期，类型为Date
     */
    public static Date roundMinute(Date date) {
        return org.apache.commons.lang3.time.DateUtils.round(date, Calendar.MINUTE);
    }

    /**
     * @description 获得对输入日期的秒数四舍五入之后的日期
     * @version
     * @title
     * @param date date类型为Date，输入日期
     * @return 四舍五入之后的日期，类型为Date
     */
    public static Date roundSecond(Date date) {
        return org.apache.commons.lang3.time.DateUtils.round(date, Calendar.SECOND);
    }

    /**
     * @description 获得对输入日期截取年份之后的日期
     * @version
     * @title
     * @param date date类型为Date，输入日期
     * @return 截取年份之后的日期，类型为Date
     */
    public static Date truncateYear(Date date) {
        return org.apache.commons.lang3.time.DateUtils.truncate(date, Calendar.YEAR);
    }

    /**
     * @description 获得对输入日期截取月份之后的日期
     * @version
     * @title
     * @param date date类型为Date，输入日期
     * @return 截取月份之后的日期，类型为Date
     */
    public static Date truncateMonth(Date date) {
        return org.apache.commons.lang3.time.DateUtils.truncate(date, Calendar.MONTH);
    }

    /**
     * @description 获得对输入日期截取天数之后的日期
     * @version
     * @title
     * @param date date类型为Date，输入日期
     * @return 截取天数之后的日期，类型为Date
     */
    public static Date truncateDay(Date date) {
        return org.apache.commons.lang3.time.DateUtils.truncate(date, Calendar.DATE);
    }

    /**
     * @description 获得对输入日期截取小时之后的日期
     * @version
     * @title
     * @param date date类型为Date，输入日期
     * @return 截取小时之后的日期，类型为Date
     */
    public static Date truncateHour(Date date) {
        return org.apache.commons.lang3.time.DateUtils.truncate(date, Calendar.HOUR);
    }

    /**
     * @description 获得对输入日期截取分钟之后的日期
     * @version
     * @title
     * @param date date类型为Date，输入日期
     * @return 截取分钟之后的日期，类型为Date
     */
    public static Date truncateMinute(Date date) {
        return org.apache.commons.lang3.time.DateUtils.truncate(date, Calendar.MINUTE);
    }

    /**
     * @description 获得对输入日期截取秒数之后的日期
     * @version
     * @title
     * @param date date类型为Date，输入日期
     * @return 截取秒数之后的日期，类型为Date
     */
    public static Date truncateSecond(Date date) {
        return org.apache.commons.lang3.time.DateUtils.truncate(date, Calendar.SECOND);
    }

    /**
     * @description 按照format格式将date转换为字符串
     * @version
     * @title
     * @param date date类型为Date，输入日期
     * @param format format类型为String,日期转化格式
     * @return 转化之后的日期，类型为String
     */
    public static String date2String(java.util.Date date, String format) {
        if (date == null) {
            return null;
        } else {
            if (format == null) {
                format = "dd/MM/yyyy";
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            return simpleDateFormat.format(date);
        }
    }

    /**
     * @description 按照默认的格式将字符串转化为date
     * @version
     * @title
     * @param sDate sdate类型为Date，输入日期
     * @param format format类型为String,日期转化格式
     * @return 转化之后的日期，类型为Date
     * @throws Exception 日期解析异常
     */
    public static java.util.Date toDate(String sDate, String format) throws Exception {
        if (sDate == null || sDate.equals("")) {
            return null;
        }
        // SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return parse(sDate, format);
    }

    /**
     * 
     * @title
     * @description 将字符串按照默认的格式转换为日期类型
     * @param sDate
     * @return
     */
    public static java.util.Date toDate(String sDate) {
        try {
            return toDate(sDate);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @description 按照输入的年月日时分秒获得日期
     * @version
     * @title
     * @param year 输入年份
     * @param month 输入月份
     * @param date 输入天数
     * @param hrs 输入小时数
     * @param min 输入分钟数
     * @param sec 输入秒数
     * @return 根据输入的参数，返回相应的日期，类型为Date
     */
    public static java.util.Date toDate(int year, int month, int date, int hrs, int min, int sec) {
        Calendar calendar = createCalendar();
        calendar.set(year, month - 1, date, hrs, min, sec);
        return calendar.getTime();
    }

    /**
     * @description 将日期按照默认的格式进行转换
     * 
     * @param date String 日期
     * @param defaultFormat String 默认格式
     * @return Date 默认格式的日期
     * @throws Exception 格式转化异常
     */
    private static Date parse(String date, String defaultFormat) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(defaultFormat);

        try {
            return simpleDateFormat.parse(date);
        } catch (ParseException e) {
            for (int i = 0; i < optionDateFormats.length; i++) {
                try {
                    SimpleDateFormat format = new SimpleDateFormat(optionDateFormats[i]);
                    return format.parse(date);
                } catch (ParseException e2) {
                    logger.debug("请尝试其他转换格式");
                    // no worries, let's try the next format.
                }
            }
            // no dateFormats left to try
            if (null == date) {
                throw new Exception("Cannot parse date <null>");
            } else {
                throw new Exception("Cannot parse date " + date);
            }

        }

    }

    /**
     * @description 据生日和当前时间计算年龄
     * @version
     * @title
     * @param birthday birthday类型为Date,输入生日
     * @param endDate endDate类型为Date，表示当前时间
     * @return 年龄，类型为int
     */
    public static int getAge(Date birthday, Date endDate) {
        return (int) getYearAmount(birthday, endDate);
    }

    /**
     * @description 根据输入日期计算年份之差
     * @version
     * @title
     * @param startDate startDate类型为Date,输入开始时间
     * @param endDate endDate类型为Date，表示终止时间
     * @return 年龄，类型为int
     */
    public static double getYearAmount(Date startDate, Date endDate) {
        return getMonthAmount(startDate, endDate) / 12.0;
    }

    /**
     * @description 计算两个日期之间的天数
     * @version
     * @title
     * @param startDate startDate类型为Date,输入开始时间
     * @param endDate endDate类型为Date，表示终止时间
     * @return 日期之间的天数，类型为int
     */
    public static double getDayAmount(Date startDate, Date endDate) {
        return (endDate.getTime() - startDate.getTime()) / (double) (1000 * 60 * 60 * 24);
    }

    /**
     * @description 计算两个日期之间的月数
     * @version
     * @title
     * @param startDate startDate类型为Date,输入开始时间
     * @param endDate endDate类型为Date，表示终止时间
     * @return 日期之间的月数，类型为int
     */
    public static double getMonthAmount(Date startDate, Date endDate) {
        int years = 0;
        int nonths = 0;
        double days = 0;
        double monthAmount = 0;
        years = getYear(endDate) - getYear(startDate);
        nonths = getMonth(endDate) - getMonth(startDate);
        if ((getDay(endDate) == getDay(startDate)) || (isMaxDayOfMonth(startDate) && isMaxDayOfMonth(endDate))) {
            days = 0;
        } else {
            days = getDay(endDate) - getDay(startDate);
        }
        monthAmount = years * 12 + nonths + days / Cst.DAYS_OF_MONTH;
        return monthAmount;
    }

    /**
     * @description 判断输入日期是否为这个月中的最大一天
     * @version
     * @title
     * @param date date类型为Date,输入日期
     * @return 如果是该月的最大月，返回true，否则，返回false
     */
    public static boolean isMaxDayOfMonth(Date date) {
        return getDay(date) == getMaxDayOfMonth(date);
    }

    /**
     * @description 获得指定月份的最大日期
     * @version
     * @title
     * @param date date类型为Date,输入日期
     * @return 输入月份中的最大日期
     */
    public static int getMaxDayOfMonth(Date date) {
        Calendar c = createCalendar();
        c.setTime(date);
        return c.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * @description 判断输入日期是否为周末
     * @version
     * @title
     * @param date date类型为Date,输入日期
     * @return 若输入日期是周末则返回true，否则，返回false
     */
    public static boolean isWeekend(java.util.Date date) {
        Calendar cal = createCalendar();
        cal.setTime(date);
        int i = cal.get(Calendar.DAY_OF_WEEK);
        return isWeekend(i);

    }

    /**
     * @description 判断输入日期是否为周六或者周日
     * @version
     * @title
     * @param currDayOfWeek date类型为Date,输入日期
     * @return 若输入日期是周六或者周日则返回true，否则，返回false
     */
    public static boolean isWeekend(int currDayOfWeek) {
        return currDayOfWeek == Calendar.SATURDAY || currDayOfWeek == Calendar.SUNDAY;

    }

    /**
     * @description 判断输入日期字符串是否为周末
     * @version
     * @title
     * @param strDate 类型为String,日期字符串
     * @return 若输入日期是周末则返回true，否则，返回false
     * @throws Exception 转化异常
     */
    public static boolean isWeekend(String strDate) throws Exception {
        Date date = parse(strDate, "yyyyMMdd");
        return isWeekend(date);

    }

    /**
     * @description 将输入的util的Date类型转化为sql的Date类型
     * @version
     * @title
     * @param utilDate 类型为Date,输入日期数据
     * @return sql的Date类型数据，类型为sql的Date
     */
    public static java.sql.Date toSqlDate(Date utilDate) {
        if (utilDate == null) {
            return null;
        }
        return new java.sql.Date(utilDate.getTime());
    }

    /**
     * @description 计算最接近的年龄
     * 
     * @param birthday Date
     * @param endDate Date
     * @return int
     */
    public static int getAgeNearest(Date birthday, Date endDate) {
        return (int) Math.round(getYearAmount(birthday, endDate));

    }

    /**
     * @description 获得日历实例
     * @version
     * @title
     * @return 日历实例，类型为Calendar
     */
    public static Calendar createCalendar() {
        Calendar result = null;

        result = Calendar.getInstance();
        return result;
    }

    /**
     * get amount of workdays between startDate and endDate
     * 
     * @description 获得开始日期和结束日期之间的所有工作日的天数
     * 
     * @param startDate Date
     * @param endDate Date
     * @return int
     * @throws RuntimeException
     */
    // public static int getWorkdayAmount(Date startDate, Date endDate) {
    // try {
    // return WorkingCalendarUtils.getWorkdayAmount(startDate, endDate);
    // } catch (Exception e) {
    // throw new RuntimeException(e);
    // }
    // }

    /**
     * get amount of workdays between startDate and endDate
     * 
     * @description 获得开始日期和结束日期之间的所有工作日的天数
     * 
     * @param startDate Date
     * @param endDate Date
     * @param orgId String
     * @return int
     * @throws RuntimeException
     */
    // public static int getWorkdayAmount(Date startDate, Date endDate, String
    // orgId) {
    // try {
    // return WorkingCalendarUtils.getWorkdayAmount(startDate, endDate, orgId);
    // } catch (Exception e) {
    // throw new RuntimeException(e);
    // }
    // }

    /**
     * get workday amount
     * 
     * @description 计算某年某月的工作日的天数
     * 
     * @param year int
     * @param month int
     * @throws RuntimeException
     * @return int
     */
    // public static int getWorkdayAmount(int year, int month) {
    // try {
    // return WorkingCalendarUtils.getWorkdayAmount(year, month);
    // } catch (Exception e) {
    // throw new RuntimeException(e);
    // }
    // }

    /**
     * get workday amount
     * 
     * @description 计算某年某月的工作日的天数
     * 
     * @param year int
     * @param month int
     * @param orgId String
     * @throws RuntimeException
     * @return int
     */
    // public static int getWorkdayAmount(int year, int month, String orgId) {
    // try {
    // return WorkingCalendarUtils.getWorkdayAmount(year, month, orgId);
    // } catch (Exception e) {
    // throw new RuntimeException(e);
    // }
    // }

    /**
     * judge whether the date is workday
     * 
     * @description 判断是否为工作日
     * 
     * @param date Date
     * @return boolean
     */
    // public static boolean isWorkday(Date date) {
    // try {
    // return WorkingCalendarUtils.isWorkday(date);
    // } catch (Exception e) {
    // throw new RuntimeException(e);
    // }
    //
    // }

    /**
     * judge whether the date is workday
     * 
     * @description 判断是否为工作日
     * 
     * @param date Date
     * @param orgId String
     * @return boolean
     */
    // public static boolean isWorkday(Date date, String orgId) {
    // try {
    // return WorkingCalendarUtils.isWorkday(date, orgId);
    // } catch (Exception e) {
    // throw new RuntimeException(e);
    // }
    //
    // }

    /**
     * @description 获得下一个工作日
     * 
     * @param startDate Date
     * @param spaceDays int
     * @param countStartDate boolean if count start date
     * @return Date
     */
    // public static Date getNextWorkday(Date startDate, int spaceDays, boolean
    // countStartDate) {
    // try {
    // return WorkingCalendarUtils.getNextWorkday(startDate, spaceDays,
    // countStartDate);
    // } catch (Exception e) {
    // throw new RuntimeException(e);
    // }
    // }

    /**
     * @description 获得下一个工作日
     * 
     * @param startDate Date
     * @param spaceDays int
     * @param countStartDate boolean if count start date
     * @param orgId String
     * @return Date
     */
    // public static Date getNextWorkday(Date startDate, int spaceDays, boolean
    // countStartDate, String orgId) {
    // try {
    // return WorkingCalendarUtils.getNextWorkday(startDate, spaceDays,
    // countStartDate, orgId);
    // } catch (Exception e) {
    // throw new RuntimeException(e);
    // }
    // }

    /**
     * get the next workday after spaceDays day from startDate.
     * 
     * @description 根据条件计算下一个工作日
     * 
     * @param startDate Date
     * @param spaceDays int
     * @return Date the next workday
     */
    // public static Date getNextWorkday(Date startDate, int spaceDays) {
    // try {
    // return WorkingCalendarUtils.getNextWorkday(startDate, spaceDays);
    // } catch (Exception e) {
    // throw new RuntimeException(e);
    // }
    // }

    /**
     * get the next workday after spaceDays day from startDate.
     * 
     * @description 获得下一个工作日
     * 
     * @param startDate Date
     * @param spaceDays int
     * @param orgId String
     * @return Date the next workdayWorkingCalendarUtils.java
     */
    // public static Date getNextWorkday(Date startDate, int spaceDays, String
    // orgId) {
    // try {
    // return WorkingCalendarUtils.getNextWorkday(startDate, spaceDays, orgId);
    // } catch (Exception e) {
    // throw new RuntimeException(e);
    // }
    // }

    /**
     * @description 由给定年月日字符串获取格式日期(年/月/日)
     * @version
     * @title
     * @param strYear strYear类型为String,年份字符串；
     * @param strMonth strMonth类型为String，月份字符串；
     * @param strDay strDay类型为String，天数字符串
     * @return 格式为“年/月/日”的日期字符串，类型为String
     */
    public static String getDate(String strYear, String strMonth, String strDay) {
        String strReturn = CharConst.EMPTY_STR;
        StringBuffer tSBql = new StringBuffer();
        int intYear = 0;
        int intMonth = 0;
        int intDay = 0;
        if ((strYear != null) && (strMonth != null) && (strDay != null) && (strYear.trim().length() > 0)
                && (strMonth.trim().length() > 0) && (strDay.trim().length() > 0)) {
            try {
                intYear = new Integer(strYear).intValue();
                intMonth = new Integer(strMonth).intValue();
                intDay = new Integer(strDay).intValue();
            } catch (Exception exception) {
                return strReturn;
            }

            if ((intYear <= 0) || (intMonth <= 0) || (intMonth > 12) || (intDay <= 0) || (intDay > 31)) {
                strReturn = CharConst.EMPTY_STR;
            } else {
                tSBql.append(intYear);
                if (intMonth < 10) {
                    tSBql.append(CharConst.DATEDELIMITER);
                    tSBql.append(CharConst.ZERO_STR);
                    tSBql.append(intMonth);
                } else {
                    tSBql.append(CharConst.DATEDELIMITER);
                    tSBql.append(intMonth);
                }

                if (intDay < 10) {
                    tSBql.append(CharConst.DATEDELIMITER);
                    tSBql.append(CharConst.ZERO_STR);
                    tSBql.append(intDay);
                } else {
                    tSBql.append(CharConst.DATEDELIMITER);
                    tSBql.append(intDay);
                }
                strReturn = tSBql.toString();
            }
        }
        return strReturn;
    }

    /**
     * @description 获得当前时间字符串（不加任何分割符：如2014060915014651）
     * @version
     * @title
     * @return 类型为String
     */
    public static String longDateTime() {
        String st1 = new SimpleDateFormat(LONGDATE).format(new Date());
        String st2 = new SimpleDateFormat(LONGTIME).format(new Date());
        return st1 + st2;

    }

    /**
     * @description 获取当前时间
     * @version
     * @title
     * @return 当前时间字符串，类型为String(hh:mm:ss)
     */
    public static String getTime() {
        return new SimpleDateFormat(TIME).format(new Date());
    }

    /**
     * @description 获取当前时间,long类型
     * @version
     * @title
     * @author caosb_wb caosb_wb@newchinalife.com
     * @return 当前时间字符串，类型为String(如’084341499’)
     */
    public static String getlongTime() {
        return new SimpleDateFormat(LONGTIME).format(new Date());
    }
    /**
     * @description 获取当前时间
     * @version
     * @title
     * @return 返回时间字符串  HH:mm:ss.S
    */
    public static String getBizTime() {
        return new SimpleDateFormat(BIZTIME).format(new Date());
    }

    /**
     * @description 获取当前日期
     * @version
     * @title
     * @return 当前日期字符串，类型为String(如’20144809’)
     */
    public static String getlongDATE() {
        return new SimpleDateFormat(LONGDATE).format(new Date());
    }

    /**
     * @description 获取当前日期
     * @version
     * @title
     * @return 当前日期字符串，类型为String（’YYYY-MM-dd’）
     */
    public static String getDate() {
        return new SimpleDateFormat(DATE).format(new Date());
    }

    /**
     * @description 通过输入年份和月份字符串，返回格式为“年/月”的日期
     * @version
     * @title
     * @param strYear 类型为String,输入年份
     * @param strMonth 类型为String，输入月份
     * @return 当前日期字符串，类型为String（’YYYY-MM-dd’）
     */
    public static String getDate(String strYear, String strMonth) {
        String strReturn = CharConst.EMPTY_STR;
        StringBuffer tSBql = new StringBuffer();
        int intYear = 0;
        int intMonth = 0;
        if ((strYear != null) && (strMonth != null) && (strYear.trim().length() > 0) && (strMonth.trim().length() > 0)) {
            intYear = new Integer(strYear).intValue();
            intMonth = new Integer(strMonth).intValue();
            if ((intYear <= 0) || (intMonth <= 0) || (intMonth > 12)) {
                strReturn = CharConst.EMPTY_STR;
            } else {
                tSBql.append(intYear);
                tSBql.append(CharConst.DATEDELIMITER);
                tSBql.append(intMonth);
                strReturn = tSBql.toString();
            }
        }
        return strReturn;
    }

    /**
     * @description 通过日期字符串，返回该日期的年份
     * @version
     * @title
     * @param strDate strDate类型为String,日期字符串
     * @return 输入日期的年份，类型为String
     */
    public static String getYear(String strDate) {
        int intPosition = 0;
        String strReturn = CharConst.EMPTY_STR;
        int intYear = 0;

        if ((strDate != null) && (strDate.trim().length() > 0)) {
            intPosition = StringUtilsEx.getPos(strDate, CharConst.DATEDELIMITER, 1);
            if (intPosition > 0) {
                strReturn = strDate.substring(0, intPosition);
                intYear = new Integer(strReturn).intValue();
                if (intYear <= 0) {
                    strReturn = CharConst.EMPTY_STR;
                } else {
                    strReturn = CharConst.EMPTY_STR + intYear;
                }

                if ((intYear < 10) && (!strReturn.equals(CharConst.EMPTY_STR))) {
                    strReturn = CharConst.ZERO_STR + strReturn;
                }
            }
        }
        return strReturn;
    }

    /**
     * @description 获取系统日期中的年份
     * @version
     * @title
     * @return 系统日期的年份，类型为String
     */
    public static String getYear() {
        StringBuffer tSBql = new StringBuffer();
        int intYear = Calendar.getInstance().get(Calendar.YEAR);
        tSBql.append(intYear);
        return tSBql.toString();
    }

    /**
     * @description 通过日期字符串，返回该日期的月份
     * @version
     * @title
     * @param strDate strDate类型为String,日期字符串
     * @return 输入日期的月份，类型为String
     */
    public static String getMonth(String strDate) {
        int intPosition1 = 0;
        int intPosition2 = 0;
        String strReturn = CharConst.EMPTY_STR;
        int intMonth = 0;
        if ((strDate != null) && (strDate.trim().length() > 0)) {
            intPosition1 = StringUtilsEx.getPos(strDate, CharConst.DATEDELIMITER, 1);
            intPosition2 = StringUtilsEx.getPos(strDate, CharConst.DATEDELIMITER, 2);
            if ((intPosition1 > 0) && intPosition2 > intPosition1) {

                strReturn = strDate.substring(intPosition1 + 1, intPosition2);

                intMonth = new Integer(strReturn).intValue();
                if ((intMonth <= 0) || (intMonth > 12)) {
                    strReturn = CharConst.EMPTY_STR;
                } else {
                    strReturn = CharConst.EMPTY_STR + intMonth;
                }

                if ((intMonth < 10) && (!strReturn.equals(CharConst.EMPTY_STR))) {
                    strReturn = CharConst.ZERO_STR + strReturn;
                }
            }
        }
        return strReturn;
    }

    /**
     * @description 获取系统日期中的月份
     * @version
     * @title
     * @return 系统日期的月份，类型为String
     */
    public static String getMonth() {
        StringBuffer tSBql = new StringBuffer();
        int intMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
        if (intMonth < 10) {
            tSBql.append(CharConst.ZERO_STR);
            tSBql.append(intMonth);
        } else {
            tSBql.append(intMonth);
        }
        return tSBql.toString();
    }

    /**
     * @description 通过日期字符串，返回该日期的天数
     * @version
     * @title
     * @param strDate strDate类型为String,日期字符串
     * @return 输入日期的天数，类型为String
     */
    public static String getDay(String strDate) {
        int intPosition = 0;
        String strReturn = CharConst.EMPTY_STR;
        int intDay = 0;
        if ((strDate != null) && (strDate.trim().length() > 0)) {
            intPosition = StringUtilsEx.getPos(strDate, CharConst.DATEDELIMITER, 2);
            if (intPosition > 0) {

                strReturn = strDate.substring(intPosition + 1);

                intDay = new Integer(strReturn).intValue();

                if ((intDay <= 0) || (intDay > 31)) {
                    strReturn = CharConst.EMPTY_STR;
                } else {
                    strReturn = CharConst.EMPTY_STR + intDay;
                }

                if ((intDay < 10) && (!strReturn.equals(CharConst.EMPTY_STR))) {
                    strReturn = CharConst.ZERO_STR + strReturn;
                }
            }
        }
        return strReturn;
    }

    /**
     * @description 获取系统日期中的天数
     * @version
     * @title
     * @return 输入日期的天数，类型为String
     */
    public static String getDay() {
        StringBuffer tSBql = new StringBuffer();
        int intDate = Calendar.getInstance().get(Calendar.DATE);
        if (intDate < 10) {
            tSBql.append(CharConst.ZERO_STR);
            tSBql.append(intDate);
        } else {
            tSBql.append(intDate);
        }
        return tSBql.toString();
    }

    /**
     * @description 获取系统日期中的小时
     * @version
     * @title
     * @return 系统日期的小时，类型为String
     */
    public static String getHour() {
        StringBuffer tSBql = new StringBuffer();
        int intHour = Calendar.getInstance().get(Calendar.HOUR) + (Calendar.HOUR_OF_DAY + 1)
                * Calendar.getInstance().get(Calendar.AM_PM);
        if (intHour < 10) {
            tSBql.append(CharConst.ZERO_STR);
            tSBql.append(intHour);
        } else {
            tSBql.append(intHour);
        }

        return tSBql.toString();
    }

    /**
     * @description 获取系统日期中的分钟
     * @version
     * @title
     * @return 系统日期的分钟，类型为String
     */
    public static String getMinute() {
        StringBuffer tSBql = new StringBuffer();
        int intMinute = Calendar.getInstance().get(Calendar.MINUTE);
        if (intMinute < 10) {
            tSBql.append(CharConst.ZERO_STR);
            tSBql.append(intMinute);
        } else {
            tSBql.append(intMinute);
        }

        return tSBql.toString();
    }

    /**
     * @description 获取系统日期中的秒数
     * @version
     * @title
     * @return 系统日期的秒数，类型为String
     */
    public static String getSecond() {
        StringBuffer tSBql = new StringBuffer();
        int intSecond = Calendar.getInstance().get(Calendar.SECOND);
        if (intSecond < 10) {
            tSBql.append(CharConst.ZERO_STR);
            tSBql.append(intSecond);
        } else {
            tSBql.append(intSecond);
        }

        return tSBql.toString();
    }

    /**
     * @description 通过输入日期字符串，返回以“-”分隔的日期值的中文表示
     * @version
     * @title
     * @param strDate strDate类型为String,输入日期字符串
     * @return 类型为String YYYY年MM月DD日
     */
    public static String getChnDate(String strDate) {
        StringBuffer strReturn = new StringBuffer();
        strReturn.append(getYear(strDate)).append(CharConst.CHINESE_YEAR_STR).append(getMonth(strDate))
                .append(CharConst.CHINESE_MONTH_STR).append(getDay(strDate)).append(CharConst.CHINESE_DAY_STR);
        return strReturn.toString();
    }

    /**
     * @description 通过输入日期类型数据，返回以“-”分隔的日期值的中文表示
     *              <p>
     *              <b>Example: </b>
     *              <p>
     *              <p>
     *              getString("Tue Oct 08 00:00:00 CST 2002") returns
     *              "2002-10-8"
     *              <p>
     * @param mDate mDate类型为Date,输入日期
     * @return 以“-”分隔的日期字符串，类型为String
     */
    public static String getDateString(Date mDate) {
        SimpleDateFormat df = new SimpleDateFormat(CharConst.FORMAT_STR_DATE);
        String tString = null;
        if (mDate != null) {
            tString = df.format(mDate);
        }
        return tString;
    }

    /**
     * @description 获取以“-”分隔的当前日期
     * @version
     * @title
     * @return 以“-”分隔的当前日期字符串，类型为String
     */
    public static String getToday() {

        return getDateString(Calendar.getInstance().getTime());
    }

    /**
     * @description 获取以“-”分隔的当前日期时间 'yyyy-MM-dd HH:mm:ss'格式
     * @version
     * @title
     * @return 以“-”分隔的当前日期时间字符串，类型为String
     */
    public static String getTodayTime() {

        SimpleDateFormat df = new SimpleDateFormat(PubConstants.DATE_TIME_FORMAT);
        String tString = df.format(Calendar.getInstance().getTime());
        return tString;
    }

    /**
     * @description 获取当前时间
     * @version
     * @title
     * @return 当前时间，类型为Date
     */
    public static Date getTodayDate() {
        return Calendar.getInstance().getTime();
    }

    /**
     * @description 取得当前月份所在季度最后一天的日期
     * @version
     * @title
     * @param setDay setDay类型为int,偏移的日期
     * @return 该月分所在季度的最后一天的日期，类型为String
     */
    public static String getQuarterDate(int setDay) {
        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH); // 系统默认月份从 0 开始
        if (month >= 0 && month <= 2) {
            cal.set(Calendar.MONTH, 3);
        } else if (month >= 3 && month <= 5) {
            cal.set(Calendar.MONTH, 6);
        } else if (month >= 6 && month <= 8) {
            cal.set(Calendar.MONTH, 9);
        } else if (month >= 9 && month <= 11) {
            cal.set(Calendar.MONTH, 12);
        }

        if (setDay > 0 && setDay < 32) {
            cal.set(Calendar.DAY_OF_MONTH, setDay);
        } else {
            cal.set(Calendar.DAY_OF_MONTH, 0);
        }
        String date = new SimpleDateFormat(PubConstants.DATE_FORMAT).format(cal.getTime());
        return date;
    }

    /**
     * @description 获得自动升降级发送短信的时间
     * @version
     * @title
     * @param type 类型为int,时间间隔代码（1 月度、2 季度、3 半年、4 年度）
     * @return 发送短信的时间，类型为String
     */
    public static String getSendSmsDate(int type) {
        switch (type) {
            case 1:
                return getLastDate(PubConstants.DATE_FORMAT, 1); // 月度
            case 2:
                return getQuarterDate(1); // 季度
            case 3:
                return getFirstAndLastYearDate(1); // 半年
            case 4:
                return getLastYearDate(1); // 一年
            default:
                return getQuarterDate(1); // 默认季度
        }
    }

    /**
     * @description 根据时间间隔代码获得该时间段的第一天
     * @version
     * @title
     * @param type 类型为int,时间间隔代码（1 月度、2 季度、3 半年、4 年度）
     * @return 该时间段的第一天时间字符串，类型为String
     */
    public static String getFirstDate(int type) {
        switch (type) {
            case 1:
                return getFirstDate(); // 月度
            case 2:
                return getFirstQuarterDate(); // 季度
            case 3:
                return getFirstYearDate(); // 半年
            case 4:
                return Calendar.getInstance().get(Calendar.YEAR) + "-01-01"; // 一年
            default:
                return getFirstQuarterDate(); // 默认季度
        }
    }

    /**
     * @description 获取本月第一天日期
     * @version
     * @title
     * @return 本月第一天日期字符串，类型为String
     */
    public static String getFirstDate() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        String date = new SimpleDateFormat(PubConstants.DATE_FORMAT).format(cal.getTime());
        return date;
    }

    /**
     * @description 获取当前年的上半年第一天日期或下半年第一天的日期字符串
     * @version
     * @title
     * @param setDay 类型为int,日期设定日
     * @return 当年最后一天的日期字符串，类型为String
     */
    public static String getLastYearDate(int setDay) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, 12);
        cal.set(Calendar.DAY_OF_MONTH, setDay);
        String date = new SimpleDateFormat(PubConstants.DATE_FORMAT).format(cal.getTime());
        return date;
    }

    /**
     * @description 获取当前年的上半年第一天日期或下半年第一天的日期
     * @version
     * @title
     * @return 当前年的上半年第一天日期或下半年第一天的日期字符串，类型为String
     */
    public static String getFirstYearDate() {
        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH); // 系统默认月份从 0 开始
        if (month >= 0 && month <= 5) {
            cal.set(Calendar.MONTH, 0); // 上半年
        } else if (month >= 6 && month <= 11) {
            cal.set(Calendar.MONTH, 6); // 下半年
        }
        cal.set(Calendar.DAY_OF_MONTH, 0);
        String date = new SimpleDateFormat(PubConstants.DATE_FORMAT).format(cal.getTime());
        return date;
    }

    /**
     * @description 获取当前年的上半年第一天日期或下半年第一天的日期字符串
     * @version
     * @title
     * @param setDay setDay类型为int，日期设定日
     * @return 当前年的上半年第一天日期或下半年第一天的日期字符串，类型为String
     */
    public static String getFirstAndLastYearDate(int setDay) {
        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH); // 系统默认月份从 0 开始
        if (month >= 0 && month <= 5) {
            cal.set(Calendar.MONTH, 6); // 上半年
        } else if (month >= 6 && month <= 11) {
            cal.set(Calendar.MONTH, 12); // 下半年
        }
        cal.set(Calendar.DAY_OF_MONTH, setDay);
        String date = new SimpleDateFormat(PubConstants.DATE_FORMAT).format(cal.getTime());
        return date;
    }

    /**
     * @description 获取当前月份的最后一天日期
     * @version
     * @title
     * @param dateFormat 日期格式化
     * @param setDay 需要设定的日
     * @return 类型为String
     */
    public static String getLastDate(String dateFormat, int setDay) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);
        cal.set(Calendar.DAY_OF_MONTH, setDay);
        if (StringUtilsEx.nullToString(dateFormat).equals(PubConstants.BLANK)) {
            dateFormat = PubConstants.DATE_TIME_FORMAT;
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
        }
        String date = new SimpleDateFormat(dateFormat).format(cal.getTime());
        return date;
    }

    /**
     * @description 获得指定年、月的月份最后一天日期
     * @version
     * @title
     * @param year year类型为int,指定日期年份
     * @param month month类型为int，指定日期月份
     * @param dateFormat dateFormat类型为String，指定格式字符串。setDay类型为int，日期设定日
     * @return 输入月份的最后一天日期字符串，类型为String
     */
    public static String getLastDate(int year, int month, String dateFormat) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, 0);
        if (dateFormat == null || dateFormat.equals(PubConstants.BLANK)) {
            dateFormat = PubConstants.DATE_FORMAT;
        }
        String date = new SimpleDateFormat(dateFormat).format(cal.getTime());
        return date;
    }

    /**
     * @description 取得当前月份所在季度第一天的日期
     * @version
     * @title
     * @return 当前月份所在季度第一天的日期字符串，类型为String
     */
    public static String getFirstQuarterDate() {
        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH); // 系统默认月份从 0 开始
        if (month >= 0 && month <= 2) {
            cal.set(Calendar.MONTH, 0);
        } else if (month >= 3 && month <= 5) {
            cal.set(Calendar.MONTH, 3);
        } else if (month >= 6 && month <= 8) {
            cal.set(Calendar.MONTH, 6);
        } else if (month >= 9 && month <= 11) {
            cal.set(Calendar.MONTH, 9);
        }

        cal.set(Calendar.DAY_OF_MONTH, 1);
        String date = new SimpleDateFormat(PubConstants.DATE_FORMAT).format(cal.getTime());
        return date;
    }

    /**
     * @description 按指定的格式格式化日期（java.util.Date类型日期）
     * @version
     * @title
     * @param date java.util.Date类型
     * @param formater String 指定格式
     * @return String 指定格式的字符串
     */
    public static String formatToString(Date date, String formater) {
        if (null == date) {
            return PubConstants.BLANK;
        } else {
            if (StringUtilsEx.isBlank(formater)) {
                formater = PubConstants.DATE_FORMAT;
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formater);
                return simpleDateFormat.format(date);
            } else {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formater);
                return simpleDateFormat.format(date);
            }
        }
    }

    /**
     * @description 按指定的格式格式化日期
     * @version
     * @title
     * @param date String类型 需要格式化的日期
     * @param formater String类型 指定的格式
     * @return 指定格式的字符串
     */
    public static String formatToString(String date, String formater) {
        if (StringUtils.isBlank(date)) {
            return PubConstants.BLANK;
        } else {
            if (StringUtilsEx.isBlank(formater)) {
                formater = PubConstants.DATE_FORMAT;
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formater);
                return simpleDateFormat.format(date);
            } else {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formater);
                return simpleDateFormat.format(date);
            }
        }
    }

    /**
     * @description 按指定的格式，格式化日期
     * @version
     * @title
     * @param date 日期
     * @param formater 指定格式
     * @return java。util。date类型 指定格式的日期
     */
    public static Date formatToDate(String date, String formater) {
        if (StringUtils.isBlank(date)) {
            return null;
        } else {
            if (StringUtils.isBlank(formater)) {
                // 默认格式
                formater = PubConstants.DATE_FORMAT;
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formater);
                try {
                    return simpleDateFormat.parse(date);
                } catch (ParseException e) {
                    logger.error(" formatToDate error ,error info is : ", e);
                    throw new RuntimeException(" formatToDate error");
                }
            } else {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formater);
                try {
                    return simpleDateFormat.parse(date);
                } catch (ParseException e) {
                    logger.error(" formatToDate error ,error info is : ", e);
                    throw new RuntimeException(" formatToDate error");
                }
            }
        }
    }

    /**
     * @description 按指定的格式，格式化日期
     * @version
     * @title
     * @param date java.util.Date类型
     * @param formater 指定的格式
     * @return 指定格式的日期
     */
    public static Date formatDate(Date date, String formater) {
        if (null == date) {
            return null;
        } else {
            if (StringUtils.isBlank(formater)) {
                // 默认格式
                formater = PubConstants.DATE_FORMAT;
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formater);
                String dte = simpleDateFormat.format(date);
                return formatToDate(dte, formater);
            } else {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formater);
                String dte = simpleDateFormat.format(date);
                return formatToDate(dte, formater);
            }
        }
    }

    /**
     * @description 将java.sql.Timestamp按照格式转为String类型
     * @version
     * @title
     * @param tst Timestamp类型 需要格式化的日期
     * @param formater 指定的格式
     * @return 指定格式的日期
     */
    public static String formatTimestamp(Timestamp tst, String formater) {
        if (null == tst) {
            return null;
        } else {
            if (StringUtils.isBlank(formater)) {
                // 默认格式
                formater = PubConstants.DATE_FORMAT;
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formater);
                Date date = null;
                try {
                    // 先转为java.util.Date再转为java.util.String
                    date = simpleDateFormat.parse(tst.toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return simpleDateFormat.format(date);
            } else {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formater);
                Date date = null;
                try {
                    date = simpleDateFormat.parse(tst.toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return simpleDateFormat.format(date);
            }
        }
    }
}
