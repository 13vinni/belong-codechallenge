package com.belong.codeChallenge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.belong.codeChallenge.common.dto.ApiError;
import com.belong.codeChallenge.common.dto.TelecomResponse;
import com.belong.codeChallenge.common.dto.ValidationFieldResult;
import com.belong.codeChallenge.common.dto.ValidationResult;
import com.belong.codeChallenge.domain.CustomerDetails;
import com.belong.codeChallenge.dto.PhoneActivateDTO;
import com.belong.codeChallenge.service.CustomerService;
import com.google.common.collect.Lists;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController(value = "/customer")
@Api(tags = "CustomerPhone")
public class CustomerPhoneController {

	private final CustomerService customerService;

	@Autowired
	public CustomerPhoneController(CustomerService customerService) {
		this.customerService = customerService;
		this.customerService.seedDataSetup();
	}

	@ApiOperation(value = "This endpoint is used to get all customer's phone numbers.")
	@GetMapping("/phones")
	public ResponseEntity<TelecomResponse> getAllCustomerPhone() {
		List<CustomerDetails> customerDetails = customerService.getAllPhoneNumbers();
		if (customerDetails.isEmpty()) {
			return new ResponseEntity<>(new TelecomResponse(new ApiError("No Customer's Phone is avaliable in our system.")), HttpStatus.OK);
		}
		log.info(String.format("Sucessfully completed the execution of getAllCustomerPhone"));
		return new ResponseEntity<>(new TelecomResponse(customerDetails), HttpStatus.OK);
	}

	@ApiOperation(value = "This endpoint is used to get customer's phone number(s).")
	@GetMapping("/phones/{customerId}")
	public ResponseEntity<TelecomResponse> getCustomerPhone(@PathVariable Long customerId) {
		CustomerDetails customerDetails = customerService.getPhoneNumbersBy(customerId);
		if (customerDetails == null) {
			return new ResponseEntity<>(new TelecomResponse(new ApiError("Invalid Customer Id")), HttpStatus.BAD_REQUEST);
		}
		log.info(String.format("Sucessfully completed the execution of getCustomerPhone"));
		return new ResponseEntity<>(new TelecomResponse(customerDetails), HttpStatus.OK);
	}

	@ApiOperation(value = "This endpoint is used to activate customer's phone number.")
	@PostMapping("/activate")
	public ResponseEntity<TelecomResponse> activateCustomerPhone(@RequestBody PhoneActivateDTO phoneActivateDTO) {
		ValidationResult vr = customerService.activatePhoneNumber(phoneActivateDTO);
		List<String> messages = Lists.newArrayList();

		if (vr.hasError()) {
			List<ValidationFieldResult> validationFieldResults = vr.fieldResults;
			validationFieldResults.forEach(v -> {
				messages.add(v.message);
			});
			return new ResponseEntity<>(new TelecomResponse(messages), HttpStatus.BAD_REQUEST);
		} else {
			messages.add(String.format("Phone Number %s for CustomerId %s is activated.", phoneActivateDTO.getPhoneNumber(), phoneActivateDTO.getCustomerId()));
		}

		log.info(String.format("Sucessfully completed the execution of activateCustomerPhone."));
		return new ResponseEntity<>(new TelecomResponse(messages), HttpStatus.OK);
	}
}
