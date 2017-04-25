

package cn.com.git.udmp.core.base;

import cn.com.git.udmp.core.logging.ILog;

/** 
 * 控制器层
 * @description 架构标识接口
 * @author Spring Cao
 * @date 2016年8月22日 下午3:47:56  
*/
public interface IController extends ILog{
    /** JSON的文档结构类型 */
    public static final String CTL_CONTENT_TYPE_JSON = "application/json";
    /** UTF-8的编码格式 */
    public static final String CTL_CHAR_CODE_UTF8 = "utf-8";
    
}
