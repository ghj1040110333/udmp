package cn.com.git.udmp.modules.act.utils;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

/**
 * 
 * @author guosg
 *
 */
@Component("actElCallUtils")
public class ActElCallUtils {
	/**
	 * 实例化一个数组,在el表达式中无法创建数组
	 * @param a
	 * @return
	 */
	public <T> List<T> asList(T... a) {
        return  Arrays.asList(a);
    }
}
