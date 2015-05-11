package com.augmentum.google.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.augmentum.google.vo.GoogleVo;
import com.jfinal.kit.PathKit;

public class PropertiesUtil {

	public static GoogleVo readFromPropertiesFile() {
		GoogleVo googleVo = new GoogleVo();
		String filePath = PathKit.getWebRootPath()
				+ "/WEB-INF/google_auth.properties";
		Properties properties = new Properties();
		InputStream is = null;
		try {
			is = new FileInputStream(new File(filePath));
			properties.load(is);
			String authUrl = properties.getProperty("auth_url");
			String scope = properties.getProperty("scope");
			String responseType = properties.getProperty("response_type");
			String accessType = properties.getProperty("access_type");
			String approval = properties.getProperty("approval_prompt");
			String tokenUrl = properties.getProperty("token_url");
			String grantType = properties.getProperty("grant_type");
			googleVo.setAccessType(accessType);
			googleVo.setAuthUrl(authUrl);
			googleVo.setApproval(approval);
			googleVo.setScope(scope);
			googleVo.setResponseType(responseType);
			googleVo.setTokenUrl(tokenUrl);
			googleVo.setGrantType(grantType);
		} catch (Exception e) {
			return null;
		} finally {
			if (null != is) {
				try {
					is.close();
				} catch (IOException e) {
				}
			}
		}
		return googleVo;
	}
}
