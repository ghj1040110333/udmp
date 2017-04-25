package cn.com.git.udmp.component.batch.core.component.chain;

import cn.com.git.udmp.component.batch.context.JobSessionContext;
import cn.com.git.udmp.component.batch.core.ICloser;

/** 
 * @description 责任链接口
 * @author liuliang  
 * @date 2015年7月7日 下午6:14:22  
*/
public interface IChain extends ICloser{
    
    
    /**
     * @description 执行责任链
     * @param jobSessionContext 
    */
    public void executeChain(JobSessionContext jobSessionContext);
}
