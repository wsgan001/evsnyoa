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
<title>事件分配</title>

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
<!-- jQuery Datepicker Plugin -->
<script type="text/javascript">
function check() {
	$(".input-notification.error").remove();
	var $eventEngineer = $("#eventEngineer");
	var $planTime1 = $("#planTime1");
	//var $planTime2 = $("#planTime2");	
	if ($eventEngineer.val() == "") {
		$eventEngineer
				.after("<span class='input-notification error png_bg'>不能为空</span>");
		$eventEngineer.focus();
		return false;
	} 
	
	if ($planTime1.val() == "") {
		$planTime1
				.after("<span class='input-notification error png_bg'>不能为空</span>");
		return false;
	}   
	/*if ($planTime2.val() == "") {
		$planTime2
				.after("<span class='input-notification error png_bg'>不能为空</span>");
		return false;
	}*/   
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
			  <h3>事件分配</h3>
				<div class="clear"></div>
			</div>
			<!-- End .content-box-header -->
			<div class="content-box-content">
				<div style="margin-left:40%;">
				<s:form action="dispatchEvent">
					
						<s:hidden name="eventID" />
						<fieldset>
							<p>
								<label for="eventCustomer">客&nbsp;户&nbsp;名&nbsp;称:</label>
								<s:property value="eventCustomer"/>
							</p>
							<p>
								<label for="eventProduct">产&nbsp;品&nbsp;名&nbsp;称:</label>
								<s:property value="eventProduct"/>
							</p>
							<p>
								<label for="eventType">事&nbsp;件&nbsp;类&nbsp;型:</label>
								<s:property value="eventType"/>
							</p>
							<p>
								<label for="eventDate">发&nbsp;生&nbsp;时&nbsp;间:</label>
								<%-- <s:textfield cssClass="text-input" id="eventDate"
									name="eventDate" size="30"
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" /> --%>
							    <s:date name="eventDate" format="yyyy-MM-dd"/>		
							</p>
							<p style="width: 400px;">
								<label for="eventInfo">事&nbsp;件&nbsp;描&nbsp;述:</label>
								 <s:if test='%{eventInfo==""}'>空</s:if>			
										 <s:else><s:property value="eventInfo"/></s:else>
							</p>
							<p>
								<label for="eventFile">文&nbsp;件&nbsp;状&nbsp;态:</label>
								<s:if test='%{fileState==0}'>未上传</s:if>
							    <s:if test='%{fileState==1}'>已上传</s:if>	
							</p>
                             <p>
								<label for="eventEngineer">执&nbsp;&nbsp;&nbsp;&nbsp;行&nbsp;&nbsp;&nbsp;&nbsp;者:</label>
								<s:select list="userlist" listKey="id" listValue="name" id="eventEngineerId" name="eventEngineerId" />
							 </p>
							 
							  <p>
								<label for="eventCheck">审&nbsp;&nbsp;&nbsp;&nbsp;核&nbsp;&nbsp;&nbsp;&nbsp;者:</label>
								<s:select list="userlist" listKey="id" listValue="name" id="eventShId" name="eventShId" />
							 </p>
							
							 
							<p>
								<label for="planTime1">第一完成时间:</label>
								<s:textfield cssClass="text-input" id="planTime1"
									name="planTime1" size="30"
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />		
							</p>
							
							<p style="margin-left:15%;">
								<input class="button" type="submit" value="分配" id="submit"
									onclick="javascript:return check();" />
								<input class="button" style="margin-left:5px;" type="button" value="返回" 
									onclick="javascript:history.back();" />
							</p>
						</fieldset>
						<div class="clear"></div>
						<!-- End .clear -->
					</s:form>
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
