//package cn.com.git.udmp.batch.web.controller;
//
//import java.net.InetAddress;
//import java.net.UnknownHostException;
//import java.util.HashMap;
//import java.util.Map;
//
//import cn.com.git.udmp.batch.web.base.FrameworkBaseAction;
//import cn.com.git.udmp.common.utils.SpringContextHolder;
//import cn.com.git.udmp.component.batch.core.ServerInit;
//
///**
// * @ClassName: BatchAgentPortAction
// * @Description: 代理监听端口启停
// * @author yangfeiit@newchinalife.com
// * @date 2015-06-10 下午4:13:25
// */
//
//public class BatchAgentPortAction extends FrameworkBaseAction {
//    private static final long serialVersionUID = 1L;
//    private static String agentListenPort;
//    private static String agentStatus;
//    private String agentPortStatus;
//    private String localHost;
//
//    public static String getAgentListenPort() {
//        return agentListenPort;
//    }
//
//    public static void setAgentListenPort(String agentListenPort) {
//        BatchAgentPortAction.agentListenPort = agentListenPort;
//    }
//
//    public static String getAgentStatus() {
//        return agentStatus;
//    }
//
//    public static void setAgentStatus(String agentStatus) {
//        BatchAgentPortAction.agentStatus = agentStatus;
//    }
//
//    public String getLocalHost() {
//        return localHost;
//    }
//
//    public void setLocalHost(String localHost) {
//        this.localHost = localHost;
//    }
//
//    public String getAgentPortStatus() {
//        return agentPortStatus;
//    }
//
//    public void setAgentPortStatus(String agentPortStatus) {
//        this.agentPortStatus = agentPortStatus;
//    }
//
//    /**
//     * @description 打开主页面
//     * @version
//     * @title
//     * @author yangfeiit@newchinalife.com
//     * @return 主页面
//     */
//    public String showBatchAgentPortIndexPage() {
//        try {
//            agentPortStatus = agentStatus;
//            localHost = InetAddress.getLocalHost().getHostAddress().toString();
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//        }
//        return "showBatchAgentPortIndexPage";
//    }
//
//    /**
//     * @description 代理端口监听启动
//     * @version
//     * @title
//     * @author yangfeiit@newchinalife.com
//     */
//    public void startAgentPort() {
//        agentListenPort = this.getRequest().getParameter("agentListenPort");
//        agentStatus = "已启动";
//        // 将数据类型为Map的List放入一个map中
//        Map<String, Object> obj = new HashMap<String, Object>();
//        // 转换成json对象传递给前台页面
//        obj.put("agentStatus", agentStatus);
//        //启动agent的批处理模块
//        logger.debug("启动agent的批处理模块，端口为{}",agentListenPort);
//        AgentSocketLoader agentSocketLoader = (AgentSocketLoader) SpringContextHolder.getBean("agentSocketLoader");
//        agentSocketLoader.setPort(agentListenPort);
//        ServerInit agentServerInit = (ServerInit) SpringContextHolder.getBean("agentServerInit");
//        agentServerInit.init();
//        logger.debug("agent的批处理模块启动结束");
//        outJson(obj);
//
//    }
//
//    /**
//     * @description 代理端口监听停止
//     * @version
//     * @title
//     * @author yangfeiit@newchinalife.com
//     */
//    public void stopAgentPort() {
//        agentListenPort = this.getRequest().getParameter("agentListenPort");
//        agentStatus = "已停用";
//        // 将数据类型为Map的List放入一个map中
//        Map<String, Object> obj = new HashMap<String, Object>();
//        // 转换成json对象传递给前台页面
//        obj.put("agentStatus", agentStatus);
//        //停止agent的批处理模块
//        logger.debug("停止agent的批处理模块，端口为{}",agentListenPort);
//        ServerInit agentServerInit = (ServerInit) SpringContextHolder.getBean("agentServerInit");
//        agentServerInit.shutdown();
//        logger.debug("agent的批处理模块停止结束");
//        
//        outJson(obj);
//    }
//
//    @Override
//    public String getBizId() {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//}
