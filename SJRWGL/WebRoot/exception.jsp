<%@ page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>系统异常信息</title>
</head>
<body style="padding:10px;background-color:#D6D3CE;">
<center><h2>系统异常信息</h2></center>
<font color="#FF0000"><b>输出异常信息内容</b></font><br/>
<textarea rows="22" cols="106">
	<!-- 输出异常信息内容 -->
	<s:property value="exception.message"/>
    <s:property value="exceptionStack"/>
</textarea>
</body>
</html>