<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ taglib prefix="e" uri="/myTag"%> 
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
			
%>

<%@ page import="java.util.*"%> <!-- 获取系统时间必须导入的  -->
<%@ page import="java.text.*"%> <!--获取系统时间必须导入的 -->

<% 
String datetime=new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()); //获取系统时间 
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
<script type="text/javascript"
	src="<%=basePath%>admin/resources/scripts/jquery.wysiwyg.js"></script>
	<script language="javascript" type="text/javascript" src="<%=basePath %>javascripts/My97DatePicker/WdatePicker.js" charset="utf-8"></script>
	
   
<script type="text/javascript">
$(function(){
var currentIndex="${currentIndex}";
var $tab=$(".content-box-tabs li a");
var $tabContent=$(".tab-content");
$(".content-box-tabs li a").removeClass("current");
$tabContent.css("display","none");
if(currentIndex=='2'){
$tab.eq(1).addClass("current");
$tabContent.eq(1).css("display","block");
}
else {
$tab.eq(0).addClass("current");
$tabContent.eq(0).css("display","block");
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
           <h3>事件管理</h3>
				<ul class="content-box-tabs">
					<li><a href="#tab1" class="default-tab" >事件编辑</a>
					</li> 
					</ul>
				<div class="clear"></div>
			</div>
			<!-- End .content-box-header -->
			<div class="content-box-content">
				<div class="tab-content default-tab" id="tab1">
					<!-- This is the target div. id must match the href of this div's tab -->
                 	
					<div style="margin-left:40%;">
						<s:form action="updateEvent">
                         <s:hidden name="eventID"/>
							<fieldset>
								<p>
								<label for="eventCustomer">客&nbsp;户&nbsp;名&nbsp;称:</label>
								<s:textfield name="eventCustomer" cssClass="text-input" id="eventCustomer" size="30" ></s:textfield>
							</p>
							<p>
								<label for="eventProduct">产&nbsp;品&nbsp;名&nbsp;称:</label>
								<s:textfield cssClass="text-input" id="eventProduct" name="eventProduct" size="30"/>				
							</p>
							<p>
								<label for="eventType">事&nbsp;件&nbsp;类&nbsp;型:</label>
								<s:select list="#{'客户测试':'客户测试','内部测试':'内部测试','排故':'排故','实施':'实施','开发':'开发','讨论':'讨论','客户拜访':'客户拜访','客户回访':'客户回访'}" name="eventType" cssClass="small-input" id="eventType"></s:select>				
							</p>
							<p>
								<label for="eventDate">发&nbsp;生&nbsp;时&nbsp;间:</label>
								<s:textfield cssClass="text-input" id="eventDate" name="eventDate" size="30" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>				
							</p>
							<p>
								<label for="eventEngineer">执&nbsp;&nbsp;&nbsp;&nbsp;行&nbsp;&nbsp;&nbsp;&nbsp;者:</label>
								<s:select list="userlist" listKey="id" listValue="name" id="eventEngineerId" name="eventEngineerId" />
							 </p>
							<p>
								<label for="eventInfo">事&nbsp;件&nbsp;描&nbsp;述:</label>
								<s:textfield cssClass="text-input" id="eventInfo" name="eventInfo" size="30"/>
														                                                         
                             </p>	
                              <p>
								<label for="planTime1">第一完成时间:</label>
								<s:textfield cssClass="text-input" id="planTime1"
									name="planTime1" size="30"
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />		
							   </p>				
								<p style="margin-left:15%;">
									<input class="button" type="submit" value="确定" id="submit"
										onclick="javascript:return check();" /> <input class="button"
										type="reset" value="重填" /> <input class="button" type="button" value="返回" 
									onclick="javascript:history.back();" />
								</p>
							</fieldset>
							<div class="clear"></div>
							<!-- End .clear -->
						</s:form>
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
</body>
<!-- Download From www.exet.tk-->
</html>
