package cn.com.git.udmp.common.excel.template;

import org.apache.poi.ss.usermodel.Sheet;

/**
 * 
 * @author guosg
 *
 */
public abstract class AbstractSheetOperator {
	
	public abstract boolean init(Sheet sheet);
	
	public abstract void load(Object data) throws Exception;
	
	
}
