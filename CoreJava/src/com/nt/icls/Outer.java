package com.nt.icls;

public class Outer {
	int x = 10;

	private class Inner {
		int x = 100;

		void m1(int x) {

			System.out.println(x);
		}
	}

	public static void main(String[] args) {
		Outer o = new Outer();
		Outer.Inner oi = o.new Inner();
		System.out.println(o.x);
		System.out.println(oi.x);
		oi.m1(1000);
		o.x = 20;
		oi.x = 200;
		System.out.println(o.x);
		System.out.println(oi.x);
		oi.m1(2000);
	}

}
