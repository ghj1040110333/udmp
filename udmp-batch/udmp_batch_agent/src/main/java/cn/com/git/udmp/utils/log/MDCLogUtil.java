
package cn.com.git.udmp.utils.log;

import org.slf4j.MDC;

import cn.com.git.udmp.component.batch.context.JobSessionContext;

/**
 * @description 操作logback的mdc类
 * @author Liang liuliang1@git.com.cn
 * @date 2016年12月8日 上午10:26:30
 */
public class MDCLogUtil {

    
    public static void setJobContext(JobSessionContext context){
        setJobId(context.getJobId());
        setJobRunId(context.getJobRunId());
    }
    
    
    public static void setJobRunId(String value) {
        setMDC("jobRunId", "jobRunId:"+value);
    }
    
    public static void setJobId(String value) {
        setMDC("jobId", "jobId:"+value);
    }
    

    public static void setMDC(String name, String value) {
        MDC.put(name, value);
    }
}
