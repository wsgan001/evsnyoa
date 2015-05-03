<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<!-- jQuery -->
<script type="text/javascript"
	src="<%=basePath%>javascripts/jquery-1.8.2.js"></script>
	
<base href="<%=basePath%>">
<title>主界面</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--                       CSS                       -->
<!-- Reset Stylesheet -->
<link rel="stylesheet" href="admin/resources/css/reset.css"
	type="text/css" media="screen" />
<!-- Main Stylesheet -->
<link rel="stylesheet" href="admin/resources/css/style.css"
	type="text/css" media="screen" />
<!-- Invalid Stylesheet. This makes stuff look pretty. Remove it if you want the CSS completely valid -->
<link rel="stylesheet" href="admin/resources/css/invalid.css"
	type="text/css" media="screen" />
<!-- Bootstrap -->
        <link href="<%=basePath%>admin/resources/css/bootstrap.min.css" rel="stylesheet">
<!-- head部分css样式 -->
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>admin/resources/css/main.css" />
<link rel="stylesheet"
	href="<%=basePath%>admin/resources/fonts/font-awesome-4.2.0/css/font-awesome.min.css">
<!--                       Javascripts                       -->
<!-- jQuery -->
<body style="background: #f0f0f0">
	<%@ include file ="top.jsp" %>
	<div id="main-content">
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
				<div class="tab-content default-tab">
					<table width="98%" align="center" border="0" cellpadding="4"
						cellspacing="0" bgcolor="#CBD8AC" style="margin-bottom:8px">
						<tr bgcolor="#FFFFFF">
							<td width="10%" bgcolor="#FFFFFF"><a
								href="<%=basePath%>event/loadPeriodicEventToDispatch.action" target="main"><img
									src="<%=basePath%>admin/resources/images/icons/eventdg.png"
									alt="统计" style="vertical-align:left;" />事件登记</a></td>
							<td width="10%" bgcolor="#FFFFFF"><a
								href="<%=basePath%>event/selectulist.action" target="main"><img
									src="<%=basePath%>admin/resources/images/icons/eventtj.png"
									alt="统计" style="vertical-align:left;" />事件统计</a></td>
							<!--<td width="10%" bgcolor="#FFFFFF"><a href="<%=basePath%>event/evaluationlist.action" target="main"><img
												src="<%=basePath%>admin/resources/images/icons/evaluation.png"
												alt="评价" style="vertical-align:left;"/>事件评价</a></td>-->
							<td width="10%" bgcolor="#FFFFFF"><s:if
									test='%{#session.admin.qxfp=="1"}'>
									<a href="<%=basePath%>admin/loadUsersList.action" target="main">
										<img
										src="<%=basePath%>admin/resources/images/icons/useradmin.png"
										alt="统计" style="vertical-align:center;" />用户管理
									</a>
								</s:if> <s:else>
									<a
										href="<%=basePath%>admin/loadUser.action?userId=<s:property value='#session.admin.id'/>"
										target='main'> <img
										src="<%=basePath%>admin/resources/images/icons/useradmin.png"
										alt="统计" style="vertical-align:center;" />用户管理
									</a>
								</s:else></td>
							<s:if test='%{#session.admin.khgl=="1"}'>
								<td width="10%" bgcolor="#FFFFFF"><a
									href="<%=basePath%>customer/loadCustomerList.action"
									target="main"> <img
										src="<%=basePath%>admin/resources/images/icons/customer.png"
										alt="统计" style="vertical-align:center;" />客户管理
								</a></td>
							</s:if>
							<s:if test='%{#session.admin.sjsh=="1"}'>		
							<td width="10%" bgcolor="#FFFFFF"><a
								href="<%=basePath%>periodicevent/loadAllPeriodicEvent.action?page=1" target="main"><img
									src="<%=basePath%>admin/resources/images/icons/eventdg.png"
									alt="统计" style="vertical-align:left;" />周期性事件浏览</a></td>
							</s:if>		
							<td width="10%" bgcolor="#FFFFFF"></td>

							<td width="60%" bgcolor="#FFFFFF"></td>
							<!--  <td width="75%" bgcolor="#FFFFFF"><span
								style="color: #880000"> <s:property value="#session.admin.post"/></span>
							</td>-->
						</tr>

					</table>

				</div>
			</div>
			<!-- End .content-box-content -->
		</div>
		<!-- End .content-box -->
		<div class="clear"></div>
		<!-- End Notifications -->
		
		<!-- End #footer -->
	</div>
</body>
</html>
