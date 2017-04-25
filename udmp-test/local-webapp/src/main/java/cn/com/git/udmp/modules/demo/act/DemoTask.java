package cn.com.git.udmp.modules.demo.act;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

public class DemoTask implements JavaDelegate {

    @Override
    public void execute(DelegateExecution arg0) throws Exception {
        // TODO Auto-generated method stub
        System.out.println("启动demo流程完毕");
    }

}
