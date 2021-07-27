package com.belong.codeChallenge.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.stream.Collectors;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import com.belong.codeChallenge.common.dto.ValidationFieldResult;
import com.belong.codeChallenge.common.dto.ValidationResult;
import com.belong.codeChallenge.domain.CustomerDetails;
import com.belong.codeChallenge.domain.PhoneDetails;
import com.belong.codeChallenge.dto.PhoneActivateDTO;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Service
public class CustomerService {
	Map<Long, CustomerDetails> customerDetailsMap = Maps.newHashMap();

	private static String INVALID_CUSTOMER_ID_MESSAGE = "Invalid CustomerId %s.";
	private static String PHONE_ALREADY_ACTIVE_MESSAGE = "Given Phone number %s is already activated.";
	private static String INVALID_PHONE_NUMBER_MESSAGE = "This Phone Number %s does not belong to customerId %d.";

	public List<CustomerDetails> getAllPhoneNumbers() {
		List<CustomerDetails> customers = customerDetailsMap.values().stream().collect(Collectors.toList());
		return customers;
	}

	public CustomerDetails getPhoneNumbersBy(Long customerId) {
		CustomerDetails customerDetails = customerDetailsMap.get(customerId);
		return customerDetails;
	}

	public ValidationResult activatePhoneNumber(PhoneActivateDTO phoneActivateDTO) {
		CustomerDetails customerDetails = customerDetailsMap.get(phoneActivateDTO.getCustomerId());
		List<ValidationFieldResult> customerVr = validateCustomerId(phoneActivateDTO);
		ValidationResult vr = ValidationResult.createWith(customerVr);

		if (customerVr.isEmpty()) {
			Optional<PhoneDetails> phoneOptional = customerDetails.getPhoneDetails().stream().filter(p -> p.getPhoneNumber().equals(phoneActivateDTO.getPhoneNumber())).findFirst();
			List<ValidationFieldResult> phoneVr = validatePhoneNumber(phoneOptional, phoneActivateDTO);
			vr = ValidationResult.createWith(phoneVr);

			if (phoneVr.isEmpty()) {
				PhoneDetails phoneDetails = phoneOptional.get();
				List<ValidationFieldResult> activatePhoneVr = alreadyActivatedPhoneValidation(phoneDetails);
				vr = ValidationResult.createWith(activatePhoneVr);

				if (activatePhoneVr.isEmpty()) {
					phoneDetails.setActivated(true);
				}
			}
		}
		return vr;
	}

	public PhoneDetails addCustomerDetails(Long customerId, String customerName, String phoneNumber, Long phoneId, boolean activate) {
		PhoneDetails newPhoneDetails = createPhone(phoneId, phoneNumber, activate);
		CustomerDetails existingCustomer = customerDetailsMap.get(customerId);
		if (existingCustomer == null) {
			CustomerDetails customerDetails = new CustomerDetails();
			customerDetails.setCustomerId(customerId);
			customerDetails.setCustomerName(customerName);
			customerDetails.setPhoneDetails(Lists.newArrayList());
			customerDetails.getPhoneDetails().add(newPhoneDetails);
			customerDetailsMap.put(customerId, customerDetails);
		} else {
			existingCustomer.getPhoneDetails().add(newPhoneDetails);
		}
		return newPhoneDetails;
	}

	/// Validation Methods

	private List<ValidationFieldResult> alreadyActivatedPhoneValidation(PhoneDetails phoneDetails) {
		List<ValidationFieldResult> vfr = Lists.newArrayList();
		if (phoneDetails.isActivated()) {
			vfr.add(ValidationFieldResult.createWith("PhoneNumber", Level.SEVERE, String.format(PHONE_ALREADY_ACTIVE_MESSAGE, phoneDetails.getPhoneNumber())));
		}
		return vfr;
	}

	private List<ValidationFieldResult> validateCustomerId(PhoneActivateDTO phoneActivateDTO) {
		List<ValidationFieldResult> vfr = Lists.newArrayList();
		CustomerDetails customerDetails = customerDetailsMap.get(phoneActivateDTO.getCustomerId());
		if (customerDetails == null) {
			vfr.add(ValidationFieldResult.createWith("customerId", Level.SEVERE, String.format(INVALID_CUSTOMER_ID_MESSAGE, phoneActivateDTO.getCustomerId())));
		}
		return vfr;
	}

	private List<ValidationFieldResult> validatePhoneNumber(Optional<PhoneDetails> phoneOptional, PhoneActivateDTO phoneActivateDTO) {
		List<ValidationFieldResult> vfr = Lists.newArrayList();
		if (!phoneOptional.isPresent()) {
			vfr.add(ValidationFieldResult.createWith("customerId", Level.SEVERE, String.format(INVALID_PHONE_NUMBER_MESSAGE, phoneActivateDTO.getPhoneNumber(), phoneActivateDTO.getCustomerId())));
		}
		return vfr;
	}

	private PhoneDetails createPhone(Long phoneId, String phoneNumber, boolean activate) {
		PhoneDetails phoneDetails = new PhoneDetails();
		phoneDetails.setPhoneId(phoneId);
		phoneDetails.setPhoneNumber(phoneNumber);
		phoneDetails.setActivated(activate);
		return phoneDetails;
	}

	public void seedDataSetup() {
		String customerName1 = RandomStringUtils.random(7, true, false).toUpperCase();
		String customerName2 = RandomStringUtils.random(7, true, false).toUpperCase();
		String customerName3 = RandomStringUtils.random(7, true, false).toUpperCase();
		String customerName4 = RandomStringUtils.random(7, true, false).toUpperCase();
		String customerName5 = RandomStringUtils.random(7, true, false).toUpperCase();
		String customerName6 = RandomStringUtils.random(7, true, false).toUpperCase();

		this.addCustomerDetails(1L, customerName1, "06597452896", 21L, false);
		this.addCustomerDetails(1L, customerName1, "6597452896", 22L, false);
		this.addCustomerDetails(1L, customerName1, "6597452897", 23L, false);
		this.addCustomerDetails(2L, customerName2, "6597452898", 24L, false);
		this.addCustomerDetails(3L, customerName3, "6597452899", 25L, false);
		this.addCustomerDetails(3L, customerName3, "6597452900", 26L, false);
		this.addCustomerDetails(3L, customerName3, "6597452901", 27L, false);
		this.addCustomerDetails(3L, customerName3, "6597452902", 28L, false);
		this.addCustomerDetails(4L, customerName4, "6597452903", 29L, false);
		this.addCustomerDetails(4L, customerName4, "6597452904", 30L, false);
		this.addCustomerDetails(5L, customerName5, "6597452905", 31L, false);
		this.addCustomerDetails(5L, customerName5, "6597452906", 32L, false);
		this.addCustomerDetails(6L, customerName6, "6597452907", 33L, false);
		this.addCustomerDetails(6L, customerName6, "6597452908", 34L, false);
	}

}
