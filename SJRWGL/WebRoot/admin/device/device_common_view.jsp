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
<title>用户管理</title>

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
	href="<%=basePath%>admin/resources/css/sendMailDiv.css" type="text/css"
	media="screen" />

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
<script language="javascript" type="text/javascript" src="<%=basePath %>javascripts/My97DatePicker/WdatePicker.js" charset="utf-8"></script>
		
<script type="text/javascript"
	src="<%=basePath%>admin/resources/scripts/PCASClass.js"></script>
	
	
	
<script type="text/javascript">
	$(function() {
	var totalPage="${pageBean.totalPage}";
	var currentPage="${pageBean.currentPage}";
	$("#page").empty();
	for(var i=1;i<=parseInt(totalPage);i++){
	 $("#page").append("<option value="+i+">第" +i+"页</option>");
	};
	$("#page").val(currentPage);
	$("#page").change(function(){
	location.href="<%=basePath%>customer/loadCustomerList.action?page="+ $("#page").val();
						});
		//alert(typeof parseInt(totalPage));

	
	});
	/*找到库管人员*/
	function findInventoryKeeper(){
		$.post('<%=basePath%>sendmail/findInventoryKeeper.action',function(result){
			if(result!=null){$("#recipients").val(result);}
			else {alert("获取内容失败！");}
		},"text");
	}
	/* 找到其他抄送人 */    
	function findAuditPerson() {
		$.post('<%=basePath%>sendmail/findAuditPerson.action',function(result){
			if(result!=null){$("#copyToMan").val(result);}
			else alert("获取内容失败！");
		},"text");

	}
	/*找到事件名称 */
	function findEventTheme() {
		var data = "eventID="+${eventID};
		$.post('<%=basePath%>sendmail/findEventNameById.action',data,function(result){
			if(result!=null){$("#mailTheme").val(result);}
			else alert("获取内容失败！");
		},"text");

	}
	/*找到设备内容*/
	function findEventDeviceContent() {
		var data = "eventID="+${eventID};
		$.post('<%=basePath%>sendmail/findEventDeviceInfoByEventId.action',data,function(result){
			if(result!=null){$("#mailContent").html(result);}
			else alert("获取内容失败！");
		},"text");
	}
	/*检查邮件内容、获取发件人、并且检查邮箱密码是否正确,用户点击提交弹出层  */
	function checkMailContentWithFindAddresser(){
		$.post('<%=basePath%>sendmail/findAddressor.action',function(result){
			if(result!=null){
				//弹出一个层，显示flag的文字	
				 $("#error_info").html("");
				 $("#light").animate({opacity:"show"}, "fast");
			     $("#fade").animate({opacity:"show"}, "fast");
                 $("#addressor").val(result); 
                 return false;
			}else{
				return false;
			    alert("获取内容失败！");
			}
		},"text");	
	}
	/*检查邮箱格式是否良好*/
	function checkMailFormat(recipients){
		var recipientsArr = recipients.split(",");
		var flag = true;
		//在JavaScript中，正 则 表达式只能使用"/"开头和结束，不能使用双引号 
		var re = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/; 
		for(var i = 0;i<recipientsArr.length;i++){
			if(re.test(recipientsArr[i])==true){  
			}else{ 
				$("#recipients").focus();
				flag = false; 
			}
		}
		return flag;
	}
	function checkMailContent(recipients,copyToMan,mailTheme,mailContent,addressor,addressorPassword){
		$(".input-notification.error").remove();
		if(recipients==""){
			$("#recipients").after("<span class='input-notification error png_bg'>不能为空</span>");
			$("#recipients").focus();
			return false;
		}
		if(mailTheme==""){
			$("#mailTheme").after("<span class='input-notification error png_bg'>不能为空</span>");
			$("#mailTheme").focus();
			return false;
		}
		if(mailContent==""){
			$("#mailContent").after("<span class='input-notification error png_bg'>不能为空</span>");
			$("#mailContent").focus();
			return false;
		}
		if(addressor==""){
			$("#addressor").after("<span class='input-notification error png_bg'>不能为空</span>");
			$("#addressor").focus();
			return false;
		}
		if(addressorPassword==""){
			$("#addressorPassword").after("<span class='input-notification error png_bg'>不能为空</span>");
			$("#addressorPassword").focus();
			return false;
		}
		var isPass = checkMailFormat(recipients);
		if(copyToMan!=null){
		isPass = checkMailFormat(copyToMan);
		}
		isPass = checkMailFormat(addressor);
		if(isPass){
			return true;
		}else{
			alert("邮箱地址不符合标准！");
			return false;
		}
	}
	/* 检查内容并发送邮件 */
	function sendMail(){
		var recipients = $("#recipients").val();
		var copyToMan = $("#copyToMan").val();
		var mailTheme = $("#mailTheme").val();
		var mailContent = $("#mailContent").val();
		var addressor = $("#addressor").val();
		var addressorPassword = $("#addressorPassword").val();
		var mailIsEmpty=checkMailContent(recipients,copyToMan,mailTheme,mailContent,addressor,addressorPassword);
		if(mailIsEmpty){
		limitButton();
		var data ="recipients="+recipients+" &copyToMan="+copyToMan+" &mailTheme="+mailTheme+" &mailContent="+mailContent+" &addressor="+addressor+" &addressorPassword="+addressorPassword;
		$.post('<%=basePath%>sendmail/sendMail.action',data,function(result){
			if(result!=null){
				if(result=="addressError"){
					$("#error_info").html("邮件收件人错误!");
					abolishLimitButton();
				}else if(result=="messageError"){
					$("#error_info").html("邮箱地址或密码错误!");
					abolishLimitButton();
				}else if(result=="sendMessageSuccess"){
					$("#error_info").html("");
					alert("发送邮件成功!");
					window.location='<%=basePath%>event/loadEvent.action';
				}
			}else{
				 alert("未知错误！");
				return false;
			}
		},"text");			
		}
		}
	
	//用于取消发送事件
    function abolishSendMail(){
        $("#light").animate({
           opacity: "hide"
       }, "slow");
       $("#fade").animate({
           opacity: "hide"
       }, "slow");
    }
	//用于设置发送按钮不能继续点击
	function limitButton(){
		$("#error_info").html("");
		$("#affirm").val("发送中...");
		$("#affirm").attr("disabled",true);
		$("#abolish").attr("disabled",true);
	}
	function abolishLimitButton(){
		$("#affirm").val("发送");
		$("#affirm").attr("disabled",false);
		$("#abolish").attr("disabled",false);
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
           <h3>设备管理</h3>
				<ul class="content-box-tabs">
					<li><a href="#tab1" class="default-tab">设备管理</a>
					</li>
					<!-- href must be unique and match the id of target div -->
					<li><a href="#tab2">发送邮件</a>
					<li><a href="#tab3">继续添加</a>
					</li>
				</ul>
				<div class="clear"></div>
			</div>
			<!-- End .content-box-header -->
			<div class="content-box-content">
				<div class="tab-content default-tab" id="tab1">
					<!-- This is the target div. id must match the href of this div's tab -->
                    <input type="hidden" name="eventID" value="${eventID}"/>
					<table class="solidTable">
							
							<thead>
							<tr>
								<th width="6%">序号</th>
								<th width="10%" >设备名称</th>
								<th width="11%" >设备型号</th>
								<th width="20%" >故障现象</th>
								<th width="11%" >公司保修号</th>
								<th width="12%">工厂保修号</th>
			                    <th width="10%">检测人</th>
			                    <th width="10%">登记日期</th>
			                    <th width="10%">操作</th>
								
							</tr>
						   </thead>
							<tbody>
								<s:iterator value="deviceList" status="item">
									<tr>
										
										<td><s:property value="#item.index"/></td>
										<td ><s:property value="deviceName" /></td>
									   <td >			
										<s:property value="deviceModel"/>
										</td>
										 <td >
											<s:property value="guzhangInfo"/>	
										</td>
										<td >
											<s:property value="protectNumber"/>	
										</td>
										
										<td >
											<s:property value="supplierPnumber"/>	
										</td>
										<td >
											<s:property value="testHuman"/>	
										</td>
										<td >
											<s:property value="recordDate"/>	
										</td>
										
										
										<td >
											<!-- Icons -->
											 <a href="<%=basePath%>device/loadDeviceToCmEdit.action?deviceID=<s:property value="id" />&eventID=${eventID}" title="编辑">编辑<img
												src="<%=basePath%>admin/resources/images/icons/pencil.png"
												alt="编辑" style="vertical-align:middle;"/> </a> 
											 <a href="<%=basePath%>device/deleteDevice.action?eventID=${eventID}&deviceID=<s:property value="id"/>" title="删除" onclick="javascript:return del();">删除<img
												src="<%=basePath%>admin/resources/images/icons/cross.png"
												alt="删除" style="vertical-align:middle;" /> </a>
										</td>
									</tr>
								</s:iterator>
						   </tbody>
						   
							
							<s:else>
							
						 <div class="notification attention png_bg">
          	<a href="#" class="close"><img src="<%=basePath %>/customer/resources/images/icons/cross_grey_small.png" title="关闭这个注意内容。" alt="close" /></a>
            <div> 查询的结果为空！</div></div>
							</s:else>
					    </table>
			  </div>
			  
			<div class="tab-content" id="tab2">
				<div style="margin-left:20%;width: 800px;">
						<form >
							<fieldset>
								<p>
									<label for="recipients">收件人(库房管理员):</label><input type="button"
										value="获取邮件收件人" id="findMailrecipients"
										name="findMailrecipients" onclick="findInventoryKeeper()" />
									
								</p>
								<p><textarea cssClass="text-input" id="recipients"
										name="recipients" ></textarea></p>

								<p>
									<label for="copyToMan">抄送(相关负责人):</label><input type="button"
										value="获取邮件抄送人" id="findMailCopyToMan"
										name="findMailCopyToMan" onclick="findAuditPerson()" />
									
								</p>
								<p><textarea cssClass="text-input" id="copyToMan"
										name="copyToMan"></textarea></p>
								<p>
									<label for="mailTheme">主&nbsp;&nbsp;&nbsp;&nbsp;题:</label><input type="button"
										value="获取邮件主题&nbsp;&nbsp;&nbsp;" id="findMailTheme" name="findMailTheme"
										onclick="findEventTheme()" />


								</p>
								<p>
									<textarea cssClass="text-input" id="mailTheme"
										name="mailTheme"></textarea></p>
								<p>
									<label for="mailContent">内&nbsp;&nbsp;&nbsp;&nbsp;容:</label><input type="button"
										value="获取邮件内容&nbsp;&nbsp;&nbsp;" id="findMailContent" name="findMailContent"
										onclick="findEventDeviceContent()" />
									<textarea id="mailContent" cssClass="text-input" class="mailContent" name="mailContent" value="" ></textarea>

								</p>
								
								<div id="light" class="white_content">
								<div id="title" class="title" name="title">
									<p>
										<font class="titleMainName" id="titleMainName" name="titleMainName"">邮箱信息</font> 
										<font class="titleDivClose" id="titleDivClose" name="titleDivClose"><a href="javascript:void(0)" onclick="$('#light').animate({ opacity: 'hide'}, 'slow');$('#fade').animate({opacity: 'hide' }, 'slow')">x</a></font>
									</p>
								</div>
								
								<div id="mail_info_div" name="mail_info_div" class="mail_info_div">
								<div id="mail_info" name="mail_info" class="mail_info">
								    <div style="margin-bottom:5px;height:25px;"><p><span id="error_info" name="error_info" style="color:red;margin-left:50px;"></span></p></div>
									<div><p><label for="addressor">发件人:</label><s:textfield cssClass="text-input" type="text" name="addressor" id="addressor"></s:textfield></p>
									<p><label for="password">密&nbsp;&nbsp;&nbsp;&nbsp;码:</label><s:textfield cssClass="text-input" type="password" name="addressorPassword" id="addressorPassword" ></s:textfield></p>
									<p><input type="button" cssClass="text-input" name="affirm" id="affirm" value="发送" onclick="sendMail()"><input type="button" cssClass="text-input" name=abolish id="abolish" onclick="abolishSendMail()" value="取消"><p></div>
								</div>
								</div>
								</div>
								<div id="fade" class="black_overlay"></div>

								<p style="margin-left:15%;">
									<input class="button" type="button" value="发送" id="submit" onclick="checkMailContentWithFindAddresser()" />
									<input class="button"  type="button" value="返回"
										onclick="javascript:history.back(-1);" />
								</p>
							</fieldset>
						</form>
						<div class="clear"></div>
						<!-- End .clear -->

					</div>
					
				</div>
						<div class="tab-content" id="tab3">
					<div style="margin-left:40%;">
						<form action="<%=basePath%>device/addDevice.action" method="post">
							 <input type="hidden" name="eventID" value="${eventID}"/>
							<fieldset>
							
							   
								<p>
									<label for="deviceName" id="deviceName">设&nbsp;&nbsp; 备&nbsp;&nbsp;&nbsp;名&nbsp;&nbsp;称:</label>
									<s:textfield name="deviceName" cssClass="text-input" id="deviceName" size="30"></s:textfield>
								</p>
							
								<p>
									<label for="deviceModel">设&nbsp;&nbsp; 备&nbsp;&nbsp;&nbsp;型&nbsp;&nbsp;号:</label>
									<s:textfield name="deviceModel" cssClass="text-input"
										id="deviceModel" size="30"></s:textfield>
								</p>
							
								<p>
									<label for="guzhangInfo">故&nbsp;&nbsp; 障&nbsp;&nbsp;&nbsp;现&nbsp;&nbsp;象:</label>
									<s:textfield name="guzhangInfo" cssClass="text-input"
										id="guzhangInfo" size="30"></s:textfield>
								</p>
							
								<p>
									<label for="protectNumber">公&nbsp;司&nbsp;保&nbsp;修&nbsp;号:</label>
									<s:textfield name="protectNumber" cssClass="text-input"
										id="protectNumber" size="30"></s:textfield>
								</p>
							
								<p>
									<label for="supplierPnumber">工&nbsp;厂&nbsp;保&nbsp;修&nbsp;号:</label>
									<s:textfield name="supplierPnumber" cssClass="text-input"
										id="supplierPnumber" size="30"></s:textfield>
								</p>
							
								<p>
									<label for="testHuman">检&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;测&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;人:</label>
									<s:textfield cssClass="text-input" id="testHuman"
										name="testHuman" size="30" />
								</p>
								<p>
									<label for="recordDate">登&nbsp;&nbsp;&nbsp;记&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;期:</label>
									<s:textfield cssClass="text-input" id="eventDate"
										name="recordDate" size="30"
										onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
								</p>
							
								<p style="margin-left:15%;">
									<input class="button" type="submit" value="提交" id="submit"
										/> <input class="button"
										type="button" value="返回"onclick="javascript:history.back(-1);" />
								</p>
							</fieldset>
							<div class="clear"></div>
						</form>
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