package com.nt.jdbc;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class GUIStudentDetailsByScrollFrame extends JFrame implements ActionListener {
	private static final String STUDENT_DETAILS_QUERY = "SELECT * FROM STUDENT";
	private JLabel lno, lname, ladd, lavg;
	private JTextField tno, tname, tadd, tavg;
	private JButton bFast, bNext, bPrev, bLast;
	private Connection con = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	public GUIStudentDetailsByScrollFrame() {
		setSize(400, 400);
		setBackground(Color.GRAY);
		setTitle("Scrolable Student Details");
		setLayout(new FlowLayout());
		lno = new JLabel("Student Number:");
		add(lno);
		tno = new JTextField(10);
		add(tno);
		lname = new JLabel("Student Name:");
		add(lname);
		tname = new JTextField(10);
		add(tname);
		ladd = new JLabel("Student Address:");
		add(ladd);
		tadd = new JTextField(10);
		add(tadd);
		lavg = new JLabel("Student Average:");
		add(lavg);
		tavg = new JTextField(10);
		add(tavg);
		bFast = new JButton("First");
		bFast.addActionListener(this);
		add(bFast);
		bNext = new JButton("Next");
		bNext.addActionListener(this);
		add(bNext);
		bPrev = new JButton("Previous");
		bPrev.addActionListener(this);
		add(bPrev);
		bLast = new JButton("Last");
		bLast.addActionListener(this);
		add(bLast);
		setVisible(true);
		tno.setEditable(false);
		tname.setEditable(false);
		tadd.setEditable(false);
		tavg.setEditable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initailizejdbc();
		this.addWindowListener(new MyWindowAdapter());
	}

	private void initailizejdbc() {
		System.out.println("GUIStudentDetailsByScrollFrame.initailizejdbc()");
		try {
			// Load jdbc driver class
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// establish the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
			// create JDBC PreparedStatement obj
			ps = con.prepareStatement(STUDENT_DETAILS_QUERY,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs = ps.executeQuery();
		} // try
		catch (ClassNotFoundException cnf) {
			cnf.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		System.out.println("GUIStudentDetailsByScrollFrame.main()");
		new GUIStudentDetailsByScrollFrame();
	}

	private class MyWindowAdapter extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			System.out.println("MiniProject_AllStatementsTest.windowClosing()");
			// close jdbbc objs
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
			try {
				if (ps != null)
					ps.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
			try {
				if (con != null)
					con.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}

		}
	};

	@Override
	public void actionPerformed(ActionEvent ae) {
		System.out.println("GUIStudentDetailsByScrollFrame.actionPerformed()");
		boolean flag = false;
		if (ae.getSource() == bFast) {
			System.out.println("Fast");
			try {
				rs.first();
				flag = true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (ae.getSource() == bNext) {
			System.out.println("next");
			try {
				if (!rs.isLast()) {
					rs.next();
					flag = true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (ae.getSource() == bPrev) {
			System.out.println("Prev");
			try {
				if (!rs.isFirst()) {
					rs.previous();
					flag = true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Last");
			try {
				rs.last();
				flag = true;
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} // if
		if (flag == true) {
			System.out.println("initialised");
			try {
				tno.setText(rs.getString(1));
				tname.setText(rs.getString(2));
				tadd.setText(rs.getString(3));
				tavg.setText(rs.getString(4));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}// action event

}// class
