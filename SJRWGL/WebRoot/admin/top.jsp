<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<head>
<script type="text/javascript">
	$(function() {
       
		var hrefName = window.location.href;/*获取当前加载的页面路径  */
		if ("<%=basePath%>event/loadEvent.action" == hrefName) {
			$(".bar_index").css("background", "black");
			$(".bar_index").css("color", "white");
		}
		if ("<%=basePath%>admin/eventcl.jsp" == hrefName) {
			$(".bar_more").css("background", "black");
			$(".bar_more").css("color", "white");
			
		}
		if ("<%=basePath%>event/loadEvent.action?page=1" == hrefName) {
			$(".bar_index").css("background", "black");
			$(".bar_index").css("color", "white");
		}
	});
	function logout() {
		if (confirm('你确定要退出系统？'))
			window.parent.location = '<%=basePath%>admin/logout.action';
		return false;
	}
</script>

</head>
<div id="top">
	<div class="top_main">
		<ul class="top_menu">
			<span>易新云通</span>
		</ul>
		<ul class="top_bar">
			<a class="bar_index" href="<%=basePath%>event/loadEvent.action"
				target="main">首页</a>
			<a class="bar_more" href="<%=basePath%>admin/eventcl.jsp" target="main">更多</a>
			<a class="bar_exit" href="javascript:void(0);" onclick="javascript:logout();">退出</a>
		</ul>
	</div>
</div>
