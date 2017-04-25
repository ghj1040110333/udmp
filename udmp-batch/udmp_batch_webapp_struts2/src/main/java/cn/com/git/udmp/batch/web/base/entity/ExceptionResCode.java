package cn.com.git.udmp.batch.web.base.entity;

/** 
 * @description 异常响应码枚举类
 * @author zhangzf_wb@newchinalife.com 
 * @date 2015年7月8日 下午6:10:18  
*/
public enum ExceptionResCode {
    NET_EXCEPTION("006001", "网络异常"),
    STORE_EXCEPTION("006002", "存储异常"),
    MIDDLEWARE_EXCEPTION("007001", "中间件异常"),
    CONTAINER_EXCEPTION("007002", "web容器异常"),
    NULLPOINT_EXCEPTION("008001", "空指针异常"),
    ARITHMETICEXCEPTION("008002", "除数为0异常"),
    PROPERTY_EXCEPTION("009001", "spring、struts配置文件异常"),
    CLASSCAST_EXCEPTION("009002", "类型强转异常"),
    OUTOFBOUND_EXCEPTION("009003", "数组越界异常"),
    SECURITY_EXCEPTION("009004", "安全异常"),
    UNKNOWN_EXCEPTION("005001", "未知异常");
    
    /**
     * @Fields resCode : 异常响应码
     */
    public String resCode;

    /**
     * @Fields resText : 异常响应信息描述
     */
    public String resText;

    /** 
     * <p>Title: 构造器</p> 
     * <p>Description: 异常相应对象构造函数</p> 
     * @param resCode 异常响应码
     * @param resText 异常响应信息描述
    */
    private ExceptionResCode(String resCode, String resText) {
        this.resCode = resCode;
        this.resText = resText;
    }

    @Override
    public String toString() {
        return String.valueOf(this.resCode + " " + this.resText);
    }
}
