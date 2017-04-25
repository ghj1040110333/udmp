package cn.com.git.udmp.batch.web.controller;

import java.util.HashMap;
import java.util.Map;

import cn.com.git.udmp.batch.web.base.FrameworkBaseAction;
import cn.com.git.udmp.common.utils.SpringContextHolder;
import cn.com.git.udmp.component.batch.core.ServerInit;


/**
 * @ClassName: BatchAgentAction
 * @Description: 批处理代理配置
 * @author yangfeiit@newchinalife.com
 * @date 2015-02-03 下午5:13:25
 */
public class BatchServerPortAction extends FrameworkBaseAction {
    private static final long serialVersionUID = 1L;

    private static String serverListenPort;
    
    private static String serverStatus;
    
    private String serverPortstatus;
    
    public static String getServerListenPort() {
        return serverListenPort;
    }

    public static void setServerListenPort(String serverListenPort) {
        BatchServerPortAction.serverListenPort = serverListenPort;
    }
    
    public static String getServerStatus() {
        return serverStatus;
    }

    public static void setServerStatus(String serverStatus) {
        BatchServerPortAction.serverStatus = serverStatus;
    }

    public String getServerPortstatus() {
        return serverPortstatus;
    }

    public void setServerPortstatus(String serverPortstatus) {
        this.serverPortstatus = serverPortstatus;
    }

    /**
     * @description 打开主页面
     * @version
     * @title
     * @author yangfeiit@newchinalife.com
     * @return 主页面
     */
    public String showBatchServerPortIndexPage(){
        System.out.println(123);
        serverPortstatus = serverStatus;
        return "showBatchServerPortIndexPage";        
    }
    
    
    /**
     * @description server端口监听启动
     * @version
     * @title
     * @author yangfeiit@newchinalife.com
     */
    public void startServerPort() {
        serverListenPort = this.getRequest().getParameter("serverListenPort");
        serverStatus="已启动";
        // 将数据类型为Map的List放入一个map中
        Map<String, Object> obj = new HashMap<String, Object>();
        // 转换成json对象传递给前台页面
        obj.put("serverStatus", serverStatus);
        ServerInit serverIniter = (ServerInit) SpringContextHolder.getBean("serverInit");
        logger.debug("启动server端服务，监听端口是:{}",serverListenPort);
        serverIniter.initByCustom(serverListenPort);
        outJson(obj);
    }                                                      

    /**
     * @description server端口监听停止
     * @version
     * @title
     * @author yangfeiit@newchinalife.com
     */
    public void stopServerPort() {
        serverListenPort = this.getRequest().getParameter("serverListenPort");
        serverStatus="已停用";
        // 将数据类型为Map的List放入一个map中
        Map<String, Object> obj = new HashMap<String, Object>();
        // 转换成json对象传递给前台页面
        obj.put("serverStatus", serverStatus);
        //停止批处理服务
        logger.debug("正在停止批处理框架....");
        ServerInit serverIniter = (ServerInit) SpringContextHolder.getBean("serverInit");
        serverIniter.shutdown();
        outJson(obj);
    }

    @Override
    public String getBizId() {
        // TODO Auto-generated method stub
        return null;
    }

    

    
}
