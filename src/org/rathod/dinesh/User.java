/**
 * 
 */
package org.rathod.dinesh;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

/**
 * 
 * @author rathod
 *
 */
public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String firstName;
	private String lastName;
	private String Username;
	private LocalDate DOB;
	private String phoneNumber;
	private Gender gender;
	private String password;
	private String city;
	private Status status;
	private final static DateTimeFormatter formatter;
	static {
		formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
	}

	public User(final String firstName, final String lastName, final String Username, final String DOB,
			final String phoneNumber, final Gender gender, final String password, final String city) {
		this.city = city;
		this.DOB = LocalDate.parse(DOB, formatter);
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.Username = Username;
		this.status = Status.NOTACTIVE;
	}

	public String getFirstName() {
		// TODO Auto-generated method stub
		return this.firstName;
	}

	public String getLastName() {
		// TODO Auto-generated method stub
		return this.lastName;
	}

	public String getUsername() {
		// TODO Auto-generated method stub
		return this.Username;
	}

	public String getDOB() {
		// TODO Auto-generated method stub
		return this.DOB.format(formatter).toString();
	}

	public String getPhoneNumber() {
		// TODO Auto-generated method stub
		return this.phoneNumber;
	}

	public Gender getGender() {
		// TODO Auto-generated method stub
		return this.gender;
	}

	public String getCity() {
		// TODO Auto-generated method stub
		return this.city;
	}

	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}

	public Status getActiveStatus() {
		// TODO Auto-generated method stub
		return this.status;
	}

	public boolean resetPassword(final String oldPassword, final String newPassword) {
		// TODO Auto-generated method stub
		boolean result = false;
		if (oldPassword.compareTo(this.password) == 0) {
			this.password = newPassword;
			result = true;
		}
		return result;
	}

	public boolean verifyPassword(final String password) {
		// TODO Auto-generated method stub
		return this.password.compareTo(password) == 0 ? true : false;
	}

	public void updateCity(final String city) {
		// TODO Auto-generated method stub
		this.city = city;
	}

	public void setPhoneNumber(String newPhoneNumber) {
		// TODO Auto-generated method stub
		this.phoneNumber = newPhoneNumber;
	}

	public void active() {
		// TODO Auto-generated method stub
		this.status = Status.ACTIVE;
	}

	public void deactive() {
		// TODO Auto-generated method stub
		this.status = Status.NOTACTIVE;
	}

	public int getAge() {
		// TODO Auto-generated method stub
		Period p1 = Period.between(DOB, LocalDate.now());
		return p1.getYears();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		boolean check=false;
		if (obj==null||this==null) return check;
		User b = (User)obj;
		if ((this.city.compareTo(b.city)==0) &&
		(this.DOB.format(formatter).toString().compareTo(b.DOB.format(formatter).toString())==0) &&
		(this.firstName.compareTo(b.firstName)==0) &&
		(this.lastName.compareTo(b.lastName)==0) &&
		(this.gender==b.gender) &&
		(this.password.compareTo(b.password)==0) &&
		(this.phoneNumber.compareTo(b.phoneNumber)==0) &&
		(this.Username.compareTo(b.Username)==0) &&
		(this.status==b.status)) check = true;
		return check;
	}
}
