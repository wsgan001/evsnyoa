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
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>admin/resources/css/main.css" />
<link rel="stylesheet"
	href="<%=basePath%>admin/resources/fonts/font-awesome-4.2.0/css/font-awesome.min.css">

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
	<!-- 头部页面 -->
	  <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
          
          <a class="navbar-brand" href="<%=basePath%>event/loadEvent.action">易新云通</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse" >
          <ul class="nav navbar-nav"style="margin-left: 870px">
            <li><a href="<%=basePath%>event/loadEvent.action" target="main">首页</a></li>
            <li><a href="<%=basePath%>admin/eventcl.jsp" target="main">更多</a></li>
            <li><a href="javascript:void(0);"  onclick="javascript:logout();">退出</a></li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>
	<div id="main-content">
		<!-- Page Head -->

		<!-- End .shortcut-buttons-set -->
		<div class="clear"></div>
		<!-- End .clear -->
		<div class="content-box">
			<!-- Start Content Box -->
			<div class="content-box-header">
				<h3>基本信息</h3>
				<div class="clear"></div>
			</div>
			<!-- End .content-box-header -->
			<div class="content-box-content">
				<div class="tab-content default-tab">
					<table width="98%" align="center" border="0" cellpadding="4"
						cellspacing="0" bgcolor="#CBD8AC" style="margin-bottom:8px">
						<tr bgcolor="#FFFFFF">
							<td width="25%" bgcolor="#FFFFFF">您的职称：</td>
							<td width="75%" bgcolor="#FFFFFF"><span
								style="color: #880000"> <s:property
										value="#session.admin.post" /></span></td>
						</tr>
						<tr bgcolor="#FFFFFF">
							<td>登陆帐号：</td>
							<td><span style="COLOR: #880000">${sessionScope.admin.account}</span>
							</td>
						</tr>
						<tr bgcolor="#FFFFFF">
							<td>版本信息：</td>
							<td><span style="COLOR: #880000">Version.1.0</span></td>
						</tr>
					</table>
					<%--<table width="98%" align="center" border="0" cellpadding="4"
						cellspacing="0" bgcolor="#CBD8AC">
						
						<tr bgcolor="#FFFFFF">
							<td height="32">系统维护联系电话：</td>
							<td><a href="#" target="_blank"><u>028-87600455</u> </a></td>
						</tr>
					</table>
				--%>
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
