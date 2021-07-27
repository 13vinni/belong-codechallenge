package com.belong.codeChallenge.controller;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import com.belong.codeChallenge.builder.PhoneActivateDTOBuilder;
import com.belong.codeChallenge.dto.PhoneActivateDTO;
import com.belong.codeChallenge.service.CustomerService;
 

@SpringBootTest
public class CustomerPhoneControllerTest {
	private static final Long VALID_CUSTOMER_ID = 3L;
	private static final Long INVALID_CUSTOMER_ID = 20L;
	
	private static final String VALID_CUSTOMER_PHONE_NUMBER = "6597452901";
	private static final String INVALID_CUSTOMER_PHONE_NUMBER = "111111111";
	
	private CustomerPhoneController customerPhoneController;
	private CustomerService customerService;

	@BeforeEach
	public void setUp() {
		this.customerService = new CustomerService();
		this.customerPhoneController = new CustomerPhoneController(customerService);
	}
	
	@Test
	public void getAllCustomerPhoneTest() {
		assertThat(customerPhoneController.getAllCustomerPhone().getStatusCode()).isEqualTo(HttpStatus.OK);
	}
	
	// Test GetPhone Numbers by CustomerId
	@Test
	public void getValidCustomerPhoneTest() {
		assertThat(customerPhoneController.getCustomerPhone(VALID_CUSTOMER_ID).getStatusCode()).isEqualTo(HttpStatus.OK);
	}
	
	@Test
	public void getInValidCustomerPhoneTest() {
		assertThat(customerPhoneController.getCustomerPhone(INVALID_CUSTOMER_ID).getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
	}

	
	
	// Tests for Activate Phone method
	@Test
	public void activateValidCustomerPhone() {
		PhoneActivateDTO phoneActivateDTO = new PhoneActivateDTOBuilder().customerId(VALID_CUSTOMER_ID).phoneNumber(VALID_CUSTOMER_PHONE_NUMBER).build();
		assertThat(customerPhoneController.activateCustomerPhone(phoneActivateDTO).getStatusCode()).isEqualTo(HttpStatus.OK);
	}
	
	@Test
	public void activateInValidPhone() {
		PhoneActivateDTO phoneActivateDTO = new PhoneActivateDTOBuilder().customerId(VALID_CUSTOMER_ID).phoneNumber(INVALID_CUSTOMER_PHONE_NUMBER).build();
		assertThat(customerPhoneController.activateCustomerPhone(phoneActivateDTO).getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
	}
	
	@Test
	public void activateInValidCustomer() {
		PhoneActivateDTO phoneActivateDTO = new PhoneActivateDTOBuilder().customerId(INVALID_CUSTOMER_ID).phoneNumber(VALID_CUSTOMER_PHONE_NUMBER).build();
		assertThat(customerPhoneController.activateCustomerPhone(phoneActivateDTO).getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
	}

	@Test
	public void activateInValidCustomerAndInValidPhone() {
		PhoneActivateDTO phoneActivateDTO = new PhoneActivateDTOBuilder().customerId(INVALID_CUSTOMER_ID).phoneNumber(INVALID_CUSTOMER_PHONE_NUMBER).build();
		assertThat(customerPhoneController.activateCustomerPhone(phoneActivateDTO).getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
	}
}

