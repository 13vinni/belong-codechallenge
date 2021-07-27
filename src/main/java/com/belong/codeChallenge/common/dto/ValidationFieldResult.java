package com.belong.codeChallenge.common.dto;

import java.util.logging.Level;

public class ValidationFieldResult {
	public String fieldName;
	public Level level;
	public String message;

	public static ValidationFieldResult createWith(String fieldName, Level level, String message) {
		return new ValidationFieldResult(fieldName, level, message);
	}

	private ValidationFieldResult(String fieldName, Level level, String message) {
		this.fieldName = fieldName;
		this.level = level;
		this.message = message;
	}
}
