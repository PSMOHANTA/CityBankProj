package com.nt.bank;

import java.util.Scanner;

public class Bank {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		BankAccount ba = new BankAccount();
		long accno = ba.getAccountNo();
		x: while (true) {
			ba.services();
			int key = sc.nextInt();
			 switch (key) {
			case 1:
				ba.accountOpening();
				ba.registration();
				break;
			case 2:
				ba.login();
				y: while (true) {
					ba.logServices();
					int key1 = sc.nextInt();
					switch (key1) {
					case 1:
						ba.deposite(ba.getAccountNo());
						break;
					case 2:
						ba.withdraw(ba.getAccountNo());
						break;
					case 3:
						break;
					case 4:
						ba.deposite(accno);
						break;
					case 5:
						System.out.println("SignOut Successfully");
						break y;
					default:
						break;
					}
				}
				break;
			case 3:
				System.out.println("Enter Account Number:");
				accno = sc.nextLong();
				ba.deposite(accno);
				break;
			case 4:
				System.out.println("Enter Account Number:");
				accno = sc.nextLong();
				ba.deposite(accno);
				break;
			case 5:
				break;
			case 6:
				break;
			case 7:
				System.out.println("Thak You Choosing Our Services");
				break x;
			default:
				System.out.println("Invalid Number");
				break;
			}
		}

	}

}
