

package cn.com.git.udmp.common.utils.bizdate.support;

import java.util.Date;

import cn.com.git.udmp.core.base.IService;
import cn.com.git.udmp.modules.sys.entity.BizTime;

/**
 * @author liang
 *
 */
public interface IBizTimeHandler extends IService{
    public Date handler(BizTime bizTime);
}
