package activiti_maven_project.com.git.ext.behavior.factory;

import org.activiti.bpmn.model.ParallelGateway;
import org.activiti.engine.impl.bpmn.behavior.ParallelGatewayActivityBehavior;
import org.activiti.engine.impl.bpmn.parser.factory.DefaultActivityBehaviorFactory;

import activiti_maven_project.com.git.ext.behavior.ParallelGatewayActivityBehaviorExt2;

public class ParallelGatewayActivityExtFactory extends DefaultActivityBehaviorFactory {

	private ParallelGatewayActivityBehaviorExt2 parallelGatewayActivityBehaviorExt;
	
	/**
	   * 通过Spring容器注入新的分支条件行为执行类
	   * @param exclusiveGatewayActivityBehaviorExt
	   */
	  public void setParallelGatewayActivityBehaviorExt(ParallelGatewayActivityBehaviorExt2 exclusiveGatewayActivityBehaviorExt) {
	    this.parallelGatewayActivityBehaviorExt = exclusiveGatewayActivityBehaviorExt;
	  }
	  
	  //重写父类中的分支条件行为执行类
	  @Override
	  public ParallelGatewayActivityBehavior createParallelGatewayActivityBehavior(ParallelGateway parallelGateway) {
		    return parallelGatewayActivityBehaviorExt;
	  }

}
