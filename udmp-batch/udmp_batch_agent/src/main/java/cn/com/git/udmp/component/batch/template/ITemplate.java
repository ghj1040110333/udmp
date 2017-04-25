package cn.com.git.udmp.component.batch.template;

import cn.com.git.udmp.component.batch.context.SessionContext;

/**
 * @description 模板接口
 * @author liuliang liuliang_wb@newchina.live
 * @date 2015年1月21日 下午2:50:32
 */
public interface ITemplate {
    /**
     * @description 模板初始化
     * @author liuliang liuliang@newchinalife.com
     * @return 成功与否
     */
    boolean tplInit();

    /**
     * @description 模板销毁
     * @author liuliang liuliang@newchinalife.com
     * @return 成功与否
     */
    boolean tplDestroy();

    /**
     * @description 执行模板
     * @author liuliang liuliang@newchinalife.com
     * @param context 上下文
     * @return 上下文
     */
    SessionContext execute(SessionContext context);
}
