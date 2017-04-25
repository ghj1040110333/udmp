package cn.com.git.udmp.batch.web.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.git.udmp.batch.web.base.FrameworkBaseAction;
import cn.com.git.udmp.component.model.CurrentPage;
import cn.com.git.udmp.impl.batch.job.ucc.IBatchJobUCC;
import cn.com.git.udmp.impl.batch.job.vo.BatchJobVO;
import cn.com.git.udmp.impl.batch.param.ucc.IParamManageUCC;
import cn.com.git.udmp.impl.batch.param.vo.ParamManageVO;
import cn.com.git.udmp.impl.batch.task.ucc.IBatchTaskUCC;
import cn.com.git.udmp.impl.batch.task.vo.BatchTaskParamVO;
import cn.com.git.udmp.impl.batch.task.vo.BatchTaskVO;

/**
 * 
 * @description 作业配置功能action
 * @author Spring Cao
 * @date 2016年8月30日 上午11:50:50
 */
@Service
@Scope("prototype")
@Transactional
public class BatchTaskAction extends FrameworkBaseAction {

    private static final long serialVersionUID = 1L;

	// 功能名称，用于日志输出
	private final String functionName = "作业配置";

	// 细览页面操作标识，标识细览页面是add/update
	private String detailControlFlag;

	// 作业参数记录数，用于细览页面初始化
	private int paramsCount;

	// 查询后，作业名称输入框显示内容保留
	private String taskNameDummy;

	// 作业VO,此VO用于outline页面
	private BatchTaskVO batchTaskVoOutline = new BatchTaskVO();

	// 作业VO,此VO用于detail页面
	private BatchTaskVO batchTaskVO = new BatchTaskVO();

	// 系统参数VO列表
	private List<ParamManageVO> paramManageVOList;

	// 作业UCC
	@Autowired
	private IBatchTaskUCC batchTaskUCC;

	// 系统参数UCC
	@Autowired
	private IParamManageUCC paramUCC;

	// 任务UCC
	@Autowired
	private IBatchJobUCC batchJobUCC;

	// 批量获取页面中作业参数的作业ID
	private BigDecimal[] taskId;

	// 批量获取页面中作业参数的作业名称
	private String[] taskParamName;

	// 批量获取页面中作业参数的手工填写作业类型
	private String[] taskParamDataType;

	// 批量获取页面中作业参数的数组标识
	private String[] isArray;

	// 批量获取页面中作业参数的必填标识
	private String[] isRequired;

	// 批量获取页面中作业参数的非手工关联参数ID
	private BigDecimal[] paramId;

	// 批量获取页面中作业参数的手工录入标识
	private String[] isManu;

	/**
	 * @description 初始化作业信息页面，分页显示batchTask作业列表
	 * 
	 * @see BatchTaskVO;IBatchTaskUCC
	 * @return {String} result
	 * @since 2014-2-04 0.0.1
	 */
	@SuppressWarnings("unchecked")
	public String outline() {
		String result = "";
		initBatchTaskActionParam();
		try {
			currentPage = getCurrentPage();
			currentPage.setTotal(batchTaskUCC.findTotal(batchTaskVoOutline));
			currentPage.setPageTotal(currentPage.getTotal() / currentPage.getPageSize() + 1);
			currentPage = batchTaskUCC.queryForPage(batchTaskVoOutline, currentPage);
			result = "batchTaskOutline";
		} catch (Exception e) {
			result = "error";
			message = "作业信息查询失败";
			logger.error(functionName + message + "\n", e.getMessage());
		}
		return result;
	}

	/**
	 * @description 初始化作业详细信息页面，分别调用方法完成增加页面和修改页面的初始化。
	 * 
	 * @return {String} result
	 * @since 2014-2-04 0.0.1
	 */
	public String detail() {
		String result = "";
		try {
			String doUpdate = ServletActionContext.getRequest().getParameter(
					"doUpdate");
			if ("yes".equals(doUpdate)) {
				String taskId = ServletActionContext.getRequest().getParameter(
						"taskId");
				updateDetailInit(BigDecimal.valueOf(Integer.parseInt(taskId)));
			} else {
				addDetailInit();
			}
			result = "batchTaskDetail";
		} catch (Exception e) {
			result = "error";
			message = "详细信息初始化失败";
			logger.error(functionName + message + ":{}\n", e.getMessage());
		}
		return result;
	}

	/**
	 * @description 初始化新增页面。
	 * 
	 * @since 2014-2-04 0.0.1
	 */
	private void addDetailInit() {
		batchTaskVO = new BatchTaskVO();
		// 获取系统参数列表
		ParamManageVO paramVo = new ParamManageVO();
		paramVo.setIsDeleted("0");
		paramManageVOList = paramUCC.findAll(paramVo);
		// 细览页面控制标识为“新增”
		detailControlFlag = "add";
		// 新增页面初始化时，作业参数列表记录条数为0
		paramsCount = 0;
	}

	/**
	 * @description 初始化修改页面。
	 * 
	 * @param {BigDecimal} taskId
	 * @see BatchTaskVO;ParamManageVO;BatchTaskUCC
	 * @since 2014-2-04 0.0.1
	 */
	private void updateDetailInit(BigDecimal taskId) {
		// 通过taskId,获取作业信息BatchTaskVO,此VO中包含作业参数列表信息
		batchTaskVO = new BatchTaskVO();
		batchTaskVO.setTaskId(taskId);
		batchTaskVO = batchTaskUCC.find(batchTaskVO);
		// 获取系统参数列表信息
		ParamManageVO paramVo = new ParamManageVO();
        paramVo.setIsDeleted("0");
        paramManageVOList = paramUCC.findAll(paramVo);
		// 细览页面控制标识为“修改”
		detailControlFlag = "update";
		// 获取作业参数列表的记录条数
		paramsCount = batchTaskVO.getBatchTaskParamVOList().size();
	}

	/**
	 * @description 新增作业
	 * 
	 * @return {String} result
	 * @see BatchTaskVO;BatchTaskParamVO;BatchTaskUCC
	 * @since 2014-2-04 0.0.1
	 */
	public String add() {
		String result = "";
		try {
			batchTaskUCC.add(batchTaskVO, fillBatchTaskParamVOList());
			result = "success";
			message = "ok_作业配置添加成功";
		} catch (Exception e) {
			message = "er_作业配置添加失败";
			logger.error(functionName + message + "\n{}", e.getMessage());
			result = "error";
		}
		return result;
	}

	/**
	 * @description 删除作业
	 * 
	 * @return {String} result
	 * @see BatchTaskVO;BatchTaskParamVO;BatchTaskUCC
	 * @since 2014-2-04 0.0.1
	 */
	public String del() {
		String result = "";
		try {
			batchTaskUCC.batchDelete(fillBatchTaskVOListForDelete(),
					fillBatchTaskParamVOListForDelete());
			result = "success";
			message = "ok_作业配置删除成功";
		} catch (Exception e) {
			message = "er_作业配置删除失败";
			logger.error(functionName + message + "\n", e.getMessage());
			result = "error";

		}
		return result;
	}

	/**
	 * @description 修改作业
	 * 
	 * @return {String} result
	 * @see BatchTaskVO;BatchTaskParamVO;BatchTaskUCC
	 * @since 2014-2-04 0.0.1
	 */
	public String update() {
		String result = "";
		try {
			batchTaskUCC.update(batchTaskVO,
					fillBatchTaskParamVOList());
			result = "success";
			message = "ok_作业配置修改成功";
		} catch (Exception e) {
			message = "er_作业配置修改失败";
			logger.error(functionName + message + ":{}\n", e.getMessage());
			result = "error";
		}
		return result;
	}

	/**
	 * @description 删除作业前检查，该作业是否已经添加到任务中
	 * 
	 * @return {String} result
	 * @see BatchJobVO;BatchJobUCC
	 * @since 2014-2-04 0.0.1
	 */
	public String delCheck() {
		String result = "success";
		message = "ok";
		BatchJobVO batchJobVO = new BatchJobVO();
		for (int i = 0; i < taskId.length; i++) {
			batchJobVO.setTaskId(taskId[i]);
			batchJobVO.setIsDeleted("0");
			if (batchJobUCC.findAll(batchJobVO).size() > 0) {
				message = "er_第" + (i + 1) + "条勾选作业已添加至任务中，当前不能删除";
				result = "error";
				break;
			}
		}
		return result;
	}

	/**
	 * @description 修改作业前检查，包含该作业的任务是否处于启用状态
	 * 
	 * @return {String} result
	 * @see BatchJobVO;BatchJobUCC
	 * @since 2014-2-04 0.0.1
	 */
	public String updateCheck() {
		String result = "success";
		message = "ok";
		BatchJobVO batchJobVO = new BatchJobVO();
		for (int i = 0; i < taskId.length; i++) {
			batchJobVO.setTaskId(taskId[i]);
			batchJobVO.setIsEnable("1");
			batchJobVO.setIsDeleted("0");
			if (batchJobUCC.findAll(batchJobVO).size() > 0) {
				message = "er_包含该作业的任务已经启用，当前不能修改";
				result = "error";
				break;
			}
		}
		return result;
	}

	/**
	 * @description 填充作业参数列表，因为没有从页面统一获取BatchTaskParamVOList，
	 *              故通过此方法通过页面批量的获取值统一在action层统一填充BatchTaskParamVOList。
	 * 
	 * @return {List<BatchTaskParamVO> } batchTaskParamVOList
	 * @see BatchTaskParamVO
	 * @since 2014-2-04 0.0.1
	 */
	private List<BatchTaskParamVO> fillBatchTaskParamVOList() {
		List<BatchTaskParamVO> batchTaskParamVOList = new ArrayList<BatchTaskParamVO>();
		for (int i = 0; i < taskParamName.length; i++) {
			BatchTaskParamVO tempVO = new BatchTaskParamVO();
			tempVO.setTaskParamName(taskParamName[i]);
			tempVO.setTaskParamDataType(taskParamDataType[i]);
			tempVO.setParamId(paramId[i]);
			tempVO.setIsArray(isArray[i]);
			tempVO.setIsRequired(isRequired[i]);
			tempVO.setIsManu(isManu[i]);
			tempVO.setParamOrder(BigDecimal.valueOf(i));
			batchTaskParamVOList.add(tempVO);
		}
		return batchTaskParamVOList;
	}

	/**
	 * @description 创建BatchTaskVOList，set要删除的作业ID
	 * 
	 * @return {List<BatchTaskVO>} result
	 * @see BatchTaskVO
	 * @since 2014-2-04 0.0.1
	 */
	private List<BatchTaskVO> fillBatchTaskVOListForDelete() {
		List<BatchTaskVO> result = new ArrayList<BatchTaskVO>();
		for (int i = 0; i < taskId.length; i++) {
			BatchTaskVO batchTaskVO = new BatchTaskVO();
			batchTaskVO.setTaskId(taskId[i]);
			result.add(batchTaskVO);
		}
		return result;
	}

	/**
	 * @description 创建BatchTaskParamVOList，set要删除的作业ID
	 * 
	 * @return {List<BatchTaskParamVO>} result
	 * @see BatchTaskParamVO
	 * @since 2014-2-04 0.0.1
	 */
	private List<BatchTaskParamVO> fillBatchTaskParamVOListForDelete() {
		List<BatchTaskParamVO> result = new ArrayList<BatchTaskParamVO>();
		for (int i = 0; i < taskId.length; i++) {
			BatchTaskParamVO batchTaskParamVO = new BatchTaskParamVO();
			batchTaskParamVO.setTaskId(taskId[i]);
			result.add(batchTaskParamVO);
		}
		return result;
	}

	/**
	 * @description 此方法用于实现页面初始化功能，initPager=yes 将初始化分页信息 initOutline=yes
	 *              将初始化作业配置页面信息。
	 * 
	 * @see BatchTaskVO;CurrentPage;
	 * @since 2014-2-04 0.0.1
	 */
	private void initBatchTaskActionParam() {
		String initPager = ServletActionContext.getRequest().getParameter(
				"initPager");
		String initOutline = ServletActionContext.getRequest().getParameter(
				"initOutline");
		if ("yes".equals(initOutline)) {
			batchTaskVoOutline = new BatchTaskVO();
			currentPage = new CurrentPage<Object>();
		}
		if ("yes".equals(initPager)) {
			currentPage = new CurrentPage<Object>();
		}
		taskNameDummy = batchTaskVoOutline.getTaskName();
	}

	/** action声明变量getter和setter */

	public BatchTaskVO getBatchTaskVoOutline() {
		return batchTaskVoOutline;
	}

	public void setBatchTaskVoOutline(BatchTaskVO batchTaskVoOutline) {
		this.batchTaskVoOutline = batchTaskVoOutline;
	}

	public BatchTaskVO getBatchTaskVO() {
		return batchTaskVO;
	}

	public void setBatchTaskVO(BatchTaskVO batchTaskVO) {
		this.batchTaskVO = batchTaskVO;
	}

	public BigDecimal[] getTaskId() {
		return taskId;
	}

	public void setTaskId(BigDecimal[] taskId) {
		this.taskId = taskId;
	}

	public String[] getTaskParamName() {
		return taskParamName;
	}

	public void setTaskParamName(String[] taskParamName) {
		this.taskParamName = taskParamName;
	}

	public String[] getTaskParamDataType() {
		return taskParamDataType;
	}

	public void setTaskParamDataType(String[] taskParamDataType) {
		this.taskParamDataType = taskParamDataType;
	}

	public String[] getIsArray() {
		return isArray;
	}

	public void setIsArray(String[] isArray) {
		this.isArray = isArray;
	}

	public String[] getIsRequired() {
		return isRequired;
	}

	public void setIsRequired(String[] isRequired) {
		this.isRequired = isRequired;
	}

	public BigDecimal[] getParamId() {
		return paramId;
	}

	public void setParamId(BigDecimal[] paramId) {
		this.paramId = paramId;
	}

	public IBatchTaskUCC getBatchTaskUCC() {
		return batchTaskUCC;
	}

	public void setBatchTaskUCC(IBatchTaskUCC batchTaskUCC) {
		this.batchTaskUCC = batchTaskUCC;
	}

	public IBatchJobUCC getBatchJobUCC() {
		return batchJobUCC;
	}

	public void setBatchJobUCC(IBatchJobUCC batchJobUCC) {
		this.batchJobUCC = batchJobUCC;
	}

	public String[] getIsManu() {
		return isManu;
	}

	public void setIsManu(String[] isManu) {
		this.isManu = isManu;
	}

	public IParamManageUCC getParamUCC() {
		return paramUCC;
	}

	public void setParamUCC(IParamManageUCC paramUCC) {
		this.paramUCC = paramUCC;
	}

	public String getTaskNameDummy() {
		return taskNameDummy;
	}

	public void setTaskNameDummy(String taskNameDummy) {
		this.taskNameDummy = taskNameDummy;
	}

	public List<ParamManageVO> getParamManageVOList() {
		return paramManageVOList;
	}

	public void setParamManageVOList(List<ParamManageVO> paramManageVOList) {
		this.paramManageVOList = paramManageVOList;
	}

	public String getDetailControlFlag() {
		return detailControlFlag;
	}

	public void setDetailControlFlag(String detailControlFlag) {
		this.detailControlFlag = detailControlFlag;
	}

	public int getParamsCount() {
		return paramsCount;
	}

	public void setParamsCount(int paramsCount) {
		this.paramsCount = paramsCount;
	}

	public String getBizId() {
		// TODO Auto-generated method stub
		return null;
	}

}
