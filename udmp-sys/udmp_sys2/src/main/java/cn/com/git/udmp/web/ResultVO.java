package cn.com.git.udmp.web;

import cn.com.git.udmp.common.web.Paginator;
import cn.com.git.udmp.common.web.ParentResultVO;
import cn.com.git.udmp.modules.sys.entity.Office;
import cn.com.git.udmp.modules.sys.entity.User;
import cn.com.git.udmp.modules.sys.utils.UserUtils;

/**
 * WEB应用返回值对象
 * @description 应用于WEB应用的，继承自ParentResultVO，内置多种分页方法 
 * @author Spring Cao
 * @date 2016年8月23日 下午4:12:01
 */
public class ResultVO extends ParentResultVO{
	private Reply reply = new Reply();
	
	/**
	 * 成功返回分页对象
	 * @param page
	 */
	public ResultVO(cn.com.git.udmp.common.persistence.Page<?> page){	
	    Paginator p = new Paginator();
		p.setPageNo(page.getPageNo());
		int count = (int)page.getCount();
		p.setTotal(count == 0?page.getList().size():count);
		p.setRecordsPerPage(page.getPageSize());
		p.setTotalPage(page.getTotalPage());
		p.setBeginIndex((page.getPageNo()-1)*page.getPageSize()+1);
		p.setEndIndex((page.getPageNo()-1)*page.getPageSize()+page.getPageSize());
		reply.setPage(p);
		reply.setBiz(page.getList());
		reply.getReturnCode().setType(ReturnCode.SUCCESS);
	}
	
	/**
	 * 成功返回假分页对象
	 * @param biz 传输的list对象
	 * @param listSize 列表行数
	 */
	public ResultVO(Object biz,int listSize){	
	    Paginator p = new Paginator();
		p.setBeginIndex(0);
		p.setDoPagination(true);
		p.setEndIndex(listSize);
		p.setPageNo(1);
		p.setQueryLastIfPageNoExceed(true);
		p.setQueryTotal(true);
		p.setRecordsPerPage(listSize);
		p.setTotal(listSize);
		p.setTotalPage(1);
		reply.setPage(p);
		reply.setBiz(biz);
		reply.getReturnCode().setType(ReturnCode.SUCCESS);
	}
	
	/**
	 * 成功返回分页对象
	 * @param page
	 */
	public ResultVO(cn.com.git.udmp.common.persistence.Page<?> page,Object biz){	
	    Paginator p = new Paginator();
		p.setPageNo(page.getPageNo());
		p.setTotal((int)page.getCount());
		p.setRecordsPerPage(page.getPageSize());
		reply.setPageItems(page.getList());
		reply.setPage(p);
		reply.setBiz(biz);
		reply.getReturnCode().setType(ReturnCode.SUCCESS);
	}
	
	/**
	 * 成功返回对象
	 * @param page
	 */
	public ResultVO(Object biz){	
		reply.setBiz(biz);
		reply.getReturnCode().setType(ReturnCode.SUCCESS);
	}
	
	public static ResultVO instanteSucVO(Object biz){
		ResultVO vo = new ResultVO();
		vo.reply.setBiz(biz);
		vo.reply.getReturnCode().setType(ReturnCode.SUCCESS);
		return vo;
	}
	
	/**
	 * 返回异常的对象
	 * @param page
	 */
	public ResultVO(String code,String messgage){	
		reply.getReturnCode().setCode(code);
		reply.getReturnCode().setMessage(messgage);
		reply.getReturnCode().setType(ReturnCode.ERROR);
	}
	
	/**
	 * 返回异常的对象
	 * @param page
	 */
	public ResultVO(String messgage){	
		reply.getReturnCode().setMessage(messgage);
		reply.getReturnCode().setType(ReturnCode.SUCCESS);
		User curUser = UserUtils.getUser();
		if(curUser != null){
			reply.setUserName(curUser.getLoginName());
			reply.setUserRealName(curUser.getName());
			reply.setUserId(curUser.getId());
		}
		Office office = UserUtils.getUser().getOffice();
		//设置   机构数据
		if(office != null){
			reply.setUserOrgId(office.getId());
			reply.setUserOrgName(office.getName());
		}
	}
	
	/**
	 * 返回对象
	 * @param page
	 */
	public ResultVO(Object biz,String messgage){	
		reply.setBiz(biz);
		reply.getReturnCode().setMessage(messgage);
		reply.getReturnCode().setType(ReturnCode.SUCCESS);
		reply.setUserId(UserUtils.getUser().getId());
		Office office = UserUtils.getUser().getOffice();
		if(office != null){
			reply.setUserOrgId(office.getId());
		}
	}
	
	public ResultVO(){
		
	}
	

	public Reply getReply() {
		return reply;
	}

	public void setReply(Reply reply) {
		this.reply = reply;
	}
	
	
}
