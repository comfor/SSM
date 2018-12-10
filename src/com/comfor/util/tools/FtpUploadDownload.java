package com.comfor.util.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;
import java.util.List;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
  
/**
 * ftp文件上传下载
 * @author 三米阳光 2018年8月10日
 */
public class FtpUploadDownload {
	
	 public static FTPClient ftpClient = new FTPClient(); 	 
	 private static List<String> afiles;
	 
	 //ftp连接方式一
	 public static FTPClient getFTPClient(String ftpHost,int ftpPort, String ftpUserName, String ftpPassword) {  
			try {  
				ftpClient = new FTPClient();  
				ftpClient.connect(ftpHost, ftpPort);// 连接FTP服务器  
				ftpClient.login(ftpUserName, ftpPassword);// 登陆FTP服务器  
				ftpClient.enterLocalActiveMode();    //主动模式
				//ftpClient.enterLocalPassiveMode(); //被动模式
				ftpClient.setFileType(FTP.BINARY_FILE_TYPE);//设置二进制文件类型
				ftpClient.setFileTransferMode(FTP.STREAM_TRANSFER_MODE); ///传输文件为流的形式
				if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {  
					ftpClient.disconnect();  
				} else {  
				}  
			} catch (SocketException e) {  
				e.printStackTrace();  
			} catch (IOException e) {  
				e.printStackTrace();  
			} catch (Exception e) {
				e.printStackTrace();  
			}
			return ftpClient;  
		} 
	
	  //ftp连接方式二 与 ftp连接方式一 是一样的，只是有些设置不一样
	  public static  boolean connectFTP(String path,String addr,int port,String username,String password) throws Exception {        
	        boolean result = false;
	        int reply;
	        ftpClient = new FTPClient();        
	        ftpClient.connect(addr,port);        
	        ftpClient.login(username,password);     
	        ftpClient.setControlEncoding("GBK");
	        ftpClient.setBufferSize(1024);
	        ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);   
	        ftpClient.setSoTimeout(60000);
	        ftpClient.setDataTimeout(60000);
	        ftpClient.setConnectTimeout(60000);
	        reply = ftpClient.getReplyCode();        
	        if (!FTPReply.isPositiveCompletion(reply)) {  //ftp连接失败
	        	ftpClient.disconnect();    
	            return result;        
	        }  
	        ftpClient.changeWorkingDirectory(path);        
	        result = true;        
	        return result;        
	    }


	/**
	 * 校验ftp连接是否正常 与 ftp连接方式二 一样
	 * */
	public static  boolean ftpIsOnline(String addr,int port,String username,String password) throws Exception {        
		boolean result = false; 
		try {
			int reply;
			ftpClient = new FTPClient();        
			ftpClient.connect(addr,port);        
			ftpClient.login(username,password);     
			ftpClient.setControlEncoding("GBK");
			ftpClient.setBufferSize(1024);
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);   
			ftpClient.setSoTimeout(60000);
			ftpClient.setDataTimeout(60000);
			ftpClient.setConnectTimeout(60000);
			reply = ftpClient.getReplyCode();        
			if (!FTPReply.isPositiveCompletion(reply)) {  //ftp连接失败
				ftpClient.disconnect();    
				return result;        
			}else{
				result = true;
				//退出连接
				ftpClient.logout();
			}  
		} catch (Exception e) {
			
		}
		return result;        
	}

	/**  
	 * ftp连接方式三 直接连接不做设置与上面三种方式是一样的
	 * Description: 向FTP服务器上传文件  
	 * @param host FTP服务器hostname  
	 * @param port FTP服务器端口  
	 * @param username FTP登录账号  
	 * @param password FTP登录密码  
	 * @param ftp 
     * @param basePath FTP服务器基础目录 
     * @param filePath FTP服务器文件存放路径。例如分日期存放：/2015/01/01。文件的路径为basePath+filePath 
     * @param filename 上传到FTP服务器上的文件名  
     * @param input 输入流  
     * @return 成功返回true，否则返回false  
     */    
	 public static FTPClient connFtp(String host, int port, String username, String password){
		 FTPClient ftp = new FTPClient();
		 try {
			 ftp.connect(host, port);// 连接FTP服务器  
	         // 如果采用默认端口，可以使用ftp.connect(host)的方式直接连接FTP服务器  
	         ftp.login(username, password);// 登录  
		} catch (IOException e) {  
            e.printStackTrace();  
        }
		return ftp;
	
	 }
	 
	 /**
	  * 关闭ftp的连接
	  * @param ftp
	  */
	 public static void closeFtp(FTPClient ftp){
		 	try {
				ftp.logout();
			} catch (IOException e) {
				e.printStackTrace();
			}finally {  
	            if (ftp.isConnected()) {  
	                try {  
	                    ftp.disconnect();  
	                } catch (IOException ioe) {  
	                }  
	            }  
	        }  
	 }
	 
	/**
	 * 图片上传
	 * @param ftp
	 * @param basePath
	 * @param filePath
	 * @param filename
	 * @param inputStream
	 * @param radio
	 * @return
	 */
	public static boolean uploadFile1(FTPClient ftp, String basePath, String filePath, String filename,InputStream inputStream, String radio) {
		boolean result = false;
		try {
			boolean cwd1 = ftp.changeWorkingDirectory(basePath + filePath);
			//切换到上传目录
			if (!cwd1) 
			{
				//如果目录不存在创建目录
				String[] dirs = filePath.split("/");   //表示可能有多重目录
				String tempPath = basePath;
				for (String dir : dirs) 
				{
					if (null == dir || "".equals(dir))
					{
						continue;
					}
					else
					{
						tempPath += dir + "/";
					}
					boolean cwd2 = ftp.changeWorkingDirectory(tempPath);
					if (!cwd2) 
					{
						//如果此目录不存在创建目录 
						boolean mkdir1 = ftp.makeDirectory(tempPath);
						if (!mkdir1) 
						{
							return result;
						} 
						else 
						{
							//如果创建成功则进入下一层目录
							tempPath += radio + "/";
							boolean cwd3 = ftp.changeWorkingDirectory(tempPath);
							if (!cwd3)
							{
								//如果此层目录不成存则创建如果成存则进入
								boolean mkdir2 = ftp.makeDirectory(tempPath);
								if (!mkdir2) 
								{
									return result;
								} 
								else 
								{
									boolean cwd4 = ftp.changeWorkingDirectory(tempPath);
								}
							}
						}
					}
				}
			}
			//如果此目录存在则进入
			else {
				String path = basePath + filePath;
				path += "/" + radio + "/";
				boolean cwd5 = ftp.changeWorkingDirectory(path);
				if (!cwd5) {
					boolean mkdir3 = ftp.makeDirectory(path);
					if(!mkdir3)
					{
						return result;
					}
					else
					{
						ftp.changeWorkingDirectory(path);
					}
				} 
				else 
				{
					ftp.changeWorkingDirectory(path);
				}
			}
			//此步可以不用，通过显示目录下的文件来判断是否成功进入到该目录中
			FTPFile[] fss = ftp.listFiles();
			//上传文件  上传的一些设置在获取ftp设置过
			boolean stf = ftp.storeFile(filename, inputStream);
			//boolean stf = ftp.storeFile(new String(filename.getBytes("UTF-8"),"iso-8859-1"), inputStream);  //中文乱码的问题
			if(!stf)
			{
				//如果没有上传成功
				return result;
			}
			else
			{
				//如果上传成功
				inputStream.close();
				result = true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}  
	
	public static boolean uploadFile2(FTPClient ftp, String basePath, String filePath, String filename,InputStream input) {
		boolean result = false;
		try {
			// 切换到上传目录 直接切换到/LInspect/RealVideo/目录，视频不用建子目录
			boolean cwd1 = ftp.changeWorkingDirectory(basePath);
			if(!cwd1) 
			{
				//如果目录不存在创建目录
				String[] dirs = filePath.split("/");
				String tempPath = basePath;
				for (String dir : dirs) 
				{
					if (null == dir || "".equals(dir))
					{
						continue;
					}
					else
					{
						tempPath += "/" + dir + "/" ;
					}
					boolean cwd2 = ftp.changeWorkingDirectory(tempPath);
					if (!cwd2) 
					{
						//如果进入没成功则创建目录
						boolean mkdir1 = ftp.makeDirectory(tempPath);
						if (!mkdir1) 
						{
							//目录创建不成功
							return result;
						} 
						else 
						{
							//目录创建成功则进入
							ftp.changeWorkingDirectory(tempPath);
						}
					}
				}
			}
			//此步可以不用，通过显示目录下的文件来判断是否成功进入到该目录中
			FTPFile[] fss = ftp.listFiles();
			//上传文件  上传的一些设置在获取ftp设置过
			boolean stf = ftp.storeFile(filename, input);
			//boolean stf = ftp.storeFile(new String(filename.getBytes("UTF-8"),"iso-8859-1"), input);  //中文乱码的问题
			if(!stf)
			{
				//如果没有上传成功
				return result;
			}
			else
			{
				//如果上传成功
				input.close();
				ftp.logout();	//此处直接断开ftp是因为视频不会多个上传，而是只有一个，因为可以上传完后直接关闭
				result = true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
				}
			}
		}
		return result;
	}  
	 
	 
    public static boolean uploadFile(String host, int port, String username, String password, String basePath,  
            String filePath, String filename, InputStream input) {  
        boolean result = false;  
        FTPClient ftp = new FTPClient();  
        try {  
            int reply;  
            ftp.connect(host, port);// 连接FTP服务器  
            // 如果采用默认端口，可以使用ftp.connect(host)的方式直接连接FTP服务器  
            ftp.login(username, password);// 登录  
            //ftp.setControlEncoding("GBK");
            ftp.setControlEncoding("UTF-8"); // 中文支持  
            reply = ftp.getReplyCode();  
            if (!FTPReply.isPositiveCompletion(reply)) {  
                ftp.disconnect();  
                return result;  
            }  
            String changePath=basePath.endsWith("/")?basePath:basePath+filePath;
            //切换到上传目录  
            if (!ftp.changeWorkingDirectory(changePath)) {  
                //如果目录不存在创建目录  
                String[] dirs = filePath.split("/");  
                String tempPath = basePath;  
                for (String dir : dirs) {  
                    if (null == dir || "".equals(dir)) continue;  
                    tempPath += "/" + dir;  
                    if (!ftp.changeWorkingDirectory(tempPath)) {  
                        if (!ftp.makeDirectory(tempPath)) {  
                            return result;  
                        } else {  
                            ftp.changeWorkingDirectory(tempPath);  
                        }  
                    }  
                }  
            }  
            //设置上传文件的类型为二进制类型  
            ftp.setFileType(FTP.BINARY_FILE_TYPE);  
            //上传文件  
            if (!ftp.storeFile(filename, input)) {  
                return result;  
            }  
            input.close();  
            ftp.logout();  
            result = true;  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            if (ftp.isConnected()) {  
                try {  
                    ftp.disconnect();  
                } catch (IOException ioe) {  
                }  
            }  
        }  
        return result;  
    }  
    
    /** 
     * 递归遍历出目录下面所有文件 
     * @param pathName 需要遍历的目录，必须以"/"开始和结束 
     * @throws IOException 
     */  
    public static void SonList(String pathName) throws IOException{  
        if(pathName.startsWith("/") && pathName.endsWith("/")){  
            String directory = pathName;  
            //更换目录到当前目录 
            ftpClient.changeWorkingDirectory(directory);  
            FTPFile[] files = ftpClient.listFiles();  
            for(FTPFile file:files){  
                if(file.isFile()){  
                	afiles.add(file.getName());  
                }else if(file.isDirectory()){  
                	SonList(directory + file.getName() + "/");  
                }  
            }  
        }  
    }
    
    /**
     * 判斷目錄是否存在，存在進目錄并返回true,不存在返回false
     * @param path
     * @return
     * @throws IOException
     */
    public static boolean isExist(String path) throws IOException {
    	FTPFile[] files = ftpClient.listFiles();
    	boolean flag = false;
    	for(FTPFile file : files) {
    		if(file.getName().contains(path)) {
    			flag = true;
    			ftpClient.changeWorkingDirectory(path);  
    		}
    	}
    	return flag;
    }
      
    /**  
     * Description: 从FTP服务器下载文件  
     * @param host FTP服务器hostname  
     * @param port FTP服务器端口  
     * @param username FTP登录账号  
     * @param password FTP登录密码  
     * @param remotePath 要下載的文件所在目錄
     * @param localPath 下载后保存到本地的路径  
     * @param locationCode 
     * @return  
     */    
	public static String downloadFile(FTPClient ftpClient, String localPath, String remotePath) {
		String picpath = "";
		try {
			String ftpPicturePath = PropertiesUtil.getProperties("conf.properties", "ftpPicturePath");
			boolean cwd1 = ftpClient.changeWorkingDirectory(ftpPicturePath + remotePath);
			if(cwd1)
			{
				//显示此目录下全部文件
				FTPFile[] fs = ftpClient.listFiles();
				//获取图片在项目中的存储目录，如果不存在则新建
				File localFile1 = new File(PathUtil.getClasspath() + localPath);
				boolean fileExit = localFile1.exists();
				if (!fileExit) 
				{
					localFile1.mkdirs();
				}
				//分别下载每张图片
				for (FTPFile ff : fs) 
				{
					//每张图片下载的位置与名称
					File localFile = new File(PathUtil.getClasspath() + localPath + "/" + ff.getName());
					//changeWorkingDirectory是用于ftp的，而不是下载到本地的路经，下载本地直接通过输出流形成（输出流也是相对ftp来说是输出）
					OutputStream output = new FileOutputStream(localFile);
					//此处只是用于地图显示才拼接的图片位置
					picpath += localPath.replaceAll("\\\\", "/") + ff.getName() + ";";
					//文件下载,设置文件名中文乱码问题
					ftpClient.retrieveFile(ff.getName(), output);
					//ftpClient.retrieveFile(new String(ff.getName().getBytes("UTF-8"),"iso-8859-1"), output);
					output.close();
				}
			}
			else
			{
				picpath="";
			}
			ftpClient.logout();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ftpClient.isConnected()) {
				try {
					ftpClient.disconnect();
				} catch (IOException ioe) {
				}
			}
		}
		return picpath;
	}  

	public static String downloadFileVio(FTPClient ftpClient, String localPath, String remotePath, String fileName) {
		String viopath = "";
		try {
			String ftpvideoPath = PropertiesUtil.getProperties("conf.properties", "ftpVideoPath");
			boolean cwd1 = ftpClient.changeWorkingDirectory(ftpvideoPath);
			if(cwd1)
			{
				//显示目录下全部视频
				FTPFile[] fs = ftpClient.listFiles();
				for (FTPFile ff : fs) 
				{
					if (ff.getName().equals(fileName))    //判断获取名称是否相同
					{
						//下载视频到本地项目路经
						File localFile = new File(PathUtil.getClasspath() + localPath);
						if (!localFile.exists()) 
						{
							localFile.mkdirs();
						}
						//changeWorkingDirectory是用于ftp的，而不是下载到本地的路经，下载本地直接通过输出流形成（输出流也是相对ftp来说是输出）
						OutputStream output = new FileOutputStream(localFile + "/" + ff.getName());
						//此处只是用于地图显示才拼接的图片位置
						viopath += localPath.replaceAll("\\\\", "/") + "/" + ff.getName() + ";";
						//文件下载,设置文件名中文乱码问题
						ftpClient.retrieveFile(ff.getName(), output);
						//ftpClient.retrieveFile(new String(ff.getName().getBytes("UTF-8"),"iso-8859-1"), output);
						output.close();
					}
				}
			}
			else
			{
				viopath="";
			}
			ftpClient.logout();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ftpClient.isConnected()) {
				try {
					ftpClient.disconnect();
				} catch (IOException ioe) {
				}
			}
		}
		return viopath;
	}  
  
    public static void main(String[] args) {  
        try {    
        	FileInputStream in=new FileInputStream(new File("F:/swf/0123456789.swf"));    
        	boolean flag = uploadFile("10.10.23.189", 21, "ftpboyi", "boyi@bj.com", "/home/ftpboyi/LInspect/RealVideo/","/swf/", "1111.swf", in);   
        	//String filePath = PathUtil.getClasspath() + "static/js/myjs" ;  
        	//boolean flag1 =downloadFile("10.10.23.189", 21, "ftpboyi", "boyi@bj.com", "/home/ftpboyi/LInspect/RealVideo/1268809174","1268809174.swf", filePath);    
            System.out.println(flag);    
        }catch (Exception e) {
        	   e.printStackTrace();    
		}  
    }

	
}
