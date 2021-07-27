package com.belong.codeChallenge.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.belong.codeChallenge.builder.PhoneActivateDTOBuilder;
import com.belong.codeChallenge.common.dto.ValidationResult;
import com.belong.codeChallenge.dto.PhoneActivateDTO;

public class CustomerServiceTest {
	 private CustomerService customerService;
	 
	 private static final Long VALID_CUSTOMER_ID = 3L;
		private static final Long INVALID_CUSTOMER_ID = 20L;
		
		private static final String VALID_CUSTOMER_PHONE_NUMBER = "6597452901";
		private static final String INVALID_CUSTOMER_PHONE_NUMBER = "111111111";

	    @BeforeEach
	    public void setUp() {
	        customerService = new CustomerService();
	        customerService.addCustomerDetails(1L, "XYZ1", "06597452896", 21L, false);
	        customerService.addCustomerDetails(1L, "XYZ1", "6597452896", 22L, false);
	        customerService.addCustomerDetails(1L, "XYZ1", "6597452897", 23L, false);
	        customerService.addCustomerDetails(2L, "XYZ2", "6597452898", 24L, false);
	        customerService.addCustomerDetails(3L, "XYZ3", "6597452899", 25L, false);
	        customerService.addCustomerDetails(3L, "XYZ3", "6597452900", 26L, false);
	        customerService.addCustomerDetails(3L, "XYZ3", "6597452901", 27L, false);
	        customerService.addCustomerDetails(3L, "XYZ3", "6597452902", 28L, false);
	        customerService.addCustomerDetails(4L, "XYZ4", "6597452903", 29L, false);
	        customerService.addCustomerDetails(4L, "XYZ4", "6597452904", 30L, false);
	    }
	    
	    @Test
	    public void getAllPhoneNumberTest() {
	    	Assertions.assertEquals(customerService.getAllPhoneNumbers().size(), 4);
	    	//Stream<Object>  i = customerService.getAllPhoneNumbers().stream().map(z -> z.getPhoneDetails()).a;
	    	
	    }
	    
	    @Test
	    public void getPhoneNumbersByTest() {
	    	Assertions.assertEquals(customerService.getPhoneNumbersBy(3L).getPhoneDetails().size(), 4);
	    	Assertions.assertEquals(customerService.getPhoneNumbersBy(2L).getPhoneDetails().size(), 1);
	    	Assertions.assertEquals(customerService.getPhoneNumbersBy(4L).getPhoneDetails().size(), 2);
	    }
	    
	    @Test
	    public void activatePhoneNumber_InValidCustomerIdTest() {
	    	PhoneActivateDTO phoneActivateDTO = new PhoneActivateDTOBuilder().customerId(INVALID_CUSTOMER_ID).phoneNumber(VALID_CUSTOMER_PHONE_NUMBER).build(); 
	    	ValidationResult vr = customerService.activatePhoneNumber(phoneActivateDTO);
	    	Assertions.assertTrue(vr.hasError());
	    	Assertions.assertEquals(vr.fieldResults.get(0).message, "Invalid CustomerId 20.");
	    }
	    
	    @Test
	    public void activatePhoneNumber_InValidPhoneNumberTest() {
	    	PhoneActivateDTO phoneActivateDTO = new PhoneActivateDTOBuilder().customerId(VALID_CUSTOMER_ID).phoneNumber(INVALID_CUSTOMER_PHONE_NUMBER).build(); 
	    	ValidationResult vr = customerService.activatePhoneNumber(phoneActivateDTO);
	    	Assertions.assertTrue(vr.hasError());
	    	Assertions.assertEquals(vr.fieldResults.get(0).message, "This Phone Number 111111111 does not belong to customerId 3.");
	    }
	    
	    @Test
	    public void activatePhoneNumber_ActivatedPhoneNumberTest() {
	    	PhoneActivateDTO phoneActivateDTO = new PhoneActivateDTOBuilder().customerId(VALID_CUSTOMER_ID).phoneNumber(VALID_CUSTOMER_PHONE_NUMBER).build(); 
	    	ValidationResult vr = customerService.activatePhoneNumber(phoneActivateDTO);
	    	Assertions.assertFalse(vr.hasError());
	    	vr = customerService.activatePhoneNumber(phoneActivateDTO);
	    	Assertions.assertTrue(vr.hasError());
	    	Assertions.assertEquals(vr.fieldResults.get(0).message, "Given Phone number 6597452901 is already activated.");
	    }
}
