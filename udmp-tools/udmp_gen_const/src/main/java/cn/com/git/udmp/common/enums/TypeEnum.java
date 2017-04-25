package cn.com.git.udmp.common.enums;

import java.util.HashMap;
import java.util.Map;

import cn.com.git.udmp.common.enums.model.Type1;

/**
 * @description 枚举名称与对应class的关系
 * @author liang
 *
 */
public enum TypeEnum {
    // Map<String,String> map = new HashMap<>();
    TYPE1(Type1.class);
   Class clazz;
   private static  Map<String, Class> map = new HashMap<String, Class>();

    TypeEnum(Class _clazz) {
        clazz = _clazz;
    }
    
    static{
        for(TypeEnum key:TypeEnum.values()){
            map.put(key.name(), key.clazz);
        }
    }
    
    /**
     * @description 根据名称获取对应的class类
     * @param name 代码类型名称
     * @return 代码类型枚举类
     */
    public static Class getClassByName(String name){
        return map.get(name);
    }
    
    
    
    
}
