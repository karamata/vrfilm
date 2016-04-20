package com.edinnova.util;

import java.util.ResourceBundle;

import com.opensymphony.xwork2.ActionContext;

public class Configuration {
	public static ResourceBundle getResourceBundle() {
		return ResourceBundle.getBundle("global");
	}

	public static String getResourceString(String key,
			Object... params) {
		ResourceBundle rs = getResourceBundle();
		String text = rs.getString(key);
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				if (params[i] != null) {
					text = text.replace("{" + i + "}", params[i].toString());
				}
			}
		}

		return text;
	}
	
	public static final Integer getAppVersion() {
		return AppSetting.getIntegerValue("app-version");
	}
	
	public static final String getMovieRealPath() {
		return AppSetting.getStringValue("movie-real-path");
	}
	
	public static final String getMovieUrlPath() {
		return AppSetting.getStringValue("movie-url-path");
	}
	
	public static final String getResourcePath() {
		return AppSetting.getStringValue("resource-path");
	}
	
	public static final String getResourcePath(String fileName) {
		return AppSetting.getStringValue("resource-path") + fileName;
	}
	
	public static final String getAdmin() {
		return AppSetting.getStringValue("admin");
	}
	
	public static final String getPassword() {
		return AppSetting.getStringValue("password");
	}
}
