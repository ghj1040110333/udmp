package cn.git.ecif.flow.user.bs;

import cn.git.ecif.flow.user.vo.CheckUserRequesterVO;
import cn.git.ecif.flow.user.vo.CheckUserResponseVO;
import cn.git.ecif.flow.user.vo.CreateUserRequesterVO;
import cn.git.ecif.flow.user.vo.CreateUserResponserVO;



public class UserBS{
	
	public CreateUserResponserVO createUserVO(CreateUserRequesterVO vo){
		System.out.println("name:"+vo.getName()+";address:"+vo.getAddress());
		CreateUserResponserVO rvo = new CreateUserResponserVO();
		rvo.setEcifId("bbbb");
		return rvo;
	}
	
	public CreateUserResponserVO create(CheckUserResponseVO check){
		check.getEcifId();
		CreateUserResponserVO rvo = new CreateUserResponserVO();
		rvo.setEcifId("111111"+check.getEcifId());
		return rvo;
	}
	
	public CheckUserResponseVO check(CheckUserRequesterVO vo){
		CheckUserResponseVO rvo = new CheckUserResponseVO();
		if("xaomin".equals(vo.getName())){
			rvo.setExist(true);
			rvo.setEcifId("aaaaa");
		}else{
			rvo.setExist(false);
		}
		return rvo;
	}
}
