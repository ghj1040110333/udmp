package cn.com.git.udmp.test.common.docx4j;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;

import org.docx4j.jaxb.Context;
import org.docx4j.openpackaging.io3.Save;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;

/**
 * 基于docx4j实现对于word文档模板的填充输出
 * @author Spring Cao
 * @version 1.0
 */
public class VariableReplace {
	
	public static void main(String[] args) throws Exception {
		
		// Exclude context init from timing
		org.docx4j.wml.ObjectFactory foo = Context.getWmlObjectFactory();

		// Input docx has variables in it: ${colour}, ${icecream}
		String inputfilepath = "contractContent.docx";
		// Output docx
		String outputfilepath ="OUT_BookmarksTextInserter.docx";
		// load as Inputstream
		InputStream is = VariableReplace.class.getResourceAsStream("contractContent.docx");
		
		WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(is);
		MainDocumentPart documentPart = wordMLPackage.getMainDocumentPart();
		// Setup variables into a Map
		HashMap<String, String> mappings = new HashMap<String, String>();
		mappings.put("customerName", "Spring");
		mappings.put("loanSum", "100000");
		mappings.put("loanLength", "12");
		mappings.put("productName", "车贷");
		mappings.put("loanPurpose", "摇到号了，买车");

		// Binding Variables
		documentPart.variableReplace(mappings);

		org.docx4j.openpackaging.io3.Save saver = new Save(wordMLPackage);
		FileOutputStream out = new FileOutputStream(outputfilepath);
		// Output a word file
		saver.save(out);
	}
}
