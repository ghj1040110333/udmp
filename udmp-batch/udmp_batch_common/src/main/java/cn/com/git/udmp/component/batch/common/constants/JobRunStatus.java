package cn.com.git.udmp.component.batch.common.constants;

/**
 * 运行实例状态常量
 * 
 * @description 运行实例状态常量
 * @author bihb bihb_wb@newchinalife.com
 * @date 2015年5月7日 下午3:37:18
 */
public final class JobRunStatus {

    /**
     * @Fields SUCCESS : 执行成功
     */
    public static final String SUCCESS = "0";

    /**
     * @Fields FAIL : 执行失败
     */
    public static final String FAIL = "1";

    /**
     * @Fields PART_SUCCESS : 部分成功
     */
    public static final String PART_SUCCESS = "2";

    /**
     * @Fields UNSTART : 未启动
     */
    public static final String UNSTART = "3";

    /**
     * @Fields RUNNING : 运行中
     */
    public static final String RUNNING = "4";

    /**
     * @Fields ABORTED : 已停止
     */
    public static final String ABORTED = "5";

}
