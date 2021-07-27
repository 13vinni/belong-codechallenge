package com.belong.codeChallenge.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PhoneDetails {
	private Long phoneId;
	private String phoneNumber;
	private boolean activated;
}
