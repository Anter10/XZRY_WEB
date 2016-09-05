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
<title>玩家所有数据导出</title>
</head>
<body>
   
    <%
 
 
       for(int index = 0; index < Param.alluserdata.length; index ++){
    	   String tableName = Param.alluserdata[index];
    	   String exportsql = "select * from "+tableName; 
    	   String exportdata = DealDatabase.ExportGameData(exportsql);
    	 /*   filemanager.getFM().writeJSONDataToFile(exportdata, "/Users/guoyouchao/Documents/CocosProjects/WMJs.10/res/JsonData/"+tableName+".json"); */
    	   filemanager.getFM().writeJSONDataToFile(exportdata, "/Users/guoyouchao/Desktop/Weimo/UserDatas/"+tableName+".json");
    	/*    filemanager.getFM().writeJSONDataToFile(exportdata, "/Users/guoyouchao/Documents/CocosProjects/WMLua3.10/res/JsonData/"+tableName+".json");
    	 */   
       } 
       response.sendRedirect("ShowData.jsp?tablename=UserData");
    %>
</body>
</html>