<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
	/*
	*步骤: 
	*	1、取出后台数据，进行解析，显示数据 OK
	* 	2、更改数据，保存，提交保存到后台 OK
	*	3、切换月份，更改日历数据 OK
	*	4、对当天进行处理 
	*/
	var today = "";
	var holidayList = <%=request.getAttribute("holidayList")%>;
	var nowDate = "<%=request.getAttribute("nowDate")%>";
	var holidaydate = "<%=request.getAttribute("holidaydate")%>";
	$(document).ready(function() {
		//初始化日历
		initDate();
		//监听 td的click时间 添加或修改样式
		initListener();
	});
	function initListener() {
		$("#dateList td").click(
				function() {
					var tdclass = $(this).attr("class");
					//获取当前月份并且补齐日期
					var selectDate = holidaydate
							+ addZero(parseInt($(this).text().replace(
									/(\s*)|(\r)|(\n)/g, '')));
					if (selectDate <= nowDate) {
						//可以添加提示信息
						parent.alertMsg.warn("不能修改小于或等于当日日期");
					} else if (tdclass != "padding") {
						$(this).toggleClass("date_has_event");
					}
				});
	}

	function submitHoliday() {
		var str = "";
		//获取假期长度
		var holiday_length = $(".date_has_event").length;
		for (var i = 0; i < holiday_length; i++) {
			//获取当前月份并且补齐日期
			str += holidaydate.replace("-", "")
					+ addZero($($(".date_has_event")[i]).text().substring(0, 2)
							.replace(/(\s*)|(\r)|(\n)/g, '')) + ",";
		}
		str += today;
		str = str.substring(0, str.length - 1);
		var month = $("#holidaydate").val() == "" ? nowDate.substring(0, 4)
				: $("#holidaydate").val();
		if (month) {
			if (str) {
				$.ajax({
					type : "POST",
					url : "sys/saveWorkends_worksDayAction.action",
					data : {
						saveholidays : str,
						deleteYm : holidaydate
					},
					dataType : "json",
					success : function(data) {
						parent.DWZ.ajaxDone(data);
					}
				});
			}
		}
	}
	function searchHolidays() {
		if ($("#holidaydate").val()) {
			var ym = $("#holidaydate").val();
			$.ajax({
				type : "POST",
				url : "sys/queryWorkDay_worksDayAction.action",
				data : {
					queryYm : ym
				},
				dataType : "json",
				success : function(data) {
					if (data) {
						$('#monthLabel').html('当前月份：' + ym);
					}
					$("#dateList tbody tr").remove();
					holidaydate = ym;
					holidayList = data;
					initDate();
					initListener();
				}
			});
		} else {
			alertMsg.warn("月份不能为空！");
		}
	}
	//初始化日期展示位置
	function initDate() {
		today = "";
		//获得该月天数
		var nextMonth = new Date(holidaydate.substr(0, 4), holidaydate.substr(
				4, 2), 1);
		var dateCount = (new Date(nextMonth.getTime() - 1000 * 60 * 60 * 24))
				.getDate();
		//起始星期位置
		var start = new Date(holidaydate.substring(0, 4) + "/"
				+ holidaydate.substr(4, 2) + "/1").getDay();
		//结束时剩余位置
		var end = 7 - (start + dateCount) % 7;
		$('#monthLabel').html('当前月份：' + holidaydate);
		//循环拼接显示内容
		var content = "<tr>";
		if (start > 0) {
			content = content
					+ "<td class=\"padding\" colspan=\""+start+"\"></td>";
		}
		for (var i = 1; i <= dateCount; i++) {
			var hodate = holidaydate.substring(0, 4) + "/"
					+ holidaydate.substr(4, 2) + "/" + addZero(i);
			content = content + "<td class=" + getTrClass(hodate, i) + "</td>";
			if ((start + i) % 7 == 0) {
				content = content + "</tr><tr>";
			}
		}
		if (end != 7) {
			content = content
					+ "<td class=\"padding\" colspan=\""+end+"\"></td>";
		}
		content = content + "</tr>";
		$("#dateList tbody").html(content);
	}

	function getTrClass(hodate, thisDay) {
		var resultClass = "";
		if (holidayList == "" || holidayList == "null" || holidayList == null) {
			//获取该日期的星期
			var xq = new Date(hodate).getDay();
			//判断周末,添加样式
			if (xq == 0 || xq == 6) {
				resultClass = "\"date_has_event\">" + thisDay;
			} else {
				resultClass = ">" + thisDay;
			}
		} else {
			hodate = hodate.replace("/", "").replace("/", "");
			for (var i = 0; i < holidayList.length; i++) {
				//判断是否是特殊的日子，若是，为其添加样式
				if (hodate == holidayList[i].especialDay) {
					resultClass = "\"date_has_event\">" + thisDay;
					//判断是否是今天
					if (hodate == nowDate) {
						resultClass = "\"today\">" + thisDay;
					}
					//为特殊日子添加描述
					if (holidayList[i].dateDesc != ""
							&& holidayList[i].dateDesc != null) {
						resultClass += "<div class=\"events\"><ul><li><span class=\"title\">描述</span><span class=\"desc\">"
								+ holidayList[i].dateDesc
								+ "</span></li></ul></div>";
					}
					break;
				} else {
					resultClass = ">" + thisDay;
				}
			}
		}
		//判断当日
		hodate = hodate.replace("/", "").replace("/", "");
		if (hodate == nowDate) {
			resultClass = "\"today\">" + thisDay;
		}
		return resultClass;
	}

	//日期补0
	function addZero(da) {
		var addz = [ '01', '02', '03', '04', '05', '06', '07', '08', '09' ];
		if (da < 10) {
			return addz[da - 1];
		} else {
			return da
		}
	}
</script>
<div class="pageFormContent nowrap">
	<div class="divider"></div>
	<dl>
		<dt style="width: 50px;">日期：</dt>
		<dd>
			<input type="text" id="holidaydate" class="date" dateFmt="yyyyMM"
				name="setholidaybaseVo.holidaydate" readonly>

			<div class="buttonActive"
				style="margin-left: 10px; margin-top: -2px;">
				<div class="buttonContent">
					<button type="submit" onclick="searchHolidays()">查询</button>
				</div>
			</div>
		</dd>
	</dl>
	<div class="divider"></div>
</div>

<div class="pageContent">
	<div class="panelBar" style="display: block">
		<ul class="toolBar">
			<li><a class="edit" myoption="submitHoliday()" target="ajaxTodo"
				title="是否确认要保存节假日"><span>保存</span></a></li>
		</ul>
	</div>
	<p style="text-align: center;" id="monthLabel"
		style="height: 30px; font-size: 14px;"></p>
	<table cellspacing="0" id="dateList" class="bigCalendar"
		style="margin-left: auto; margin-right: auto">
		<thead>
			<tr>
				<th>星期日</th>
				<th>星期一</th>
				<th>星期二</th>
				<th>星期三</th>
				<th>星期四</th>
				<th>星期五</th>
				<th>星期六</th>
			</tr>
		</thead>
		<tbody>

		</tbody>
		<tfoot>
			<tr>
				<th>星期日</th>
				<th>星期一</th>
				<th>星期二</th>
				<th>星期三</th>
				<th>星期四</th>
				<th>星期五</th>
				<th>星期六</th>
			<tr>
		</tfoot>
	</table>
</div>