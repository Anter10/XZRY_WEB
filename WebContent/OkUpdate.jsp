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
    String addname = request.getParameter("tn");
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加<%=addname %>数据</title>
</head>
<body>
     <%
        String addSql = "";
        if (addname != null){
        	addSql = "update "+addname+" set ";
            
        	String starP = "'";
        	String upsql = "";
        	String part  = ",";
        	for(int index = 0; index < Param.addMessage.length(); index ++){
        		if (index == Param.updateModelData.length() - 1 && index > 0){
        			part = ""; 
        		}
        		String value = Param.updateModelData.getString(index);
        		String v1 = request.getParameter(value);
        		System.out.print(value+"更新数据 ＝ "+v1);
        		upsql = upsql + value + " = " + "'" + v1 + "'" + part;
        	}
        	upsql = addSql +  upsql +"where id = "+ request.getParameter("id");
         	System.out.print("更新数据 ＝ "+upsql);/*  */ 
        	boolean updata = DealDatabase.updateDatabase(upsql);
        	
        	if (updata == true){
        		response.sendRedirect("ShowData.jsp?tablename="+addname);
        	}else{
        		response.sendRedirect("error.jsp");
        	}
     %>
        
     <%
        } 
     %>
</body>
</html>