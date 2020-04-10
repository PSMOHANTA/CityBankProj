package com.nt.cj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Test {
	private static final String ACC_LOGIN="SELECT ACC_NO FROM BANK_ACCOUNT_DETAILS WHERE BALANCE=5000";
	public static void main(String[] args) {
	Scanner sc=null;
	Connection con=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	try {
		sc= new Scanner(System.in);
		// register JDBC driver
				Class.forName("oracle.jdbc.driver.OracleDriver");
				// establish the connection
				con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
				// create PreparedStatement
				if (con != null)
					ps = con.prepareStatement(ACC_LOGIN,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
				if (ps != null)
					rs = ps.executeQuery();
				System.out.println(rs.next()==false);
				if(rs.first()) {
					System.out.println("Login Successfully");
					System.out.println(rs.getLong(1)); 
				} 
	} catch (Exception e) {
		e.printStackTrace();
	}

	}

}
