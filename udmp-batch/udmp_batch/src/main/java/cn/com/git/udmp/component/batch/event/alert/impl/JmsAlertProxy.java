package cn.com.git.udmp.component.batch.event.alert.impl;
//package cn.com.git.udmp.component.batch.event.alert.impl;
//
//import org.springframework.web.servlet.view.velocity.VelocityConfig;
//
//import cn.com.git.udmp.component.batch.event.alert.AlertMsg;
//
///**
// * 监控平台jms报警代理对象，报警代理对象响应报警动作，但不做任何操作
// * @description 监控平台jms报警代理对象，报警代理对象响应报警动作，但不做任何操作
// * @author bihb bihb_wb@newchinalife.com
// * @date 2015年4月13日 下午4:12:35  
// */
//public class JmsAlertProxy extends BaseAlertProxy implements IAlertProxy {
//    /**
//     * @Fields jmsTemplate : jmsTemplate,用来发送jms消息
//     */ 
//    private JmsTemplate jmsTemplate;
//
//    /**
//     * 监控平台报警操作</br>
//     * <p>接口数据内容:</p>
//     * <p>监控消息采用分隔符形式报文格式。报文域按顺序排列，域之间用“,”分隔，最后一域无分隔符，以16进制0x00为结束符。</p>
//     * <p>消息编码：UTF-8</p>
//     * <p>消息类型：binary</p>
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
//            //与模板的merge操作，然后进行报警操作
//            VelocityConfig velocityConfig = new VelocityConfig();
//            velocityConfig.setTemplateName(getAlertTemplate());
//
//            //测试模板对应jms报文顺序
////            msg = new AlertMsg();
////            msg.setBizId("bizId");
////            msg.setChildSRVCode("childSRVCode");
////            msg.setChildSRVOwner("childSRVOwner");
////            msg.setInvokeTime("invokeTime");
////            msg.setMsgDate("msgDate");
////            msg.setMsgId("msgId");
////            msg.setMsgTime("msgTime");
////            msg.setOrgCd("orgCd");
////            msg.setReplyTime("replyTime");
////            msg.setRequestTime("requestTime");
////            msg.setResCd("resCd");
////            msg.setResponseTime("responseTime");
////            msg.setResText("resText");
////            msg.setServCd("servCd");
////            msg.setServerName("serverName");
////            msg.setSysCd("sysCd");
//            String alertMsg = VelocityUtils.getMergeResultFromTemplate(velocityConfig, TEMPLATE_VAR_HOLDER, msg);
//            jmsTemplate.convertAndSend(alertMsg);
//            System.out.println("-------------异常信息为：------------");
//            System.out.println(alertMsg);
//            try {
//                Thread.sleep(getAlertDelaySecond() * TIMES);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            getLogger().info("监控平台jms报警完成，监控信息:" + alertMsg);
//        }
//
//    }
//
//    /**
//     * 返回jmsTemplate的属性值
//     * @description 返回jmsTemplate的属性值
//     * @version 1.0
//     * @title 返回jmsTemplate的属性值
//     * @author bihb bihb_wb@newchinalife.com
//     * @return 返回获取jmsTemplate的属性值
//     */
//    public JmsTemplate getJmsTemplate() {
//        return jmsTemplate;
//    }
//
//    /**
//     * @description 给jmsTemplate属性赋值
//     * @version 1.0
//     * @title 给jmsTemplate属性赋值
//     * @author bihb bihb_wb@newchinalife.com
//     * @param jmsTemplate jmsTemplate属性 
//     */
//    public void setJmsTemplate(JmsTemplate jmsTemplate) {
//        this.jmsTemplate = jmsTemplate;
//    }
//
//}
