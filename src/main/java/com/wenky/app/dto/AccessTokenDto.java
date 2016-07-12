package com.wenky.app.dto;

import java.util.Date;

public class AccessTokenDto {
	private String code;
	private Date expiry;
	public AccessTokenDto() {
		// TODO Auto-generated constructor stub
	}
	
	public Date getExpiry() {
		return expiry;
	}
	public void setExpiry(Date expiry) {
		this.expiry = expiry;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
}
