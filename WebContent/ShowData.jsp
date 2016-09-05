<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import = "org.json.*"  %>
<%@ page import = "database.DealDatabase" %>
<%@ page import = "java.io.*" %>
<%@ page import = "javax.sql.*"%>
<%@ page import = "Parameter.*" %>
<%@ page import = "file.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
   String TN = request.getParameter("tablename");
%>
<title><%= TN %></title>
</head>
<body>
<p>微末数据管理 </p>
<hr color = red>
<a href = "test.jsp">数据首页</a>
<a href = "ExportData.jsp">导出JSON数据</a>
<a href = "BeiFen.jsp">备份Excel数据</a>
<a href = "UpExcelData.jsp">上传Excel数据</a>
<hr color = "red">

 
<a href = "PlayerExportData.jsp">玩家数据备份</a>

<hr color="red">
 

   <%@ include file= "../index.jsp" %>
   <hr color = "red">
    <a href = "UpdateData.jsp?tb=<%=TN %>">添加<%=TN %>数据</a>
    <a href = "ExportSingleData.jsp?tb=<%=TN %>"><%=TN %>表导出数据</a>
   <!-- 显示数据 -->
    <%
     String sql = "Select * from "+TN+" order by id ASC";
    
    System.out.print("数据 ＝ "+TN);
     String getData = DealDatabase.getQuerryJsonStringData(sql);
    
     //filemanager.getFM().writeJSONDataToFile(getData, "/Users/guoyouchao/Desktop/Weimo/JSONData/"+TN+".json");
     if (getData != null){
     JSONObject obj = new JSONObject(getData);
     JSONObject objJSON = new JSONObject();
     if (obj != null){
       JSONArray array = obj.getJSONArray("ps");
       JSONArray array1 = obj.getJSONArray("lb");
   
       Param.addMessage = array1;
         objJSON.put("tb", TN);
         objJSON.put("lb", array1);
         //System.out.print("当前索引 "+objJSON.toString()+"\n");  
       int count = array.length();
      %> 
            
       
        <hr color = "red">
        <table border = "1">
        <caption><%=TN %></caption>
      <%
        for(int index = 0; index < array1.length(); index ++){
      %>
           <th><%= array1.get(index).toString() %></th>
              
           <%
            } 
            for(int index1 = 0; index1 < count; index1 ++){
            %>
        
           <tr>
           
           <%
           JSONObject obj1 = new JSONObject();
           String id = "";
           String width = "80";
           for(int index2 = 0; index2 < array1.length(); index2 ++){
        	  obj1 = array.getJSONObject(index1);
        	  id  = obj1.getString(array1.get(0).toString());
        	  if (index2 == array1.length() - 1){
        		  width = "280";
        	  }
           %>  
              <td height  = "60" width=<%=width %> ><%= obj1.getString(array1.get(index2).toString()) %></td>
              
           <%
              }
           
           %>
              <%
               System.out.print("iiiiddd "+count);
               if (count > 1){
               %>
              <td><a href = "DeleteData.jsp?tn=<%=TN %>&&id=<%=id %>">删除</a></td>
              <%
                 }
              %>
              <td><a href = "UpdateModelData.jsp?tn=<%=TN %>&&id=<%=id %>">更新</a></td>
           </tr>
           <%
           
             }
           %>
        </table>
     <%}
     else
     
     %>
     <%
        }
     %>
     
</body>
</html>