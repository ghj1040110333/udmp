package cn.com.git.udmp.bizflow.mapping;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import cn.com.git.udmp.bizflow.data.DataObject;
/**
 * 表达式处理
 * @author updated by Spring Cao
 *
 */
public class ValueExpressHandler {

	public static Object express(String express,ValueObject vo){
		if(StringUtils.isBlank(express)){
			return null;
		}
		//如果为$开头，那么这是个变量，如果不是就是个常量
		if(express.startsWith("$")){
			express = express.substring(1);
			Object data = null;
			if(express.startsWith("/")){
				express = express.substring(1);
				data = vo.getParamsStore();
			}else{
				data= vo.getCur();
			}
			String[] paths = express.split("/");
			List<String> pl = new LinkedList<String>();
			for(String path : paths){
				pl.add(path);
			}
			return value(data,pl);
		}else{
			return express;
		}
	}

	private static Object value(Object data,List<String> pl){
		Object o = data;
		if(pl.size()>0){
			if(o instanceof DataObject){
				String cp = pl.get(0);
				o = ((DataObject) o).get(cp);
				pl.remove(0);
				o = value(o,pl);
			}
		}
		return o;
	}
}
