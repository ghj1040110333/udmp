package cn.com.git.udmp.common.validate.creditcard;

import org.apache.commons.lang3.math.NumberUtils;

/** 
 * @description 校验信用卡号，判断其长度是否为16位 并且全部为数字
 * @author Spring Cao
 * @version v1.0
*/
public class CreditCardUtil {
    /** 
     * <p>Title:构造方法 </p> 
     * <p>Description: </p>  
    */
    public CreditCardUtil() {
    }

    /**
     * 校验信用卡号，判断其长度是否为16位，并且全部为数字
     * @title
     * @param creditCardNo 信用卡号
     * @return ture 长度为16位数字；false 结果不为16位数字
    */
    public static boolean validate(String creditCardNo) {
        boolean rc = true;
        if (creditCardNo.length() != 16 || !NumberUtils.isNumber(creditCardNo)) {
            rc = false;
        }
        return rc;
    }
}
