<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript">
	var userid = "<s:property value='#session.userId'/>";
	var timevar = 30000;
	var reCheckUserTime = 3000;
	var shutInterval;
	var gotoLonginFlag = 0;
	var noticeCountKey = "notice";
	var noticeCountVal = "";

	shutInterval = setInterval(getMessages, timevar);

	$(document).ready(function() {
		clock();
		//定时查询消息提醒
		setTimeout("getMessages()", 5000);//登陆后既开始扫描一次
		//setInterval('reCheckUser()', reCheckUserTime);
	});

	function clock() {
		now = new Date;
		month = now.getMonth();
		month = month + 1;
		year = now.getYear() < 1900 ? 1900 + now.getYear() : now.getYear()
		var clockStr = year + "-" + getDouble(month) + "-"
				+ getDouble(now.getDate()) + " " + getDouble(now.getHours())
				+ ":" + getDouble(now.getMinutes()) + ":"
				+ getDouble(now.getSeconds());
		$("#clockStr").text(clockStr);
		setTimeout("clock()", 1000);
	}

	function getDouble(number) {
		var numbers = [ "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" ];
		for (var i = 0; i < numbers.length; i++) {
			if (numbers[i] == number) {
				return "0" + numbers[i];
			} else if (i == 9) {
				return number;
			}
		}
	}

	function getMessages() {

	}

	//cookie放入userid
	function reCheckUser() {
		//当前用户
		var id = '${sessUser.userId}';
		//Cookie用户
		var val = getCookie("git_userid");
		//如果当前用户与Cookie用户不一样，就刷新页面
		if (id != val) {
			location.reload();
		}
	}
	function navigationManage() {
		$.get("${ctx}/navigation_loginAction.action?time="
				+ new Date().getTime(), function(data) {
			$('#fastNatList').html(data);
		});
	}

	function changeImg() {
		$("#altimg").attr("src",
				"udmp/dwz/themes/default/images/news/msg_icon.png");
		$("#taskNotice").attr(
				"href",
				"sys/showHornCountPage_hornPersonalSettingAction.action?cookieNotice="
						+ noticeCountVal);
		return true;
	}
	//手动启动消息提醒
	function startIntervalAgain() {
		clearInterval(shutInterval);
		shutInterval = setInterval(getMessages, timevar);
	}

	var orderUserid = '${sessUser.userName}';
	//检查是否要修改密码
	function checkIsModPwd() {
		$.post("checkIsForceModPwd_loginAction.action", null, function(data) {
			var obj = eval("(" + data + ")");
			if ('501' == obj.flag) {
				forceModPwd();
				//$("#forceModWarnMessage").text(obj.flag);
			}
		});
	}

	//强制修改密码  Knight 2013-04-26 add
	function forceModPwd() {
		if ($.pdialog) {
			$.pdialog.open("webapp/init/forceModPassWord.jsp", "login", "修改密码",
					{
						mask : true,
						maxable : false,
						resizable : false,
						drawable : false,
						width : 380,
						height : 300,
						isClose : false
					});
		} else {
			window.location = "webapp/init/forceModPassWord.jsp";
		}
	}

	function clickHomePageTitle(menuCode) {
		if (menuCode == 'index') {
			$("#homePageTitle").click();
		} else {
			$("#sidebar").find("a[rel=" + menuCode + "]").click();
		}
	}
	function clearListerInterval() {
		clearInterval(shutInterval);
	}
	/*
	 * 关闭当前浏览器的窗口
	 */
	function closeCurrWindow() {
		window.close();
	}
	/*
	 * 获取浏览器信息的函数 
	 */
	function browserinfo() {
		var Browser_Name = navigator.appName;
		var Browser_Version = parseFloat(navigator.appVersion);
		var Browser_Agent = navigator.userAgent;
		var Actual_Version, Actual_Name;
		var is_IE = (Browser_Name == "Microsoft Internet Explorer");//判读是否为ie浏览器
		var is_NN = (Browser_Name == "Netscape");//判断是否为netscape浏览器
		var is_op = (Browser_Name == "Opera");//判断是否为Opera浏览器
		if (is_NN) {
			//upper 5.0 need to be process,lower 5.0 return directly
			if (Browser_Version >= 5.0) {
				if (Browser_Agent.indexOf("Netscape") != -1) {
					var Split_Sign = Browser_Agent.lastIndexOf("/");
					var Version = Browser_Agent.lastIndexOf(" ");
					var Bname = Browser_Agent.substring(0, Split_Sign);
					var Split_sign2 = Bname.lastIndexOf(" ");
					Actual_Version = Browser_Agent.substring(Split_Sign + 1,
							Browser_Agent.length);
					Actual_Name = Bname
							.substring(Split_sign2 + 1, Bname.length);

				}
				if (Browser_Agent.indexOf("Firefox") != -1) {
					var Split_Sign = Browser_Agent.lastIndexOf("/");
					var Version = Browser_Agent.lastIndexOf(" ");
					Actual_Version = Browser_Agent.substring(Split_Sign + 1,
							Browser_Agent.length);
					Actual_Name = Browser_Agent.substring(Version + 1,
							Split_Sign);

				}
				if (Browser_Agent.indexOf("Safari") != -1) {
					if (Browser_Agent.indexOf("Chrome") != -1) {
						var Split_Sign = Browser_Agent.lastIndexOf(" ");
						var Version = Browser_Agent.substring(0, Split_Sign);
						var Split_Sign2 = Version.lastIndexOf("/");
						var Bname = Version.lastIndexOf(" ");
						Actual_Version = Version.substring(Split_Sign2 + 1,
								Version.length);
						Actual_Name = Version.substring(Bname + 1, Split_Sign2);
					} else {
						var Split_Sign = Browser_Agent.lastIndexOf("/");
						var Version = Browser_Agent.substring(0, Split_Sign);
						var Split_Sign2 = Version.lastIndexOf("/");
						var Bname = Browser_Agent.lastIndexOf(" ");
						Actual_Version = Browser_Agent.substring(
								Split_Sign2 + 1, Bname);
						Actual_Name = Browser_Agent.substring(Bname + 1,
								Split_Sign);

					}
				}

			} else {
				Actual_Version = Browser_Version;
				Actual_Name = Browser_Name;
			}
		} else if (is_IE) {
			var Version_Start = Browser_Agent.indexOf("MSIE");
			var Version_End = Browser_Agent.indexOf(";", Version_Start);
			Actual_Version = Browser_Agent.substring(Version_Start + 5,
					Version_End)
			Actual_Name = Browser_Name;

			if (Browser_Agent.indexOf("Maxthon") != -1
					|| Browser_Agent.indexOf("MAXTHON") != -1) {
				var mv = Browser_Agent.lastIndexOf(" ");
				var mv1 = Browser_Agent.substring(mv, Browser_Agent.length - 1);
				mv1 = "遨游版本:" + mv1;
				Actual_Name += "(Maxthon)";
				Actual_Version += mv1;
			}

		} else if (Browser_Agent.indexOf("Opera") != -1) {
			Actual_Name = "Opera";
			var tempstart = Browser_Agent.indexOf("Opera");
			var tempend = Browser_Agent.length;
			Actual_Version = Browser_Version;
		} else {
			Actual_Name = "Unknown Navigator"
			Actual_Version = "Unknown Version"
		}
		/*------------------------------------------------------------------------------
		 --Your Can Create new properties of navigator(Acutal_Name and Actual_Version) --
		 --Userage:                                                                     --
		 --1,Call This Function.                                                        --
		 --2,use the property Like This:navigator.Actual_Name/navigator.Actual_Version;--
		 --向对象中新增属性，用于在closewin函数中进行判断--
		 ------------------------------------------------------------------------------*/
		navigator.Actual_Name = Actual_Name;
		navigator.Actual_Version = Actual_Version;

		/*---------------------------------------------------------------------------
		 --Or Made this a Class.                                                     --
		 --Userage:                                                                  --
		 --1,Create a instance of this object like this:var browser=new browserinfo;--
		 --2,user this instance:browser.Version/browser.Name;                        --
		 ---------------------------------------------------------------------------*/
		this.Name = Actual_Name;
		this.Version = Actual_Version;
	}
	browserinfo();
	// document.write("你使用的浏览器是:"+navigator.userAgent);
	//document.write("<br>");
	// document.write("你使用的浏览器是:"+navigator.Actual_Name+",版本号:"+navigator.Actual_Version);

	function closewin() {
		alertMsg.confirm("确定退出登录么？",{okCall:function(){
			var type = "";
			$.ajax({
				url:'sys/logonOut_loginAction.action',
				cache:false,
				success:function(){
					// 点击确定时，跳转到登录页面
					window.location = 'login.action';
					// 取消原关闭浏览器选项卡页的操作
// 					if (navigator.userAgent.indexOf("MSIE") >= 0) {
// 						type = "MSIE";
// 						if (navigator.Actual_Version == "7.0") {
// 							type += "7";
// 						} else if (navigator.Actual_Version == "6.0") {
// 							type += "6";
// 						} else if (navigator.Actual_Version == "8.0") {
// 							type += "8";
// 						}
// 					} else if (navigator.userAgent.indexOf("Firefox") >= 0) {
// 						type = "FF";
// 					} else if (navigator.userAgent.indexOf("Chrome") >= 0){
// 						type = "Chrome";
// 					}
// 					if (type == "MSIE6") {
// 						window.opener = null;
// 						window.close();
// 					} else if (type == "MSIE7") {
// 						window.open('', '_top');
// 						window.top.close();
// 					} else if (type == "MSIE8") {
// 						window.open('', '_top');
// 						window.top.close();
// 					} else if (type == "FF") {
// 						window.open('', '_parent', '');
// 						window.close();
// 					} else {
// 						window.close();
// 					}
				},
				fail:function(){ 
					alertMsg.error("退出失败！");	
				}
			});
			
		}});
	}
</script>
<div id="header" style="visibility:hidden;">
	<div class="headerNav">
		<ul class="nav">
			<li><span class="date" id="clockStr"
				style="font-size: 13px; color: #8AA39C"></span></li>
			<li><a href="#">版本号：</a></li>
			<li><a href="#">用户名：</a></li>
			<li><a href="" id="taskNotice" myOption="changeImg()"
				target="dialog" width="300" height="300" rel="showMessage"
				title="待处理事件提醒" max="false" mask="false" maxable="true"
				minable="false">任务提醒</a></li>
			<li><a
				href="sys/showSettingPage_hornPersonalSettingAction.action"
				target="dialog" width="900" height="550" title="个人设置" max="false"
				mask="false" maxable="false" minable="false">个人设置</a></li>
			<li><a href="javascript:void(0);" onclick="closewin()">退出</a></li>
		</ul>
	</div>
	<div class="top_meun">
		<ul id="fastNatList">
			<li><a href="#" onclick="clickHomePageTitle('index')"><span>首页
				</span>
					<p>|</p></a></li>
			<s:iterator value="#session.meueNav">
				<li><a href="#" onclick="clickHomePageTitle('${menuId}')"><span>${menuName}</span>
						<p>|</p></a></li>
			</s:iterator>
		</ul>
	</div>
</div>