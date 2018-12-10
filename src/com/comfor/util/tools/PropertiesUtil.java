package com.comfor.util.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 获取与设置配置文件
 * @author 三米阳光 2018年8月10日
 */
public class PropertiesUtil {
	
	 //获取配置信息
	public static String getProperties(String proper,String name) {
		Properties properties = new Properties();
		InputStream inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream(proper);
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties.getProperty(name);
	}
	
	public static void setProperties(String proper, String key,String value){
		String path = PropertiesUtil.class.getResource("/").getPath()+proper;
		Properties prop = new Properties();  
		InputStream fis = null;  
		OutputStream fos = null;  
		try {  
			File file = new File(path);  
			if (!file.exists())  
				file.createNewFile();  
			fis = new FileInputStream(file);  
			prop.load(fis);  
			fis.close();//一定要在修改值之前关闭fis  
			fos = new FileOutputStream(file);  
			prop.setProperty(key, value); //设值-保存
			prop.store(fos, "Update '" + key + "'+ '"+value);  
		} catch (IOException e) {  
		} finally{  
			try {  
				fos.close();  
				fis.close();  
			} catch (IOException e) {  
				e.printStackTrace();  
			}  
		}  
	}
	
	public static void main(String[] args) throws Exception {
//		PropertiesUtil util=new PropertiesUtil();
		setProperties("conf.properties", "ALARM_COUNT", "12");
		System.out.println(PropertiesUtil.getProperties("conf.properties","ALARM_COUNT"));
		
	}
}
