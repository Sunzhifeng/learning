<%@page contentType="text/html;charset=gb2312" import="java.sql.*,java.util.*"%>

<%
if(session.getAttribute("user")!=null){
//--------------------------------------logged------------------------------------------------------------------
	
%>
<html>
<head>
<title>����ҳ��</title>
<link rel="stylesheet" type="text/css" href="style/style.css">
<link rel="stylesheet" type="text/css" href="style/default.css">

</head>
<BODY>

<table cellpadding=0 cellspacing=0 width=200 align=center class="table">

  <tr>
        <td height=25 align="center" bgcolor="#DBC2B0"> 
          <b>�û�����</b></td>
  </tr>
  <tr>
    <td>
            <table cellpadding=0 cellspacing=0 align=center width=180 class="table" >                           
              <tr> 
                <td height=20><a href=/bank/deposit.jsp target=right >���</a></td>
              </tr>
              <tr> 
                <td height=20><a href=/bank/withdrawal.jsp target=right>ȡ��</a></td>
              </tr>
              <tr> 
                <td height=20><a href=/bank/transfer.jsp target=right>ת��</a></td>
              </tr>
               <tr> 
                <td height=20><a href=/bank/transaction/list?pager.curPage=1 target=right>��ѯ���׼�¼</a></td>
              </tr>
               <tr> 
                <td height=20><a href=/bank/information.jsp target=right>�鿴��Ϣ</a></td>
              </tr>
               <tr> 
                <td height=20><a href=/bank/modify.jsp target=right>�޸ĸ�����Ϣ</a></td>
              </tr>
               <tr> 
                <td height=20><a href=/bank/changepwd.jsp target=right>�޸�����</a></td>
              </tr>
               <tr> 
                <td height=20 ><a href=/bank/user/user_logout target=_top>ע��</a></td>
              </tr>
              </table>
	</td>
  </tr>
</table>
<%
}
%>