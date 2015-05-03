<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="e" uri="/myTag"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>事件管理</title>

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
	href="<%=basePath%>javascripts/kindeditor/themes/simple/simple.css" type="text/css"
	media="screen" />
<!--                       Javascripts                       -->
<!-- jQuery -->

<script type="text/javascript"
	src="<%=basePath%>javascripts/jquery-1.8.2.js"></script>
<script type="text/javascript"
	src="<%=basePath%>admin/resources/scripts/jquery.wysiwyg.js"></script>
<!-- jQuery Configuration -->
<script type="text/javascript"
	src="<%=basePath%>admin/resources/scripts/simpla.jquery.configuration.js"></script>
<!-- Facebox jQuery Plugin -->
<script type="text/javascript"
	src="<%=basePath%>admin/resources/scripts/facebox.js"></script>
	<script language="javascript" type="text/javascript" src="<%=basePath %>javascripts/My97DatePicker/WdatePicker.js" charset="utf-8"></script>
	<script type="text/javascript" src="<%=basePath%>javascripts/kindeditor/kindeditor-min.js"></script>
	<script>
	   
	   var options={
	                
	                resizeType : 1,
					allowPreviewEmoticons : false,
					allowImageUpload : false,
					items : [
						'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
						'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
						'insertunorderedlist', '|']
	   };
        KindEditor.ready(function(K) {
                window.editor = K.create('#editor_id',options);
        });
        </script>	
<!-- jQuery Datepicker Plugin -->
<script type="text/javascript">
function check() {
	$(".input-notification.error").remove();
	var $completeTime = $("#completeTime");
	var $completeState = $("#completeState");
	
	
	if ($completeTime.val() == "") {
		$completeTime
				.after("<span class='input-notification error png_bg'>不能为空</span>");
		return false;
	} 
	
	if ($completeState.val() == "") {
		$completeState
				.after("<span class='input-notification error png_bg'>不能为空</span>");
		$completeState.focus();
		return false;
	   }
		
	return true;
};
</script>
</head>
<body style="background: #f0f0f0">
    <%@ include file="/admin/top.jsp" %>
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
			  <h3>事件处理</h3>
			  <ul class="content-box-tabs">
					<li><a href="#tab1" class="default-tab">填写日志</a>
					</li>
				</ul>
				<div class="clear"></div>
			</div>
			<!-- End .content-box-header -->
			<div class="content-box-content">
			<div class="tab-content default-tab" id="tab1">
				<div style="margin-left:20%;">
					<s:form action="addDevice" namespace="/device">
					 <input type="hidden" name="paiguID" value="${param.paiguID}" /> 
						<form action="<%=basePath%>device/addDevice.action" method="post">

						<fieldset>
							<p>
								<label for="deviceName"> 产品名称：</label>
								<s:textfield name="deviceName" cssClass="text-input" id="deviceName" size="30" ></s:textfield>	<span id="msg"></span>
							</p>
							<p>
								<label for="deviceModel">产品型号:</label>
								<s:textfield  name="deviceModel" cssClass="text-input" id="deviceModel"  size="30"/>				
							</p>
							<p>
								<label for="guzhangInfo">故障现象:</label>
								<s:textfield cssClass="text-input" id="guzhangInfo" name="guzhangInfo" size="30"/>				
							</p>
							<p>

								<label for="protectNumber">产品保修号:</label>
								<s:textfield cssClass="text-input" id="protectNumber" name="protectNumber" size="30"/>

							</p>
							<p>

								<label for="supplierPnumber">供货商保修号:</label>
								<s:textfield cssClass="text-input" id="supplierPnumber" name="supplierPnumber" size="30"/>

							</p>
							<p>
								<label for="telephone">客户电话号码:</label>
								<s:textfield cssClass="text-input" id="telephone" name="telephone" size="30"/>				
							</p>
							
							<p>
								<label for="repairDate">返修日期:</label>
								<s:textfield cssClass="text-input" id="repairDate" name="repairDate" size="30"/>				
							</p>
							 <p>
								<label for="testHuman">设备检测人:</label>
								<s:textfield cssClass="text-input" id="testHuman" name="testHuman" size="30"/>				
							</p>
							
							<p>
								<label for="result">设备处理结果:</label>
								<s:textfield cssClass="text-input" id="result" name="result" size="30"/>				
							</p>
									
							
									
										
								<p style="margin-left:13%;">
									<input class="button" type="submit" value="确定" id="submit"
										onclick="javascript:return check();" /> <input class="button"
										type="reset" value="重填" /> <input class="button"
										type="button" value="返回"onclick="javascript:location.href='<%=basePath%>admin/loadUsersList.action'" />
								</p>
							</fieldset>
						</s:form>
						<div class="clear"></div>
						<!-- End .clear -->
				</div>
			</div>
			</div>
			<!-- End .content-box-content -->
		</div>
		<!-- End .content-box -->
		<div class="clear"></div>
		<!-- End Notifications -->
		
		<!-- End #footer -->
	</div>
	<!-- End #main-content -->
</body>
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
<!-- Download From www.exet.tk-->
</html>