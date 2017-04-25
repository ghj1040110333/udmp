package cn.com.git.udmp.utils.lang;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Collection;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import cn.com.git.udmp.component.model.CharConst;
import cn.com.git.udmp.component.model.PubConstants;
import cn.com.git.udmp.core.logging.ILog;

/**
 * 
 * @description 提供对字符串操作的工具类
 * @author Spring Cao
 * @date 2016年8月30日 上午11:45:32
 */
public class StringUtilsEx implements ILog{

    private static final String UPPER_CASE = "UPPER_CASE";

    private static final String LOWER_CASE = "LOWER_CASE";

    private static final String NON_FOREIGN_CHAR = new String(new char[] { '\uff1f', '\u2015', '\uff5e', '\u2225',
        '\uff0d', '\uffe0', '\uffe1', '\uffe2', '\uff71', '\uff72', '\uff73', '\uff74', '\uff75', '\uff76',
        '\uff77', '\uff78', '\uff79', '\uff7a', '\uff7b', '\uff7c', '\uff7d', '\uff7e', '\uff7f', '\uff80',
        '\uff81', '\uff82', '\uff83', '\uff84', '\uff85', '\uff86', '\uff87', '\uff88', '\uff89', '\uff8a',
        '\uff8b', '\uff8c', '\uff8d', '\uff8e', '\uff8f', '\uff90', '\uff91', '\uff92', '\uff93', '\uff94',
        '\uff95', '\uff96', '\uff97', '\uff98', '\uff99', '\uff9a', '\uff9b', '\uff9c', '\uff66', '\uff9d',
        '\uff67', '\uff68', '\u30a5', '\uff6a', '\uff6b', '\uff6c', '\uff6d', '\uff6e', '\uff6f', '\uff70',
        '\uff76', '\uff9e', '\uff77', '\uff9e', '\uff78', '\uff9e', '\uff79', '\uff9e', '\uff7a', '\uff9e',
        '\uff7b', '\uff9e', '\uff7c', '\uff9e', '\uff7d', '\uff9e', '\uff7e', '\uff9e', '\uff7f', '\uff9e',
        '\uff80', '\uff9e', '\uff81', '\uff9e', '\uff82', '\uff9e', '\uff83', '\uff9e', '\uff84', '\uff9e',
        '\uff8a', '\uff9e', '\uff8b', '\uff9e', '\uff8c', '\uff9e', '\uff8d', '\uff9e', '\uff8e', '\uff9e',
        '\uff8a', '\uff9f', '\uff8b', '\uff9f', '\uff8c', '\uff9f', '\uff8d', '\uff9f', '\uff8e', '\uff9f' });

    // private static final Pattern PTN_WORD_CHAR = Pattern.compile("(\\w|\\.)+");

    // private static final Pattern PTN_PERSON_NAME = Pattern.compile("[^<>()]+");

    /** Javascript key word array that need to check XSS */
    private static final Pattern[] JAVASCRIPT_KEY_WORD_ARRAY = new Pattern[] { Pattern
        .compile(".*((<[\\x00]*(script|input|iframe|frame|a|img|object|applet)(\\s)+.*>)"
                + "|((http|https|ftp|telnet)://)|(<[\\x00]*script>)|(@import['\"]javascript:)).*") };
    
    private static final Pattern[] SQL_INJECTION_PATTERNS = new Pattern[] { Pattern
        .compile("(/\\*.*\\*/.*=.*)|(('\\s{3}''\\s{3}')|(\\d+|.*'|\\))\\s*(and)\\s*'.*'\\s*=\\s*'.*\\s*('\\s*\\-\\-)?)"
                + "|((\\d+|.*'|\\));?\\s*(select|insert|update|delete|drop|truncate|union|declare|create|exec|having).*\\-\\-)"
                + "|(.*'\\s*(\\|\\||\\+)\\s*'.*'\\s*(\\|\\||\\+)\\s*'.*)") };

    private static NumberFormat format = new DecimalFormat("0.00");
    
    /** 
     * <p>Title: 构造方法 </p> 
     * <p>Description: </p>  
    */
    private StringUtilsEx() {
    }

    /**
     * @description 检验输入字符串是否合法（不包含破坏js脚本信息）
     * @version
     * @title
     * @author xinhua xinhua@newchinalife.com
     * @param value value类型为String，被校验字符串
     * @return 若字符串合法，返回true,否则返回false
    */
    public static boolean checkXss(String value) {
        if (value == null) {
            return true;
        }

        value = value.toLowerCase();
        for (int i = 0; i < JAVASCRIPT_KEY_WORD_ARRAY.length; i++) {
            if (JAVASCRIPT_KEY_WORD_ARRAY[i].matcher(value).find()) {
                return false;
            }
        }
        return true;
    }

    /**
     * @description 将驼峰式命名的字符串转换为下划线大写方式。如果转换前的驼峰式命名的字符串为空，则返回空字符串。
     *  例如：helloWorld->HELLO_WORLD
     * 
     * @param propertyName 转换前的驼峰式命名的字符串
     * @return 转换后下划线大写方式命名的字符串
     */
    public static String propertyNameToColumnName(String propertyName) {
        StringBuilder result = new StringBuilder();

        if (null != propertyName && propertyName.length() > 0 && propertyName.trim().length() > 0) {
            propertyName = propertyName.trim();
            result.append(propertyName.substring(0, 1).toUpperCase());
            // 循环处理其余字符
            for (int i = 1; i < propertyName.length(); i++) {
                String s = propertyName.substring(i, i + 1);
                // 在大写字母前添加下划线
                if (s.equals(s.toUpperCase()) && !Character.isDigit(s.charAt(0))) {
                    result.append("_");
                }
                // 其他字符直接转成大写
                result.append(s.toUpperCase());
            }
        }
        return result.toString();
    }

    /**
     * @description 将下划线大写方式命名的字符串转换为驼峰式。
     *  如果转换前的下划线大写方式命名的字符串为空，则返回空字符串。</br> 例如：HELLO_WORLD->helloWorld
     * 
     * @param columnName 转换前的下划线大写方式命名的字符串
     * @return 转换后的驼峰式命名的字符串
     */
    public static String columnNameToFiledName(String columnName) {
        StringBuilder result = new StringBuilder();
        if (null != columnName && columnName.length() > 0 && columnName.trim().length() > 0) {

            columnName = columnName.trim();

            if (!columnName.contains("_")) {
                // 不含下划线，仅将首字母小写
                // return columnName.substring(0, 1).toLowerCase() + columnName.substring(1);
                return columnName.toLowerCase();
            } else {
                // 用下划线将原始字符串分割
                String[] camels = columnName.split("_");
                for (String camel : camels) {
                    // 跳过原始字符串中开头、结尾的下换线或双重下划线
                    if (camel.isEmpty()) {
                        continue;
                    }
                    // 处理真正的驼峰片段
                    if (result.length() == 0) {
                        // 第一个驼峰片段，全部字母都小写
                        result.append(camel.toLowerCase());
                    } else {
                        // 其他的驼峰片段，首字母大写
                        result.append(camel.substring(0, 1).toUpperCase());
                        result.append(camel.substring(1).toLowerCase());
                    }
                }
            }
        }
        return result.toString();
    }

    /**
     * @description 检验输入字符串是否合法（不包含sql恶意注入信息）
     * @version
     * @title
     * @author xinhua xinhua@newchinalife.com
     * @param value value类型为String，被校验字符串
     * @return  若字符串合法，返回true,否则返回false
    */
    public static boolean checkSqlInject(String value) {
        if (value == null) {
            return true;
        }

        value = value.toLowerCase();
        for (int i = 0; i < SQL_INJECTION_PATTERNS.length; i++) {
            if (SQL_INJECTION_PATTERNS[i].matcher(value).find()) {
                return false;
            }
        }
        return true;
    }

    /**
     * @description 检查该字符串是否为一个整数字符串
     * @version
     * @title
     * @author xinhua xinhua@newchinalife.com
     * @param str str类型为String，被校验字符串
     * @return 若字符串为整数字符串，返回true,否则返回false
    */
    public static boolean isInteger(String str) {
        if (str != null && !str.equals("") && NumberUtils.isDigits(str) && !str.startsWith("0")) {
            return true;
        }
        return false;
    }

    /**
     * @description 检查一个字符串是否为null，如果是null就返回一个空字符串
     * @version
     * @title
     * @author xinhua xinhua@newchinalife.com
     * @param str 类型为String，被校验字符串
     * @return 若字符串为null，返回空字符串,否则返回该字符串
    */
    public static String nullToEmpty(String str) {
        if (str == null) {
            return "";
        }
        return str;
    }

    /**
     * @description 检查一个字符串是否为空字符串，如果是空字符串就返回null
     * @version
     * @title
     * @author xinhua xinhua@newchinalife.com
     * @param str str类型为String，被校验字符串
     * @return  若字符串为null，返回null,否则返回该字符串
    */
    public static String emptyToNull(String str) {
        if (str == null || str.trim().equals("")) {
            return null;
        }
        return str;
    }

    /**
     * @description 将制定的字符串按给定分隔串拆分成字符串数组
     * @version
     * @title
     * @author xinhua xinhua@newchinalife.com
     * @param str 类型为String，被拆分字符串
     * @param splitor splitor类型为String,分隔串。
     * @return 拆分之后的字符串数组，类型为String[]
     * @throws Exception 
    */
    public static String[] split(String str, String splitor) throws Exception {
        if (str == null) {
            throw new Exception();
        }
        StringTokenizer st = new StringTokenizer(str, splitor);
        String[] strAry = new String[st.countTokens()];
        int i = 0;
        while (st.hasMoreTokens()) {
            strAry[i] = st.nextToken();
            i++;
        }
        return strAry;
    }

    /**
     * @description 将java字符串二维数组转换生成一个javascript数组
     * @version
     * @title
     * @author xinhua xinhua@newchinalife.com
     * @param sValue sValue类型为String[][]，被转化字符串数组
     * @return 转化之后的js数组，类型为String
    */
    public static String generateJSArray(String[][] sValue) {
        if (sValue == null) {
            sValue = new String[0][0];
        }
        StringBuffer buff = new StringBuffer(4096);
        buff.append("new Array(");
        String[] ss;
        String s;
        for (int i = 0; i < sValue.length; i++) {
            ss = sValue[i];
            if (ss == null) {
                ss = new String[0];
            }
            if (i > 0) {
                buff.append(",");
            }
            buff.append("new Array(");
            for (int j = 0; j < ss.length; j++) {
                if (j > 0) {
                    buff.append(",");
                }
                s = ss[j];
                if (s == null) {
                    s = "&nbsp;";
                } else if ("".equals(s.trim())) {
                    s = "&nbsp;";
                }
                buff.append("\"" + StringEscapeUtils.escapeJava(s) + "\"");
            }
            buff.append(")");
        }
        buff.append(")");
        return buff.toString();
    }

    /**
     * @description 将一个字符串转换为一个布尔值（转化规则为：若输入串忽略大小写等于“y”、“yes”、“true”，返回true,否则返回false）
     * @version
     * @title
     * @author xinhua xinhua@newchinalife.com
     * @param str str类型为String，被转化字符串
     * @return 若输入串忽略大小写等于“y”、“yes”、“true”，返回true,否则返回false
    */
    public static boolean toBoolean(String str) {
        return "TRUE".equalsIgnoreCase(str) || "Y".equalsIgnoreCase(str) || "YES".equalsIgnoreCase(str);
    }

    /**
     * @description 得到字符串长度，英文占一个字符，中文占2个字符
     * @version
     * @title
     * @author xinhua xinhua@newchinalife.com
     * @param str 类型为String，输入字符串
     * @return 输入字符串长度，类型为int
    */
    public static int getStringLength(String str) {
        int len;
        if ("".equals(str) || str == null) {
            len = 0;
        } else {
            byte[] b = str.getBytes();
            len = b.length;
        }
        return len;
    }

    /**
     * @description 将所有的字符串按照给定的大小写格式转换并返回
     * @version
     * @title
     * @author xinhua xinhua@newchinalife.com
     * @param value 类型为String，输入字符串
     * @param styleCode 类型为String，大小写格式
     * @return 转化之后的字符串，类型为String
    */
    public static String convertCharacterStyle(String value, String styleCode) {
        String result = null;

        if (UPPER_CASE.equals(styleCode)) {
            result = value.toUpperCase();
        } else if (LOWER_CASE.equals(styleCode)) {
            result = value.toLowerCase();
        } else {
            result = value;
        }
        return result;
    }

    /**
     * @description 将html的特殊符号转化成字符串，如@、空格等
     * @version
     * @title
     * @author xinhua xinhua@newchinalife.com
     * @param str 类型为Object，输入对象
     * @return 转化之后的字符串，类型为String
    */
    public static String escapeSpecificHtml(Object str) {
        String strValue = "";
        if (str == null) {
            return "";
        }
        strValue = str.toString();
        return strValue.replaceAll("'", "&#39;").replaceAll("\"", "&quot;").replaceAll("<", "&lt;")
                .replaceAll(">", "&gt;");
    }

    /**
     * @description 将集合转化为字符串，集合中的各项用指定的字符串连接
     * @version
     * @title
     * @author xinhua xinhua@newchinalife.com
     * @param coll 类型为Collection，需转化的集合
     * @param delim 类型为String,连接符
     * @return 转化之后的字符串，类型为String
    */
    public static String collectionToDelimitedString(Collection coll, String delim) {

        if (coll == null) {
            return "";
        }

        StringBuffer sb = new StringBuffer();
        Iterator it = coll.iterator();
        int size = coll.size();
        int i = 0;
        while (it.hasNext()) {
            if (i > 0) {
                sb.append(it.next());
                if (i != size) {
                    sb.append(delim);
                }
            }
            i++;
        }
        return sb.toString();
    }

    /**
     * @description 将一句字符串语句转换成标准的英文语句，即每个单词的首字母大写，其他的字母小写，单词之间用空格隔开 Format a string to a stand English clause e.g: 'THIS IS A TEST
     * CASE' should be converted to 'This Is A Test Case'.
     * @version
     * @title
     * @author xinhua xinhua@newchinalife.com
     * @param clause clause类型为String，需转化的字符串
     * @return 转化之后的字符串，类型为String
    */
    public static String formatClause(String clause) {
        String ret = "";
        String tem = "";
        if (clause != null && !"".equals(clause)) {
            StringTokenizer st = new StringTokenizer(clause, " ");
            while (st.hasMoreElements()) {
                tem = st.nextToken();
                if (!"".equals(tem.trim())) {
                    if (tem.length() > 1) {
                        tem = (tem.substring(0, 1)).toUpperCase() + (tem.substring(1)).toLowerCase();
                    } else {
                        tem = tem.toUpperCase();
                    }
                    ret = ret + tem + " ";
                }
            }
            ret = ret.substring(0, ret.length() - 1);
        }
        return ret;
    }

    /**
     * @description 判断输入是否为JISO208或ASCII编码
     * @version
     * @title
     * @author xinhua xinhua@newchinalife.com
     * @param input 需校验的字符串 
     * @return boolean 若为JISO208或ASCII编码，返回true,否则返回false。
     * @throws Exception 异常 
     */
    public static boolean isJISOrASCIICode(String input) throws Exception {
        if (input == null) {
            return true;
        }
        String chr = null;
        // StringBuffer sb = new StringBuffer();
        // int number = 0;

        for (int i = 0; i < input.length(); i++) {
            chr = input.substring(i, i + 1);
            if (NON_FOREIGN_CHAR.indexOf(chr) == -1) {
                byte[] jis = chr.getBytes("JIS0208");
                if (jis.length < 1 || jis.length > 2) {
                    // throw new Exception("Inregular character found");
                    return false;
                }
                if (jis.length == 2 && jis[0] == 0x21 && jis[1] == 0x29) {
                    /*
                     * if (sb.indexOf(chr) == -1) { 
                     * // not found before if 
                     * (number > 0) sb.append(","); 
                     * sb.append(chr);
                     * number++; 
                     * }
                     */
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * @description 判断是否为null或者空字符串
     * @version
     * @title
     * @author xinhua xinhua@newchinalife.com
     * @param str 需要判断的字符串
     * @return 是否为空
    */
    public static boolean isNullOrEmpty(String str) {
        return isNull(str) || isEmpty(str);
    }

    /**
     * @description 判断是否为null
     * @version
     * @title
     * @author xinhua xinhua@newchinalife.com
     * @param str 需要判断的字符串
     * @return 是否为null
    */
    public static boolean isNull(String str) {
        if (str == null) {
            return true;
        }
        return false;
    }

    /**
     * @description 判断是否为空或者空字符串
     * @version
     * @title
     * @author xinhua xinhua@newchinalife.com
     * @param str 需要判断的字符串
     * @return 是否为空
    */
    public static boolean isEmpty(String str) {
        if (str != null && str.trim().equals("")) {
            return true;
        }
        return false;
    }

    /**
     * @description 用HTML实体除去一个字符串中的字母
     * @version
     * @title
     * @author xinhua xinhua@newchinalife.com
     * @param str 除去的字符串，可能为空
     * @return  除去之后的字符串, 如果输入为空返回空
    */
    public static String escapeHtml(String str) {
        return StringEscapeUtils.escapeHtml3(str);
    }

    /**
     * @description 用java规则除去一个字符串中的字母
     * @version
     * @title
     * @author xinhua xinhua@newchinalife.com
     * @param str 除去的字符串，可能为空
     * @return 除去之后的字符串, 如果输入为空返回空
    */
    public static String escapeJavaScript(String str) {
        return StringEscapeUtils.escapeJava(str);
    }

    // public static String replace(String text, String repl, String with) {
    // return org.apache.commons.lang.StringUtils.replace(text, repl, with);
    // }

    /**
     * @description 返回输入字符串的长度
     * @version
     * @title
     * @author caosb_wb caosb_wb@newchinalife.com
     * @param strSource strSource类型为String，表示源字符串
     * @return  输入字符串的长度，类型为long
    */
    public static long getLength(String strSource) {
        return strSource.getBytes().length;
    }

    /**
     * @description 将空对象（null）转化为空字符串(“”)
     * @version
     * @title
     * @author caosb_wb caosb_wb@newchinalife.com
     * @param obj obj类型为Object，表示需要转化的空对象
     * @return  空字符串，类型为String
    */
    public static String nullToString(final Object obj) {
        return (obj == null) || (PubConstants.NULL.equals(String.valueOf(obj).trim())) ? StringUtils.EMPTY : String
                .valueOf(obj).trim();
    }

    /**
     * @description 得到字符串文字个数
     * @version
     * @title
     * @author xinhua xinhua@newchinalife.com
     * @param strSource strSource类型为String，表示源字符串
     * @return  输入字符串文字的个数，类型为long
    */
    public static long getNum(String strSource) {
        return strSource.length();
    }

    /**
     * @description 判断字符串是否是全为数字
     * @version
     * @title
     * @author xinhua xinhua@newchinalife.com
     * @param strSource strSource类型为，表示源字符串String
     * @return 若输入字符串全为数字，返回true，若否，返回false
     */
    public static boolean isNumber(String strSource) {
        try {
            Double.parseDouble(strSource);
            return true;
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return false;
        }

    }

    /**
     * @description 判断输入字符串中是否含有非数字字符
     * @version
     * @title
     * @author xinhua xinhua@newchinalife.com
     * @param strSource strSource类型为String，表示源字符串
     * @return 若不含有非数字字符，则返回true,若否，则返回false
    */
    public static boolean isPunc(String strSource) {
        try {
            Double.parseDouble(strSource);
            return true;
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return false;
        }

    }

    /**
     * @description 判断字符串是否为空
     * @version
     * @title
     * @author xinhua xinhua@newchinalife.com
     * @param strSource strSource类型为String，表示源字符串
     * @return 若不含有空白字符，则返回true,若否，则返回false
    */
    public static boolean isBlank(String strSource) {
        if (strSource == null || strSource.trim().equals(CharConst.EMPTY_STR)) {
            return true;
        } else {
            return false;
        }

    }
    
    /**
     * @description 去掉左边空格
     * @version
     * @title
     * @author xinhua xinhua@newchinalife.com
     * @param tStr 输入字符串String，表示源字符串
     * @return 返回去掉左边空格的字符串，类型为String
    */
    public static String leftTrim(String tStr) {

        String ttStr = null;
        if (tStr == null || CharConst.EMPTY_STR.equals(tStr)) {
            ttStr = CharConst.EMPTY_STR;
        } else {
            ttStr = tStr.replaceAll("^[　 ]+", CharConst.EMPTY_STR);
        }
        return ttStr;
    }

    /**
     * @description 去掉右边空格
     * @version
     * @title
     * @author xinhua xinhua@newchinalife.com
     * @param tStr 输入字符串String，表示源字符串
     * @return 返回去掉右边空格的字符串，类型为String
    */
    public static String rightTrim(String tStr) {

        String ttStr = null;
        if (tStr == null || tStr.equals(CharConst.EMPTY_STR)) {
            ttStr = CharConst.EMPTY_STR;
        } else {
            ttStr = tStr.replaceAll("[　 ]+$", CharConst.EMPTY_STR);
        }
        return ttStr;
    }

    /**
     * @description 去掉前后空格
     * @version
     * @title
     * @author xinhua xinhua@newchinalife.com
     * @param tStr 输入字符串String，表示源字符串
     * @return 返回去掉前后空格的字符串，类型为String
    */
    public static String cTrim(String tStr) {
        String ttStr = null;
        if (tStr == null) {
            ttStr = CharConst.EMPTY_STR;
        } else {
            ttStr = tStr.trim();
        }
        return ttStr;
    }

    /**
     * @description 去掉所有空格
     * @version
     * @title
     * @author xinhua xinhua@newchinalife.com
     * @param tStr 输入字符串String，表示源字符串
     * @return 返回去掉所有空格的字符串，类型为String
    */
    public static String removeSpace(String tStr) {
        String ttStr = null;
        if (tStr == null) {
            ttStr = CharConst.EMPTY_STR;
        } else {
            ttStr = tStr.replaceAll(" ", CharConst.EMPTY_STR);
        }
        return ttStr;
    }

    /**
     * @description 返回子串在主串中出现第intTimes次的位置（intTimes由输入参数确定）
     * @version
     * @title
     * @author xinhua xinhua@newchinalife.com
     * @param strMain 主字符串
     * @param strSub 子字符串
     * @param intTimes 出现次数
     * @return int 位置值,如果子串在主串中没有出现指定次数,则返回-1
    */
    public static int getPos(String strMain, String strSub, int intTimes) {
        int intCounter = 0; // 循环记数
        int intPosition = 0; // 位置记录
        int intLength = strSub.length(); // 子串长度

        if (intTimes <= 0) {
            return -1;
        }
        while (intCounter < intTimes) {
            intPosition = strMain.indexOf(strSub, intPosition);
            if (intPosition == -1) {
                return -1;
            }
            intCounter++;
            intPosition += intLength;
        }
        return intPosition - intLength;
    }

    /**
     * @description 获取从指定位置开始子串在主串中出现第 n 次的位置
     * @version
     * @title
     * @author xinhua xinhua@newchinalife.com
     * @param strMain String 主字符串
     * @param strSub String 子字符串
     * @param intStartIndex int 起始位置
     * @param intTimes int 出现次数
     * @return int 位置值,如果从起始位置起子串在主串中没有出现指定次数,则返回-1
     */
    public static int getPos(String strMain, String strSub, int intStartIndex, int intTimes) {
        if (strMain.length() - 1 < intStartIndex) {
            return -1;
        }
        int intPosition = getPos(strMain.substring(intStartIndex), strSub, intTimes);
        if (intPosition != -1) {
            intPosition += intStartIndex;
        }
        return intPosition;
    }

    /**
     * @description 计算字符串出现的次数
     * @version
     * @title
     * @author xinhua xinhua@newchinalife.com
     * @param strMain String 原串
     * @param strFind String 查找字符串
     * @return 返回字符串出现的次数
     */
    public static int getTimes(String strMain, String strFind) {
        int intStartIndex = 0;
        int intEndIndex = 0;
        int intTimes = 0;

        if (strMain == null || strMain.equals(CharConst.EMPTY_STR)) {
            return intTimes;
        }

        while ((intEndIndex = strMain.indexOf(strFind, intStartIndex)) > -1) {
            intTimes = intTimes + 1;
            intStartIndex = intEndIndex + strFind.length();
        }
        return intTimes;
    }

    /**
     * @description 将字符串按照指定的分隔字符进行拆分,返回从指定序号的分隔符到前一个分隔符之间的字符串
     * @version
     * @title
     * @author xinhua xinhua@newchinalife.com
     * @param strMain String 主字符串
     * @param strDelimiters String 分隔符
     * @param intSerialNo int 分隔符序号
     * @return String 指定序号的分隔符到前一个分隔符之间的字符串,如果没有找到则返回"" 例如：值赋串类似于 值1|值2|值3|值4| 则intSerialNo=0 return "" intSerialNo=1
     *         return "值1" intSerialNo=5 return ""
     */
    public static String decodeStr(String strMain, String strDelimiters, int intSerialNo) {
        int intIndex = 0; /* 分隔符出现在主字符串中的起始位置 */
        int intCount = 0; /* 在扫描主字符串的过程中,第几次遇到分隔符字符串 */
        String strReturn = CharConst.EMPTY_STR; /* 作为返回值的字符串 */

        if (strMain.length() < strDelimiters.length()) {
            return CharConst.EMPTY_STR; /* 若主字符串比分隔符串还要短的话,则返回空字符串 */
        }

        intIndex = strMain.indexOf(strDelimiters);
        if (intIndex == -1) {
            return CharConst.EMPTY_STR; /* 若主字符串中不存在分隔符,则返回空字符串 */
        }
        /* 未找到分隔符时退出循环,并返回空字符串 */
        while (intIndex != -1) {
            strReturn = strMain.substring(0, intIndex);
            intCount++;
            if (intCount == intSerialNo) {
                if (intIndex == 0) {
                    return CharConst.EMPTY_STR;
                } else {
                    return strReturn.trim();
                }
            }
            strMain = strMain.substring(intIndex + 1);
            intIndex = strMain.indexOf(strDelimiters);
        }
        return CharConst.EMPTY_STR;
    }

    /**
     * @description 拼接两个输入字符串
     * @version
     * @title
     * @author xinhua xinhua@newchinalife.com
     * @param strMain String 传入字符串1
     * @param strAdd String 传入字符串2
     * @return String 拼接后返回
     */
    public static String strAdd(String strMain, String strAdd) {
        StringBuffer stringAdd = new StringBuffer();
        if (strMain == null || strMain.equals(CharConst.EMPTY_STR)) {
            stringAdd.append(CharConst.EMPTY_STR);
        } else {
            stringAdd.append(strMain);
        }
        if (strAdd == null || strAdd.equals(CharConst.EMPTY_STR)) {
            stringAdd.append(CharConst.EMPTY_STR);
        } else {
            stringAdd.append(strAdd);
        }

        return stringAdd.toString();
    }

    /**
     * @description 将输入字符串按照分割符拆分成字符串数组
     * @version
     * @title
     * @author xinhua xinhua@newchinalife.com
     * @param strSet String 字符串
     * @param delimiter char 分隔符
     * @return 拆分之后的字符串数组,类型为String[]
     */
    public static String[] splitString(String strSet, char delimiter) {
        String[] str = strSet.split(String.valueOf(delimiter));
        return str;
    }

    /**
     * @description 转换字符串（主要是SQL语句的条件串）
     * @version
     * @title
     * @author xinhua xinhua@newchinalife.com
     * @param strMessage String 待转换的字符串 (形如:<I>字段名^操作符^字段值^</I>) 新格式为：{[(]字段名 操作符 字段值 [)]连接符号}版本格式串^
     * @return String 返回字符串(SQL语句中的 WHERE 条件子句,但不包括 'where'关键字)
     */
    public static String makeCondition(String strMessage) {
        String strSegment = CharConst.EMPTY_STR;
        String strField = CharConst.EMPTY_STR;
        String strOperator = CharConst.EMPTY_STR;
        String strValue = CharConst.EMPTY_STR;
        String strRemain = CharConst.EMPTY_STR;
        String strReturn = "1=1"; // 恒真条件
        int intPosition = 0;

        if (strMessage.indexOf(CharConst.DELIMITER) < 0) {
            return strMessage;
        }
        strRemain = strMessage;

        if (!strRemain.endsWith(CharConst.DIRECTMODE + CharConst.DELIMITER)) {
            do {
                intPosition = getPos(strRemain, CharConst.DELIMITER, 3);
                if (intPosition < 0) {
                 // 分解完毕
                    return strReturn;
                }
                strSegment = strRemain.substring(0, intPosition + 1).trim();
                strRemain = strRemain.substring(intPosition + 1);
                if (strSegment.length() < 1) {
                 // 分段结束    
                    break;
                }
                strField = decodeStr(strSegment, CharConst.DELIMITER, 1);
                strOperator = decodeStr(strSegment, CharConst.DELIMITER, 2);
                strValue = decodeStr(strSegment, CharConst.DELIMITER, 3);
                if (strValue.equals(CharConst.BLANK)) {
                    strValue = " ";
                }
                StringBuffer strReturnBuffer = new StringBuffer(strReturn);
                strReturn = strReturnBuffer.append(" AND ").append("(").toString();
                // 判断操作符
                if (strOperator.equals(CharConst.BETWEEN)) {
                    intPosition = strValue.indexOf(CharConst.BETWEEN_AND);
                    strReturnBuffer = new StringBuffer(strReturn);
                    strReturn = strReturnBuffer.append(strField).append(" BETWEEN '")
                            .append(strValue.substring(0, intPosition).trim()).append("'").append(" AND  '")
                            .append(strValue.substring(intPosition + 1).trim()).append("' ").toString();
                } else if (strOperator.equals(CharConst.CONTAIN)) {
                    strReturnBuffer = new StringBuffer(strReturn);
                    strReturn = strReturnBuffer.append(strField).append(" matches '").append(strValue).append("*' ")
                            .toString();
                } else {
                    strSegment = CharConst.EMPTY_STR;
                    while (true) {
                        intPosition = strValue.indexOf(CharConst.OR);
                        if (intPosition < 0) {
                            if (strSegment.equals(CharConst.EMPTY_STR)) {
                                strReturnBuffer = new StringBuffer(strReturn);
                                strReturn = strReturnBuffer.append(strField).append(" ").append(strOperator)
                                        .append(" '").append(strValue).append("' ").toString();
                            } else {
                                strReturnBuffer = new StringBuffer(strReturn);
                                strReturn = strReturnBuffer.append(strSegment).append(" OR ").append(strField)
                                        .append(" ").append(strOperator).append(" '").append(strValue.trim())
                                        .append("' ").toString();
                            }
                            break;
                        }
                        StringBuffer strSegmentBuffer = new StringBuffer(strSegment);
                        if (!strSegment.equals(CharConst.EMPTY_STR)) {
                            strSegment = strSegmentBuffer.append(" OR ").toString();
                        }

                        strSegment = strSegmentBuffer.append(strField).append(" ").append(strOperator).append(" '")
                                .append(strValue.substring(0, intPosition).trim()).append("' ").toString();
                        strValue = strValue.substring(intPosition + CharConst.LENGTH_OR);
                    }
                }
                strReturnBuffer = new StringBuffer(strReturn);
                strReturn = strReturnBuffer.append(") ").toString();
            } while (true);
        } else {
            strRemain = strRemain.substring(0,
                    strRemain.length() - CharConst.DIRECTMODE.length() - CharConst.DELIMITER.length());
            if (strRemain.trim().equals(CharConst.EMPTY_STR)) {
                strRemain = "1=1";
            }
            strReturn = strRemain;
        }
        return strReturn;
    }

    /**
     * @description 将字符串转换为GBK字符串
     * @version
     * @title
     * @author xinhua xinhua@newchinalife.com
     * @param strOriginal String 原串
     * @return String 将原串由ISO8859_1(Unicode)编码转换为GBK编码
     */
    public static String unicodeToGBK(String strOriginal) {
        if (strOriginal != null) {
            try {
                // 如果在这里不作任何处理，全部直接返回的话，会是什么现象？
                if (isGBKString(strOriginal)) {
                    logger.debug("It's GBK: " + strOriginal);
                    return strOriginal;
                } else {
                    logger.debug("It's ISO8859_1: " + strOriginal);
                    return new String(strOriginal.getBytes("ISO8859_1"), "GBK");
                }

            } catch (Exception exception) {
                logger.error(exception.getMessage());
                return strOriginal;
            }
        } else {
            return CharConst.EMPTY_STR;
        }
    }

    /**
     * @description 将字符串转换为Unicode字符串
     * @version
     * @title
     * @author xinhua xinhua@newchinalife.com
     * @param strOriginal String 原串
     * @return String 将原串由GBK编码转换为ISO8859_1(Unicode)编码
     */
    public static String gBKToUnicode(String strOriginal) {
        if (CharConst.CHANGECHARSET) {
            return getUniCode(strOriginal);
        } else {
            if (strOriginal == null) {
                return CharConst.EMPTY_STR;
            } else {
                return strOriginal;
            }
        }
    }

    /**
     * @description 获取中文的UniCode编码。
     * @version
     * @title
     * @author xinhua xinhua@newchinalife.com
     * @param strOriginal 中文字符串
     * @return 中文字符串的UniCode编码
     */
    private static String getUniCode(String strOriginal) {
        if (strOriginal != null) {
            if (isGBKString(strOriginal)) {
                try {
                    return new String(strOriginal.getBytes("GBK"), PubConstants.GBK_UNICODE);
                } catch (UnsupportedEncodingException e) {
                    logger.error(e.getMessage());
                    return strOriginal;
                }
            } else {
                return strOriginal;
            }
        } else {
            return null;
        }
    }

    /**
     * @description 将字符串转换为Unicode字符串
     * @version
     * @title
     * @author xinhua xinhua@newchinalife.com
     * @param strOriginal String 原串
     * @param realConvert boolean 是否确认转换
     * @return String 将原串由GBK编码转换为ISO8859_1(Unicode)编码
     */
    public static String gBKToUnicode(String strOriginal, boolean realConvert) {
        if (!realConvert) {
            return unicodeToGBK(strOriginal);
        }
        return getUniCode(strOriginal);
    }

    /**
     * @description 判断是否是GBK编码
     * @version
     * @title
     * @author xinhua xinhua@newchinalife.com
     * @param tStr tStr类型为String，表示判断字符串
     * @return 若输入字符串是GBK编码，返回true，否则，返回false
     */
    public static boolean isGBKString(String tStr) {

        Pattern tPattern = Pattern.compile("[\\u4e00-\\u9fa5]");
        Matcher tMatcher = tPattern.matcher(tStr);
        return tMatcher.find();

    }

    /**
     * @description 判断是否是Unicode编码
     * @version
     * @title
     * @author xinhua xinhua@newchinalife.com
     * @param tStr tStr类型为String，表示判断字符串
     * @return 若输入字符串是Unicode编码，返回true，否则，返回false
     */
    public static boolean isUnicodeString(String tStr) {
        int tlength = tStr.length();
        int t1 = 0;
        for (int i = 0; i < tlength; i++) {
            t1 = Integer.parseInt(Integer.toOctalString(tStr.charAt(i)));
            if (t1 > 511) {
                return false;
            }
        }
        return true;
    }

    /**
     * @description 使用指定类中的decode()方法解包给定字符串，返回null时表示出现异常
     * @version
     * @title
     * @author xinhua xinhua@newchinalife.com
     * @param strMessage String 字符串
     * @param intCount int 解包次数
     * @param cl Class 包含decode()方法的类,并且此类中含有FIELDNUM属性
     * @return Vector 将每个解包数据生成对应的类实例,并将这些实例作为返回Vector的元素
     */
    public static Vector<Object> stringToVector(String strMessage, int intCount, Class<?> cl) {
        int intFieldNum = 0;
        Object object = null;
        Vector<Object> vec = new Vector<Object>();
        int intPosition = 0;
        Class<?>[] parameters = { String.class };
        Method method = null;
        Field field = null;
        String[] therecord = { new String() };
        try {
            object = cl.newInstance();
            method = cl.getMethod("decode", parameters);
            field = cl.getField("FIELDNUM");
            intFieldNum = field.getInt(object);

            for (int i = 0; i < intCount; i++) {
                object = cl.newInstance();
                intPosition = getPos(strMessage, CharConst.PACKAGESPILTER, intFieldNum);

                if (intPosition == strMessage.length() - 1) {
                    therecord[0] = strMessage;
                    method.invoke(object, therecord);
                    vec.addElement(object);
                    break;
                } else {
                    therecord[0] = strMessage.substring(0, intPosition + 1);

                    method.invoke(object, therecord);
                    vec.addElement(object);
                    strMessage = strMessage.substring(intPosition + 1);
                }
            }
        } catch (Exception exception) {
            logger.error(exception.getMessage());
            return null;
        } 
        return vec;
    }

    /**
     * @description 将主串中与查找串相等的子串序列替换成子串
     * @version
     * @title
     * @author xinhua xinhua@newchinalife.com
     * @param strMain String 原串
     * @param strFind String 查找字符串
     * @param strReplaceWith String 替换字符串
     * @return String 替换后的字符串，如果原串为空或者为""，则返回""
     */
    public static String replace(String strMain, String strFind, String strReplaceWith) {
        StringBuffer tSBql = new StringBuffer();
        int intStartIndex = 0;
        int intEndIndex = 0;

        if (strMain == null || strMain.equals(CharConst.EMPTY_STR)) {
            return CharConst.EMPTY_STR;
        }

        while ((intEndIndex = strMain.indexOf(strFind, intStartIndex)) > -1) {
            tSBql.append(strMain.substring(intStartIndex, intEndIndex));
            tSBql.append(strReplaceWith);

            intStartIndex = intEndIndex + strFind.length();
        }
        tSBql.append(strMain.substring(intStartIndex, strMain.length()));

        return tSBql.toString();
    }

    /**
     * @description  字符串转换成HTML格式
     * @version
     * @title
     * @author xinhua xinhua@newchinalife.com
     * @param strInValue String 传入字符串
     * @return 返回之后的HTML格式的字符串，类型为String
     */
    public static String toHTMLFormat(String strInValue) {
        StringBuffer tSBql = new StringBuffer();
        char c;

        for (int i = 0; i < strInValue.length(); i++) {
            c = strInValue.charAt(i);
            switch (c) {
                case '<':
                    tSBql.append("&lt;");
    
                    break;
                case '>':
                    tSBql.append("&gt;");
    
                    break;
                case '\n':
                    tSBql.append("<br>");
    
                    break;
                case '\r':
                    break;
                case ' ':
                    tSBql.append("&nbsp;");
    
                    break;
                default:
                    tSBql.append(c);
    
                    break;
            }
        }
        return tSBql.toString();
    }

    /**
     * @description 包装字符串中的转义字符，将其变为可输出字符，如：'\n'，包装之后为'\\n'
     * @version
     * @title
     * @author xinhua xinhua@newchinalife.com
     * @param strInValue strInvalue类型为String，表示传入字符串
     * @return 返回包装之后的字符串，类型为String
     */
    public static String encode(String strInValue) {
        StringBuffer tSBql = new StringBuffer();
        char c;

        for (int i = 0; i < strInValue.length(); i++) {
            c = strInValue.charAt(i);
            switch (c) {
                case ':':
    
                    // hardcode 同Common.js中 NAMEVALUEDELIMITER //域名与域值的分隔符
                    tSBql.append("：");
    
                    break;
                case '|':
    
                    // hardcode 同Common.js中 FIELDDELIMITER //域之间的分隔符
                    tSBql.append("┃");
    
                    break;
                case '\n':
                    tSBql.append("\\n");
    
                    break;
                case '\r':
                    tSBql.append("\\r");
    
                    break;
                case '\"':
                    tSBql.append("\\\"");
    
                    break;
                case '\'':
                    tSBql.append("\\\'");
    
                    break;
                case '\b':
                    tSBql.append("\\b");
    
                    break;
                case '\t':
                    tSBql.append("\\t");
    
                    break;
                case '\f':
                    tSBql.append("\\f");
    
                    break;
                case '\\':
                    tSBql.append("\\\\");
    
                    break;
                case '<':
                    tSBql.append("\\<");
    
                    break;
                case '>':
                    tSBql.append("\\>");
    
                    break;
                default:
                    tSBql.append(c);
    
                    break;
            }
        }
        return tSBql.toString();
    }

    /**
     * @description 将二维数组进行数据打包
     * @version
     * @title
     * @author xinhua xinhua@newchinalife.com
     * @param arr String[][] 存储数据的二维数组
     * @return String 按照编码规则将数组转换后得到的字符串
     */
    public static String encode(String[][] arr) {
        StringBuffer tSBql = new StringBuffer();
        int rowcount = arr.length; // 取得数组的行数
        int colcount = arr[0].length; // 取得数组的列数
        int eleCount = rowcount * colcount;

        if (eleCount != 0) {
            tSBql.append("0");
            tSBql.append("|");
            tSBql.append(String.valueOf(rowcount));
            tSBql.append("^");
            for (int i = 0; i < rowcount; i++) {
                for (int j = 0; j < colcount; j++) {
                    if (j != colcount - 1) {
                        tSBql.append(arr[i][j]);
                        tSBql.append("|");
                    }
                }
                if (i != rowcount - 1) {
                    tSBql.append("^");
                }
            }
        }
        return tSBql.toString();
    }

    /**
     * @description 生成给定长度的字符串（有指定长度个空格组成）
     * @version
     * @title
     * @author xinhua xinhua@newchinalife.com
     * @param intLength int 字符串长度
     * @return 返回指定长度的字符串，类型为String
     */
    public static String space(int intLength) {
        StringBuffer strReturn = new StringBuffer();
        for (int i = 0; i < intLength; i++) {
            strReturn.append(" ");
        }
        return strReturn.toString();
    }

    /**
     * @description 以指定内容生成给定长度的字符串,不足以空格补齐,超长截去
     * @version
     * @title
     * @author xinhua xinhua@newchinalife.com 
     * @param strValue String 指定内容
     * @param intLength int 字符串长度
     * @return 返回截去或补足之后的字符串，类型为String
     */
    public static String space(String strValue, int intLength) {
        int strLen = strValue.length();

        StringBuffer strReturn = new StringBuffer();
        if (strLen > intLength) {
            strReturn.append(strValue.substring(0, intLength));
        } else {
            if (strLen == 0) {
                strReturn.append(" ");
            } else {
                strReturn.append(strValue);
            }

            for (int i = strLen; i < intLength; i++) {
                strReturn.append(" ");
            }
        }
        return strReturn.toString();
    }

    /**
     * @description 以指定内容生成给定长度的字符串,不足以指定字符按指定方式补齐,超长截去
     * @version
     * @title
     * @author xinhua xinhua@newchinalife.com 
     * @param strValue String 指定内容
     * @param intLength int 字符串长度
     * @param appendchar char 指定字符
     * @param lRApp char 指定方式 L:左补齐 R:右补齐
     * @return 返回截去或不足足之后的字符串，类型为String
     */
    public static String getStringWith(String strValue, int intLength, char appendchar, char lRApp) {
        int strLen = strValue.length();

        StringBuffer strReturn = new StringBuffer();
        if (strLen > intLength) {
            strReturn.append(strValue.substring(0, intLength));
        } else {
            if (strLen == 0) {
                strReturn.append(appendchar);
            } else {
                if (lRApp == 'R') {
                    strReturn.append(strValue); // 右补齐
                }
            }

            for (int i = strLen; i < intLength; i++) {
                strReturn.append(appendchar);
            }
            if (strLen > 0) {
                if (lRApp == 'L') {
                    strReturn.append(strValue); // 左补齐
                }
            }
        }
        return strReturn.toString();
    }

    /**
     * @description 将源文件复制指定的目标文件
     * @version
     * @title
     * @author xinhua xinhua@newchinalife.com 
     * @param fromFile 表示源文件路径
     * @param toFile 表示目标文件路径
     * @return 若复制成功返回true，若失败返回fasle
     */
    public static boolean copyFile(String fromFile, String toFile) {
        FileInputStream in;
        FileOutputStream out;
        try {
            in = new FileInputStream(fromFile);
            out = new FileOutputStream(toFile);
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage());
            return false;
        }

        byte[] b = new byte[1024];
        int len;

        try {
            while ((len = in.read(b)) != -1) {
                out.write(b, 0, len);
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
            return false;
        }
        try {
            out.close();
            in.close();
        } catch (IOException e) {
            logger.error(e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * @description 将输入数字转化为中文大写（如：’1’转化之后为’壹’）
     * @version
     * @title
     * @author xinhua xinhua@newchinalife.com 
     * @param intValue 表示输入数字
     * @return 转化之后的字符串，类型为String
     */
    public static String toUpper(int intValue) {
        String strOutValue = CharConst.EMPTY_STR;
        String[] strTemp = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };
        try {
            strOutValue = strTemp[intValue];
        } catch (Exception exception) {
            logger.error("数字大写转换失败：" + exception.getMessage());
            // Log.printException(exception.toString());
            strOutValue = CharConst.EMPTY_STR;
        }
        return strOutValue;
    }

    /**
     * @description 输入中文数字单位数组的下标，获得大写中文数字单位，单位数组为
     * { "仟", "佰", "拾", "亿", "仟", "佰", "拾", "万", "仟", "佰","拾"}
     * @version
     * @title
     * @author xinhua xinhua@newchinalife.com
     * @param intValue 表示输入数字
     * @return 返回下标对应的单位，类型为String
     */
    public static String getUnit(int intValue) {
        String strOutValue = CharConst.EMPTY_STR;
        String[] strTemp = { "仟", "佰", "拾", "亿", "仟", "佰", "拾", "万", "仟", "佰", "拾", CharConst.EMPTY_STR,
            CharConst.EMPTY_STR, CharConst.EMPTY_STR };

        try {
            strOutValue = strTemp[intValue];
        } catch (Exception exception) {
            logger.error("获得单位失败：" + exception.getMessage());
            // Log.printException(exception.toString());
            strOutValue = CharConst.EMPTY_STR;
        }
        return strOutValue;
    }

    /**
     * @description 浮点数字转换为中文大写(数据不能超过12位)
     * @version
     * @title
     * @author xinhua xinhua@newchinalife.com
     * @param dblInValue double表示输入数字
     * @return 转化之后的字符串，类型为String
     */
    public static String toChinese(double dblInValue) {
        StringBuffer strOutValue = new StringBuffer(CharConst.EMPTY_STR);
        String strValue = new DecimalFormat("0").format(dblInValue * 100);
        String strTemp = "                 ";
        String strThat = CharConst.EMPTY_STR;
        int i = 0;
        int j = 0;
        int k = 0;

        k = strValue.length();
        if (k > 14) {
            return CharConst.EMPTY_STR;
        }

        strValue = strTemp.substring(0, 14 - k) + strValue;

        for (i = 14 - k; i < 14; i++) {

            j = new Integer(strValue.substring(i, i + 1)).intValue();
            if (j > 0) {
                strOutValue.append(strThat).append(toUpper(j)).append(getUnit(i));
                strThat = CharConst.EMPTY_STR;
            } else {
                if (i == 11) {
                    strOutValue.append(getUnit(i));
                }

                if (i == 7 && !"0000".equals(strValue.substring(4, 8))) {
                    strOutValue.append(getUnit(i));
                }

                if (i == 3 && !"0000".equals(strValue.substring(0, 4))) {
                    strOutValue.append(getUnit(i));
                }

                if (i < 11) {
                    strThat = toUpper(0);
                }

                if (i == 12) {
                    strThat = toUpper(0);
                }
            }
        }
        return strOutValue.toString();
    }

    /**
     * @description 整数转换为中文大写(数据不能超过12位)
     * @version
     * @title
     * @author xinhua xinhua@newchinalife.com
     * @param intInValue intInValue表示输入数字
     * @return 转化之后的字符串，类型为String
     */
    public static String toChinese(int intInValue) {
        return toChinese((double) intInValue);
    }

    /**
     * @description 长整型数字转换为中文大写(数据不能超过12位)
     * @version
     * @title
     * @author xinhua xinhua@newchinalife.com
     * @param longInValue longInValue表示输入数字
     * @return 转化之后的字符串，类型为String
     */
    public static String toChinese(long longInValue) {
        return toChinese((double) longInValue);
    }

    /**
     * @description 将字符串补数 将sourString的前面用cChar补足cLen长度的字符串
     * @version
     * @title
     * @author xinhua xinhua@newchinalife.com
     * @param sourString 表示源字符串
     * @param cChar 表示补足字符串
     * @param cLen  表示补足长度
     * @return 返回补足之后的字符串，类型为String字符串,如果字符串超长，则不做处理
     */
    public static String lCh(String sourString, String cChar, int cLen) {
        int tLen = sourString.length();
        int i;
        int iMax;
        String tReturn = CharConst.EMPTY_STR;
        if (tLen >= cLen) {
            return sourString;
        }
        iMax = cLen - tLen;
        for (i = 0; i < iMax; i++) {
            tReturn += cChar;
        }
        tReturn = tReturn.trim() + sourString.trim();
        return tReturn;
    }

    /**
     * @description 该函数得到源字符串中的第n个以指定的分割符分割的字符串
     * （如：源字符串为”b/a/a”，分隔符为”/”，n为1，得到的字符串为”b”）
     * @version
     * @title
     * @author xinhua xinhua@newchinalife.com
     * @param cStr 源字符串
     * @param ci 表示第c_i个分割的字符串
     * @param cSplit 表示分割字符串
     * @return 返回分割之后的字符串，类型为String
     */
    public static String getStr(String cStr, int ci, String cSplit) {
        String tStr1 = CharConst.EMPTY_STR;
        String tStr2 = CharConst.EMPTY_STR;
        String tstrOld = CharConst.EMPTY_STR;
        int i = 0;
        int iStart = 0;
        tStr1 = cStr;
        tStr2 = cSplit;
        i = 0;
        try {
            while (i < ci) {
                iStart = tStr1.indexOf(tStr2, 0);
                if (iStart >= 0) {
                    i += 1;
                    tstrOld = tStr1;
                    tStr1 = tStr1.substring(iStart + tStr2.length(), tStr1.length());
                } else {
                    if (i != ci - 1) {
                        tStr1 = CharConst.EMPTY_STR;
                    }
                    break;
                }
            }

            if (iStart >= 0) {
                tStr1 = tstrOld.substring(0, iStart);
            }
        } catch (Exception ex) {
            logger.error("获得单位失败：" + ex.getMessage());
            tStr1 = CharConst.EMPTY_STR;
        }
        return tStr1;
    }

    /**
     * @description 将主串中与匹配串匹配的字符序列替换为给定替换字符串（如源字符串为"abcabcabc"，匹配串为”abc”，替换字符串为”v”，得到的结果为”vvv”）
     * @version
     * @title
     * @author xinhua xinhua@newchinalife.com
     * @param strMain String 原串
     * @param strFind String 匹配字符串
     * @param strReplaceWith String 替换字符串,在替换时不区分大小写
     * @return String 替换后的字符串，如果原串为空或者为""，则返回""
     */
    public static String replaceEx(String strMain, String strFind, String strReplaceWith) {
        StringBuffer tSBql = new StringBuffer();
        String tStrMain = strMain.toLowerCase();
        String tStrFind = strFind.toLowerCase();
        int intStartIndex = 0;
        int intEndIndex = 0;

        if (strMain == null || strMain.equals(CharConst.EMPTY_STR)) {
            return CharConst.EMPTY_STR;
        }

        while ((intEndIndex = tStrMain.indexOf(tStrFind, intStartIndex)) > -1) {
            tSBql.append(strMain.substring(intStartIndex, intEndIndex));
            tSBql.append(strReplaceWith);

            intStartIndex = intEndIndex + strFind.length();
        }
        tSBql.append(strMain.substring(intStartIndex, strMain.length()));

        return tSBql.toString();
    }

    /**
     * @description 比较两个字符串是否相等，若相等，返回true,否则，返回false。(处理空值的情况：将null视为空字符串””，即输入参数分别为null和””时，函数返回ture)
     * @version
     * @title
     * @author xinhua xinhua@newchinalife.com
     * @param a String 表示比较字符串
     * @param b String 表示比较字符串
     * @return boolean 如果相同返回true,否则返回fasle
     */
    public static boolean compareString(String a, String b) {
        // 这个地方是否需要修改，还需要进一步考虑，如果修改的话，仍需要保证null=""的情况
        if (unicodeToGBK(cTrim(a)).equals(unicodeToGBK(cTrim(b)))) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @description 将数值转化为精确到小数点后两位的浮点数，即金额形式 0.00
     * @version
     * @title
     * @author xinhua xinhua@newchinalife.com
     * @param value value类型为String，表示输入数字
     * @return 格式化之后的字符串，类型为String
     */
    public static String formatDec(double value) {
        try {
            return format.format(value);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return String.valueOf(value);
        }
    }
}
