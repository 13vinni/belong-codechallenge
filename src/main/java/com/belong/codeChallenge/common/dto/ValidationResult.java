package com.belong.codeChallenge.common.dto;

import java.util.List;
import java.util.logging.Level;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;

public class ValidationResult {
	public List<ValidationFieldResult> fieldResults;

	public ValidationResult(List<ValidationFieldResult> fieldResults) {
		this.fieldResults = fieldResults;
	}

	public static ValidationResult createWith(ValidationFieldResult... fieldResults) {
		return new ValidationResult(Lists.newArrayList(fieldResults));
	}

	public static ValidationResult createWith(List<ValidationFieldResult> fieldResults) {
		return new ValidationResult(fieldResults);
	}

	public boolean hasError() {
		List<ValidationFieldResult> validationFieldResults = fieldResults.stream().filter(o -> o.level == Level.SEVERE).collect(Collectors.toList());
		int validationFieldResultsCount = validationFieldResults.size();
		return validationFieldResultsCount > 0;
	}
}