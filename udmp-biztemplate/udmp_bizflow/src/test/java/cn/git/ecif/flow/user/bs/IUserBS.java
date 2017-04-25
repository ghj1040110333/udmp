package cn.git.ecif.flow.user.bs;

import cn.git.ecif.flow.user.vo.CheckUserRequesterVO;
import cn.git.ecif.flow.user.vo.CheckUserResponseVO;
import cn.git.ecif.flow.user.vo.CreateUserRequesterVO;
import cn.git.ecif.flow.user.vo.CreateUserResponserVO;

public interface IUserBS {
	public CreateUserResponserVO create(CreateUserRequesterVO vo);
	
	public CheckUserResponseVO check(CheckUserRequesterVO vo);
}
