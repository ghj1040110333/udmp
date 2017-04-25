

package cn.com.git.udmp.test.batch.framework.ucc;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.com.git.udmp.common.exception.FrameworkException;
import cn.com.git.udmp.common.utils.testsupport.AbstractTest;
import cn.com.git.udmp.component.model.CurrentPage;
import cn.com.git.udmp.impl.batch.jobRun.ucc.IBatchJobRunUCC;
import cn.com.git.udmp.impl.batch.jobRun.vo.BatchJobRunVO;

/**
 * @author liang
 *
 */
@ContextConfiguration(locations = { "classpath*:/META-INF/spring/spring-udmp-context.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class BatchJobRunUCCTest{
	@Autowired
	private IBatchJobRunUCC batchJobRunUCC;

	@Test
	public void testFindAll() {
		BatchJobRunVO batchJobRunVO = new BatchJobRunVO();
		// batchJobRunVO.setIsEnable(BatchCommonConst.BATCH_FLAG_TRUE);
		// batchJobRunVO.setIsDeleted(BatchCommonConst.BATCH_FLAG_FALSE);
		// batchJobRunVO.setIsGroup(BatchCommonConst.BATCH_FLAG_FALSE);
		List<BatchJobRunVO> batchJobVOResult = batchJobRunUCC.findAll(batchJobRunVO);
		System.out.println(batchJobVOResult);
	}

	/**
	 * @description 测试任务运行实例信息查询操作（需要关联任务表的任务ID查询，否则查询不出结果）
	 *  
	*/
	@Test
	public void testFind() {
		BatchJobRunVO batchJobRunVO = new BatchJobRunVO();
		// batchJobVO.setIsEnable(BatchCommonConst.BATCH_FLAG_TRUE);
		// batchJobVO.setIsDeleted(BatchCommonConst.BATCH_FLAG_FALSE);
		// batchJobVO.setIsGroup(BatchCommonConst.BATCH_FLAG_FALSE);
		batchJobRunVO.setJobRunId(new BigDecimal(1));
		batchJobRunVO.setJobId(new BigDecimal(238));
		BatchJobRunVO result = batchJobRunUCC.find(batchJobRunVO);
		System.out.println("结果集是否为null:" + result == null);
		System.out.println(result.getJobName());
		System.out.println(result.getJobChainRunId());
	}

	/**
	 * @title
	 * @description 测试任务运行实例分页查询操作（需要关联任务表的任务ID查询，否则查询不出结果）
	 *  
	*/
	@Test
	public void testQueryForPage() {
		BatchJobRunVO batchJobVO = new BatchJobRunVO();
		// batchJobVO.setIsEnable(BatchCommonConst.BATCH_FLAG_TRUE);
		// batchJobVO.setIsDeleted(BatchCommonConst.BATCH_FLAG_FALSE);
		// batchJobVO.setIsGroup(BatchCommonConst.BATCH_FLAG_FALSE);
		CurrentPage<BatchJobRunVO> currentPage = new CurrentPage<BatchJobRunVO>();
		CurrentPage<BatchJobRunVO> queryForPage = batchJobRunUCC.queryForPage(batchJobVO, currentPage);
		System.out.println(queryForPage.getPageItems().size());
	}

	/**
	 * @title
	 * @description 测试job运行实例表的新增操作
	 *  
	*/
	@Test
	public void testAdd() {
		BatchJobRunVO batchJobVO = new BatchJobRunVO();
		batchJobVO.setJobId(new BigDecimal(266));
		batchJobVO.setCreateTime(new Date());
		batchJobVO.setStartTime(new Date());
		batchJobVO.setProcessDate(new Date());
		batchJobVO.setDueDate(new Date());
		batchJobVO.setStatus("1");
		batchJobVO.setIsSplit("1");
		batchJobVO.setAgentId(new BigDecimal(207));
		batchJobRunUCC.add(batchJobVO);
		System.out.println(""+batchJobVO.getJobRunId());
//		throw new FrameworkException("1");
	}
	
	@Test
	public void testGetBatch(){
	    int batch = batchJobRunUCC.findLastBatch("e6f7d8dfad9846f6a10bc37a94a63d6b");
	    System.out.println(batch);
	}

	
	public void setBatchJobUCC(IBatchJobRunUCC batchJobRunUCC) {
		this.batchJobRunUCC = batchJobRunUCC;
	}
}
