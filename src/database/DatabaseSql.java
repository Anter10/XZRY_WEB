package database;

import java.sql.ResultSet;

import com.mysql.jdbc.ResultSetMetaData;
import com.mysql.jdbc.Statement;

public class DatabaseSql {
     // 登陆语句
	public static String getLoginSql(String id, String pwd){
		String loginSql = "select * from FYKJ_User where id = '"+id+"' and pwd = '"+pwd+"'";
		return loginSql;
	}
	
	// 注册语句
	public static String getRegisterSql(String uid,String pwd,String register_dev,String registertime){
		String registerSql = "insert into FYKJ_User(FYKJ_User.id,FYKJ_User.pwd,FYKJ_User.registertime,FYKJ_User.registerdev) values('"+uid+"','"+pwd+"','"+registertime+"','"+register_dev+"')";
		return registerSql;
	}
	
	// 获得当前用户是否存在
	public static String getUserIsExit(String uid){
		String uidExistSql = "select id from FYKJ_User where FYKJ_User.id = '"+uid+"'";
		return uidExistSql;
	}
	
	// 首次设置验证码
	public static String setUserRegisterYZM(String id, String yzm,long yztime){
		String setVerificationCodeSql = "insert into FYKJ_Register(FYKJ_Register.id,FYKJ_Register.yzm,FYKJ_Register.registertime) values('"+id+"','"+yzm+"',"+yztime+")";
		return setVerificationCodeSql;
	}
	
	// 更新注册验证码
	public static String updateUserRegisterOfYzm(String id, String yzm,String utime){
		String updateyzmSql = "update FYKJ_Register set yzm = '"+yzm+"', FYKJ_Register.registertime = '"+utime+"' where id = '"+id+"'";
		return updateyzmSql;
	}
	
	// 验证码验证
	public static String VerRegisterOfYzm(String id, String yzm){
		String veryzmSql = "select * from FYKJ_Register where FYKJ_Register.id = '"+id+"' and FYKJ_Register.yzm = '"+yzm+"'";
		return veryzmSql;
	}
	
	// 查看注册表中是否存在用户 uid
	public static String registerUserHasExit(String uid){
		String hasregistertable = "select * from FYKJ_Register where id = '"+uid+"'";
		return hasregistertable;
	}
	
	// 获得用户数据
	public static String getUserDataSql(String uid){
		String userDataSql = "select * from FYKJ_User where FYKJ_User.id = '"+uid+"'";
		return userDataSql;
	}
	 
	
}
