package com.belong.codeChallenge.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerDetails {
	private Long customerId;
	private String customerName;
	private List<PhoneDetails> phoneDetails;
}
