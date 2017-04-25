package cn.com.git.udmp.common.excel.template;

import java.util.HashMap;
import java.util.Map;

import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import cn.com.git.udmp.common.utils.ConvertUtils;
import cn.com.git.udmp.common.utils.StringUtils;

/**
 * 
 * @author guosg
 *
 */
public class JavascriptSheetOperator extends AbstractSheetOperator{
	Map<String, String> map = new HashMap<String, String>();
	Map<String, String> fieldMap = new HashMap<String, String>();
	Sheet sheet = null;
	@Override
	public boolean init(Sheet sheet) {
		this.sheet = sheet;
		int num = 0;
		boolean suc = false;
		while(num<=sheet.getLastRowNum()){
			Row row = sheet.getRow(num);
			if(row == null) break;
			short cellNum = row.getFirstCellNum();
			while(cellNum <= row.getLastCellNum()){
				Object value = ExcelTemplateUtils.getCellValue(row, cellNum);
				if(value instanceof String){
					String v = (String)value;
					if(v.indexOf("$s{")>=0){
						v = v.substring(v.indexOf("$s{")+3, v.indexOf("}"));
						String k = num+"_"+cellNum;
						map.put(k, v);
						fieldMap.put(k, (String)value);
						suc = true;
					}
				}
				cellNum++;
			}
			num++;
		}
		return suc;
	}
	
	

	@Override
	public void load(Object data) throws Exception{
		
		Map<?, ?> dmap = PropertyUtils.describe(data);
		int num = 0;
		while(num<= sheet.getLastRowNum()){
			Row row = sheet.getRow(num);
			if(row == null) break;
			ScriptEngineManager manager = new ScriptEngineManager();
			ScriptEngine engine = manager.getEngineByName("js");
			engine.getContext().setAttribute("data", data,ScriptContext.ENGINE_SCOPE);
			short cellNum = row.getFirstCellNum();
			while(cellNum < row.getLastCellNum()){
				String k = num+"_"+cellNum;
				Cell cell = row.getCell(cellNum);
				if(map.containsKey(k)){
					String n = (String) map.get(k);
					Object d = engine.eval(n); 
					String v = fieldMap.get(k);
					if(d==null)
						d ="";
					d = StringUtils.replace(v, "$s{"+n+"}", ConvertUtils.serialValue(d.getClass(), d,"yyyy-MM-dd"));
				
					ExcelTemplateUtils.setCell(cell, d);
				}
				
				cellNum++;
			}
			num++;
		}
	}

}
