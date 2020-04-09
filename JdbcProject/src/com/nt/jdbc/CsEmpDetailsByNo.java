package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class CsEmpDetailsByNo {
	private static final String EMPDETAILS_QUERY = "{CALL P_GETEMPDETAILSBYNO(?,?,?,?)}";

	public static void main(String[] args) {
		Scanner sc = null;
		Connection con = null;
		CallableStatement cs = null;
		int no = 0;

		try {
			// read inputs
			sc = new Scanner(System.in);
			System.out.println("Enter Employe Number: ");
			no = sc.nextInt();
			
			// Load jdbc driver class
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// get jdbc connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
			// create prepared statement
			if (con != null) {

				cs = con.prepareCall(EMPDETAILS_QUERY);
			}
			// set out parameter
			if (cs != null) {
				cs.registerOutParameter(2, Types.VARCHAR);
				cs.registerOutParameter(3, Types.VARCHAR);
				cs.registerOutParameter(4, Types.FLOAT);
			}
			// set in parameter
			if (cs != null) {
				cs.setInt(1, no);
			}
			// Execute
			if (cs != null) {
				cs.execute();
			}
			// invoke
			System.out.println("Name : " + cs.getString(2));
			System.out.println("Designation : " + cs.getString(3));
			System.out.println("Salary : " + cs.getFloat(4));
		} // try
		catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (cs != null) {
					cs.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
			try {
				if (sc != null) {
					sc.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} // finally

	}// main

}// class
