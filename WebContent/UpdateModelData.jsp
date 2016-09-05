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
    String id = request.getParameter("id");
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

 

<title>更新<%=addname %>数据</title> 
</head>
<body>
     <a href = "ShowData.jsp?tablename=<%=addname %>">返回</a>
     <p><%=addname %>数据更新</p>
     <hr color = "red">
     <form action = "OkUpdate.jsp?tn=<%=addname %>" method = "post">
     <%
        String addSql = "";
       
        if (addname != null){
        	String selectSql =  "select * from " + addname + " where id = "+id+"";
        	
        	String selectStr = DealDatabase.getQuerryJsonStringData(selectSql);
        	
        	if (selectStr != null){
        		
        		JSONObject getJson = new JSONObject(selectStr);
        		
        		
        		JSONArray lbJson = getJson.getJSONArray("lb");
        		JSONObject psJson = getJson.getJSONArray("ps").getJSONObject(0);
        		Param.updateModelData = lbJson;
                String key = "";
         for(int index = 0; index < lbJson.length(); index ++){
             key =  lbJson.getString(index);
         %>
             
             <%=key+":" %>
             <input width = "320" type = "text" id = <%=key %> name=<%= key%> value=<%= psJson.getString(key) %> ><br>
         
          <%
        		}
          %>
           <input type = "submit" value = "更新">
           <input type = "reset" value = "清空">
         </form>
        <%
        	 }
        %>
     <%
          
           } 
     %>
</body>
</html>
