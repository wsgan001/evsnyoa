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
<link rel="stylesheet" type="text/css" href="<%=basePath%>admin/resources/css/main.css" />
 <link rel="stylesheet" href="<%=basePath%>admin/resources/fonts/font-awesome-4.2.0/css/font-awesome.min.css">
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
	location.href="<%=basePath%>admin/loadUsersList.action?page="+ $("#page").val();
						});
		//alert(typeof parseInt(totalPage));

	$("#username").change(function() {
		$("#username + #username_error").remove();
		if($("#username").val()!=''){
			$.getJSON("<%=basePath%>admin/isExist.action", {
				account : $("#username").val()
			}, function(data) {
				if (data.flag == true) {
					$("#msg").css("color", "red");
					$("#msg").html("该账号不可用！");
				} else {
					$("#msg").css("color", "green");
					$("#msg").html("该账号可用！");
				}

			});
		}
		
		});
	});

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
					.after("<span class='input-notification error png_bg' id='psword_error'>不能为空</span>");
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
	function del() {
		if (confirm("确定要删除该条记录吗？")) {
			return true;
		}
		return false;
	};
</script>
</head>
<body style="background: #f0f0f0">
  	<div id="home">
		<div class="container">
			<div class="header">
				<div class="headerTop">
					
						<div class="headerLeft clearfix">
						
						<img src="<%=basePath%>/admin/resources/images/headimages/logo.png" id="logo" alt="logo" align="left"/>
						<p>Evnsy</p>
						</div>
						<div class="headerRight clearfix">
							<form action="" method="get" class="searchForm" />
								<p><input type="text" name="search" onfocus="if(this.value=='Search')this.value='';" onblur="if(this.value=='')this.value='Search';" value="Search" /></p>
							</form>
							<div class="socialLinks clearfix">
								<a href="javascript:void(0);" title="退出登陆" onclick="javascript:logout();"><img src="<%=basePath %>/admin/resources/images/headimages/quit.jpg" title="退出" alt="quit" /></a>
								<a href="<%=basePath%>event/loadEvent.action" target="main"><img src="<%=basePath %>/admin/resources/images/headimages/home.jpg" title="主页" alt="home" /></a>
								<a href="<%=basePath%>admin/eventcl.jsp" target="main"><img src="<%=basePath %>/admin/resources/images/headimages/more.jpg" title="更多" alt="more" /></a> 
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>	
	</div>
	
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
           <h3>用户管理</h3>
				<ul class="content-box-tabs">
					<li><a href="#tab1" class="default-tab">用户管理</a>
					</li>
					<!-- href must be unique and match the id of target div -->
					<li><a href="#tab2">用户添加</a>
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
							<tr>
								<th width="8%" ><input class="check-all" type="checkbox" />全选</th>
								<th width="8%" >序 号</th>
								<th width="10%" >用户账号</th>
								<!--  <th width="15%" >用户密码</th>-->
								<th width="10%" >用户职称</th>
								<th width="29%" >用户权限</th>
								<th width="20%" >操 作</th>
							</tr>
						   </thead>
							<tbody>
								<s:iterator value="pageBean.list" status="item">
									<tr>
										<td ><input type="checkbox" name="checkList"
											value="<s:property value="id" />" />
										</td>
										<td ><s:property value="%{#item.count+10*(pageBean.currentPage-1)}" /></td>
										
										<td ><s:property value="account" /></td>
								
										<!--  <td ><s:property value="pwd" /></td>-->
									   <td >			
										<s:property value="post"/>
										</td>
										 <td >
										<s:if test='%{sjdj=="1"}'>事件登记</s:if>
										<s:if test='%{sjfp=="1"}'>事件分配</s:if>
										<s:if test='%{sjcl=="1"}'>事件处理</s:if>
										<s:if test='%{sjsh=="1"}'>事件审核</s:if>
										<s:if test='%{qxfp=="1"}'>权限分配</s:if>				
										</td>
										<td >
											<!-- Icons -->
											 <a href="<%=basePath%>admin/loadUser.action?userId=<s:property value="id" />&page=<s:property value="page"/>" title="编辑">编辑<img
												src="<%=basePath%>admin/resources/images/icons/pencil.png"
												alt="编辑" style="vertical-align:middle;"/> </a> |
												<a href="<%=basePath%>admin/deleteUser.action?userId=<s:property value="id" />&page=<s:property value="page"/>" title="删除" onclick="javascript:return del();">删除<img
												src="<%=basePath%>admin/resources/images/icons/cross.png"
												alt="删除" style="vertical-align:middle;" /> </a>
										</td>
									</tr>
								</s:iterator>
						   </tbody>
						   <tfoot>
							<tr>
								<td colspan="8">
									
									<div class="pagination">
									       共有<s:property value="pageBean.totalPage" />页，当前第&nbsp;<s:property value="pageBean.currentPage"/>
								/<s:property value="pageBean.totalPage" />&nbsp;页，
										<a href="<%=basePath%>admin/loadUsersList.action?page=1"
											title="首页">&laquo;首页</a>
										<s:if test="%{pageBean.currentPage == 1}">
											<a href="javascript:void(0)"
												onclick="javascript:alert('已经是第一页！')">&laquo;上一页</a>
										</s:if>
										<s:else>
											<a
												href="<%=basePath%>admin/loadUsersList.action?page=<s:property value="%{pageBean.currentPage-1}"/>">&laquo;上一页</a>
										</s:else>
										<select name="page" id="page" style="width:90px;">
										</select>
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
												href="<%=basePath%>admin/loadUsersList.action?page=<s:property value="%{pageBean.currentPage+1}"/>">下一页&raquo;</a>
										</s:else>
										<a
											href="<%=basePath%>admin/loadUsersList.action?page=<s:property value="pageBean.totalPage"/>">尾页&raquo;</a>
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
				<div class="tab-content" id="tab2">
					<div style="margin-left:40%;">
						<form action="<%=basePath%>admin/addUsers.action" method="post">

							<fieldset>
								<p>
								<label for="username"> 账&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号:</label>
								<s:textfield name="account" cssClass="text-input" id="username" size="30" ></s:textfield>	<span id="msg"></span>
							</p>
							<p>
								<label for="psword">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码:</label>
								<s:textfield cssClass="text-input" id="psword" name="pwd" size="30"/>				
							</p>
							<p>
								<label for="name">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名:</label>
								<s:textfield cssClass="text-input" id="name" name="name" size="30"/>				
							</p>
							<p>

								<label for="post">职&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;位:</label>
								<s:select list="#{'总经理':'总经理','经理':'经理','财务':'财务','总工程师':'总工程师','技术顾问':'技术顾问','销售经理':'销售经理','销售':'销售','工程师':'工程师','技术':'技术','开发':'开发','行政':'行政','商务助理':'商务助理'}" name="post" cssClass="small-input" id="post"></s:select>

							</p>
							<p>
								<label for="telephone">权&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;限:</label>
								<%-- <s:checkboxlist list="#{'1':'事件浏览','2':'事件登记','3':'事件分配','4':'事件审核'}" name="qx" listKey="key"
listValue="value"  value="{'1','2'}"></s:checkboxlist>	 --%>	
                                  <s:checkbox name="sjdj"  value="true" fieldValue="true" >事件登记</s:checkbox>
                                  <s:checkbox name="sjfp"  fieldValue="true" >事件分配</s:checkbox>
                                  <s:checkbox name="sjcl"  fieldValue="true">事件处理</s:checkbox>	
                                  <s:checkbox name="sjsh"  fieldValue="true" >事件审核</s:checkbox>	
                                  <s:checkbox name="qxfp"  fieldValue="true" >权限分配</s:checkbox>			
							</p>
							<p>
								<label for="telephone">电话号码:</label>
								<s:textfield cssClass="text-input" id="telephone" name="telephone" size="30"/>				
							</p>
							<p>
								<label for="email">电子邮件:</label>
								<s:textfield cssClass="text-input" id="email" name="email" size="30"/>				
							</p>			
								<p style="margin-left:15%;">
									<input class="button" type="submit" value="确定" id="submit"
										onclick="javascript:return check();" /> <input class="button"
										type="reset" value="重填" /> <input class="button"
										type="button" value="返回"onclick="javascript:location.href='<%=basePath%>admin/loadUsersList.action'" />
								</p>
							</fieldset>
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
