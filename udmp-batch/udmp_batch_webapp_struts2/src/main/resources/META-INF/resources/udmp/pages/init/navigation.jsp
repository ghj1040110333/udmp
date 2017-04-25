<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<script language="JavaScript">
	function clickHomePageTitle(menuCode){
	       if(menuCode=='index'){
	    	   $("#homePageTitle").click();
	       }else{
	    	   $("#sidebar").find("a[rel="+menuCode+"]").click();
	       }
	    }
</script>

<li>
	<a href="#" onclick="clickHomePageTitle('index')"><span>首页</span>
					<p>|</p></a>
</li>
<s:iterator value="#session.meueNav">
<li><a href="#" onclick="clickHomePageTitle('${menuId}')"><span>${menuName}</span>
					<p>|</p></a></li>
</s:iterator>

