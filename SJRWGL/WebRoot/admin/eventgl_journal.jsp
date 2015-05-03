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
<title>日志管理</title>

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
<!-- Bootstrap -->
        <link href="<%=basePath%>admin/resources/css/bootstrap.min.css" rel="stylesheet">
<!-- head部分css样式 -->
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>admin/resources/css/main.css" />
<link rel="stylesheet"
	href="<%=basePath%>admin/resources/fonts/font-awesome-4.2.0/css/font-awesome.min.css">
<!--                       Javascripts                       -->
<!-- jQuery -->
<script type="text/javascript"
	src="<%=basePath%>javascripts/ckeditor/ckeditor.js"></script>
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
<%-- <script type="text/javascript">
	$(function() {
	var totalPage="${pageBean.totalPage}";
	var currentPage="${pageBean.currentPage}";
	$("#page").empty();
	for(var i=1;i<=parseInt(totalPage);i++){
	 $("#page").append("<option value="+i+">第" +i+"页</option>");
	};
	$("#page").val(currentPage);
	$("#page").change(function(){
	location.href="<%=basePath%>admin/loadUsersList.action?page="+ $("#page").val();
						})
		//alert(typeof parseInt(totalPage));

	
</script> --%>
</head>
<body style="background: #f0f0f0">
	
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
				<h3>
					<span style="color:grey"><s:property
							value="myevent.eventCustomer" /></span>的事件日志
				</h3>
				<ul class="content-box-tabs">
					<li><a href="#tab1" class="default-tab">日志管理</a></li>
				</ul>
				<div class="clear"></div>
			</div>
			<!-- End .content-box-header -->
			<div class="content-box-content">
				<div class="tab-content default-tab" id="tab1">
					<!-- This is the target div. id must match the href of this div's tab -->

					<table class="solidTable">
						<s:if test="%{pageBean.allRow>=0}">
							<thead>
								<tr>
									<!-- <th width="8%" ><input class="check-all" type="checkbox" />全选</th> -->
									<th width="8%">序 号</th>
									<th width="40%">日志描述</th>
									<th width="22%">已上传文档</th>
									<th width="10%">记录日期</th>
									<th width="20%">操 作</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="pageBean.list" status="item">
									<tr>
										<%-- <td ><input type="checkbox" name="checkList"
											value="<s:property value="id" />" />
										</td> --%>
										<td><s:property
												value="%{#item.count+10*(pageBean.currentPage-1)}" /></td>

										<td><s:property value="rzinfo" escapeHtml="false" /></td>
                                        <td><s:if test='%{fileState==0}'>无文档上传</s:if><s:else><s:property value="fileName"/></s:else></td>
										<td><s:date name="rzdate" format="yyyy-MM-dd" /></td>
										<td>
											<!-- Icons --> 
											<a
											href="<%=basePath%>journal/loadJournalToView.action?journalID=<s:property value="id" />&page=<s:property value="page"/>"
											title="查看">查看<img
												src="<%=basePath%>admin/resources/images/icons/view.gif"
												alt="查看" style="vertical-align:middle;" />
										   </a> 
										   <!-- <a href="<%=basePath%>admin/loadUser.action?userId=<s:property value="id" />&page=<s:property value="page"/>" title="编辑">编辑<img
												src="<%=basePath%>admin/resources/images/icons/pencil.png"
												alt="编辑" style="vertical-align:middle;"/> </a>
												<a href="<%=basePath%>admin/deleteUser.action?userId=<s:property value="id" />&page=<s:property value="page"/>" title="删除" onclick="javascript:return del();">删除<img
												src="<%=basePath%>admin/resources/images/icons/cross.png"
												alt="删除" style="vertical-align:middle;" /> </a> -->
										</td>
									</tr>
								</s:iterator>
							</tbody>
							<tfoot>
								<tr>
									<td colspan="8">

										<div class="pagination">
											共有
											<s:property value="pageBean.totalPage" />
											页，当前第&nbsp;
											<s:property value="pageBean.currentPage" />
											/
											<s:property value="pageBean.totalPage" />
											&nbsp;页， <a
												href="<%=basePath%>journal/loadJournalList.action?page=1&eventID=<s:property value="eventID" />"
												title="首页">&laquo;首页</a>
											<s:if test="%{pageBean.currentPage == 1}">
												<a href="javascript:void(0)"
													onclick="javascript:alert('已经是第一页！')">&laquo;上一页</a>
											</s:if>
											<s:else>
												<a
													href="<%=basePath%>journal/loadJournalList.action?page=<s:property value="%{pageBean.currentPage-1}"/>&eventID=<s:property value="eventID" />">&laquo;上一页</a>
											</s:else>
											<!--  <a href="#"
											class="number" title="1">1</a> <a href="#" class="number"
											title="2">2</a> <a href="#" class="number current" title="3">3</a>
										<a href="#" class="number" title="4">4</a>  -->
											<s:if test="%{pageBean.currentPage == pageBean.totalPage}">
												<a href="javascript:void(0)"
													onclick="javascript:alert('已经是最后一页！')">下一页&raquo;</a>
											</s:if>
											<s:else>
												<a
													href="<%=basePath%>journal/loadJournalList.action?page=<s:property value="%{pageBean.currentPage+1}"/>&eventID=<s:property value="eventID" />">下一页&raquo;</a>
											</s:else>
											<a
												href="<%=basePath%>journal/loadJournalList.action?page=<s:property value="pageBean.totalPage"/>&eventID=<s:property value="eventID" />">尾页&raquo;</a>
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
