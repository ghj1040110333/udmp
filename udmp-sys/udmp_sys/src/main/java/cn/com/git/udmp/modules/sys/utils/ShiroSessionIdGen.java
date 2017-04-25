

package cn.com.git.udmp.modules.sys.utils;

import java.io.Serializable;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import cn.com.git.udmp.common.utils.IdGen;

/**
 * Shiro SessionId生成器
 * @description 
 * @author Spring Cao
 * @date 2016年8月26日 上午11:34:34
 */
@Service()
@Lazy(false)
public class ShiroSessionIdGen implements SessionIdGenerator{

    @Override
    public Serializable generateId(Session session) {
        return IdGen.uuid();
    }
}
