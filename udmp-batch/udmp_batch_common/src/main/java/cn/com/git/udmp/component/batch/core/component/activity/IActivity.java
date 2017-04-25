 package cn.com.git.udmp.component.batch.core.component.activity;

/** 
 * @description activity接口
 * @author liuliang liuliang_wb@newchina.live 
 * @date 2015年1月21日 下午3:38:58  
*/
public interface IActivity {
    /**
     * @description 设置当前activity的节点类型
     * @author liuliang liuliang@newchinalife.com 
    */
    void cmpType();
    /**
     * @description activity的执行逻辑
     * @author liuliang liuliang@newchinalife.com 
    */
    void cmpAct();
}
