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
<title>客户编辑</title>

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
<script type="text/javascript"
	src="<%=basePath%>admin/resources/scripts/jquery.wysiwyg.js"></script>
<!-- jQuery Configuration -->
<script type="text/javascript"
	src="<%=basePath%>admin/resources/scripts/simpla.jquery.configuration.js"></script>
<!-- Facebox jQuery Plugin -->
<script type="text/javascript"
	src="<%=basePath%>admin/resources/scripts/facebox.js"></script>
<script type="text/javascript"
	src="<%=basePath%>admin/resources/scripts/PCASClass.js"></script>
<!-- jQuery Datepicker Plugin -->
<script type="text/javascript">
	function check() {
		$(".input-notification.error").remove();
		var $username = $("#customerName");
		if ($username.val() == "") {
			$username
					.after("<span class='input-notification error png_bg' id='username_error'>不能为空</span>");
			$username.focus();
			return false;
		}

	};
	//页面加载使用
	window.onload = function() {
		var province = '${customer.province}';
		var city = '${customer.city}';
		var county = '${customer.county}';

		$("#province").val(province);
		$("#province").change();//模拟手动触发onchange事件之后才能把下拉框的值附上去

		$("#city").val(city);
		$("#city").change();
		$("#county").val(county);

	}
</script>
</head>
<body style="background: #f0f0f0">
<%@ include file ="/admin/top.jsp" %>
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
				<h3>客户编辑</h3>
				<div class="clear"></div>
			</div>
			<!-- End .content-box-header -->
			<div class="content-box-content">
				<div style="margin-left:40%;">
					<s:form action="updateCustomer">
						<s:hidden name="id" />
						<fieldset>
							<p>
								<label for="username">客户名称:</label>
								<s:textfield name="customerName" cssClass="text-input"
									id="customerName" size="40"></s:textfield>
								<span id="msg"></span>
							</p>



							<p>
								<label for="name">负&nbsp;责&nbsp;人:</label>   
								
								<s:textfield cssClass="text-input" id="person" name="person"
									size="40" />
							</p>

							<p>
								<label for="telephone">电话号码:</label>
								<s:textfield cssClass="text-input" id="number" name="number"
									size="40" />
							</p>
							<p>
								<label for="city">所&nbsp;在&nbsp;地&nbsp;区:</label>
							<fieldset style="padding:5px;">
								<%-- <s:select list="" name="province" id="province" ></s:select> --%>
								<select name="province" id="province" class="province"></select>
								<select name="city" id="city" class="city"></select> <select
									name="county" id="county" class="county"
									></select><br>
							</fieldset>
							<script language="javascript" defer>
								new PCAS("province", "city", "county");
							</script>
							</p>

							<p>
								<label for="email">详细地址:</label>
								<s:textfield cssClass="text-input" id="address" name="address"
									size="40" />
							</p>

							<p style="margin-left:20%;">
								<input class="button" type="submit" value="保存" id="submit"
									onclick="javascript:return check();" /> <input class="button"
									type="button" value="返回" onclick="javascript:history.go(-1)" />
							</p>
						</fieldset>
						<div class="clear"></div>
						<!-- End .clear -->
					</s:form>
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
