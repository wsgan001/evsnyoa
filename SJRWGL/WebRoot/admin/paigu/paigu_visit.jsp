<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'paigu_visit.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript">
function check() {
	$(".input-notification.error").remove();
	var $username = $("#username");
	var $psword = $("#psword");
	if ($username.val() == "") {
		$username
				.after("<span class='input-notification error png_bg' id='username_error'>不能为空</span>");
		$username.focus();
		return false;
	} 
	if ($psword.val() == "") {
		$psword
				.after("<span class='i nput-notification error png_bg' id='psword_error'>不能为空</span>");
		$psword.focus();
		return false;
	}
	var $telephone = $("#telephone");
	//验证电话格式
	 var reg1 =/^(\(\d{3,4}\)|\d{3,4}-)?\d{7,8}$/;
	 var reg2=/^[1][0-9]{10}$/;
     if($telephone.val() != ""){
     if(!(reg1.test($telephone.val())||reg2.test($telephone.val()))){
     $telephone
				.after("<span class='input-notification error png_bg' >电话格式不正确</span>");
		$telephone.focus();
		return false;
     }
		
	}
	</script>
  </head>
  
  <body>
     page. <br>
  </body>
</html>
