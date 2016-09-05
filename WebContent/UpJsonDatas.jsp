<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import = "com.jspsmart.upload.*" %>
 
<%@ page import = "java.io.DataOutputStream" %>
  
<%@ page import = "org.json.*"  %>
<%@ page import = "database.*" %>
 
<%@ page import = "javax.sql.*"%>
<%@ page import = "Parameter.*" %>
 
<%@ page import = "jxl.*" %>
<%@ page import = "jxl.write.*" %>
<%@ page import = "file.filemanager" %>


<%
       String has = request.getParameter("has").toString();
      
       if (has != null){
    	    SmartUpload upload = new SmartUpload();
			upload.initialize(pageContext);
			upload.upload();  
			com.jspsmart.upload.File f1 = upload.getFiles().getFile(0);   
			if(f1 != null){
			  String types = f1.getFilePathName();
			  String fileName = f1.getFileName();
			  System.out.print("file Name ＝ "+fileName);
			  fileName = fileName.split(".json")[0];
			 
			  //删除数据表
			  String droptable = "drop table if exists "+fileName+"";
			  boolean hasupdate = DealDatabase.updateDatabase(droptable);
			  if(hasupdate == true){
				// response.getWriter().write("ok drop ＝ ");
				/*  String createtable = "create table "/*  */
			  }else{
			    // response.getWriter().write("create table");
			  }
			}else
			{
			response.getWriter().write("没有得到文件");
			}
       }else{
    	    
       }
			
 
%>

 <form action="UpJsonDatas.jsp?has=ss" enctype="multipart/form-data" method="post">
       上传文件1：<input type="file" name="file1"><br/>
       <input type="submit" value="提交">
 </form>