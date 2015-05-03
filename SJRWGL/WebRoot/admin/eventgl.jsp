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
<!-- Bootstrap -->
        <link href="<%=basePath%>admin/resources/css/bootstrap.min.css" rel="stylesheet">
<!-- head部分css样式 -->
 <link rel="stylesheet" type="text/css"
	href="<%=basePath%>admin/resources/css/main.css" />
<link rel="stylesheet"
	href="<%=basePath%>admin/resources/fonts/font-awesome-4.2.0/css/font-awesome.min.css">

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
<script language="javascript" type="text/javascript"
	src="<%=basePath%>javascripts/My97DatePicker/WdatePicker.js"
	charset="utf-8"></script>
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
	<%@ include file ="top.jsp" %>
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
				<div class="clear"></div>
			</div>
			<!-- End .content-box-header -->
			<div class="content-box-content">
				<div style="margin-left:40%;">
					<!--<s:form action="passEvent">-->
					<s:hidden name="eventID" />
					<fieldset>
						<p>
							<label for="eventCustomer">客&nbsp;户&nbsp;名&nbsp;称:</label>
							<s:property value="eventCustomer" /></br>
						</p>
						<p>
							<label for="eventProduct">产&nbsp;品&nbsp;名&nbsp;称:</label>
							<s:property value="eventProduct" /></br>
						</p>
						<p>
							<label for="eventType">事&nbsp;件&nbsp;类&nbsp;型:</label>
							<s:property value="eventType" /></br>
						</p>
						<p>
							<label for="eventDate">发&nbsp;生&nbsp;时&nbsp;间:</label>
							<s:date name="eventDate" format="yyyy-MM-dd" /></br>
						</p>
						<p style="width: 400px;">
							<label for="eventInfo">事&nbsp;件&nbsp;描&nbsp;述:</label>
							<s:if test='%{eventInfo==""}'>空</s:if>
							<s:else>
								<s:property value="eventInfo" /></br>
							</s:else>
						</p>
						<p>
							<label for="eventFile">文&nbsp;件&nbsp;状&nbsp;态:</label>
							<s:if test='%{fileState==0}'>未上传</s:if>
							<s:if test='%{fileState==1}'>已上传</s:if>
							</br>

						</p>
						 
						<p style="width: 400px;">
							<label for="eventEngineer">执&nbsp;&nbsp;&nbsp;行&nbsp;&nbsp;&nbsp;者:</label>
							<s:property value="eventEngineer" /></br>
						</p>
						<p>
							<label for="planTime1">计划完成时间:</label>
							<s:date name="planTime1" format="yyyy-MM-dd" /></br>
						</p>
                         
                          <p>
							<label for="clsjLate">是&nbsp;否&nbsp;延&nbsp;期:</label>
							<s:if test='%{clsjLate=="1"}'>已申请延期</s:if>
							<s:else>未申请延期</s:else></br>
						</p>
                         
						<p>
							<label for="completeState">完&nbsp;成&nbsp;状&nbsp;态:</label>
							<s:if test='%{completeState=="0"}'>未完成</s:if></br>
						</p>

						<p style="margin-right:40%;">
						<table width="98%" align="center" border="0" cellpadding="4"
							cellspacing="0" bgcolor="#CBD8AC" style="margin-bottom:8px">
							<tr bgcolor="#FFFFFF">
								<!-- <td width="10%" bgcolor="#FFFFFF">
								<a class="btn btn-default"
									href="<%=basePath%>admin/eventgl_rz.jsp?eventID=<s:property value="eventID"/>"
									target="main"><img
										src="<%=basePath%>admin/resources/images/icons/eventlog.png"
										alt="日志" style="vertical-align:left;" />填写日志</a></td> --> 
								<td width="10%" bgcolor="#FFFFFF">
								 <a class="btn btn-default" href="<%=basePath%>event/loadEventToLate.action?eventID=<s:property value="id" />&page=<s:property value="page"/>"
									target="main" title="延期">延期申请</td>
								<td width="10%" bgcolor="#FFFFFF">
								<a  class="btn btn-default"
									href="<%=basePath%>event/loadEventToApply.action?eventID=<s:property value="id" />&page=<s:property value="page"/>"
									title="申请">完成申请</a></td>
								<td width="10%" bgcolor="#FFFFFF">
								<a class="btn btn-default " href="javascript:history.back();">返回继续</a></td>
									<td width="10%" bgcolor="#FFFFFF"></td>
								<td width="20%" bgcolor="#FFFFFF"></td>
								<!-- <a href="<%=basePath%>admin/eventgl_apply_late.jsp" target="main"> -->
							</tr>
						</table>
						</p>
					</fieldset>
					<!--</s:form>-->
					<div class="clear"></div>
					<!-- End .clear -->
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