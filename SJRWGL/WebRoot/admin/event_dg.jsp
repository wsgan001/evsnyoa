<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib prefix="e" uri="/myTag"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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

	Date today = Calendar.getInstance().getTime();
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

<!-- Main Stylesheet -->
<link rel="stylesheet" href="<%=basePath%>admin/resources/css/style.css"
	type="text/css" media="screen" />
<!-- Invalid Stylesheet. This makes stuff look pretty. Remove it if you want the CSS completely valid -->
<link rel="stylesheet"
	href="<%=basePath%>admin/resources/css/invalid.css" type="text/css"
	media="screen" />
<link rel="stylesheet"
	href="<%=basePath%>admin/resources/css/mystyle.css" type="text/css"
	media="screen" />
<link rel="stylesheet" href="<%=basePath%>css/mystyle.css"
	type="text/css" media="screen" />


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
$(document).ready(function(){
	//所有的class为tableListContent的tr加上click事件
	$("tr.tableListContent").click(function(){
	  $(this).find("input").attr("checked",true);//打勾   
	  $(this).find("td").css("background-color","red");//改变背景色
	});
	});

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

function dateCompare(startdate,enddate)   
{   
var arr=startdate.split("-");    
var starttime=new Date(arr[0],arr[1],arr[2]);    
var starttimes=starttime.getTime();   
  
var arrs=enddate.split("-");    
var lktime=new Date(arrs[0],arrs[1],arrs[2]);    
var lktimes=lktime.getTime();   
  
if(starttimes>lktimes)    
{   
return false;   
}   
else  
 return true;   
 }  
var color;
$(function(){
$("tr").hover(
		  function () {
			if(this==(document.getElementById("tr_1")))
				return;
			if(this==(document.getElementById("tr_2")))
				return;
		    $(this).addClass("hover");
		    color = this.style.backgroundColor;
		    this.style.backgroundColor="#7EC0EE";
		  },
		  function () {
			  if(this==(document.getElementById("tr_1")))
					return;
			  
		    $(this).removeClass("hover");
		    this.style.backgroundColor=color;
		  }
		);
});
function onclick1(){
	alert("click here");
	var eventid = document.getElementById("id").value;
	
	document.location='<%=basePath%>event/loadEventToView.action?eventID='+ eventid;
	};
</script>
</head>

<body style="background: #f0f0f0">
	<s:bean name="com.xnjd.hynm.tld.FenPeiComparator"
		var="FenPeiComparator" />
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
					<li><a href="#tab1" class="default-tab">事件浏览</a></li>
					<!-- <li><a href="#tab2">其他事件</a> -->
                </ul>
				<div class="clear"></div>
			</div>
			<!-- End .content-box-header -->
			<div class="content-box-content">
				<div class="tab-content default-tab" id="tab1">
					<!-- This is the target div. id must match the href of this div's tab -->
					<div style="margin-left:0.5%;width:100px;"></div>


					<form action="<%=basePath%>event/loadEvent.action">
						<table class="solidTable" id="dataTable">
                           
							<s:if test="%{pageBean.allRow>=0}">
								<thead>
									<tr id="tr_1">
										<th width="5%">序 号</th>
										<th width="10%">客户名称</th>
										<th width="8%">事件类型</th>
										<th width="17%">事件描述</th>
										<th width="10%">发生日期</th>
										<th width="10%">计划完成日期</th>
										<th width="8%">执行人</th>
										<th width="8%">审核人</th>
										<th width="8%">分配</th>
										<th width="8%">状态</th>
										<th width="8%">操作</th>
										<!--<th width="15%" >操 作</th> -->
									</tr>
								</thead>



								<!--<tbody style="color:black">  -->
								<tbody>


                               <s:sort comparator="FenPeiComparator" source="pageBean.list" >
											<ol>
									<s:iterator status="item">
									
										<!--<s:if test='%{eventType=="排故"}'>
											  <s:if test='%{#session.admin.name==eventEngineer}'>
												<s:if test='%{nowstate=="0"}'>
													<tr
														onclick="window.location='<%=basePath%>admin/paigu/paigu_info.jsp?eventID=<s:property value="id"/>'">
												</s:if>
												<s:elseif test='%{nowstate=="1"}'>
													<tr
														onclick="window.location='<%=basePath%>paigu/loadPaiguByEventId.action?eventID=<s:property value="id"/>'">
												</s:elseif>
												<s:elseif test='%{nowstate=="2"}'>
													<tr
														onclick="window.location='<%=basePath%>paigu/loadPaiguToDevice.action?eventID=<s:property value="id"/>'">
												</s:elseif>
												<s:elseif test='%{nowstate=="3"}'>
											       <tr onclick="window.location='<%=basePath%>device/loadDeviceEditList.action?eventID=<s:property value="id"/>'">
											        </s:elseif> 
											  </s:if>
											   
											   <s:else>
											     <s:if test='%{#session.admin.kfgl=="1"}'>
											        <s:if test='%{nowstate=="3"}'>
											       <tr onclick="window.location='<%=basePath%>device/loadDeviceList.action?eventID=<s:property value="id"/>'">
											        </s:if>  
											     </s:if>
											     <s:else>
												 <tr onclick="window.location='<%=basePath%>event/loadEventToView.action?eventID=<s:property value="id" />'">
												 </s:else>
											   </s:else>
										</s:if>
										
										<s:else>

											<s:if test='%{applyState==1||dispatchState==0}'>
												<tr
													onclick="window.location='<%=basePath%>event/loadEventToView.action?eventID=<s:property value="id" />'"
													style="color:red">
											</s:if>
											<s:else>
												<tr
													onclick="window.location='<%=basePath%>event/loadEventToView.action?eventID=<s:property value="id" />'">
											</s:else>
										</s:else>-->


										<s:if test='%{applyState==1||dispatchState==0}'><tr onclick="window.location='<%=basePath%>event/loadEventToView.action?eventID=<s:property value="id" />'"  style="color:red"></s:if>
							    <s:else><tr onclick="window.location='<%=basePath%>event/loadEventToView.action?eventID=<s:property value="id" />'"></s:else>


										
											
											<td><s:property value="%{#item.count+16*(pageBean.currentPage-1)}" /></td>
											<td><s:property value="eventCustomer" /></td>
											<td><s:property value="eventType" /></td>
											<td><s:if test='%{eventInfo==""}'>空</s:if> 
											 <s:else>
													<s:property value="eventInfo" />
												</s:else>
												</td>
											<td><s:date name="eventDate" format="yyyy-MM-dd"/></td>

											<td><s:date name="planTime1" format="yyyy-MM-dd" /></td>
											<td><s:property value="eventEngineer" /></td>
											<td><s:property value="visitName" /></td>
											 <td><s:if test='%{dispatchState==0}'>未分配</s:if> <s:if
												test='%{dispatchState==1}'>已分配</s:if></td>
										<td><s:if test='%{applyState==0}'>处理未完成</s:if> <s:if
												test='%{applyState==1}'>已申请完成</s:if></td>
										<td>
										
										<s:if test='%{applyState==1}'>需审核</s:if>
										<s:if test='%{completeState==0}'>
										<s:if test='%{applyState==0}'>
										<s:if test='%{dispatchState==1}'>待处理</s:if>
										</s:if>
										</s:if>
										<s:if test='%{completeState==0}'>
										<s:if test='%{dispatchState==0}'>需分配</s:if>
										</s:if> 
										<s:if test='%{completeState==1}'>已完成</s:if>
										</td> 
											
											</tr>
									</s:iterator>
									    </ol>
									 </s:sort>
								</tbody>

								<tfoot>
									<tr id="tr_2">
										<td colspan="11">

											<div class="pagination">
												共有
												<s:property value="pageBean.totalPage" />
												页，当前第&nbsp;
												<s:property value="pageBean.currentPage" />
												/
												<s:property value="pageBean.totalPage" />
												&nbsp;页， <a
													href="<%=basePath%>event/loadEvent.action?page=1"
													title="首页">&laquo;首页</a>
												<s:if test="%{pageBean.currentPage == 1}">
													<a href="javascript:void(0)"
														onclick="javascript:alert('已经是第一页！')">&laquo;上一页</a>
												</s:if>
												<s:else>
													<a
														href="<%=basePath%>event/loadEvent.action?page=<s:property value="%{pageBean.currentPage-1}"/>">&laquo;上一页</a>
												</s:else>
												<!--<a href="#"
											class="number" title="1">1</a> <a href="#" class="number"
											title="2">2</a> <a href="#" class="number current" title="3">3</a>
										<a href="#" class="number" title="4">4</a>  -->
												<s:if test="%{pageBean.currentPage == pageBean.totalPage}">
													<a href="javascript:void(0)"
														onclick="javascript:alert('已经是最后一页！')">下一页&raquo;</a>
												</s:if>
												<s:else>
													<a
														href="<%=basePath%>event/loadEvent.action?page=<s:property value="%{pageBean.currentPage+1}"/>">下一页&raquo;</a>
												</s:else>
												<a
													href="<%=basePath%>event/loadEvent.action?page=<s:property value="pageBean.totalPage"/>">尾页&raquo;</a>
											</div> <!-- End .pagination -->
											<div class="clear"></div>
										</td>
									</tr>
								</tfoot>

							</s:if>
							</a>

							<s:else>

								<div class="notification attention png_bg">
									<a href="#" class="close"><img
										src="<%=basePath%>/admin/resources/images/icons/cross_grey_small.png"
										title="关闭这个注意内容。" alt="close" /></a>
									<div>查询的结果为空！</div>
								</div>
							</s:else>
						</table>
					</form>
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
