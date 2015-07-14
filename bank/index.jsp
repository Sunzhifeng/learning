<%@page contentType="text/html;charset=gb2312" import="java.sql.*,java.util.*"%>
<%
	if(session.getAttribute("user")==null)
	{ 
%>
		<jsp:forward page="login.jsp"></jsp:forward>
<%
	}
%>
<html>
<head>
<title>мЬиорЬпп</title>
</head>
  <frameset framespacing="0" border="false" cols="270,*" frameborder="0">
  <frame name="left"  scrolling="no" marginwidth="0" marginheight="0" src="/bank/left.jsp">
  <frame name="right" scrolling="yes" src="/bank/welcome.jsp">
</frameset>

<noframes>
</noframes> 
</html>
