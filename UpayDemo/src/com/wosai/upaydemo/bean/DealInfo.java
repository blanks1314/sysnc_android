package com.wosai.upaydemo.bean;

import java.io.Serializable;

import cn.wosai.upay.PayMethod;

public class DealInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3497376597955929028L;

	private int id;
	private long amount;
	private String acount;
	private String bNo;
	private String cNo;
	private String payMethod;
	private String time;
	private String state;
	private String orderId;

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public String getAcount() {
		return acount;
	}

	public void setAcount(String acount) {
		this.acount = acount;
	}

	public String getbNo() {
		return bNo;
	}

	public void setbNo(String bNo) {
		this.bNo = bNo;
	}

	public String getcNo() {
		return cNo;
	}

	public void setcNo(String cNo) {
		this.cNo = cNo;
	}

	public PayMethod getPayMethod() {
		return PayMethod.valueOf(payMethod);
	}

	public void setPayMethod(PayMethod payMethod) {
		this.payMethod = payMethod.name();
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

}
