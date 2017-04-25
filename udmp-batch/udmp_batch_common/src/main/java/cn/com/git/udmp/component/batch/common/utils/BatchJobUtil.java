package cn.com.git.udmp.component.batch.common.utils;

import cn.com.git.udmp.component.batch.common.constants.CommandEnum;
import cn.com.git.udmp.component.batch.common.constants.JobRunStatus;
import cn.com.git.udmp.component.batch.common.constants.StatusEnum;

/**
 * @description
 * @author liuliang liuliang_wb@newchina.live
 * @date 2015年5月8日 下午5:38:09
 */
public class BatchJobUtil {

    /**
     * @description 根据枚举类型返回对应标志值
     * @author liuliang liuliang@newchinalife.com
     * @param statusEnum 枚举状态
     * @return 状态标志值
     */
    public static String getStatusFlagByEnum(StatusEnum statusEnum) {
        switch (statusEnum) {
            case SUCCESS:
                return JobRunStatus.SUCCESS;
            case FAIL:
                return JobRunStatus.FAIL;
            case RUNNING:
                return JobRunStatus.RUNNING;
            case PART_SUCCESS:
                return JobRunStatus.PART_SUCCESS;
            case UNSTART:
                return JobRunStatus.UNSTART;
            case ABORTED:
                return JobRunStatus.ABORTED;
            default:
                break;
        }
        return null;
    }

    /**
     * @description 通过字符串属性获取对应的状态枚举类值
     * @author liuliang liuliang@newchinalife.com
     * @param statusFlag 状态的字符串值
     * @return 状态枚举类值
     */
    public static StatusEnum getStatusEnumByFlag(String statusFlag) {
        if (statusFlag.equals(JobRunStatus.SUCCESS)) {
            return StatusEnum.SUCCESS;
        } else if (statusFlag.equals(JobRunStatus.UNSTART)) {
            return StatusEnum.UNSTART;
        } else if (statusFlag.equals(JobRunStatus.RUNNING)) {
            return StatusEnum.RUNNING;
        } else if (statusFlag.equals(JobRunStatus.PART_SUCCESS)) {
            return StatusEnum.PART_SUCCESS;
        } else if (statusFlag.equals(JobRunStatus.FAIL)) {
            return StatusEnum.FAIL;
        } else if (statusFlag.equals(JobRunStatus.ABORTED)) {
            return StatusEnum.ABORTED;
        }
        return null;
    }

    /**
     * @description 根据指令的字符串名称获取指令的枚举类
     * @author liuliang liuliang@newchinalife.com
     * @param name 指令的字符串名称
     * @return 指令的枚举类
     */
    public static CommandEnum getCommandEnumByName(String name) {
        for (CommandEnum command : CommandEnum.values()) {
            if (name.equals(command.name())) {
                return command;
            }
        }
        return null;
    }
}
