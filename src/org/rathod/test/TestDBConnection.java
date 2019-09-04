package org.rathod.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.rathod.dinesh.DBConnection;

class TestDBConnection {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
		DBConnection.closeConnection();
	}
	
	@Test
	void TestConnection() {
		Assertions.assertTrue(DBConnection.checkConnection());
	}

	@Test
	void TestCloseConnection() {
		Assertions.assertFalse(DBConnection.closeConnection());
	}
	
	@Test
	void TestSetupDB() {
		Assertions.assertTrue(DBConnection.checkConnection());
		DBConnection.setupDB();
	}
}
