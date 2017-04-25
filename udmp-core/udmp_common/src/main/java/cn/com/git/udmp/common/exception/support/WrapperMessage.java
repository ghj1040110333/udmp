package cn.com.git.udmp.common.exception.support;

import java.io.Serializable;

import cn.com.git.udmp.common.exception.ExceptionMessageHelper;


/**
 * 消息传递对象
 * 
 * @author Spring.Cao
 * @version v1.0 2015-12-22
 */
public class WrapperMessage implements Serializable {

    /** UUID */
    private static final long serialVersionUID = 1L;

    /** 信息编码 */
    protected String key;

    /** 信息附带参数 */
    protected Object[] params;

    /**
     * 构造函数
     * 
     * @param key String
     * @param params Object[]
     */
    public WrapperMessage(String key, Object[] params) {
        super();
        this.key = key;
        this.params = params;
    }

    /**
     * 构造函数
     * 
     * @param key String
     */
    public WrapperMessage(String key) {
        super();
        this.key = key;
    }
    
    public String getKey() {
        return key;
    }
    
    public void setKey(String key) {
        this.key = key;
    }
    
    public Object[] getParams() {
        return params;
    }
    
    public void setParams(Object[] params) {
        this.params = params;
    }

    /**
     * 根据错误的key值查找一个value出来。
     * 
     * @param key1 String
     * @return String
     */
    public String getValue(String key1) {
        return ExceptionMessageHelper.getExMsg(key1);
    }
}
