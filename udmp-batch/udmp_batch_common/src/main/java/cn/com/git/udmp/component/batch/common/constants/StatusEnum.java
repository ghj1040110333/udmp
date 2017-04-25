package cn.com.git.udmp.component.batch.common.constants;

/**
 * @description 状态枚举类（与状态值的常量类JobRunStatus.java一致）
 * @author liuliang liuliang_wb@newchina.live
 * @date 2015年5月8日 下午5:41:54
 */
public enum StatusEnum {
    // STARTED,WAITING,STOPPED,PAUSE,FINISHED,RUNNING,EXCEPTION;
    /**
     * @Fields SUCCESS : 成功状态
     */
    SUCCESS,
    /**
     * @Fields FAIL : 失败状态
     */
    FAIL,
    /**
     * @Fields RUNNING : 运行状态
     */
    RUNNING,
    /**
     * @Fields PART_SUCCESS : 部分成功
     */
    PART_SUCCESS,
    /**
     * @Fields UNSTART : 未启动
     */
    UNSTART,
    /**
     * @Fields ABORTED : 终止
     */
    ABORTED;
}
