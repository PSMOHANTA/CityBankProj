package com.nt.jdbc;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.TextArea;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class ShopBillingGUI extends JFrame {
	private static final String GET_ITMS = "SELECT ITEMS FROM PRODUCTS_DETAILS";
	private static final String GET_PR_AVL = "SELECT PRICE,ITEM_AVAILABLE FROM PRODUCTS_DETAILS WHERE ITEMS=?";
	private static final String GET_FULL = "SELECT ITEMS,PRICE,PRICE*? FROM PRODUCTS_DETAILS WHERE ITEMS=?";
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JComboBox comboBox;
	private JComboBox comboBox_1;
	private Connection con;

	private PreparedStatement ps, ps1, ps2;
	private ResultSet rs1, rs2, rs3;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextArea textArea;
	private String item;
	private int nos, sno = 1, total;
	private float t_amt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShopBillingGUI frame = new ShopBillingGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ShopBillingGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		comboBox = new JComboBox<String>();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// get Selected Item from Text box
					item = (String) comboBox.getSelectedItem();
					// set query param values
					ps1.setString(1, item);
					// execute the query
					rs2 = ps1.executeQuery();
					// process the rs2 and set values to text boxes
					if (rs2.next()) {
						textField_2.setText(rs2.getString(1));
						textField_3.setText(rs2.getString(2));
					}
				} // try
				catch (SQLException se) {
					se.printStackTrace();
				}
			}
		});
		comboBox.setBounds(10, 53, 143, 20);
		contentPane.add(comboBox);

		JLabel lblNewLabel = new JLabel("Items :                          Price :     Availablity");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 28, 288, 14);
		contentPane.add(lblNewLabel);

		JButton btnNewButton = new JButton("ADD");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// get Selected Item from Text box
					nos = (int) comboBox_1.getSelectedItem();
					// set query param values
					ps2.setInt(1, nos);
					ps2.setString(2, item);
					// execute the query
					rs3 = ps2.executeQuery();
					// process the rs2 and set values to text boxes
					if (rs3.next()) {
						textArea.append("     " + sno + "           " + rs3.getString(1) + "		" + rs3.getString(2)
								+ "        " + nos + "	     " + rs3.getString(3) + "\n");
						sno = sno + 1;
						t_amt = t_amt + rs3.getFloat(3);
					}
					total = total + nos;

					String f1 = Integer.toString(total);
					String f2 = Float.toString(t_amt);

					textField.setText(f2);
					textField_1.setText(f1);
				} // try
				catch (SQLException se) {
					se.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBounds(363, 52, 61, 23);
		contentPane.add(btnNewButton);

		comboBox_1 = new JComboBox<Integer>();
		comboBox_1.setBounds(308, 53, 45, 20);
		contentPane.add(comboBox_1);

		JLabel lblNewLabel_1 = new JLabel("Qnty.");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(308, 29, 46, 14);
		contentPane.add(lblNewLabel_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 99, 414, 119);
		contentPane.add(scrollPane);

		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setRows(12);
		textArea.setEditable(false);

		JLabel lblNewLabel_2 = new JLabel("SNo");
		lblNewLabel_2.setBounds(20, 84, 46, 14);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Products");
		lblNewLabel_3.setBounds(74, 84, 86, 14);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Price");
		lblNewLabel_4.setBounds(252, 84, 46, 14);
		contentPane.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Qnty.");
		lblNewLabel_5.setBounds(308, 84, 46, 14);
		contentPane.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("Amount");
		lblNewLabel_6.setBounds(373, 86, 46, 14);
		contentPane.add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("Total Amount:");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_7.setBounds(114, 232, 73, 14);
		contentPane.add(lblNewLabel_7);

		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(184, 229, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		JButton btnNewButton_1 = new JButton("Proceed to Checkout");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_1.setBounds(270, 228, 149, 23);
		contentPane.add(btnNewButton_1);

		JLabel lblNewLabel_8 = new JLabel("No. Items :");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_8.setBounds(10, 232, 56, 14);
		contentPane.add(lblNewLabel_8);

		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBounds(72, 229, 35, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setBounds(159, 53, 57, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);

		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setBounds(226, 53, 61, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);

		initializeJdbc();
		this.addWindowListener(new MyWindowAdapter());

	}

	private void initializeJdbc() {

		try {
//register JDBC driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
//establish the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
con.createStatement();
//create PreparedStatement object
			ps = con.prepareStatement(GET_ITMS);
			ps1 = con.prepareStatement(GET_PR_AVL);
			ps2 = con.prepareStatement(GET_FULL);
// execute query
			rs1 = ps.executeQuery();
//copy snos of rs1 to ComboBox
			while (rs1.next()) {
				comboBox.addItem(rs1.getString(1));
			}
			for (int i = 1; i <= 12; i++) {
				comboBox_1.addItem(i);
			}
		} // try
		catch (SQLException se) {
			se.printStackTrace();
		}
		catch (ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
//close jdbc objs
			try {

				if (rs1 != null)
					rs1.close();
			}
			catch (SQLException se) {
				se.printStackTrace();
			}
			try {
				if (ps != null)
					ps.close();
			}
			catch (SQLException se) {
				se.printStackTrace();
			}
		} // finally
	}// initializeJdbc
	private class MyWindowAdapter extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			// close jdbbc objs
			try {
				if (rs2 != null && rs3 != null) {
					rs2.close();
					rs3.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
			try {
				if (ps1 != null && ps2 != null) {
					ps1.close();
					ps2.close();
				}
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
}
