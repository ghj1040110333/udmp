package cn.com.git.udmp.component.model;

/** 
 * @description 常量 类
 * @author chuzm_wb@newchina.live 
 * @date 2014年10月22日 上午9:30:08  
*/
public class Cst {

    /** days of a month */
    public static int DAYS_OF_MONTH = 31;

    /** IAS application server */
    public static final String APP_SERVER_TYPE_IAS = "9iAS";

    /** WebLogic application server */
    public static final String APP_SERVER_TYPE_WEBLOGIC = "WEBLOGIC";

    /** WebSphere application server */
    public static final String APP_SERVER_TYPE_WEBSPHERE = "WEBSPHERE";

    /** Region name for String resource cache */
    public static String CACHE_STRING_RESOURCE = "STRING_RESOURCE";

    /** Region name for country level organ id of current user */
    public static String CACHE_COUNTRY_LEVEL_ORGAN_ID = "COUNTRY_LEVEL_ORGAN_ID";

    /** Region name for product definition cache */
    public static String CACHE_PRODUCT_DEFINITION = "PRODUCT_DEFINITION";

    /** Region name for agent definition cache */
    public static String CACHE_AGENT_DEFINITION = "AGENT_DEFINITION";

    /** Region name for dept definition cache */
    public static String CACHE_DEPT_DEFINITION = "DEPT_DEFINITION";

    /** Region name for company organ definition cache */
    public static String CACHE_COMPANY_ORGAN_DEFINITION = "COMPANY_ORGAN_DEFINITION";

    /** Region name for employee definition cache */
    public static String CACHE_EMPLOYEE_DEFINITION = "EMPLOYEE_DEFINITION";

    /** Region name for integration definition cache */
    public static final String CACHE_INTEGRATION_DEFINITION = "INTEGRATION_DEFINITION";

    /** Region name for common parameter cache */
    public static final String CACHE_COMMON_PARA = "COMMON_PARA";

    /** Region name for code table cache */
    public static final String CACHE_CODE_TABLE = "CODE_TABLE";

    /** Region name for module urls cache */
    public static final String CACHE_MODULE_URLS = "MODULE_URLS";

    /** Region name for highlight objects cache */
    public static final String CACHE_EXCEPTION_HIGHLIGHT_OBJECS = "EXCEPTION_HIGHLIGHT_OBJECTS";

    /** Region name for roles in data role type */
    public static final String CACHE_ROLE_OF_DATA_ROLE_TYPE = "ROLE_OF_DATA_ROLE_TYPE";

    /** Region name for module role access rights */
    public static final String CACHE_MODULE_ROLE_ACCESS_RIGHTS = "CACHE_MODULE_ROLE_ACCESS_RIGHTS";

    /** Region name for organ role access rights */
    public static final String CACHE_ORGAN_ROLE_ACCESS_RIGHTS = "CACHE_ORGAN_ROLE_ACCESS_RIGHTS";

    /** the region stored the users operation information */
    public static final String CACHE_USER_OPERATION_INFO = "CACHE_USER_OPERATION_INFO";

    /** Region name for setup table role access rights */
    public static final String CACHE_SETUPTABLE_ROLE_ACCESS_RIGHTS = "CACHE_SETUPTABLE_ROLE_ACCESS_RIGHTS";

    /** Region name for system css def */
    public static final String CACHE_CSS_DEF = "CACHE_CSS_DEF";

    public static final String CACHE_V_MONEY = "CACHE_V_MONEY";

    public static final String CACHE_CODETABLE_DATA = "CACHE_CODETABLE_DATA";

    /** string resouce language separator */
    /** string resouce language separator */
    public static String STRING_RESOURCE_LANG_SEPARATOR = "_";

    /** development environment flag */
    public static String ENV_FLAG_DEV = "1";

    /** public development environment flag */
    public static String ENV_FLAG_PUB_DEV = "1.1";

    /** test environment flag */
    public static String ENV_FLAG_TEST = "2";

    /** deploy environment flag */
    public static String ENV_FLAG_DEPLOY = "3";

    /** UAT environment flag */
    public static String ENV_FLAG_UAT = "4";

    /** production environment flag */
    public static String ENV_FLAG_PRODUCTION = "5";

    public static String HIBERNATE = "HIBERNATE";

    // config file anme for multi company support
    public static final String MULTI_COMPANY_SUPPORT_CONFIG_FILE_NAME = "MultiCompanySupport.properties";

    // path for config files
    public static final String KEY_CONFIG_FILE_PATH = "config.file.path";

    // path for absolute path
    public static final String KEY_ABSOLUTE_PATH = "absolutepath.properties";

    // whether current environment is stand alone
    public static final String KEY_STAND_ALONE_APP = "config.stand_alone_app";

    // removed public static final String
    // STARTUP_CONFIG_FILE_NAME="statup.config.properties";
    // key for customer company name parameter
    public static final String KEY_CUSTOMER_COMPANY_NAME = "customer.company.name";

    // key for app server parameter
    public static final String KEY_APP_SERVER_PATH = "config.appserver.path";

    // key for instance name parameter
    public static final String KEY_APP_SERVER_INSTANCE_NAME = "config.appserver.instance.name";

    // base config file name
    public static final String BASE_CONFIG_FILE_NAME = "config.properties";

    // absolute path file name
    public static final String ABSOLUTE_PATH_FILE_NAME = "absolutepath.properties";

    // cache config file name
    public static final String CACHE_CONFIG_FILE_NAME = "cache-config.xml";

    // check interval for config file change
    public static final int CONFIG_FILE_CHECK_INTERVAL = 1000;

    // LOGIN TYPE -- DB
    public static final String LOGIN_TYPE_DB = "DB";

    // LOGIN TYPE -- SSO
    public static final String LOGIN_TYPE_SSO = "SSO";

    // Amin role name
    public static final String M_ADMIN = "M_ADMIN";

    // highligh message created by strRes tag
    public static final String HIGHLIGHT_STRRES_MSG = "HIGHLIGHT_STRRES_MSG";

    public static final String COLUMN_SEQUENCE = "COLUMN_SEQUENCE";

    // business date need reload interval time

    public static final String BIZ_DATE_RELOAD_SEC = "BIZ_DATE_RELOAD_SEC";

    /** the region name of all the time zone ids the the jdk supported */
    public static final String CACHE_ALL_TIME_ZONE_IDS = "CACHE_ALL_TIME_ZONE_IDS";

    public static final String DATABASE_TYPE_ORACLE = "oracle";

    /** Region name for action role access rights */
    public static final String CACHE_ACTION_ROLE_ACCESS_RIGHTS = "CACHE_ACTION_ROLE_ACCESS_RIGHTS";

    /** Region name for action role access rights */
    public static final String CACHE_ACTION_ROLE_ACCESS_RIGHTS_ACTIONVO = "CACHE_ACTION_ROLE_ACCESS_RIGHTS_ACTIONVO";
    
    /** 
     * <p>Title: 构造方法</p> 
     * <p>Description: </p>  
    */
    private Cst() {

    }
}