<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script language="JavaScript">
 $(document).ready(function(){
    var listMenu = "<s:property value="#session.sessUser.navigationid"/>";
    if(listMenu !="-"){
       var arrayObj = new Array();　
        arrayObj = listMenu.split(",")
        for (j=0; j<arrayObj.length; j++){
         //将左侧的select的option选中
				 $("#initDept").children().filter('[value='+arrayObj[j]+']').attr("selected",true);
			}
			 //将选中的左侧菜单移到右侧
			 AppendItem('initDept', 'resultDept', false);
    }
 });
function LTrim(str) {
	var whitespace = new String("　 \t\n\r");
	var s = new String(str);
	if (whitespace.indexOf(s.charAt(0)) != -1) {
		var j = 0, i = s.length;
		while (j < i && whitespace.indexOf(s.charAt(j)) != -1) {
			j++;
		}
		s = s.substring(j, i);
	}
	return s;
}
 

function AppendItem(allMenu, menu, isAll) {
	for (j=0; j<document.getElementById(allMenu).length; j++){
		if (isAll == true || document.getElementById(allMenu).options[j].selected){
			//GET VALUE
			document.getElementById(allMenu).options[j].selected = false;
			//GET LENGTH
			DesLen = document.getElementById(menu).length;
			// NEW OPTION
			document.getElementById(menu).options[DesLen] = new Option(LTrim(document.getElementById(allMenu).options[j].text), document.getElementById(allMenu).options[j].value);
			document.getElementById(allMenu).remove(j);
			j--;
		}
	}
}

function ok(){
	var navigationid = "";
	var loop = 0;
	$("#resultDept option").each(function() {
	     loop++;
	     if(!isNaN($(this).val())){
       		navigationid += $(this).val()+",";
         }
  });	
  if(loop>7){
      alertMsg.warn('已选菜单最多选中<span style=\"color: red; font-size: 12px; font-weight: bold;\">7</span>个，请修改后再提交！')
      return;
    }
  
  navigationid = navigationid.substring(0,navigationid.length-1);
  
  $.post("${ctx}sys/updateUserNav_userAction.action?time=" + new Date().getTime(), {navigationid:navigationid}, myCallback, "json");
   
}
function myCallback(json){
	DWZ.ajaxDone(json);
	navigationManage();
}
</script>
<div class="" style="margin-top: 15px; overflow: hidden;">
		<table width="100%"
			style="background-color: #EEF4F5; border-right: solid 1px #aaccef; border-bottom: solid 1px #aaccef; border-top: solid 1px #aaccef; border-left: solid 1px #aaccef;"
			cellpadding="0" cellspacing="0" bgcolor="#f3f8fb" align="center">
			<tr height="40px">
				<td colspan="2" align="center">
				     <h1 style="color:#183152;margit-top:5px">待选菜单</h1>
				</td>
				<td colspan="2" align="center">
				    <h1 style="color:#183152;margit-top:5px">已选菜单</h1>
				</td>
			</tr>
			<tr>
				<td width="45%" align="center">
					<select id="initDept" name="initDept" size="20"
						style="width: 150px;" multiple="multiple"
						ondblclick="return AppendItem('initDept', 'resultDept', false);">
						<s:iterator value="meueNav">
							<option value="<s:property value='menuId' />" >
								<s:property value='menuName' />
								&nbsp;
							</option>
						</s:iterator>
					</select>
				</td>
				<td width="10%" align="center">
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="button"
								onClick="return AppendItem('initDept', 'resultDept', false);">
								==》
							</button>
							<br/>
						</div>
					</div>
					<div style="width: 20px;"></div>
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="button"
								onClick="return AppendItem('resultDept', 'initDept', false);">
								《==
							</button>
						</div>
					</div>
				</td>
				<td width="45%" align="center">
					<select id="resultDept" name="resultDepts" style="width: 150px;"
						size="20" multiple="true"
						ondblclick="return AppendItem('resultDept', 'initDept', false);"></select>
				</td>
			</tr>
			<tr height="30px">
				<td>
					&nbsp;
				</td>
				<td align="center" colspan=4 height="35px">
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="button" onclick="ok()" >
								确定
							</button>
						</div>
					</div>
				</td>
			</tr>
		</table>
	</form>
</div>
