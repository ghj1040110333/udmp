<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/udmpCommon.jsp"%>
<script type="text/javascript">
	function resetAll(){
		$('#username').attr('value','');
		$('#realname').attr('value','');
		$("#userdisable").selectMyComBox("","navTab");
		$('#branchCode').attr('value','');
	}
</script>
<form id="pagerForm" method="post"
	action="sys/queryUser_userAction.action?leftFlag=0&menuId=${menuId }">
	<input type="hidden" name="pageNum" value="${pageInfo.currentPage}" />
	<input type="hidden" name="numPerPage" value="${pageInfo.numPerPage}" />
</form>
<div class="pageHeader">
	<form action="sys/queryUser_userAction.action?leftFlag=0&menuId=${menuId }" method="post" name="form1" id="form1" class="pageForm required-validate" onsubmit="return navTabSearch(this);" rel="pagerForm">
	<div class="panel">
	<h1>查询条件</h1><div>
	<div class="searchBar">
		<table class="searchContent">
		  <tr>
		  	<td>
				用户代码：
			</td>
			<td>
				<input id="username" type="text" name="userTVo.userName" value="${userTVo.userName }" size="15" maxlength="20"/>
			</td>
			<td>
				用户姓名：
			</td>
			<td>
				<input id="realname" type="text" name="userTVo.realname" value="${userTVo.realname }" size="15" maxlength="20" title="本文支持模糊查询"/>
			</td>
			<td>
				用户状态：
			</td>
			<td>
				<s:select id="userdisable" cssClass="combox" list="userStarMap" listKey="key" listValue="value"  headerKey="" headerValue="全部" name="userTVo.userdisable"></s:select>
			</td>
			<td>
				机构代码：
			</td>
			<td>
				<dl>
					<dd><input id="branchCode" name="userTVo.organCode" value="${userTVo.organCode}" type="text" class="organ" clickId="menuBtn" showOrgName="branchname" needAll="true"/>
					<a id="menuBtn" class="btnLook" href="#" style="position: relative; left: 158px;"></a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input id="branchname" type="text" readOnly/>
				</dd>
				</dl>
			</td>
		  </tr>
		</table>
		<div class="subBar">
			<ul>
				<li>
					<div class="buttonActive" >
						<div class="buttonContent">
							<button type="submit" id="userQuery">查询</button>
						</div>
					</div>
				</li>
				<li>
					<div class="buttonActive" >
						<div class="buttonContent">
							<button type="button" onclick="resetAll();" id="userQueryReset">重置</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</div></div></div>
	</form>
</div>
<div class="pageContent">
	<div class="panel">
	<h1>查询结果</h1><div>
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="sys/addUser_userAction.action?addUserFlag=add&menuId=${menuId }" maxable="false" minable="false" resizable="false" title="用户新增" width="600" height="280" target="dialog" rel="userAdd"><span>新增</span></a></li>
			<li class="line">line</li>
			<li><a class="edit" href="sys/updateUser_userAction.action?userVo.userId={uid}&updateUserFlag=update&menuId=${menuId }" maxable="false" resizable="false" minable="false" title="用户修改" width="600" height="280" rel="userUpdate" target="dialog"><span>修改</span></a></li>
			<li class="line">line</li>
			<li><a class="delete" href="sys/deleteUser_userAction.action?userVo.userId={uid}&menuId=${menuId }" target="ajaxTodo"  title="是否确认要删除" id="userDelete"><span>删除</span></a></li>
			<li class="line">line</li>
<!-- 			<li><a class="edit" href="sys/updateUserRole_userAction.action?userVo.userId={uid}&updateUserRoleFlag=updateRole" maxable="false" resizable="false" minable="false" title="分配角色" width="1000" height="600" rel="update" target="dialog"><span>分配角色</span></a></li> -->
<!-- 			<li class="line">line</li> -->
			<li><a class="edit" href="sys/mustNoline_userAction.action?userVo.userId={uid}" target="ajaxTodo" title="强制下线" id="userOutLine"><span>强制下线</span></a></li>
			<li class="line">line</li>
			<li><a class="edit" href="sys/clearAllOnline_userAction.action?menuId=${menuId }" title="是否清除所有在线用户" target="ajaxTodo" id="usersOutLine"><span>清除所有在线用户</span></a></li>
			<li class="line">line</li>
			<li><a class="edit" href="sys/userUnlock_userAction.action?userVo.userId={uid}" target="ajaxTodo" title="用户解锁" id="userUnlock"><span>用户解锁</span></a></li>
			<li class="line">line</li>
			<li><a class="edit" href="sys/lockUser_userAction.action?userVo.userId={uid}" target="ajaxTodo" title="主动锁定用户" id="lockUser"><span>主动锁定用户</span></a></li>
			<li class="line">line</li>
			<li><a class="edit" href="sys/resetPassword_loginAction.action?userVo.userId={uid}" target="ajaxTodo" title="是否重置用户密码为：000000" id="lockUser"><span>重置用户密码</span></a></li>
			<li class="line">line</li>
			<li><a class="edit" href="sys/updateUserAndRoleGroup_userAction.action?userVo.userId={uid}&updateUserGroupFlag=updateUserGroup" maxable="false" resizable="true" minable="false" title="用户分配角色组" width="500" height="600" rel="update" target="dialog" id="divideRoleGroup"><span>分配角色组</span></a></li>
		</ul>
	</div>

	<table class="table" width="100%" layoutH="225">
		<thead>
			<tr height="25" align="center">
				<th width="4%">选择</th>
				<th>用户编号</th>
				<th>用户代码</th>
				<th>用户姓名</th>
				<th>机构代码</th>
				<th>用户状态</th>
				<th>是否锁定</th>
				<th>在线状态</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="pageInfo.valueList" status="st">
				<tr align="center" height="25" target="uid" rel="${userId}">
		     		<td>
		     			<input type="radio" name="userManager" value="" />
		     		</td>
		       		<td>
		       			<div align="center">
		       				${userId }
		       			</div>
		       		</td>
		       		<td>
		       			<div align="center">
		       				${userName }
		       			</div>
		       		</td>
		       		<td>
		       			<div align="center">
		       				${realname }
		       			</div>
		       		</td>
		       		<td>
		       			<div align="center">
		       				${organCode }
		       			</div>
		       		</td>
		           	<td>
		               	<div align="center">
		               		<s:if test="userdisable==\"N\"">
		               			有效
		               		</s:if>	
		               		<s:elseif test="userdisable==\"Y\"">
		               			失效
		               		</s:elseif>
		               	</div>
		       		</td>
		       		<td>
		               	<div align="center">
		               		<s:if test="invalidlogin >= 3">
		               			是
		               		</s:if>	
		               		<s:else>
		               			否
		               		</s:else>
		               	</div>
		       		</td>
		       		<td>
		               	<div align="center">
		               		<Field:codeTable
							name="servicePublishAndConsumeVO.systemId"
							tableName="T_UDMP_SUB_SYSTEM_INFO" cssClass="combox"
							nullOption="true" />
		               	</div>
		       		</td>
		     	</tr>
	      	</s:iterator>
		</tbody>
	</table>
	<div class="panelBar" >
		<div class="pages">
			<span>显示</span>
			<s:select   list="#{20:'20',50:'50',100:'100',200:'200'}"  name="select" onchange="navTabPageBreak({numPerPage:this.value})" value="pageInfo.numPerPage">
     		</s:select>
			<span>条，共${pageInfo.totalCount}条</span>		
		</div>
		<div class="pagination" id="userList" targetType="navTab" totalCount="${pageInfo.totalCount}" numPerPage="${pageInfo.numPerPage}" pageNumShown="10" currentPage="${pageInfo.currentPage}"></div>
	</div></div>
</div></div>
