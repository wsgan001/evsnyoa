<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<title>温馨提示</title>
<script language="javascript">
	var t = 3;
	function flushTime(){
		if (t==0){
			window.top.location="<%=basePath%>admin/login.jsp";
		} else {
			document.getElementById("num").innerHTML = t;
		}
		t = t - 1;
	}
	setInterval("flushTime();", 1000);
</script>
</head>
<body>
	<br />
	<br />
	<br />
	<table width="560" border="0" align="center" cellpadding="0"
		cellspacing="0">
		<tr>
			<td><img height="117"
				src="<%=basePath%>admin/resources/images/sorry.gif" width="174"></td>
			<td width="360"><p align="left" class="titleText">
					对不起，您的登录已经过期!<br> 请重新登录,谢谢! <span id="num">3</span>
				</p></td>
		</tr>
	</table>

</body>
</html>
