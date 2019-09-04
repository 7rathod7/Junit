package org.rathod.test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.rathod.dinesh.Gender;
import org.rathod.dinesh.MySerializable;
import org.rathod.dinesh.User;

class TestSerializable {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
		MySerializable.close();
	}

	@Test
	void TestWriting() {
		User user=new User("dinesh", "rathod", "dineshr8", "14.05.1997", "7045800407", Gender.MALE, "12345678",
				"Hyderabad");
		Assertions.assertTrue(MySerializable.writeObj(user));
	}

	@Test
	void TestReading() {
		MySerializable.copy();
		User user=new User("dinesh", "rathod", "dineshr8", "14.05.1997", "7045800407", Gender.MALE, "12345678",
				"Hyderabad");
		Assertions.assertEquals(user,MySerializable.ReadObj());
	}
}
