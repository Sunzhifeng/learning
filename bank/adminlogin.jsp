<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ taglib uri='/struts-tags' prefix='s' %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>login</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="style/style.css" >
	<link rel="stylesheet" type="text/css" href="style/default.css" >
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script language="javascript" >
	function login(){
	
		var hidden=document.getElementById("hidden").value;
		if(document.getElementById("username"+hidden).value ==""){
			alert("用户名不能为空");
			return false;
		}else if(document.getElementById("password"+hidden).value ==""){
			alert("密码不能为空");
			return false;
		} else {
			return true;
		}
	}
	
	
	
	
	
</script>
  </head>
  
  <body >
  		<div align="center">
		<form method="post" name="myform" action="admin/login" >
			<table width="450" border="0" class="table">
				<tbody>
				<tr>
					<td colspan="2" align="center">
					</td>
					
				</tr>
				<tr>
				<td>&nbsp;用户名：</td>
				<td>&nbsp;
					
					<input id="username2" type="text" name="admin.username" >
					
				</td>
				</tr>
				<tr>
				<td>&nbsp;密码：</td>
				<td>&nbsp; 
					
					<input id="password2" type="password" name="admin.password">
					
				</td></tr>
				
				<tr>
				<td>
					
				</td>
				<td>&nbsp; 
					<input type="submit" value="登录" id="login">
				
				</td></tr>
				</tbody>
			</table>
			<s:fielderror fieldName="username" cssStyle="color:red;"/>
			<s:fielderror fieldName="password" cssStyle="color:red;"/>
			
		</form> 
    </div>
  </body>
</html>
