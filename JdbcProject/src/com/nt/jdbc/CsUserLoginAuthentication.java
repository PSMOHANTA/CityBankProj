package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class CsUserLoginAuthentication {
	private static final String USER_AUTHO_QUERY = "{CALL P_AUTHENTICATION(?,?,?)}";

	public static void main(String[] args) {
		Scanner sc = null;
		Connection con = null;
		CallableStatement cs = null;
		String user = null;
		String psw = null;
		try {
			// read inputs
			sc = new Scanner(System.in);

			if (sc != null) {
				System.out.println("Enter Username : ");
				user = sc.next();
				System.out.println("Enter Password : ");
				psw = sc.next();
			}
			// establish JDBC connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
			// create jdbc Callable statement
			if (con != null) {
				cs = con.prepareCall(USER_AUTHO_QUERY);
			}
			// set out param
			if (cs != null) {
				cs.registerOutParameter(3, Types.VARCHAR);
			}
			// set in param
			if (cs != null) {
				cs.setString(1, user);
				cs.setString(2, psw);
			}
			// Execute
			if (cs != null) {
				cs.execute();
			}
			System.out.println(cs.getString(3));
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

	}

}
