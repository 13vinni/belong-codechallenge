package com.belong.codeChallenge.common.dto;

import lombok.Data;

@Data
public class TelecomResponse {
	private Object payload;
	
	public TelecomResponse(Object payload) {
		this.payload = payload;
	}
}
