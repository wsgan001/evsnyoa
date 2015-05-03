<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div id="footer" style="text-align:right;">
			<!--  <small> <!-- Remove this notice or replace it with whatever you want -->
				<!--   &#169; Copyright 2014 Yixin Technology <br/>
				<p></p>
				 Powered by <a href="<%=basePath%>admin/main.jsp" target="main">evsny</a> -->
				 
				 <a href="javascript:void(0);" title="退出登陆" onclick="javascript:logout();"><img
												src="<%=basePath%>admin/resources/images/icons/logout2.png" title="退出登录"
												alt="退出" style="vertical-align:left;"/></a>
				 
				 <a href="<%=basePath%>event/loadEvent.action" target="main"><img
												src="<%=basePath%>admin/resources/images/icons/right.png" title="返回主页"
												alt="返回" style="vertical-align:left;"/></a>
				<!--  &#169; 点击进入 -->
				<a href="<%=basePath%>admin/eventcl.jsp" target="main"><img
												src="<%=basePath%>admin/resources/images/icons/left.png" title="其他内容"
												alt="内容" style="vertical-align:left;"/></a>
		
				 
		<!-- <a href="<%=basePath%>event/setEvent.action?eventID=<s:property value="id" />统计事件<img
												src="<%=basePath%>admin/resources/images/icons/pencil.png"
												alt="审核" style="vertical-align:left;"/> </a>  -->
		</div>