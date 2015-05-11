/**
 * 
 */
package com.augmentum.google.generate.base;

/**
 * @author Jason.Zhu
 * @date   2014-3-19
 * @email jasonzhu@augmentum.com.cn
 */
public class Attr {

	private String name;
	private boolean pk;
	private String type;
	private int dbSize;
	private boolean notNull;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isPk() {
		return pk;
	}
	public void setPk(boolean pk) {
		this.pk = pk;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getDbSize() {
		return dbSize;
	}
	public void setDbSize(int dbSize) {
		this.dbSize = dbSize;
	}
	public boolean isNotNull() {
		return notNull;
	}
	public void setNotNull(boolean notNull) {
		this.notNull = notNull;
	}
	@Override
	public String toString() {
		return "Attr [name=" + name + ", pk=" + pk + ", type=" + type
				+ ", dbSize=" + dbSize + ", notNull=" + notNull + "]";
	}
	
	
	
	
	
}
