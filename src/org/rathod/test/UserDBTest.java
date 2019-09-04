package org.rathod.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.rathod.dinesh.DBConnection;
import org.rathod.dinesh.Gender;
import org.rathod.dinesh.Status;
import org.rathod.dinesh.User;
import org.rathod.dinesh.UserDB;

class UserDBTest {
	User Ironman;
	User captainAmerica;
	User Thor;
	User BlackWidow;
	User MsPotts;

	@BeforeEach
	void setUp() throws Exception {
		DBConnection.checkConnection();
		DBConnection.setupDB();
		Ironman = new User("tony", "stark", "mark31", "04.04.1965", "7045800407", Gender.MALE, "12345678a@", "New York");
		UserDB.addUser("tony", "stark", "mark31", "04.04.1965", "7045800407", Gender.MALE, "12345678a@", "New York");
		captainAmerica = new User("chris", "evans", "captainAmerica1", "13.06.1981", "7045800407", Gender.MALE,
				"12345678a@", "California");
		UserDB.addUser("chris", "evans", "captainAmerica1", "13.06.1981", "7045800407", Gender.MALE, "12345678a@",
				"California");
		Thor = new User("chris", "hemsworth", "thor2", "11.08.1983", "7045800407", Gender.MALE, "12345678a@", "New York");
		UserDB.addUser("chris", "hemsworth", "thor2", "11.08.1983", "7045800407", Gender.MALE, "12345678a@", "New York");
		BlackWidow = new User("natasha", "romanova", "blackwidow3", "22.11.1984", "7045800407", Gender.FEMALE,
				"12345678a@", "California");
		UserDB.addUser("natasha", "romanova", "blackwidow3", "22.11.1984", "7045800407", Gender.FEMALE, "12345678a@",
				"California");
		MsPotts = new User("pepper", "potts", "ironwomen31", "27.09.1972", "7045800407", Gender.FEMALE, "12345678a@",
				"New York");
		UserDB.addUser("pepper", "potts", "ironwomen31", "27.09.1972", "7045800407", Gender.FEMALE, "12345678a@",
				"New York");
	}

	@AfterEach
	void tearDown() throws Exception {
		UserDB.reset();
		DBConnection.removeDataBase();
		DBConnection.closeConnection();
	}

	@Test
	void TestAdduser() {
		Assertions.assertTrue(UserDB.addUser("dinesh", "rathod", "dineshr8", "14.05.1997", "7045800407", Gender.MALE,
				"12345678a@", "Hyderabad"));
		Assertions.assertTrue(UserDB.addUser("Jennifer", "Lawrence", "jenniel8", "15.08.1990", "7045800407",
				Gender.FEMALE, "12345678a@", "New York"));
	}

	@Test
	void TestDeactivateUser() {
		UserDB.deactivateUser("mark31");
		Assertions.assertEquals(Status.NOTACTIVE, UserDB.getActiveStatus("mark31"));
	}

	@Test
	void TestActivateUser() {
		UserDB.activateUser("mark31");
		Assertions.assertEquals(Status.ACTIVE, UserDB.getActiveStatus("mark31"));
	}

	@Test
	void TestSearchUserByUsername() {
		Assertions.assertEquals(Ironman, UserDB.getUserByUsername("mark31"));
		Assertions.assertEquals(captainAmerica, UserDB.getUserByUsername("captainAmerica1"));
		Assertions.assertEquals(null, UserDB.getUserByUsername("captainAmerica"));
	}

	@Test
	void TestSearchUserByName() {
		User[] data = { Ironman };
		Assertions.assertArrayEquals(data, UserDB.getUserByName("tony"));
		User[] data1={captainAmerica};
		Assertions.assertArrayEquals(data1, UserDB.getUserByName("evans"));
		User[] data2={captainAmerica,Thor};
		Assertions.assertArrayEquals(data2, UserDB.getUserByName("chris"));
	}

	@Test
	void TestChangePassword() {
		Assertions.assertTrue(UserDB.addUser("dinesh", "rathod", "dineshr8", "14.05.1997", "7045800407", Gender.MALE,
				"12345678a@", "Hyderabad"));
		Assertions.assertTrue(UserDB.addUser("Jennifer", "Lawrence", "jenniel8", "15.08.1990", "7045800407",
				Gender.FEMALE, "12345678a@", "New York"));
		Assertions.assertTrue(UserDB.changePassword("dineshr8", "12345678a@", "87654321a@"));
		Assertions.assertFalse(UserDB.changePassword("jenniel8", "12345677a@", "87654321a@"));
	}

	@Test
	void TestTotalUser() {
		Assertions.assertEquals(5, UserDB.getSize());
		UserDB.addUser("dinesh", "rathod", "dineshr8", "14.05.1997", "7045800407", Gender.MALE, "12345678a@",
				"Hyderabad");
		UserDB.addUser("Jennifer", "Lawrence", "jenniel8", "15.08.1990", "7045800407", Gender.FEMALE, "12345678a@",
				"New York");
		Assertions.assertEquals(7, UserDB.getSize());
	}

	@Test
	void TestListAllUsers() {
		User[] data = { Ironman, captainAmerica, Thor, BlackWidow, MsPotts };
		Assertions.assertArrayEquals(data, UserDB.listAllUsers());
	}

	@Test
	void TestFilterByAge() {
		User[] data = { Ironman };
		Assertions.assertArrayEquals(data, UserDB.filterByAge(54));
	}

	@Test
	void TestFilterByCity() {
		User[] data1 = { Ironman, Thor, MsPotts };
		Assertions.assertArrayEquals(data1, UserDB.filterByCity("New York"));
		User[] data2 = { captainAmerica, BlackWidow };
		Assertions.assertArrayEquals(data2, UserDB.filterByCity("California"));
	}

	@Test
	void TestFilterByGender() {
		User[] femaleData = { BlackWidow, MsPotts };
		Assertions.assertArrayEquals(femaleData, UserDB.filterByGender(Gender.FEMALE));

		User[] maleData = { Ironman, captainAmerica, Thor };
		Assertions.assertArrayEquals(maleData, UserDB.filterByGender(Gender.MALE));
	}
}
