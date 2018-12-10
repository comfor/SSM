package com.comfor.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * 区域控制类
 * 
 * @author
 *
 */
@Controller
@RequestMapping("TiseeSearch")
public class TiseeSearchController {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@RequestMapping("searchImageCallback.do")
	@ResponseBody
	public void searchImageCallback(String result)
	{
		System.out.println("TiseeSearchController.searchImageCallback");
		logger.info("TiseeSearchController.searchImageCallback 接收值："+result);
		String decodedResult;
		try {
			decodedResult = URLDecoder.decode(result, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			decodedResult = result;
			e.printStackTrace();
		}
		logger.info("TiseeSearchController.searchImageCallback 最终返回值："+decodedResult);
	}

}
