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
<title>导航栏</title>
<base href="<%=basePath%>">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" href="admin/resources/css/reset.css"
	type="text/css" media="screen" />
<!-- Main Stylesheet -->
<link rel="stylesheet" href="admin/resources/css/style.css"
	type="text/css" media="screen" />
<!-- Invalid Stylesheet. This makes stuff look pretty. Remove it if you want the CSS completely valid -->
<link rel="stylesheet" href="admin/resources/css/invalid.css"
	type="text/css" media="screen" />
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
<script type="text/javascript">
	var ap_name = navigator.appName;
	var ap_vinfo = navigator.appVersion;
	var ap_ver = parseFloat(ap_vinfo.substring(0, ap_vinfo.indexOf('(')));
	var time_start = new Date();
	var clock_start = "${sessionScope.startTime}";
	var dl_ok = false;
	function init() {
		if (ap_name == "Netscape" && ap_ver >= 3.0)
			dl_ok = true;
		return true;
	}
	function get_time_spent() {
		var time_now = new Date();
		return ((time_now.getTime() - clock_start) / 1000);
	}
	function show_secs() // show the time user spent on the side
	{
		var i_total_secs = Math.round(get_time_spent());
		var i_secs_spent = i_total_secs % 60;
		var i_mins_spent = Math.round((i_total_secs - 30) / 60);
		var s_secs_spent = ""
				+ ((i_secs_spent > 9) ? i_secs_spent : "0" + i_secs_spent);
		var s_mins_spent = ""
				+ ((i_mins_spent > 9) ? i_mins_spent : "0" + i_mins_spent);
		document.getElementById("time").innerHTML = s_mins_spent + ":"
				+ s_secs_spent;
		window.setTimeout('show_secs()', 1000);
	}
</script>
</head>

<body onLoad="init(); window.setTimeout('show_secs()',1);">
	<div id="body-wrapper">
		<!-- Wrapper for the radial gradient background -->
		<div id="sidebar">
			<div id="sidebar-wrapper">
				<!-- Sidebar with logo and menu -->
				<h1 id="sidebar-title">
					<a href="#"></a>
				</h1>
				<!-- Logo (221px wide) -->
				<a href="index.jsp" target="_blank"><img style="width:210px;"
					id="logo" src="admin/resources/images/logo.png" alt="LOGO" /></a>
				<!-- Sidebar Profile links -->

				<div id="profile-links">
					<a
						href="<%=basePath%>admin/loadUser.action?userId=<s:property value='#session.admin.id'/>"
						target='main'> 你好,
						${sessionScope.admin.post}--${sessionScope.admin.name}。</a><br />当前在线时间为<span
						style="color:red;" id="time"></span>。<br /> <br /> <a
						href="index.jsp" title="返回主页" target="_blank">其他</a> | <a
						href="javascript:void(0);" title="注销当前登陆账号"
						onclick="javascript:logout();">注销</a>
				</div>
				<ul id="main-nav">
					<!-- Accordion Menu -->
					<li>
						<!-- <a class="now-top-item current" href="<%=basePath%>event/loadEvent.action">事件任务管理</a> -->
						<s:if test='%{#session.admin.qxfp=="1"}'>
							<a class="nav-top-item current"> <!-- Add the class "no-submenu" to menu items with no sub menu-->
								系统任务管理
							</a>

							<ul>
								<s:if test='%{#session.admin.sjdj=="1"}'>
									<li><a href="<%=basePath%>event/loadEvent.action"
										target='main'>事件处理</a></li>
								</s:if>


								<s:if test='%{#session.admin.sjfp=="1"}'>
									<li><a
										href="<%=basePath%>event/loadUndispatchEvent.action"
										target='main'>事件分配</a></li>
								</s:if>
								<s:if test='%{#session.admin.sjsh=="1"}'>
									<li><a href="<%=basePath%>event/loadUnPassedEvent.action"
										target='main'>事件审核</a></li>
								</s:if>
								<s:if test='%{#session.admin.sjsh=="1"}'>
									<li><a href="<%=basePath%>event/loadCompletedEvent.action"
										target='main'>事件统计</a></li>
								</s:if>


							</ul>
					</li>
					</s:if>
					<s:if test='%{#session.admin.qxfp=="1"}'>
						<li><a class="nav-top-item">系统用户管理</a>
							<ul>
								<li><a href="<%=basePath%>admin/loadUsersList.action"
									target='main'>用户管理</a></li>
							</ul></li>
					</s:if>
					<s:else>
						<!-- <li> <a class="nav-top-item">用户信息管理</a>
          <ul>
            <li><a href="<%=basePath%>admin/loadUser.action?userId=<s:property value='#session.admin.id'/>" target='main'>个人信息</a></li>
          </ul>
        </li> -->
					</s:else>
					<!-- <li> <a class="nav-top-item">系统使用帮助</a>
          <ul>
            <li><a href="<%=basePath%>admin/main.jsp" target='main'>使用说明</a></li>
          </ul>
        </li> -->
				</ul>
				<!-- End #main-nav -->
			</div>
		</div>
	</div>
</body>
</html>
