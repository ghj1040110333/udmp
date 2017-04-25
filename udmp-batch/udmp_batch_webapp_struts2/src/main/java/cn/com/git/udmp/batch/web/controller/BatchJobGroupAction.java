package cn.com.git.udmp.batch.web.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.git.udmp.batch.web.base.FrameworkBaseAction;
import cn.com.git.udmp.common.exception.FrameworkException;
import cn.com.git.udmp.component.model.Constants;
import cn.com.git.udmp.impl.batch.job.ucc.IBatchJobUCC;
import cn.com.git.udmp.impl.batch.job.vo.BatchJobVO;
import cn.com.git.udmp.impl.batch.jobDepend.vo.BatchJobDependVO;
import cn.com.git.udmp.impl.batch.jobParam.vo.BatchJobParamVO;
import cn.com.git.udmp.impl.batch.task.ucc.IBatchTaskUCC;

/**
 * 
 * @description 任务管理
 * @author Spring Cao
 * @date 2016年8月30日 上午11:45:59
 */
@Service
@Scope("prototype")
@Transactional
public class BatchJobGroupAction extends FrameworkBaseAction {
    private static final long serialVersionUID = 1L;

    /** 任务组操作标记 */
    private static final String ADD_FLAG = "1";
    private static final String UPDATE_FLAG = "2";
    private static final String DELETE_FLAG = "3";
    /**
     * @Fields IS_GROUP : "1"表示是任务组
     */
    private static final String IS_GROUP = "1";
    /**
     * @Fields JOB_TYPE : "G"表示是任务组
     */
    private static final String JOB_TYPE = "G";

    private String flag; // 任务组操作类型
    private String moduleId; // 页面返回的任务组id
    @Autowired
    private IBatchJobUCC batchJobUCC;
    @Autowired
    private IBatchTaskUCC batchTaskUCC;
    private BatchJobVO batchJobVO;
    private List<BatchJobVO> batchJobList = new ArrayList<BatchJobVO>();

    public BatchJobVO getBatchJobVO() {
        return batchJobVO;
    }

    public void setBatchJobVO(BatchJobVO batchJobVO) {
        this.batchJobVO = batchJobVO;
    }

    public IBatchJobUCC getBatchJobUCC() {
        return batchJobUCC;
    }

    public void setBatchJobUCC(IBatchJobUCC batchJobUCC) {
        this.batchJobUCC = batchJobUCC;
    }

    @Override
    public String getBizId() {
        return null;
    }

    /**
     * @description 打开主查询页面
     * @version
     * @title
     * @return 登录页面
     */
    public String showBatchJobGroupIndexPage() {
        return "showBatchJobGroupIndexPage";
    }

    /**
     * @description 查询任务组list
     * @version
     * @title
     * @return
     */
    public void getBatchJobGroupList() {
        BatchJobVO jobGroupVO = new BatchJobVO();
        jobGroupVO.setIsGroup(IS_GROUP);
        List<BatchJobVO> jobGroupList = new ArrayList<BatchJobVO>();
        jobGroupList = batchJobUCC.findAll(jobGroupVO);
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map;
        // 遍历获取到的菜单集合将每个菜单的属性放入一个map中
        for (BatchJobVO key : jobGroupList) {
            map = new HashMap<String, Object>();
            // pId,id和name 是生成菜单树的必要条件
            map.put("id", key.getJobId());
            map.put("pId", key.getJobGroupId());
            map.put("name", key.getJobName());
            list.add(map);
        }
        // 将数据类型为Map的List放入一个map中
        Map<String, Object> obj = new HashMap<String, Object>();
        obj.put("jobGroupList", list);
        outJson(obj);
    }

    /**
     * @description 更新任务组
     * @version
     * @title
     */
    public void updateJobGroup() {
        try {
            // 根据页面返回的操作标记判断新增修改操作
        	BatchJobVO jobVO = new BatchJobVO();
            List<BatchJobDependVO> batchJobDependList = new ArrayList<BatchJobDependVO>();
            List<BatchJobParamVO> batchJobParamList = new ArrayList<BatchJobParamVO>();
            jobVO.setBatchJobParamList(batchJobParamList);
            jobVO.setBatchJobPostDependList(batchJobDependList);
            jobVO.setBatchJobPreDependList(batchJobDependList);
            
            //modified by liang on 20160921--bug修复
            jobVO.setJobName(this.batchJobVO.getJobName());
            jobVO.setJobGroupId(this.batchJobVO.getJobGroupId());
            jobVO.setIsGroup("1");
            jobVO.setIsEnable("1");
            jobVO.setIsAllowManu("1");
            jobVO.setIsAllowSplit("1");
            jobVO.setJobBatchSize(new BigDecimal(100));
            jobVO.setJobThreadLimitCnt(new BigDecimal(1));
            jobVO.setJobAlertDuration(new BigDecimal(60));
            jobVO.setJobExpectDuration(new BigDecimal(60));
            
            if (flag.trim().equals(ADD_FLAG)) {
                jobVO.setJobType(JOB_TYPE);
                logger.debug("新增任务组");
                batchJobUCC.add(jobVO);
            } else if (flag.trim().equals(UPDATE_FLAG)) {
                logger.debug("更新任务组");
                batchJobUCC.update(jobVO);
            }
            // 处理成功则发送Ajax请求，前段页面根据状态信息显示操作成功，第四个参数为处理完成之后再次发送请求
            outAjax(Constants.DWZ_STATUSCODE_200, Constants.DWZ_MESSAGE_200, "", "", "");
        } catch (FrameworkException e) {
            // 如果处理异常则发送错误状态信息
            outAjax(Constants.DWZ_STATUSCODE_300, Constants.DWZ_MESSAGE_300, "", "", "");
            throw new FrameworkException(e.getMessage());
        }
    }

    /**
     * @description 删除任务组
     * @version
     * @title
     */
    public void deleteJobGroup() {
        try {
            // 根据页面返回的操作标记判断删除操作
            if (flag.trim().equals(DELETE_FLAG)) {
                logger.debug("删除任务组");
                batchJobVO = new BatchJobVO();
                batchJobVO.setJobId(new BigDecimal(moduleId));
                batchJobUCC.delete(batchJobVO);
            }
            // 处理成功则发送Ajax请求，前段页面根据状态信息显示操作成功，第四个参数为处理完成之后再次发送请求
            outAjax(Constants.DWZ_STATUSCODE_200, Constants.DWZ_MESSAGE_200, "", "", "");
        } catch (FrameworkException e) {
            // 如果处理异常则发送错误状态信息
            outAjax(Constants.DWZ_STATUSCODE_300, Constants.DWZ_MESSAGE_300, "", "", "");
            throw new FrameworkException(e.getMessage());
        }
    }

    /**
     * @description 显示任务组包含的任务
     * @version
     * @title
     */
    public void showJobGroupInfo() {
        try {
            batchJobVO = new BatchJobVO();
            batchJobVO.setJobGroupId(new BigDecimal(moduleId));
            batchJobVO.setIsGroup("0");
            batchJobList = new ArrayList<BatchJobVO>();
            batchJobList = batchJobUCC.findAll(batchJobVO);
            batchJobVO.setJobId(new BigDecimal(moduleId));
            batchJobVO = batchJobUCC.find(batchJobVO);
            // 将数据类型为Map的List放入一个map中
            Map<String, Object> obj = new HashMap<String, Object>();
            obj.put("batchJobList", batchJobList);
            outJson(obj);
        } catch (FrameworkException e) {
            outAjax(Constants.DWZ_STATUSCODE_300, Constants.DWZ_MESSAGE_300, "", "", "");
            throw new FrameworkException(e.getMessage());
        }
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public IBatchTaskUCC getBatchTaskUCC() {
        return batchTaskUCC;
    }

    public void setBatchTaskUCC(IBatchTaskUCC batchTaskUCC) {
        this.batchTaskUCC = batchTaskUCC;
    }

    public List<BatchJobVO> getBatchJobList() {
        return batchJobList;
    }

    public void setBatchJobList(List<BatchJobVO> batchJobList) {
        this.batchJobList = batchJobList;
    }
}
