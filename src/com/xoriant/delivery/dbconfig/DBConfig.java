package com.xoriant.delivery.dbconfig;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConfig {

	// Connect java application to the database by following steps and also we
	// need
	// to add one external jar- mysql connector
	public static Connection con = null;

	public static Connection getConnection() {
		if (con == null) {
			try {
				// Connect java application to the database
				Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecartdb", "root", "root");
				//System.out.println("=========== Database Connection Created Succesfully========");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Issue Present at the time of creating connection");
			}
		}
		return con;
	}

	public static void closeConnection() {
		if (con != null) {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String args[]) {
		getConnection();

	}
}
