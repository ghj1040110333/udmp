

package cn.com.git.udmp.common.utils.bizdate.support.handler;

import java.util.Date;

import cn.com.git.udmp.common.utils.DateUtils;
import cn.com.git.udmp.common.utils.bizdate.support.IBizTimeHandler;
import cn.com.git.udmp.modules.sys.entity.BizTime;

/**
 * @description 加时间
 * @author liang
 *
 */
public class PlusTimeHandler implements IBizTimeHandler {

    /* （非 Javadoc）
     * @see cn.com.git.udmp.common.utils.bizdate.support.IBizTimeHandler#handler(cn.com.git.udmp.modules.sys.entity.BizTime)
     */
    @Override
    public Date handler(BizTime bizTime) {
        String customTime = bizTime.getCustomTime();
        return DateUtils.addDay(new Date(), Integer.valueOf(customTime));
    }

}
