package cn.com.git.udmp.batch.web.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import cn.com.git.udmp.batch.web.base.FrameworkBaseAction;
import cn.com.git.udmp.common.exception.FrameworkException;
import cn.com.git.udmp.component.model.Constants;
import cn.com.git.udmp.impl.batch.agent.ucc.IBatchAgentUCC;
import cn.com.git.udmp.impl.batch.agent.vo.BatchAgentVO;

/**
 * 
 * @description 批处理代理配置
 * @author Spring Cao
 * @date 2016年8月30日 上午11:42:33
 */
@Service
@Scope("prototype")
@Transactional
public class BatchAgentAction extends FrameworkBaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private IBatchAgentUCC batchAgentUCC;
	private BatchAgentVO batchAgentVO;

	/**
	 * @Fields batchAgentTVO : 查询VO
	 */
	private BatchAgentVO batchAgentTVO = new BatchAgentVO();

	/**
	 * @Fields batchAgentAVO : 增加修改删除使用VO
	 */
	private BatchAgentVO batchAgentAVO = new BatchAgentVO();

	/**
	 * @Fields List<BatchAgentVO> : 显示用List
	 */
	private List<BatchAgentVO> batchAgentList;

	public BatchAgentVO getBatchAgentAVO() {
		return batchAgentAVO;
	}

	public void setBatchAgentAVO(BatchAgentVO batchAgentAVO) {
		this.batchAgentAVO = batchAgentAVO;
	}

	public BatchAgentVO getBatchAgentTVO() {
		return batchAgentTVO;
	}

	public void setBatchAgentTVO(BatchAgentVO batchAgentTVO) {
		this.batchAgentTVO = batchAgentTVO;
	}

	public BatchAgentVO getBatchAgentVO() {
		return batchAgentVO;
	}

	public void setBatchAgentVO(BatchAgentVO batchAgentVO) {
		this.batchAgentVO = batchAgentVO;
	}

	public IBatchAgentUCC getBatchAgentUCC() {
		return batchAgentUCC;
	}

	public void setBatchAgentUCC(IBatchAgentUCC batchAgentUCC) {
		this.batchAgentUCC = batchAgentUCC;
	}

	public List<BatchAgentVO> getBatchAgentList() {
		return batchAgentList;
	}

	public void setBatchAgentList(List<BatchAgentVO> batchAgentList) {
		this.batchAgentList = batchAgentList;
	}

	@Override
	public String getBizId() {
		return null;
	}

	/**
	 * @description 打开主查询页面
	 * @version
	 * @title
	 * @author yangfeiit@newchinalife.com
	 * @return 登录页面
	 */
	public String showBatchAgentIndexPage() {
		return "showBatchAgentIndexPage";
	}

	/**
	 * @description 打开新增页面
	 * @version
	 * @title
	 * @author yangfeiit@newchinalife.com
	 * @return 新增页面
	 */
	public String showInsertBatchAgentPage() {
		return "showInsertBatchAgentPage";
	}

	/**
	 * @description 打开修改页面，重新查询数据显示在页面上
	 * @version
	 * @title
	 * @author yangfeiit@newchinalife.com
	 * @return 登录页面
	 */
	public String showUpdateBatchAgentPage() {
		try {
			String batchAgentId = this.getRequest().getParameter(
					"batchAgentVO.batchAgentId");
			if (StringUtils.isEmpty(batchAgentId)) {
				this.errorMsg = "";
				return "error";
			}
			batchAgentVO = new BatchAgentVO();
			batchAgentVO.setAgentId(new BigDecimal(batchAgentId));
			batchAgentAVO = batchAgentUCC.find(batchAgentVO);
		} catch (FrameworkException e) {
			outAjax(Constants.DWZ_STATUSCODE_300, "open update page failed!",
					"", "", "");
			logger.error("open update page failed!", e);
		}
		return "showUpdateBatchAgentPage";
	}

	/**
	 * @description 查询操作，根据查询条件查出数据
	 * @version
	 * @title
	 * @author yangfeiit@newchinalife.com
	 * @return 登录页面
	 */
	public String showBatchAgent() {
		try {
			if (null != batchAgentTVO) {
				currentPage = getCurrentPage();
				currentPage.setTotal(batchAgentUCC
						.findBatchAgentTotal(batchAgentTVO));
				currentPage.setPageTotal(currentPage.getTotal()
						/ currentPage.getPageSize() + 1);
				currentPage = batchAgentUCC.queryForPage(batchAgentTVO,
						currentPage);
			}
		} catch (FrameworkException e) {
			e.printStackTrace();
			this.errorMsg = "";
			return "error";
		}
		return "showBatchAgentIndexPage";
	}

	/**
	 * @description 新增操作
	 * @version
	 * @title
	 * @author yangfeiit@newchinalife.com
	 * @return
	 */
	public void insertBatchAgent() {
		try {
			batchAgentUCC.add(batchAgentAVO);
			outAjax(Constants.DWZ_STATUSCODE_200, Constants.DWZ_MESSAGE_200,
					"", "", Constants.DWZ_CALLBACKTYPE_CLOSE);
			logger.info("新增代理成功");
		} catch (FrameworkException e) {
			outAjax(Constants.DWZ_STATUSCODE_300, "新增代理失败", "", "", "");
			logger.error("新增代理失败", e);
		}
	}

	/**
	 * @description 删除操作，将选中的一条数据删除
	 * @version
	 * @title
	 * @author yangfeiit@newchinalife.com
	 * @return
	 */
	public void deleteBatchAgent() {
		try {
			String batchAgentId = this.getRequest().getParameter(
					"batchAgentVO.batchAgentId");
			batchAgentVO = new BatchAgentVO();
			batchAgentVO.setAgentId(new BigDecimal(batchAgentId));
			BatchAgentVO batchAgentBackVO = batchAgentUCC.find(batchAgentVO);
			batchAgentUCC.delete(batchAgentBackVO);
			outAjax(Constants.DWZ_STATUSCODE_200, Constants.DWZ_MESSAGE_200,
					"", "", "");
			logger.info("删除代理成功");
		} catch (FrameworkException e) {
			outAjax(Constants.DWZ_STATUSCODE_300, "删除代理失败", "", "", "");
			logger.error("删除代理失败", e);
		}
	}

	/**
	 * @description 更新操作
	 * @version
	 * @title
	 * @author yangfeiit@newchinalife.com
	 * @return
	 */
	public void update() {
		try {
			batchAgentUCC.update(batchAgentAVO);
			outAjax(Constants.DWZ_STATUSCODE_200, Constants.DWZ_MESSAGE_200,
					"", "", Constants.DWZ_CALLBACKTYPE_CLOSE);
			logger.info("update data success");
		} catch (FrameworkException e) {
			outAjax(Constants.DWZ_STATUSCODE_300, "更新代理失败", "", "", "");
			logger.error("update data failed", e);
		}
	}

	/**
	 * @description 测试连接
	 * @version
	 * @title
	 * @author yangfeiit@newchinalife.com
	 * @return
	 */
	public void linkToAgent() {
		// TODO 测试连接按钮事件处理 带完善
		try {
			outAjax(Constants.DWZ_STATUSCODE_200, "连接成功", "", "", "");
			logger.info("连接成功");
		} catch (FrameworkException e) {
			outAjax(Constants.DWZ_STATUSCODE_300, "连接失败", "", "", "");
			logger.error("连接失败", e);
		}
	}
}
