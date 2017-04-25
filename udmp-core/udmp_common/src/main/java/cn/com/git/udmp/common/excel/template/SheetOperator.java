package cn.com.git.udmp.common.excel.template;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import cn.com.git.udmp.common.excel.annotation.ExcelTemplateField;

/**
 * 
 * @author guosg
 *
 */
public class SheetOperator extends AbstractSheetOperator{
	Map<String, String> map = new HashMap<String, String>();
	Sheet sheet = null;
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
					if(v.indexOf("${")==0){
						v = v.substring(2, v.length()-1);
						String k = num+"_"+cellNum;
						map.put(k, v);
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
		
		for(Field field : data.getClass().getDeclaredFields()){
			ExcelTemplateField excelField = field.getAnnotation(ExcelTemplateField.class);
			if(excelField!=null){
				String k = excelField.rowNum()+"_"+excelField.colNum();
				map.put(k, field.getName());
			}
		}
		
		Map<?, ?> dmap = PropertyUtils.describe(data);
		int num = 0;
		while(num<sheet.getLastRowNum()){
			Row row = sheet.getRow(num);
			if(row == null) break;
			short cellNum = row.getFirstCellNum();
			while(cellNum < row.getLastCellNum()){
				String k = num+"_"+cellNum;
				Cell cell = row.getCell(cellNum);
				if(map.containsKey(k)){
					String n = (String) map.get(k);
					Object d = dmap.get(n);
					ExcelTemplateUtils.setCell(cell, d);
				}
				
				cellNum++;
			}
			num++;
		}
	}

}
