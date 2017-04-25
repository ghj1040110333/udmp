package cn.com.git.udmp.common.enums;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @description 代码操作工具类
 * @author liang
 */
public class TypeUtils {

    private static final String PACKAGE_NAME = "cn.com.git.udmp.common.enums.model";
    /**
     * @description 通过反射机制获取代码枚举对象
     * @param typeName
     *            代码名称
     * @return
     * @throws Exception
     */
    public static Map<String, IType> getCodeClass(String typeName) throws Exception {
        Class obj = getTypeClass(typeName);
        Map<String, IType> typeMap = initialSingleEnumMap(obj);
        return typeMap;
    }
    
    /**
     * @description  根据类型名称获取代码集合，如根据"sex"获取{"1":"man","2":"woman"}
     * @param typeName
     * @return
     * @throws Exception
     */
    public static Map<String,String> getCodeMap(String typeName) throws Exception{
        Map<String, IType> classMap = getCodeClass(typeName);
        Map<String,String> resultMap = new HashMap<String, String>();
        //lambda syntax
        //        classMap.forEach((x,y)->{
//            resultMap.put(y.getId(), y.getValue());
//        });
        
        for(String key:classMap.keySet()){
            IType y = classMap.get(key);
            resultMap.put(y.getId(),y.getValue());
        }
        
        return resultMap;
    }

    /**
     * @param typeName
     *            类型名称
     * @return 获取类型的具体类对象
     */
    private static Class getTypeClass(String typeName) {
        Class obj;
        try {
            obj = TypeEnum.getClassByName(typeName);
            if (obj == null) {
                obj = TypeEnum.getClassByName(typeName.toUpperCase());
            }
            if (obj == null) {
                obj = Class.forName(PACKAGE_NAME +"."+ convertToClassName(typeName));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("代码类反射异常");
        }
        return obj;
    }

    /**
     * @description 根据代码类型和代码ID获取对应代码值
     * @param typeName
     * @param codeId
     * @return
     * @throws Exception
     */
    public static String getValue(String typeName, String codeId) throws Exception {
        Map<String, String> codeMap = getCodeMap(typeName);
        if (codeMap == null) {
            throw new RuntimeException("代码表获取失败");
        }
        return codeMap.get(codeId);
    }

    public static String convertToClassName(String typeName) {
        StringBuffer name = new StringBuffer();
        typeName = typeName.trim();
        name.append(typeName.substring(0, 1).toUpperCase()).append(typeName.substring(1));
        return name.toString();
    }

    /* 加载每个枚举对象数据 **/
    private static Map<String, IType> initialSingleEnumMap(Class<?> cls) throws Exception {
        Method method = cls.getMethod("values");//对枚举类的反射
        IType inter[] = (IType[]) method.invoke(null, null);
        List<IType> typeList = Arrays.asList(inter);
        Map<String, IType> typeMap = new LinkedHashMap<String, IType>();
        //lambda syntax
        //        typeList.stream().sorted((x, y) -> x.getSeq().compareTo(y.getSeq())).forEach(x -> {
//            typeMap.put(x.name(), x);//设置属性id和对应枚举的map集合
//        });
        
        typeList.sort(new Comparator<IType>() {
            @Override
            public int compare(IType x, IType y) {
                return  x.getSeq().compareTo(y.getSeq());
            }
        });
        for(IType type:typeList){
            typeMap.put(type.name(), type);//设置属性id和对应枚举的map集合
        }
        
        return typeMap;
        
    }

    public static void main(String[] args) throws Exception {
        System.out.println(TypeUtils.getCodeMap("color"));
        System.out.println(TypeUtils.getValue("color", "yellow"));
    }

}
