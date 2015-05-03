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
<title>事件分配</title>

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
<link href="<%=basePath%>admin/resources/css/bootstrap.min.css"
	rel="stylesheet">
<!-- head部分css样式 -->
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>admin/resources/css/main.css" />
<link rel="stylesheet"
	href="<%=basePath%>admin/resources/fonts/font-awesome-4.2.0/css/font-awesome.min.css">
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
	/* 对页面步骤流程图进行字体显示控制 */
	$(function() {
		var currentDispatchState = '${dispatchState}';
		var currentApplyState = '${applyState}';
		var currentSJFP = '${session.admin.sjfp}';
		if (currentDispatchState == "true" && currentApplyState == "false") {
			myDate = new Date();
			var aDate1, aDate2, oDate1, oDate2, iDays, planTime1, clsjLate, currentTime = "";
			clsjLate = '${clsjLate}';
			if (clsjLate != "true") {
				var planTimeTest = '${planTime1}';
				planTime1 = planTimeTest.split(" ")[0];//获取计划完成时间yyyy-MM-dd
				planCompleteTime = planTime1.split("-");
				//获取年月日
				currentTime = (myDate.getFullYear() + "-");
				currentTime = currentTime + ((myDate.getMonth() + 1) + "-");
				currentTime = currentTime + (myDate.getDate());
				//比较两个日期的大小，值得注意的是要把月份放在第一位
				if(Date.parse((myDate.getMonth() + 1)+"/"+myDate.getDate()+"/"+myDate.getFullYear())>Date.parse(
						planCompleteTime[1]+"/"+planCompleteTime[2]+"/"+planCompleteTime[0] 	)){
				oDate1 = planTime1.split("-");//截断为数组
				
				aDate1 = new Date(oDate1[1] + "/" + oDate1[2] + "/" + oDate1[0]);//重新排列,因为天数之差只有日,时，分，秒的概念，没有月和年，转为MM-dd-yyyy格式
				oDate2 = currentTime.split("-");
				aDate2 = new Date(oDate2[1] + '/' + oDate2[2] + '/' + oDate2[0]);
				var TotalMilliseconds = Math.abs(aDate2 - aDate1);//获取两个时间差的毫秒数  
				
				iDays = TotalMilliseconds / 1000 / 24 / 60 / 60;//获取相差天数 
				var BTNOT = Math.floor(iDays / 3);
				
			    if (BTNOT==0){
			    	$("#event_state").html(
					"<p>事</p><p>件</p><p>处</p><p>理</p><p>中</p>");
			    }
			    else{
				$("#event_state")
						.html(
								"<p>事</p><p>件</p><p>超</p><p>期</p><p>&nbsp;<font style='color:red;font-weight:bold;'>"
										+ BTNOT + "</font></p><p>次</p>");}
			}
				else{
					$("#event_state").html(
					"<p>事</p><p>件</p><p>处</p><p>理</p><p>中</p>");
				}
				} else {
				$("#event_state").html(
						"<p>事</p><p>件</p><p>处</p><p>理</p><p>中</p>");
			}
		} else if (currentDispatchState == "false" && currentSJFP == "true") {
			$("#event_state").html("<p>事</p><p>件</p><p>待</p><p>分</p><p>配</p>");
		} else if (currentApplyState == "true" && currentSJFP == "true") {
			$("#event_state").html(
					"<p><font style='color:green'>事</font></p><p><font style='color:green'>件</font></p><p><font style='color:green'>申</font></p><p><font style='color:green'>请</font></p><p><font style='color:green'>完</font></p><p><font style='color:green'>成</font></p>");
		}
	});

	/*获取当前时间并进行操作  */

</script>
<style type="text/css">
.event_state_info {
    position: absolute;
	width: 90px;
	height: 240px;
	border-radius: 5px;
	box-shadow: 5px 2px 6px #000;
}
.event_state_top{
    width: 91px;
	height: 28px;
	background-color: #000000;
	color: white;
    font-size: 18px;
    padding-top: 10px;
    
    text-align:center;
    border-radius: 5px 5px 0 0; 
}


.event_state {
	background: none repeat scroll 0 0 #F4F4F4;

	width: 40px;
	height: 190px;
	padding-top: 15px;
	padding-left: 35px;
	padding-right: 15px;
	color: black;
	float: left;

	position: relative;

    border-radius: 0 0  5px 5px; 

}

</style>

</head>
<body style="background: #f0f0f0">
	<%@ include file="top.jsp"%>
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
				<h3>事件查看</h3>
				<div class="clear"></div>
			</div>
			<!-- End .content-box-header -->
			<div class="content-box-content">

				<!--提示区域  -->
				<div class="event_state_info" id="event_state_info">
					<div class="event_state_top" id="event_state_top">状态</div>
					<div class="event_state" id="event_state"></div>
				</div>

				<div style="margin-left:38%;width: ">
					<s:hidden name="eventID"/>
					<form>
						<fieldset>
                           
							<p>
								<label for="eventCustomer">客&nbsp;户&nbsp;名&nbsp;称:</label>
								&nbsp&nbsp<s:property value="eventCustomer"/>
							</p>
							<p>
								<label for="eventProduct">产&nbsp;品&nbsp;名&nbsp;称:</label>
								&nbsp&nbsp<s:property value="eventProduct" />
							</p>

							<p>
								<label for="eventType">事&nbsp;件&nbsp;类&nbsp;型:</label>
								&nbsp&nbsp<s:property value="eventType" />
							</p>

							<p>
								<label for="eventDate">发&nbsp;生&nbsp;时&nbsp;间:</label>
								&nbsp&nbsp<s:date name="eventDate" format="yyyy-MM-dd" />
							</p>
							<p>
								<label for="dgdate">登&nbsp;记&nbsp;时&nbsp;间:</label>
								&nbsp&nbsp<s:date name="dgdate" format="yyyy-MM-dd EEE HH:mm:ss" />
							</p>
							

							<p style="width: 400px;">
								<label for="eventInfo">事&nbsp;件&nbsp;描&nbsp;述:</label>
								&nbsp&nbsp<s:property value="eventInfo" />
							</p>

							<p>
								<label for="eventEngineer">执&nbsp;行&nbsp;人:</label>
								&nbsp&nbsp<s:property value="eventEngineer" />
							</p>

							<p>
								<label for="eventEngineer">审&nbsp;核&nbsp;人:</label>
								&nbsp&nbsp<s:property value="visitName" />
							</p>
                             <p>
								<label for="planTime1">计划完成日期:</label>
								&nbsp&nbsp<s:date name="planTime1" format="yyyy-MM-dd" />
							</p>
                             <p style="color:red">
                               <label for ="clsjLate">是否申请延期：</label>
                               &nbsp&nbsp<s:if test='%{clsjLate=="1"}'>有延期申请</s:if>
							<s:else>无延期申请</s:else></br>
							
                             </p>
                             <p>
                               <s:if test='%{clsjLatehand=="1"}'><label>是否同意延期：已同意延期申请</label></s:if>
                                <s:else><label>是否同意延期： 无延期申请无审核</label></s:else>
                             </p>
                             
                             <p>
                             <label for="planTime3">延&nbsp期&nbsp时&nbsp间</label>
                             &nbsp&nbsp<s:date name="planTime3" format="yyyy-MM-dd"/>
                             </p>

							<p style="margin-right:30%;">
							<table width="98%" align="center" border="0" cellpadding="4"
								cellspacing="0" bgcolor="#CBD8AC" style="margin-bottom:8px">
								<tr bgcolor="#FFFFFF">
									<s:if test='%{dispatchState==1}'>
										<s:if test='%{applyState==0}'>
											<s:if test='%{#session.admin.name==eventEngineer}'>
												<td width="10%" bgcolor="#FFFFFF"><a
													class="btn btn-default"
													href="<%=basePath%>event/loadEventToHand.action?eventID=<s:property value="id"/>&page=<s:property value="page"/>"
													title="处理"><img
														src="<%=basePath%>admin/resources/images/headimages/deal.png"
														alt="处理事件" style="vertical-align:middle;" />处理事件</a></td>
											</s:if>
										</s:if>

									</s:if>

									<s:if test='%{applyState==0}'>
										<s:if test='%{#session.admin.sjdj=="1"}'>
											<td width="10%" bgcolor="#FFFFFF"><a
												class="btn btn-default "
												href="<%=basePath%>event/loadEventToEdit.action?eventID=<s:property value="id" />&page=<s:property value="page"/>"
												title="编辑"><img
													src="<%=basePath%>admin/resources/images/headimages/edit.png"
													alt="编辑事件" style="vertical-align:middle;" />编辑事件</a></td>
										</s:if>
									</s:if>

									<s:if test='%{dispatchState==0}'>
										<s:if test='%{#session.admin.sjfp=="1"}'>
											<td width="10%" bgcolor="#FFFFFF"><a
												class="btn btn-default "
												href="<%=basePath%>event/loadEventToDispatch.action?eventID=<s:property value="id" />&page=<s:property value="page"/>"
												title="分配"><img
													src="<%=basePath%>admin/resources/images/headimages/distrbute.png"
													alt="分配事件" style="vertical-align:middle;" />分配事件</a></td>
										</s:if>
									</s:if>

									<s:if test='%{clsjLate==1}'>
										<s:if test='%{#session.admin.sjsh=="1"}'>
											<s:if test='%{clsjLatehand=="0"}'>
												<s:if test='%{#session.admin.name==visitName}'>
													<td width="10%" bgcolor="#FFFFFF"><a
														class="btn btn-default "
														href="<%=basePath%>event/loadEventToLateHand.action?eventID=<s:property value="id" />&page=<s:property value="page"/>"
														title="审核"><img
															src="<%=basePath%>admin/resources/images/headimages/examine.png"
															alt="延期审核" style="vertical-align:middle;" />延期审核</a></td>
												</s:if>
											</s:if>
										</s:if>
									</s:if>

									<s:if test='%{applyState==1}'>
										<s:if test='%{#session.admin.sjsh=="1"}'>
											<s:if test='%{#session.admin.name==visitName}'>
												<td width="10%" bgcolor="#FFFFFF"><a
													class="btn btn-default "
													href="<%=basePath%>event/loadEventToPass.action?eventID=<s:property value="id" />&page=<s:property value="page"/>"
													title="审核"><img
														src="<%=basePath%>admin/resources/images/headimages/examine.png"
														alt="审核事件" style="vertical-align:middle;" />审核事件</a></td>
											</s:if>
										</s:if>
									</s:if>
									<s:if test='%{#session.admin.name==eventEngineer}'>
										<td width="10%" bgcolor="#FFFFFF"><a
											class="btn btn-default "
											href="<%=basePath%>journal/loadJournalListAdmin.action?eventID=<s:property value="id" />&page=<s:property value="page"/>"
											title="日志"><img
												src="<%=basePath%>admin/resources/images/headimages/read.png"

												alt="查看日志" style="vertical-align:middle;" />日志管理</a></td>

									</s:if>
									<s:else>
										<td width="10%" bgcolor="#FFFFFF"><a
											class="btn btn-default "
											href="<%=basePath%>journal/loadJournalList.action?eventID=<s:property value="id" />&page=<s:property value="page"/>"
											title="日志"><img

												src="<%=basePath%>admin/resources/images/headimages/read.png"
												alt="查看日志" style="vertical-align:middle;" />查看过程</a></td>
									</s:else>

									<s:if test='%{#session.admin.qxfp=="1"}'>
										<td width="10%" bgcolor="#FFFFFF"><a
											class="btn btn-default "
											href="<%=basePath%>event/deleteEvent.action?eventID=<s:property value="id" />&page=<s:property value="page"/>"
											title="删除" onclick="javascript:return del();"><img
												src="<%=basePath%>admin/resources/images/headimages/delete.png"
												alt="删除事件" style="vertical-align:middle;" />删除事件</a></td>
									</s:if>

									<td width="10%" bgcolor="#FFFFFF"><a
										class="btn btn-default "
										href="<%=basePath%>event/loadEvent.action"><img
											src="<%=basePath%>admin/resources/images/headimages/return.png"
											alt="返回继续" style="vertical-align:middle;" />返回继续</a></td>
									<td width="20%" bgcolor="#FFFFFF"></td>
									<!-- <a href="<%=basePath%>admin/eventgl_apply_late.jsp" target="main"> -->
								</tr>
							</table>
							</p>
							<!-- <p style="margin-left:20%;">
								<input class="button" type="button" value="返回" 
									onclick="javascript:history.back();" />
							</p>  -->

						</fieldset>
					</form>
					<div class="clear"></div>
					<!-- End .clear -->
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
