package cn.com.git.udmp.batch.web.controller;

import org.springframework.util.StringUtils;

import com.opensymphony.xwork2.ActionContext;

import cn.com.git.udmp.batch.web.base.FrameworkBaseAction;
import cn.com.git.udmp.component.model.Constants;

/**
 * 
 * @description 主页面action
 * @author wulei_wb
 * @date 2014年9月5日 上午11:24:57
 */
public class MainInitAction extends FrameworkBaseAction {
    private static final long serialVersionUID = -7618144143085614888L;


    private String loginPath;

    /**
     * 
     * @description 进入主页面
     * @version
     * @title
     * @author huoyp_wb
     * @return SUCCESS
     */
    public String toMain() {
        // 从request中获取tokenId和amUrl
//        String tokenId = getRequest().getParameter("tokenId");
//        String amUrl = getRequest().getParameter("amUrl");
//        String flag = getRequest().getParameter("flag");
        // 判断是从页面登录还是从同一认证平台跳转
        System.out.println("123");
//        return SUCCESS;
        // 将排列好的菜单放入session中供前台页面使用
//        this.getSession().setAttribute("menuStr", menuStr.toString());
        return "test";
    }


    /**
     * 
     * @description 进入iframe主页面
     * @version
     * @title
     * @author huoyp_wb
     * @return SUCCESS
     */
    public String toIMain() {
        return SUCCESS;
    }

    /**
     * 
     * @description 进入登陆页面
     * @version
     * @title
     * @author huoyp_wb
     * @return SUCCESS
     */
    public String toLogin() {
        if (!StringUtils.isEmpty(loginPath)) {
            ActionContext.getContext().put("timeout", Constants.TIME_OUT_MESSAGE);
            loginPath = "";
        }
        return SUCCESS;
    }
    
    /**
     * 
     * @description 进入欢迎页面
     * @version
     * @title
     * @author huoyp_wb
     * @return mainInitAction_toWelcome
     */
    public String toWelcome() {
        return "mainInitAction_toWelcome";
    }

    public String getLoginPath() {
        return loginPath;
    }

    public void setLoginPath(String loginPath) {
        this.loginPath = loginPath;
    }

    @Override
    public String getBizId() {
        return null;
    }
}
