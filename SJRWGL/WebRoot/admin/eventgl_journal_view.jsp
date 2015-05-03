<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="e" uri="/myTag"%>
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
<!--                       Javascripts                       -->
<!-- Bootstrap -->
        <link href="<%=basePath%>admin/resources/css/bootstrap.min.css" rel="stylesheet">
<!-- head部分css样式 -->
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>admin/resources/css/main.css" />
<link rel="stylesheet"
	href="<%=basePath%>admin/resources/fonts/font-awesome-4.2.0/css/font-awesome.min.css">
<!-- jQuery -->
<script type="text/javascript"
	src="<%=basePath%>javascripts/jquery-1.8.2.js"></script>
<script type="text/javascript"
	src="<%=basePath%>admin/resources/scripts/jquery.wysiwyg.js"></script>
<!-- jQuery Configuration -->
<script type="text/javascript"
	src="<%=basePath%>admin/resources/scripts/simpla.jquery.configuration.js"></script>
<!-- Facebox jQuery Plugin -->
<script type="text/javascript"
	src="<%=basePath%>admin/resources/scripts/facebox.js"></script>
<!-- jQuery Datepicker Plugin -->
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
				<h3>事件日志</h3>
				<ul class="content-box-tabs">
					<li><a href="#tab1" class="default-tab">日志详细</a></li>
				</ul>
				<div class="clear"></div>
			</div>
			<!-- End .content-box-header -->
			<div class="content-box-content">
				<div class="tab-content default-tab" id="tab1">
					<div style="margin-left:30%;">
						<s:hidden name="journalID" />
						<fieldset>

							<p>
								<label for="rzdate">日&nbsp;志&nbsp;时&nbsp;间：</label>
								<s:date name="rzdate" format="yyyy-MM-dd" />
							</p>
							<p>
								<label for="rzinfo">日&nbsp;志&nbsp;描&nbsp;述：</label>
								<s:property value="rzinfo" escapeHtml="false" />
							</p>
                            <p>
								<label for="journalFile">日&nbsp;志&nbsp;文&nbsp;档:</label>
								<s:if test='%{fileState==0}'>未上传</s:if>
							    <s:if test='%{fileState==1}'>已上传 </br>
							    <s:property value="fileName"/>
							     <a href="<%=basePath%>filedownload/fileDownload.action?pathName=<s:property value="filePath"/>" title="下载文档">下载文档</a>
							     </s:if>	
							
							</p>
							<p style="margin-left:20%;">
								<input class="button" type="button" value="返回"
									onclick="javascript:history.back();" />

							</p>
						</fieldset>

						<div class="clear"></div>
						<!-- End .clear -->
					</div>
				</div>
				<!-- End .content-box-content -->
			</div>
		</div>
		<!-- End .content-box -->
		<div class="clear"></div>
		<!-- End Notifications -->

		<!-- End #footer -->
	</div>
	<!-- End #main-content -->
</body>
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
<!-- Download From www.exet.tk-->
</html>

