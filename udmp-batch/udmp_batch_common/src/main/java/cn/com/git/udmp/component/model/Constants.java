package cn.com.git.udmp.component.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;

/**
 * @description 公共常量
 * @author wulei_wb wulei_wb@newchina.live
 * @date 2014年10月20日 上午10:46:15
 */
public class Constants {
    /**
     * @Fields CURRENT_SESSION : JSP中session的一个属性（当前会话）
     */
    public static final String CURRENT_SESSION = "currentSession";
    /**
     * @Fields CURRENT_USER : JSP中session的一个属性（当前登录用户）
     */
    public static final String CURRENT_USER = "currentUser";
    /**
     * @Fields CURRENT_SESSION_TMP_PARAM : JSP中session的一个属性（参数）
     */
    public static final String CURRENT_SESSION_TMP_PARAM = "params";
    /**
     * @Fields ORDER : 分页标签中使用查询结果排序方式--允许排序
     */
    public static final String ORDER = "ORDER";
    /**
     * @Fields ORDER_DESC : 分页标签中使用查询结果排序方式--降序
     */
    public static final String ORDER_DESC = "desc";
    /**
     * @Fields ORDER_ASC : 分页标签中使用查询结果排序方式--升序
     */
    public static final String ORDER_ASC = "asc";
    /**
     * @Fields PAGER_PAGE_SIZE : 分页标签中每页条数
     */
    public static final int PAGER_PAGE_SIZE = 10;
    /**
     * @Fields PAGER_PAGE_SIZE_MAX : 分页标签中每页条数
     */
    public static final int PAGER_PAGE_SIZE_MAX = 100;
    /**
     * @Fields CODE_TYPE : 系统编码 UTF-8
     */
    public static final String CODE_TYPE = "UTF-8";
    /**
     * @Fields SYS_TIME_OUT_STEP1 : 系统过期时间,单位分钟 当缓存中没有数据时有效
     */
    public static final Long SYS_TIME_OUT_STEP1 = 5L;
    /**
     * @Fields SYS_TIME_OUT_STEP2 : 系统长期过期时间，单位分钟 当缓存中没有数据时有效
     */
    public static final Long SYS_TIME_OUT_STEP2 = 15L;
    /**
     * @Fields MAX_LENGTH_INTEGER : 数字转字符串时的整数、小数最大位数
     */
    public static final int MAX_LENGTH_INTEGER = 12;
    /**
     * @Fields MAX_LENGTH_FRACTION : 数字转字符串时的整数、小数最大位数
     */
    public static final int MAX_LENGTH_FRACTION = 12;
    /**
     * @Fields PAGE_NUM : 分页默认信息 ---页数1
     */
    public static final int PAGE_NUM = 1;
    /**
     * @Fields NUM_PER_PAGE : 分页默认信息 ---每页展示的条数20
     */
    public static final int NUM_PER_PAGE = 20;
    /**
     * @Fields START_MESSAGE : 分页默认信息 ---初始第1页
     */
    public static final int START_MESSAGE = 1;
    /**
     * @Fields DEFAULT_DATE_FORMAT_STR : 日期样式 yyyy-MM-dd
     */
    public static final String DEFAULT_DATE_FORMAT_STR = "yyyy-MM-dd";
    /**
     * @Fields SHORT_DATE_FORMAT_STR : 日期样式 yyyyMMdd
     */
    public static final String SHORT_DATE_FORMAT_STR = "yyyyMMdd";
    /**
     * @Fields DATE_S_FORMAT_STR : 日期样式 yyyy/MM/dd
     */
    public static final String DATE_S_FORMAT_STR = "yyyy/MM/dd";
    /**
     * @Fields TIME_S_FORMAT_STR : 日期样式 yyyy/MM/dd
     */
    public static final String TIME_S_FORMAT_STR = "yyyy/MM/dd HH:mm:ss";
    /**
     * @Fields DEFAULT_FORMAT_STR : 日期样式 yyyy-MM-dd HH:mm:ss
     */
    public static final String DEFAULT_FORMAT_STR = "yyyy-MM-dd HH:mm:ss";
    /**
     * @Fields FULL_FORMAT_STR : 日期样式 yyyy-MM-dd HH:mm:ss SSS
     */
    public static final String FULL_FORMAT_STR = "yyyy-MM-dd HH:mm:ss SSS";
    /**
     * @Fields FULL_TIMES_FORMAT_STR : 日期样式 yyyyMMdd HH:mm:ss
     */
    public static final String FULL_TIMES_FORMAT_STR = "yyyyMMdd HH:mm:ss";
    /**
     * @Fields DATE_SIX_FORMAT_STR : 日期样式 yyMMdd
     */
    public static final String DATE_SIX_FORMAT_STR = "yyMMdd";
    /**
     * @Fields DATE_FULL_FORMAT_STR : 日期样式 yyyyMMdd
     */
    public static final String DATE_FULL_FORMAT_STR = "yyyyMMdd";
    /**
     * @Fields FULL_NO_FORMAT_STR : 日期样式 yyyyMMddHHmmss
     */
    public static final String FULL_NO_FORMAT_STR = "yyyyMMddHHmmss";
    /**
     * @Fields TIME_FORMAT_STR : 日期样式 HH:mm:ss
     */
    public static final String TIME_FORMAT_STR = "HH:mm:ss";
    /**
     * @Fields TIME_FORMATJ_STR : 日期样式 HHmmss
     */
    public static final String TIME_FORMATJ_STR = "HHmmss";
    /**
     * @Fields TIME_FOUR_FORMATJ_STR : 日期样式 HHmm
     */
    public static final String TIME_FOUR_FORMATJ_STR = "HHmm";
    /**
     * @Fields SHORT_NO_FORMAT_STR : 日期样式 yyMMddHHmm
     */
    public static final String SHORT_NO_FORMAT_STR = "yyMMddHHmm";
    /**
     * @Fields IDEN_IS_NO : 启用标识
     */
    public static final long IDEN_IS_NO = 0;
    /**
     * @Fields IDEN_IS_YES : 是否标识
     */
    public static final long IDEN_IS_YES = 1;
    /**
     * @Fields STATUSCODE_2 : 登录失败，错误原因：校验失败
     */
    public static final String STATUSCODE_2 = "2";
    /**
     * @Fields STATUSCODE_3 : 登录失败，错误原因：密码错误
     */
    public static final String STATUSCODE_3 = "3";
    /**
     * @Fields STATUSCODE_200 : 登录成功，并刷新页面
     */
    public static final String STATUSCODE_200 = "200";
    /**
     * @Fields STATUSCODE_201 : 登录成功，不刷新页面
     */
    public static final String STATUSCODE_201 = "201";
    /**
     * @Fields STATUSCODE_300 : 登录失败，返回登录页面
     */
    public static final String STATUSCODE_300 = "300";
    /**
     * @Fields DWZ_STATUSCODE_200 : DWZ表单返回信息 200
     */
    public static final String DWZ_STATUSCODE_200 = "200";
    /**
     * @Fields DWZ_STATUSCODE_300 : DWZ表单返回信息 300
     */
    public static final String DWZ_STATUSCODE_300 = "300";
    /**
     * @Fields DWZ_STATUSCODE_301 : DWZ表单返回信息 301
     */
    public static final String DWZ_STATUSCODE_301 = "301";
    /**
     * @Fields DWZ_STATUSCODE_302 : DWZ表单返回信息 302
     */
    public static final String DWZ_STATUSCODE_302 = "302";
    /**
     * @Fields DWZ_STATUSCODE_400 : DWZ表单返回信息 400
     */
    public static final String DWZ_STATUSCODE_400 = "400";
    /**
     * @Fields DWZ_STATUSCODE_403 : DWZ表单返回信息 403
     */
    public static final String DWZ_STATUSCODE_403 = "403";
    /**
     * @Fields DWZ_MESSAGE_200 : DWZ表单返回信息 操作成功
     */
    public static final String DWZ_MESSAGE_200 = "操作成功！";
    /**
     * @Fields DWZ_MESSAGE_300 : DWZ表单返回信息 操作失败
     */
    public static final String DWZ_MESSAGE_300 = "操作失败！";
    /**
     * @Fields DWZ_MESSAGE_301 : DWZ表单返回信息 会话超时
     */
    public static final String DWZ_MESSAGE_301 = "会话超时！";
    /**
     * @Fields DWZ_MESSAGE_302 : DWZ表单返回信息 会话过期，请重新登录
     */
    public static final String DWZ_MESSAGE_302 = "会话过期，请重新登录！";
    /**
     * @Fields DWZ_MESSAGE_400 : DWZ表单返回信息 校验异常
     */
    public static final String DWZ_MESSAGE_400 = "校验异常！";
    /**
     * @Fields DWZ_NAVTABID_ : DWZ表单返回信息
     */
    public static final String DWZ_NAVTABID_ = "";
    /**
     * @Fields DWZ_NAVTABID_REL : DWZ表单返回信息 page
     */
    public static final String DWZ_NAVTABID_REL = "page";
    /**
     * @Fields DWZ_CALLBACKTYPE_CLOSE : DWZ表单返回信息 closeCurrent
     */
    public static final String DWZ_CALLBACKTYPE_CLOSE = "closeCurrent";
    /**
     * @Fields LOGON_OUT_ACTION_URL : DWZ表单返回信息 logon
     */
    public static final String LOGON_OUT_ACTION_URL = "/logonOut_loginAction.action";
    /**
     * @Fields MAIN_ACTION_URL : DWZ表单返回信息 main
     */
    public static final String MAIN_ACTION_URL = "/main.action";
    /**
     * @Fields DWZ_CALLBACKTYPE_FORWARD : 退出URL
     */
    public static final String DWZ_CALLBACKTYPE_FORWARD = "forward";
    /**
     * @Fields PRE_PATH : 获取当前操作的路径
     */
    public static final String PRE_PATH = "prePath";
    /**
     * @Fields PRE_PATH_ROOT : 工程上下文根路径
     */
    public static final String PRE_PATH_ROOT = "/";
    /**
     * @Fields PRE_PATH_EQ : 工程路径参数分隔符
     */
    public static final String PRE_PATH_EQ = "=";
    /**
     * @Fields NAVIGATION_NULL : 工程路径参数分隔符
     */
    public static final String NAVIGATION_NULL = "-";
    /**
     * @Fields PRE_MENU_ID : 菜单ID--menuId
     */
    public static final String PRE_MENU_ID = "menuId";
    /**
     * @Fields AUTHORITY_SESSION_ERROR_ : 权限控制提醒信息
     */
    public static final String AUTHORITY_SESSION_ERROR_ = "拒绝访问，直接输入URL或者两个IE窗口使用同一Session";
    /**
     * @Fields AUTHORITY_URL_ERROR_ : 权限控制提醒信息
     */
    public static final String AUTHORITY_URL_ERROR_ = "拒绝访问，该URL没有配置或非法访问！";
    /**
     * @Fields TIME_OUT_MESSAGE : session过期提醒信息
     */
    public static final String TIME_OUT_MESSAGE = "会话过期，请重新登录！";
    /**
     * @Fields NO_LOGIN_MESSAGE : session过期提醒信息
     */
    public static final String NO_LOGIN_MESSAGE = "您还没有登录，请先登录!";
    /**
     * @Fields USER_KICKED : session过期提醒信息
     */
    public static final String USER_KICKED = "管理员将您强制下线!";
    /**
     * @Fields TO_LOGIN_PATH : 会话过期时
     */
    public static final String TO_LOGIN_PATH = "1";
    /**
     * @Fields DICTIONARY_SYS_PARAM : 系统参数
     */
    public static final String DICTIONARY_SYS_PARAM = "50";
    /**
     * @Fields HOLIDAY_YES : 判断节假日标识
     */
    public static final String HOLIDAY_YES = "Y";
    /**
     * @Fields HOLIDAY_NO : 判断节假日标识
     */
    public static final String HOLIDAY_NO = "N";
    /**
     * @Fields ADD_USER_FIRST_PWD : 新增用户初始密码
     */
    public static final String ADD_USER_FIRST_PWD = "000000";
    /**
     * @Fields SYS_USER : 系统管理员
     */
    public static final String SYS_USER = "SYSADMIN";

    /* used by tanzl_wb */
    /**
     * @Fields INTEGER_ZERO : 常量0
     */
    public static final int INTEGER_ZERO = 0;
    /**
     * @Fields INTEGER_ONE : 常量1
     */
    public static final int INTEGER_ONE = 1;
    /**
     * @Fields NEGATIVE_ONE : 常量-1
     */
    public static final int NEGATIVE_ONE = -1;
    /**
     * @Fields STR_NOTFIND : 没有找到字符串 2014/9/4 新增
     */
    public static final int STR_NOTFIND = -1;
    /**
     * @Fields INTEGER_TWO : 设置为启用用户 2014/9/4 从TabAction 迁移
     */
    public static final int INTEGER_TWO = 2;
    /**
     * @Fields ORGREL_LAST_LEVEL : 机构级别——四级机构
     */
    public static final String ORGREL_LAST_LEVEL = "04";
    /**
     * @Fields USER_TYPE : 用户类型
     */
    public static final int USER_TYPE = 1;

    /**
     * @Fields ROLE_TYPE_MENU : 菜单角色类型
     */
    public static final int ROLE_TYPE_MENU = 1;
    /**
     * @Fields ROLE_TYPE_DATA : 数据角色类型
     */
    public static final int ROLE_TYPE_DATA = 2;

    /**
     * @Fields MENU_ALL_ID : 角色管理常量 :子角色类型为所有菜单
     */
    public static final BigDecimal MENU_ALL_ID = new BigDecimal(55);
    /**
     * @Fields DATA_ALL_ID : 角色管理常量 :子角色类型为所有数据
     */
    public static final BigDecimal DATA_ALL_ID = new BigDecimal(50);

    /**
     * @Fields ORGAN_ALL_ID : 角色管理常量：子角色类型为所有机构
     */
    public static final BigDecimal ORGAN_ALL_ID = new BigDecimal(45);

    /**
     * @Fields YES_NO__NO : 布尔值转String工具类-No
     */
    public static final String YES_NO__NO = "N";
    /**
     * @Fields YES_NO__NA : 布尔值转String工具类-N/A
     */
    public static final String YES_NO__NA = "W";
    /**
     * @Fields YES_NO__YES : 布尔值转String工具类-Yes
     */
    public static final String YES_NO__YES = "Y";

    /**
     * @Fields DEVELOP_MODE : 开发模式
     */
    public static final String DEVELOP_MODE = "D";
    /**
     * @Fields PRODUCE_MODE : 生产模式
     */
    public static final String PRODUCE_MODE = "P";
    /**
     * @Fields INTEGRATION_MODE : 系统集成测试模式
     */
    public static final String INTEGRATION_MODE = "I";
    /**
     * @Fields PRE_PRODUCE_MODE : 预生产模式
     */
    public static final String PRE_PRODUCE_MODE = "PP";

    /**
     * @Fields SYSTEM_ID_PD : 产品工厂系统编号
     */
    public static final String SYSTEM_ID_PD = "063";

    /**
     * @Fields SYSTEM_ID_PA : 保单管理系统编号
     */
    public static final String SYSTEM_ID_PA = "064";

    /**
     * @Fields SYSTEM_ID_NB : 新契约系统编号
     */
    public static final String SYSTEM_ID_NB = "065";

    /**
     * @Fields SYSTEM_ID_UW : 核保系统编号
     */
    public static final String SYSTEM_ID_UW = "066";

    /**
     * @Fields SYSTEM_ID_CLM : 理赔系统编号
     */
    public static final String SYSTEM_ID_CLM = "067";

    /**
     * @Fields SYSTEM_ID_CS : 保全系统编号
     */
    public static final String SYSTEM_ID_CS = "068";

    /**
     * @Fields SYSTEM_ID_CSS : 柜面系统编号
     */
    public static final String SYSTEM_ID_CSS = "071";

    /**
     * @Fields SYSTEM_ID_CAP : 收付费系统编号
     */
    public static final String SYSTEM_ID_CAP = "075";

    /**
     * @Fields SYSTEM_ID_MMS : 营销支持系统编号
     */
    public static final String SYSTEM_ID_MMS = "076";
    /**
     * @Fields PARA_NAME_MODE : 系统参数——模式（P为生产模式，D为开发模式，I为集成模式，PP为预生产模式）
     */
    public static final String PARA_NAME_MODE = "mode";
    /**
     * @Fields PARA_NAME_SYSTEM_ID :
     *         系统参数——系统编号（产品工厂:063,保单管理:064,新契约:065,核保:066,
     *         理赔:067,保全:068,工作流:069,规则管理平台:070,
     *         柜面:071,内容平台:072,接入渠道整合平台:073,打印:074,收付费:075,营销支持:076）
     */
    public static final String PARA_NAME_SYSTEM_ID = "systemId";
    /**
     * @Fields PARA_NAME_VERSION : 系统参数——版本号
     */
    public static final String PARA_NAME_VERSION = "version";
    /**
     * @Fields PARA_NAME_UAMS_URL : 系统参数——统一身份认证URL
     */
    public static final String PARA_NAME_UAMS_URL = "uamsURL";
    public static final Map<String,String> sysCodeMap = new HashMap<String,String>();
    static{
    	sysCodeMap.put(SYSTEM_ID_PD, "PRD");
    	sysCodeMap.put(SYSTEM_ID_PA, "PA");
    	sysCodeMap.put(SYSTEM_ID_NB, "NB");
    	sysCodeMap.put(SYSTEM_ID_UW, "UW");
    	sysCodeMap.put(SYSTEM_ID_CLM, "CLM");
    	sysCodeMap.put(SYSTEM_ID_CSS, "CSS");
    	sysCodeMap.put(SYSTEM_ID_CAP, "CAP");
    	sysCodeMap.put(SYSTEM_ID_MMS, "MMS");
    	sysCodeMap.put(SYSTEM_ID_CS, "CS");
    }
    
    
}
