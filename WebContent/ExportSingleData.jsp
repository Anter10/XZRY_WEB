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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
   <% 
         
        String tableName = request.getParameter("tb");
        if (tableName != null){
        	String exportsql = "select * from "+tableName; 
            String exportdata = DealDatabase.ExportGameData(exportsql);
         	filemanager.getFM().writeJSONDataToFile(exportdata, "/Users/guoyouchao/Desktop/xclient/res/JsonData/"+tableName+".json");
        }
        response.sendRedirect("ShowData.jsp?tablename="+tableName);
    %>
</body>
</html>