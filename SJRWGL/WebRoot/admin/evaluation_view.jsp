<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
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
			  <h3>事件管理</h3>
				<div class="clear"></div>
			</div>
			<!-- End .content-box-header -->
			<div class="content-box-content">
			
			   <div style="margin-left:0.5%;">
                   <table class="solidTable">
                   <thead>
							<tr>						
								<th width="8%" >客户名称</th>
								<th width="8%" >事件类型</th>
								<th width="10%" >发生日期</th>
								<th width="8%" >完成时间</th>
								<th width="8%" >执行人</th>
								<th width="8%" >审核人</th>
								
							</tr>
					</thead>
                    <tbody>
                       <tr>
                           <td><s:property value="eventCustomer"/></td>
						    <td><s:property value="eventType"/></td>
							<td><s:date name="eventDate" format="yyyy-MM-dd"/></td>
							<td><s:date name="completeTime" format="yyyy-MM-dd"/></td>	
							<td><s:property value="eventEngineer"/></td>
							<td><s:property value="accountByShsjId.name"/></td>
					  </tr>
					</tbody>
				</table>
                </div>
                
                <div style="margin-left:2%;">
				<s:form action="addEvaluation" namespace="/evaluation" method="post">
				 <input type="hidden" name="eventID" value="${param.eventID}" /> 
						<fieldset>
							<p style="font-size:18px;">
								<label for="pjxm">评&nbsp;价&nbsp;项&nbsp;目</label>
								<label for="pjdj">&nbsp;&nbsp;评&nbsp;价&nbsp;等&nbsp;级</label>
						     </p>
						     <p>
							（评价采用十分制，优秀10分，良好7分，合格5分）
							</p>
						    <HR style="FILTER: progid:DXImageTransform.Microsoft.Shadow(color:#987cb9,direction:145,strength:15)" width="100%" color=#987cb9 SIZE=1>
							<p style="font-size:15px;">
							
							  <label >问&nbsp;题&nbsp;解&nbsp;决:</label>
							  
							  <s:radio name="wtjj" list="%{#{'1':'优秀','2':'良好','3':'合格'}}" value="'1'" ></s:radio>
							 </p>
							<p style="font-size:15px;">
								<label >是&nbsp;否&nbsp;延&nbsp;期:</label>
								<s:radio name="sfyq" list="%{#{'1':'未延期','2':'延期'}}" value="'1'" ></s:radio>
								
							</p>
							<p style="font-size:15px;">
								<label >技&nbsp;术&nbsp;难&nbsp;度:</label>
								 <s:radio name="jsnd" list="%{#{'1':'困难','2':'较难','3':'一般'}}"  value="'1'"></s:radio>
							    
							</p>
							<p style="font-size:15px;">
								<label >文&nbsp;档&nbsp;质&nbsp;量:</label>
								 <s:radio name="wdzl" list="%{#{'1':'优秀','2':'良好','3':'合格'}}" value="'1'" ></s:radio>
                               
							</p> 
							
							
							
                           <p style="width: 400px;">
								
							<p style="margin-left:20%;">
								<input class="button" type="submit" value="确定" 
									id="submit"/>
							</p>
							
						</fieldset>
						</s:form>
						<div class="clear"></div>
						<!-- End .clear -->
				</div>
                
                
                
				
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