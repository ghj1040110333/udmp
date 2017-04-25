package cn.com.git.udmp.component.batch.common.utils;

import cn.com.git.udmp.component.batch.common.constants.JobRunStatus;
import cn.com.git.udmp.component.batch.common.constants.StatusEnum;

public class StatusUtil {
    public static StatusEnum getStatusEnum(String status) {
        StatusEnum jbEnum = null;
        if("0".equals(status)){
            jbEnum = StatusEnum.SUCCESS;
        }else if("1".equals(status)){
            jbEnum = StatusEnum.FAIL;
        }else if("2".equals(status)){
            jbEnum = StatusEnum.PART_SUCCESS;
        }else if("3".equals(status)){
            jbEnum = StatusEnum.UNSTART;
        }else if("4".equals(status)){
            jbEnum = StatusEnum.RUNNING;
        }else if("5".equals(status)){
            jbEnum = StatusEnum.ABORTED;
        }
        return jbEnum;
    }
    
    public static String getStringStatus(StatusEnum status){
        switch (status) {
        case SUCCESS:
            return JobRunStatus.SUCCESS;
        case ABORTED:
            return JobRunStatus.ABORTED;
        case FAIL:
            return JobRunStatus.FAIL;
        case PART_SUCCESS:
            return JobRunStatus.PART_SUCCESS;
        case RUNNING:
            return JobRunStatus.RUNNING;
        case UNSTART:
            return JobRunStatus.UNSTART;
        default:
            return null;
        }
    }
    
}
