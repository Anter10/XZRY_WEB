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
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>上传数据</title>
</head>
<body>
<p>上传数据</p>
<hr color = "red">
<%

   String has = request.getParameter("has");
   if(has != null){
		SmartUpload upload = new SmartUpload();
		upload.initialize(pageContext);
	
		upload.upload();  
		
		com.jspsmart.upload.File f1 = upload.getFiles().getFile(0);
		if (f1 != null){
			    String types = f1.getFilePathName();
		 	    String fileName = f1.getFileName();
			    String Save_Location= "/res/ExcelData/";
			    String appPath = application.getRealPath(Save_Location);
			    
			    if (filemanager.getFM().isDir(appPath) == false){
			    	filemanager.getFM().createDir(appPath);
			    }
			    Long time = Param.getCurentServerTime();
			    String storePath = appPath+types;
			    f1.saveAs(storePath, upload.SAVE_PHYSICAL);  
			    JSONArray fileJSONArray  = filemanager.readExcelData(storePath);  
		        JSONArray zdArray = fileJSONArray.getJSONArray(0);
		        String tablename = fileName.split("\\.")[0];
		        
		        for(int dataIndex = 0; dataIndex < fileJSONArray.length(); dataIndex++){
		        	String updateSql = "";
		        	JSONArray data = fileJSONArray.getJSONArray(dataIndex);
		        	String select = "select * from " + tablename + " where id = '"+data.getString(0)+"'";
		        	String delete = "delete from " + tablename + " where id = "+data.getString(0)+"";
		        	System.out.print("数据删除成功12 = "+delete+"\n");
	        		boolean hasupdate = DealDatabase.updateDatabase(delete);
		        	String hasId =  DealDatabase.getQuerryJsonStringData(select);
		        	String partDh = ",";
		        	String partSql = "" ;
		        	
		        	
	        		
	        		if(hasupdate == true){
	        			System.out.print("数据删除成功 = "+delete+"\n");
	        		}
	        		
		        	if(hasId == null) {
		        		updateSql = "insert into " + tablename + " values(";
		        		for(int zdIndex = 0; zdIndex < zdArray.length(); zdIndex ++){
		        			if(zdIndex == zdArray.length() -1){
		        				partDh = "";
		        			}
		        			partSql = partSql + "'"+data.getString(zdIndex)+"'" + partDh;
		            	}
		        		partSql = partSql + ")";
		        		updateSql = updateSql + partSql;
			        	System.out.print("SLQYUJU = "+updateSql+"\n");
			            hasupdate = DealDatabase.updateDatabase(updateSql);
		        	}else{
		        		
		        		updateSql = "update "+tablename+" set ";
		        		for(int zdIndex = 0; zdIndex < zdArray.length(); zdIndex ++){
		        			if(zdIndex == zdArray.length() -1){
		        				partDh = "";
		        			}
		        			partSql =  partSql + zdArray.getString(zdIndex) + " = '"+data.getString(zdIndex)+"'"  + partDh;
		            	}
		        		updateSql = updateSql + partSql + "where id = "+data.getString(0)+""; 
			        	System.out.print("更新数据 = "+updateSql+"\n");
			            hasupdate = DealDatabase.updateDatabase(updateSql);
		        	}
		        		
		        	
		         
		         }
		        if (tablename != null){
	        		response.sendRedirect("ShowData.jsp?tablename="+tablename);
	        	}
		}else{
			response.sendRedirect("error.jsp");
		}
 	   
   }
   
   
    

%>
<!-- {pageContext.request.contextPath}/servlet/UploadHandleServlet -->
 <form action="UpExcelData.jsp?has=1" enctype="multipart/form-data" method="post">
       上传Excel文件1：<input type="file" name="file1"><br/>
      
       <input type="submit" value="提交">
 </form>
</body>
</html>