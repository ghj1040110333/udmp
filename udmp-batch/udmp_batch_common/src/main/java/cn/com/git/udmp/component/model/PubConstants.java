package cn.com.git.udmp.component.model;

/** 
 * @description 公共工具类常量
 * @author chuzm_wb@newchina.live 
 * @date 2014年10月22日 上午9:32:44  
*/
public class PubConstants {

    /** batch deal number */
    public static int BATCH_DEAL_NUM = 50;

    public static long DEFINED_MOD_SEED = 97;

    public static int ONE_THOUSAND = 1000;
    public static int ONE_HUNDRED = 100;
    public static final String TRANSCODE_PREFFIX = "0";

    /** sqlsessiontemplate */
    public static final String SQL_SESSION_TEMPLATE = "sqlSessionTemplate";

    public static final String FIND_PARA_DEF = "findParaDef";

    public static final String FIND_ALL_PARA_DEF = "findAllParaDef";

    public static final String FIND_PARA_VALUE = "findParaDef";
    public static final String FIND_ALL_PARA_VALUE = "findAllParaDef";

    public static final String BASE_DAO = "baseDao";

    public static final String ISO_8859_1_ENCODE = "iso-8859-1";
    public static final String GBK_UNICODE = "ISO8859_1";
    public static final String DEFAULT_ENCODE = "UTF-8";
    public static final String GBK_ENCODE = "GBK";
    public static final String HTML_CONTENT = "text/html; charset=utf-8";

    public static final String CACHE_SWITCH_ON = "on";
    public static final String CACHE_SWITCH_OFF = "off";
    public static final String CACHE_ONE_ENTRY = "entry";

    public static final String MAX_SIZE = "maxSize";
    public static final String PAGE_NO = "pageNo";
    public static final String PAGE_SIZE = "pageSize";

    public static final String GREATER_NUM = "GREATER_NUM";
    public static final String LESS_NUM = "LESS_NUM";

    public static final String COUNT_SUFFIX = "_total";
    public static final String VALID = "0";
    public static final String INVALID = "1";
    public static final String ERROR = "error";
    public static final String LOGIN = "login";
    public static final String LOGOUT = "logout";
    public static final String ADMIN = "admin";
    public static final String SUPER_ADMIN = "superAdmin";
    public static final String LOGIN_USER = "user";
    public static final String LOGINED = "logined";
    public static final String UTF_8 = "UTF-8";

    public static final String BACK = "back";
    public static final String PASS = "pass";
    public static final String OVER_LOAD = "overload";

    public static final String WELCOME = "welcome";
    public static final String INDEX = "index";
    public static final String SUCCESS = "success";
    public static final String INPUT = "input";
    public static final String FAILURE = "failure";
    public static final String EXIST = "exist";

    public static final String IS_NULL = "0";
    public static final String IS_NOT_NULL = "1";

    public static final String PROCESSING = "0";
    public static final String COMPLETED = "1";

    public static final String NEW = "0";
    public static final String DELETED = "1";
    public static final String SYSTEM = "1";

    public static final String CREATE_OPERATION = "CREATE";
    public static final String READ_SINGLE_OPERATION = "FINDSINGLE";
    public static final String READ_ALL_OPERATION = "FINDALL";
    public static final String UPDATE_OPERATION = "UPDATE";
    public static final String DELETE_OPERATION = "DELETE";

    public static final String QUERY_HEAD = "SELECT ";
    public static final String INSERT_HEAD = "INSERT INTO ";
    public static final String DELETE_HEAD = "DELETE FROM ";
    public static final String UPDATE_HEAD = "UPDATE ";
    public static final String FROM = "FROM ";

    public static final String ALL = "* ";

    public static final String AS = "AS ";
    public static final String COUNT = "COUNT";

    public static final String LEFT = "LEFT ";
    public static final String RIGHT = "RIGHT ";
    public static final String JOIN = "JOIN ";

    public static final String WHERE = "WHERE ";
    public static final String WHERE_UPPER = "WHERE";
    public static final String WHERE_LOWER = "where";
    public static final String ON = "ON ";

    public static final String ROWNUM = "ROWNUM ";
    public static final String LIMIT = "LIMIT ";

    public static final String ORACLE_DATABASE = "ORACLE";
    public static final String MYSQL_DATABASE = "MYSQL";

    public static final String DEFAULT_CONDITION = "1 = 1 ";

    public static final String EQUAL_TO = "= ";
    public static final String MAX_RETURN_NUMBER = "1000 ";
    public static final String MAX = "MAX";
    public static final String MIN = "MIN";

    public static final String SET = "SET ";
    public static final String SET_UPPER = "SET";
    public static final String SET_LOWER = "set";
    public static final String AND = "AND ";
    public static final String OR = "OR ";
    public static final String VALUES = "VALUES ";

    public static final String MIDDLE_LEFT_PARENTHESE = "[ ";
    public static final String LEFT_PARENTHESE = "( ";
    public static final String MIDDLE_RIGHT_PARENTHESE = " ] ";
    public static final String RIGHT_PARENTHESE = " ) ";
    public static final String COMMA = ", ";
    public static final String SINGLE_QUOTES = "'";
    public static final String QUESTION_MARK = "?";
    public static final String DOLLAR_SIGN = "$";
    public static final String POUND_SIGN = "#";
    public static final String SET_METHOD_PREFFIX = "set";

    public static final String ORDER_BY = "ORDER BY  ";
    public static final String GROUP_BY = "GROUP BY  ";
    public static final String ASC = "ASC ";
    public static final String DESC = "DESC ";

    public static final String LEFT_TRIM = "LTRIM";
    public static final String RIGHT_TRIM = "RTRIM";
    public static final String UPPER = "UPPER";
    public static final String LOWER = "LOWER";
    public static final String TO_TIMESTAMP = "TO_TIMESTAMP";
    public static final String TO_DATE = "TO_DATE";
    public static final String TO_CHAR = "TO_CHAR";
    public static final String MYSQL_TO_DATE = "str_to_date";

    public static final String TIMESTAMP_FORMAT = "yyyy-MM-dd HH24:mi:ss";
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    // 输出格式 精确到毫秒 15:58:10.411
    public static final String TIME_FORMAT = "HH:mm:ss.S";

    public static final String MYSQL_TIMESTAMP_FORMAT = "%Y-%m-%d %T";
    public static final String MYSQL_DATE_FORMAT = "%Y-%m-%d";
    public static final String BLANK = "";
    public static final String NULL = "null";
    public static final String STRIPING = "-";
    public static final char LOWER_STRIPING = '_';

    public static final String IS_DELETED = "is_deleted";
    public static final String INSERT_BY = "insert_by";
    public static final String INSERT_TIME = "insert_time";
    public static final String UPDATE_BY = "update_by";
    public static final String UPDATE_TIME = "update_time";
    public static final String INSERT_TIMESTAMP = "insert_timestamp";
    public static final String UPDATE_TIMESTAMP = "update_timestamp";

    public static final String SYSTEM_AUDIT = "systemAudit";
    public static final String BUSINESS_AUDIT = "buinessAudit";

    public static final String TRUE = "true";
    public static final String FALSE = "false";
    public static final String DIV = "/";
    
    public static final String LINUX_SIGNAL = "/";
    
    public static final String WINDOWS_SIGNAL = "\\";
    
    public static final String POINT_SIGNAL = ".";

    public static final String VELOCITY_CONFIG_PROPERTIES = "META-INF/velocity/velocity.properties";
    public static final String VELOCITY_TEMPLATE_PATH = "META-INF/velocity/template/";
    public static final String DEFAULT_CACHE_CONFIG = "classpath*:/ehcache-failsafe.xml";
    
    public static final String SET_DATA_METHOD = "setData";
    
    /** 
     * <p>Title: 构造方法</p> 
     * <p>Description: </p>  
    */
    private PubConstants() {
    }

}
