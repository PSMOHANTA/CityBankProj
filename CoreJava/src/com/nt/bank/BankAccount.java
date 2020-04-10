package com.nt.bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

public class BankAccount {
	private static final String ACC_OPENING = "INSERT INTO BANK_ACCOUNT_DETAILS(ACC_NO,NAME,ADDRESS,BALANCE) VALUES(?,?,?,?)";
	private static final String ACC_LOGIN = "SELECT ACC_NO FROM BANK_ACCOUNT_DETAILS WHERE USERNAME=? AND PASSWORD=?";
	private static final String ACC_DEPOSITE = "UPDATE BANK_ACCOUNT_DETAILS SET BALANCE=BALANCE+? WHERE ACC_NO=?";
	private static final String ACC_WITHDRAW = "UPDATE BANK_ACCOUNT_DETAILS SET BALANCE=BALANCE-? WHERE ACC_NO=?";
	private static final String ACC_REGD = "UPDATE BANK_ACCOUNT_DETAILS SET USERNAME=?,PASSWORD=? WHERE ACC_NO=?";
	Connection con = null;
	PreparedStatement ps = null;
	Scanner sc = null;
	private long accNo = 0;
	private String accHName = null;
	private String address = null;
	private double balance = 0.0, dAmount = 0.0, wAmount = 0.0;
	int count = 0;
	ResultSet rs = null;
	private String usr = null;
	private String psw = null;
	long accountNo;
	void services() {
		System.out.println();
		System.out.print("Choose one option: ");
		System.out.println(
				"Are you want to: \n \n(1) Open Account\n(2) Login\n(3) Deposite \n(4) Withdrawal \n(5) Transfer Amount \n(6) Balance Enquary \n(7) Exit");
		System.out.println();
		System.out.print("Enter one option: ");
	}
	void logServices() {
		System.out.println();
		System.out.print("Choose one option: ");
		System.out.println(
				"Are you want to: \n \n(1) Deposite \n(2) Withdrawal \n(3) Transfer Amount \n(4) Balance Enquary \n(5) Exit");
		System.out.println();
		System.out.print("Enter one option: ");
	}

	void accountOpening() {
		try {
			sc = new Scanner(System.in);
			// read Inputs
			if (sc != null) {
				Random rad = new Random();
				accNo = rad.nextInt(999999999);
				System.out.println("Enter Account Holder Name:");
				accHName = sc.nextLine();
				System.out.println("Enter Account Holder Address:");
				address = sc.nextLine();
				System.out.println("Enter Opeaning Balance:");
				balance = sc.nextDouble();
			}
			// register JDBC driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// establish the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
			// create PreparedStatement
			if (con != null)
				ps = con.prepareStatement(ACC_OPENING);
			// set value to query @param
			if (ps != null) {
				ps.setLong(1, accNo);
				ps.setString(2, accHName);
				ps.setString(3, address);
				ps.setDouble(4, balance);
			}
			if (ps != null)
				count = ps.executeUpdate();
			if (count == 1) {
				System.out.println("Account Opening Successfully of \nAccount No: " + accNo + "\nAccount Holder Name: "
						+ accHName + "\nAccount Holder address=" + address + "\nBalance: " + balance);
			} else {
				System.out.println("Account Opening Faild");
			}
		} catch (SQLException se) {
			se.printStackTrace();

		} catch (ClassNotFoundException cnf) {
			cnf.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void login() {
		try {
			sc = new Scanner(System.in);
			// read Inputs
			if (sc != null) {
				System.out.println("Enter Username:");
				usr = sc.nextLine();
				System.out.println("Enter Password:");
				psw = sc.nextLine();
			}
			// register JDBC driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// establish the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
			// create PreparedStatement
			if (con != null)
				ps = con.prepareStatement(ACC_LOGIN, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			// set value to query @param
			if (ps != null) {
				ps.setString(1, usr);
				ps.setString(2, psw);
			}
			if (ps != null)
				rs = ps.executeQuery();
			if (rs.first()) {
				System.out.println("Login Successfully");
				accountNo = rs.getLong(1);
			} else {
				System.out.println("Invalide username/password");
			}
		} catch (SQLException se) {
			se.printStackTrace();

		} catch (ClassNotFoundException cnf) {
			cnf.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void deposite(long accno) {
		this.accountNo = accno;
		try {
			sc = new Scanner(System.in);
			// read Inputs
			if (sc != null) {
				System.out.println("Enter Deposite Amount:");
				dAmount = sc.nextDouble();
			}
			// register JDBC driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// establish the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
			// create PreparedStatement
			if (con != null)
				ps = con.prepareStatement(ACC_DEPOSITE);
			// set value to query @param
			if (ps != null)
				ps.setDouble(1, dAmount);
			ps.setLong(2, accountNo);
			if (ps != null)
				count = ps.executeUpdate();
			if (count == 1) {
				System.out.println("Amount of INR " + dAmount + " Creadited to your Account");
			} else {
				System.out.println("Amount Deposite Faild");
			}
		} catch (SQLException se) {
			se.printStackTrace();

		} catch (ClassNotFoundException cnf) {
			cnf.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void withdraw(long accno) {
		this.accountNo = accno;
		try {
			sc = new Scanner(System.in);
			// read Inputs
			if (sc != null) {
				System.out.println("Enter Amount Want to Withdraw:");
				wAmount = sc.nextDouble();
			}
			// register JDBC driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// establish the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
			// create PreparedStatement
			if (con != null)
				ps = con.prepareStatement(ACC_WITHDRAW);
			// set value to query @param
			if (ps != null)
				ps.setDouble(1, wAmount);
			ps.setLong(2, accountNo);
			if (ps != null)
				count = ps.executeUpdate();
			if (count == 1) {
				System.out.println("Amount INR " + wAmount + " Debited from Your Account");
			} else {
				System.out.println("You have Insufficient Balance in Your Account");
			}
		} catch (SQLException se) {
			se.printStackTrace();

		} catch (ClassNotFoundException cnf) {
			cnf.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public long getAccountNo() {
		return accountNo;
	}

	public void registration() {
		try {
			sc = new Scanner(System.in);
			// read Inputs
			if (sc != null) {
				System.out.println("Enter Account Number:");
				accountNo = sc.nextLong();
				System.out.println("Enter UserName:");
				usr = sc.next();
				System.out.println("Enter Passord:");
				psw = sc.next();
			}
			// register JDBC driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// establish the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
			// create PreparedStatement
			if (con != null)
				ps = con.prepareStatement(ACC_REGD);
			// set value to query @param
			if (ps != null)
				ps.setString(1, usr);
			ps.setString(2, psw);
			ps.setLong(3, accountNo);
			if (ps != null)
				count = ps.executeUpdate();
			if (count == 1) {
				System.out.println("Registration Success");
			} else {
				System.out.println("Try Again ");
			}
		} catch (SQLException se) {
			se.printStackTrace();

		} catch (ClassNotFoundException cnf) {
			cnf.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
