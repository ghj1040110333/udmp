

package cn.com.git.udmp.common.utils.bizdate.support.handler;

import java.text.ParseException;
import java.util.Date;

import cn.com.git.udmp.common.utils.DateUtils;
import cn.com.git.udmp.common.utils.bizdate.support.IBizTimeHandler;
import cn.com.git.udmp.modules.sys.entity.BizTime;

/**
 * @description 业务时间处理器-设置确定的业务时间
 * @author liang
 *
 */
public class SetTimeHandler implements IBizTimeHandler{

    /* （非 Javadoc）
     * @see cn.com.git.udmp.common.utils.bizdate.support.IBizTimeHandler#handler(cn.com.git.udmp.modules.sys.entity.BizTime)
     */
    @Override
    public Date handler(BizTime bizTime) {
        try {
            return DateUtils.parseDate(bizTime.getCustomTime(), bizTime.getTimePattern());
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }
    }

}
