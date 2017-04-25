package cn.com.git.udmp.common.utils.bizdate;
//package cn.com.git.udmp.common.utils.bizdate;
//
//import java.text.DateFormat;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.TimeZone;
//
//import org.apache.commons.lang3.math.NumberUtils;
//import org.slf4j.Logger;
//
//import cn.com.git.udmp.common.exception.FrameworkRuntimeException;
//import cn.com.git.udmp.common.logging.LoggerFactory;
//import cn.com.git.udmp.common.persistence.entity.UserEntity;
//import cn.com.git.udmp.common.utils.DateUtils;
//import cn.com.git.udmp.common.utils.StringUtils;
//import cn.com.git.udmp.common.utils.UdmpContext;
//import cn.com.git.udmp.common.utils.bizdate.support.IUserDayHandler;
//import cn.com.git.udmp.common.utils.bizdate.support.UserDayHandlerFactory;
//
///**
// * @description 工作日获取工具类
// * @author liuliang liuliang_wb@newchina.live
// * @date 2014年9月11日 下午5:53:59
// */
//public class DateContext {
//    private static Logger logger = LoggerFactory.getLogger();
//    /**
//     * @Fields handlerTag : 设置自定义时间的操作模式（确定日期or偏移量）
//     */
//    private static String handlerTag = UserDayHandlerFactory.USER_DAY_SET;
//    private static IUserDayHandler handler = UserDayHandlerFactory.getUserDayHandler(handlerTag);
//
//    public static void setHandler(String tag) {
//        handlerTag = tag;
//        handler = UserDayHandlerFactory.getUserDayHandler(handlerTag);
//    }
//
//    /**
//     * @description 一键取消自定义的用户营业时间
//     */
//    public static void oneVoteCancel() {
//        handler.oneVote(false);
//    }
//
//    /**
//     * @description 一键激活自定义的用户营业时间
//     */
//    public static void unOneVoteCancel() {
//        handler.oneVote(true);
//    }
//
//    /**
//     * @description Get current user local time
//     * @version
//     * @title
//     * @author ebao
//     * @return Date current user local time
//     */
//    public static Date getCurrentUserLocalTime() {
//
//        UserEntity user = null;
//        try {
//            user = UdmpContext.getUser();
//        } catch (RuntimeException ex) {
////            if (ThreadBindResourceManager.hasBindedResource(WebConstant.NO_APPUSER_TRUE)) {
////                return new Date();
////            }
//            throw new FrameworkRuntimeException(ex.getMessage());
//        }
//        // return getLocalTime(user);
//
//        return getLocalTimeByUser(user);
//    }
//
//    /**
//     * @deprecated
//     * @description this method is just created for _showTime() js function
//     * @version
//     * @title
//     * @author ebao
//     * @modifed by liang 方法名从organRelatedTime 改为relatedTime</br>
//     * @return user organ related time ,format is yyyy/month/date/hour/minutes/seconds
//     */
////    public static String getCurrentUserRelatedTime() {
////        Date userOrganRelatedTime = getCurrentUserLocalTime();
////        String returnDate = "";
////        if (userOrganRelatedTime != null) {
////            String TIME_DEFAULT_FORMAT = "yyyy/MM/dd/HH/mm/ss";
////            Calendar calendar = Calendar.getInstance();
////            calendar.setTime(userOrganRelatedTime);
////            try {
////                returnDate = DateFormat.format(userOrganRelatedTime, TIME_DEFAULT_FORMAT);
////            } catch (Exception e) {
////                throw new RuntimeException(e);
////            }
////        }
////        return returnDate;
////    }
//    
//    
//    /**
//     * @deprecated
//     * @description
//     * @version
//     * @title
//     * @author liuliang liuliang_wb@newchinalife.com
//     * @return 
//    */
////    public static String getOrganRelatedTimeIfHaveCurrentUser() {
////        Date userOrganRelatedTime = null;
////        try {
////            userOrganRelatedTime = getCurrentUserLocalTime();
////        } catch (Exception e) {
////            // do nothing
////
////            userOrganRelatedTime = new Date();
////        }
////        String returnDate = "";
////        if (userOrganRelatedTime != null) {
////            String TIME_DEFAULT_FORMAT = "yyyy/MM/dd/HH/mm/ss";
////            Calendar calendar = Calendar.getInstance();
////            calendar.setTime(userOrganRelatedTime);
////            try {
////                returnDate = DateFormat.format(userOrganRelatedTime, TIME_DEFAULT_FORMAT);
////            } catch (Exception e) {
////                throw new RuntimeException(e);
////            }
////        }
////        return returnDate;
////    }
//
//    /**
//     * @deprecated
//     * @description this method is just created for _showTime() js function
//     * @version
//     * @title
//     * @author liuliang liuliang_wb@newchinalife.com
//     * @return biz process date ,format is yyyy/month/date/hour/minutes/seconds
//     */
////    public static String getBizProcessDateStr() {
////        Date busiProcDate = BizDateUtil.getBizDate();
////        String returnDate = "";
////        if (busiProcDate != null) {
////            String TIME_DEFAULT_FORMAT = "yyyy/MM/dd/HH/mm/ss";
////            Calendar calendar = Calendar.getInstance();
////            calendar.setTime(busiProcDate);
////            try {
////                returnDate = DateFormat.format(busiProcDate, TIME_DEFAULT_FORMAT);
////            } catch (Exception e) {
////                throw new RuntimeException(e);
////            }
////        }
////        return returnDate;
////    }
//
//    /**
//     * @deprecated
//     * @description Get the current local time of the organ.
//     * @version
//     * @title
//     * @author EBAO
//     * @param organId 机构ID
//     * @return 获取机构的当前时间
//     */
////    public static Date getLocalTime(String organId) {
////        Date appDate = null;
////        try {
////            appDate = getLocalTimeNoBusiDate(organId);
////        } catch (Exception ex) {
////            throw new RuntimeException(ex);
////        }
////
////        // get business processing date.
////        try {
////            Date busiProcDate = BizDateUtil.getBizDate();
////            if (busiProcDate != null && busiProcDate.compareTo(appDate) <= 0) {
////                return busiProcDate;
////            } else {
////                return appDate;
////            }
////        } catch (Exception ex) {
////            throw new RuntimeException(ex);
////        }
////    }
//
//    /**
//     * @description 根据用户获得当前操作时间
//     * @version
//     * @title
//     * @author ebao
//     * @modifed by liang
//     * @param userId 用户ID
//     * @return 用户的当前操作时间
//     */
//    public static Date getLocalTimeByUser(Long userId) {
//        Date appDate = null;
//        try {
//            appDate = getLocalTimeNoBusiDateByUserId(userId);
//        } catch (Exception ex) {
//            throw new RuntimeException(ex);
//        }
//
//        return appDate;
//    }
//
//    /**
//     * @deprecated
//     * @description
//     * @param orgId String
//     * @param deptId String
//     * @return Date
//     */
////    public static Date getLocalTime(String orgId, String deptId) {
////        Date appDate = null;
////        try {
////            appDate = getLocalTimeNoBusiDate(orgId, deptId);
////        } catch (Exception ex) {
////            throw new RuntimeException(ex);
////        }
////
////        // get business processing date.
////        try {
////            Date busiProcDate = BizDateUtil.getBizDate();
////            if (busiProcDate != null && busiProcDate.compareTo(appDate) <= 0) {
////                return busiProcDate;
////            } else {
////                return appDate;
////            }
////        } catch (Exception ex) {
////            throw new RuntimeException(ex);
////        }
////    }
//
//    /**
//     * @deprecated
//     * @param appUser UserEntity
//     * @return Date
//     */
////    private static Date getLocalTimek(UserEntity appUser) {
////        Date appDate = null;
////        try {
////            appDate = getLocalTimeNoBusiDate(appUser);
////        } catch (Exception ex) {
////            throw new RuntimeException(ex);
////        }
////
////        // get business processing date.
////        try {
////            Date busiProcDate = BizDateUtil.getBizDate();
////            if (busiProcDate != null && busiProcDate.compareTo(appDate) <= 0) {
////                return busiProcDate;
////            } else {
////                return appDate;
////            }
////        } catch (Exception ex) {
////            throw new RuntimeException(ex);
////        }
////    }
//
//    /**
//     * @modified by liang 更新原方法为根据用户获取偏移量
//     * @param appUser UserEntity
//     * @return Date
//     */
//    private static Date getLocalTimeByUser(UserEntity appUser) {
//        Date appDate = null;
//        try {
//            appDate = getLocalTimeNoBusiDateByUser(appUser);
//        } catch (Exception ex) {
//            throw new FrameworkRuntimeException(ex.getMessage());
//        }
//        return appDate;
//    }
//
//    /**
//     * @deprecated
//     * @param organId 机构id
//     * @return Date
//     */
////    public static Date getLocalTimeNoBusiDate(String organId) {
////        Date currTime = getCurrTimeByTimeZone(organId);
////        try {
////            String addDays = Para.getParaValue(ParaId.PUB_ORGAN_TIME_OFFSET, organId);
////            return DateUtilsEx.addDay(currTime, NumberUtils.toInt(addDays));
////        } catch (Exception ex) {
////            throw new FrameworkRuntimeException(ex.getMessage());
////        }
////    }
//
//    /**
//     * @deprecated
//     * @param appUser UserEntity
//     * @return Date
//     */
////    private static Date getLocalTimeNoBusiDate(UserEntity appUser) {
////        if (appUser == null) {
////            return new Date();
////        }
////        return getLocalTimeNoBusiDate(appUser.getOrganId(), appUser.getDeptId());
////    }
//
//    /**
//     * @modified by liang 按用户获取
//     * @param appUser UserEntity
//     * @return Date
//     */
//    private static Date getLocalTimeNoBusiDateByUser(UserEntity appUser) {
//        if (appUser == null) {
//            return new Date();
//        }
//        return getLocalTimeNoBusiDateByUserId(appUser.getId());
//    }
//
//    /**
//     * 
//     * @param orgId 机构id
//     * @param deptId 部门id
//     * @return Date
//     */
//    private static Date getLocalTimeNoBusiDate(String orgId, String deptId) {
//        if (StringUtils.isBlank(orgId) && StringUtils.isBlank(deptId)) {
//            return new Date();
//        }
//        Date currTime = getCurrTimeByTimeZone(orgId);
//        String addDays = null;
//        // 获取机构的时间偏移量
//        addDays = Para.getParaValueTryRelas(ParaId.PUB_ORGAN_TIME_OFFSET, orgId);
//
//        // 获取用户的时间偏移量
//        // addDays = UserParams.getInstance().getAddDayById(orgId);
//
//        if (StringUtils.isBlank(addDays)) {
//            return currTime;
//        }
//        return DateUtilsEx.addDay(currTime, NumberUtils.toInt(addDays));
//    }
//
//    /**
//     * 根据用户id获取操作时间
//     * 
//     * @param userId Long
//     * @return Date
//     */
//    private static Date getLocalTimeNoBusiDateByUserId(String userId) {
//        return handler.getUserDayById(userId);
//
//    }
//
////    public static Date getCurrentUserLocalTimeNoBusiDate() {
////        return getLocalTimeNoBusiDate(UdmpContext.getCurrentUser());
////    }
//
//    public static String getLocalTimeIfHaveCurrentUser() {
//        java.util.Date currUserLocalTime;
//        String strCurrUserLocalTime = null;
//        if (UdmpContext.isUserInited()) {
//            currUserLocalTime = DateContext.getCurrentUserLocalTime();
//        } else {
//            currUserLocalTime = new java.util.Date();
//        }
//        try {
//            strCurrUserLocalTime = DateUtils.formatDateTime(currUserLocalTime);
//        } catch (Exception e) {
//            throw new FrameworkRuntimeException(e.getMessage());
//        }
//        return strCurrUserLocalTime;
//    }
//
//    /**
//     * get the user local time by the given ogran. if the organ does not difine the para of time zone ignore the time
//     * zone and just return the new Date(),otherwise return the time by time zone.
//     * 
//     * @param organId String
//     * @return Date
//     */
//    public static Date getCurrTimeByTimeZone(String organId) {
//        Date result;
//        // validate the time zone
//
//        TimeZone tz = getTimeZone(organId);
//        if (null == tz) {
//            return new Date();
//        }
//        Calendar cal = Calendar.getInstance(tz);
//        result = new Date();
//        result.setYear(cal.get(Calendar.YEAR) - 1900);
//        result.setMonth(cal.get(Calendar.MONTH));
//        result.setDate(cal.get(Calendar.DAY_OF_MONTH));
//        result.setHours(cal.get(Calendar.HOUR_OF_DAY));
//        result.setMinutes(cal.get(Calendar.MINUTE));
//        result.setSeconds(cal.get(Calendar.SECOND));
//
//        return result;
//    }
//
//    public static TimeZone getTimeZone(String organId) {
//        //TODO 根据机构获取时区
//        return null;
////        return LocalFormatUtils.getTimeZone(organId);
//    }
//
//    public static String getCurrentTimeNoUser() {
//        String returnDate = "";
//        String TIME_DEFAULT_FORMAT = "yyyy/MM/dd/HH/mm/ss";
//        Calendar calendar = Calendar.getInstance();
//        try {
//            returnDate = DateUtils.formatDate(calendar.getTime(), TIME_DEFAULT_FORMAT);
//        } catch (Exception e) {
//            throw new FrameworkRuntimeException(e.getMessage());
//        }
//        return returnDate;
//    }
//
//}
