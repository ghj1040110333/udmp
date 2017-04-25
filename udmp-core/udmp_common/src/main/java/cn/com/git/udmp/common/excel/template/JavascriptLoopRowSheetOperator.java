package cn.com.git.udmp.common.excel.template;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

import cn.com.git.udmp.common.excel.annotation.ExcelTemplateField;
import cn.com.git.udmp.common.utils.ConvertUtils;
import cn.com.git.udmp.common.utils.StringUtils;

/**
 * 
 * @author guosg
 *
 */
public class JavascriptLoopRowSheetOperator extends AbstractSheetOperator{
	Map<String, String> map = new HashMap<String, String>();
	Map<String, String> fieldMap = new HashMap<String, String>();
	String name;
	
	Sheet sheet = null;
	Row templateRow;
	Map<Short,CellStyle> cellStyles = new HashMap<Short,CellStyle>();
	List<CellRangeAddress> mergeRangeAddresses = new ArrayList<CellRangeAddress>();
	CellStyle rowStyle = null;
	int curRowNum;
	int templateRowNum;
	@Override
	public boolean init(Sheet sheet) {
		this.sheet = sheet;
		int num = 0;
		boolean suc = false;
		while(num<=sheet.getLastRowNum()){
			Row row = sheet.getRow(num);
			if(row == null) break;
			short cellNum = row.getFirstCellNum();
			while(cellNum < row.getLastCellNum()){
				Object value = ExcelTemplateUtils.getCellValue(row, cellNum);
				
				if(value instanceof String){
					String v = (String)value;
					if(v.indexOf("$ls{")>=0){
						v = v.substring(v.indexOf("$ls{")+4, v.indexOf("}"));
						if(v.indexOf("||")>0){
							String[] ts = v.split("\\|\\|");
							name=ts[0];
							v=ts[1];
						}
						String k = num+"_"+cellNum;
						map.put(k, v);
						fieldMap.put(k, (String)value);
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
			int sheetCount = sheet.getNumMergedRegions();
			for(int i=0;i<sheetCount;i++){
				CellRangeAddress range = sheet.getMergedRegion(i);
				if(range.getFirstRow() == templateRow.getRowNum()){
					mergeRangeAddresses.add(range);
				}
			}
			
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
				ScriptEngineManager manager = new ScriptEngineManager();
				ScriptEngine engine = manager.getEngineByName("js");
				engine.getContext().setAttribute("data", data,ScriptContext.ENGINE_SCOPE);
				lis = (List<?>) engine.eval(name);
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
		clearMergeRegin(templateRowNum,templateRowNum+lis.size());
		//如果实际的行数比当前的行要大，那么，将会进行复制
		if(sheet.getLastRowNum() > templateRowNum){
			copy(templateRowNum+1, sheet.getLastRowNum(), templateRowNum+lis.size());
		}
		for(Object obj : lis){
			Row row = sheet.createRow(curRowNum);
			
			
			
			if(rowStyle!=null)
				row.setRowStyle(rowStyle);
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

					d = engine.eval(n); 
					if(d != null){
						d = ConvertUtils.serialValue(d.getClass(), d, "yyyy-MM-dd");
					}else{
						d = "";
					}
					ExcelTemplateUtils.setCell(cell, d);
				}
				
				cellNum++;
			}
			for(CellRangeAddress range : mergeRangeAddresses){
				CellRangeAddress rowRange =  new CellRangeAddress(curRowNum, curRowNum, range.getFirstColumn(), range.getLastColumn());
				sheet.addMergedRegion(rowRange);
			}
			curRowNum++;
		}
	}
	/**
	 * 去除合并的区域
	 */
	private void clearMergeRegin(int rowNum,int lastRowNum){
		List<Integer> removes = new ArrayList<Integer>();
		int sheetCount = sheet.getNumMergedRegions();
		int sheetIndex = -1;
		for(int i=0;i<sheetCount;i++){
			CellRangeAddress range = sheet.getMergedRegion(i);
			if(range.getFirstRow() >= rowNum && range.getLastRow()<=lastRowNum){
				sheetIndex = i;
				break;
			}
		}
		if(sheetIndex!=-1){
			sheet.removeMergedRegion(sheetIndex);
			clearMergeRegin(rowNum, lastRowNum);
		}
		
	}
	/**
	 * 
	 * @param fromRowNum
	 * @param toRowNum
	 */
	private void copy(int fromFistRowNum,int fromLastRowNum,int toRowNum){
		
		for(int rowIndex=fromFistRowNum;rowIndex<=fromLastRowNum;rowIndex++){
			Row toRow = sheet.createRow(toRowNum);
			Row fromRow = sheet.getRow(rowIndex);
			toRow.setRowStyle(fromRow.getRowStyle());
			toRow.setHeight(fromRow.getHeight());
			toRow.setHeightInPoints(toRow.getHeightInPoints());
			toRow.setZeroHeight(fromRow.getZeroHeight());
			for(int i= fromRow.getFirstCellNum();i <= fromRow.getLastCellNum();i++){
				
				Cell fromCell = fromRow.getCell(i);
				if(fromCell ==  null){
					continue;
				}
				Cell toCell = toRow.createCell(i);
				
				toCell.setCellStyle(fromCell.getCellStyle());
				toCell.setCellComment(fromCell.getCellComment());
				toCell.setCellType(fromCell.getCellType());
				if(fromCell.getHyperlink()!=null)
				toCell.setHyperlink(fromCell.getHyperlink());
				if(fromCell.getCellType() ==  Cell.CELL_TYPE_FORMULA){
					toCell.setCellFormula(fromCell.getCellFormula());
				}else{
					Object obj = ExcelTemplateUtils.getCellValue(fromRow, i);
					ExcelTemplateUtils.setCell(toCell, obj);
				}
			}
			toRowNum++;
		}
	}

}

