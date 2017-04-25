package cn.com.git.udmp.common.persistence;

import java.util.HashMap;
import java.util.Map;

/**
 * Oracle数据库字段类型与Java类型转换
 * 
 * @author Administrator
 *
 */
public class OracleColTypeHelper {
    /** 
     * @Fields JAVA_STRING_FULL_TYPE : java中字符串类型的全称 
     */ 
     public static final String JAVA_STRING_FULL_TYPE = "java.lang.String";
     /** 
     * @Fields JAVA_DATE_FULL_TYPE : java中日期类型的全称  
     */ 
     public static final String JAVA_DATE_FULL_TYPE = "java.util.Date";
     /** 
     * @Fields JAVA_BIGDECIMAL_FULL_TYPE : java中bigDecimal类型的全称  
     */ 
     public static final String JAVA_BIGDECIMAL_FULL_TYPE = "java.math.BigDecimal";
     /** 
     * @Fields JAVA_INTEGER_FULL_TYPE : java中整型的类型全称  
     */ 
     public static final String JAVA_INTEGER_FULL_TYPE = "java.lang.Integer";
     /** 
     * @Fields JAVA_STRING_SHORT_TYPE : java中字符串的类型缩写  
     */ 
     public static final String JAVA_STRING_SHORT_TYPE = "String";
     /** 
     * @Fields JAVA_DATE_SHORT_TYPE : java中日期的类型缩写 
     */ 
     public static final String JAVA_DATE_SHORT_TYPE = "Date";
     /** 
     * @Fields JAVA_BIGDECIMAL_SHORT_TYPE : java中BigDecimal的类型缩写 
     */ 
     public static final String JAVA_BIGDECIMAL_SHORT_TYPE = "BigDecimal";
     /** 
     * @Fields JAVA_INTEGER_SHORT_TYPE : java中整型的类型缩写 
     */ 
     public static final String JAVA_INTEGER_SHORT_TYPE = "Integer";
     /** 
     * @Fields ORACLE_NUMBER : oracle中的numbwe类型 
     */ 
     public static final String ORACLE_NUMBER = "NUMBER";
     /** 
     * @Fields ORACLE_VARCHAR2 : oracle中的varchar2类型 
     */ 
     public static final String ORACLE_VARCHAR2 = "VARCHAR2";
     /** 
     * @Fields ORACLE_DATE : oracle中的日期类型
     */ 
     public static final String ORACLE_DATE = "DATE";
     /** 
     * @Fields ORACLE_BINARY_FLOAT : oracle中的binary_float类型 
     */ 
     public static final String ORACLE_BINARY_FLOAT = "BINARY_FLOAT";
     /** 
     * @Fields ORACLE_BINARY_DOUBLE : oracle中的binary_double类型 
     */ 
     public static final String ORACLE_BINARY_DOUBLE = "BINARY_DOUBLE";
     /** 
     * @Fields ORACLE_TIMESTAMP : oracle中的timestamp类型
     */ 
     public static final String ORACLE_TIMESTAMP = "TIMESTAMP";
     /** 
     * @Fields ORACLE_CHAR : oracle中的char类型 
     */ 
     public static final String ORACLE_CHAR = "CHAR";

     /** 
     * @Fields GET_STRING : getString字符串 
     */ 
     public static final String GET_STRING = "getString";
     /** 
     * @Fields GET_BIGDECIMAL : getBigDecimal字符串
     */ 
     public static final String GET_BIGDECIMAL = "getBigDecimal";
     /** 
     * @Fields GET_INT : getInt字符串 
     */ 
     public static final String GET_INT = "getInt";
     /** 
     * @Fields GET_SQL_DATE : getSqlDate字符串 
     */ 
     public static final String GET_SQL_DATE = "getSqlDate";

     /** 
     * @Fields SET_STRING : setString字符串 
     */ 
     public static final String SET_STRING = "setString";
     /** 
     * @Fields SET_BIGDECIMAL : setBigDecimal字符串 
     */ 
     public static final String SET_BIGDECIMAL = "setBigDecimal";
     /** 
     * @Fields SET_INT : setInt字符串 
     */ 
     public static final String SET_INT = "setInt";
     /** 
     * @Fields SET_SQL_DATE : setSqlDate字符串 
     */ 
     public static final String SET_SQL_DATE = "setSqlDate";
     /** 
     * @Fields ORACLE_LEFT_TRIM_FUNC : Ltrim函数 
     */ 
     public static final String ORACLE_LEFT_TRIM_FUNC = "LTRIM";
     /** 
     * @Fields ORACLE_RIGHT_TRIM_FUNC : Rtrim函数
     */ 
     public static final String ORACLE_RIGHT_TRIM_FUNC = "RTRIM";
     /** 
     * @Fields ORACLE_UPPER_FUNC : oracle中upper函数
     */ 
     public static final String ORACLE_UPPER_FUNC = "UPPER";
     /** 
     * @Fields TO_TIME_STAMP : to_timestamp函数 
     */ 
     public static final String TO_TIME_STAMP = "TO_TIMESTAMP";

     private static Map<String, String> javaTypeToJavaShortType = new HashMap<String, String>();

     static {
         javaTypeToJavaShortType.put(JAVA_STRING_FULL_TYPE, JAVA_STRING_SHORT_TYPE);
         javaTypeToJavaShortType.put(JAVA_DATE_FULL_TYPE, JAVA_DATE_SHORT_TYPE);
         javaTypeToJavaShortType.put(JAVA_BIGDECIMAL_FULL_TYPE, JAVA_BIGDECIMAL_SHORT_TYPE);
         javaTypeToJavaShortType.put(JAVA_INTEGER_FULL_TYPE, JAVA_INTEGER_SHORT_TYPE);
     }

     /**
      * 获取java 类型的short类型 ,如java.lang.String 到String
      * 
      * @param fullJavaType java数据类型的全称
      * @return java数据类型的缩写
      */
     public static String getJavaShortType(String fullJavaType) {
         return javaTypeToJavaShortType.get(fullJavaType);
     }

     /**
      * 根据oracle类型获取java 类型的full类型 ,如VARCHAR2 到java.lang.String
      * 
      * @param oracleType oracle中的数据类型
      * @return java对应的数据类型的全称
      */
     public static String getJavaFullType(String oracleType) {

         String result = "";
         if (null != oracleType && oracleType.length() > 0) {
             oracleType = oracleType.trim().toUpperCase();
             String[] arr = oracleType.split("_");
//             String dataSize = arr[1];
//             String digit = arr[2];
             if (ORACLE_NUMBER.equals(arr[0])) {
                 // if(null != dataSize && dataSize.length() >0 ){
                 // dataSize = dataSize.trim();
                 // int dataSizz = Integer.valueOf(dataSize);
                 // int digits =0;
                 // if(null != digit && digit.length()>0){
                 // digit=digit.trim();
                 // digits = Integer.valueOf(digit);
                 // }
                 // if(dataSizz <10 && digits ==0 ){
                 // result = JAVA_INTEGER_FULL_TYPE;
                 //
                 // }else{
                 // result = JAVA_BIGDECIMAL_FULL_TYPE;
                 // }
                 // }
                 result = JAVA_BIGDECIMAL_FULL_TYPE;
             }

             if (ORACLE_VARCHAR2.equals(arr[0])) {
                 result = JAVA_STRING_FULL_TYPE;
             }

             if (ORACLE_DATE.equals(arr[0])) {
                 result = JAVA_DATE_FULL_TYPE;
             }

             if (ORACLE_BINARY_FLOAT.equals(arr[0])) {
                 return JAVA_BIGDECIMAL_FULL_TYPE;
             }

             if (ORACLE_BINARY_DOUBLE.equals(arr[0])) {
                 result = JAVA_BIGDECIMAL_FULL_TYPE;
             }

             if (ORACLE_TIMESTAMP.equals(arr[0]) || arr[0].startsWith(ORACLE_TIMESTAMP)) {
                 result = JAVA_DATE_FULL_TYPE;
             }

             if (ORACLE_CHAR.equals(arr[0])) {
                 result = JAVA_STRING_FULL_TYPE;
             }
         }

         return result;
     }

     /**
      * 根据oracle类型获取java 类型的full类型 ,如VARCHAR2 到java.lang.String
      * 
      * @param oracleType oracle中的数据类型
      * @return 对应的java数据类型
      */
     public static String getJavaFullTypeByOracleShortType(String oracleType) {
         String result = "";
         if (null != oracleType && oracleType.length() > 0) {
             oracleType = oracleType.trim().toUpperCase();
             if (ORACLE_NUMBER.equals(oracleType)) {
                 result = JAVA_INTEGER_FULL_TYPE;
             }
             if (ORACLE_VARCHAR2.equals(oracleType)) {
                 result = JAVA_STRING_FULL_TYPE;
             }
             if (ORACLE_DATE.equals(oracleType)) {
                 result = JAVA_DATE_FULL_TYPE;
             }
             if (ORACLE_BINARY_FLOAT.equals(oracleType)) {
                 return JAVA_BIGDECIMAL_FULL_TYPE;
             }
             if (ORACLE_BINARY_DOUBLE.equals(oracleType)) {
                 result = JAVA_BIGDECIMAL_FULL_TYPE;
             }
             if (ORACLE_TIMESTAMP.equals(oracleType) || oracleType.startsWith(ORACLE_TIMESTAMP)) {
                 result = JAVA_DATE_FULL_TYPE;
             }
             if (ORACLE_CHAR.equals(oracleType)) {
                 result = JAVA_STRING_FULL_TYPE;
             }
         }
         return result;
     }

     /**
      * 获取set类型前缀
      * 
      * @param javaShortType java类型的缩写
      * @return set方法的前缀
      */
     public static String getSetPrefix(String javaShortType) {
         String result = "";
         if (null != javaShortType && javaShortType.length() > 0) {
             javaShortType = javaShortType.trim();
             if (JAVA_STRING_SHORT_TYPE.equalsIgnoreCase(javaShortType)) {
                 result = SET_STRING;
             }
             if (JAVA_BIGDECIMAL_SHORT_TYPE.equalsIgnoreCase(javaShortType)) {
                 result = SET_BIGDECIMAL;
             }
             if (JAVA_INTEGER_SHORT_TYPE.equalsIgnoreCase(javaShortType)) {
                 result = SET_INT;
             }
             if (JAVA_DATE_SHORT_TYPE.equalsIgnoreCase(javaShortType)) {
                 result = SET_SQL_DATE;
             }
         }
         return result;
     }

     /**
      * 获取get类型前缀
      * 
      * @param javaShortType java类型的缩写
      * @return get方法的前缀
      */
     public static String getGetPrefix(String javaShortType) {
         String result = "";
         if (null != javaShortType && javaShortType.length() > 0) {
             javaShortType = javaShortType.trim();
             if (JAVA_STRING_SHORT_TYPE.equalsIgnoreCase(javaShortType)) {
                 result = GET_STRING;
             }
             if (JAVA_BIGDECIMAL_SHORT_TYPE.equalsIgnoreCase(javaShortType)) {
                 result = GET_BIGDECIMAL;
             }
             if (JAVA_INTEGER_SHORT_TYPE.equalsIgnoreCase(javaShortType)) {
                 result = GET_INT;
             }
             if (JAVA_DATE_SHORT_TYPE.equalsIgnoreCase(javaShortType)) {
                 result = GET_SQL_DATE;
             }
         }
         return result;
     }
     
     public static void main(String a[]){
         System.out.println(getGetPrefix("String"));
         System.out.println(getJavaFullTypeByOracleShortType("NUMBER"));
         System.out.println(getJavaFullType("NUMBER"));
     }
}
