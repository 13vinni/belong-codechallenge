package com.belong.codeChallenge.builder;

import com.belong.codeChallenge.dto.PhoneActivateDTO;

public class PhoneActivateDTOBuilder {
	private Long customerId;
	private String phoneNumber;

	public PhoneActivateDTO build() {
		PhoneActivateDTO phoneActivateDTO = new PhoneActivateDTO();
		phoneActivateDTO.setCustomerId(customerId);
		phoneActivateDTO.setPhoneNumber(phoneNumber);
		return phoneActivateDTO;
	}

	public PhoneActivateDTOBuilder customerId(Long customerId) {
		this.customerId = customerId;
		return this;
	}

	public PhoneActivateDTOBuilder phoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
		return this;
	}
}
