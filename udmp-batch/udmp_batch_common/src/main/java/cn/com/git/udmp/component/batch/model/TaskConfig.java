package cn.com.git.udmp.component.batch.model;

/** 
 * @description 作业配置类 
 * @author liuliang liuliang_wb@newchina.live 
 * @date 2015年1月29日 下午4:04:36  
*/
public class TaskConfig {
    private String taskId;
    private String taskName;
    private String taskClazz;
    private String isSpringBean;
    private String taskSystem;
    private String isDelete;
    
    public String getTaskId() {
        return taskId;
    }
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
    public String getTaskName() {
        return taskName;
    }
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
    public String getTaskClazz() {
        return taskClazz;
    }
    public void setTaskClazz(String taskClazz) {
        this.taskClazz = taskClazz;
    }
    public String getIsSpringBean() {
        return isSpringBean;
    }
    public void setIsSpringBean(String isSpringBean) {
        this.isSpringBean = isSpringBean;
    }
    public String getTaskSystem() {
        return taskSystem;
    }
    public void setTaskSystem(String taskSystem) {
        this.taskSystem = taskSystem;
    }
    public String getIsDelete() {
        return isDelete;
    }
    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }
    
}
