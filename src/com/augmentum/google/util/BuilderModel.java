/**
 * 
 */
package com.augmentum.google.util;

import java.util.List;

import com.augmentum.google.generate.base.Model;

/**
 * @author Jason.Zhu
 * @date   2014-3-19
 * @email jasonzhu@augmentum.com.cn
 */
public class BuilderModel {

	private String db;
	private String packageName;
	private List<Model> models;
	public String getDb() {
		return db;
	}
	public void setDb(String db) {
		this.db = db;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public List<Model> getModels() {
		return models;
	}
	public void setModels(List<Model> models) {
		this.models = models;
	}
	
}
