<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>file upload success</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="">
	

  </head>
  
  <body> 
	<div align="center">
    	<s:fielderror cssStyle="color:red;"/>
    	${message}<br>
    	<!--上传时间:<s:date name="date"/><br>
					 realPath:${realPath}<br>
				    contextPath:${contextPath}<br>
				    contextName:${contextName}<br> -->
		
	</div>
  </body>
</html>
