package com.augmentum.google.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

import com.augmentum.google.model.GoogleAccountInfo;
import com.augmentum.google.vo.GoogleVo;

public class TokenUtil {
	private static Logger log = Logger.getLogger(TokenUtil.class);

	public static String getTokenByRefreshToken(String game) {
		String token = "";
		GoogleVo googleVo = PropertiesUtil.readFromPropertiesFile();
		GoogleAccountInfo account = GoogleAccountInfo.dao.findByGameUnique(game);
		String tokenUrl = googleVo.getTokenUrl();
		String clientId = account.getStr("clientId");
		String clientSecret = account.getStr("clientSecret");
		String refreshToken = account.getStr("refreshToken");
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode rootNode = null;
		HttpPost httpRequst = new HttpPost(tokenUrl);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("client_id", clientId));
		params.add(new BasicNameValuePair("client_secret", clientSecret));
		params.add(new BasicNameValuePair("grant_type", "refresh_token"));
		params.add(new BasicNameValuePair("refresh_token", refreshToken));
		try {
			httpRequst.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
			HttpResponse httpResponse = closeableHttpClient.execute(httpRequst);
			if (httpResponse.getStatusLine().getStatusCode() == ConstantsUtil.HTTP_OK) {
				HttpEntity httpEntity = httpResponse.getEntity();
				rootNode = objectMapper.readTree(httpEntity.getContent());
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			try {
				closeableHttpClient.close();
			} catch (IOException e) {
				log.error(e.getMessage(), e);
			}
		}
		if (null != rootNode) {
			JsonNode node = rootNode.get("access_token");
			if (null != node) {
				token = node.getTextValue();
			}
		}
		return token;
	}
}
