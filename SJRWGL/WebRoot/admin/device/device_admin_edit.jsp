<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ taglib prefix="e" uri="/myTag"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%@ page import="java.util.*"%>
<!-- 获取系统时间必须导入的  -->
<%@ page import="java.text.*"%>
<!--获取系统时间必须导入的 -->

<%
	String datetime = new SimpleDateFormat("yyyy-MM-dd")
			.format(Calendar.getInstance().getTime()); //获取系统时间
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>设备登记管理</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<!--                       CSS                       -->
<!-- Reset Stylesheet -->
<link rel="stylesheet" href="<%=basePath%>admin/resources/css/reset.css"
	type="text/css" media="screen" />
<!-- Main Stylesheet -->
<link rel="stylesheet" href="<%=basePath%>admin/resources/css/style.css"
	type="text/css" media="screen" />
<!-- Invalid Stylesheet. This makes stuff look pretty. Remove it if you want the CSS completely valid -->
<link rel="stylesheet"
	href="<%=basePath%>admin/resources/css/invalid.css" type="text/css"
	media="screen" />

<link rel="stylesheet"
	href="<%=basePath%>javascripts/kindeditor/themes/simple/simple.css"
	type="text/css" media="screen" />
<!-- head部分css样式 -->
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>admin/resources/css/main.css" />
<link rel="stylesheet"
	href="<%=basePath%>admin/resources/fonts/font-awesome-4.2.0/css/font-awesome.min.css">
	<link rel="stylesheet"
	href="<%=basePath%>admin/resources/css/step.css" type="text/css"
	media="screen" />
<!--                       Javascripts                       -->
<!-- jQuery -->
<script type="text/javascript"
	src="<%=basePath%>javascripts/jquery-1.8.2.js"></script>
<!-- jQuery Configuration -->
<script type="text/javascript"
	src="<%=basePath%>admin/resources/scripts/simpla.jquery.configuration.js"></script>
<!-- Facebox jQuery Plugin -->
<script type="text/javascript"
	src="<%=basePath%>admin/resources/scripts/facebox.js"></script>
<!-- jQuery Datepicker Plugin -->
<script type="text/javascript"
	src="<%=basePath%>admin/resources/scripts/jquery.wysiwyg.js"></script>
<script language="javascript" type="text/javascript"
	src="<%=basePath%>javascripts/My97DatePicker/WdatePicker.js"
	charset="utf-8"></script>



<script type="text/javascript">
	$(function() {
		var currentIndex = "${currentIndex}";
		var $tab = $(".content-box-tabs li a");
		var $tabContent = $(".tab-content");
		$(".content-box-tabs li a").removeClass("current");
		$tabContent.css("display", "none");
		if (currentIndex == '2') {
			$tab.eq(1).addClass("current");
			$tabContent.eq(1).css("display", "block");
		} else {
			$tab.eq(0).addClass("current");
			$tabContent.eq(0).css("display", "block");
		}
	});
	function check() { 
		$(".input-notification.error").remove();
		var $buyDate = $("#buyDate");
		var $guzhangInfo = $("#guzhangInfo");
		var $supplier = $("#supplier");
		var $planReturndate = $("#planReturndate");
		var $actualReturndate = $("#actualReturndate");
		var $retestHuman = $("#retestHuman");
		var $remarks = $("#remarks");
		var $visitinfo = $("#visitinfo");
		
		if ($buyDate.val() == "") {
			$buyDate
					.after("<span class='input-notification error png_bg'>不能为空</span>");
			return false;
		}

		if ($guzhangInfo.val() == "") {
			$guzhangInfo
					.after("<span class='input-notification error png_bg'>不能为空</span>");
			$guzhangInfo.focus();
			return false;
		}
		if ($supplier.val() == "") {
			$supplier
					.after("<span class='input-notification error png_bg'>不能为空</span>");
			$supplier.focus();
			return false;
		}
		if ($planReturndate.val() == "") {
			$planReturndate
					.after("<span class='input-notification error png_bg'>不能为空</span>");
			
			return false;
		}
		if ($actualReturndate.val() == "") {
			$actualReturndate
					.after("<span class='input-notification error png_bg'>不能为空</span>");
			return false;
		}
		if ($retestHuman.val() == "") {
			$retestHuman
					.after("<span class='input-notification error png_bg'>不能为空</span>");
			$retestHuman.focus();
			return false;
		}
		if ($remarks.val() == "") {
			$remarks
					.after("<span class='input-notification error png_bg'>不能为空</span>");
			$remarks.focus();
			return false;
		}
		if ($visitinfo.val() == "") {
			$visitinfo
					.after("<span class='input-notification error png_bg'>不能为空</span>");
			$visitinfo.focus();
			return false;
		}
		return true;
		
	};

</script>
</head>
<body style="background: #f0f0f0">
	<%@ include file="/admin/top.jsp"%>
	<div id="main-content">
		<!-- Main Content Section with everything -->
		<noscript>
			<!-- Show a notification if the user has disabled javascript -->
			<div class="notification error png_bg">
				<div>
					Javascript is disabled or is not supported by your browser. Please
					<a href="http://browsehappy.com/"
						title="Upgrade to a better browser">upgrade</a> your browser or <a
						href="http://www.google.com/support/bin/answer.py?answer=23852"
						title="Enable Javascript in your browser">enable</a> Javascript to
					navigate the interface properly. Download From <a
						href="http://www.exet.tk">exet.tk</a>
				</div>
			</div>
		</noscript>
		<!-- Page Head -->
		<!-- End .shortcut-buttons-set -->
		<div class="clear"></div>
		<!-- End .clear -->
		<div class="content-box">
			<!-- Start Content Box -->
			<div class="content-box-header">
				<h3>设备管理</h3>
				<ul class="content-box-tabs">
					<li><a href="#tab1" class="default-tab">设备登记</a></li>
				</ul>
				<div class="clear"></div>
			</div>
			<!--步骤模块start--------------  -->
			<div class="step_div">
			    <li><div class="step_circled" id="step_circled">1</div><img alt="步骤" src="<%=basePath%>admin/resources/images/step_jiantou.png"></li>	
			    <li><div class="step_circled" id="step_circled">2</div><img alt="步骤" src="<%=basePath%>admin/resources/images/step_jiantou.png"></li>	
			    <li><div class="step_circled" id="step_circled">3</div><img alt="步骤" src="<%=basePath%>admin/resources/images/step_jiantou.png"></li>
			    <li><div class="step_circled" id="step_circled">4</div><img alt="步骤" src="<%=basePath%>admin/resources/images/step_huishe_jiantou.png"></li>	
			    <li><div class="step_circle" id="step_circle">5</div></li>	
			</div>
			<div class="step_state_div">
			    <li><div class="step_state_one" id="step_stated">故障登记</div></li>		    
			    <li><div class="step_state_two" id="step_stated">故障描述</div></li>
			    <li><div class="step_state_third" id="step_stated">登记设备信息</div></li>
			    <li><div class="step_state_four" id="step_state">设备返修信息</div></li>
			    <li><div class="step_state_five" id="step_state">排故处理</div></li>
			</div>
			<!--步骤模块end--------------  -->
			<!-- End .content-box-header -->
			<div class="content-box-content">
				<div class="trouble_info_add_1"  id="tab1"  style="margin-left:40%;"> 

                    <form action="<%=basePath%>device/updateDeviceAdmin.action" method="post">  
                         <input type="hidden" name="eventID" value="${eventID}"/> 
						 <input type="hidden" name="id" value="${deviceID}"/>
							<fieldset>
							   <p>
									<label for="deviceName" id="deviceName">设&nbsp;&nbsp; 备&nbsp;&nbsp;&nbsp;名&nbsp;&nbsp;称:</label>
									<s:textfield name="deviceName" cssClass="text-input" id="deviceName" size="30"></s:textfield>
								</p>
							
								<p>
									<label for="deviceModel">设&nbsp;&nbsp; 备&nbsp;&nbsp;&nbsp;型&nbsp;&nbsp;号:</label>
									<s:textfield name="deviceModel" cssClass="text-input"
										id="deviceModel" size="30"></s:textfield>
								</p>
								<p>
									<label for="buyDate">购&nbsp;买&nbsp;日&nbsp;期:</label>
									<s:textfield cssClass="text-input" id="buyDate"
										name="buyDate" size="30"
										onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
										
										
								</p>
						        <p>
									<label for="isGuarantee">是否在保修期内：</label>
								    <s:radio list="#{'1':'是','0':'否'}" value="1" name="isGuarantee" id="isGuarantee" ></s:radio>	
								</p> 
							     <p>
									<label for="supplier">供&nbsp;应&nbsp;商：</label>
									<s:textfield name="supplier" cssClass="text-input"
										id="supplier" size="30"></s:textfield>
								</p>				
								 <p>
									<label for="planReturndate">预&nbsp;计&nbsp;返&nbsp;库日期:</label>
									<s:textfield cssClass="text-input" id="planReturndate"
										name="planReturndate" size="30"
										onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
								</p>
							    <p>
									<label for="actualReturndate">实际抵库日期:</label>
									<s:textfield cssClass="text-input" id="actualReturndate"
										name="actualReturndate" size="30"
										onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
										
								</p>
								<p>
									<label for="retestHuman">返修检测人:</label>
									<s:textfield cssClass="text-input" id="retestHuman"
										name="retestHuman" size="30" />
								</p>
								
								<p>
									<label for="remarks">备注:</label>
									<s:textfield cssClass="text-input" id="remarks"
									name="remarks" size="30" />
								</p>
								<p>
									<label for="visitInfo">回访:</label>
									<s:textfield cssClass="text-input" id="visitinfo"
										name="visitinfo" size="30" />
								</p>
							
							   <p>
					           <input class="button" type="submit" value="提交" id="submit"
									onclick="javascript:return check();" />
								<input class="button" type="button" onclick="javascript:history.back()" value="返回" />
								</p> 
							</fieldset>
							<div class="clear"></div>
							<!-- End .clear -->
						</form>
					</div> 
				</div>
				<hr>
					         
				
			</div>
            
			<div class="tab-content" id="tab2"></div>
			<!-- End .content-box-content -->
		</div>
		<!-- End .content-box -->
		<div class="clear"></div>
		<!-- End Notifications -->

		<!-- End #footer -->
	</div>
	<!-- End #main-content -->
	<s:if test="hasFieldErrors()">
		<e:msgdialog basepath="<%=basePath%>">
			<s:fielderror />
		</e:msgdialog>
	</s:if>
	<s:if test="hasActionErrors()">
		<e:msgdialog basepath="<%=basePath%>">
			<s:actionerror />
		</e:msgdialog>
	</s:if>
	<s:if test="hasActionMessages()">
		<e:msgdialog basepath="<%=basePath%>">
			<s:actionmessage />
		</e:msgdialog>
	</s:if>
</body>
<!-- Download From www.exet.tk-->
</html>