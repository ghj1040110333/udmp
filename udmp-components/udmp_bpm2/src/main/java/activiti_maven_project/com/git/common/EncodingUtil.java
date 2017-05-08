package activiti_maven_project.com.git.common;

import java.io.UnsupportedEncodingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class EncodingUtil {
	private static Logger log = LogManager.getLogger();
	
	public static String changeEncoding(String message) throws UnsupportedEncodingException{
		String encodeType=getEncoding(message);
		log.debug("encodeType====="+encodeType);
		return  new String(message.getBytes(encodeType),EncodeContent.ENCODING_UTF8);
	}
	
	
	
	
	public static String getEncoding(String str) {      
	       String encode = EncodeContent.ENCODING_UTF8;      
	      try {      
	          if (str.equals(new String(str.getBytes(encode), encode))) {      
	               String s = encode;      
	              return s;      
	           }      
	       } catch (Exception exception) {
	    	   exception.printStackTrace();
	       }      
	       encode = EncodeContent.ENCODING_ISO8859;      
	      try {      
	          if (str.equals(new String(str.getBytes(encode), encode))) {      
	               String s1 = encode;      
	              return s1;      
	           }      
	       } catch (Exception exception1) {     
	    	   exception1.printStackTrace();
	       }      
	       encode = EncodeContent.ENCODING_GB2312;      
	      try {      
	          if (str.equals(new String(str.getBytes(encode), encode))) {      
	               String s2 = encode;      
	              return s2;      
	           }      
	       } catch (Exception exception2) {   
	    	   exception2.printStackTrace();
	       }      
	       encode = EncodeContent.GBK;      
	      try {      
	          if (str.equals(new String(str.getBytes(encode), encode))) {      
	               String s3 = encode;      
	              return s3;      
	           }      
	       } catch (Exception exception3) {  
	    	   exception3.printStackTrace();
	       }      
	      return "";      
	   }
}
