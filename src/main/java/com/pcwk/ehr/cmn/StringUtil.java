package com.pcwk.ehr.cmn;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

public class StringUtil {
	public static String nvl(String value, String defaultValue) {
		  if(null == value || value.trim().isEmpty()){
			  return defaultValue;
		  }		
		return value;
	}
	/*
	 * 32bit UUID 생성
	 * @return String
	 * */
	public static String getUUID() {
		// String resultUUID = "";
		UUID uuidTemp = UUID.randomUUID();
		
		// System.out.println(uuidTemp);
		// System.out.println(uuidTemp.toString().replaceAll("-", "").length());
		
		return uuidTemp.toString().replaceAll("-", "");
	} // String
} // class
