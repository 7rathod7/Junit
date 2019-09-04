package org.rathod.dinesh;

import java.util.ArrayList;
import java.util.LinkedList;

public class UserDB {

	static LinkedList<User> registeredUsers = null;

	static {
		registeredUsers = new LinkedList<User>();
	}

	public static void reset() {
		registeredUsers.clear();
	}

	public static boolean checkUsernameExistance(final String string) {
		// TODO Auto-generated method stub
		boolean check = false;
		for (User user : UserDB.registeredUsers) {
			if (user.getUsername().compareTo(string) == 0) {
				check = true;
				break;
			} 
		}
		return check || checkInDBServer(string);
	}

	private static boolean checkInDBServer(final String username) {
		return false;
	}

	private static void addUserToDBServer(User user) {
		// TODO Auto-generated method stub
		DBConnection.addUser(user);
	}

	public static boolean addUser(final String firstName, final String lastName, final String Username,
			final String DOB, final String phoneNumber, final Gender gender, final String password, final String city) {
		// TODO Auto-generated method stub
		boolean check = false;
		if (!checkUsernameExistance(Username)) {
			if (MyValidator.validateAlphaNumeric(Username) && MyValidator.validateDOB(DOB)
					&& MyValidator.validateName(firstName) && MyValidator.validateName(lastName)
					&& MyValidator.validatePhoneNumber(phoneNumber) && MyValidator.validatePassword(password)) {
				User user = new User(firstName, lastName, Username, DOB, phoneNumber, gender, password, city);
				check = true;
				UserDB.registeredUsers.add(user);
				addUserToDBServer(user);
			}
		}
		return check;
	}

	public static void deactivateUser(final String username) {
		User user = getUserByUsername(username);
		user.deactive();
		DBConnection.updateEntry(user.getUsername(),"status","notactive");
	}

	public static Status getActiveStatus(final String username) {
		// TODO Auto-generated method stub
		return getUserByUsername(username).getActiveStatus();
	}

	public static void activateUser(final String username) {
		// TODO Auto-generated method stub
		User user = getUserByUsername(username);
		user.active();
		DBConnection.updateEntry(user.getUsername(),"status","active");
	}

	public static Object[] filterByGender(final Gender gender) {
		// TODO Auto-generated method stub
		ArrayList<User> res = new ArrayList<User>();
		for (User user : UserDB.registeredUsers) {
			if (user.getGender() == gender) {
				res.add(user);
			}
		}
		return res.toArray();
	}

	public static Object[] filterByCity(final String city) {
		// TODO Auto-generated method stub
		ArrayList<User> res = new ArrayList<User>();
		for (User user : UserDB.registeredUsers) {
			if (user.getCity() == city) {
				res.add(user);
			}
		}
		return res.toArray();
	}

	public static Object[] filterByAge(final int age) {
		// TODO Auto-generated method stub
		ArrayList<User> res = new ArrayList<User>();
		for (User user : UserDB.registeredUsers) {
			if (user.getAge() == age) {
				res.add(user);
			}
		}
		return res.toArray();
	}

	public static Object[] listAllUsers() {
		// TODO Auto-generated method stub
		return  registeredUsers.toArray();
	}

	public static int getSize() {
		// TODO Auto-generated method stub
		return registeredUsers.size();
	}

	public static boolean changePassword(final String username, String oldpassword, String newpassword) {
		// TODO Auto-generated method stub
		return getUserByUsername(username).resetPassword(oldpassword, newpassword);
	}

	public static Object[] getUserByName(final String name) {
		// TODO Auto-generated method stub
		ArrayList<User> res = new ArrayList<User>();
		for (User user : UserDB.registeredUsers) {
			if (user.getFirstName().compareTo(name) == 0 || user.getLastName().compareTo(name) == 0) {
				res.add(user);
			}
		}
		return res.toArray();
	}

	public static User getUserByUsername(final String username) {
		for (User user : UserDB.registeredUsers) {
			if (user.getUsername().compareTo(username) == 0) {
				return user;
			}
		}
		return null;
	}
	
}
