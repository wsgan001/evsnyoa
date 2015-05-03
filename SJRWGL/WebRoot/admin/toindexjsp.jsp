<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@include file="./isLogined.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>事件任务管理</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<frameset cols="*" rows="*" name="btFrame" frameborder="NO" border="0"
	framespacing="0">
	<!--  <frame src="<%=basePath%>admin/menu.jsp" noresize name="menu" scrolling="no"> -->

	<frame src="<%=basePath%>event/toloadevent.action" noresize name="main"
		scrolling="yes">
	<!--<frame src="<%=basePath%>admin/float.jsp" noresize name="float" scrolling="no">  -->
	<body>true
	</body>
</frameset>
<noframes>
	<body>浏览器不支持框架
	</body>
</noframes>
</html>
