package cn.com.git.udmp.common.excel.template;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.ResourceUtils;

/**
 * 使用Excel模板生成Excel
 * @author guosg
 *
 */
public class ExcelTemplate {
	private final static Log logger = LogFactory.getLog(ExcelTemplate.class);
	/**
	 * 工作薄对象
	 */
	private Workbook wb;
	
	private Map<Integer,List<AbstractSheetOperator>> sheets = new HashMap<Integer,List<AbstractSheetOperator>>();
	
	public ExcelTemplate(InputStream templateFile,ExcelFileType excelFileType){
		init(templateFile,excelFileType);
	}
	/**
	 * 文件路径
	 * @param fileName
	 */
	public ExcelTemplate(String fileName){
		ExcelFileType type = null;
		InputStream is;
		if (StringUtils.isBlank(fileName)){
			throw new RuntimeException("导入文档为空!");
		}else if(fileName.toLowerCase().endsWith("xls")){    
			type = ExcelFileType.xls;    
        }else if(fileName.toLowerCase().endsWith("xlsx")){  
        	type = ExcelFileType.xlsx;
        }else{
        	throw new RuntimeException("fileName "+fileName+"不是一个Excel文件");
        }
		
		try {
			is = new FileInputStream(ResourceUtils.getFile(fileName));
		} catch (FileNotFoundException e) {
			throw new RuntimeException("fileName "+fileName+"文件没有找到",e);
		}
		
		init(is,type);
		
		
	}
	/**
	 * 加载数据
	 * @param datas
	 * @throws Exception
	 */
	public void load(List<?> datas) throws Exception{
		for(int i=0;i<datas.size();i++){
			
			List<AbstractSheetOperator>  ops = sheets.get(i);
			for(AbstractSheetOperator op : ops){
				op.load(datas.get(i));
			}
		}
		wb.getCreationHelper().createFormulaEvaluator().evaluateAll();
	}
	/**
	 * 加载数据
	 * @param data 需要加载的数据
	 * @param sheetIndex 
	 * @throws Exception
	 */
	public void load(Object data,int sheetIndex) throws Exception{
		List<Object> lis = new ArrayList<Object>();
		for(int i=0;i<=sheetIndex;i++){
			if(sheetIndex == i){
				lis.add(data);
				break;
			}
			lis.add(new Object());
		}
		
		load(lis);
	}
	
	public void write(OutputStream os) throws Exception{
		wb.write(os);
	}
	
	
	public void write(String fileName) throws Exception{
		File file = new File(fileName);
		
		OutputStream os = new FileOutputStream(file);
		wb.write(os);
	}

	
	protected void init(InputStream templateFile,ExcelFileType excelFileType){
		try{
			if(ExcelFileType.xls.equals(excelFileType)){    
				this.wb = new HSSFWorkbook(templateFile);
	        }else if(ExcelFileType.xlsx.equals(excelFileType)){  
	        	this.wb = new XSSFWorkbook(templateFile);
	        }else{  
	        	throw new RuntimeException("文档格式不正确!");
	        }  
		}catch(Exception ex){
			throw new RuntimeException("导入Excel模板失败",ex);
		}
		int i =0;
		while(true){
			try{
				Sheet sheet = wb.getSheetAt(i);
				sheets.put(i,ExcelTemplateUtils.build(sheet));
			}catch(IllegalArgumentException ex){
				break;
			}
			i++;
		}
		logger.debug("Initialize success.");
		
		
	}
	
}
