package cn.com.git.udmp.common.validate.datetime;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.com.git.udmp.common.utils.StringUtils;

/** 
 * 日期时间格式校验工具类
 * @author Spring Cao
 * @version v1.0
*/
public class DateTimeUtil {

    /**
     * 是否是有效的日期格式
     * 
     * @param date 被校验的日期
     * @param formater 格式
     * @return true:正确,false:错误
     */
    public static boolean isValidateDate(String date, String formater) {
        boolean result = false;
        if (StringUtils.isNotBlank(date)) {
            if (StringUtils.isBlank(formater)) {
                formater = "\\d{4}-\\d{1,2}-\\d{1,2}";
            }
            Pattern pattern = Pattern.compile(formater);
            if (null != pattern) {
                Matcher matcher = pattern.matcher(date);
                if (null != matcher) {
                    if (matcher.matches()) {
                        StringBuffer datePattern = new StringBuffer();
                        datePattern.append("^((\\d{2}(([02468][048])|([13579][26]))");
                        datePattern.append("[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|");
                        datePattern
                                .append("(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?");
                        datePattern
                                .append("((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?(");
                        datePattern
                                .append("(((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?");
                        datePattern
                                .append("((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))");
                        Pattern dateValidatePattern = Pattern.compile(datePattern.toString());
                        if (null != dateValidatePattern) {
                            Matcher dateValidateMatcher = dateValidatePattern.matcher(date);
                            if (null != dateValidateMatcher) {
                                result = dateValidateMatcher.matches();
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    /**
     * @description 是否是有效的时间格式(HH24:mi)
     * 
     * @param time 被校验的日期
     * @return true:正确,false:错误
     */
    public static boolean isValidateTime(String time) {
        boolean result = false;
        if (StringUtils.isNotBlank(time)) {
            String formater = "\\d{1,2}:\\d{1,2}";
            Pattern pattern = Pattern.compile(formater);
            if (null != pattern) {
                Matcher matcher = pattern.matcher(time);
                if (null != matcher) {
                    StringBuffer timePattern = new StringBuffer();
                    timePattern.append("^([0-2][0-9]):([0-5][0-9])$");
                    Pattern timeValidatePattern = Pattern.compile(timePattern.toString());
                    if (null != timeValidatePattern) {
                        Matcher timeValidateMatcher = timeValidatePattern.matcher(time);
                        if (null != timeValidateMatcher) {
                            result = timeValidateMatcher.matches();
                        }
                    }
                }
            }
        }
        return result;
    }

    /**
     * 是否是有效的时间格式(HH24:mi)
     * 
     * @param time 被校验的日期
     * @param formater 格式
     * @return true:正确,false:错误
     */
    public static boolean isValidateTime(String time, String formater) {
        boolean result = false;
        if (StringUtils.isNotBlank(time)) {
            if (StringUtils.isBlank(formater)) {
                formater = "\\d{1,2}:\\d{1,2}";
            }
            Pattern pattern = Pattern.compile(formater);
            if (null != pattern) {
                Matcher matcher = pattern.matcher(time);
                if (null != matcher) {
                    StringBuffer timePattern = new StringBuffer();
                    timePattern.append("^([0-2][0-9]):([0-5][0-9])$");
                    Pattern timeValidatePattern = Pattern.compile(timePattern.toString());
                    if (null != timeValidatePattern) {
                        Matcher timeValidateMatcher = timeValidatePattern.matcher(time);
                        if (null != timeValidateMatcher) {
                            result = timeValidateMatcher.matches();
                        }
                    }
                }
            }
        }
        return result;
    }

    /**
     * 功能：判断字符串是否为yyyy-MM-dd HH24:mi:ss日期格式
     * 
     * @param dateTime 被校验的日期
     * @return true:正确,false:错误
     */
    public static boolean isValidDateTime(String dateTime) {
        if (StringUtils.isNotBlank(dateTime)) {
            Pattern pattern = Pattern
                    .compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-9]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
            Matcher m = pattern.matcher(dateTime);
            if (m.matches()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * 功能：判断字符串是否为yyyy-MM-dd日期格式
     * 
     * @param date 被校验的日期
     * @return true:正确,false:错误
     */
    public static boolean isValidateDate(String date) {
        boolean result = false;
        if (StringUtils.isNotBlank(date)) {
            String formater = "\\d{4}-\\d{1,2}-\\d{1,2}";
            StringBuffer datePattern = new StringBuffer();
            datePattern.append("^((\\d{2}(([02468][048])|([13579][26]))");
            datePattern.append("[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|");
            datePattern
                    .append("(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?");
            datePattern
                    .append("((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?(");
            datePattern
                    .append("(((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?");
            datePattern.append("((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))");
            Pattern pattern = Pattern.compile(formater);
            Matcher match = pattern.matcher(date);
            if (match.matches()) {
                pattern = Pattern.compile(datePattern.toString());
                match = pattern.matcher(date);
                result = match.matches();
            }
        }
        return result;
    }

    /**
     * 是否是有效的时间格式(HH24:mi:ss)
     * 
     * @param time 被校验的日期
     * @return true:正确,false:错误
     */
    public static boolean isTimeSecondsFormat(String time) {
        boolean result = false;
        if (StringUtils.isNotBlank(time)) {
            String formater = "\\d{1,2}:\\d{1,2}:\\d{1,2}";
            Pattern pattern = Pattern.compile(formater);
            if (null != pattern) {
                Matcher matcher = pattern.matcher(time);
                if (null != matcher) {
                    StringBuffer timePattern = new StringBuffer();
                    timePattern.append("^([0-2][0-9]):([0-5][0-9]):([0-5][0-9])$");
                    Pattern timeValidatePattern = Pattern.compile(timePattern.toString());
                    if (null != timeValidatePattern) {
                        Matcher timeValidateMatcher = timeValidatePattern.matcher(time);
                        if (null != timeValidateMatcher) {
                            result = timeValidateMatcher.matches();
                        }
                    }
                }
            }
        }
        return result;
    }

}
