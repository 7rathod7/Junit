package org.rathod.dinesh;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

public class DBConnection {
	private static Connection connection = null;
	private static String dbuser = "root";
	private static String dbpassword = "45";
	private static String dbname = "UserManagement";
	private static String dbtable = "userdata";
	private static Statement stmt = null;
	private static PreparedStatement pstmt = null;

	public static boolean checkConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/", dbuser, dbpassword);
			MyLogger.logger.info("connection to database successful");
			return true;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public static boolean closeConnection() {
		boolean check = false;
		try {
			if (connection != null) {
				connection.close();
				check = true;
				MyLogger.logger.info("terminating conneciton to database successful");
				connection = null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return check;
	}

	public static boolean setupDB() {
		boolean check = false;
		if (connection != null) {
			MyLogger.logger.info("seting db");
			String createDB = "CREATE DATABASE " + dbname + ";";
			String createTB = "CREATE TABLE " + dbtable + "( firstname varchar(30)," + "lastname varchar(30),"
					+ "username varchar(30)," + "dob varchar(30)," + "phone varchar(30),"
					+ "gender ENUM('male','female')," + "password varchar(15)," + "city varchar(30),"
					+ "status ENUM('active','notactive'),PRIMARY KEY(username));";

			ResultSet resultSet = null;
			int status;
			try {
				stmt = connection.createStatement();
				resultSet = stmt.executeQuery("show databases;");
				boolean checkForDB = false;
				while (resultSet.next()) {
					if (resultSet.getString(1).compareTo(dbname) == 0) {
						checkForDB = true;
						MyLogger.logger.info("database already exists");
						break;
					}
				}
				if (!checkForDB) {
					MyLogger.logger.info("no such database exists, trying to create one");
					status = stmt.executeUpdate(createDB);
					MyLogger.logger.info("status code after creating db : " + status);
				}
				status = stmt.executeUpdate("use " + dbname + ";");
				MyLogger.logger.info("status code after setting database to use : " + status);
				resultSet = stmt.executeQuery("show tables;");
				boolean checkForTB = false;
				while (resultSet.next()) {
					if (resultSet.getString(1).compareTo(dbtable) == 0) {
						checkForTB = true;
						MyLogger.logger.info("table already exists");
						check = true;
						break;
					}
				}
				if (!checkForTB) {
					status = stmt.executeUpdate(createTB);
					MyLogger.logger.info("status code after creating tb : " + status);
					check = true;
				} else
					MyLogger.logger.info("table already exists");

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return check;
	}

	public static void addUser(User user) {
		// TODO Auto-generated method stub
		int status;
		try {
			pstmt = connection.prepareStatement("INSERT INTO userdata(firstname,lastname,username,dob"
					+ ",phone,gender,password,city,status) VALUES (?,?,?,?,?,?,?,?,?)");
			pstmt.setString(1, user.getFirstName());
			pstmt.setString(2, user.getLastName());
			pstmt.setString(3, user.getUsername());
			pstmt.setString(4, user.getDOB());
			pstmt.setString(5, user.getPhoneNumber());
			pstmt.setString(6, user.getGender().toString());
			pstmt.setString(7, user.getPassword());
			pstmt.setString(8, user.getCity());
			pstmt.setString(9, user.getActiveStatus().toString());
			try {
				status = pstmt.executeUpdate();
				MyLogger.logger.info("object added : " + status);
			} catch (SQLIntegrityConstraintViolationException e) {
				MyLogger.logger.info(e.getMessage());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void removePreviousData() {
		int status;
		try {
			stmt = connection.createStatement();
			status = stmt.executeUpdate("delete from " + dbtable + ";");
			MyLogger.logger.info("delete all rows from table : " + status);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void removeDataBase() {
		int status;
		try {
			stmt = connection.createStatement();
			status = stmt.executeUpdate("drop database " + dbname + ";");
			MyLogger.logger.info("dropping whole database : " + status);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void updateEntry(String username, String field, String value) {
		// TODO Auto-generated method stub
		int status;
		try {
			pstmt = connection.prepareStatement("UPDATE "+dbtable+" SET "+field+"=? WHERE username=?;");
			pstmt.setString(1, value);
			pstmt.setString(2, username);
			try {
				status = pstmt.executeUpdate();
				MyLogger.logger.info("object modified : " + status);
			} catch (SQLIntegrityConstraintViolationException e) {
				MyLogger.logger.info(e.getMessage());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
