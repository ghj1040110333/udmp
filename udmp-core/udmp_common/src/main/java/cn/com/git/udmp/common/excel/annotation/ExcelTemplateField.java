

package cn.com.git.udmp.common.excel.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Excel注解定义
 * @author guosg
 * @version 2014-05-30
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelTemplateField {
	/**
	 * 行数
	 * @return
	 */
	int rowNum();
	/**
	 * 列数
	 * @return
	 */
	int colNum();
}
