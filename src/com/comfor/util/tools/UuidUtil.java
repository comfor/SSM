package com.comfor.util.tools;

import java.util.UUID;

/**
 * 生成UUID码（通用唯一识别码）
 * @author 三米阳光 2018年8月13日
 */
public class UuidUtil {

	public static String get32UUID() {
		String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
		return uuid;
	}
	
	public static void main(String[] args) {
		System.out.println(get32UUID());
	}
}

