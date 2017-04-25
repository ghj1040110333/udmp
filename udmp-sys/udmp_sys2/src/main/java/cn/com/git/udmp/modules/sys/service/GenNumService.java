package cn.com.git.udmp.modules.sys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.git.udmp.common.enums.GenNumberEnum;
import cn.com.git.udmp.common.utils.DateUtils;
import cn.com.git.udmp.common.utils.LuhnUtils;
import cn.com.git.udmp.modules.sys.dao.GenNumDao;
import cn.com.git.udmp.modules.sys.dao.SysTaskDateDao;

/**
 * 生成序列号的服务
 * @author guosg
 *
 */
//@Service
public class GenNumService{
	@Autowired
	private GenNumDao genNumDao;
	@Autowired
	private SysTaskDateDao sysTaskDateDao;
	/**
	 * 
	 * @param genNumber
	 * @return
	 */
	public String genNumber(GenNumberEnum genNumber){
		StringBuffer sb = new StringBuffer();
		String date = DateUtils.formatDate(sysTaskDateDao.getSysDate(), "yyyyMMdd");
		sb.append(genNumber.getIndex());
		sb.append(date);
		sb.append(formatSeq(genNumDao.seq(genNumber),8));
		sb.append(LuhnUtils.getCheckNumber(sb.toString()));
		return sb.toString();
	}
	
	private String formatSeq(Integer seq,int size){
		String ss = seq.toString();
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<size-ss.length();i++){
			sb.append("0");
		}
		sb.append(ss);
		return sb.toString();
	}
	
	
	
}
