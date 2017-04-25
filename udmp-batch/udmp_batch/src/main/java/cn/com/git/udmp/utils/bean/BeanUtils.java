package cn.com.git.udmp.utils.bean;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.math.NumberUtils;

import cn.com.git.udmp.component.model.CurrentPage;
import cn.com.git.udmp.core.exception.FrameworkRuntimeException;
import cn.com.git.udmp.core.logging.ILog;
import cn.com.git.udmp.utils.lang.DateUtilsEx;
import net.sf.cglib.beans.BeanMap;

/**
 * @description 操作bean的工具类
 * @author chuzm_wb@newchina.live
 * @date 2014年10月20日 上午11:09:33
 */
public class BeanUtils implements ILog{

    /**
     * @Fields supportSqlDateType : 是否支持sqlDate类型
     */
    private static boolean supportSqlDateType;

    /**
     * <p>
     * Title:
     * </p>
     * <p>
     * Description: 构造方法
     * </p>
     */
    private BeanUtils() {
    }

    /**
     * @description 设置是否支持sqlDate类型
     * @version
     * @title
     * @author tanzhiliang tanzl_wb@newchinalife.com
     * @param supportSqlDateType 是否支持sqlDate类型
     */
    public static void setSupportSqlDateType(boolean supportSqlDateType) {
        BeanUtils.supportSqlDateType = supportSqlDateType;
    }

    /**
     * @description 从源bean向目的bean复制属性值（所有的相同的属性全部复制）
     * 
     * @param destBean Object 属性将被修改的目标Bean
     * @param srcBean Object 属性将被取出的源Bean
     */
    public static void copyProperties(Object destBean, Object srcBean) {
        try {
            copyProperties(destBean, srcBean, true);
        } catch (Exception e) {
            throw new FrameworkRuntimeException(e.getMessage());
        }
    }

    /**
     * @description 从源bean向目的bean复制属性值（所有的相同的属性全部复制）
     * @version
     * @title
     * @author wulei_wb wulei_wb@newchinalife.com
     * @param destClass 属性将被修改的目标Bean的class
     * @param srcBean 属性将被取出的源Bean
     * @param <T> Obj
     * @return 目标Bean
     */
    public static <T extends Object> T copyProperties(Class<T> destClass, Object srcBean) {
        try {
            T destBean = destClass.newInstance();
            copyProperties(destBean, srcBean, true);
            return destBean;
        } catch (Exception e) {
            throw new FrameworkRuntimeException(e.getMessage());
        }
    }

    /**
     * @description 将一个bean集合转换成一个新类型的bean集合
     * 
     * @param destClass Class 目标Bean类型
     * @param srcBeans Collection 被克隆的bean集合数据源
     * @throws Exception 异常.
     * @return Collection 目标集合对象
     */
    public static Collection copyCollection(Class destClass, Collection srcBeans) throws Exception {
        return copyCollection(destClass, srcBeans, true);
    }

    /**
     * @description 将一个bean集合转换成一个新类型的bean集合
     * 
     * @param destClass Class 目标Bean类型
     * @param srcBeans Collection 被克隆的bean集合数据源
     * @param nullUpdate 是否更新数据源bean的空属性值
     * @throws Exception 异常.
     * @return Collection 目标集合对象
     */
    public static Collection copyCollection(Class destClass, Collection srcBeans, boolean nullUpdate) throws Exception {

        ArrayList newBeans = null;
        if (srcBeans != null) {
            newBeans = new ArrayList(srcBeans.size());
            Iterator srcBeanList = srcBeans.iterator();
            Object newBean = null;
            Object srcBean = null;
            while (srcBeanList.hasNext()) {
                srcBean = srcBeanList.next();
                if (srcBean instanceof String || srcBean instanceof Double || srcBean instanceof Float
                        || srcBean instanceof Integer || srcBean instanceof Boolean || srcBean instanceof BigDecimal) {
                    newBeans.add(srcBean);
                } else {
                    newBean = destClass.newInstance();
                    copyProperties(newBean, srcBean, nullUpdate);
                    if (newBean != null) {
                        newBeans.add(newBean);
                    }
                }
            }
        }
        return newBeans;
    }

    /**
     * @description 将一个bean集合转换成一个新类型的bean集合
     * @version
     * @title
     * @author wulei_wb wulei_wb@newchinalife.com
     * @param destClass 目标集合Class
     * @param srcBeans 源集合
     * @param <T> Obj
     * @param <M> Obj
     * @return 目标集合
     */
    public static <T extends Object, M extends Object> List<M> copyList(Class<M> destClass, List<T> srcBeans) {
        try {
            return copyList(destClass, srcBeans, true);
        } catch (Exception e) {
        	logger.error("BeanUtils error:",e);
            throw new FrameworkRuntimeException(e.getMessage());
        }
    }

    /**
     * @description 将一个bean集合转换成一个新类型的bean集合
     * @version
     * @title
     * @author wulei_wb wulei_wb@newchinalife.com
     * @param destClass 目标对象Class
     * @param srcBeans 源集合
     * @param nullUpdate 是否忽略空属性值
     * @param <T> Obj
     * @param <M> Obj
     * @return 目标集合
     * @throws Exception 异常
     */
    public static <T extends Object, M extends Object> List<M> copyList(Class<M> destClass, List<T> srcBeans,
            boolean nullUpdate) throws Exception {
        ArrayList newBeans = null;
        if (srcBeans != null) {
            newBeans = new ArrayList(srcBeans.size());
            Iterator srcBeanList = srcBeans.iterator();
            Object newBean = null;
            Object srcBean = null;
            while (srcBeanList.hasNext()) {
                srcBean = srcBeanList.next();
                if (srcBean instanceof String || srcBean instanceof Double || srcBean instanceof Float
                        || srcBean instanceof Integer || srcBean instanceof Boolean || srcBean instanceof BigDecimal) {
                    newBeans.add(srcBean);
                } else {
                    newBean = destClass.newInstance();
                    copyProperties(newBean, srcBean, nullUpdate);
                    if (newBean != null) {
                        newBeans.add(newBean);
                    }
                }
            }
        }
        return newBeans;
    }

    /**
     * @description 多表查询对象互转
     * @version
     * @title
     * @author wulei_wb wulei_wb@newchinalife.com
     * @param destClass 目标对象class
     * @param srcBean 源对象
     * @param <T> Obj
     * @return 目标对象
     */
    public static <T extends Object> T copyMultiProperties(Class<T> destClass, Object srcBean) {
        try {
            T newBean = destClass.newInstance();
            return copyMultiProperties(newBean, srcBean, true);
        } catch (Exception e) {
            throw new FrameworkRuntimeException(e.getMessage());
        }
    }

    /**
     * @description 多表查询对象互转
     * @version
     * @title
     * @author wulei_wb wulei_wb@newchinalife.com
     * @param destObj 目标对象
     * @param srcObj 源对象
     * @param nullUpdate 是否忽略空属性值
     * @param <T> Obj
     * @return 目标对象
     * @throws Exception 异常
     */
    public static <T extends Object> T copyMultiProperties(T destObj, Object srcObj, boolean nullUpdate)
        throws  Exception {
        if (srcObj == null) {
            return null;
        }
        Field[] fields = srcObj.getClass().getDeclaredFields();
        for (Field field : fields) {
            PropertyDescriptor pd = new PropertyDescriptor(field.getName(), srcObj.getClass());
            Method getMethod = pd.getReadMethod();
            Object o = getMethod.invoke(srcObj);
            copyProperties(destObj, o, nullUpdate);
        }
        return destObj;
    }

    /**
     * @description 将一个bean数组转换成一个新类型的bean数组
     * 
     * @param destClass Class 目标Bean类型
     * @param srcBeans Collection 被克隆的bean集合数据源
     * @throws Exception 异常.
     * @return Object 新类型的bean集合
     */
    public static Object copyArray(Class destClass, Object srcBeans) throws Exception {
        return copyArray(destClass, srcBeans, true);
    }

    /**
     * @description 将一个bean数组转换成一个新类型的bean数组
     * 
     * @param destClass 目标Bean类型
     * @param srcBeans Object[] 被克隆的bean数组数据源
     * @param nullUpdate 是否更新数据源bean的空属性值
     * @throws Exception 异常.
     * @return Object[] 目标数组对象
     */
    public static Object copyArray(Class destClass, Object srcBeans, boolean nullUpdate) throws Exception {
        if (srcBeans == null) {
            return null;
        }
        /*
         * if(!destClass.isArray()){ throw new
         * Exception("DestClass is not a Array!"); }
         */
        int length = Array.getLength(srcBeans);
        Object newArray = Array.newInstance(destClass, length);
        Object newBean = null;
        boolean canInitialize = false;
        if (destClass.getPackage() != null && destClass.getPackage().toString().indexOf("com.nci") != -1) {
            Constructor[] cons = destClass.getConstructors();
            for (int i = 0; i < cons.length; i++) {
                if (cons[i].getParameterTypes() == null || cons[i].getParameterTypes().length == 0) {
                    canInitialize = true;
                    break;
                }
            }
        }
        for (int i = 0; i < length; i++) {
            if (destClass.equals(srcBeans.getClass().getComponentType())) {
                if (destClass.isPrimitive() || destClass.equals(Integer.class) || destClass.equals(Long.class)
                        || destClass.equals(Float.class) || destClass.equals(Double.class)
                        || (destClass.equals(String.class)) || (destClass.equals(BigDecimal.class))) {
                    newBean = Array.get(srcBeans, i);
                    if (newBean != null) {
                        Array.set(newArray, i, newBean);
                    }
                } else {
                    if (canInitialize) {
                        newBean = destClass.newInstance();
                        copyProperties(newBean, Array.get(srcBeans, i), nullUpdate);
                        if (newBean != null) {
                            Array.set(newArray, i, newBean);
                        }
                    } else {
                        if (isCloneable(destClass)) {
                            newBean = Array.get(srcBeans, i);
                            Array.set(newArray, i, getCloneMethod(newBean.getClass()).invoke(newBean, null));
                        }
                    }
                }
            } else {
                if (destClass.equals(Integer.class) || destClass.equals(Long.class) || destClass.equals(Float.class)
                        || destClass.equals(Double.class) || (destClass.equals(String.class))
                        || (destClass.equals(BigDecimal.class))) {
                    newBean = convert0(Array.get(srcBeans, i), destClass, null);
                    if (newBean != null) {
                        Array.set(newArray, i, newBean);
                    }
                } else {
                    if (canInitialize) {
                        newBean = destClass.newInstance();
                        copyProperties(newBean, Array.get(srcBeans, i), nullUpdate);
                        if (newBean != null) {
                            Array.set(newArray, i, newBean);
                        }
                    } else {
                        newBean = convert0(Array.get(srcBeans, i), destClass, null);
                        Array.set(newArray, i, newBean);
                    }
                }
            }
        }
        return newArray;
    }

    /**
     * @description 返回特定Bean的特定属性，无论返回的是什么格式的属性都不用类型转换
     * 
     * @param bean 要被提取属性的Bean
     * @param propertyName 要提取的可能有索引或者内嵌的属性名
     * @throws Exception 出现错误.
     * @return Object 属性值
     */
    public static Object getProperty(Object bean, String propertyName) throws Exception {
        Object result = org.apache.commons.beanutils.PropertyUtils.getProperty(bean, propertyName);
        return result;

    }

    /**
     * @description 返回一个Map，包含Bean对象的属性值
     * 
     * @param bean Bean对象
     * @return the 属性Map集合
     * @throws Exception 异常
     */
    public static Map getProperties(Object bean) throws Exception {
        PropertyDescriptor[] propertyDescriptors = org.apache.commons.beanutils.PropertyUtils
                .getPropertyDescriptors(bean);
        PropertyDescriptor propertyDescriptor = null;
        HashMap properties = new HashMap();
        String propName = null;
        for (int i = 0; i < propertyDescriptors.length; i++) {
            propertyDescriptor = propertyDescriptors[i];
            propName = propertyDescriptor.getName();
            properties.put(propName, org.apache.commons.beanutils.PropertyUtils.getSimpleProperty(bean, propName));
        }
        return properties;
    }

    /**
     * @description 将properties文件放到list中，并返回
     * @version
     * @title
     * @author eBaoTech
     * @param beans 对象数组
     * @return 包含所有属性的ArrayList
     * @throws Exception 异常
     */
    public static Collection getPropertyCollection(Object[] beans) throws Exception {
        ArrayList propertyList = new ArrayList();
        for (int i = 0; i < beans.length; i++) {
            Map properties = getProperties(beans[i]);
            propertyList.add(properties);
        }
        return propertyList;
    }

    /**
     * @description 将相同的属性值从一个bean对象复制到另一个对象
     * 
     * @param destObj Object 属性将被修改的目标Bean
     * @param srcObj Object 属性将被取出的源Bean
     * @param nullUpdate 是否忽略空属性值
     * @return Object 返回复制属性后的对象
     * @throws Exception 异常
     */
    public static Object copyProperties(Object destObj, Object srcObj, boolean nullUpdate)
        throws Exception {
        if (srcObj == null || destObj == null) {
            return null;
        }
        try {
            // if(nullUpdate){
            // temp change to use bean map copy
            BeanMap srcBeanMap = BeanMap.create(srcObj);
            BeanMap dstBeanMap = BeanMap.create(destObj);
            Iterator keys = srcBeanMap.keySet().iterator();
            while (keys.hasNext()) {
                Object key = keys.next();
                if ("class".equals(key)) {
                    continue;
                }
                Class dstPropertyType = dstBeanMap.getPropertyType((String) key);
                if (dstPropertyType == null) {
                    continue;
                }
                Class srcPropertyType = srcBeanMap.getPropertyType((String) key);
                // 判断属性是否可比较，如果不可比较，中止本次循环
                if (!isPropertyCompatible(srcPropertyType, dstPropertyType)) {
                    continue;
                }
                Object value = srcBeanMap.get(key);
                if (!nullUpdate && (value == null)) {
                    continue;
                }
                Object convertedValue = convert0(value, dstPropertyType, key);
                dstBeanMap.put(key, convertedValue);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return destObj;
    }

    /**
     * @description 类转换方法
     * @version
     * @title
     * @author eBaoTech
     * @param source 源类
     * @param classToConvert 转换的类
     * @param context 配置文件内容
     * @return 转换后的对象
     */
    public static Object convert0(Object source, Class classToConvert, Object context) {
        // temp disable for the filter program is not work
        String temp = null;
        Object dest = null;
        try {
            if (source != null && classToConvert.equals(source.getClass())) {
                if (source.getClass().getPackage() != null) {
                    if (source.getClass().getPackage().getName().startsWith("cn.com.git.udmp")) {

                        dest = classToConvert.newInstance();
                        BeanUtils.copyProperties(dest, source);
                        return dest;
                    } else {
                        /*
                         * if (source.getClass().equals(Boolean.class) ||
                         * source.getClass().equals(Integer.class) ||
                         * source.getClass().equals(Long.class) ||
                         * source.getClass().equals(Float.class) ||
                         * source.getClass().equals(Double.class))
                         */
                        return source;
                    }
                } else {
                    return source;
                }
            } else if (source != null && classToConvert != null && source.getClass().isArray()) {
                /** @todo: there is bug when copy array */
                return BeanUtils.copyArray(classToConvert.getComponentType(), source);
            } else if (source != null && classToConvert != null && (source instanceof Collection)) {
                if (((Collection) source).size() == 0) {
                    return new ArrayList();
                }
                return BeanUtils
                        .copyCollection(((Collection) source).iterator().next().getClass(), (Collection) source);
            } else if (source != null && classToConvert != null && (source instanceof Map)) {
                return BeanUtils.copyMap(classToConvert, (Map) source);
            } else if (source != null && !classToConvert.isPrimitive()
                    && classToConvert.getPackage().getName().startsWith("com.nci") && !source.getClass().isPrimitive()
                    && source.getClass().getPackage().getName().startsWith("com.nci")) {
                // dest = classToConvert.newInstance();
                if (classToConvert.isAssignableFrom(source.getClass())) {
                    dest = source.getClass().newInstance();
                } else if (!classToConvert.isInterface() && !Modifier.isAbstract(classToConvert.getModifiers())) {
                    dest = classToConvert.newInstance();
                }
                BeanUtils.copyProperties(dest, source);
                return dest;
            } else {
                if (classToConvert.equals(String.class)) {
                    if (source == null) {
                        return null;
                    }
                    if (source instanceof Date) {
                        return DateUtilsEx.date2String((Date) source, null);
                    } else {
                        return source.toString();
                    }
                } else if (classToConvert.equals(Integer.class)) {
                    if (source == null || "".equals(source)) {
                        return null;
                    } else {
                        return NumberUtils.createInteger(source.toString());
                    }
                } else if (classToConvert.equals(Long.class)) {
                    if (source == null || "".equals(source)) {
                        return null;
                    } else {
                        return NumberUtils.createLong(source.toString());
                    }
                } else if (classToConvert.equals(Float.class)) {
                    if (source == null || "".equals(source)) {
                        return null;
                    } else {
                        return NumberUtils.createFloat(source.toString());
                    }
                } else if (classToConvert.equals(Double.class)) {
                    if (source == null || "".equals(source)) {
                        return null;
                    } else {
                        return NumberUtils.createDouble(source.toString());
                    }
                } else if (classToConvert.equals(BigDecimal.class)) {
                    if (source == null || "".equals(source)) {
                        return null;
                    } else {
                        return NumberUtils.createBigDecimal(source.toString());
                    }
                } else if (classToConvert.equals(int.class)) {
                    if (source == null || source.equals("")) {
                        return NumberUtils.createInteger("0");
                    } else {
                        return NumberUtils.createInteger(source.toString());
                    }
                } else if (classToConvert.equals(long.class)) {
                    if (source == null || "".equals(source)) {
                        return NumberUtils.createLong("0");
                    } else {
                        return NumberUtils.createLong(source.toString());
                    }
                } else if (classToConvert.equals(float.class)) {
                    if (source == null || "".equals(source)) {
                        return NumberUtils.createFloat("0");
                    } else {
                        return NumberUtils.createFloat(source.toString());
                    }
                } else if (classToConvert.equals(double.class)) {
                    if (source == null || "".equals(source)) {
                        return NumberUtils.createDouble("0");
                    } else {
                        return NumberUtils.createDouble(source.toString());
                    }
                } else if (classToConvert.equals(BigDecimal.class)) {
                    if (source == null || "".equals(source)) {
                        return NumberUtils.createBigDecimal("0");
                    } else {
                        return NumberUtils.createBigDecimal(source.toString());
                    }
                } else if (classToConvert.equals(Timestamp.class)) {
                    if (source == null || "".equals(source)) {
                        return null;
                    } else if (source instanceof Date) {
                        return new Timestamp(((Date) source).getTime());
                    } else if (source instanceof String) {
                        return new Timestamp(DateUtilsEx.toDate((String) source).getTime());
                    } else {
                        throw new Exception("Unsupported Datatype:" + classToConvert);
                    }
                } else if (classToConvert.equals(Date.class)) {
                    if (source == null) {
                        return null;
                    } else if (source.getClass().equals(Timestamp.class)) {
                        return new Date(((Timestamp) source).getTime());
                    } else if (source.getClass().equals(String.class)) {
                        return DateUtilsEx.toDate((String) source);
                    } else if (source.getClass().equals(java.sql.Date.class) && supportSqlDateType) {

                        return (Date) source;

                    } else {
                        throw new Exception("Unsupported Datatype:" + classToConvert);
                    }
                } else if (classToConvert.equals(boolean.class)) {
                    if (source == null) {
                        return Boolean.FALSE;
                    }
                    if (source instanceof String) {
                        if (logger.isDebugEnabled()) {
                            logger.debug("source:" + source);
                        }
                        temp = (String) source;
                        if (temp.equalsIgnoreCase("y") || temp.equalsIgnoreCase("true")) {
                            return new Boolean(true);
                        } else if (temp.equalsIgnoreCase("n") || temp.equalsIgnoreCase("false")) {
                            return new Boolean(false);
                        }
                    }
                    return new Boolean("true".equals(source.toString()) ? true : false);
                }
            }
        } catch (Exception ex) {
            throw new RuntimeException("convert error," + ex.getMessage() + "-->" + classToConvert + ",source:"
                    + source.getClass() + ",propertyName:" + context, ex);
            // return null;
        }
        // temp disable for filter program not work
        //
        return null;
    }

    /*
     * public Object convert(Object value, Class target) { return
     * convert(target, value,null); }
     */
    /**
     * @description 类转换方法
     * @version
     * @title
     * @author eBaoTech
     * @param source 源类
     * @param classToConvert 转换的类
     * @param context 配置文件内容
     * @return 转换后的对象
     */
    public static Object convert(Object source, Class classToConvert, Object context) {
        return convert0(source, classToConvert, context);
    }

    /**
     * @description 将配置文件置空的方法
     * @version
     * @title
     * @author eBaoTech
     * @param destObj 目标对象
     * @return 置空后的对象
     * @throws Exception 异常
     */
    public static Object setPropertiesNull(Object destObj) throws Exception {

        PropertyDescriptor[] descriptors = PropertyUtils.getPropertyDescriptors(destObj);
        for (int i = 0; i < descriptors.length; i++) {
            String name = descriptors[i].getName();
            if ("class".equals(name)) {
                continue;
            }
            if (PropertyUtils.isWriteable(destObj, name)) {
                Object value = PropertyUtils.getSimpleProperty(destObj, name);
                setPropertyValue(destObj, name, null, null);
            }
        }
        return destObj;

    }

    /**
     * @description 设置属性值
     * 
     * @param targetBean 目标对象
     * @param propName 属性名 将会被转化为set方法的名字
     * @param propValue 属性值
     * @throws Exception 异常
     */
    public static void setPropertyValue(Object targetBean, String propName, Object propValue)
        throws Exception {
        setPropertyValue(targetBean, propName, propValue, null);

    }

    /**
     * @description 设置属性值
     * 
     * @param targetBean 目标对象
     * @param propName 属性名 将会被转化为set方法的名字
     * @param propValue 属性值
     * @param sourceBean 源对象
     * @throws Exception 异常
     */
    private static void setPropertyValue(Object targetBean, String propName, Object propValue, Object sourceBean)
        throws Exception {
        // temp disable for filter not work
        // Object detail = monitor.startProfileDetail("setPropertyValue 4
        // para");

        int delimiterPos = propName.indexOf(".");
        if (delimiterPos == -1) {
            PropertyDescriptor descriptor = PropertyUtils.getPropertyDescriptor(targetBean, propName);
            Method writeMethod = PropertyUtils.getWriteMethod(descriptor);
            if (writeMethod != null) {
                Class[] paramTypes = writeMethod.getParameterTypes();
                Object[] args = new Object[1];

                /*
                 * if (paramTypes[0].isArray()) { args[0] = convert0(propValue,
                 * paramTypes[0], propName); } else { args[0] =
                 * convert0(propValue, paramTypes[0], propName); }
                 */
                args[0] = convert0(propValue, paramTypes[0], propName);
                writeMethod.invoke(targetBean, args);
            }
        }
        // temp disable for filter not work
        //

    }

    /**
     * copy data from a map to a javabean object
     * 
     * @param destClass dest obj class
     * @param source map source
     * @return javabean obj
     * @throws Exception java.lang.Exception
     */
    private static Object copyMap(Class destClass, Map source) throws Exception {
        if (destClass.equals(java.util.HashMap.class) || destClass.equals(java.util.Map.class)) {
            HashMap dest = new HashMap();
            dest.putAll(source);
            return dest;
        } else {
            Object destObj = destClass.newInstance();
            Iterator names = source.keySet().iterator();
            while (names.hasNext()) {
                String name = (String) names.next();
                if (PropertyUtils.isWriteable(destObj, name)) {
                    Object value = source.get(name);
                    setPropertyValue(destObj, name, value, null);
                }
            }
            return destObj;
        }

    }

    /**
     * @description 从一个 map拷贝数据到一个javabean对象
     * 
     * @param destObj 目标对象
     * @param source map数据源
     * @return javabean对象
     * @throws Exception 异常
     */
    public static Object copyMap(Object destObj, Map source) throws Exception {
        Iterator names = source.keySet().iterator();
        while (names.hasNext()) {
            String name = (String) names.next();
            if (PropertyUtils.isWriteable(destObj, name)) {
                Object value = source.get(name);
                setPropertyValue(destObj, name, value, null);
            }
        }
        return destObj;
    }

    /**
     * @description 是否可以复制
     * @version
     * @title
     * @author eBaoTech
     * @param clazz 类类型
     * @return 该类是否可以复制
     */
    private static boolean isCloneable(Class clazz) {
        Method[] methods = clazz.getMethods();
        for (int i = 0; i < methods.length; i++) {
            if (methods[i].getName().equals("clone")
                    && (methods[i].getParameterTypes() == null || methods[i].getParameterTypes().length == 0)) {
                return Modifier.isPublic(methods[i].getModifiers());
            }
        }
        return false;
    }

    /**
     * @description 获得复制类的方法
     * @version
     * @title
     * @author eBaoTech
     * @param clazz 类类型
     * @return 方法
     */
    private static Method getCloneMethod(Class clazz) {
        Method[] methods = clazz.getMethods();
        for (int i = 0; i < methods.length; i++) {
            if (methods[i].getName().equals("clone")
                    && (methods[i].getParameterTypes() == null || methods[i].getParameterTypes().length == 0)) {
                return methods[i];
            }
        }
        return null;
    }

    /**
     * @description 打印 bean
     * @version
     * @title
     * @author eBaoTech
     * @param bean bean对象
     * @return string 打印结果
     */
    public static String printBean(Object bean) {
        String result = ToStringBuilder.reflectionToString(bean);
        return result;
    }

    /**
     * @description 反射是否相同
     * @version
     * @title
     * @author eBaoTech
     * @param origin 初始对象
     * @param compare 对比的对象
     * @return 是否相同
     */
    public static boolean reflectionEquals(Object origin, Object compare) {
        boolean result = EqualsBuilder.reflectionEquals(origin, compare);
        return result;

    }

    /**
     * @description 反射hashcode
     * @version
     * @title
     * @author eBaoTech
     * @param bean bean对象
     * @return hashcode值
     */
    public static int reflectionHashCode(Object bean) {
        //
        int result = HashCodeBuilder.reflectionHashCode(bean);
        //
        return result;
    }

    /**
     * 从ServletRequest中复制值给一个对象
     * 
     * @param bean 目标bean对象
     * @param request ServletRequest请求对象
     * @return Object 复制数据后的对象
     * @throws Exception 异常
     */
    public static Object copyObject(Object bean, ServletRequest request) throws Exception {
        // get all parameters
        Enumeration paramNames = request.getParameterNames();
        // parameter name
        String paramName = null;
        // parameter value
        Object paramValue = null;
        // parameter values
        String[] paramValues = null;

        Map properties = new HashMap();
        while (paramNames.hasMoreElements()) {
            paramName = (String) paramNames.nextElement();
            paramValues = request.getParameterValues(paramName);
            switch (paramValues.length) {
                case 0:
                    paramValue = null;
                    break;
                case 1:
                    paramValue = paramValues[0];
                    if (paramValue != null && paramValues[0].length() == 0) {
                        paramValue = null;
                    }
                    break;
                default:
                    paramValue = paramValues;
            }
            if (paramValue != null) {
                properties.put(paramName, paramValue);
            }
        } // end while

        org.apache.commons.beanutils.BeanUtils.populate(bean, properties);
        return bean;
    }

    /**
     * @description 将对象的属性放入一个Map中
     * @version
     * @title
     * @author ebao
     * @param obj 对象
     * @return Map对象
     */
    public static Map getMap(Object obj) {

        Map result = null;
        try {
            result = PropertyUtils.describe(obj);
        } catch (IllegalAccessException e) {
            result = new HashMap();
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            result = new HashMap();
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            result = new HashMap();
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @description 属性是否可以比较
     * @version
     * @title
     * @author eBaoTech
     * @param srcClass 源类
     * @param dstClass 目的类
     * @return 属性是否可以比较
     */
    private static boolean isPropertyCompatible(Class srcClass, Class dstClass) {
        if (srcClass == null || dstClass == null) {
            return false;
        }
        if (srcClass.isArray() && !dstClass.isArray()) {
            return false;
        }
        if (!srcClass.isArray() && dstClass.isArray()) {
            return false;
        }
        return true;
    }
    
    /**
     * @description 分页信息currentPage对象互转
     * @version
     * @title
     * @author wulei_wb wulei_wb@newchinalife.com
     * @param destClass 目标对象Class
     * @param srcBeans 源对象
     * @param <T> Obj
     * @param <M> Obj
     * @return 目标对象CurrentPage
     */
    public static <T extends Object, M extends Object> CurrentPage<M> copyCurrentPage(Class<M> destClass,
            CurrentPage<T> srcBeans) {
        try {
            return copyCurrentPage(destClass, srcBeans, true);
        } catch (Exception e) {
            throw new FrameworkRuntimeException(e.getMessage());
        }
    }
    
    /**
     * @description 分页信息currentPage对象互转
     * @version
     * @title
     * @author wulei_wb wulei_wb@newchinalife.com
     * @param destClass 目标对象Class
     * @param srcBeans 源对象
     * @param nullUpdate 是否忽略空属性值
     * @param <T> Obj
     * @param <M> Obj
     * @return 目标对象CurrentPage
     * @throws Exception 异常
     */
    public static <T extends Object, M extends Object> CurrentPage<M> copyCurrentPage(Class<M> destClass,
            CurrentPage<T> srcBeans, boolean nullUpdate) throws Exception {
        List<T> pageItems = srcBeans.getPageItems();
        CurrentPage<M> result = new CurrentPage<M>();
        // copyProperties(result, srcBeans, nullUpdate);
        result.setPageNo(srcBeans.getPageNo());
        result.setPageSize(srcBeans.getPageSize());
        result.setTotal(srcBeans.getTotal());
        result.setMaxSize(srcBeans.getMaxSize());
        result.setPageTotal(srcBeans.getPageTotal());
        result.setPageItems(copyList(destClass, pageItems, nullUpdate));
        if (srcBeans.getParamObject() != null) {
            M dest = destClass.newInstance();
            copyProperties(dest, srcBeans.getParamObject(), nullUpdate);
            result.setParamObject(dest);
        }
        return result;
    }
}
