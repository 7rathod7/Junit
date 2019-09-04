package org.rathod.dinesh;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashSet;
import java.util.Set;

public class MyValidator {
	private final static DateTimeFormatter formatter;
	private static Set<String> registeredUser;
	static {
		formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		registeredUser = new HashSet<String>();
	}

	public static boolean validateName(final String string) {
		// TODO Auto-generated method stub
		boolean check = true;
		for (char c : string.toCharArray()) {
			if (!(c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z')) {
				check = false;
				break;
			}
		}
		return check;
	}

	public static boolean validatePhoneNumber(final String phoneNumber) {
		// TODO Auto-generated method stub
		boolean check = true;
		for (char c : phoneNumber.toCharArray()) {
			if (!(c >= '0' && c <= '9')) {
				check = false;
				break;
			}
		}
		return check && (phoneNumber.length() == 10);
	}

	public static boolean validateUsername(final String string) {
		// TODO Auto-generated method stub
		validateAlphaNumeric(string);
		boolean check = true;
		if (registeredUser.contains(string)) {
			check = false;
		} else {
			if (!Application.checkUsernameExistance(string)) {
				registeredUser.add(string);
			}
		}
		return check;
	}

	public static boolean validateAlphaNumeric(final String string) {
		// TODO Auto-generated method stub
		boolean check = true;
		boolean alpha = false;
		boolean numeric = false;
		for (char c : string.toCharArray()) {
			if (!(c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z' || c >= '0' && c <= '9')) {
				check = false;
				break;
			}
			if (!alpha) {
				if (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z') {
					alpha = true;
				}
			}
			if (!numeric) {
				if (c >= '0' && c <= '9') {
					numeric = true;
				}
			}
		}
		return check && numeric && alpha;
	}

	public static boolean validateDOB(final String dob) {
		// TODO Auto-generated method stub
		try {
			LocalDate.parse(dob, formatter);
		} catch (DateTimeParseException pe) {
			MyLogger.logger.warning(pe.getMessage());
			return false;
		}
		return true;
	}

	public static boolean validatePassword(final String password) {
		// TODO Auto-generated method stub
		if (password.length() > 15 || password.length() < 8) return false; 
		boolean check = true;
		boolean alpha = false;
		boolean numeric = false;
		boolean special = false;
		for (char c : password.toCharArray()) {
			if ( !special &&(c == '@' || c== '!' || c== '$')) {
				special=true;
				continue;
			}
			if (!(c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z' || c >= '0' && c <= '9')) {
				check = false;
				break;
			}
			if (!alpha) {
				if (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z') {
					alpha = true;
				}
			}
			if (!numeric) {
				if (c >= '0' && c <= '9') {
					numeric = true;
				}
			}
		}
		return check && numeric && alpha && special;
	}

}
