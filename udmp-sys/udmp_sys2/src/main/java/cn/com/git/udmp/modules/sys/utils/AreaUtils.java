

package cn.com.git.udmp.modules.sys.utils;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import cn.com.git.udmp.common.utils.SpringContextHolder;
import cn.com.git.udmp.modules.sys.dao.AreaDao;
import cn.com.git.udmp.modules.sys.entity.Area;

/**
 * 地区工具类
 * @author Spring Cao
 * @version 2013-5-29
 */
public class AreaUtils {
	
	private static AreaDao areaDao = SpringContextHolder.getBean(AreaDao.class);

	public static String getFullAreaNameById(String areaId,String linkStr,String defaultValue){
		if (StringUtils.isNotBlank(areaId) && StringUtils.isNotBlank(areaId)){
			Area ar = areaDao.get(areaId);
			String s = ar.getParentIds();
			s = "'"+s.replaceAll(",","','");
			ar.setParentIds(s);
			List<Area> lst = areaDao.findparentsList(ar);
			StringBuffer resultStr = new StringBuffer();
			for(int i=0;i<lst.size();i++){
				resultStr.append(lst.get(i).getName());
				resultStr.append(linkStr);
			}
			resultStr.append(ar.getName());
			return resultStr.toString();
		}
		return defaultValue;
	}
}
