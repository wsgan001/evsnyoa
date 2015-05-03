<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ taglib prefix="e" uri="/myTag"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%  request.setCharacterEncoding("UTF-8"); %>
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
	String datetime = new SimpleDateFormat("yyyy-MM-dd EEE HH:mm:ss")
    .format(Calendar.getInstance().getTime()); //获取系统时间
   
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>事件登记管理</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<!--                       CSS                       -->
<!-- Reset Stylesheet -->
<link rel="stylesheet" href="<%=basePath%>admin/resources/css/reset.css"
	type="text/css" media="screen" />
	<link rel="stylesheet" type="text/css"
	href="<%=basePath%>css/jquery.bigautocomplete.css" />
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
<!-- head部分css样式 -->
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>admin/resources/css/main.css" />
<link rel="stylesheet"
	href="<%=basePath%>admin/resources/fonts/font-awesome-4.2.0/css/font-awesome.min.css">
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
<script type="text/javascript"
	src="<%=basePath%>admin/resources/scripts/PCASClass.js"></script>
	<script type="text/javascript"
	src="<%=basePath%>javascripts/jquery.bigautocomplete.js"></script>
<style type="text/css">
.black_overlay {
	display: none;
	position: absolute;
	top: 0%;
	left: 0%;
	width: 100%;
	height: 100%;
	background-color: black;
	z-index: 1001;
	-moz-opacity: 0.8;
	opacity: .80;
	filter: alpha(opacity = 80);
}

.white_content {
	display: none;
	position: absolute;
	top: 25%;
	left: 25%;
	width: 665px;
	height: 50%;
	padding: 0px;
	border: 2px solid #F7F8F8;
	background-color: white;
	z-index: 1002;
	overflow: auto;
	-webkit-border-radius: 5px;/* chrom椭圆? */
	-moz-border-radius: 5px;/* huohu div椭圆 */
}

.title {
	height: 35px;
	width: 100%;
	background-color: #F7F8F8;
	z-index: 1002;
	font-size: 18;
}
.titleMainName{ 
    width:100px;
    height:30px;
    padding-left: 10px;
    margin-left:5px;
    padding-top:2px;
    align:left;
    font-size:18px;
    font-weight:bold;
    position: absolute;
    
}
.titleDivClose{
    width:20px;
    height:30px;
    float:right;
    margin-left:635px;  
    font-size:24px;
    color:blue;
    position: absolute;
    font-weight: bold;
}
.customerInfo{
  width:78%;
  height:80%;
  cursor:pointer;/*指针  */
  text-decoration:none;/* 下划线 */
  font-size: 16px;
  color:red;
  padding-left: 80px;
  padding-top:10px;
  font-weight: bold;
  position: absolute;
}
</style>
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
	function check() {
		$(".input-notification.error").remove();
		var $eventCustomer = $("#eventCustomer");
	    var $eventProduct = document.eventForm.eventProduct.value;
	    var $eventProductNode = $("#eventProduct");
		var $eventType = document.eventForm.eventType.value;
		var $eventTypeNode=$("#eventType");
		var $eventDate = $("#eventDate");
		if ($eventCustomer.val() == "") {
			
			$eventCustomer
					.after("<span class='input-notification error png_bg'>不能为空</span>");
			$eventCustomer.focus();
			return false;
		}

		if ($eventProduct =="产品名称") {
			$eventProductNode
					.after("<span class='input-notification error png_bg'>必须选择</span>");
			$eventProductNode.focus();
			return false;
		}
		if ($eventType == "事件类型") {
			$eventTypeNode
					.after("<span class='input-notification error png_bg'>必须选择</span>");
			$eventTypeNode.focus();
			return false;
		}
		if(($('input:radio[name="periodicEvent"]:checked').val())!="1"){
			if ($eventDate.val() == "") {
				$eventDate
						.after("<span class='input-notification error png_bg'>不能为空</span>");
				return false;
			}
		}
		return true;
	};
	
	   function findCustomer(){
	        var province = $("#province").val();
	         var city = $("#city").val();
	         var county = $("#county").val();
	          if(province!=null&&city!=null&&county!=null){//判断是否都为空
	              $.ajax({
	              type:"POST",
	                  url:'<%=basePath%>customer/findCustomerList.action',
						data : "province=" + province + "&city=" + city
								+ "&county=" + county,
						success : function(flag) {
							/* alert(flag); */
							if (flag != 1) {
								//弹出一个层，显示flag的文字	
								showLightWitdFade();
								var customerArr = flag.split(",");
								var resultString = "";
								for ( var i = 0; i < customerArr.length; i++) {
									resultString += "<a onclick=findCustomerByName(\".arr1"+i+"\")><p name=\"arr1"+i+"\" class=\"arr1"+i+"\" id=\"arr1"+i+"\">"+ customerArr[i] + "</p></a>";
								}
								document.getElementById('customerInfo').innerHTML = resultString;
							} else {   
								showLightWitdFade();
								document.getElementById('customerInfo').innerHTML ='<h2>没有对应的客户,请手动输入,或检查一下地址是否选择正确<h2>';

							}
						}
					}
					);
		}
	}
	   function showLightWitdFade(){
		   $("#light").animate({opacity:"show"}, "slow");
		     $("#fade").animate({opacity:"show"}, "slow");
	   }
	   function hideLightWithFade(){
		   $("#light").animate({opacity: "hide"}, "slow");
	       $("#fade").animate({opacity: "hide"}, "slow");
	   }
	//用于客户点击事件
    function findCustomerByName(str){
       var customerContent = $(str).text();//值得注意的是获取p标签的值用text();HTML()和value()都不是很管用
       document.getElementById('eventCustomer').value=customerContent;
       hideLightWithFade();
    }
	$(function(){
		$("#reset_info").click(function(){
			/*执行重置的时候需要一段时间，所以这里采用延时函数给重置以后才执行以下代码才有效果  */
			setTimeout(function () {  
				 $(".province").change(); 
				$(".city").change();
				$("#eventBigType").change();
				$("#productBigType1").change();
		    }, 30);	
		});	
		
	});
	//事件类型数组
	 var eventTypeArr = new Array(['1','现场交流'],['1','调研'],['1','解决方案'],['1','招标参数'],['1','投标文件'],['1','市场会议'],['1','培训'],['1','演示'],['1','测试'],['1','提交用户文档'],['1','报价']
	       ,['2','调研'],['2','项目实施'],['2','排故'],['2','巡检'],['2','测试演示'],['2','技术交流'],['2','编写文档'],['2','会议学习'],['2','认证考试'],
	       ['3','需求分析'],['3','开发文档'],['3','开发']);
	  //产品类型数组
	  var productTypeArr1 = new Array(['1','vsphere'],['1','vcenter'],['1','esxi'],['1','view'],['1','Operation'],['1','SRM'],['1','DR/DP'],['2','NBU'],['2','BE'],
			  ['2','SSR'],['2','SF'],['2','SEP'],['2','SWG'],['2','SNAC'],['2','SDCS'],['2','DLP'],['2','SMG'],['3','单机oracle'],['3','集群oracle'],['4','IBM'],['4','同有'],['4','HP'],
			  ['4','EMC'],['4','NetApp'],['4','华为'],['4','联想'],['5','易云通云终端'],['6','网络交换机'],['7','光钎交换机'],['8','其他产品']);
	  /* 填充事件间隔数组   */
	  var dateArr = new Array(['5','每1天'],['5','每2天'],['5','每3天'],['5','每4天'],['5','每5天'],['5','每6天'],['5','每7天'],['5','每8天'],['5','每9天'],['5','每10天'],['5','每11天'],['5','每12天'],['5','每13天'],['5','每14天'],['5','每15天'],['5','每16天'],['5','每17天'],['5','每18天'],['5','每19天'],['5','每20天'],['5','每21天'],['5','每22天'],['5','每23天'],['5','每24天'],['5','每25天'],['5','每26天'],['5','每27天'],['5','每28天'],['5','每29天'],['5','每30天']
	  ,['3','每1周'],['3','每2周'],['3','每3周'],['3','每4周'],['3','每5周'],['3','每6周'],['3','每7周'],['3','每8周'],['3','每9周'],['3','每10周'],['3','每11周'],['3','每12周'],['2','每1月'],['2','每2月'],['2','每3月'],['2','每4月'],['2','每5月'],['2','每6月'],['2','每7月'],['2','每8月'],['2','每9月'],['2','每10月'],['2','每11月'],['2','每12月']);
	  /* 填充事件类型  */
	  function changeSelect(eventId){
		  document.eventForm.eventType.length=0;
		  if(eventId==0)document.eventForm.eventType.options[0] = new Option('事件类型');
		  for(var i=0;i<eventTypeArr.length;i++){
			  if (eventTypeArr[i][0] == eventId)                    //[0] [1] 第一列 第二列
             {document.eventForm.eventType.options[document.eventForm.eventType.length] = new Option(eventTypeArr[i][1]);

             }                    
		  } 
	  }
	  /*填充产品类型  */
	  function changeSelect1(productId){
		  document.eventForm.eventProduct.length=0;
		  if(productId==0)document.eventForm.eventProduct.options[0] = new Option('产品分类');
		  for(var i=0;i<productTypeArr1.length;i++){	  
			  if (productTypeArr1[i][0] == productId) //当符合它那一列的时候就创建
              {document.eventForm.eventProduct.options[document.eventForm.eventProduct.length] = new Option(productTypeArr1[i][1]); 
              }                    
		  } 
	  }
	  /* 填充事件间隔  */
	  function intervalTimeChange(eventId){
		  document.eventForm.intervalTime.length=0;
		  if(eventId==0){document.eventForm.intervalTime.options[0] = new Option('间隔时间');
		  $("#timeInterval").val(0);
		  }
		  for(var i=0;i<dateArr.length;i++){
			  if (dateArr[i][0] == eventId)                    //[0] [1] 第一列 第二列
             {document.eventForm.intervalTime.options[document.eventForm.intervalTime.length] = new Option(dateArr[i][1]);}                    
		  }
		  document.eventForm.intervalTime.onchange();
		  
	  }
	  

	  /* 选择时间赋值到timeInterval文本框  */
	  function selectChange(eventName){
		  //把每多少天的数字截取出来。使用substr方法
		  var time = eventName.substr(1,eventName.length-2);
		  $("#timeInterval").val(time);
		
	  }
	  /*事件结束日期必须大于事件开始日期*/
	  function checkTime(){
		var eventStartTime = $("#eventStartTime").val();
		var startTimeArr = eventStartTime.split("-");
		var eventEndTime = $("#eventEndTime").val();
		var endTime = eventEndTime.split("-");
		if(Date.parse(endTime[1]+"/"+endTime[2]+"/"+endTime[0])>=  
		Date.parse(startTimeArr[1]+"/"+startTimeArr[2]+"/"+startTimeArr[0])){  
		//做自己的操作  
		}else{
			alert("事件结束日期必须大于时间开始日期!");
			$("#eventEndTime").val("");
		}  
	  };
	  /*检测是周期性事件还是非周期性事件进行选择性提交  */
	 function checkIsPeriodic(){
			  var commonEvent = check();
			  var periodicEvent = checkPeriodicInfo();
		 		 if(commonEvent==true&&periodicEvent==true){
				  return true;
		 		 }
		 		 return false;
	  }
	  /*检查周期性事件特有属性是否为空 */
	  function checkPeriodicInfo(){
			var $eventStartTime = $("#eventStartTime");
		    var $timeInterval = $("#timeInterval");
		    var $eventEndTime = $("#eventEndTime");
		    if($eventStartTime.val()==""){
		    	$eventStartTime
				.after("<span class='input-notification error png_bg'>不能为空</span>");
				$eventStartTime.focus();
				return false;
		    }
		    if($eventEndTime.val()==""){
		    	$eventEndTime
				.after("<span class='input-notification error png_bg'>不能为空</span>");
				$eventEndTime.focus();
				return false;
		    }
		    if($timeInterval.val()=="0"){
		    	$timeInterval
				.after("<span class='input-notification error png_bg'>不能为空</span>");
				$timeInterval.focus();
				return false;
		    }
		    return true;
	  }
	  //检查开始时间大于登记时间
	  function checkStartTime(){
		  var date = new Date();
		  var currentTime = date.getFullYear()+"-";
		  currentTime += (date.getMonth()+1)+"-";//月份从0开始0-11
		  currentTime += date.getDate();
		  var $eventStartTime = $("#eventStartTime");
		  var starTime  = $eventStartTime.val();
		  var currentTimeArr = currentTime.split("-");
		  var starTimeArr = starTime.split("-");
		  if(Date.parse(currentTimeArr[1]+"/"+currentTimeArr[2]+"/"+currentTimeArr[0])>=
		      Date.parse(starTimeArr[1]+"/"+starTimeArr[2]+"/"+starTimeArr[0])){
		       alert("事件开始时间必须在今天以后!");
		       $eventStartTime.val("");
	  		}
	  }
	   function eventCustomerChange(){
		   $("#eventCustomer").attr("autocomplete","off");//关掉浏览器默认提示栏
		   var data = returnHintData();//返回json数组
			$("#eventCustomer").bigAutocomplete({width:604,data:data,callback:function(data){
				if(data!=null){
					$("#bigAutocompleteContent").hide();
				}	
			} });
	   }
	   //为了解决第一次进页面插件没有足够时间加载导致第一次选择选项失效
	   $(function(){
		   $("#eventCustomer").attr("autocomplete","off");//关掉浏览器默认提示栏
		   var data=null;
		   $("#eventCustomer").bigAutocomplete({width:604,data:data,callback:function(data){
			} });    
	   });
	 
	  //返回提示数据类
	 	 function returnHintData (){
		  var dataInfo=null;
	  	  var customerName = $("#eventCustomer").val();
	  	  if(customerName===undefined||""==customerName){
	  		customerName=""; 
	  		return null;
	  	  }else{	  
		  var data = "customerName="+customerName;
		  $.ajax({
				  cache: false,//默认值: true，dataType 为 script 和 jsonp 时默认为 false。设置为 false 将不缓存此页面。
		          async: false, //是否异步
		          data:data,
		          dataType:'text',
		          type: 'post',
				  url:"<%=basePath%>customer/findDimCustomer.action",
				  success:function(result){
			 	 var jsonData = eval('(' + result + ')');//将json字符串转化为json对象
			  	 dataInfo = jsonData;
		  }});
		  return dataInfo;
	  	  }  
	  }
	  $(function(){ 
		  var $eventType ='${periodicEvent.eventType}';
		  setOption:for(var i =0;i<eventTypeArr.length;i++){
			  if(eventTypeArr[i][1]==$eventType){
					  $("#eventBigType").get(0).selectedIndex=eventTypeArr[i][0];//index为索引值
					  $(".eventBigType").change();
					  //下面是设置二级联动下拉值
					  var count=$("#eventType option").length;
					  for(var i=0;i<count;i++){     
						  if($("#eventType ").get(0).options[i].text == $eventType){  
					            $("#eventType").get(0).options[i].selected = true;
					            break setOption;  
					        }  
					    }  
			  } 
		  } 
		  //设置时间间隔的类型
		  var $intervalType ='${periodicEvent.intervalType}';
		  var $timeInterval = '${periodicEvent.timeInterval}';
		  if($intervalType==2){
			  $("#intervalType").get(0).selectedIndex=3;//index为索引值
			  $("#intervalType").change();
			  //下面是设置二级联动下拉值
			  $("#timeInterval").val($timeInterval);
			  var $timeInterval = "每"+$timeInterval+"月";
			  
			  var count=$("#intervalTime option").length;
			  for(var i=0;i<count;i++){
				  
				  if($("#intervalTime").get(0).options[i].text == $timeInterval){  
			            $("#intervalTime").get(0).options[i].selected = true;
			            break;  
			        }  
			    }
		  }
		  else if($intervalType==3){
			  $("#intervalType").get(0).selectedIndex=2;//index为索引值
			  $("#intervalType").change();
			   var count=$("#intervalTime option").length;
			   $("#timeInterval").val($timeInterval);
			  var $timeInterval = "每"+$timeInterval+"周";
			  
			  for(var i=0;i<count;i++){
				  if($("#intervalTime").get(0).options[i].text == $timeInterval){  
			            $("#intervalTime").get(0).options[i].selected = true;
			            break;  
			        }  
			    } 
		  }else if($intervalType==5){ 
			  $("#intervalType").get(0).selectedIndex=1;//index为索引值
			  $("#intervalType").change();
			  $("#timeInterval").val($timeInterval);
			   var $timeInterval = "每"+$timeInterval+"天";
			   
			  var count=$("#intervalTime option").length;
			  for(var i=0;i<count;i++){ 
				  if($("#intervalTime").get(0).options[i].text == $timeInterval){  
			            $("#intervalTime").get(0).options[i].selected = true;
			            break;  
			        }  
			    } 
		  }
		  //设置产品下拉框
		  var $eventProduct ='${periodicEvent.eventProduct}';
		  setOption1:for(var i =0;i<productTypeArr1.length;i++){
			  if(productTypeArr1[i][1]==$eventProduct){
					  $("#productBigType1").get(0).selectedIndex=productTypeArr1[i][0];//index为索引值
					  $("#productBigType1").change();
					  //下面是设置二级联动下拉值
					  var count=$("#eventProduct option").length;
					  for(var i=0;i<count;i++){     
						  if($("#eventProduct ").get(0).options[i].text == $eventProduct){  
					            $("#eventProduct").get(0).options[i].selected = true;
					            break setOption1;  
					        }  
					    }
			  } 
		  }
		  //设置执行人和审核人
		  $eventEngineer = '${periodicEvent.eventEngineer}';
		  $visitName = '${periodicEvent.visitName}';
		  var count=$("#eventEngineerId option").length;
		  for(var i=0;i<count;i++){     
			  if($("#eventEngineerId").get(0).options[i].text == $eventEngineer){  
		            $("#eventEngineerId").get(0).options[i].selected = true;
		            break;  
		        }  
		    }
		  var count=$("#eventShId option").length;
		  for(var i=0;i<count;i++){     
			  if($("#eventShId").get(0).options[i].text == $visitName){  
		            $("#eventShId").get(0).options[i].selected = true;
		            break;  
		        }  
		    }  	
	  });
</script>

</head>
<body style="background: #f0f0f0">
	
	
	<%@ include file ="top.jsp" %>
	<!--<s:action name="loadEvent.action" namespace="/" executeResult="true"></s:action>-->

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
					<li><a href="#tab1" class="default-tab">事件登记</a>
					</li>
				</ul>
				<div class="clear"></div>
				<!-- 弹出层效果 -->
			</div>
			
			<div id="light" class="white_content">
				<div id="title" class="title" name="title">
					<p>
						<font class="titleMainName" id="titleMainName" name="titleMainName"">客户信息</font> 
						<font class="titleDivClose" id="titleDivClose" name="titleDivClose"><a href="javascript:void(0)" onclick="hideLightWithFade()">X</a></font>
					</p>
				</div>
				
				<div id="customerInfo" name="customerInfo" class="customerInfo">
					<!-- 客户信息 -->
				</div>
			</div>
			<div id="fade" class="black_overlay"></div>

			<!-- End .content-box-header -->
			<div class="content-box-content">
				<div class="tab-content default-tab" id="tab1">
					<!-- This is the target div. id must match the href of this div's tab -->

					<div style="margin-left:40%;">
						<form action="<%=basePath%>periodicevent/updatePeriodicEventById.action" method="post" enctype="multipart/form-data" name="eventForm" id="eventForm" onsubmit="return checkIsPeriodic()">
							<input type="hidden" name="id" value="${periodicEvent.id }"/>
							<%-- <p>
								<label for="city">所&nbsp;在&nbsp;地&nbsp;区:</label>
								<fieldset style="padding:5px;">
								<select name="province" id="province" class="province"></select>
								<select name="city" id="city"></select> <select name="county"
									id="county" class="county" onchange="findCustomer()"></select><br>
							</fieldset>
							<script language="javascript" defer>
								new PCAS("province", "city", "county");
							</script>
							</p> --%>
			
							<fieldset>
								<div>
									<p>
										<label for="eventCustomer">客&nbsp;户&nbsp;名&nbsp;称:</label>
										<input name="eventCustomer" cssClass="text-input" type="text" readonly="readonly"
											id="eventCustomer" class="eventCustomer" size="30" value="${periodicEvent.eventCustomer}" onkeyup="eventCustomerChange()"/>
									</p>
								</div>
							
								<div style="margin-top: 10px">
									<p>
										<label for="eventType">事&nbsp;件&nbsp;类&nbsp;型:</label>
										<select id="eventBigType" class="eventBigType" cssClass="small-input" onchange="changeSelect(this.value)">
                                        <option value="0">事件类型</option>
                                        <option value="1">售前分类</option>
                                        <option value="2">售后分类</option>
                                        <option value="3">开发分类</option>
    									</select>
    									<select name="eventType" id="eventType">
    									<option>事件类型</option>
    									</select>
									</p>
								</div>
								<div>
									<div class="periodiceventdiv">
									<p>
										<label for="eventStartTime">开始时间:</label>
										<s:textfield name="eventStartTime" cssClass="text-input"
											id="eventStartTime" class="eventStartTime" size="30"
											value="%{periodicEvent.eventStartTime}"
											onchange="checkStartTime()"
											onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" ></s:textfield>
									</p>
								</div>
								<div class="periodiceventdiv">
									<p>
										<label for="eventEndTime">结束时间:</label>
										<s:textfield name="eventEndTime" cssClass="text-input"
											id="eventEndTime" class="eventEndTime" size="30"
											value="%{periodicEvent.eventEndTime}"
											onchange="checkTime()"
											onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"
											></s:textfield>
									</p>
								</div>
								<div class="periodiceventdiv">
									<p>
										<label for="intervalType">时间间隔:</label>
										<select id="intervalType" name="intervalType" class="intervalType" Class="small-input" onchange="intervalTimeChange(this.value)">
										     <option value="0">间隔类型</option>
										     <option value="5">按天区分</option>
										     <option value="3">按周区分</option>
										     <option value="2">按月区分</option>
										</select>
										<select id="intervalTime" cssClass="small-input" onchange="selectChange(this.value)">
										     <option value="0">间隔时间</option>
										</select>
										<input type="hidden" id="timeInterval" name="timeInterval" value="${periodicEvent.timeInterval}"/>
									</p>
								</div>
								
								<p>
								<label for="eventEngineer">执&nbsp;&nbsp;&nbsp;&nbsp;行&nbsp;&nbsp;&nbsp;&nbsp;者:</label>
								<s:select list="userlist" listKey="id"  listValue="name" id="eventEngineerId" name="eventEngineerId" />
							 	</p>
							 
								  <p>
									<label for="eventCheck">审&nbsp;&nbsp;&nbsp;&nbsp;核&nbsp;&nbsp;&nbsp;&nbsp;者:</label>
									<s:select list="userlist" listKey="id" listValue="name"  id="eventShId" name="eventShId" />
							 	 </p>
									
								</div>
									<div style="margin-top: 10px">
									<p>
										<label for="eventProduct">产&nbsp;品&nbsp;名&nbsp;称:</label>
										<select id="productBigType1"  class="productBigType1" cssClass="small-input" onchange="changeSelect1(this.value)">
								       	<option value="0">产品类型</option>
								        <option value="1">vmware</option>
								        <option value="2">Symantec</option>
								        <option value="3">Oracle</option>
								        <option value="4">存储</option>
								        <option value="5">易云通云终端</option>
								        <option value="6">网络交换机</option>
								        <option value="7">光钎交换机</option>
								        <option value="8">其他产品</option>
									    </select>
									    <select id="eventProduct" name="eventProduct">
									    <option>产品名称</option>
									    </select>
									</p>
								</div>
								<div style="margin-top: 10px">
									<p>
										<label for="eventInfo">事&nbsp;件&nbsp;描&nbsp;述:</label>
										<s:textfield cssClass="text-input" id="eventInfo"
										value="%{periodicEvent.eventInfo}"
											name="eventInfo" size="30" />
										<!-- <textarea id="editor_id" name="editor_id" style="width:300px;height:200px;"></textarea> -->
									</p>
								</div>
								<div style="margin-top: 10px">
									<p style="margin-left:15%;">
										<input class="button" type="submit" value="确定" id="submit" /> <input
											class="button" type="reset" id="reset_info" value="重填" />
									</p>
								</div>
							</fieldset>
							<div class="clear"></div>
							<!-- End .clear -->
						</form>
					</div>


				</div>

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
