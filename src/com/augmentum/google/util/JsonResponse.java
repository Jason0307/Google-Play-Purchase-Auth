/**
 * 
 */
package com.augmentum.google.util;

/**
 * 
 * @author Jason.Zhu
 * @email jasonzhu@augmentum.com.cn
 * @date   May 13, 2014 11:51:30 AM
 * @param <T>
 *  */
public class JsonResponse<T> {

	private T response;
	private int statusCode;
	private String additionalInfo;

	public JsonResponse() {
		super();
	}

	public JsonResponse(int statusCode, T response) {
		this.statusCode = statusCode;
		this.response = response;
	}

	public JsonResponse(int statusCode, T response, String additionalInfo) {
		this.statusCode = statusCode;
		this.response = response;
		this.additionalInfo = additionalInfo;
	}

	public T getResponse() {
		return response;
	}

	public void setResponse(T response) {
		this.response = response;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}


}
