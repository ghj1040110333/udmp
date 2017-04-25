package cn.com.git.udmp.bizflow.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;


/**
 * XML工具类
 * @author updated by Spring Cao
 *
 */
public class XmlUtils {

	static Log log = LogFactory.getLog(XmlUtils.class);

	public static Document read(InputStream is){
		return read(is, null);
	}

	public static Document read(InputStream is,String encoding){
		SAXReader reader = new SAXReader();
		if(StringUtils.isNotBlank(encoding)){
			reader.setEncoding(encoding);
		}
		Document doc = null;
		try {
			doc = reader.read(is);
		} catch (DocumentException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		return doc;
	}

	public static Document read(String soap){
		Document doc = null;
		try {
			doc =DocumentHelper.parseText(soap);
		} catch (DocumentException e) {
			throw new RuntimeException("ESB-XML-001", e);
		}
		return doc;
	}

	/**
	 * 把document对象转为String  的xml
	 * @param doc
	 * @param encoding 字符集 默认情况下是UTF-8
	 * @return
	 * @throws RealException
	 */
	public static String toString(Document doc,String encoding){
		if(doc ==null) return null;
		String r = null;

		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding(encoding!=null?encoding:"UTF-8");
		StringWriter out =null;
		XMLWriter writer = null;
		try{
			out = new StringWriter();
			writer = new XMLWriter(out,format);
			writer.write(doc);
			r = out.getBuffer().toString();
		}catch (Exception e) {
			throw new RuntimeException("将doc转化为String报错",e);
		}finally{
			try {
				if(out!=null)
					out.close();

				if(writer!=null){
					writer.close();
				}
			} catch (IOException e) {
				log.error("无法关闭StringWriter");
			}
		}
		return r;
	}

}
