package cn.com.git.udmp.component.batch.event.alert.impl;
//package cn.com.git.udmp.component.batch.event.alert.impl;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import cn.com.git.udmp.component.batch.event.alert.AlertMsg;
//import cn.com.git.udmp.component.sms.model.smsfile.SmsFile;
//import cn.com.git.udmp.component.sms.model.webservicemessage.WebServiceMessage;
//import cn.com.git.udmp.component.sms.webserviceconfig.WebServiceConfig;
//import cn.com.git.udmp.framework.spring.SpringContextHolder;
//import cn.com.git.udmp.util.sms.SMSUtil;
//
///**
// * 短信报警代理对象，报警代理对象响应报警动作，但不做任何操作
// * @description 短信报警代理对象，报警代理对象响应报警动作，但不做任何操作
// * @author bihb bihb_wb@newchinalife.com
// * @date 2015年4月13日 下午4:12:35  
// */
//public class SmsAlertProxy extends BaseAlertProxy implements IAlertProxy {
//
//    /**
//     * @description 报警操作
//     * @version 1.0
//     * @title 报警操作
//     * @author bihb bihb_wb@newchinalife.com
//     * @see cn.com.git.udmp.component.batch.event.alert.impl.IAlertProxy#alert(cn.com.git.udmp.component.batch.event.alert.AlertMsg)
//     * @param msg 报警操作
//     */
//    @Override
//    public void alert(AlertMsg msg) {
//        //如果是启用状态，那么进行报警操作
//        if (isEnable()) {
//            //与模板的merge操作，然后进行报警操作,短信内容按需进行术语格式化，暂时保留
//            //String alertMsg = VelocityUtils.getMergeResultFromTemplate(velocityConfig, contextkey, msg);
//            try {
//                Thread.sleep(getAlertDelaySecond() * TIMES);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            //获取udmp中默认的短信服务配置
//            WebServiceConfig webserviceConfig = (WebServiceConfig) SpringContextHolder.getBean("webServiceConfig");
//            //设置短信工具的上下文（主要是短信服务的用户名、密码、ip、端口等）
//            SMSUtil.setWebSrvConfig(webserviceConfig);
//            //短信数据类型赋值
//            SmsFile smsMessage = setSmsMessage(msg);
//            //短信xml模板转换
//            String xmlSmsMessages = SMSUtil.assemblingSms(smsMessage);
//            boolean sendFlag = SMSUtil.sendSmsByWebService(xmlSmsMessages);
//            if (sendFlag) {
//                getLogger().info("短信报警发送成功。");
//            } else {
//                getLogger().info("短信报警发送失败。");
//            }
//            
//        }
//
//    }
//
//    /**
//     * 创建短信对象
//     * @description 创建短信对象
//     * @version 1.0
//     * @title 创建短信对象
//     * @author bihb bihb_wb@newchinalife.com
//     * @param msg 报警消息
//     * @return 报警短信对象
//     */
//    private SmsFile setSmsMessage(AlertMsg msg) {
//        SmsFile smsMessage = new SmsFile();
//        //目前保留测试数据,设置头文件
//        smsMessage.setOrganization("85");
//        smsMessage.setServiceType("JRZH");
//        smsMessage.setExtension(false);
//        smsMessage.setStartDate("2014-08-20");
//        smsMessage.setEndDate("2014-08-20");
//        smsMessage.setStartTime("09:00");
//        smsMessage.setEndTime("21:00");
//        
//        List<WebServiceMessage> messages = new ArrayList<WebServiceMessage>();
//        
//        WebServiceMessage webServiceMessage = new WebServiceMessage();
//        webServiceMessage.setReceiver("18510381586");
//        webServiceMessage.setContents(" this is a test sms ");
//        webServiceMessage.setOrgCode("63");
//        messages.add(webServiceMessage);
//        
//        webServiceMessage = new WebServiceMessage();
//        webServiceMessage.setReceiver("13988888888");
//        webServiceMessage.setContents(" this is a test sms ");
//        webServiceMessage.setOrgCode("63");
//        messages.add(webServiceMessage);
//        
//        //添加短信清单列表
//        smsMessage.setMessages(messages);
//        return smsMessage;
//    }
//
//}
