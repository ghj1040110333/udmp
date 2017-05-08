package activiti_maven_project.com.git.bean;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.delegate.BpmnError;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.DelegateTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("actTestBean")
public class ActTestBean {
	private static Logger log = LoggerFactory.getLogger(ActTestBean.class);
	public String print(String str){
		log.debug("ActTestBean.print:["+str+"]");
		return "test:"+str;
	}
	
	public String  printBKey(DelegateExecution exc){
		String key=exc.getProcessBusinessKey();
		log.debug("ActTestBean.printBKey:["+key+"]");
		return key;
	}
	
	
	public void invokeTask(DelegateTask task){
		task.setAssignee((String)task.getVariable("name"));
		task.setVariable("setByTask", task.getVariable("name"));
	}
	
	public void sleepTask(String time,DelegateExecution execution){
		try {
			log.debug("sleep:["+time+"]");
			Thread.sleep(new Long(time).longValue());
			log.debug("sleep:["+time+"],ActTestBean.sleepTask:["+execution.getCurrentActivityName()+"]");
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public List getUserList(DelegateExecution execution){
			String ls=(String)execution.getVariable("assigneeList");
			String[] ll=ls.split(";");
			List l=new ArrayList();
			for(String a:ll){
				l.add(a);
			}
			return l;
	}
	
	
	public String testRule(DelegateExecution execution){
		return "1";
	}
	
	public String testRule2(DelegateExecution execution){
		execution.setVariable("lv", "0");
		return "1";
	}
	
	public void exceptionTask(DelegateExecution execution){
		try {
			String str=null;
			str.split(";");

		} catch (Exception e) {
			if(null==e.getMessage()){
				execution.setVariable("errMessage", e);
			}else{
				execution.setVariable("errMessage", e.getMessage());
			}
			e.printStackTrace();
			throw new BpmnError("BPM-ERROR-001");
			
		} 
	}
	
	
	public void doError(String str){
		log.debug("**************************************************************************");
		String str1=null;
		str1.split(";");
	}
}
