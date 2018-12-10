package com.comfor.util.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
/**
 * 发送短信
 * @author 三米阳光 2018年8月21日
 */
public class SendSMS {
	
	public static void main(String[] args){
		send("15623581883","【博艺网讯】test");    //信息中一定要带 【博艺网讯】
	}

	/** 发送短信 **/
	public static boolean send(String mob, String msg) {
		String str = "";
		try {
			// 创建HttpClient实例
			HttpClient httpclient = new DefaultHttpClient();
			// 构造一个post对象
			HttpPost httpPost = new HttpPost("http://h.1069106.com:1210/Services/MsgSend.asmx/SendMsg");
			// 添加所需要的post内容
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("userCode", "bywxcf"));
			nvps.add(new BasicNameValuePair("userPass", "bywxcf2015"));
			nvps.add(new BasicNameValuePair("DesNo", mob));
			nvps.add(new BasicNameValuePair("Msg", msg));
			nvps.add(new BasicNameValuePair("Channel", "0"));

			httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
			HttpResponse response = httpclient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				InputStream instreams = entity.getContent();
				str = convertStreamToString(instreams);
				System.out.println(str);
			}

			// HttpRequestSender httpRequest = new HttpRequestSender();
			// //发送 POST 请求
			// String sr=WeixinUtil.httpRequest(
			// "http://h.1069106.com:1210/Services/MsgSend.asmx/SendMsg",
			// "POST",
			// "userCode=XXXXX&userPass=XXXXX&DesNo="+mob+"&Msg="+msg+"&Channel=1");

			Document doc = null;
			doc = DocumentHelper.parseText(str); // 将字符串转为XML
			if (doc == null)
				return false;
			Element rootElt = doc.getRootElement(); // 获取根节点
			if (rootElt == null)
				return false;
			// System.out.println("根节点：" + rootElt.getName()); // 拿到根节点的名称
			System.out.println("根节点的值：" + rootElt.getText()); // 拿到根节点的名称
			if (rootElt.getText() == null || "".equals(rootElt.getText()))
				return false;
			if (Long.parseLong(rootElt.getText()) > 0) {
				return true;
			} else {
				return false;
			}
		} catch (DocumentException e) {
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static String convertStreamToString(InputStream is) {      
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));      
        StringBuilder sb = new StringBuilder();      
        String line = null;      
        try {      
            while ((line = reader.readLine()) != null) {  
                sb.append(line + "\n");      
            }      
        } catch (IOException e) {      
            e.printStackTrace();      
        } finally {      
            try {      
                is.close();      
            } catch (IOException e) {      
               e.printStackTrace();      
            }      
        }      
        return sb.toString();      
    }  
  
	
}
