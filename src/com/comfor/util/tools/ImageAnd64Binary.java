package com.comfor.util.tools;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.net.util.Base64;

/**
 * 图片与二进限的相互转换
 * @author 三米阳光 2018年8月10日
 */
public class ImageAnd64Binary {
	
    public static void main(String[] args){    	
		String imgSrcPath 	 = "D:/FaceServer/13231.jpg";     //生成64编码的图片的路径
		String imgCreatePath = "E:\\apache-tomcat-6.0.37\\webapps/pro/ueditor2/jsp/upload1/20140318/480ace2bfc6e44608595bd4adbdeb067.jpg";     //将64编码生成图片的路径
		imgCreatePath=imgCreatePath.replaceAll("\\\\", "/");
		System.out.println(imgCreatePath);
    	String strImg = getImageStr(imgSrcPath);
    	System.out.println(strImg);
        System.out.println(generateImage(strImg, imgCreatePath));
    }
    
   /**
     * 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
     * @param imgSrcPath 生成64编码的图片的路径
     * @return
     */
    public static String getImageStr(String imgSrcPath){
        InputStream in = null;
        byte[] data = null;
        //读取图片字节数组
        try {
            in = new FileInputStream(imgSrcPath);        
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String imageStr = Base64.encodeBase64String(data);
        return imageStr;//返回Base64编码过的字节数组字符串
    }
    
    /**
     * 对字节数组字符串进行Base64解码并生成图片
     * @param imgStr            转换为图片的字符串
     * @param imgCreatePath     将64编码生成图片的路径
     * @return
     */
    public static boolean generateImage(String imgStr, String imgCreatePath){
        if (imgStr == null) //图像数据为空
        {
        	return false;
        }
        try {
            //Base64解码
            byte[] b = Base64.decodeBase64(imgStr);
            for(int i=0;i<b.length;++i) {
                if(b[i]<0) {//调整异常数据
                    b[i]+=256;
                }
            }
            
            OutputStream out = new FileOutputStream(imgCreatePath);    
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e){
            return false;
        }
    }
    
}