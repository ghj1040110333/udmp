package cn.com.git.udmp.common.validate.mail;

import java.util.List;

/**
 * 提供对邮箱校验的方法
 * @author Spring Cao
 * @version v1.0
 */
public class EmailUtil {

    /**
     * @description 校验邮箱格式是否正确
     * @version
     * @title
     * @param email email类型为String，表示被检验邮箱
     * @return 若输入邮箱格式正确，返回true，否则，返回false
     */
    public static boolean validate(String email) {
        if (email == null || "".equals(email)) {
            return false;
        }
        if (email.matches("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @description 校验邮箱格式是否正确
     * @version
     * @title
     * @param email List<String> 邮箱
     * @return 若输入邮箱格式全部正确，返回true，否则，返回false
     */
    public static boolean validate(List<String> email) {
        for (String e : email) {
            boolean checkEmail = validate(e);
            if (checkEmail == false) {
                return false;
            }
        }
        return true;
    }
}
