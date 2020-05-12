package org.demo.entity;

import java.util.Date;

public class Log {
	
	private Integer id;
	
	private String operator;
	
	private String type;
	
	private String result;
	
	private Date optTime;

	public Log() {
		
	}
	
	public Log(Integer id, String operator, String type, String result, Date optTime) {
		super();
		this.id = id;
		this.operator = operator;
		this.type = type;
		this.result = result;
		this.optTime = optTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Date getOptTime() {
		return optTime;
	}

	public void setOptTime(Date optTime) {
		this.optTime = optTime;
	}

	@Override
	public String toString() {
		return "Log [id=" + id + ", operator=" + operator + ", type=" + type + ", result=" + result + ", optTime="
				+ optTime + "]";
	}
}
