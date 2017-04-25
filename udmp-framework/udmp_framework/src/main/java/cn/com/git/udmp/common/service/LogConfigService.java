

package cn.com.git.udmp.common.service;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.qos.logback.classic.Level;

/**
 * 日志Service
 * @author Spring Cao
 * @version 2014-05-16
 */
@Service
@Transactional(readOnly = true)
public class LogConfigService{

    /**
     * 设置debug模式
     * 
     * @param loggerName
     *            logger名称
     */
    public void setDebug(String loggerName) {
        changeLogLvl(Level.DEBUG, loggerName);
    }

    /**
     * 设置error模式
     * 
     * @param loggerName
     *            logger名称
     */
    public void setError(String loggerName) {
        changeLogLvl(Level.ERROR, loggerName);
    }

    /**
     * 设置info模式
     * 
     * @param loggerName
     *            logger名称
     */
    public void setInfo(String loggerName) {
        changeLogLvl(Level.INFO, loggerName);
    }

    /**
     * 设置全局debug模式（只能作用于未配置logger的范围）
     */
    public void setRootDebug() {
        changeLogLvl(Level.DEBUG, org.slf4j.Logger.ROOT_LOGGER_NAME);
    }

    /**
     * 设置全局error模式（只能作用于未配置logger的范围）
     */
    public void setRootError() {
        changeLogLvl(Level.ERROR, org.slf4j.Logger.ROOT_LOGGER_NAME);
    }

    /**
     * 设置全局warn模式（只能作用于未配置logger的范围）
     */
    public void setRootWarn() {
        changeLogLvl(Level.WARN, org.slf4j.Logger.ROOT_LOGGER_NAME);
    }

    /**
     * 设置全局info模式（只能作用于未配置logger的范围）
     */
    public void setRootInfo() {
        changeLogLvl(Level.INFO, org.slf4j.Logger.ROOT_LOGGER_NAME);
    }

    /**
     * 设置warn模式
     * 
     * @param loggerName
     *            logger名称
     */
    public void setWarn(String loggerName) {
        changeLogLvl(Level.WARN, loggerName);
    }

    /**
     * 修改日志级别
     * 
     * @param level
     *            日志级别
     * @param loggerName
     *            日志logger名称
     */
    private void changeLogLvl(Level level, String loggerName) {
        ch.qos.logback.classic.Logger logger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(loggerName);
        logger.setLevel(level);
    }

}
