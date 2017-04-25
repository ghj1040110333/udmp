<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:set var="ctx">${pageContext.request.contextPath}</s:set>
<script src="${ctx }/udmp/md5/md5.js" type="text/javascript"></script>
<script src="${ctx }/udmp/scripts/common.js" type="text/javascript"></script>
<%@ page
	import="com.nci.udmp.framework.para.*,java.util.*,com.nci.udmp.app.bizservice.bo.*,com.nci.udmp.framework.util.*"%>
<%
    Map<String, Object> map = ParaDefInitConst.getSystemConst();
	Map<String, Object> applicationMap = ParaDefInitConst.getApplicationConst();
	ParaDefBO paraDefBO = (ParaDefBO) map.get(Constants.PARA_NAME_SYSTEM_ID);
	ParaDefBO appliDefBO = new ParaDefBO();
	if(applicationMap.containsKey(Constants.IF_CHECK_CODE)){
	    appliDefBO = (ParaDefBO) applicationMap.get(Constants.IF_CHECK_CODE);
	}
%>
<style type="text/css">
* {
	margin: 0;
	padding: 0;
}

.login_user {
	height: 400px;
	width: 437px;
	background-attachment: scroll;
	background-image: url(udmp/dwz/themes/default/images/news/relogin.png);
	background-repeat: no-repeat;
	float: right;
	z-index: 40;
	top: 27px;
	left: 2px;
	margin-right: 0px;
	position: absolute;
}

.reLoginInfo {
	margin-top: 80px;
}

.login_user .login_user_flied {
	position: absolute;
	top: 65px;
	left: 40px;
}

#relogin_info {
	font-size: 15px;
	color: red;
	position: relative;
	left: 50px;
	width: 300px;
	top: 63px;
}

#forUserNameDia {
	position: absolute;
	left: 50px;
	font-size: 14px;
	top: 9px;
}

#userNameDia {
	width: 150px;
	top: 5px;
	left: 140px;
	position: absolute;
}

#foruserPwdDia {
	position: absolute;
	left: 50px;
	font-size: 14px;
	top: 37px;
}

#userPwdDia {
	width: 150px;
	top: 35px;
	left: 140px;
	position: absolute;
}
</style>
<script type="text/javascript">
	//判断是否校验验证码
	udmpUtil.checkCode = '<%=appliDefBO.getParaValue()%>' === '1';
	$(function () {
		//清除用户在线状态
		$.post("clearUser_loginAction.action?userid="+userid); 
		//初始化用户id
		$("#userNameDia").val(orderUserid);
		//密码输入框获取焦点
		$("#userPwdDia").focus();
		//重置
		$("#resetInputDia").click(function(){
			//将用户名清空部分注释掉
// 			$("#userNameDia").val("");
			$("#userPwdDia").val("");
		});
		//按回车键触发提交
		$("#userPwdDia").keyup(function(e) {
		    if (13 == e.keyCode) {
		    	clickLogin();
		    }
		});
		// 加载页面时默认点击一下验证码 向session中添加值 修复不点击时session中无验证码的bug BY HYP
		$("#createCheckCode").click();
	});
	function clickLogin(){
		var userid=$("#userNameDia",$.pdialog.getCurrent()).val();
		var pwd = $("#userPwdDia",$.pdialog.getCurrent()).val();
		var checkcode = $("#checkcode").val();
		if(isNulOrEmpty(pwd)){
			$("#relogin_info",$.pdialog.getCurrent()).html("密码不能为空!");
				return;
			}
			// 不校验验证码
			if(udmpUtil.checkCode && isNulOrEmpty(checkcode)){
				$("#relogin_info").text("验证码不能为空!");
				return;
			}
			$("#clickLogin").attr("disabled",　true);
			$.post('clickLogin_loginAction.action',  {userid:userid,orderUserid:"",pwd:hex_md5(pwd),checkcode:hex_md5(checkcode)},function(data){
				if(data.flag==300 && udmpUtil.checkCode){
					$("#relogin_info").text("验证码不正确!");
					document.getElementById("createCheckCode").src=document.getElementById("createCheckCode").src+"?nocache="+new Date().getTime();
				}else if(data.flag==200){
					//登录成功页面跳转到主页
					window.location.href = 'main.action';
					//登录成功，将用户存入Cookie
					document.cookie="git_userid="+data.id;
				}else if(data.flag==201){
					//startIntervalAgain();
					$.pdialog.closeCurrent();
					//登录成功，将用户存入Cookie
					document.cookie="git_userid="+data.id;
					$("body").unbind("keydown",escapeEnter);
				}else{
					if(data.flag==3) {
						$("#pwd").val("");
					}
					$("#relogin_info").text(data.msg);
				}
			},"json");
		}
	$("body").bind("keydown",escapeEnter);
	function escapeEnter(event){
		if(event.keyCode == 13){
			return false;
		}
	}
	function myReload(){
		document.getElementById("createCheckCode").src=document.getElementById("createCheckCode").src+"?nocache="+new Date().getTime();
	}
	$(function(){
		// 不校验验证码将验证码部分隐藏
		if(!udmpUtil.checkCode){
			$("#forCheckCodeDia").parents("p:first").css("display","none");
		}
	});
</script>
<div class="login_user">
	<div class="login_user_flied">
		<p>
			<label for="userNameDia" id="forUserNameDia">用户名：</label> <input
				type="text" id="userNameDia" readonly="readonly" />
		</p>
		<p>
			<label for="userPwdDia" id="foruserPwdDia">密&nbsp&nbsp&nbsp&nbsp码：</label>
			<input type="password" style="" id="userPwdDia" />
		</p>
		<p style="position: relative; top: 63px; left: 50px;">
			<label for="checkcode" id="forCheckCodeDia" style="font-size: 14px;">验证码：</label>
			<input type="text" id="checkcode"
				style="position: absolute; left: 90px; font-size: 14px;" />
		</p>
		<p id="relogin_info" style="position: absolute; top: 140px;">您的会话已超时，请重新登录！</p>
		<div class="relogin_bar"
			style="position: relative; top: 10px; left: 50px">
			<a href="#"><img
				src="udmp/dwz/themes/default/images/news/btn_login.png" alt="登录系统"
				name="Image3" width="81" height="38" border="0"
				onclick="clickLogin()" /> </a> <a href="#"><img
				src="udmp/dwz/themes/default/images/news/btn_re.png" name="Image4"
				width="81" height="38" border="0" id="resetInputDia" /> </a> <img
				src="${ctx}/PictureCheckCode" id="createCheckCode"
				style="border: #999999 solid 1px; height: 38px; width: 80px; margin-left: 0px; margin-top: 64px;"
				onClick="myReload()">
		</div>

	</div>
</div>