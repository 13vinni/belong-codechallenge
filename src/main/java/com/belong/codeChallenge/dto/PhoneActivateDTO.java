package com.belong.codeChallenge.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PhoneActivateDTO {
	private Long customerId;
	private String phoneNumber;
}
