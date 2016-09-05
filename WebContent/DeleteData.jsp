<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import = "org.json.*"  %>
<%@ page import = "database.*" %>
<%@ page import = "java.io.*" %>
<%@ page import = "javax.sql.*"%>
<%@ page import = "Parameter.*" %>
<%@ page import = "file.*" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<% 
    response.setCharacterEncoding("UTF-8");
    request.setCharacterEncoding("UTF-8");
    String tableName = request.getParameter("tn");
    String deleteId  = request.getParameter("id");
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
    <%
       if (tableName != null){
    	   String deleteSql = "delete from "+tableName + " where id = '"+deleteId+"'";
    	   boolean delete = DealDatabase.updateDatabase(deleteSql);
       	   System.out.print("删除语句＝ "+deleteSql);
	       	if (delete == true){
	       		response.sendRedirect("ShowData.jsp?tablename="+tableName);
	       	}else{
	       		response.sendRedirect("error.jsp");
	       	}
       }
    %>
</body>
</html>