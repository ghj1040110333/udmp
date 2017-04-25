package cn.com.git.udmp.common.excel.template;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

/**
 * 
 * @author guosg
 *
 */
public class ExcelTemplateUtils {
	private static final Log logger = LogFactory.getLog(ExcelTemplateUtils.class);
	/**
	 * 获取单元格值
	 * @param row 获取的行
	 * @param column 获取单元格列号
	 * @return 单元格值
	 */
	public static Object getCellValue(Row row, int column){
		Object val = "";
		try{
			Cell cell = row.getCell(column);
			if (cell != null){
				if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
					val = cell.getNumericCellValue();
				}else if (cell.getCellType() == Cell.CELL_TYPE_STRING){
					val = cell.getStringCellValue();
				}else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA){
					val = cell.getCellFormula();
				}else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN){
					val = cell.getBooleanCellValue();
				}else if (cell.getCellType() == Cell.CELL_TYPE_ERROR){
					val = cell.getErrorCellValue();
				}
			}
		}catch (Exception e) {
			return val;
		}
		return val;
	}
	
	public static void setCell(Cell cell, Object val){
		try {
			if (val == null){
				cell.setCellValue("");
			} else if (val instanceof String) {
				cell.setCellValue((String) val);
			} else if (val instanceof Integer) {
				cell.setCellValue((Integer) val);
			} else if (val instanceof Long) {
				cell.setCellValue((Long) val);
			} else if (val instanceof Double) {
				cell.setCellValue((Double) val);
			}else if (val instanceof BigDecimal) {
				BigDecimal v = (BigDecimal)val;
				cell.setCellValue(v.doubleValue()  );
			} else if (val instanceof Float) {
				cell.setCellValue((Float) val);
			} else if (val instanceof Date) {
				cell.setCellValue((Date) val);
			}
		} catch (Exception ex) {
			logger.info("Set cell value  error: " + ex.toString(),ex);
			cell.setCellValue(val.toString());
		}
	}
	
	public static List<AbstractSheetOperator> build(Sheet sheet){
		List<AbstractSheetOperator> lis = new ArrayList<AbstractSheetOperator>();
		AbstractSheetOperator o = new SheetOperator();
		if(o.init(sheet)){
			lis.add(o);
		}
		
		o = new JavascriptSheetOperator();
		if(o.init(sheet)){
			lis.add(o);
		}
		
		o = new LoopRowSheetOperator();
		if(o.init(sheet)){
			lis.add(o);
		}
		
		o = new JavascriptLoopRowSheetOperator();
		if(o.init(sheet)){
			lis.add(o);
		}
		
		
		return lis;
	}
}	
