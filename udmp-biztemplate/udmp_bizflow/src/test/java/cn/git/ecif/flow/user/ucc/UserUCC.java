package cn.git.ecif.flow.user.ucc;

import cn.com.git.udmp.bizflow.WorkFlowExecutor;
import cn.com.git.udmp.bizflow.data.DataObject;
import cn.git.ecif.flow.user.vo.CreateUserRequesterVO;
import cn.git.ecif.flow.user.vo.CreateUserResponserVO;

public class UserUCC {
	WorkFlowExecutor workFlowExcutor;
	
	public CreateUserResponserVO create(CreateUserRequesterVO vo) throws Exception{
		DataObject obj = workFlowExcutor.runProcess(vo);
		
		CreateUserResponserVO rvo = new CreateUserResponserVO();
		rvo.setData(obj.getData());
		
		return rvo;
	}
	
	public WorkFlowExecutor getWorkFlowExcutor() {
		return workFlowExcutor;
	}
	public void setWorkFlowExcutor(WorkFlowExecutor workFlowExcutor) {
		this.workFlowExcutor = workFlowExcutor;
	}
	
}
