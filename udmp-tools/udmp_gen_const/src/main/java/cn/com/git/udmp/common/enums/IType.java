package cn.com.git.udmp.common.enums;

import java.util.Map;

/**
 * @description 代码表的操作接口
 * @author liang 
 *
 */
public interface IType{
    
    
    public String name();
    
    public String getSeq();
   
    public String getId();
    
    public String getValue();
    
    /**
     * @return 获取代码id与代码值对应的map集合
     */
    public Map<String,String> getType();
    
    /**
     * @param id
     * @return 根据代码ID获取代码值
     */
    public String getValueById(String id);
    
    /**
     * @param value 
     * @return 根据代码值获取对应ID
     */
    public String getIdByValue(String value);
}
