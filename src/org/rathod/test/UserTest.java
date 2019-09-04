package org.rathod.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.rathod.dinesh.Gender;
import org.rathod.dinesh.Status;
import org.rathod.dinesh.User;

class UserTest {

	User user1;
	User user2;

	@BeforeEach
	void setUp() throws Exception {
		user1 = new User("dinesh", "rathod", "dineshr8", "14.05.1997", "7045800407", Gender.MALE, "12345678",
				"Hyderabad");
		user2 = new User("Jennifer", "Lawrence", "jenniel8", "15.08.1990", "7045800407", Gender.FEMALE, "12345678",
				"New York");
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void TestParametricConstructor() {
		Assertions.assertEquals("dinesh", user1.getFirstName());
		Assertions.assertEquals("rathod", user1.getLastName());
		Assertions.assertEquals("dineshr8", user1.getUsername());
		Assertions.assertEquals("14.05.1997", user1.getDOB());
		Assertions.assertEquals("7045800407", user1.getPhoneNumber());
		Assertions.assertEquals(Gender.MALE, user1.getGender());
		Assertions.assertEquals("12345678", user1.getPassword());
		Assertions.assertEquals("Hyderabad", user1.getCity());
		Assertions.assertEquals(Status.NOTACTIVE, user1.getActiveStatus());
	}

	@Test
	void TestValidUser() {
		Assertions.assertEquals(Status.NOTACTIVE, user1.getActiveStatus());
		Assertions.assertEquals(Status.NOTACTIVE, user2.getActiveStatus());
	}

	@Test
	void TestActiveUser() {
		user1.active();
		user2.active();
		Assertions.assertEquals(Status.ACTIVE, user1.getActiveStatus());
		Assertions.assertEquals(Status.ACTIVE, user2.getActiveStatus());
	}

	@Test
	void TestDeactiveUser() {
		user1.active();
		user2.active();
		Assertions.assertEquals(Status.ACTIVE, user1.getActiveStatus());
		Assertions.assertEquals(Status.ACTIVE, user2.getActiveStatus());
		user1.deactive();
		user2.deactive();
		Assertions.assertEquals(Status.NOTACTIVE, user1.getActiveStatus());
		Assertions.assertEquals(Status.NOTACTIVE, user2.getActiveStatus());
	}

	@Test
	void TestUpdatePhoneNumber() {
		user1.setPhoneNumber("8790110330");
		Assertions.assertEquals("8790110330", user1.getPhoneNumber());
		Assertions.assertEquals("7045800407", user2.getPhoneNumber());
	}

	@Test
	void TestResetPassword() {
		Assertions.assertTrue(user1.resetPassword("12345678", "87654321"));
		Assertions.assertFalse(user1.verifyPassword("12345678"));
		Assertions.assertFalse(user1.verifyPassword("887654321"));
		Assertions.assertTrue(user2.verifyPassword("12345678"));
		Assertions.assertFalse(user2.resetPassword("123456789", "87654321"));
	}

	@Test
	void TestUpdateCity() {
		Assertions.assertEquals("Hyderabad", user1.getCity());
		Assertions.assertEquals("New York", user2.getCity());
		user1.updateCity("New York");
		user2.updateCity("Hyderabad");
		Assertions.assertEquals("Hyderabad", user2.getCity());
		Assertions.assertEquals("New York", user1.getCity());
	}
}
