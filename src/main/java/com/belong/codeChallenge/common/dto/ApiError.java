package com.belong.codeChallenge.common.dto;

import lombok.Data;

@Data
public class ApiError {
	private String message;

	public ApiError() {

	}

	public ApiError(String message) {
		this();
		this.setMessage(message);
	}
}
