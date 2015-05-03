<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ taglib prefix="e" uri="/myTag"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%@ page import="java.util.*"%>
<!-- 获取系统时间必须导入的  -->
<%@ page import="java.text.*"%>
<!--获取系统时间必须导入的 -->

<%
	String datetime = new SimpleDateFormat("yyyy-MM-dd")
			.format(Calendar.getInstance().getTime()); //获取系统时间
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>事件登记管理</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<!--                       CSS                       -->
<!-- Reset Stylesheet -->
<link rel="stylesheet" href="<%=basePath%>admin/resources/css/reset.css"
	type="text/css" media="screen" />
<!-- Main Stylesheet -->
<link rel="stylesheet" href="<%=basePath%>admin/resources/css/style.css"
	type="text/css" media="screen" />
<!-- Invalid Stylesheet. This makes stuff look pretty. Remove it if you want the CSS completely valid -->
<link rel="stylesheet"
	href="<%=basePath%>admin/resources/css/invalid.css" type="text/css"
	media="screen" />
<!--                       Javascripts                       -->
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
<!-- Bootstrap -->
        <link href="<%=basePath%>admin/resources/css/bootstrap.min.css" rel="stylesheet">
<!-- head部分css样式 -->
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>admin/resources/css/main.css" />
<link rel="stylesheet"
	href="<%=basePath%>admin/resources/fonts/font-awesome-4.2.0/css/font-awesome.min.css">
<script type="text/javascript"
	src="<%=basePath%>admin/resources/scripts/jquery.wysiwyg.js"></script>
<script language="javascript" type="text/javascript"
	src="<%=basePath%>javascripts/My97DatePicker/WdatePicker.js"
	charset="utf-8"></script>
<script type="text/javascript">



	$(function() {
		var currentIndex = "${currentIndex}";
		var $tab = $(".content-box-tabs li a");
		var $tabContent = $(".tab-content");
		$(".content-box-tabs li a").removeClass("current");
		$tabContent.css("display", "none");
		if (currentIndex == '2') {
			$tab.eq(1).addClass("current");
			$tabContent.eq(1).css("display", "block");
		} else {
			$tab.eq(0).addClass("current");
			$tabContent.eq(0).css("display", "block");
		}
	});
	function check() {
		$(".input-notification.error").remove();
		var $eventCustomer = $("#eventCustomer");
		var $eventProduct = $("#eventProduct");
		var $eventType = $("#eventType");
		var $eventDate = $("#eventDate");
		var $eventInfo = $("#eventInfo");
		var $eventFile = $("#eventFile");
		if ($eventCustomer.val() == "") {
			$eventCustomer
					.after("<span class='input-notification error png_bg'>不能为空</span>");
			$eventCustomer.focus();
			return false;
		}

		if ($eventProduct.val() == "") {
			$eventProduct
					.after("<span class='input-notification error png_bg'>不能为空</span>");
			$eventProduct.focus();
			return false;
		}
		if ($eventType.val() == "") {
			$eventType
					.after("<span class='input-notification error png_bg'>不能为空</span>");
			$eventType.focus();
			return false;
		}
		if ($eventDate.val() == "") {
			$eventDate
					.after("<span class='input-notification error png_bg'>不能为空</span>");
			return false;
		}
		/* if ($eventInfo.val() == "") {
			$eventInfo
					.after("<span class='input-notification error png_bg'>不能为空</span>");
			$eventInfo.focus();
			return false;
		}  */
		return true;
	};
	function Export(obj) {
		obj.form.action = 'event/export.action';
		obj.form.submit();
		return false;
	}
	function loadCompletedEvent(obj) {
		obj.form.action = 'event/loadCompletedEvent.action';
		obj.form.submit();
		return false;
	}

</script>

</head>
<body style="background: #f0f0f0">
  
	<!--<s:action name="loadEvent.action" namespace="/" executeResult="true"></s:action>-->
    <%@ include file ="top.jsp" %>
	<div id="main-content">
		<!-- Main Content Section with everything -->
		<noscript>
			<!-- Show a notification if the user has disabled javascript -->
			<div class="notification error png_bg">
				<div>
					Javascript is disabled or is not supported by your browser. Please
					<a href="http://browsehappy.com/"
						title="Upgrade to a better browser">upgrade</a> your browser or <a
						href="http://www.google.com/support/bin/answer.py?answer=23852"
						title="Enable Javascript in your browser">enable</a> Javascript to
					navigate the interface properly. Download From <a
						href="http://www.exet.tk">exet.tk</a>
				</div>
			</div>
		</noscript>
		<!-- Page Head -->
		<!-- End .shortcut-buttons-set -->
		<div class="clear"></div>
		<!-- End .clear -->
	
		<div class="content-box">
			<!-- Start Content Box -->
			<div class="content-box-header">
				<h3>事件管理</h3>
				<ul class="content-box-tabs">
					<li><a href="#tab1" class="default-tab">事件统计</a></li>

				</ul>
				<div class="clear"></div>
			</div>
			<!-- End .content-box-header -->
			<div class="content-box-content">

				<div class="tab-content default-tab" id="tab1">
					<!-- This is the target div. id must match the href of this div's tab -->
					<!--<s:action name="loadCompletedEvent.action" namespace="/" executeResult="true"></s:action>-->
					<!--<form action="<%=basePath%>event/loadCompletedEvent.action" method="post" enctype="multipart/form-data">-->
					<!--<s:action  name="loadCompletedEvent.action" executeResult="true"></s:action>-->
					<div style="margin-left:0.5%;">
						<!-- <form action="<%=basePath%>event/loadCompletedEvent.action"> -->
						<form>
							<s:if test='%{#session.admin.sjsh=="1"}'>
								<label for="search_eventEngineer" style="margin-left:20px;">&nbsp;执行者:</label>
								<s:select style="width:100px;text-align:center" list="userlist"
									listKey="id" listValue="name" name="search_eventEngineer"
									cssClass="text-input" id="search_eventEngineer"></s:select>
							</s:if>
							<label for="search_startDate" style="margin-left:40px;">开始时间:</label>
							<s:textfield cssClass="text-input"
								style="width:100px;text-align:center" id="search_startDate"
								name="search_startDate"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
							<label for="search_endDate" style="margin-left:20px;">结束时间:</label>
							<s:textfield cssClass="text-input"
								style="width:100px;text-align:center" id="search_endDate"
								name="search_endDate"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
							<!-- <input type="submit" value="查询" class="text-input" /> <input type="button" value="导出" class="text-input"/> -->
							<input type="button" value="查询" onclick="loadCompletedEvent(this)"> 
							<input type="button" value="导出" onclick="Export(this)"> 
							<input type="button" value="返回" onclick="javascript:history.back();"/>
						</form>

					</div>
					<div style="margin-left:0.5%;">
						<table class="solidTable">
							<s:if test="%{pageBean_tj.allRow>=0}">
								<thead>
									<tr>
										<th width="8%">序 号</th>
										<th width="8%">客户名称</th>
										<th width="12%">产品名称</th>
										<th width="8%">事件类型</th>
										<th width="10%">日期</th>
										<th width="15%">描述</th>
										<th width="8%">执行人</th>
										<th width="8%">完成时间</th>
										<th width="8%">审核人</th>
										<th width="15%">操 作</th>
									</tr>
								</thead>

								<tbody>
									<s:if test="date"></s:if>

									<s:iterator value="pageBean_tj.list" status="item">
										<tr>
											<td><s:property
													value="%{#item.count+10*(pageBean_tj.currentPage-1)}" /></td>

											<td><s:property value="eventCustomer" /></td>

											<td><s:property value="eventProduct" /></td>
											<td><s:property value="eventType" /></td>
											<td><s:date name="eventDate" format="yyyy-MM-dd" /></td>
											<td><s:if test='%{eventInfo==""}'>空</s:if> <s:else>
													<s:property value="eventInfo" />
												</s:else></td>
											<td><s:property value="eventEngineer" /></td>
											<td><s:date name="completeTime" format="yyyy-MM-dd" />
											</td>
											<td><s:property value="accountByShsjId.name" /></td>
											<td>
												<!-- Icons --> <a
												href="<%=basePath%>event/loadEventByTjView.action?eventID=<s:property value="id" />&page_tj=<s:property value="page_tj"/>"
												title="查看">查看<img
													src="<%=basePath%>admin/resources/images/icons/view.gif"
													alt="查看" style="vertical-align:middle;" />
											</a>
											</td>
										</tr>
									</s:iterator>
								</tbody>
                              <tfoot>
									<tr>
										<td colspan="10">

											<div class="pagination">
												共有
												<s:property value="pageBean_tj.totalPage" />
												页，当前第&nbsp;
												<s:property value="pageBean_tj.currentPage" />/
												<s:property value="pageBean_tj.totalPage" />
												&nbsp;页， <a
													href="<%=basePath%>event/loadCompletedEvent.action?page_tj=1&search_eventEngineer=<s:property value="search_eventEngineer"/>&search_endDate=<s:property value="search_endDate"/>&search_startDate=<s:property value="search_startDate"/>"
													title="首页">&laquo;首页</a>
												<s:if test="%{pageBean_tj.currentPage == 1}">
													<a href="javascript:void(0)"
														onclick="javascript:alert('已经是第一页！')">&laquo;上一页</a>
												</s:if>
												<s:else>
													<a
														href="<%=basePath%>event/loadCompletedEvent.action?page_tj=<s:property value="%{pageBean_tj.currentPage-1}"/>&search_eventEngineer=<s:property value="search_eventEngineer"/>&search_endDate=<s:property value="search_endDate"/>&search_startDate=<s:property value="search_startDate"/>">&laquo;上一页</a>
												</s:else>
												<!--  <a href="#"
											class="number" title="1">1</a> <a href="#" class="number"
											title="2">2</a> <a href="#" class="number current" title="3">3</a>
										<a href="#" class="number" title="4">4</a>  -->
												<s:if
													test="%{pageBean_tj.currentPage == pageBean_tj.totalPage}">
													<a href="javascript:void(0)"
														onclick="javascript:alert('已经是最后一页！')">下一页&raquo;</a>
												</s:if>
												<s:else>
													<a
														href="<%=basePath%>event/loadCompletedEvent.action?page_tj=<s:property value="%{pageBean_tj.currentPage+1}"/>&search_eventEngineer=<s:property value="search_eventEngineer"/>&search_endDate=<s:property value="search_endDate"/>&search_startDate=<s:property value="search_startDate"/>">下一页&raquo;</a>
												</s:else>
												<a
													href="<%=basePath%>event/loadCompletedEvent.action?page_tj=<s:property value="pageBean_tj.totalPage"/>&search_eventEngineer=<s:property value="search_eventEngineer"/>&search_endDate=<s:property value="search_endDate"/>&search_startDate=<s:property value="search_startDate"/>">尾页&raquo;</a>
											</div> <!-- End .pagination -->
											<div class="clear"></div>
										</td>
									</tr>
								</tfoot>
								
							</s:if>
							<s:else>

								<div class="notification attention png_bg">
									<a href="#" class="close"><img
										src="<%=basePath%>/admin/resources/images/icons/cross_grey_small.png"
										title="关闭这个注意内容。" alt="close" /></a>
									<div>查询的结果为空！</div>
								</div>
							</s:else>

						</table>
						<div class="clear"></div>
						<!-- End .clear -->

					</div>
				</div>
			</div>
			<!-- End .content-box-content -->
		</div>
		<!-- End .content-box -->
		<div class="clear"></div>
		<!-- End Notifications -->

		<!-- End #footer -->
	</div>
	<!-- End #main-content -->
	<s:if test="hasFieldErrors()">
		<e:msgdialog basepath="<%=basePath%>">
			<s:fielderror />
		</e:msgdialog>
	</s:if>
	<s:if test="hasActionErrors()">
		<e:msgdialog basepath="<%=basePath%>">
			<s:actionerror />
		</e:msgdialog>
	</s:if>
	<s:if test="hasActionMessages()">
		<e:msgdialog basepath="<%=basePath%>">
			<s:actionmessage />
		</e:msgdialog>
	</s:if>
</body>
<!-- Download From www.exet.tk-->
</html>
