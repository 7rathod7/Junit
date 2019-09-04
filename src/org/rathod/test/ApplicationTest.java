package org.rathod.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.rathod.dinesh.Application;
import org.rathod.dinesh.DBConnection;
import org.rathod.dinesh.Gender;

class ApplicationTest {

	Application app;

	@BeforeEach
	void setUp() throws Exception {
		DBConnection.checkConnection();
		DBConnection.setupDB();
		app = new Application();
	}

	@AfterEach
	void tearDown() throws Exception {
		Application.reset();
		DBConnection.removeDataBase();
		DBConnection.closeConnection();
	}

	@Test
	void Testlogin() {
		Assertions.assertTrue(app.register("dinesh", "rathod", "dineshr8", "14.05.1997", "7045800407", Gender.MALE,
				"12345678a@", "Hyderabad"));
		Assertions.assertTrue(app.register("Jennifer", "Lawrence", "jenniel8", "15.08.1990", "7045800407",
				Gender.FEMALE, "12345678a@", "New York"));
		Assertions.assertTrue(app.login("dineshr8", "12345678a@"));
		Assertions.assertTrue(app.login("jenniel8", "12345678a@"));
		Assertions.assertFalse(app.login("dineshr8", "12345658a@"));
		Assertions.assertFalse(app.login("jenniel8", "12345698a@"));
	}

	@Test
	void TestRegistration() {
		Assertions.assertTrue(app.register("dinesh", "rathod", "dineshr8", "14.05.1997", "7045800407", Gender.MALE,
				"12345678a@", "Hyderabad"));
		Assertions.assertTrue(app.register("Jennifer", "Lawrence", "jenniel8", "15.08.1990", "7045800407",
				Gender.FEMALE, "12345678a@", "New York"));
		Assertions.assertFalse(app.register("dinesh", "rathod", "dineshr8", "14.05.1997", "7045800407", Gender.MALE,
				"12345678a@", "Hyderabad"));
		Assertions.assertFalse(app.register("Jennifer", "Lawrence", "jenniel8", "15.08.1990", "7045800407",
				Gender.FEMALE, "12345678a@", "New York"));
	}

	@Test
	void TestResetPassword() {
		Assertions.assertTrue(app.register("dinesh", "rathod", "dineshr8", "14.05.1997", "7045800407", Gender.MALE,
				"12345678a@", "Hyderabad"));
		Assertions.assertTrue(app.register("Jennifer", "Lawrence", "jenniel8", "15.08.1990", "7045800407",
				Gender.FEMALE, "12345678a@", "New York"));
		Assertions.assertTrue(app.resetPassword("dineshr8", "12345678a@", "87654321a@"));
		Assertions.assertFalse(app.resetPassword("dineshr8", "87654311a@", "87654321a@"));
		Assertions.assertTrue(app.resetPassword("jenniel8", "12345678a@", "87654320a@"));
	}

	@Test
	void TestUniqueUser() {
		Assertions.assertTrue(app.register("dinesh", "rathod", "dineshr8", "14.05.1997", "7045800407", Gender.MALE, "12345678a@",
				"Hyderabad"));
		Assertions.assertFalse(app.register("dinesh", "rathod", "dineshr8", "14.05.1997", "7045800407", Gender.MALE, "12345678a@",
				"Hyderabad"));
	}

}
