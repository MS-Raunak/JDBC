package com.ms.jdbc.my_jdbc_crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class App3 {
	private static Connection con;

	// Making Connection With Database
	public static void establishConnection() throws ClassNotFoundException, SQLException {
		String driver_url = "com.mysql.cj.jdbc.Driver";
		String db_url = "jdbc:mysql://localhost:3306/jdbc";

		// Registering the Driver
		Class.forName(driver_url);

		// Establishing the Connection
		con = DriverManager.getConnection(db_url, "root", "tiger");
	}

	// Inserting data in database
	public static void insertingData(int id, String name, int age) throws SQLException {
		// Creating Statement(PreparedStatement)
		PreparedStatement ps = con.prepareStatement("INSERT INTO EMPLOYEE(ID,NAME,AGE) VALUES(?,?,?)");
		ps.setInt(1, id);
		ps.setString(2, name);
		ps.setInt(3, age);

		ps.execute();

		System.out.println("data inserted successfully");
	}

	// Get data by id from database
	public static void GetDataById(int id) throws SQLException {
		// Creating Statement(PreparedStatement)
		PreparedStatement ps = con.prepareStatement("SELECT * FROM EMPLOYEE WHERE ID=?");
		ps.setInt(1, id);

		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			System.out.println("ID: " + rs.getInt(1) + "\t Name: " + rs.getString(2) + "\t Age: " + rs.getInt(3));
		}
	}

	// Getting all data from database
	public static void GetAllData() throws SQLException {
		// Creating Statement(PreparedStatement)
		PreparedStatement ps = con.prepareStatement("SELECT * FROM EMPLOYEE");
		
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			System.out.println("ID: " + rs.getInt(1) + "\t Name: " + rs.getString(2) + "\t Age: " + rs.getInt(3));
		}
	}

	//Updating data by id
	public static void update() throws SQLException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please select the option what you have to update?\n1.Name\n2.Age "); 
		int ch = sc.nextInt();
		
		System.out.println("enter your id: ");
		int id = sc.nextInt();
		
		switch(ch) {
		case 1: {
			System.out.println("enter new name: ");
			String name = sc.next();
			updateName(id,name); break;
		}
		case 2: {
			System.out.println("enter new age: ");
			int age = sc.nextInt();
			updateAge(id, age); break;
		}
		default: System.out.println("Please Select Valid Option");
		}
		
	}
	
	//Update Name
	public static void updateName(int id, String name) throws SQLException {
		PreparedStatement ps = con.prepareStatement("UPDATE EMPLOYEE SET NAME=? WHERE ID=?");
		ps.setString(1, name);
		ps.setInt(2, id);
		ps.execute();
		
		System.out.println("your name is updated successfully");
		
	}
	
	//Update Age
	public static void updateAge(int id, int age) throws SQLException {
		PreparedStatement ps = con.prepareStatement("UPDATE EMPLOYEE SET AGE=? WHERE ID=?");
		ps.setInt(1, age);
		ps.setInt(2, id);
		
		ps.execute();
		
		System.out.println("your age is updated successfully");
	}
	
	//Deleting data by id
	public static void deleteDataById(int id) throws SQLException {
		PreparedStatement ps = con.prepareStatement("DELETE FROM EMPLOYEE WHERE ID=?");
		ps.setInt(1, id);
		
		ps.execute();
		
		System.out.println("data deleted successfully");
	}

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		App3.establishConnection();
		// App3.insertingData(5, "Vishnu", 26);
		//App3.GetDataById(5);
		App3.GetAllData();
		//App3.update();
		App3.deleteDataById(5);
		App3.GetAllData();

	}
}
