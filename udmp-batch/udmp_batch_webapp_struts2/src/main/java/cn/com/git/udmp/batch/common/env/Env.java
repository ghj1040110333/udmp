package cn.com.git.udmp.batch.common.env;

import java.util.Properties;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.math.NumberUtils;

import cn.com.git.udmp.common.utils.StringUtils;
import cn.com.git.udmp.component.model.Cst;
import cn.com.git.udmp.core.logging.ILog;

/**
 * 
 * @description 公共基础环境配置
 * @author Spring Cao
 * @date 2016年8月30日 下午1:43:50
 */
public class Env implements ILog{
    /**
     * @Fields DEFAULT_LANGUAGE_ID : 系统默认语言
     */
    public static String DEFAULT_LANGUAGE_ID = "011";

    /**
     * @Fields DEFAULT_MONEY_ID : 系统默认金钱
     */
    public static String DEFAULT_MONEY_ID = "8";

    /**
     * @Fields CURRENCY_DEFAULT_FORMAT : 默认货币格式
     */
    public static String CURRENCY_DEFAULT_FORMAT;

    /**
     * @Fields DATE_DEFAULT_FORMAT : 默认日期格式
     */
    public static String DATE_DEFAULT_FORMAT = "yyyy-MM-dd";

    /**
     * @Fields TIME_DEFAULT_FORMAT : 默认时间格式
     */
    public static String TIME_DEFAULT_FORMAT = "HH:mm:ss";

    /**
     * @Fields CUR_WEB_SERVER_ADDRESS : 当前web服务地址
     */
    public static String CUR_WEB_SERVER_ADDRESS;

    // The login type
    /**
     * @Fields LOGIN_TYPE : 登录类型
     */
    public static String LOGIN_TYPE = "SSO";

    // is batch environment
    /**
     * @Fields IS_BATCH_ENV : 是否是批量的环境变量
     */
    public static boolean IS_BATCH_ENV;

    // adhoc batch processing duration limit
    /**
     * @Fields BATCH_ADHOC_DURATION_LIMIT : adhoc batch processing duration limit
     */
    public static String BATCH_ADHOC_DURATION_LIMIT;

    /**
     * @Fields BATCH_SCHEDULER_INTERNAL :
     */
    public static long BATCH_SCHEDULER_INTERNAL = 1000L;

    /**
     * @Fields BATCH_JOB_INTERNAL :
     */
    public static long BATCH_JOB_INTERNAL = 1000L;

    /**
     * @Fields BATCH_COMMIT_SIZE : 批处理提交大小
     */
    public static int BATCH_COMMIT_SIZE = 10;

    /**
     * @Fields BATCH_ADMIN_EMAIL : 邮件批处理
     */
    public static String BATCH_ADMIN_EMAIL;

    /** 
    * @Fields USER_NAME_MATCH_CASE : 用户名匹配情况 
    */ 
    public static String USER_NAME_MATCH_CASE = "011";

    /** 
    * @Fields ROLE_NAME_MATCH_CASE :  角色名匹配情况
    */ 
    public static String ROLE_NAME_MATCH_CASE = "011";

    /** 
    * @Fields SET_PWD_BY_ADMIN :  系统管理员是否能设置密码
    */ 
    public static boolean SET_PWD_BY_ADMIN;

    // for setting SMTP Host
    /** 
    * @Fields MAIL_HOST :  邮件地址
    */ 
    public static String MAIL_HOST;

    /** 
    * @Fields SYN_LANG_ID :  语言Id
    */ 
    public static String SYN_LANG_ID = "011";

//    /** 
//    * @Fields USER_DEFAULT_ACCESS_ORGANS_TYPE :  用户默认的机构类型
//    */ 
//    public static final String USER_DEFAULT_ACCESS_ORGANS_TYPE = "USER_DEFAULT_ACCESS_ORGANS_TYPE";

    /** 
    * @Fields SYS_ADMIN_EMAIL :  系统管理员Email
    */ 
    public static String SYS_ADMIN_EMAIL;

    /** 
    * @Fields SHOW_CURRENCY_SIGN :  显示货币标记
    */ 
    public static boolean SHOW_CURRENCY_SIGN;

//    /** 
//    * @Fields CODE_TABLE_RADIO_STYLE_RECORD_COUNT :  码表广播样式记录
//    */ 
//    public static int CODE_TABLE_RADIO_STYLE_RECORD_COUNT = 3;

    /** 
    * @Fields CODETABLE_STYLE_DEFINE : 码表样式定义 
    */ 
    public static String CODETABLE_STYLE_DEFINE = "JSP_DEFINE";

    /** 
    * @Fields SHOW_FORMAT_WHEN_IO_OUT :  显示格式
    */ 
    public static boolean SHOW_FORMAT_WHEN_IO_OUT;

    /** 
    * @Fields INPUT_CHARSET_CONTROL :  输入字符集控制
    */ 
    public static String INPUT_CHARSET_CONTROL;

    /** 
    * @Fields NOT_SHOW_STAR_WHEN_NOT_NULL :  不显示启动
    */ 
    public static boolean NOT_SHOW_STAR_WHEN_NOT_NULL;

    /** 
    * @Fields CONFIG_TCODE_DESCCOLUMN_STYLE : TODO
    */ 
    public static boolean CONFIG_TCODE_DESCCOLUMN_STYLE;

    /** 
    * @Fields CODETABLE_ERROR_POPUP_WINDOW :  码表错误是否弹出窗口
    */ 
    public static boolean CODETABLE_ERROR_POPUP_WINDOW;

    private static Properties prop;

    static {
        try {
            logger.info("Env init begin...");

            // 载入本地配置

            Properties envPropeties = new Properties();
            envPropeties.load(Env.class.getClassLoader().getResourceAsStream("META-INF/env/env.properties"));
            prop = envPropeties;

            CURRENCY_DEFAULT_FORMAT = prop.getProperty("CURRENCY_DEFAULT_FORMAT");
            DATE_DEFAULT_FORMAT = prop.getProperty("DATE_DEFAULT_FORMAT");
            TIME_DEFAULT_FORMAT = prop.getProperty("TIME_DEFAULT_FORMAT");
            CUR_WEB_SERVER_ADDRESS = prop.getProperty("CURRENT_WEB_SERVER_ADDRESS");
            USER_NAME_MATCH_CASE = prop.getProperty("USER_NAME_MATCH_CASE");
            ROLE_NAME_MATCH_CASE = prop.getProperty("ROLE_NAME_MATCH_CASE");
            DEFAULT_LANGUAGE_ID = prop.getProperty("DEFAULT_LANGUAGE_ID", "011");
            DEFAULT_MONEY_ID = prop.getProperty("DEFAULT_MONEY_ID", "8"); // default
            LOGIN_TYPE = prop.getProperty("LOGIN_TYPE", "DB");
            IS_BATCH_ENV = BooleanUtils.toBoolean(prop.getProperty("IS_BATCH_ENV"));

            BATCH_ADHOC_DURATION_LIMIT = prop.getProperty("BATCH_ADHOC_DURATION_LIMIT", "3600");

            BATCH_SCHEDULER_INTERNAL = NumberUtils.toLong(prop.getProperty("BATCH_SCHEDULER_INTERNAL", "10000"));
            BATCH_JOB_INTERNAL = NumberUtils.toLong(prop.getProperty("BATCH_JOB_INTERNAL", "5000"));

            SET_PWD_BY_ADMIN = BooleanUtils.toBoolean(prop.getProperty("SET_PWD_BY_ADMIN"));

            BATCH_COMMIT_SIZE = NumberUtils.toInt(prop.getProperty("BATCH_SCHEDULER_INTERNAL", "10"));

            BATCH_ADMIN_EMAIL = prop.getProperty("BATCH_SCHEDULER_INTERNAL");

            MAIL_HOST = prop.getProperty("MAIL_HOST");

            SYS_ADMIN_EMAIL = prop.getProperty("SYS_ADMIN_EMAIL");

            SHOW_CURRENCY_SIGN = BooleanUtils.toBoolean(prop.getProperty("SHOW_CURRENCY_SIGN"));

            if (StringUtils.isNotBlank(prop.getProperty("SYN_LANG_ID"))) {
                SYN_LANG_ID = prop.getProperty("SYN_LANG_ID");
            }

            String codetableDefine = prop.getProperty("CODETABLE_STYLE_DEFINE");

            logger.info("Env init success end!");
            final HostnameVerifier hv = new HostnameVerifier() {
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }
            };
            HttpsURLConnection.setDefaultHostnameVerifier(hv);
        } catch (Throwable ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * @description 获取配置参数
     * @version
     * @title
     * @author jason
     * @param key map的key值
     * @return 获取到的配置参数
     */
    public static String getValue(String key) {
        if (Cst.KEY_CONFIG_FILE_PATH.equalsIgnoreCase(key)) {
            return getExtendConfigDirectory();
        }
        return prop.getProperty(key);
    }

    /**
     * @description 获取配置参数
     * @version
     * @title
     * @author jason
     * @param key map的key值
     * @param defaultValue 自定义默认参数
     * @return 如果获取到配置则返回配置参数，否则返回默认值
     */
    public static String getValue(String key, String defaultValue) {
        String v = getValue(key);
        return (v == null) ? defaultValue : v;
    }

    /**
     * @description 内部参数设置
     * @version
     * @title
     * @param key map的key值
     * @param value 内部参数
     */
    public static void internalSetValue(String key, String value) {
        prop.setProperty(key, value);
    }

    /**
     * @description 获取扩展的配置目录
     * @version
     * @title
     * @return 配置目录
     */
    public static String getExtendConfigDirectory() {
//        return ServerInfo.getEbaoHomeConfig();
        return "";
    }
}
