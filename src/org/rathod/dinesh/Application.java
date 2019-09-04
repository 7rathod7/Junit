package org.rathod.dinesh;

import java.util.LinkedList;

public class Application {

	public boolean login(final String username, final String password) {
		// TODO Auto-generated method stub
		boolean check = false;
		if (UserDB.checkUsernameExistance(username)) {
			check = true;
		}
		return check && (UserDB.getUserByUsername(username).getPassword().compareTo(password) == 0 ? true : false);
	}

	public boolean register(final String firstName, final String lastName, final String Username, final String DOB,
			final String phoneNumber, final Gender gender, final String password, final String city) {
		// TODO Auto-generated method stub
		return UserDB.addUser(firstName, lastName, Username, DOB, phoneNumber, gender, password, city);
	}

	public boolean resetPassword(String username, String oldpassword, String newpassword) {
		// TODO Auto-generated method stub
		boolean check=false;
		if(UserDB.checkUsernameExistance(username)) {
			User user = UserDB.getUserByUsername(username);
			if (user.resetPassword(oldpassword, newpassword)) {
				check=true;
			}
		}
		return check;
	}

	public static boolean checkUsernameExistance(final String username) {
		// TODO Auto-generated method stub
		return UserDB.checkUsernameExistance(username);
	}

	public static void reset() {
		// TODO Auto-generated method stub
		UserDB.reset();
	}

}
