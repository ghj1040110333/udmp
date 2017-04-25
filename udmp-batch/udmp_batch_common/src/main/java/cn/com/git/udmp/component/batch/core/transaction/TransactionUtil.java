package cn.com.git.udmp.component.batch.core.transaction;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import cn.com.git.udmp.common.utils.SpringContextHolder;

/**
 * @description 全局事务的工具类
 * @author liuliang liuliang_wb@newchina.live
 * @date 2015年1月22日 上午10:22:14
 */
public class TransactionUtil {
    /**
     * @description 获取事务控制器
     * @author liuliang liuliang@newchinalife.com
     * @return 事务控制器
     */
    public static PlatformTransactionManager getTransactionManager() {
        return SpringContextHolder.getTransactionManager();
    }

    /**
     * @description 获取事务的模板
     * @author liuliang liuliang@newchinalife.com
     * @return 事务模板
     */
    public static TransactionTemplate getTransactionTemplate() {
        return SpringContextHolder.getTransactionTemplate();
    }

}
