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
        	
        	String json = request.getParameterNames().toString();
        	
        	addSql = "insert into "+addname+"";
        	String startSql = "(";
        	String endSql   = ")";
        	
          	String startSql1 = "(";
        	String endSql1   = ")";
          	
        	String part = ",";
        	String values = "";
        	String startPart = "'";
        	String startPart1 = "'";
        	
/*         	if (addname == "HeroStatueConvert"){
        		index = 1;
        	} */
        	
        	System.out.print("\nvalue12 ＝ "+addname+"\n");
        	for(int index = 0; index < Param.addMessage.length(); index ++){
        		if (index == Param.addMessage.length() - 1 && index > 0){
        			part = "";
        		}
        		String value = Param.addMessage.getString(index);
        		String str = request.getParameter(value.toString());
        	    if(addname.toString() == "HeroStatueConvert"){
        			 str = "1";
        			 System.out.print("\ndbs ＝ "+str+"\n");
        		} 
        	
        		System.out.print("\nvalue ＝ "+str+"\n");
        		startSql1 = startSql1 + startPart + str + startPart1 + part;
        		startSql = startSql + value +part;
      
        	} 
        	startSql1 = startSql1 + endSql1;
        	addSql = addSql + startSql + endSql + " values "+ startSql1;
        	System.out.print("插入语句 ＝ "+addSql);
        	
        	boolean insert = DealDatabase.updateDatabase(addSql);
        	
        	if (insert == true){
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