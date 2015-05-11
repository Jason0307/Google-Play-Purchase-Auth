/**
 * 
 */
package com.augmentum.google.generate.base;

import java.util.List;

/**
 * @author Jason.Zhu
 * @email jasonzhu@augmentum.com.cn
 * @date   May 14, 2014 10:07:55 AM
 */
public class Finder {

	private String finderName;
	private List<Attr> finderAttrs;
	private int returnType;
	public String getFinderName() {
		return finderName;
	}
	public void setFinderName(String finderName) {
		this.finderName = finderName;
	}
	public List<Attr> getFinderAttrs() {
		return finderAttrs;
	}
	public void setFinderAttrs(List<Attr> finderAttrs) {
		this.finderAttrs = finderAttrs;
	}
	public int getReturnType() {
		return returnType;
	}
	public void setReturnType(int returnType) {
		this.returnType = returnType;
	}
	
	
	
	
}
