<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'device_edit.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<!-- Reset Stylesheet -->

	<script type="text/javascript">
	  var productTypeArr = new Array();
	  productTypeArr[0]=new Array('1','现场交流');
	  productTypeArr[1]=new Array('1','调研');
	  productTypeArr[2]=new Array('1','解决方案');
	  productTypeArr[3]=new Array('1','招标参数');
	  productTypeArr[4]=new Array('1','投标文件');
	  productTypeArr[5]=new Array('1','市场会议');
	  productTypeArr[6]=new Array('1','培训');
	  productTypeArr[7]=new Array('1','演示');
	  productTypeArr[8]=new Array('1','测试');
	  productTypeArr[9]=new Array('1','提交用户的各种文档');
	  productTypeArr[10]=new Array('1','报价');
	  productTypeArr[11]=new Array('2','调研');
	  productTypeArr[12]=new Array('2','项目实施');
	  productTypeArr[13]=new Array('2','排故');
	  productTypeArr[14]=new Array('2','巡检');
	  productTypeArr[15]=new Array('2','测试演示');
	  productTypeArr[16]=new Array('2','技术交流');
	  productTypeArr[17]=new Array('2','编写文档');
	  productTypeArr[18]=new Array('2','会议学习');
	  productTypeArr[19]=new Array('2','认证考试');
	  function changeSelect(productId){
		  document.productForm.productsmallType.length=0;
		  if(productId==0)document.productForm.productsmallType.options[0] = new Option('产品分类','选择你要添加的产品分类');
		  for(var i=0;i<productTypeArr.length;i++){
			  if (productTypeArr[i][0] == productId)                    //[0] [1] 第一列 第二列
              {document.productForm.productsmallType.options[document.productForm.productsmallType.length] = new Option(productTypeArr[i][1], productTypeArr[i][2]);}                    
		  } 
	  }
	  var productTypeArr1 = new Array();
	  productTypeArr1[0] = new Array('1','vsphere');
	  productTypeArr1[1] = new Array('1','vcenter');
	  productTypeArr1[2] = new Array('1','esxi');
	  productTypeArr1[3] = new Array('1','view');
	  productTypeArr1[4] = new Array('1','Operation');
	  productTypeArr1[5] = new Array('1','SRM');
	  productTypeArr1[6] = new Array('1','DR/DP');
	  productTypeArr1[7] = new Array('2','NBU');
	  productTypeArr1[8] = new Array('2','BE');
	  productTypeArr1[9] = new Array('2','SSR');
	  productTypeArr1[10] = new Array('2','SF');
	  productTypeArr1[11] = new Array('2','SEP');
	  productTypeArr1[12] = new Array('2','SWG');
	  productTypeArr1[13] = new Array('2','SNAC');
	  productTypeArr1[14] = new Array('2','SDCS');
	  productTypeArr1[15] = new Array('2','DLP');
	  productTypeArr1[16] = new Array('2','SMG');
	  productTypeArr1[17] = new Array('3','单机oracle');
	  productTypeArr1[18] = new Array('3','集群oracle');
	  productTypeArr1[19] = new Array('4','IBM');
	  productTypeArr1[20] = new Array('4','同有');
	  productTypeArr1[21] = new Array('4','HP');
	  productTypeArr1[22] = new Array('4','EMC');
	  productTypeArr1[23] = new Array('4','NetApp');
	  productTypeArr1[24] = new Array('4','华为');
	  productTypeArr1[25] = new Array('4','联想');
	  productTypeArr1[26] = new Array('5','易云通云终端');
	  productTypeArr1[27] = new Array('6','网络交换机');
	  productTypeArr1[28] = new Array('7','光钎交换机');
	  productTypeArr1[29] = new Array('8','其他产品');
	  function changeSelect1(productId){
		  document.productForm1.productsmallType1.length=0;
		  if(productId==0)document.productForm1.productsmallType1.options[0] = new Option('产品分类','选择你要添加的产品分类');
		  for(var i=0;i<productTypeArr1.length;i++){	  
			  if (productTypeArr1[i][0] == productId) //当符合它那一列的时候就创建
              {document.productForm1.productsmallType1.options[document.productForm1.productsmallType1.length] = new Option(productTypeArr1[i][1]);}                    
		  } 
	  }
	function find(){
		alert(document.productForm1.productsmallType1.value);
		
		
	}
	</script>

  </head>
  
  <body>
    <form id="productForm" name="productForm" method="POST" action="">
    <select id="productBigType" name="productBigType" class="productBigType" cssClass="small-input" onchange="changeSelect(this.value)">
        <option value="0">事件大类</option>
        <option value="1">售前工作分类</option>
        <option value="2">售后工作分类</option>
    </select>
    <select name="productsmallType">
    <option>事件小类</option>
    </select>
    </form>
    <form id="productForm1" name="productForm1" method="POST" action="">
    <select id="productBigType1" name="productBigType1" class="productBigType1" cssClass="small-input" onchange="changeSelect1(this.value)">
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
    <select name="productsmallType1">
    <option>产品名称</option>
    </select>
    </form>
    <input type="button" value="find" onclick="find()">
  </body>
</html>
