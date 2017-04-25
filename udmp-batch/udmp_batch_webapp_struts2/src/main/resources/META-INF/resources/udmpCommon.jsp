<%@page  language="java"  contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%-- <%@ taglib uri="http://www.git.com.cn/taglib/udmp/form" prefix="gitForm"%> --%>
<%-- <%@ taglib uri="http://www.git.com.cn/taglib/udmp/field" prefix="Field" %> --%>
<input id="jspname" type="hidden" value="<%=request.getRequestURI()%>" />
<!-- 自定义token标签 -->
<gitForm:token />
<%-- <%@ page import="com.nci.udmp.framework.tag.tokentag.TokenHelper"%> --%>
<%-- <% String tokenval = TokenHelper.getSessionToken(request); %> --%>
<%-- <input name="token" type="hidden" value="<%=tokenval%>"/> --%>
