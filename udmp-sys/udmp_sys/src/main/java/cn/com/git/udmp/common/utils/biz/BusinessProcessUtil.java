
package cn.com.git.udmp.common.utils.biz;


import java.text.SimpleDateFormat;
import java.util.Date;

import cn.com.git.udmp.common.utils.KeyGenUtils;
import cn.com.git.udmp.common.utils.SpringContextHolder;
import cn.com.git.udmp.common.utils.StringUtils;
import cn.com.git.udmp.core.logging.LoggerContextUtil;

/**
 * 业务处理工具类，需要在application.propeties中进行设置
 * <li>business.sys_no 系统的编号 	3位
 * <li>business.sub_sys_no 子系统的编号 2位
 * <li>business.area_id 区域标识	1位
 * <li>business.server_no 服务器实例号	3位
 * 
 * @author guosg
 *
 */
public class BusinessProcessUtil {
	private static final ThreadLocal<String> parentSeq = new ThreadLocal<String>();
	private static final ThreadLocal<String> transNoLocal = new ThreadLocal<String>();
	private static BusinessSeqConfigInfo businessSeqConfigInfo = SpringContextHolder.getBean(BusinessSeqConfigInfo.class);
	/**
	 * 业务处理初始化，当需要起一个线程进行处理是需要进行初始化<p>
	 * <li>初始化业务流水号
	 * <li>初始化日志上下文
	 * @param parentBizSeqNo:父业务编号，业务编号的前23位，如果传入的编号超过23位，将被截取
	 * @param transNo 交易编号
	 */
	public static void init(String parentBizSeqNo,String transNo){
		setCurrentParentBizSeqNo(parentBizSeqNo);
		setCurrentTransNo(transNo);
		
		LoggerContextUtil.setContextInfo(getCurrentParentBizSeqNo(), 
				transNo,
				businessSeqConfigInfo.getSysNo()+"_"+businessSeqConfigInfo.getSubSysNo() , 
				null);
	}
	
	/**
	 * 生成一个业务编号
	 * @return
	 */
	public static String genBizSeq(){
		return getCurrentParentBizSeqNo()+genBizSubSeq();
	}
	
	/**
	 * 设置一个父亲的交易编号，设置的场景例如<p>
	 * <ul>
	 * 	<li>在多线程操作是需要进行多线程操作时，处理逻辑如下
	 * 	<ul>
	 * 		<li>1.父线程中获取BusinessSequenceUtil.getParent()放入一个变量中
	 * 		<li>2.在子线程中进行setParent(父线程中的变量)操作
	 *  </ul>
	 * </ul>
	 * @param seq
	 */
	public static void setCurrentParentBizSeqNo(String seq){
		if(StringUtils.isBlank(seq)){
			seq=genBizParentSeq();
		}else if(seq.length()>23){
			seq = StringUtils.substring(seq, 0, 22);
		}
		parentSeq.set(seq);
	}
	
	/**
	 * 
	 */
	public static String getCurrentParentBizSeqNo(){
		String pbsn = parentSeq.get();
		if(StringUtils.isBlank(pbsn)){
			pbsn=genBizParentSeq();
			parentSeq.set(pbsn);
		}
		return pbsn;
		
	}
	
	/**
	 * 业务编号的前23位是系统的父编号，这个编号在一次的交易请求下是唯一的
	 *<ul>
	 *<li>Digit 1 .. 3: 渠道系统编号（网银渠道固定313）
	 *<li>Digit 4 .. 5: 服务器实例号（由运维统一分配，目前包括直销、网银各应用实例）
	 *<li>Digit 6 .. 13: 渠道发起日期（YYYYMMDD）
	 *<li>Digit 14：区域标识（主机房、同城灾备机房、异地灾备机房。具体编码待分配）
	 *<li>Digit 15 .. 23: 渠道业务顺序号（<9位自增长循环序列>）	
	 * @return
	 */
	public static String genBizParentSeq() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String strDate = (sdf.format(new Date())).toString();
		
		StringBuffer sbf = new StringBuffer();
		sbf.append(businessSeqConfigInfo.getSysNo()) 
			.append(businessSeqConfigInfo.getServiceNo())
			.append(strDate)
			.append(businessSeqConfigInfo.getAreaId())
			.append(KeyGenUtils.getKey("2"));
		return sbf.toString();
	}
	/**
	 * 交易流水的子编号，这个编号是交易编号32位中的后9位<p>
	 * <ul>
	 * <li>Digit 24 .. 26: 调用服务的系统的系统编号 （网银渠道固定313）
	 * <li>Digit 27 .. 28：子系统的编号
	 * <li>Digit 29 .. 32：调用系统本次流水号操作的顺序号
	 * <ul>
	 * <p>
	 * 27 .. 32根据rcs的自身特点进行了变更，一些是原始的系统编号
	 * Digit 27 .. 32: 调用系统本次流水号操作的顺序号（网银渠道发起固定填写000000）
	 * <p>
	 * @return
	 */
	public static String genBizSubSeq(){
		StringBuffer sb = new StringBuffer();
		
		sb.append(businessSeqConfigInfo.getSysNo())
			.append(businessSeqConfigInfo.getSubSysNo())
			.append(KeyGenUtils.getKey("3"));
		return sb.toString();
	}
	
	
	
	/**
	 * 获得交易编号
	 * @return
	 */
	public static String getCurrentTransNo(){
		return transNoLocal.get();
	}
	
	/**
	 * 设置交易编号
	 * @param transNo
	 */
	public static void setCurrentTransNo(String transNo){
		transNoLocal.set(transNo);
	}
}
