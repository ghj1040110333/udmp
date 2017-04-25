package cn.com.git.udmp.common.utils.bizdate;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;

import cn.com.git.udmp.common.exception.FrameworkException;
import cn.com.git.udmp.common.utils.DateUtils;
import cn.com.git.udmp.core.logging.LoggerFactory;

/**
 * 业务进度日期时间工具类 This class is business process date util class
 * 
 * @author Linqer.Li
 */
public class BizDateUtil {
    private static long lastLoadTime = System.currentTimeMillis();
    private static long needReloadSec = 60;
    private static Logger logger = LoggerFactory.getLogger();
    private static Date obizDate = null;
    private static boolean needReload = false;
    static {
        //TODO 非必要-从配置中获取业务时间重新获取的时间间隔
//        String sNeedReloadSec = Env.getValue(Cst.BIZ_DATE_RELOAD_SEC);
//        if (StringUtils.isNotBlank(sNeedReloadSec)) {
//            needReloadSec = Long.parseLong(sNeedReloadSec);
//        }
        loadBizDateFromDB();
    }

    /**
     * 获取业务进度时间
     * 
     * @return business process date
     */
    public static synchronized Date getBizDate() {
        if (isNeedReload()) {
            loadBizDateFromDB();
            if (logger.isDebugEnabled()) {
                logger.debug("Reload business process date from db ");
            }
        }

        if (obizDate != null) {
            Date oldsBizDate = obizDate;
            Date sysDate = null;
            //TODO 非必要-获取特定用户的当前时间（不含业务时间）
//            if (AppUserContext.isUserInited()) {
////                sysDate = DateContext.getCurrentUserLocalTimeNoBusiDate();
//            } else {
//                sysDate = new Date();
//            }
            sysDate = new Date();
            Calendar sBizCal = Calendar.getInstance();
            sBizCal.setTime(oldsBizDate);
            sBizCal.set(Calendar.HOUR_OF_DAY, 0);
            sBizCal.set(Calendar.MINUTE, 0);
            sBizCal.set(Calendar.SECOND, 0);
            Calendar sysCal = Calendar.getInstance();
            sysCal.setTime(sysDate);
            if (logger.isDebugEnabled()) {
                logger.debug("sBizCal date is : " + sBizCal.getTime());
                logger.debug("sysCal  date is : " + sysCal.getTime());
            }

            int sysHour = sysCal.get(Calendar.HOUR_OF_DAY);
            if (sysHour == 23) {
                obizDate = getsBizDate(sBizCal, sysCal);
            } else {
                Calendar sBizTomDate = Calendar.getInstance();
                sBizTomDate.setTime(sBizCal.getTime());
                sBizTomDate.add(Calendar.DAY_OF_MONTH, 1);
                if (!sysCal.before(sBizTomDate)) {
                    obizDate = getsBizDate(sBizCal, sysCal);
                } else {
                    sBizCal.set(Calendar.HOUR_OF_DAY, 23);
                    obizDate = sBizCal.getTime();
                }
            }
        }
        return obizDate;
    }

    /**
     * 是否需要重新加载
     * 
     * @return
     */
    private static boolean isNeedReload() {
        if (needReload) {
            return true;
        } else {
            return (System.currentTimeMillis() - lastLoadTime) > (needReloadSec * 1000);
        }
    }

    /**
     * 从数据库加载业务时间
     */
    private static void loadBizDateFromDB() {
        String sBizDate = "";
        try {
            //TODO 从数据库获取业务时间
//            sBizDate = Para.getParaValueAlwaysFromDB(ParaId.PUB_BUSI_PROC_DATE);
            sBizDate = "2012/12/31";
        } catch (RuntimeException ex) {
            FrameworkException oCause = null;
            Object objexception = ex.getCause();
            try {
                oCause = (FrameworkException) objexception;
            } catch (ClassCastException ccex) {
                throw ex;
            }
//            if (oCause != null && (oCause.getErrCode() == Para.NoValue || oCause.getErrCode() == Para.NoDef)) {
//                obizDate = null;
//            } else {
//                throw ex;
//            }
        }
        if (logger.isDebugEnabled()) {
            logger.debug("The load biz date is : " + sBizDate);
        }

        if (StringUtils.isNotBlank(sBizDate)) {
            try {
                obizDate =  DateUtils.parseDate(sBizDate);
//                obizDate = DateFormat.toDate(sBizDate);
            } catch (Exception e) {
                if (logger.isDebugEnabled()) {
                    logger.debug("business process date format error");
                }
                throw new RuntimeException(e);
            }
        }
        needReload = false;
        lastLoadTime = System.currentTimeMillis();
    }

    /**
     * 获取业务日期时间
     * 
     * @param sBizCal
     * @param sysCal
     * @return Date
     */
    private static Date getsBizDate(Calendar sBizCal, Calendar sysCal) {
        sBizCal.set(Calendar.HOUR_OF_DAY, 23);
        long offsetMillis = sysCal.getTimeInMillis() - sBizCal.getTimeInMillis();

        if (offsetMillis >= 0) {
            if (offsetMillis > 215940000) {
                sBizCal.set(Calendar.MINUTE, 59);
                sBizCal.set(Calendar.SECOND, 59);
                sBizCal.set(Calendar.MILLISECOND, 999);
            } else {
                long offsetMin = offsetMillis / (60 * 60 * 1000);
                long offsetSec = offsetMillis % (60 * 60 * 1000) / (60 * 1000);
                long offsetHMillis = offsetMillis % (60 * 1000) / (60 * 100);
                sBizCal.set(Calendar.MINUTE, (int) offsetMin);
                sBizCal.set(Calendar.SECOND, (int) offsetSec);
                sBizCal.set(Calendar.MILLISECOND, 100 * ((int) offsetHMillis));
            }
            Date returnDate = sBizCal.getTime();

            if (logger.isDebugEnabled()) {
                logger.debug("Business process date is : " + returnDate);
            }
            return returnDate;
        }

        return null;
    }

    /**
     * 设置是否需要重新加载
     * 
     * @param needReload boolean
     */
    public static void setNeedReload(boolean needReload) {
        BizDateUtil.needReload = needReload;
    }
    
    
    public static void main(String[] args) {
        System.out.println(getBizDate());
    }

}
