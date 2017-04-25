package cn.com.git.udmp.utils.lang;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import cn.com.git.udmp.component.model.Constants;


/**
 * @description 线程安全的日期工具 支持多线程
 * @author xinhua xinhua@newchina.live
 * @date 2014年10月20日 下午5:23:19
 */
public abstract class UtilDate {

    protected static final ThreadLocal<Map<String, DateFormat>> FORMATS = new ThreadLocal<Map<String, DateFormat>>();

    /**
     * 根据字符串创建DateFormat对象
     * 
     * @param formatStr 需要创建日期格式
     * @return DateFormat DateFormat对象
     */
    protected static DateFormat getFormat(String formatStr) {
        if (FORMATS.get() == null) {
            FORMATS.set(new HashMap<String, DateFormat>());
        }
        Map<String, DateFormat> temp = FORMATS.get();
        if (temp.get(formatStr) == null) {
            DateFormat f = new SimpleDateFormat(formatStr);
            temp.put(formatStr, f);
        }
        return temp.get(formatStr);
    }

    /**
     * 默认yyyy-MM-dd HH:mm:ss格式输入当前时间
     * 
     * @return String
     */
    public static String now() {
        return now(Constants.DEFAULT_FORMAT_STR);
    }

    /**
     * long数字转化为Date
     * 
     * @param time 时间，单位毫秒
     * @return Date 日期
     */
    public static Date parse(long time) {
        return new Date(time);
    }

    /**
     * 给定格式，格式化Date为字符串
     * 
     * @param date 日期
     * @param formatStr 需要的日期格式
     * @return String 格式化后的日期
     */
    public static String format(Date date, String formatStr) {
        DateFormat format = getFormat(formatStr);
        return format.format(date);
    }

    /**
     * 给定格式输入当前时间
     * 
     * @param formatStr 给定格式
     * @return String 给定格式的当前时间
     */
    public static String now(String formatStr) {
        DateFormat format = getFormat(formatStr);
        return format.format(new Date());
    }

    /**
     * hh add 130506 给定格式输入次日日期
     * 
     * @param formatStr 给定日期格式
     * @return String 次日日期
     */
    public static String next(String formatStr) {
        DateFormat format = getFormat(formatStr);
        String date = format.format(new Date());
        return new Integer(Integer.parseInt(date) + 1).toString();
    }

    /**
     * 给定格式，格式化long日期数字为字符串
     * 
     * @param time 时间，毫秒数
     * @param formatStr 给定格式
     * @return String 给定格式的日期
     */
    public static String format(long time, String formatStr) {
        // 给定格式，格式化Date为字符串
        return format(new Date(time), formatStr);
    }

    /**
     * 给定格式，将字符串转化为Date
     * 
     * @param datetime 字符串类型日期
     * @param formatStr 给定日期格式
     * @return Date 转化后的日期
     */
    public static Date parse(String datetime, String formatStr) {
        DateFormat format = getFormat(formatStr);
        try {
            return format.parse(datetime);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 日期加减
     * 
     * @param dateSource - 日期
     * @param unit - 日期加减的单位（年月日小时分钟）。例如，day或days:天；hour或hours:小时……
     * @param number - 要加减的数量，负数为减。
     * @return 返回加减后的日期
     */
    public static Date addDate(Date dateSource, String unit, int number) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateSource);
        if (unit.equalsIgnoreCase("year") || unit.equalsIgnoreCase("years")) {
            calendar.add(Calendar.YEAR, number);
        }
        if (unit.equalsIgnoreCase("month") || unit.equalsIgnoreCase("months")) {
            calendar.add(Calendar.MONTH, number);
        }
        if (unit.equalsIgnoreCase("day") || unit.equalsIgnoreCase("days")) {
            calendar.add(Calendar.DATE, number);
        } else if (unit.equalsIgnoreCase("hour") || unit.equalsIgnoreCase("hours")) {
            calendar.add(Calendar.HOUR, number);
        } else if (unit.equalsIgnoreCase("minute") || unit.equalsIgnoreCase("minutes")) {
            calendar.add(Calendar.MINUTE, number);
        }
        return calendar.getTime();
    }

    /**
     * 获取星期几
     * 
     * @param dateSource 目标日期
     * @return 当日的星期
     */
    public static String getDayOfWeek(Date dateSource) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateSource);
        return toWeekNameCN(calendar.get(Calendar.DAY_OF_WEEK));
    }

    /**
     * 转换成中文星期名称
     * 
     * @param weekNum 目标星期数
     * @return String 要显示的星期数
     */
    public static String toWeekNameCN(int weekNum) {
        switch (weekNum) {
            case 1:
                return "星期日";
            case 2:
                return "星期一";
            case 3:
                return "星期二";
            case 4:
                return "星期三";
            case 5:
                return "星期四";
            case 6:
                return "星期五";
            case 7:
                return "星期六";
            default:
                return null;
        }
    }

    /**
     * yyyy-MM-dd格式输出日期
     * 
     * @param date Date 目标日期
     * @return java.lang.String 字符串类型日期
     */
    public static String dateFmt(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    /**
     * @description 获取yyyy-MM-dd HH:mm:ss 格式当前日期
     * @version
     * @title
     * @author xinhua xinhua@newchinalife.com
     * @return 返回目标格式当前日期
     */
    public static String getDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dt = sdf.format(new Date());
        return dt;
    }

    /**
     * @description 获取yyyy/MM/dd HH:mm:ss 格式当前日期
     * @version
     * @title
     * @author xinhua xinhua@newchinalife.com
     * @return 返回目标格式当前日期
     */
    public static String getDateTime1() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String dt = sdf.format(new Date());
        return dt;
    }

    /**
     * 
     * @return yyMMdd
     */
    /**
     * @description 获取yyMMdd格式当前日期
     * @version
     * @title
     * @author xinhua xinhua@newchinalife.com
     * @return 返回目标格式当前日期
     */
    public static String getDateTime2() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
        String dt = sdf.format(new Date());
        return dt;
    }

    /**
     * 得到系统时间
     * 
     * @return 系统时间
     * @throws Exception 数字转字符串
     */
    public static String getLocalTime() throws Exception {
        String localTime = "";
        Calendar calendar = new GregorianCalendar();
        java.util.Date trialTime = new java.util.Date();
        calendar.setTime(trialTime);
        localTime = UtilDate.num2String(calendar.get(Calendar.HOUR_OF_DAY), 2) + ":"
                + UtilDate.num2String(calendar.get(Calendar.MINUTE), 2) + ":"
                + UtilDate.num2String(calendar.get(Calendar.SECOND), 2);
        return localTime;
    }

    /**
     * 得到系统日期
     * 
     * @return 系统时间
     * @throws Exception 数字转字符串
     */
    public static String getLocalDate() throws Exception {
        String localTime = "";
        Calendar calendar = new GregorianCalendar();
        java.util.Date trialTime = new java.util.Date();
        calendar.setTime(trialTime);
        localTime = UtilDate.num2String(calendar.get(Calendar.YEAR), 4) + "/"
                + UtilDate.num2String(calendar.get(Calendar.MONTH) + 1, 2) + "/"
                + UtilDate.num2String(calendar.get(Calendar.DAY_OF_MONTH), 2);
        return localTime;
    }

    /**
     * @description 数字转字符串 如参数为（12345，3）处理后为123
     * @version
     * @title
     * @author xinhua xinhua@newchinalife.com
     * @param number 目标数字
     * @param length 要截取目标的长度
     * @return 处理后的字符串
     */
    private static String num2String(int number, int length) {
        String result = "" + number;
        int len = result.length();
        if (len > length) {
            result = result.substring(0, length);
        }
        for (int i = 0; i < length - len; i++) {
            result = "0" + result;
        }
        return result;
    }

    /**
     * @description 获取日历
     * @version
     * @title
     * @author xinhua xinhua@newchinalife.com
     * @return 日历字符串
     */
    public static String getCalendarStr() {
        String strYear = "";
        String strMon = "";
        String strDay = "";

        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int day = c.get(Calendar.DATE);
        int month = c.get(Calendar.MONTH);

        strYear = String.valueOf(year).substring(2, 4);

        switch (month) {
            case Calendar.JANUARY:
                strMon = "JAN";
                break;
            case Calendar.FEBRUARY:
                strMon = "FEB";
                break;
            case Calendar.MARCH:
                strMon = "MAR";
                break;
            case Calendar.APRIL:
                strMon = "APR";
                break;
            case Calendar.MAY:
                strMon = "MAY";
                break;
            case Calendar.JUNE:
                strMon = "JUN";
                break;
            case Calendar.JULY:
                strMon = "JUL";
                break;
            case Calendar.AUGUST:
                strMon = "AUG";
                break;
            case Calendar.SEPTEMBER:
                strMon = "SEP";
                break;
            case Calendar.OCTOBER:
                strMon = "OCT";
                break;
            case Calendar.NOVEMBER:
                strMon = "NOV";
                break;
            case Calendar.DECEMBER:
                strMon = "DEC";
                break;
            default:
                strMon = "";
                break;
        }
        strDay = (day >= 10) ? String.valueOf(day) : "0" + String.valueOf(day);

        return strDay + strMon + strYear;
    }

    /**
     * @description 按照指定格式格式化日期，如果日期为空返回null
     * @version
     * @title
     * @author xinhua xinhua@newchinalife.com
     * @param aMask 指定日期格式
     * @param aDate 日期参数
     * @return 格式化后的日期
     */
    public static String getDateTime(String aMask, Date aDate) {
        SimpleDateFormat df = null;
        String returnValue = "";

        if (aDate == null) {
            return "";
        }
        df = new SimpleDateFormat(aMask);
        returnValue = df.format(aDate);

        return returnValue;
    }

    /**
     * @description 将日期转化为字符串
     * @version
     * @title
     * @author xinhua xinhua@newchinalife.com
     * @param pattern 日期格式化
     * @param aDate 传入日期
     * @return 转化后的日期字符串
     */
    public static String convertDateToString(String pattern, Date aDate) {
        return getDateTime(pattern, aDate);
    }

    /**
     * @description 获取本地师转化为 yyMMdd 格式
     * @version
     * @title
     * @author xinhua xinhua@newchinalife.com
     * @return 转化后的日期
     */
    public static String convertCurrentTimeByUSLocale() {
        return new SimpleDateFormat("yyMMdd", Locale.US).format(new Date());
    }

    /**
     * @description: 根据所给日期返回两日期相差的毫秒数
     * 
     * @param max 大日期
     * @param min 小日期
     * @return 相差的毫秒数
     */
    public static long getMillisecond(Date max, Date min) {
        long a1 = max.getTime();
        long a2 = min.getTime();
        long a3 = a1 - a2;

        return a3;
    }

}
