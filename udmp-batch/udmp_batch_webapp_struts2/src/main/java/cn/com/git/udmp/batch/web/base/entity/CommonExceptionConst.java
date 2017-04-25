package cn.com.git.udmp.batch.web.base.entity;

/** 
 * @description 异常信息常量类
 * @author zhangzf_wb@newchinalife.com 
 * @date 2015年7月10日 下午5:19:58  
*/
public class CommonExceptionConst {
    /** 
    * @Fields NULLPOINTEREXCEPTION : 空指针异常
    */ 
    public static final String NULLPOINTEREXCEPTION = "NullPointerException";
    
    /** 
    * @Fields ARITHMETICEXCEPTION : 算术运算异常（包含除数为0的情况） 
    */ 
    public static final String ARITHMETICEXCEPTION = "ArithmeticException";
    
    /** 
    * @Fields ARRAYINDEXOUTOFBOUNDSEXCEPTION : 数组下标越界异常 
    */ 
    public static final String ARRAYINDEXOUTOFBOUNDSEXCEPTION = "ArrayIndexOutOfBoundsException";

    /** 
    * @Fields INDEXOUTOFBOUNDSEXCEPTION :  下标越界异常 :当访问某个序列的索引值小于0或者大于等于序列的大小时，抛出该异常 
    */ 
    public static final String INDEXOUTOFBOUNDSEXCEPTION = "IndexOutOfBoundsException";
    
    /** 
    * @Fields CLASSCASTEXCEPTION : 强制类型转换异常 
    */ 
    public static final String CLASSCASTEXCEPTION = "ClassCastException";
    
    /** 
    * @Fields SECURITYEXCEPTION : 安全异常 ：由安全管理器抛出，用于指示违反安全情况的异常。
    */ 
    public static final String SECURITYEXCEPTION = "SecurityException";
    
    /** 
    * @Fields NOSUCHBEANDEFINITIONEXCEPTION : spring配置文件自动加载找不到相对应的bean异常 
    */ 
    public static final String  NOSUCHBEANDEFINITIONEXCEPTION = "NoSuchBeanDefinitionException";
    
    /** 
    * @Fields NOUNIQUEBEANDEFINITIONEXCEPTION : spring配置文件中的bean不是唯一的异常 
    */ 
    public static final String NOUNIQUEBEANDEFINITIONEXCEPTION = "NoUniqueBeanDefinitionException";
    
    /** 
    * @Fields CONNECTIONEXCEPTION : 该异常发生在客户端进行连接操作时，由于找不到ip或port不正确，找不到ip或ip不存在导致的。 
    */ 
    public static final String CONNECTIONEXCEPTION = "ConnectionException"; 
    
    /** 
    * @Fields SOCKETTIMEOUTEXCEPTION : socket超时的情况下抛出
    */ 
    public static final String SOCKETTIMEOUTEXCEPTION = "SocketTimeoutException";
    
    /** 
    * @Fields BINDEXCEPTION : 该异常发生在服务器端进行bind操作时，与port一样的端口已经被启动 ，被占用时抛出
    */ 
    public static final String BINDEXCEPTION = "BindException";
    
    /** 
    * @Fields SOCKETEXCEPTION : 网络编程常见异常
    */ 
    public static final String SOCKETEXCEPTION = "SocketException";
    
    /** 
    * @Fields ARRAYSTOREEXCEPTION : 数组存储异常：当向数组中存放非数组声明类型对象时抛出 
    */ 
    public static final String ARRAYSTOREEXCEPTION = "ArrayStoreException";
    
    /** 
    * @Fields SERVLETEXCEPTION : servlet异常                                
    */ 
    public static final String SERVLETEXCEPTION = "ServletException";
    
}
