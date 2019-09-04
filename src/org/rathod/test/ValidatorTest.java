package org.rathod.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.rathod.dinesh.MyValidator;

class ValidatorTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void TestValidateName() {
		Assertions.assertFalse(MyValidator.validateName("dinesh7"));
		Assertions.assertTrue(MyValidator.validateName("Rathod"));
		Assertions.assertFalse(MyValidator.validateName("jennifer@"));
		Assertions.assertTrue(MyValidator.validateName("Lawrence"));
	}

	@Test
	void TestValidatePhoneNumber() {
		Assertions.assertFalse(MyValidator.validatePhoneNumber("70456700"));
		Assertions.assertTrue(MyValidator.validatePhoneNumber("7045800407"));
		Assertions.assertFalse(MyValidator.validatePhoneNumber("704580040@"));
		Assertions.assertTrue(MyValidator.validatePhoneNumber("8790110300"));
	}

	@Test
	void TestUniqueUsername() {
		Assertions.assertTrue(MyValidator.validateUsername("dineshr8"));
		Assertions.assertTrue(MyValidator.validateUsername("Rathodr8"));
		Assertions.assertTrue(MyValidator.validateUsername("jenniferl8"));
		Assertions.assertTrue(MyValidator.validateUsername("Lawrencej8"));
		Assertions.assertFalse(MyValidator.validateUsername("jenniferl8"));
		Assertions.assertFalse(MyValidator.validateUsername("Lawrencej8"));
	}

	@Test
	void TestAlphaNumeric() {
		Assertions.assertFalse(MyValidator.validateAlphaNumeric("dineshr8!"));
		Assertions.assertTrue(MyValidator.validateAlphaNumeric("Rathodr8"));
		Assertions.assertFalse(MyValidator.validateAlphaNumeric("jenniferl8@"));
		Assertions.assertTrue(MyValidator.validateAlphaNumeric("Lawrencej8"));
	}

	@Test
	void TestValidateDOB() {
		Assertions.assertFalse(MyValidator.validateDOB("14-05-1997"));
		Assertions.assertTrue(MyValidator.validateDOB("14.05.1997"));
		Assertions.assertFalse(MyValidator.validateDOB("99-13-1002"));
		Assertions.assertTrue(MyValidator.validateDOB("15.08.1990"));
		Assertions.assertFalse(MyValidator.validateDOB("A9-13-1tt2"));
	}
	
	@Test
	void TestValidatePassword() {
		Assertions.assertTrue(MyValidator.validatePassword("12345678a@"));
		Assertions.assertFalse(MyValidator.validatePassword("12348a@"));
		Assertions.assertFalse(MyValidator.validatePassword("12345678a"));
		Assertions.assertFalse(MyValidator.validatePassword("12345678@"));
	}
}
