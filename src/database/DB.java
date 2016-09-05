package database;

import java.sql.*; 

public class DB{
	  private Connection conn = null;
	  private Statement stat = null;
      //加载数据库驱动类
      
                                                        
      private String url = "jdbc:mysql://localhost:3306/Xuzhirongyao?useUnicode=true&characterEncoding=UTF-8";
      //数据库用户名
      private String user = "root";
      //数据库密码 
      private String password = "123456";
      
//      private String url = "jdbc:mysql://121.42.53.24:3306/WMDATABASE?useUnicode=true&characterEncoding=UTF-8";
//      //数据库用户名
//      private String user = "root";
//      //数据库密码 
//      private String password = "e20yEIOd6Ugl";
 
//	  private String url = "jdbc:mysql://mysql.sql88.eznowdata.com/sq_games1?useUnicode=true&characterEncoding=UTF-8";
//	  //数据库用户名
//	  private String user = "sq_games1";
//	  //数据库密码
//	  private String password = "Xzmm323425";    
	    
 
      
      private static DB dbm;
      public DB() throws InstantiationException, IllegalAccessException{
    	      	  try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   	  
      }
      public static DB  getInstance(){
    	  if(dbm == null){
    		  try {
				dbm = new DB();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	  }
    	  return dbm;
      }
      public void closeConn(){
    	  try {
			if (conn != null){
				conn.close();
//				conns.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      } 
      public Statement getStamt(){
    	  try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	  try {
			stat = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
    	  return stat;
      }
      
      
	 
}
