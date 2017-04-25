

package cn.com.git.udmp.core;

import org.junit.Test;

import cn.com.git.udmp.core.logging.ILog;


/**
 * 日志测试
 * @author liang
 *
 */
public class LogTest implements ILog{

    @Test
    public void testLogInit() {
        logger.debug("this is a log init test");
    }

}
