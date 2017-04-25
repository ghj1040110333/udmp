package cn.com.git.udmp.common.excel.template;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import cn.com.git.udmp.common.excel.annotation.ExcelTemplateField;
import cn.com.git.udmp.common.utils.StringUtils;

/**
 * 
 * @author guosg
 *
 */
public class LoopRowSheetOperator extends AbstractSheetOperator{
	Map<String, String> map = new HashMap<String, String>();
	String name;
	
	Sheet sheet = null;
	Row templateRow;
	Map<Short,CellStyle> cellStyles = new HashMap<Short,CellStyle>();
	CellStyle rowStyle = null;
	int curRowNum;
	int templateRowNum;
	@Override
	public boolean init(Sheet sheet) {
		this.sheet = sheet;
		int num = 0;
		boolean suc = false;
		while(num<sheet.getLastRowNum()){
			Row row = sheet.getRow(num);
			if(row == null) break;
			short cellNum = row.getFirstCellNum();
			while(cellNum < row.getLastCellNum()){
				Object value = ExcelTemplateUtils.getCellValue(row, cellNum);
				
				if(value instanceof String){
					String v = (String)value;
					if(v.indexOf("$l{")==0){
						v = v.substring(3, v.length()-1);
						if(v.indexOf(".")>0){
							int i = v.indexOf(".");
							name = v.substring(0,i);
							v = v.substring(i+1,v.length());
						}
						
						String k = num+"_"+cellNum;
						map.put(k, v);
						this.templateRow = row;
						curRowNum = row.getRowNum();
						templateRowNum = row.getRowNum();
						
						
						suc = true;
					}
				}
				cellNum++;
			}
			num++;
		}
		if(suc){
			rowStyle=templateRow.getRowStyle();
			
			for(short i=0;i<templateRow.getLastCellNum();i++){
				Cell cell = templateRow.getCell(i);
				if(cell !=null){
					cellStyles.put(i, cell.getCellStyle());
				}
			}
		}
		return suc;
	}

	@Override
	public void load(Object data) throws Exception {
		List<?> lis = null;
		if(StringUtils.isNotBlank(name)){
			if(data instanceof List){
				lis = (List<?>) data;
			}else{
				lis = (List<?>) PropertyUtils.getProperty(data, name);
			}
		}
		
		if(data instanceof List){
			lis = (List<?>) data;
		}
		
		if(lis.size()>0){
			Object d = lis.get(0);
			for(Field field : d.getClass().getDeclaredFields()){
				ExcelTemplateField excelField = field.getAnnotation(ExcelTemplateField.class);
				if(excelField!=null){
					String k = excelField.rowNum()+"_"+excelField.colNum();
					map.put(k, field.getName());
				}
			}
		}
		for(Object obj : lis){
			Row row = sheet.createRow(curRowNum);
			if(rowStyle!=null)
				row.setRowStyle(rowStyle);
			Map<?, ?> dmap = PropertyUtils.describe(obj);
			ScriptEngineManager manager = new ScriptEngineManager();
			ScriptEngine engine = manager.getEngineByName("js");
			engine.getContext().setAttribute("data", obj,ScriptContext.ENGINE_SCOPE);
			short cellNum = this.templateRow.getFirstCellNum();
			while(cellNum < this.templateRow.getLastCellNum()){
				Cell cell = row.createCell(cellNum);
				String k = this.templateRow.getRowNum()+"_"+cellNum;
				cell.setCellStyle(cellStyles.get(cellNum));
				
				if(map.containsKey(k)){
					
					String n = (String) map.get(k);
					Object d = "";
					if(n.startsWith("s:")){
						n = n.substring(2);
						d = engine.eval(n); 
					}else{
						d = dmap.get(n);
					}
					ExcelTemplateUtils.setCell(cell, d);
				}
				
				cellNum++;
			}
			curRowNum++;
		}
		
		
	}

}
