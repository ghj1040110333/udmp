package cn.com.git.udmp.component.batch.event.alert.impl;
//package cn.com.git.udmp.component.batch.event.alert.impl;
//
//import cn.com.git.udmp.component.batch.event.alert.AlertMsg;
//import cn.com.git.udmp.component.mail.mailinfo.MailInfo;
//import cn.com.git.udmp.component.mail.serverconfig.MailServerConfig;
//import com.nci.udmpa.framework.context.AppUser;
//import cn.com.git.udmp.framework.context.AppUserContext;
//import cn.com.git.udmp.framework.spring.SpringContextHolder;
//import cn.com.git.udmp.util.mail.MailUtil;
//
///**
// * 邮件报警代理对象，报警代理对象响应报警动作，但不做任何操作
// * 
// * @description 邮件报警代理对象，报警代理对象响应报警动作，但不做任何操作
// * @author bihb bihb_wb@newchinalife.com
// * @date 2015年4月13日 下午4:12:35
// */
//public class MailAlertProxy extends BaseAlertProxy implements IAlertProxy {
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
//        // 如果是启用状态，那么进行报警操作
//        if (isEnable()) {
//            // 与模板的merge操作，然后进行报警操作
//            // String alertMsg =
//            // VelocityUtils.getMergeResultFromTemplate(velocityConfig,
//            // contextkey, msg);
//            try {
//                Thread.sleep(getAlertDelaySecond() * TIMES);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            // 由于都是异步线程处理，对有需要使用appuser上下文的进行默认批处理用户设置，供某些审计功能使用
//            setUserContext();
//            // 获取udmp的基础公共邮件配置，设置邮件工具上线问
//            MailServerConfig mailServerConfig = (MailServerConfig) SpringContextHolder.getBean("mailServerConfig");
//            MailUtil.createMailSession(mailServerConfig);
//            // 邮件数据赋值
//            MailInfo mailInfo = setMailMessage(msg);
//            // 发送邮件
//            boolean sendFlag = MailUtil.sendTextMail(mailInfo);
//            if (sendFlag) {
//                getLogger().info("邮件报警发送成功。");
//            } else {
//                getLogger().info("邮件报警发送失败。");
//            }
//        }
//
//    }
//
//    /**
//     * 由于都是异步线程处理，对有需要使用appuser上下文的进行默认批处理用户设置，供某些审计功能使用
//     * 
//     * @description 由于都是异步线程处理，对有需要使用appuser上下文的进行默认批处理用户设置，供某些审计功能使用
//     * @version 1.0
//     * @title 由于都是异步线程处理，对有需要使用appuser上下文的进行默认批处理用户设置，供某些审计功能使用
//     * @author bihb bihb_wb@newchinalife.com
//     */
//    private void setUserContext() {
//        // 由于都是异步线程处理，对有需要使用appuser上下文的进行默认批处理用户设置，供某些审计功能使用
//        AppUser user = new AppUser();
//        // 默认批处理使用的公共userid=1000000000；10位数字
//        user.setUserId(1000000000);
//        user.setUserName("batch_user");
//        AppUserContext.setCurrentUser(user);
//    }
//
//    /**
//     * 创建邮件对象
//     * 
//     * @description 创建邮件对象
//     * @version 1.0
//     * @title 创建邮件对象
//     * @author bihb bihb_wb@newchinalife.com
//     * @param msg 报警消息
//     * @return 报警短信对象
//     */
//    private MailInfo setMailMessage(AlertMsg msg) {
//        // 测试数据
//        MailInfo mailInfo = new MailInfo("liuliang_wb@newchinalife.com", "bihb_wb@newchinalife.com", "邮件主题-------",
//                "邮件内容！54646546456");
//        return mailInfo;
//    }
//
//}
