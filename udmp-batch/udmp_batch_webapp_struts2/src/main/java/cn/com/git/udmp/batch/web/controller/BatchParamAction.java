package cn.com.git.udmp.batch.web.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.git.udmp.batch.web.base.FrameworkBaseAction;
import cn.com.git.udmp.common.exception.FrameworkException;
import cn.com.git.udmp.component.model.Constants;
import cn.com.git.udmp.impl.batch.param.ucc.IParamManageUCC;
import cn.com.git.udmp.impl.batch.param.vo.ParamManageVO;
import cn.com.git.udmp.impl.batch.param.vo.SubParamVO;

/**
 * 
 * @description 批处理参数管理
 * @author Spring Cao
 * @date 2016年8月30日 上午11:49:12
 */
@Service
@Scope("prototype")
@Transactional
public class BatchParamAction extends FrameworkBaseAction {

    private static final long serialVersionUID = 1L;

    private String[] paramItemName; // 子参数名
    private String[] paramItemValue; // 子参数值

    private ParamManageVO batchParamVO;

    /** 查询使用VO */
    private ParamManageVO batchParamTVo = new ParamManageVO();

    /** 增加修改删除使用VO */
    private ParamManageVO batchParamAVo = new ParamManageVO();

    /** 参数管理UCC */
    @Autowired
    private IParamManageUCC paramUCC;

    public String[] getParamItemName() {
        return paramItemName;
    }

    public void setParamItemName(String[] paramItemName) {
        this.paramItemName = paramItemName;
    }

    public String[] getParamItemValue() {
        return paramItemValue;
    }

    public void setParamItemValue(String[] paramItemValue) {
        this.paramItemValue = paramItemValue;
    }

    public ParamManageVO getBatchParamVO() {
        return batchParamVO;
    }

    public void setBatchParamVO(ParamManageVO batchParamVO) {
        this.batchParamVO = batchParamVO;
    }

    public ParamManageVO getBatchParamTVo() {
        return batchParamTVo;
    }

    public void setBatchParamTVo(ParamManageVO batchParamTVo) {
        this.batchParamTVo = batchParamTVo;
    }

    public ParamManageVO getBatchParamAVo() {
        return batchParamAVo;
    }

    public void setBatchParamAVo(ParamManageVO batchParamAVo) {
        this.batchParamAVo = batchParamAVo;
    }

    public IParamManageUCC getParamUCC() {
        return paramUCC;
    }

    public void setParamUCC(IParamManageUCC paramUCC) {
        this.paramUCC = paramUCC;
    }

    /**
     * @description 打开主查询页面
     * @version
     * @title
     * @return 登录页面
     */
    public String showBatchParamIndexPage() {
        return "showBatchParamIndexPage";
    }

    /**
     * @description 打开新增页面
     * @version
     * @title
     * @return 新增页面
     */
    public String showInsertBatchParamPage() {
        batchParamVO = new ParamManageVO();
        batchParamAVo = new ParamManageVO();

        return "showInsertBatchParamPage";
    }

    /**
     * @description 打开修改页面
     * @version
     * @title
     * @return 修改页面
     */
    public String showUpdateBatchParamPage() {
        try {
            String batchParamId = this.getRequest().getParameter("paramVo.paramId");
            if (batchParamId == null) {
                this.errorMsg = "";
                return "error";
            }
            batchParamVO = new ParamManageVO();
            batchParamVO.setParamId(new BigDecimal(batchParamId));
            batchParamAVo = paramUCC.find(batchParamVO);
        } catch (FrameworkException e) {
            e.printStackTrace();
            outAjax(Constants.DWZ_STATUSCODE_300, "open update page failed!", "", "", "");
            logger.error("open update page failed!", e);
        }

        return "showUpdateBatchParamPage";
    }

    /**
     * @description 参数查询
     * @version
     * @title
     * @return 参数管理查询页面
     */
    public String showBatchParam() {
        try {
            if (null != batchParamTVo) {
				currentPage = getCurrentPage();
				currentPage.setTotal(paramUCC.findBatchAgentTotal(batchParamTVo));
				currentPage.setPageTotal(currentPage.getTotal()	/ currentPage.getPageSize() + 1);
            	currentPage = paramUCC.queryForPage(batchParamTVo, currentPage);
            }
        } catch (FrameworkException e) {
//            e.printStackTrace();
            logger.error(e.getMessage());
            this.errorMsg = "";
            return "error";
        }
        return "showBatchParamIndexPage";
    }

    /**
     * @description 参数新增
     * @version
     * @title
     */
    public void insertParam() {
        try {
            fillSubParamList();
            paramUCC.add(batchParamAVo);
            outAjax(Constants.DWZ_STATUSCODE_200, Constants.DWZ_MESSAGE_200, "", "", Constants.DWZ_CALLBACKTYPE_CLOSE);
            logger.info("insert data success");
        } catch (FrameworkException e) {
            e.printStackTrace();
            outAjax(Constants.DWZ_STATUSCODE_300, "insert data failed", "", "", "");
            logger.error("insert data failed", e);
        }
    }

    /**
     * @description 参数修改
     * @version
     * @title
     * @return
     */
    public void update() {
        try {
            fillSubParamList();
            paramUCC.update(batchParamAVo);
            outAjax(Constants.DWZ_STATUSCODE_200, Constants.DWZ_MESSAGE_200, "", "", Constants.DWZ_CALLBACKTYPE_CLOSE);
            logger.info("update data success");
        } catch (FrameworkException e) {
            e.printStackTrace();
            outAjax(Constants.DWZ_STATUSCODE_300, "update data failed", "", "", "");
            logger.error("update data failed", e);
        }
    }

    /**
     * @description 参数删除
     * @version
     * @title
     * @return
     */
    public void delete() {
        try {
            String paramId = this.getRequest().getParameter("paramVo.paramId");
            batchParamVO = new ParamManageVO();
            batchParamVO.setParamId(new BigDecimal(paramId));
            paramUCC.delete(batchParamVO);
            // TODO 删除时判断参数是否使用中
            // 作业参数声明表T_UDMP_BATCH_TASK_PARAM.PARAM_ID中已被使用的参数不能删除
            //
            // if("使用中"){
            // this.errorMsg = "已作为作业参数使用中，不能删除";
            // }
            outAjax(Constants.DWZ_STATUSCODE_200, Constants.DWZ_MESSAGE_200, "", "", "");
            logger.info("delete data success");
        } catch (FrameworkException e) {
            e.printStackTrace();
            outAjax(Constants.DWZ_STATUSCODE_300, "delete data failed", "", "", "");
            logger.error("delete data failed", e);
        }
    }

    /**
     * @description 填充子参数List
     * @version
     * @title
     * @author yangfeiit@newchinalife.com
     */
    public void fillSubParamList() {
        List<SubParamVO> batchSubParamList = new ArrayList<SubParamVO>();
        SubParamVO subParamVO;
        if (batchParamAVo.getIsSingleValue().equals("0")) {
            for (int i = 0; i < paramItemName.length; i++) {
                subParamVO = new SubParamVO();
                subParamVO.setParamItemName(paramItemName[i].trim());
                subParamVO.setParamItemValue(paramItemValue[i].trim());
                subParamVO.setParamItemOrder(new BigDecimal(i + 1));
                batchSubParamList.add(subParamVO);
            }
        }
        batchParamAVo.setSubParamList(batchSubParamList);
    }

    @Override
    public String getBizId() {
        // TODO Auto-generated method stub
        return null;
    }
}