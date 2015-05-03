<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="e" uri="/myTag"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN""http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>系统登录</title>
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
	src="<%=basePath%>/javascripts/jquery-1.8.2.js"></script>
<!-- jQuery Configuration -->
<script type="text/javascript"
	src="<%=basePath%>admin/resources/scripts/simpla.jquery.configuration.js"></script>
<!-- Facebox jQuery Plugin -->
<script type="text/javascript"
	src="<%=basePath%>admin/resources/scripts/facebox.js"></script>
<!-- jQuery WYSIWYG Plugin -->
<script type="text/javascript"
	src="<%=basePath%>admin/resources/scripts/jquery.wysiwyg.js"></script>
<script type="text/javascript">
	$(document).keyup(function(event) {
		if (event.keyCode == 13) {
			$("#submit").trigger("click");
		}
	});

	$(function() {
		$("#code").click(
				function() {
					$("#code").attr(
							"src",
							"admin/securityCodeImage.action?rd="
									+ Math.random());
				})
	});
	function check() {
		$(".input-notification.error").remove();
		var $uname = $("#uname");
		var $upword = $("#upword");
		var $vcode = $("#vcode");
		if ($uname.val() == "") {
			$uname
					.after("<span class='input-notification error png_bg' id='uname_error'>不能为空</span>");
			$uname.focus();
			return false;
		} else
			$("#uname_error").remove();
		if ($upword.val() == "") {
			$upword
					.after("<span class='input-notification error png_bg' id='upword_error'>不能为空</span>");
			$upword.focus();
			return false;
		} else
			$("#upword_error").remove();

		if ($vcode.val() == "") {
			$("#code")
					.after(
							"<span class='input-notification error png_bg' id='vcode_error'>不能为空</span>");
			$vcode.focus();
			return false;
		} else
			$("#vcode_error").remove();
		return true;
	};
</script>
</head>
<body id="login">
	<div id="login-wrapper" class="png_bg">
		<div id="login-top">

			<!-- Logo (221px width) -->
			<a href="#"><img id="logo"
				src="<%=basePath%>admin/resources/images/logo.png" alt="" /></a>
		</div>
		<!-- End #logn-top -->
		<div id="login-content">
			<div style="width:400px">
				<s:form action="admin_login" method="post">
					<fieldset>
						<p>
							<label for="uname">用户名:</label>
							<s:textfield id="uname" name="account" cssClass="text-input" />
						</p>
						<div class="clear"></div>
						<p>
							<label>密&nbsp;&nbsp;&nbsp;&nbsp;码：</label>
							<s:password id="upword" name="pwd" cssClass="text-input" />
						</p>
						<div class="clear"></div>
						<p>
							<label>验证码：</label>
							<s:textfield id="vcode" name="code" cssClass="text-input"
								cssStyle="width:120px;" />
							<img id="code"
								src="<%=basePath%>admin/securityCodeImage.action?rd=<%=Math.random()%>"
								alt="验证码" border="1"
								style="width:60px; height:23px;margin-left:10px;cursor:pointer;" title="看不清？点击更换另一个验证码。" />
						</p>
						<div class="clear"></div>
						<p>
							<s:submit value="登陆" cssStyle="margin-left:100px;"
								cssClass="button" onclick="return check()" />
							<s:reset cssClass="button" value="重填"
								cssStyle="margin-left:35px;" />
						</p>
						<div class="clear"></div>
					</fieldset>
				</s:form>
			</div>
		</div>
		<!-- End #login-content -->
	</div>
	<!-- End #login-wrapper -->
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
</body>
</html>
