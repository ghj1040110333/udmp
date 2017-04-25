package cn.com.git.udmp.batch.common.tag;

import java.util.List;

import cn.com.git.udmp.modules.sys.entity.Dict;

public class TagHelp {
	public static void removeLimitation(List<Dict> origList,List<String> limitList)
	{
		int i=-1;
		for(String codeValueTemp:limitList)
		{
			
			i=findDictionaryIndexById(origList,codeValueTemp);
			if(i>=0){
				origList.remove(i);
			}
		}
	}
	
	private static int findDictionaryIndexById(List<Dict> argList,String value)
	{
		int j=-1;
		for(int i=0;i<argList.size();i++)
		{
			if(argList.get(i).getValue().equals(value))
			{
				return i;
			}
		}
		return j;
	}

}
