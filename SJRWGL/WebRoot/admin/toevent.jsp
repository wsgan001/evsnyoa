<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="e" uri="/myTag"%>
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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body><s:iterator value="pageBean.list" status="item"><s:property value="id"/><s:property value="eventCustomer"/><s:property value="eventType"/>;<s:if test ='%{eventEngineer!=""}'><s:property value="eventEngineer"/></s:if><s:if test='%{dispatchState==0}'>未分配</s:if><s:if test='%{completeState==0&&applyState==1'>已完成未审核</s:if><s:if test='%{completeState==0&&applyState==0}'>未完成</s:if>|</s:iterator></body>
</html>
