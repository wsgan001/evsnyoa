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
<title>设备登记管理</title>

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
	href="<%=basePath%>javascripts/kindeditor/themes/simple/simple.css"
	type="text/css" media="screen" />
	<link rel="stylesheet"
	href="<%=basePath%>admin/resources/css/step.css" type="text/css"
	media="screen" />
<!-- Bootstrap -->
        <link href="<%=basePath%>admin/resources/css/bootstrap.min.css" rel="stylesheet">
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
	/*点击更多生成表单div效果  基本思路点击添加就添加一个div包含*/ 
	function add(){
		//首先获取隐藏域的内容判断现在是新增多少个
		 var currentFormNubmer =parseInt($(".theFaultNumber").val());
         var newIncrease = $(".trouble_info_form_1").html();//获取html元素
         var divTop= '<div class="trouble_info_add_'+currentFormNubmer+'" style="margin-left:40%;"><hr><input class="button" id="device_trouble_info_save" type="submit" value="保存" style="float:right;" onclick="trouble_save(\'.trouble_info_form_'+currentFormNubmer+'\',\'.trouble_info_add_'+currentFormNubmer+'\')"/>&nbsp;&nbsp;&nbsp;<input class="button" id="trouble_info_delete" type="button" value="删除" style="float:right;" onclick="delete_trouble_div(\'.trouble_info_add_'+currentFormNubmer+'\')"/><input style="display: none;float:right;" class="button" type="submit" value="修改" id="device_trouble_info_edit" name="device_trouble_info_edit" onclick="trouble_edit(\'.trouble_info_form_'+currentFormNubmer+'\',\'.trouble_info_add_'+currentFormNubmer+'\')"/><div><div class="trouble_info_form_'+currentFormNubmer+'">';
         var divBottom= '</div></div>';
         var newIncreaseDiv = divTop+newIncrease+divBottom;
		 /*在选择元素结尾 */	  
		 $(".tab-content").append(newIncreaseDiv);
		 $(".trouble_info_add_"+currentFormNubmer+" :input").attr("disabled",false);
		 $(".theFaultNumber").val(parseInt(currentFormNubmer)+1);
		 /*判断页面还有几个表单用于完成提交按钮使用  */
		  var formNumber = $(".formNumber").val();
		  $(".formNumber").val(parseInt(formNumber)+1);
		  $(".trouble_info_form_"+currentFormNubmer+" :input").css("background","white");
		  $(".trouble_info_add_"+currentFormNubmer+" :input[name=deviceID]").val(0);
	}
	/*删除生成的表单*/
	function delete_trouble_div(class_info){
	     //判断如果设备id为空那么是简单的删除，如果有值也提示是否进行删除  如果id不为空那么是进数据库删除
	     var formNumber = $(".formNumber").val();
		  $(".formNumber").val(parseInt(formNumber)-1);
		 $(class_info).remove();
		}
	/*判断是否为空*/
	function check_trouble_info(deviceName,testHuman,supplierPnumber,protectNumber,guzhangInfo,deviceModel,recordDate,class_info){
		$(".input-notification.error").remove();
		if (deviceName == "") {
			$(class_info+" :input[name=deviceName]")
					.after("<span class='input-notification error png_bg'>不能为空</span>");
			$(class_info+" :input[name=deviceName]").focus();
			return false;
		}
		if (deviceModel == "") {
			$(class_info+" :input[name=deviceModel]")
					.after("<span class='input-notification error png_bg'>不能为空</span>");
			$(class_info+" :input[name=deviceModel]").focus();
			return false;
		}
		if (guzhangInfo == "") {
			$(class_info+" :input[name=guzhangInfo]")
					.after("<span class='input-notification error png_bg'>不能为空</span>");
			$(class_info+" :input[name=guzhangInfo]").focus();
			return false;
		}
		if (protectNumber == "") {
			$(class_info+" :input[name=protectNumber]")
					.after("<span class='input-notification error png_bg'>不能为空</span>");
			$(class_info+" :input[name=protectNumber]").focus();
			return false;
		}
		
		if (supplierPnumber == "") {
			$(class_info+" :input[name=supplierPnumber]")
					.after("<span class='input-notification error png_bg'>不能为空</span>");
			$(class_info+" :input[name=supplierPnumber]").focus();
			return false;
		}
		if (testHuman == "") {
			$(class_info+" :input[name=testHuman]")
					.after("<span class='input-notification error png_bg'>不能为空</span>");
			$(class_info+" :input[name=testHuman]").focus();
			return false;
		}
		if (recordDate == "") {
			$(class_info+" :input[name=recordDate]")
					.after("<span class='input-notification error png_bg'>不能为空</span>");
			return false;
		} 
		return "true";
		
	}
	/*保存生成表单所填内容  */
	function trouble_save(class_info,class_info2){
	   var eventID= $("#eventID").val();
		var eventID= $("#eventID").val(); 
       var deviceName= $(class_info+" :input[name=deviceName]").val(); 
       var testHuman= $(class_info+" :input[name=testHuman]").val(); 
       var supplierPnumber= $(class_info+" :input[name=supplierPnumber]").val(); 
       var protectNumber= $(class_info+" :input[name=protectNumber]").val(); 
       var guzhangInfo= $(class_info+" :input[name=guzhangInfo]").val(); 
       var deviceModel= $(class_info+" :input[name=deviceModel]").val(); 
       var recordDate= $(class_info+" :input[name=recordDate]").val(); 
       var is_satisfy = check_trouble_info(deviceName,testHuman,supplierPnumber,protectNumber,guzhangInfo,deviceModel,recordDate,class_info);
       if(is_satisfy=="true"){
    	   var deviceID = $(class_info+" :input[name=deviceID]").val();
    	   if(deviceID==0){
    		   //为保存状态
    		   var data ="eventID="+eventID+"&deviceName="+deviceName+"&testHuman="+testHuman+"&supplierPnumber="+supplierPnumber+"&protectNumber="+protectNumber+"&guzhangInfo="+guzhangInfo+"&deviceModel="+deviceModel+"&recordDate="+recordDate;
        	   $.post('<%=basePath%>device/saveDeviceWithReturnId.action',data,function(result){
        		   if(result!=0){
        			  alert("添加成功!");
        			  $(class_info+" :input[name=deviceID]").val(result);
        			  $(class_info+" :input").attr("disabled",true);
        			  $(class_info+" :input[name=device_trouble_info_edit]").attr("disabled",false);
        			  var formNumber = $(".formNumber").val();
        			  $(".formNumber").val(parseInt(formNumber)-1);
        			  $(class_info2+" #device_trouble_info_save").hide();
        			  $(class_info2+" #trouble_info_delete").hide();
        			  $(class_info2+" #device_trouble_info_edit").css("display","block"); 
        			  $(class_info+" :input").css("background","#E9E9E9");
        			  var submitedFormNumber =  $("#submitedFormNumber").val();
        			  $("#submitedFormNumber").val(parseInt(submitedFormNumber)+1);
        		   }else if(result==0){
        			   alert("添加失败!");
        		   }   
        	   }); 
    	   }else{
    		   //为修改状态
    		   var data ="eventID="+eventID+"&deviceName="+deviceName+"&testHuman="+testHuman+"&supplierPnumber="+supplierPnumber+"&protectNumber="+protectNumber+"&guzhangInfo="+guzhangInfo+"&deviceModel="+deviceModel+"&recordDate="+recordDate;
        	   $.post('<%=basePath%>device/updateDeviceByDeviceId.action',data,function(result){
        		   if(result!=0){
        			  alert("更新成功!");
        			  $(class_info+" :input").attr("disabled",true);
        			  $(class_info+" :input[name=device_trouble_info_edit]").attr("disabled",false);
        			  var formNumber = $(".formNumber").val();
        			  $(class_info2+" #device_trouble_info_save").hide();
        			  $(class_info2+" #device_trouble_info_edit").css("display","block"); 
        			  $(class_info+" :input").css("background","#E9E9E9");
        		   }else if(result==0){
        			   alert("更新失败!");
        		   }   
        	   }); 
    	   }
    	   
       }
	}
	/* /*对已提交的进行修改 */
	function trouble_edit(class_info,class_info2){
		$(class_info+" :input").attr("disabled",false);
		$(class_info2+" #device_trouble_info_save").show();
		$(class_info2+" #device_trouble_info_edit").css("display","none"); 
		$(class_info+" :input[name=deviceName]").focus();
		$(class_info+" :input").css("background","white");
	}
	

	/*判断还有未提交的表单 */
	function check_form_number(){
		var submitedFormNumber = $("#submitedFormNumber").val();
		if(submitedFormNumber==0){
			alert("您不能提交,因为您还没保存任何内容!");
		}else{
		  var formNumber = $(".formNumber").val();
		var eventid= $("#eventID").val();
		  if(parseInt(formNumber)>=1){
			  if(confirm('还有未填内容,你是否选择跳转页面?'))
			window.location.href= "<%=basePath%>device/loadDeviceToEdit.action?eventID="+eventid;
			  return false;
		  }else{  
			  window.location.href= "<%=basePath%>device/loadDeviceToEdit.action?eventID="+eventid;
		  }
	}
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
					<li><a href="#tab1" class="default-tab">设备登记</a></li>
				</ul>
				<div class="clear"></div>
			</div>
			<!-- End .content-box-header -->
			<div class="content-box-content">
			<!--步骤模块start--------------  -->
		<!--步骤模块start--------------  -->
			<div class="step_div">
			    <li><div class="step_circled" id="step_circled">1</div><img alt="步骤" src="<%=basePath%>admin/resources/images/step_jiantou.png"></li>	
			    <li><div class="step_circled" id="step_circled">2</div><img alt="步骤" src="<%=basePath%>admin/resources/images/step_jiantou.png"></li>	
			    <li><div class="step_circled" id="step_circled">3</div><img alt="步骤" src="<%=basePath%>admin/resources/images/step_huishe_jiantou.png"></li>
			    <li><div class="step_circle" id="step_circle">4</div><img alt="步骤" src="<%=basePath%>admin/resources/images/step_huishe_jiantou.png"></li>	
			    <li><div class="step_circle" id="step_circle">5</div></li>	
			</div>
			<div class="step_state_div">
			    <li><div class="step_state_one" id="step_stated">故障登记</div></li>		    
			    <li><div class="step_state_two" id="step_stated">故障描述</div></li>
			    <li><div class="step_state_third" id="step_stated">登记设备信息</div></li>
			    <li><div class="step_state_four" id="step_state">设备返修信息</div></li>
			    <li><div class="step_state_five" id="step_state">排故处理</div></li>
			</div>
			<!--步骤模块end---------------->
				<div class="tab-content default-tab" id="tab1">
				<!--这个隐藏框作用是用来判断当前有几个需要提交的div 默认值为1-->
			    <input type="hidden" class="theFaultNumber" name="theFaultNumber" id="theFaultNumber" value="2">
			    <!--判断当前有多少个表单 -->
			    <input type="hidden" class="formNumber" name="formNumber" id="formNumber" value="1">
			    <!--用户提交之后查询查询所添加的id  -->
			    <input type="hidden" value="${eventID}" name="eventID" id="eventID">
			    <!--用于用户点完成提交的时候判断用户是否已提交一个以上  -->
			    <input type="hidden" value="0" name="submitedFormNumber" id="submitedFormNumber">
					<div class="trouble_info_add_1" style="text-align: right;width: 5%;float:right; height:25px;" ><input class="button" style="float:right;" id="device_trouble_info_save" type="submit" value="保存" 
						onclick="trouble_save('.trouble_info_form_1','.trouble_info_add_1')"/><input style="display: none;float:right;" class="button" type="submit" value="修改" id="device_trouble_info_edit" name="device_trouble_info_edit"
						onclick="trouble_edit('.trouble_info_form_1','.trouble_info_add_1')"/></div>
					<!-- This is the target div. id must match the href of this div's tab -->
					<div class="trouble_info_form_1"  style="margin-left:40%;margin-top:-15px;">
						<form action="<%=basePath%>device/addDevice.action" method="post" onsubmit="return false;" >
							<fieldset>
							<!-- 默认为空为需要保存状态 不为空为修改状态deviceID -->
							   <input type="hidden" name="deviceID" id="deviceID" value="0">
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
							
								
							</fieldset>
							<div class="clear"></div>
						</form>
					</div>
                                
				</div>
				<hr style="border:1px border gray;">
	                             <p style="text-align: center;">
	                             <input type="button" class="button" onclick="add()" value="继续添加">
									<input class="button" type="button" value="完成提交" id="submit"
										onclick="check_form_number()" /> <input class="button"
										type="reset" value="返回" onclick="javascript:history.back()" />
								 </p>
				<div class="tab-content" id="tab2"></div>
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