package file;
import java.io.*;
import jxl.*;
import jxl.read.biff.BiffException;
import jxl.write.*;
import sun.misc.BASE64Decoder;
 
import org.json.*;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
public class filemanager {
   
	private static  filemanager  fm = new filemanager();
	
	
	public static filemanager getFM(){
		if(fm == null){
			fm = new filemanager();
		}
		return fm;
	}
	
//	读取Excel 数据 将其转换成JSON数据
	 public static JSONArray readExcelData(String path) throws BiffException, IOException, JSONException{
		    WorkbookSettings workbookSettings = new WorkbookSettings();
		    workbookSettings.setEncoding("GBK");
    	   
	        Workbook workbook = Workbook.getWorkbook(new File(path),workbookSettings);
	       
	        Sheet sheet = workbook.getSheet(0);//得到excel第一页的内容
	        int col =    sheet.getColumns();
	        int row =    sheet.getRows();
	        
	        JSONArray finalArray = new JSONArray();
	        for (int i = 0; i < row; i++) {
	            JSONArray tArray = new JSONArray();
	            for (int j = 0; j < col; j++) {
                    String value = sheet.getCell(j,i).getContents();
	                tArray.put(j, value);
	            }
	            finalArray.put(i, tArray);
	        }
//            System.err.println(finalArray.toString());
	        return finalArray;
	    }
	
	// 将文本写入文件
	public void writeJSONDataToFile(String data,String fielName){
		try{ 
			 System.out.print(fielName);
			 File f1 = createFile(fielName);
		     BufferedWriter writer = new BufferedWriter(new FileWriter(f1));
             writer.write(data);
             
		     writer.close();
		}catch(Exception e){
		}
	}
	//创建文件
	public File createFile(String filename) throws IOException{
		File f1 = new File(filename);
		f1.createNewFile();
		return f1;
	}
	
	// 创建目录
	public void createDir(String dir){
		File f1 = new File(dir);
		f1.mkdirs();
	}
    // 判断是否是文件
	public boolean isFile(String filename){
		File f1 = new File(filename);
		return f1.isFile();
	}
	//判断是否是文件夹
	public boolean isDir(String filename){
		File f1 = new File(filename);
		return f1.isDirectory();
	}
	// 判断当前给定的文件或者目录是否存在
	public boolean isExit(String filename){
		File f1 = new File(filename);
		return f1.exists();
	}
	//重新命名文件
	public boolean renameFile(String oldfile,String newfile){
		File f1 = new File(oldfile);
		File f2 = new File(newfile);
		return f1.renameTo(f2);
	}
	public boolean deleteDictionaryOrFile(String name){
		File f = new File(name);
		return f.delete();
	}

	
	public void printDirOfFiles(String dirname){
		File f = new File(dirname);
		if(f.isDirectory()){
			int i = 0;
			for(;i < f.listFiles().length; i++){
				System.out.println("第 "+ String.valueOf(i)+"文件是 ：" + f.listFiles()[i].getName().toString());
			}
		}
	}
	
	// 返回指定目录中的文件
	public File[] getDirOfFiles(String dirname){
		File f = new File(dirname);
		File[] files = new File[f.listFiles().length];
		if(f.isDirectory()){
			int i = 0;
			for(;i < f.listFiles().length; i++){
				if(f.listFiles()[i].isFile()){
					files[i] = f.listFiles()[i];
				}
			}
		}
		return files;
	}
	
	// 文件拷贝 第一个参数拷贝到那个文件 第二个参数 被拷贝的文件  第三个参数 是否覆盖原来的内容
	public boolean copyFileFromTo(String fromfile ,String tofile,boolean append) throws IOException{
		boolean finish = false;
		@SuppressWarnings("resource")
		FileOutputStream output = new FileOutputStream(tofile,append);
		@SuppressWarnings("resource")
		FileInputStream input = new FileInputStream(fromfile);
		int c = 0;
		while((c = input.read()) != -1){
			output.write(c);
		}
		return finish;
	}
	
	// 带缓冲的文件拷贝 第一个参数拷贝到那个文件 第二个参数 被拷贝的文件  第三个参数 是否覆盖原来的内容 第四个参数表示缓冲区域的大小
	public boolean copyFileFromToWithBuffered(String fromfile ,String tofile,boolean append,int size) throws IOException{
		boolean finish = false;
		FileOutputStream output = new FileOutputStream(tofile,append);
		FileInputStream input = new FileInputStream(fromfile);
		@SuppressWarnings("resource")
		BufferedInputStream bufferedinput = new BufferedInputStream(input,size);
		@SuppressWarnings("resource")
		BufferedOutputStream budderedoutput = new BufferedOutputStream(output,size);
		
		int c = 0;
		while((c = bufferedinput.read()) != -1){
			budderedoutput.write(c);
		}
		budderedoutput.flush();
		budderedoutput.close();
		return finish;
	}
	
	// 拷贝文件夹下面所有文件到另一个文件夹
	public void copyDirToDir(String oldDir, String newDir) throws IOException{
		File olddir = new File(oldDir);
		if(olddir.isDirectory() == true){
			File files[] = olddir.listFiles();
			int a = 123;
			for(int fileIndex = 0; fileIndex < files.length; fileIndex ++){
				if(files[fileIndex].isFile()){
					String oldname = files[fileIndex].getPath();
					
					String newname = newDir + files[fileIndex].getName();
					FileOutputStream fo = new FileOutputStream(newname);
					FileInputStream  fi = new FileInputStream(oldname);
					int c = 0;
					while((c = fi.read()) != -1){
						System.out.println(c);
						c = c ^ a;
						fo.write(c);
					}
					fo.flush();
					fo.close();
				}
				
 
//				
//				
//				
//				copyFileFromTo(oldname,newname,true);
			}
		}
	}
	
}
