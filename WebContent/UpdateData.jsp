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
<title></title>
</head>
<body>
       <% 
          String tableNames = request.getParameter("tb");
          int index = 0;
        /*   System.out.print("当前添加属性 ＝ "+Param.addMessage.toString()); */
           
           
       %>
       <p><%=tableNames %>数据添加</p>
       <hr color = red><br>
       <form method = "post" action = "AddData.jsp?tn=<%=tableNames %>">
           <%
              
              System.out.print(tableNames);
              if (tableNames == "HeroStatueConvert"){
            	  index = 1;
            	  System.out.print("HeroStatueConvert "+tableNames);
              } 
              for(; index < Param.addMessage.length(); index ++){
           %>
              <%=Param.addMessage.getString(index)+":" %>
              <input type = "text" id=<%= Param.addMessage.getString(index)%> name=<%= Param.addMessage.getString(index)%> ><br>
           <%
              }
           %>
           <input type = "submit" value = "添加">
           <input type = "reset" value = "清空">
       </form>
</body>
</html>