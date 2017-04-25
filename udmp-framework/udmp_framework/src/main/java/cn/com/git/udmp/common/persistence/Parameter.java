

package cn.com.git.udmp.common.persistence;

import cn.com.git.udmp.common.model.DataObject;

/**
 * 查询参数类
 * @author Spring Cao
 * @version 2014-8-23
 */
//public class Parameter extends HashMap<String, Object> {
public class Parameter extends DataObject {
    
	private static final long serialVersionUID = 1L;
	
	/**
	 * 构造类，例：new Parameter(id, parentIds)
	 * @param values 参数值
	 */
	public Parameter(Object... values) {
		if (values != null){
			for (int i=0; i<values.length; i++){
//				put("p"+(i+1), values[i]);
			    set("p"+(i+1), values[i]);
			}
		}
	}
	
	/**
	 * 构造类，例：new Parameter(new Object[][]{{"id", id}, {"parentIds", parentIds}})
	 * @param parameters 参数二维数组
	 */
	public Parameter(Object[][] parameters) {
		if (parameters != null){
			for (Object[] os : parameters){
				if (os.length == 2){
//					put((String)os[0], os[1]);
				    set((String)os[0], os[1]);
				}
			}
		}
	}
	
}
