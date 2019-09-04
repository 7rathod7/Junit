package org.rathod.dinesh;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class MySerializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8949802115694829579L;
	private static String ifilename = "input.txt";
	private static String ofilename = "output.txt";
	private static ObjectOutputStream objOut = null;
	private static ObjectInputStream objIn = null;
	private static FileOutputStream fos = null;
	private static FileInputStream fis = null;

	public static boolean writeObj(User obj) {
		try {
			fos = new FileOutputStream(ofilename);
			objOut = new ObjectOutputStream(fos);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean check = false;
		if (objOut != null) {
			try {
				objOut.writeObject(obj);
				check = true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return check;
	}

	public static User ReadObj() {
		try {
			fis = new FileInputStream(ifilename);
			objIn = new ObjectInputStream(fis);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		User user = null;
		if (objIn != null) {
			try {
				try {
					user = (User) objIn.readObject();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return user;
	}

	public static void close() {
		try {
			if (objOut != null)
				objOut.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if (objIn != null)
				objIn.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if (fos != null)
				fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void copy() {
		try {
			OutputStream outputStream = new FileOutputStream(ifilename);
			InputStream inputStream = new FileInputStream(ofilename);
			int data = inputStream.read();
			while (data != -1) {
				outputStream.write(data);
				data = inputStream.read();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
