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
<title>用户编辑</title>

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
<!-- jQuery Datepicker Plugin -->
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
     
     var $email = $("#email");
 	// 验证邮箱的正则表达式
 	var reg = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
     if($email.val() != ""&&!reg.test($email.val())){
 		$email
 				.after("<span class='input-notification error png_bg' >邮箱格式不正确</span>");
 		$email.focus();
 		return false;
 	}
	return true;
};
</script>
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
			 <h3>用户编辑</h3>
				<div class="clear"></div>
			</div>
			<!-- End .content-box-header -->
			<div class="content-box-content">
					<div style="margin-left:40%;">
					<s:form action="updateUser" >
						<s:hidden name="id" />
						<fieldset>
								<p>
								<label for="username"> 账&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号:</label>
								<s:textfield name="account" cssClass="text-input" id="username" size="30" readonly="true"></s:textfield>	<span id="msg"></span>
							</p>
							<p>
								<label for="psword">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码:</label>
								<!--<s:password  cssClass="text-input" id="psword" name="pwd" size="30"/>-->
								 <s:password  id="upword" name="pwd" cssClass="text-input" size="30" showPassword="true"/>										
							</p>
							<s:if test='%{#session.admin.qxfp=="1"}'>
							<p>
								<label for="telephone">权&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;限:</label>
						
                                  <s:checkbox name="sjdj"  value="sjdj"  >事件登记</s:checkbox>
                                  <s:checkbox name="sjfp"  value="sjfp"  >事件分配</s:checkbox>
                                  <s:checkbox name="sjcl" value="sjcl"   >事件处理</s:checkbox>	
                                  <s:checkbox name="sjsh" value="sjsh"   >事件审核</s:checkbox>	
                                  <s:checkbox name="qxfp" value="qxfp"   >权限分配</s:checkbox>
                                  <s:checkbox name="khgl" value="khgl"   >客户管理</s:checkbox>
                                  <s:checkbox name="kfgl" value="kfgl"   >库房管理</s:checkbox>
							</p>
							<p>

								<label for="post">职&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;位:</label>
								<s:select list="#{'总经理':'总经理','经理':'经理','财务':'财务','总工程师':'总工程师','技术顾问':'技术顾问','销售经理':'销售经理','销售':'销售','工程师':'工程师','技术':'技术','开发':'开发','行政':'行政','商务助理':'商务助理'}" name="post" cssClass="small-input" id="post"></s:select>
							</p>
							</s:if>
							<s:else>
							<p>
								<label for="telephone">权&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;限:</label>
						
                                        <s:if test='%{sjdj=="1"}'>事件登记</s:if>
										<s:if test='%{sjfp=="1"}'>事件分配</s:if>
										<s:if test='%{sjcl=="1"}'>事件处理</s:if>
										<s:if test='%{sjsh=="1"}'>事件审核</s:if>
										<s:if test='%{qxfp=="1"}'>权限分配</s:if>
										<s:if test='%{khgl=="1"}'>客户管理</s:if>
										<s:if test='%{kfgl=="1"}'>库房管理</s:if>	
							</p>
							<s:hidden name="sjdj"></s:hidden>
							<s:hidden name="sjfp"></s:hidden>
							<s:hidden name="sjcl"></s:hidden>
							<s:hidden name="sjsh"></s:hidden>
							<s:hidden name="qxfp"></s:hidden>
							<s:hidden name="khgl"></s:hidden>
							<s:hidden name="kfgl"></s:hidden>
							<s:hidden name="post"></s:hidden>
							</s:else>
							<p>
								<label for="name">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名:</label>							
								<s:textfield cssClass="text-input" id="name" name="name" size="30"/>									
							</p>
						
							<p>
								<label for="telephone">电话号码:</label>
								<s:textfield cssClass="text-input" id="telephone" name="telephone" size="30"/>				
							</p>
							<p>
								<label for="email">电子邮件:</label>
								<s:textfield cssClass="text-input" id="email" name="email" size="30"/>				
							</p>	
							
							<p style="margin-left:20%;">
								<input class="button" type="submit" value="保存" id="submit"
									onclick="javascript:return check();" />
								<input class="button"type="button" value="返回" onclick="javascript:history.back();" /> 
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
		<s:fielderror/>
	</e:msgdialog>
</s:if>
<s:if test="hasActionErrors()">
	<e:msgdialog basepath="<%=basePath%>">
		<s:actionerror/>
	</e:msgdialog>
</s:if>
<s:if test="hasActionMessages()">
	<e:msgdialog basepath="<%=basePath%>">
		<s:actionmessage/>
	</e:msgdialog>
</s:if>
<!-- Download From www.exet.tk-->
</html>
