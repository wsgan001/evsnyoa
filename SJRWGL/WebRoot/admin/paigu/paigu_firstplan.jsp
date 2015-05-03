
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
<title>事件管理</title>

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
<link rel="stylesheet"
	href="<%=basePath%>javascripts/kindeditor/themes/simple/simple.css" type="text/css"
	media="screen" />
	<link rel="stylesheet"
	href="<%=basePath%>admin/resources/css/step.css" type="text/css"
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
	<script language="javascript" type="text/javascript" src="<%=basePath %>javascripts/My97DatePicker/WdatePicker.js" charset="utf-8"></script>
	<script type="text/javascript" src="<%=basePath%>javascripts/kindeditor/kindeditor-min.js"></script>
	<script>
	   
	   var options={
	                
	                resizeType : 1,
					allowPreviewEmoticons : false,
					allowImageUpload : false,
					items : [
						'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
						'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
						'insertunorderedlist', '|']
	   };
        KindEditor.ready(function(K) {
                window.editor = K.create('#editor_id',options);
        });
        </script>	
<!-- jQuery Datepicker Plugin -->
<script type="text/javascript">
function check() {
	$(".input-notification.error").remove();
	/* 在父iframe中获取子iframe中body的内容  contentWindow获取子iframe所以元素*/
 	var paigu = $(document.getElementsByTagName("iframe")[0].contentWindow.document.body).html();
	var guzhangType = $('input:radio[name="guzhangType"]:checked');
	 if (guzhangType.val()===undefined) {
		alert("请选择故障类型!");
		return false;
	   }
	 if (paigu== "") {
		 $(".paiguInfo").after("<span class='input-notification error png_bg'>不能为空</span>");
		return false;
	   }
	return true;
};
$(function(){
	$('input:radio[name="guzhangType"]').change(function(){
			var guzhangType = $('input:radio[name="guzhangType"]:checked').val();
			if(guzhangType=="软件故障"){alert("当前故障类型：无硬件故障!");}
			else{alert("当前故障类型：有"+guzhangType);} 
	});
	
})

</script>
</head>
<body style="background: #f0f0f0">
<%@ include file="/admin/top.jsp" %>
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
			  <h3>事件处理</h3>
			  <ul class="content-box-tabs">
					<li><a href="#tab1" class="default-tab">填写日志</a>
					</li>
				</ul>
				<div class="clear"></div>
			</div>
			<!-- End .content-box-header -->
			<div class="content-box-content">
			<!--步骤模块start--------------  -->
			<div class="step_div">
			    <li><div class="step_circled" id="step_circled">1</div><img alt="步骤" src="<%=basePath%>admin/resources/images/step_jiantou.png"></li>	
			    <li><div class="step_circled" id="step_circled">2</div><img alt="步骤" src="<%=basePath%>admin/resources/images/step_huishe_jiantou.png"></li>	
			    <li><div class="step_circle" id="step_circle">3</div><img alt="步骤" src="<%=basePath%>admin/resources/images/step_huishe_jiantou.png"></li>
			    <li><div class="step_circle" id="step_circle">4</div><img alt="步骤" src="<%=basePath%>admin/resources/images/step_huishe_jiantou.png"></li>	
			    <li><div class="step_circle" id="step_circle">5</div></li>	
			</div>
			<div class="step_state_div">
			    <li><div class="step_state_one" id="step_stated">故障登记</div></li>		    
			    <li><div class="step_state_two" id="step_stated">故障描述</div></li>
			    <li><div class="step_state_third" id="step_state">登记设备信息</div></li>
			    <li><div class="step_state_four" id="step_state">设备返修信息</div></li>
			    <li><div class="step_state_five" id="step_state">排故处理</div></li>
			</div>
			<!--步骤模块end--------------  -->
			<div class="tab-content default-tab" id="tab1">
				<div style="margin-left:20%;">
					<s:form action="paiguHand" namespace="/paigu" method="post" enctype="multipart/form-data">
					<!-- <input type="hidden" name="eventID" value="${param.eventID}" />  -->
					
					     <input type="hidden" name="paiguID" value="${paigu.id}"/>
					     
					<!--<s:hidden name="paiguID"/>-->
					
						<fieldset>
						    <p>
							<label for="guzhangType">故&nbsp;障&nbsp;类&nbsp;型:</label>	
							 <input type="radio" name="guzhangType" id="guzhangType" value="硬件故障"><label for="guzhangType">有硬件故障</label>
							 <input type="radio" name="guzhangType" id="guzhangType" value="软件故障"><label for="guzhangType">无硬件故障</label>
							</p>
							
						<%-- 	<s:select list="#{'软件故障':'软件故障','硬件故障':'硬件故障'}" name="guzhangType" cssClass="small-input" id="guzhangType"></s:select> --%>
							<p>
								<label for="paiguFile">文&nbsp;件&nbsp;上&nbsp;传:</label>
								<s:file id="paiguFile" name="paiguFile"/>				
							</p>	
							
							<p>
							 <label for="paiguInfo">详&nbsp;细&nbsp;处&nbsp;理&nbsp;步&nbsp;骤:</label>
							</p>
							<p id="paigu"><textarea id="editor_id" class="paiguInfo" name="paiguInfo" style="width:100px;height:200px;"></textarea></p>	
								 
				     
		                    
							<p style="margin-left:15%;">
									<input class="button" type="submit" value="提交日志" id="submit" onclick="javascript:return check();" /> 
	 
								   <input class="button" type="reset" value="重填日志" /> 
								   <!-- <input class="button" type="button" value="查看日志"/> -->
								   <input class="button"  type="button" value="返回上页" onclick="javascript:history.back();"/>
								</p>
					

						</fieldset>
						</s:form>
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