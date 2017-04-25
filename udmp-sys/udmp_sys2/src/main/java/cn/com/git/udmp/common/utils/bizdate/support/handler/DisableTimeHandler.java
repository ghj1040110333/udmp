

package cn.com.git.udmp.common.utils.bizdate.support.handler;

import java.util.Date;

import cn.com.git.udmp.common.utils.bizdate.support.IBizTimeHandler;
import cn.com.git.udmp.modules.sys.entity.BizTime;

/**
 * @description 不使用业务时间
 * @author liang
 *
 */
public class DisableTimeHandler implements IBizTimeHandler{

    /* （非 Javadoc）
     * @see cn.com.git.udmp.common.utils.bizdate.support.IBizTimeHandler#handler(cn.com.git.udmp.modules.sys.entity.BizTime)
     */
    @Override
    public Date handler(BizTime bizTime) {
        return new Date();
    }

}
