package cn.com.git.udmp.common.act;

import java.util.HashMap;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 
 * @author guosg
 *
 */
@Service
public class PrinterService {
	private static final Logger logger = LoggerFactory.getLogger(PrinterService.class);
	/**
	 * 
	 */
	public void print(HashMap<String, Object> map){
		for(Entry<String, Object> entry : map.entrySet()){
			logger.debug(entry.getKey()+":"+entry.getValue());
		}
	}
}
