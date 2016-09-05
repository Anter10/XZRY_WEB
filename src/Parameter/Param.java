package Parameter;

import org.json.*;

import java.io.*; 
import javax.mail.*;
import java.util.*;
import javax.mail.internet.*;
import java.util.Calendar;  
import java.util.Date; 
import java.net.*;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
 



public class Param {
	

	 
  // 公司邮箱地址
  public static String COMPANYEAMIL = "892466942@qq.com";
  

  //  上传参数字段
  public static String PJ = "pj";
  //  成功返回参数字段
  public static String PS = "ps";
  //  失败返回参数字段
  public static String PE = "pe";
  
  // 相关数据类型
  public static int INT          =    4;
  public static int VARCHAR      =    12;
  public static int DOUBLE       =    8;  
  public static int FLOAT        =    7;
  public static int YEAR         =    91;
  public static String errorKey  =    "error";
  
  public static String showkey   =    "show";
  public static String successKey  =    "success";
  // 获得当前系统时间
  public static long getCurentServerTime(){
	  long curTime = System.currentTimeMillis();
	  return curTime;
  }
  public static JSONArray addMessage;
  public static int MAXNUM = 0;
  public static String updateDataSql;
  public static String addDataSql;
  public static JSONArray updateModelData;
  public static String getSysYMD(){
	  String     ftime     =    "";
	  Calendar   now       =    Calendar.getInstance();  
	  String     year      =    now.get(Calendar.YEAR)         +    "年";
	  String     month     =    (now.get(Calendar.MONTH) + 1)  +    "月" ;
      String     day       =    now.get(Calendar.DAY_OF_MONTH) +    "日";
      String     time      =    now.get(Calendar.HOUR_OF_DAY)  +    "时";
      String     minute    =    now.get(Calendar.MINUTE)       +    "分";
      String     second    =    now.get(Calendar.SECOND)       +    "秒";
      ftime                =    year + month + day + time + minute + second;
      return     ftime;
  }
  //  失败的相关数据代号
  public static JSONObject ERROR_CODE = new JSONObject();
 
  // 所有表名
  public static String [] allgametable = {
		  "HeroStatues","HeroStatueConvert","SystemBattleUnitData","Specialeffects","BattleSceneParamData","AllSpecialData","Rolestateicon","RoleSpecialData","BattleRoleSpecialGrowup","CommonActionData","SystemBuffData","SystemSkillData","SystemSonSkillData"
  };
  
  public static String [] alluserdata = {
		   };
  // 获的JSON对象
  public static JSONObject getJsonObj(){
	  return new JSONObject();
  }
  // 获的JSON数组
  public static JSONArray getJSONArray(){
	  return new JSONArray();
  }
  // 发送数据
  public static void sendData(String data,PrintWriter pw){
	  pw.write(data);
	  pw.flush();
	  pw.close();
  }
  // 产生随机数
  public static String getYZM(){
	  String randStr = "";
	  randStr += (int)(Math.random()*9+1);
	  for(int i = 0; i < 5; i++){
          randStr += (int)(Math.random()*10);
	  }
      return randStr;
  }
  //  发送邮件的方法
  public static boolean SendEmail(String emailaddress,String emailtitle,String message,PrintWriter pw) throws MessagingException{
	  boolean hassend = false;
	  String qm ="xzntxMM323425_"; //您的QQ密码
	  String tu = "qq.com"; //你邮箱的后缀域名
	  Properties props=new Properties();
      props.setProperty("www.anters.net","smtp."+tu);//发信的主机，这里我填写的是我们公司的主机！可以不用修改！
      props.setProperty("mail.smtp.auth","true");
      Session s = Session.getInstance(props);
      s.setDebug(true);
      MimeMessage msg = new MimeMessage(s);
      //给消息对象设置发件人/收件人/主题/发信时间
      InternetAddress from= new InternetAddress("892466942@qq.com"); //这里的115798090 改为您发信的QQ号
      msg.setFrom(from);
      InternetAddress to=new InternetAddress(emailaddress);
      msg.setRecipient(Message.RecipientType.TO,to);
      msg.setSubject(emailtitle);
      msg.setSentDate(new Date());
      //给消息对象设置内容
      BodyPart mdp=new MimeBodyPart();//新建一个存放信件内容的BodyPart对象
      mdp.setContent(message,"text/html;charset=UTF-8");//给BodyPart对象设置内容和格式/编码方式
      MimeMultipart mm = new MimeMultipart();//新建一个MimeMultipart对象用来存放BodyPart对
      //象(事实上可以存放多个)
      mm.addBodyPart(mdp);//将BodyPart加入到MimeMultipart对象中(可以加入多个BodyPart)
      msg.setContent(mm);//把mm作为消息对象的内容
      msg.saveChanges();
      Transport transport=s.getTransport("smtp");
      transport.connect("smtp."+tu,"892466942",qm); //这里的115798090也要修改为您的QQ号码
      try{
    	  transport.sendMessage(msg,msg.getAllRecipients()); 
          transport.close();
          hassend = true;
      }catch(SendFailedException ex){
    	  JSONObject errorObj = new JSONObject();
		  try {
			errorObj.put(Param.errorKey, "验证码已经发到你的邮箱, 请注意查收");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  Param.sendData(errorObj.toString(), pw);
		  hassend = false;
      }
      System.out.print("邮箱发送成功  ＝"+hassend);
	  return hassend;
  }
  // 使用阿里大鱼短信平台发送
  public static JSONObject sendMSGBydy(String toPhone, String msg) throws ApiException{
//	                                                 http://gw.api.taobao.com/router/rest
	  TaobaoClient client = new DefaultTaobaoClient("http://gw.api.taobao.com/router/rest", "23406282", "261aac88b72a489a5f515dc6359e54f4");
      AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
	  req.setExtend("123456");
	  req.setSmsType("normal");
	  req.setSmsFreeSignName("郭有超");
	  req.setSmsParamString(  "{code:'"+msg+"'}"  );
	  req.setRecNum( "15010215839" );
	  req.setSmsTemplateCode( "SMS_12200925" );
	  AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
	  System.out.println(rsp.getBody());
      
	  JSONObject obj = null;
		try {
			System.out.println("发送短信返回数据 ＝ "+rsp.getBody());
			obj = new JSONObject( rsp.getBody());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return obj; 
  }
  
  
  
  // 用手机号发送短信
  public static JSONObject sendMessageToPhoneNumber(String toPhone, String message){
	  
	  String httpUrl = "http://apis.baidu.com/kingtto_media/106sms/106sms";
	  String httpArg = "mobile="+toPhone+"&content="+message+"&tag=2";
	  String jsonResult = request(httpUrl, httpArg);
	  
	  JSONObject obj = null;
	try {
		obj = new JSONObject(jsonResult);
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	System.out.println(obj.toString());
	  return obj;
  }
  
  public static String request(String httpUrl, String httpArg) {
      BufferedReader reader = null;
      String result = null;
      StringBuffer sbf = new StringBuffer();
      httpUrl = httpUrl + "?" + httpArg;

      try {
          URL url = new URL(httpUrl);
          HttpURLConnection connection = (HttpURLConnection) url
                  .openConnection();
          connection.setRequestMethod("GET");
          // 填入apikey到HTTP header
          connection.setRequestProperty("apikey",  "586e025c76f3d64c1048d1fc642fdb83");
          connection.connect();
          InputStream is = connection.getInputStream();
          reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
          String strRead = null;
          while ((strRead = reader.readLine()) != null) {
              sbf.append(strRead);
              sbf.append("\r\n");
          }
          reader.close();
          result = sbf.toString();
      } catch (Exception e) {
          e.printStackTrace();
      }
      return result;
  }
  
  public static void Log(String key,String msg){
	  System.out.println(key + " = " + msg);
  }
  

}
