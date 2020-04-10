package com.nt.stjc;

public class Test {

	public static void main(String[] args) {
		Example e1=Example.getE();
		Example e2=Example.getE();
		System.out.println(e1);
		e1.setX(10);
		e1.setY(20);
		System.out.println(e1);
		System.out.println(e1);
		e2.setX(30);
		System.out.println(e1);
		System.out.println(e1);
	}

}
