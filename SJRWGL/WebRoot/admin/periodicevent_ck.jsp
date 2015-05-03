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
	
	<link rel="stylesheet" type="text/css"
	href="<%=basePath%>css/jquery.bigautocomplete.css" />
	
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
<script type="text/javascript">
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
	
	function isDeletePeriodicEvent(eventId){
		if(confirm('您确定要删除该条周期性事件吗?')){
			return true;
		}
		return false;	
	}
	<%-- //更新状态
	function updateEventIsOpenState(eventId,eventIsOpenState){
		$.post(<%=basePath%>+"periodicevent/updateEventIsOpenState.action?id="+eventId+"&eventIsOpen="+eventIsOpenState,POST,function(result){	
			 if(result==true){
				 
			 }else{
				 
			 }
			
		},"text");
		
		
		
		
	} --%>
	
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
           <h3>周期性事件管理</h3>
				<ul class="content-box-tabs">
					<li><a href="#tab1" class="default-tab">事件查看</a>
					</li>
					
				</ul>
				<div class="clear"></div>
			</div>
			<!-- End .content-box-header -->
			<div class="content-box-content">
				<div class="tab-content default-tab" id="tab1">
					<!-- This is the target div. id must match the href of this div's tab -->

					<table class="solidTable">
							<s:if test="%{pageBean.allRow>=0}">
							<thead>
							<tr id="tr_1">						
								<th width="6%" >序 号</th>
								<th width="8%" >事件类型</th>
								<th width="12%" >客户名称</th>
								<th width="8%" >产品类型</th>
								<th width="10%" >下一周期开始时间</th>
								<th width="10%" >开始日期</th>
								<th width="8%" >结束日期</th>
								<!-- <th width="8%" >事件间隔(天)</th> -->
								<th width="9%" >事件描述</th>
								<th width="6%" >执行人</th>
								<th width="7%" >审核人</th>
								<th width="13%">操作</th>
							</tr>
						   </thead>
							<tbody>
								<s:iterator value="pageBean.list" status="item">
									<tr onclick="window.location='<%=basePath%>/periodicevent/loadPeriodicEventRelaEvent.action?page=1&id=<s:property value="id"/>'">	
										<td><s:property value="%{#item.count+16*(pageBean.currentPage-1)}" /></td>
										<td><s:property value="eventType"/></td>
										<td><s:property value="eventCustomer"/></td>
										<td><s:property value="eventProduct" /></td>
										<td><s:date name="createEventDate" format="yyyy-MM-dd"/></td>
										<td><s:date name="eventStartTime" format="yyyy-MM-dd"/></td>
										<td><s:date name="eventEndTime" format="yyyy-MM-dd"/></td>
										<%-- <td><s:property value="timeInterval" /></td> --%>
										 <td>
										 <s:if test='%{eventInfo==""}'>空</s:if>			
										 <s:else><s:property value="eventInfo"/></s:else>
										</td>
										 <td >			
										<s:property value="eventEngineer"/>
										</td>
										 <td >	
										 <s:property value="visitName"/>	
										</td>
										<td>
										<s:if test='%{eventIsOpen==true}'>
											<a href="<%=basePath%>periodicevent/updateEventIsOpenState.action?id=<s:property value="id"/>&eventIsOpen=<s:property value="false"/>"
												title="开启" onclick="javascript:return del();">开启中..<img
												src="<%=basePath%>admin/resources/images/icons/isPlay.ico" style="width:19px;height:19px;vertical-align:middle;"
												alt="开启" /></a>
										</s:if>
										<s:else>
											<a href="<%=basePath%>periodicevent/updateEventIsOpenState.action?id=<s:property value="id"/>&eventIsOpen=<s:property value="true"/>"
												title="暂停" onclick="javascript:return del();">暂停中..<img
												src="<%=basePath%>admin/resources/images/icons/isPause.ico"
												alt="暂停" style="width:19px;height:19px;vertical-align:middle;"/></a>
										</s:else>
										
										<a href="<%=basePath%>periodicevent/loadPeriodicEventById.action?id=<s:property value="id"/>"
											title="编辑" onclick="javascript:return del();">编辑<img
												src="<%=basePath%>admin/resources/images/icons/pencil.png"
												alt="编辑" style="vertical-align:middle;"/></a>
											<!-- Icons -->
										<a href="<%=basePath%>periodicevent/deletePeriodicEventById.action?id=<s:property value="id"/>" title="删除" onclick="javascript:return isDeletePeriodicEvent(<s:property value="id"/>)">删除<img
												src="<%=basePath%>admin/resources/images/icons/cross.png"
												alt="删除" style="vertical-align:middle;" /> </a> 
										</td> 
									</tr></a>
								</s:iterator>
						   </tbody>
						   <tfoot>
							<tr id="tr_2">
								<td colspan="11">
									
									<div class="pagination">
									       共有<s:property value="pageBean.totalPage" />页，当前第&nbsp;<s:property value="pageBean.currentPage"/>
								/<s:property value="pageBean.totalPage" />&nbsp;页，
										<a href="<%=basePath%>periodicevent/loadAllPeriodicEvent.action?page=1"
											title="首页">&laquo;首页</a>
										<s:if test="%{pageBean.currentPage == 1}">
											<a href="javascript:void(0)"
												onclick="javascript:alert('已经是第一页！')">&laquo;上一页</a>
										</s:if>
										<s:else>
											<a
												href="<%=basePath%>periodicevent/loadAllPeriodicEvent.action?page=<s:property value="%{pageBean.currentPage-1}"/>">&laquo;上一页</a>
										</s:else>
										<!--  <a href="#"
											class="number" title="1">1</a> <a href="#" class="number"
											title="2">2</a> <a href="#" class="number current" title="3">3</a>
										<a href="#" class="number" title="4">4</a>  -->
										<s:if test="%{pageBean.currentPage == pageBean.totalPage}">
											<a href="javascript:void(0)"
												onclick="javascript:alert('已经是最后一页！')">下一页&raquo;</a>
										</s:if>
										<s:else>
											<a
												href="<%=basePath%>periodicevent/loadAllPeriodicEvent.action?page=<s:property value="%{pageBean.currentPage+1}"/>">下一页&raquo;</a>
										</s:else>
										<a
											href="<%=basePath%>periodicevent/loadAllPeriodicEvent.action?page=<s:property value="pageBean.totalPage"/>">尾页&raquo;</a>
									</div> <!-- End .pagination -->
									<div class="clear"></div>
								</td>
							</tr>
						</tfoot>
							</s:if>
							<s:else>
							
						 <div class="notification attention png_bg">
          	<a href="#" class="close"><img src="<%=basePath %>/admin/resources/images/icons/cross_grey_small.png" title="关闭这个注意内容。" alt="close" /></a>
            <div> 查询的结果为空！</div></div> 
							</s:else>
					</table>
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
