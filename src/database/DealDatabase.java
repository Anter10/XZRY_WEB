package database;
import java.sql.*; 
import org.json.*;

import com.mysql.jdbc.ResultSetMetaData;
import com.mysql.jdbc.Statement;

import Parameter.Param;
import file.filemanager;

import javax.naming.*;


public class DealDatabase {
    //	得到ResultSet查询结果
	public static String getQuerryJsonStringData(String sql) throws SQLException, JSONException{
	   Statement stmt1 = (Statement) DB.getInstance().getStamt();
  	   ResultSet rs = stmt1.executeQuery(sql);
  	 
  	   ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
  	   String tbname = rsmd.getTableName(1);
//  	   System.out.print("biaoming "+tbname);
  	   int colum = rsmd.getColumnCount();
  	   String [] labelName = new String[colum];
  	
  	   for(int index = 0; index < colum; index ++){
  		 labelName[index] = rsmd.getColumnName(index +1);
  	   }
  	 
  	   JSONArray ta = new JSONArray();
       int index = 0;
       while(rs.next()){
    	   
      	   JSONObject appts = new JSONObject();
       	   for(int ji = 1; ji <= colum; ji ++){
       		  String name = rsmd.getColumnName(ji);
       		  int type = rsmd.getColumnType(ji);
       		  if(type == Param.DOUBLE){
       			 appts.put(name, rs.getDouble(name));
       		  }else if(type == Param.INT){
       			 appts.put(name, rs.getInt(name));
       		  }else if(type == Param.FLOAT){
        		 appts.put(name, rs.getFloat(name));
        	  }else{
       			 appts.put(name, rs.getString(name));
       		  }
       		  
       	   }
       	   ta.put(index, appts);
           index = index + 1;              	
       } 
       JSONObject sendobj = new JSONObject();
       
       sendobj.put("ps", ta);
       sendobj.put("lb",labelName);
       
       if (index == 0){
    	   return null;
       }
      
  	   return sendobj.toString();
	}
	
	
    // 导出数据
	public static String ExportGameData(String sql) throws SQLException, JSONException{
		 Statement stmt1 = (Statement) DB.getInstance().getStamt();
	  	   ResultSet rs = stmt1.executeQuery(sql);
	  	 
	  	   ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
	  	   String tbname = rsmd.getTableName(1);
//	  	   System.out.print("biaoming "+tbname);
	  	   int colum = rsmd.getColumnCount();
	  	   String [] labelName = new String[colum];
	  	
	  	   for(int index = 0; index < colum; index ++){
	  		 labelName[index] = rsmd.getColumnName(index +1);
	  	   }
	  	 
	  	   JSONArray ta = new JSONArray();
	       int index = 0;
	       while(rs.next()){
	      	   JSONObject appts = new JSONObject();
	       	   for(int ji = 1; ji <= colum; ji ++){
	       		  String name = rsmd.getColumnName(ji);
	       		  int type = rsmd.getColumnType(ji);
	       		  if(type == Param.INT && rs.getInt(name) == -101){
	       		    continue;
	       		  }
	       		
	       		  if(type == Param.DOUBLE){
	       			 appts.put(name, rs.getDouble(name));
	       		  }else if(type == Param.INT){
	       			 appts.put(name, rs.getInt(name));
	       			
	       		  }else if(type == Param.FLOAT){
	        		 appts.put(name, rs.getFloat(name));
	        	  }else{
	       			 appts.put(name, rs.getString(name));
	       		  }
	       		  
	       	   }
	       	   ta.put(index, appts);
	           index = index + 1;              	
	       } 
	       
	       if (index == 0){
	    	   return null;
	       }
	       
	   
	  	   return ta.toString();
	}
	// 更新数据库 返回true说嘛更新成功
	public static boolean updateDatabase(String sql) throws SQLException{
		  Statement stmt = (Statement) DB.getInstance().getStamt();
          int a = stmt.executeUpdate(sql);
          boolean yes = false;
          if (a != -1){
        	 yes = true;
          }
          return yes;
	}
	
//	到处单条数据
	   public static void exportSingleData(String dicName, String sql) throws SQLException, JSONException{
		   Statement stmt1 = (Statement) DB.getInstance().getStamt();
	  	   ResultSet rs = stmt1.executeQuery(sql);
	  	 
	 	   ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
	  	   String tbname = rsmd.getTableName(1);
//	  	   System.out.print("biaoming "+tbname);
	  	   int colum = rsmd.getColumnCount();
	  	   String [] labelName = new String[colum];
	  	  
	  	   for(int index = 0; index < colum; index ++){
	  		 labelName[index] = rsmd.getColumnName(index +1);
	  	   }
	  while(rs.next()){
 	   JSONObject appts = new JSONObject();
  	   for(int ji = 1; ji <= colum; ji ++){
  		  String name = rsmd.getColumnName(ji);
  		  int type = rsmd.getColumnType(ji);
  		  if(type == Param.DOUBLE){
  			 appts.put(name, rs.getDouble(name));
  		  }else if(type == Param.INT){
  			 appts.put(name, rs.getInt(name));
  		  }else if(type == Param.FLOAT){
   		   appts.put(name, rs.getFloat(name));
   	      }else{
  			 appts.put(name, rs.getString(name));
  		  }
  	    }
       // 导出文件
  	   String fileName = appts.getString("id");
  	   if(dicName == ""){
  		   
  		 filemanager.getFM().writeJSONDataToFile(appts.toString(), "/Users/ammini/Desktop/xzry/res/JsonData/"+dicName+"/"+fileName+".json");  
  	   }else{
  		 filemanager.getFM().writeJSONDataToFile(appts.toString(), "/Users/ammini/Desktop/xzry/res/JsonData/"+dicName+"/"+fileName+".json");
  	   }
  	  
    } 
	   }
}
