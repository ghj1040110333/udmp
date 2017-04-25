<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="batch/pages/batchJob/batchJobCron.js"></script>

<script>
	function saveCron() {
		$("#jobFrequency").val($("#cronText").val());
		alertMsg.info("执行频率为:"+$("#jobFrequency").val());
		$.pdialog.closeCurrent();
	}
</script>

<body>

	<div class="tabs" eventtype="click" currentindex="0">
		<div class="tabsHeader">
			<div class="tabsHeaderContent">
				<ul>
					<li class="selected" width="20%"><a href="javascript:;"><span>分秒</span></a></li>
					<li class="" width="20%"><a href="javascript:;"><span>小时</span></a></li>
					<li class="" width="20%"><a href="javascript:;"><span>日</span></a></li>
					<li class="" width="20%"><a href="javascript:;"><span>月</span></a></li>
					<li class="" width="20%"><a href="javascript:;"><span>周</span></a></li>
				</ul>
			</div>
		</div>
		<div class="tabsContent" style="height: 180px;">
			<div style="display: block;">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="3%" height="27" align="left">
							<div align="left">
								<input name="fz" id="fz" type="radio" value="fz1"
									onclick="fz_radio_click(this)">
							</div>
						</td>
						<td width="22%" align="left">秒周期循环：从</td>
						<td width="15%" align="left"><div align="left">
								<input name="mks" id="mks" type="text" value="0" width="20px">
							</div></td>
						<td width="25%" align="left">秒钟开始，间隔</td>
						<td width="15%" align="left">
							<div align="left">
								<input name="mzx" id="mzx" type="text" value="0" length:10px>
							</div>
						</td>
						<td width="20%" align="left">秒钟执行一次</td>

					</tr>
					<tr>
						<td height="25">
							<div align="left">
								<input name="fz" id="fz" type="radio"
									onclick="fz_radio_click(this)" value="fz2" checked>
							</div>
						</td>
						<td height="25"><div align="left">分周期循环：从</div></td>
						<td height="25"><input name="fks" id="fks" type="text"
							value="0"/></td>
						<td height="25">分钟开始，间隔</td>
						<td height="25"><input name="fzx" id="fzx" type="text"
							value="1"></td>
						<td height="25">分钟执行一次</td>
					</tr>
					<tr>
						<td height="25"><input name="fz" id="fz" type="radio"
							value="fz3" onclick="fz_radio_click(this)"></td>
						<td height="25">手动指定</td>
						<td height="25">&nbsp;</td>
						<td height="25">&nbsp;</td>
						<td height="25">&nbsp;</td>
						<td height="25">&nbsp;</td>
					</tr>
					<tr>
						<td height="100" colspan="6" align="center">
							<table width="99%" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td><input type="checkbox" name="chk1" id="chk1" value="1"
										disabled="disabled"></td>
									<td>1</td>
									<td><input type="checkbox" name="chk2" id="chk2" value="2"
										disabled="disabled"></td>
									<td>2</td>
									<td><input type="checkbox" name="chk3" id="chk3" value="3"
										disabled="disabled"></td>
									<td>3</td>
									<td><input type="checkbox" name="chk4" id="chk4" value="4"
										disabled="disabled"></td>
									<td>4</td>
									<td><input type="checkbox" name="chk5" id="chk5" value="5"
										disabled="disabled"></td>
									<td>5</td>
									<td><input type="checkbox" name="chk6" id="chk6" value="6"
										disabled="disabled"></td>
									<td>6</td>
									<td><input type="checkbox" name="chk7" id="chk7" value="7"
										disabled="disabled"></td>
									<td>7</td>
									<td><input type="checkbox" name="chk8" id="chk8" value="8"
										disabled="disabled"></td>
									<td>8</td>
									<td><input type="checkbox" name="chk9" id="chk9" value="9"
										disabled="disabled"></td>
									<td>9</td>
									<td><input type="checkbox" name="chk10" id="chk10"
										value="10" disabled="disabled"></td>
									<td>10</td>
									<td><input type="checkbox" name="chk11" id="chk11"
										value="11" disabled="disabled"></td>
									<td>11</td>
									<td><input type="checkbox" name="chk12" id="chk12"
										value="12" disabled="disabled"></td>
									<td>12</td>
									<td><input type="checkbox" name="chk13" id="chk13"
										value="13" disabled="disabled"></td>
									<td>13</td>
									<td><input type="checkbox" name="chk14" id="chk14"
										value="14" disabled="disabled"></td>
									<td>14</td>
									<td><input type="checkbox" name="chk15" id="chk15"
										value="15" disabled="disabled"></td>
									<td>15</td>
								</tr>
								<tr>
									<td><input type="checkbox" name="chk16" id="chk16"
										value="16" disabled="disabled"></td>
									<td>16</td>
									<td><input type="checkbox" name="chk17" id="chk17"
										value="17" disabled="disabled"></td>
									<td>17</td>
									<td><input type="checkbox" name="chk18" id="chk18"
										value="18" disabled="disabled"></td>
									<td>18</td>
									<td><input type="checkbox" name="chk19" id="chk19"
										value="19" disabled="disabled"></td>
									<td>19</td>
									<td><input type="checkbox" name="chk20" id="chk20"
										value="20" disabled="disabled"></td>
									<td>20</td>
									<td><input type="checkbox" name="chk21" id="chk21"
										value="21" disabled="disabled"></td>
									<td>21</td>
									<td><input type="checkbox" name="chk22" id="chk22"
										value="22" disabled="disabled"></td>
									<td>22</td>
									<td><input type="checkbox" name="chk23" id="chk23"
										value="23" disabled="disabled"></td>
									<td>23</td>
									<td><input type="checkbox" name="chk24" id="chk24"
										value="24" disabled="disabled"></td>
									<td>24</td>
									<td><input type="checkbox" name="chk25" id="chk25"
										id="chk1" value="25" disabled="disabled"></td>
									<td>25</td>
									<td><input type="checkbox" name="chk26" id="chk26"
										value="26" disabled="disabled"></td>
									<td>26</td>
									<td><input type="checkbox" name="chk27" id="chk27"
										value="27" disabled="disabled"></td>
									<td>27</td>
									<td><input type="checkbox" name="chk28" id="chk28"
										value="28" disabled="disabled"></td>
									<td>28</td>
									<td><input type="checkbox" name="chk29" id="chk29"
										value="29" disabled="disabled"></td>
									<td>29</td>
									<td><input type="checkbox" name="chk30" id="chk30"
										value="30" disabled="disabled"></td>
									<td>30</td>
								</tr>
								<tr>
									<td><input type="checkbox" name="chk31" id="chk31"
										value="31" disabled="disabled"></td>
									<td>31</td>
									<td><input type="checkbox" name="chk32" id="chk32"
										value="32" disabled="disabled"></td>
									<td>32</td>
									<td><input type="checkbox" name="chk33" id="chk33"
										value="33" disabled="disabled"></td>
									<td>33</td>
									<td><input type="checkbox" name="chk34" id="chk34"
										value="34" disabled="disabled"></td>
									<td>34</td>
									<td><input type="checkbox" name="chk35" id="chk35"
										value="35" disabled="disabled"></td>
									<td>35</td>
									<td><input type="checkbox" name="chk36" id="chk36"
										value="36" disabled="disabled"></td>
									<td>36</td>
									<td><input type="checkbox" name="chk37" id="chk37"
										value="37" disabled="disabled"></td>
									<td>37</td>
									<td><input type="checkbox" name="chk38" id="chk38"
										value="38" disabled="disabled"></td>
									<td>38</td>
									<td><input type="checkbox" name="chk39" id="chk39"
										value="39" disabled="disabled"></td>
									<td>39</td>
									<td><input type="checkbox" name="chk40" id="chk40"
										value="40" disabled="disabled"></td>
									<td>40</td>
									<td><input type="checkbox" name="chk41" id="chk41"
										value="41" disabled="disabled"></td>
									<td>41</td>
									<td><input type="checkbox" name="chk42" id="chk42"
										value="42" disabled="disabled"></td>
									<td>42</td>
									<td><input type="checkbox" name="chk43" id="chk43"
										value="43" disabled="disabled"></td>
									<td>43</td>
									<td><input type="checkbox" name="chk44" id="chk44"
										value="44" disabled="disabled"></td>
									<td>44</td>
									<td><input type="checkbox" name="chk45" id="chk45"
										value="45" disabled="disabled"></td>
									<td>45</td>
								</tr>
								<tr>
									<td><input type="checkbox" name="chk46" id="chk46"
										value="46" disabled="disabled"></td>
									<td>46</td>
									<td><input type="checkbox" name="chk47" id="chk47"
										value="47" disabled="disabled"></td>
									<td>47</td>
									<td><input type="checkbox" name="chk48" id="chk48"
										value="48" disabled="disabled"></td>
									<td>48</td>
									<td><input type="checkbox" name="chk49" id="chk49"
										value="49" disabled="disabled"></td>
									<td>49</td>
									<td><input type="checkbox" name="chk50" id="chk50"
										value="50" disabled="disabled"></td>
									<td>50</td>
									<td><input type="checkbox" name="chk51" id="chk51"
										value="51" disabled="disabled"></td>
									<td>51</td>
									<td><input type="checkbox" name="chk52" id="chk52"
										value="52" disabled="disabled"></td>
									<td>52</td>
									<td><input type="checkbox" name="chk53" id="chk53"
										value="53" disabled="disabled"></td>
									<td>53</td>
									<td><input type="checkbox" name="chk54" id="chk54"
										value="54" disabled="disabled"></td>
									<td>54</td>
									<td><input type="checkbox" name="chk55" id="chk55"
										value="55" disabled="disabled"></td>
									<td>55</td>
									<td><input type="checkbox" name="chk56" id="chk56"
										value="56" disabled="disabled"></td>
									<td>56</td>
									<td><input type="checkbox" name="chk57" id="chk57"
										value="57" disabled="disabled"></td>
									<td>57</td>
									<td><input type="checkbox" name="chk58" id="chk58"
										value="58" disabled="disabled"></td>
									<td>58</td>
									<td><input type="checkbox" name="chk59" id="chk59"
										value="59" disabled="disabled"></td>
									<td>59</td>
									<td><input type="checkbox" name="chk0" id="chk0" value="0"
										disabled="disabled"></td>
									<td>0</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</div>
			<div style="display: none;">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="10" height="26"><div align="left">
								<input name="xs" id="xs" type="radio" value="xs1" checked
									onClick="xs_radio_click(this)">
							</div></td>
						<td width="97%" align="left"><div align="left">每一小时</div></td>
					</tr>
					<tr>
						<td height="20" width="10"><div align="left">
								<input name="xs" id="xs" type="radio" value="xs2"
									onClick="xs_radio_click(this)">
							</div></td>
						<td height="20" align="left"><div align="left">手动指定</div></td>
					</tr>
					<tr>
						<td height="100" colspan="2" align="center"><table
								width="99%" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td>AM：</td>
									<td><input type="checkbox" name="sj0" id="sj0" value="0"
										disabled="disabled"></td>
									<td>0</td>
									<td><input type="checkbox" name="sj1" id="sj1" value="1"
										disabled="disabled"></td>
									<td>1</td>
									<td><input type="checkbox" name="sj2" id="sj2" value="2"
										disabled="disabled"></td>
									<td>2</td>
									<td><input type="checkbox" name="sj3" id="sj3" value="3"
										disabled="disabled"></td>
									<td>3</td>
									<td><input type="checkbox" name="sj4" id="sj4" value="4"
										disabled="disabled"></td>
									<td>4</td>
									<td><input type="checkbox" name="sj5" id="sj5" value="5"
										disabled="disabled"></td>
									<td>5</td>
									<td><input type="checkbox" name="sj6" id="sj6" value="6"
										disabled="disabled"></td>
									<td>6</td>
									<td><input type="checkbox" name="sj7" id="sj7" value="7"
										disabled="disabled"></td>
									<td>7</td>
									<td><input type="checkbox" name="sj8" id="sj8" value="8"
										disabled="disabled"></td>
									<td>8</td>
									<td><input type="checkbox" name="sj9" id="sj9" value="9"
										disabled="disabled"></td>
									<td>9</td>
									<td><input type="checkbox" name="sj10" id="sj10"
										value="10" disabled="disabled"></td>
									<td>10</td>
									<td><input type="checkbox" name="sj11" id="sj11"
										value="11" disabled="disabled"></td>
									<td>11</td>
								</tr>
								<tr>
									<td>PM：</td>
									<td><input type="checkbox" name="sj12" id="sj12"
										value="12" disabled="disabled"></td>
									<td>12</td>
									<td><input type="checkbox" name="sj13" id="sj13"
										value="13" disabled="disabled"></td>
									<td>13</td>
									<td><input type="checkbox" name="sj14" id="sj14"
										value="14" disabled="disabled"></td>
									<td>14</td>
									<td><input type="checkbox" name="sj15" id="sj15"
										value="15" disabled="disabled"></td>
									<td>15</td>
									<td><input type="checkbox" name="sj16" id="sj16"
										value="16" disabled="disabled"></td>
									<td>16</td>
									<td><input type="checkbox" name="sj17" id="sj17"
										value="17" disabled="disabled"></td>
									<td>17</td>
									<td><input type="checkbox" name="sj18" id="sj18"
										value="18" disabled="disabled"></td>
									<td>18</td>
									<td><input type="checkbox" name="sj19" id="sj19"
										value="19" disabled="disabled"></td>
									<td>19</td>
									<td><input type="checkbox" name="sj20" id="sj20"
										value="20" disabled="disabled"></td>
									<td>20</td>
									<td><input type="checkbox" name="sj21" id="sj21"
										value="21" disabled="disabled"></td>
									<td>21</td>
									<td><input type="checkbox" name="sj22" id="sj22"
										value="22" disabled="disabled"></td>
									<td>22</td>
									<td><input type="checkbox" name="sj23" id="sj23"
										value="23" disabled="disabled"></td>
									<td>23</td>
								</tr>
							</table></td>
					</tr>
				</table>
			</div>
			<div style="display: none;">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="10" height="24"><div align="left">
								<input name="mt" id="mt" type="radio" value="mt1" checked
									onClick="mt_radio_click(this)">
							</div></td>
						<td width="97%" align="left"><div align="left">
								每一日（<span class="STYLE1">温馨提示：选择周循环则日循环失效！</span>）
							</div></td>
					</tr>
					<tr>
						<td height="20" width="10"><div align="left">
								<input name="mt" id="mt" type="radio" value="mt2"
									onClick="mt_radio_click(this)">
							</div></td>
						<td height="20" align="left"><div align="left">手动指定</div></td>
					</tr>
					<tr>
						<td height="100" colspan="2" align="center"><table
								width="100%" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td><input type="checkbox" name="t1" id="t1" value="1"
										disabled="disabled"></td>
									<td>1</td>
									<td><input type="checkbox" name="t2" id="t2" value="2"
										disabled="disabled"></td>
									<td>2</td>
									<td><input type="checkbox" name="t3" id="t3" value="3"
										disabled="disabled"></td>
									<td>3</td>
									<td><input type="checkbox" name="t4" id="t4" value="4"
										disabled="disabled"></td>
									<td>4</td>
									<td><input type="checkbox" name="t5" id="t5" value="5"
										disabled="disabled"></td>
									<td>5</td>
									<td><input type="checkbox" name="t6" id="t6" value="6"
										disabled="disabled"></td>
									<td>6</td>
									<td><input type="checkbox" name="t7" id="t7" value="7"
										disabled="disabled"></td>
									<td>7</td>
									<td><input type="checkbox" name="t8" id="t8" value="8"
										disabled="disabled"></td>
									<td>8</td>
									<td><input type="checkbox" name="t9" id="t9" value="9"
										disabled="disabled"></td>
									<td>9</td>
									<td><input type="checkbox" name="t10" id="t10" value="10"
										disabled="disabled"></td>
									<td>10</td>
									<td><input type="checkbox" name="t11" id="t11" value="11"
										disabled="disabled"></td>
									<td>11</td>
									<td><input type="checkbox" name="t12" id="t12" value="12"
										disabled="disabled"></td>
									<td>12</td>
									<td><input type="checkbox" name="t13" id="t13" value="13"
										disabled="disabled"></td>
									<td>13</td>
									<td><input type="checkbox" name="t14" id="t14" value="14"
										disabled="disabled"></td>
									<td>14</td>
									<td><input type="checkbox" name="t15" id="t15" value="15"
										disabled="disabled"></td>
									<td>15</td>
								</tr>
								<tr>
									<td><input type="checkbox" name="t16" id="t16" value="16"
										disabled="disabled"></td>
									<td>16</td>
									<td><input type="checkbox" name="t17" id="t17" value="17"
										disabled="disabled"></td>
									<td>17</td>
									<td><input type="checkbox" name="t18" id="t18" value="18"
										disabled="disabled"></td>
									<td>18</td>
									<td><input type="checkbox" name="t19" id="t19" value="19"
										disabled="disabled"></td>
									<td>19</td>
									<td><input type="checkbox" name="t20" id="t20" value="20"
										disabled="disabled"></td>
									<td>20</td>
									<td><input type="checkbox" name="t21" id="t21" value="21"
										disabled="disabled"></td>
									<td>21</td>
									<td><input type="checkbox" name="t22" id="t22" value="22"
										disabled="disabled"></td>
									<td>22</td>
									<td><input type="checkbox" name="t23" id="t23" value="23"
										disabled="disabled"></td>
									<td>23</td>
									<td><input type="checkbox" name="t24" id="t24" value="24"
										disabled="disabled"></td>
									<td>24</td>
									<td><input type="checkbox" name="t25" id="t25" value="25"
										disabled="disabled"></td>
									<td>25</td>
									<td><input type="checkbox" name="t26" id="t26" value="26"
										disabled="disabled"></td>
									<td>26</td>
									<td><input type="checkbox" name="t27" id="t27" value="27"
										disabled="disabled"></td>
									<td>27</td>
									<td><input type="checkbox" name="t28" id="t28" value="28"
										disabled="disabled"></td>
									<td>28</td>
									<td><input type="checkbox" name="t29" id="t29" value="29"
										disabled="disabled"></td>
									<td>29</td>
									<td><input type="checkbox" name="t30" id="t30" value="30"
										disabled="disabled"></td>
									<td>30</td>
								</tr>
								<tr>
									<td><input type="checkbox" name="t31" id="t31" value="31"
										disabled="disabled"></td>
									<td>31</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
								</tr>
							</table></td>
					</tr>
				</table>
			</div>
			<div style="display: none;">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td height="20" width="20"><div align="left">
								<input name="my" id="my" type="radio" value="my1" checked
									onClick="my_radio_click(this)">
							</div></td>
						<td width="570"><div align="left">每一月</div></td>
					</tr>
					<tr>
						<td height="20" width="20"><div align="left">
								<input name="my" id="my" type="radio" value="my2"
									onClick="my_radio_click(this)">
							</div></td>
						<td height="20"><div align="left">手动指定</div></td>
					</tr>
					<tr>
						<td height="100" colspan="2" align="center"><table
								width="95%" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td width="4%"><input type="checkbox" name="y1" id="y1"
										value="1" disabled="disabled"></td>
									<td width="11%">1</td>
									<td width="5%"><input type="checkbox" name="y2" id="y2"
										value="2" disabled="disabled"></td>
									<td width="12%">2</td>
									<td width="5%"><input type="checkbox" name="y3" id="y3"
										value="3" disabled="disabled"></td>
									<td width="12%">3</td>
									<td width="4%"><input type="checkbox" name="y4" id="y4"
										value="4" disabled="disabled"></td>
									<td width="13%">4</td>
									<td width="4%"><input type="checkbox" name="y5" id="y5"
										value="5" disabled="disabled"></td>
									<td width="13%">5</td>
									<td width="4%"><input type="checkbox" name="y6" id="y6"
										value="6" disabled="disabled"></td>
									<td width="13%">6</td>
								</tr>
								<tr>
									<td><input type="checkbox" name="y7" id="y7" value="7"
										disabled="disabled"></td>
									<td>7</td>
									<td><input type="checkbox" name="y8" id="y8" value="8"
										disabled="disabled"></td>
									<td>8</td>
									<td><input type="checkbox" name="y9" id="y9" value="9"
										disabled="disabled"></td>
									<td>9</td>
									<td><input type="checkbox" name="y10" id="y10" value="10"
										disabled="disabled"></td>
									<td>10</td>
									<td><input type="checkbox" name="y11" id="y11" value="11"
										disabled="disabled"></td>
									<td>11</td>
									<td><input type="checkbox" name="y12" id="y12" value="12"
										disabled="disabled"></td>
									<td>12</td>
								</tr>
							</table></td>
					</tr>
				</table>
			</div>
			<div style="display: none;">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td height="20" width="10"><input name="mzhou" id="mzhou" 
							type="checkbox" value="checkbox" onclick="zhou_checkbox_click()"></td>
						<td width="100%"><div align="left">
								每一周(<span class="STYLE1">温馨提示：选择周循环则日循环失效！</span>)
							</div></td>
					</tr>
					<tr>
						<td height="50" colspan="2" align="center"><table width="90%"
								border="0" cellpadding="0" cellspacing="0 ">
								<tr>
									<td width="51"><div align="left">
											<input name="mzh" id="mzh1" type="radio" disabled="disabled"
												value="mzh1" checked onclick="zhou_radio_click(this)">
										</div></td>
									<td colspan="14"><div align="left">每一周</div></td>
								</tr>
								<tr>
									<td><div align="left">
											<input name="mzh" id="mzh2" type="radio" value="mzh2"
												disabled="disabled" onclick="zhou_radio_click(this)">
										</div></td>
									<td colspan="14"><div align="left">手动指定</div></td>
								</tr>
								<tr>
									<td>&nbsp;</td>
									<td width="10"><input type="checkbox" name="week1"
										id="week1" value="1" disabled="disabled"></td>
									<td width="35">周一</td>
									<td width="10"><input type="checkbox" name="week2"
										id="week2" value="2" disabled="disabled"></td>
									<td width="35">周二</td>
									<td width="10"><input type="checkbox" name="week3"
										id="week3" value="3" disabled="disabled"></td>
									<td width="35">周三</td>
									<td width="10"><input type="checkbox" name="week4"
										id="week4" value="4" disabled="disabled"></td>
									<td width="35">周四</td>
									<td width="10"><input type="checkbox" name="week5"
										id="week5" value="5" disabled="disabled"></td>
									<td width="35">周五</td>
									<td width="10"><input type="checkbox" name="week6"
										id="week6" value="6" disabled="disabled"></td>
									<td width="35">周六</td>
									<td width="10"><input type="checkbox" name="week7"
										id="week7" value="7" disabled="disabled"></td>
									<td width="35">周日</td>
								</tr>
							</table></td>
					</tr>
				</table>
			</div>
		</div>
		<div class="tabsFooter">
			<div class="tabsFooterContent"></div>
		</div>
	</div>
	<div style="margin-top: 5px">

			<div>
				<table style="width: 100%;">
					<tr height="10">
						<td width="10%"></td>
						<td width="15%">秒</td>
						<td width="15%">分</td>
						<td width="15%">小时</td>
						<td width="15%">日</td>
						<td width="15%">月</td>
						<td width="15%">星期</td>
					</tr>
					<tr height="10">
						<td>字段值</td>
						<td><input type="text" id="cronSecond" readonly="true"
							style="width: 90%"></td>
						<td><input type="text" id="cronMinute" readonly="true"
							style="width: 90%"></td>
						<td><input type="text" id="cronHour" readonly="true"
							style="width: 90%"></td>
						<td><input type="text" id="cronDay" readonly="true"
							style="width: 90%"></td>
						<td><input type="text" id="cronMonth" readonly="true"
							style="width: 90%"></td>
						<td><input type="text" id="cronWeek" readonly="true"
							style="width: 90%"></td>
					</tr>
					<tr height="10">
						<td colspan="2">Cron表达式</td>
						<td colspan="3" align="left"><input type="text" id="cronText"
							readonly="true" style="width: 80%"></td>
						<td align="left"><input type="button" value="生成表达式"
							onClick="getvalues()"></td>
					</tr>
				</table>
			</div>
			<div class="formBar">
				<ul style="margin-top: -3px;">
					<li>
						<div class="buttonActive">
							<div class="buttonContent">
								<button onclick="saveCron()">保存</button>
							</div>
						</div>
					</li>
					<li>
						<div class="buttonActive">
							<div class="buttonContent">
								<button type="button" onclick="$.pdialog.closeCurrent();">取消</button>
							</div>
						</div>
					</li>
				</ul>
			</div>

	</div>
</body>