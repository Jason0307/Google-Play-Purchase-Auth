package com.augmentum.google.vo;
/**
 * @author Jason.Zhu
 * @date   2013-8-26
 * @email jasonzhu@augmentum.com.cn
 */
public class VerifyReceiptVo {

	private String kind;
	private long purchaseTime;
	private int purchaseState = 1;
	private int consumptionState;
	private String developerPayload;
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public long getPurchaseTime() {
		return purchaseTime;
	}
	public void setPurchaseTime(long purchaseTime) {
		this.purchaseTime = purchaseTime;
	}
	public int getPurchaseState() {
		return purchaseState;
	}
	public void setPurchaseState(int purchaseState) {
		this.purchaseState = purchaseState;
	}
	public int getConsumptionState() {
		return consumptionState;
	}
	public void setConsumptionState(int consumptionState) {
		this.consumptionState = consumptionState;
	}
	public String getDeveloperPayload() {
		return developerPayload;
	}
	public void setDeveloperPayload(String developerPayload) {
		this.developerPayload = developerPayload;
	}
	
}
